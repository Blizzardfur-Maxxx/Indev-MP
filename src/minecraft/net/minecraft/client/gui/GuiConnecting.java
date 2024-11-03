package net.minecraft.client.gui;

import net.minecraft.client.GuiMainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.net.Client;

public class GuiConnecting extends GuiScreen implements Runnable {

    private final String host;
    private final int port;
    private Thread connectionThread;
    private boolean cancelled = false;

    public GuiConnecting(Minecraft minecraft, String host, int port) {
        this.mc = minecraft;
        this.host = host;
        this.port = port;
        
        // Set the server IP and port directly in Minecraft instance
        this.mc.server = host;
        this.mc.port = port;

        System.out.println("Connecting to " + host + ", " + port);

        // Start a new thread to initialize networkClient without affecting the main thread
        this.connectionThread = new Thread(this, "Server Connector");
        this.connectionThread.start();
    }

    @Override
    public void initGui() {
        this.controlList.clear();
        this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Cancel"));
    }

    @Override
    public void updateScreen() {
        // Only display loading messages or progress
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) { // Cancel button
            this.cancelled = true;
            if (this.mc.networkClient != null) {
                this.mc.networkClient.serverConnection.disconnect();
            }
            this.mc.displayGuiScreen(new GuiMainMenu());
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    	mc.currentScreen = null;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        // Prevent any input actions during connecting
    }

    @Override
    public void onGuiClosed() {
        if (this.mc.networkClient != null && !this.cancelled) {
            this.mc.networkClient.serverConnection.disconnect();
        }
    }

    @Override
    public void run() {
        try {
            if (!cancelled) {
                // Set up network client in Minecraft instance without any additional logic
                this.mc.networkClient = new Client(this.mc, this.mc.server, this.mc.port, this.mc.session.username, this.mc.session.mpPass);
            }
        } catch (Exception e) {
            System.err.println("Failed to connect: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
