package net.minecraft.client.gui;

import org.lwjgl.input.Keyboard;

public final class GuiMultiplayer extends GuiScreen {
    private GuiScreen parentScreen;
    private String title = "Enter server address:";
    private String serverIP;
    private int counter = 0;

    public GuiMultiplayer(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;

        // Initialize serverIP with last server from options if available, otherwise an empty string
        this.serverIP = (this.mc != null && this.mc.options != null && this.mc.options.lastServer != null)
                        ? ((String) this.mc.options.lastServer).replace("_", ":")
                        : "";
    }

    @Override
    public final void initGui() {
        this.controlList.clear();
        Keyboard.enableRepeatEvents(true);
        this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120, "Connect"));
        this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 144, "Cancel"));
        
        // Enable connect button only if the input IP is not empty
        ((GuiButton) this.controlList.get(0)).enabled = this.serverIP.trim().length() > 0;
    }

    @Override
    public final void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public final void updateScreen() {
        ++this.counter;
    }

    @Override
    protected final void actionPerformed(GuiButton button) {
        if (button.enabled) {
            if (button.id == 0 && this.serverIP.trim().length() > 0) {
                // Save the server IP and connect
                this.mc.options.lastServer = this.serverIP.replace(":", "_");
                this.mc.options.saveOptions();
                
                // Parse IP and port if specified
                String[] addressParts = this.serverIP.split(":");
                if (addressParts.length > 2) {
                    addressParts = new String[]{this.serverIP};
                }

                // Connect to the parsed IP and port (default: 25565)
                this.mc.displayGuiScreen(new GuiConnecting(this.mc, addressParts[0], addressParts.length > 1 ? parseIntWithDefault(addressParts[1], 25565) : 25565));
            }

            if (button.id == 1) {
                // Return to the parent screen if "Cancel" is clicked
                this.mc.displayGuiScreen(this.parentScreen);
            }
        }
    }

    private int parseIntWithDefault(String input, int defaultValue) {
        try {
            return Integer.parseInt(input.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    protected final void keyTyped(char character, int keyCode) {
        // Handle backspace for deleting characters
        if (keyCode == 14 && this.serverIP.length() > 0) {
            this.serverIP = this.serverIP.substring(0, this.serverIP.length() - 1);
        }
        
        // Allow characters for IP entry and limit length to 128
        String allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,:_";
        if (allowedCharacters.indexOf(character) >= 0 && this.serverIP.length() < 128) {
            this.serverIP = this.serverIP + character;
        }

        // Enable connect button if input field has text
        ((GuiButton) this.controlList.get(0)).enabled = this.serverIP.trim().length() > 0;
    }

    @Override
    public final void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        drawCenteredString(this.fontRenderer, this.title, this.width / 2, 40, 0xFFFFFF);

        int xPos = this.width / 2 - 100;
        int yPos = this.height / 2 - 10;
        
        // Draw input box
        drawRect(xPos - 1, yPos - 1, xPos + 200 + 1, yPos + 20 + 1, -6250336);
        drawRect(xPos, yPos, xPos + 200, yPos + 20, 0xFF000000);
        
        // Draw entered text with cursor
        String displayText = this.serverIP + (this.counter / 6 % 2 == 0 ? "_" : "");
        drawString(this.fontRenderer, displayText, xPos + 4, yPos + 6, 14737632);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
