package net.minecraft.client.gui;

import net.minecraft.client.gui.GuiScreen;

public class GuiConnectingScreen extends GuiScreen {
    private String message = "Connecting...";
    private int progress = 0;

    // Constructor
    public GuiConnectingScreen() {}

    // Update progress (optional, if you want to change progress dynamically)
    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // Clear the screen and draw the message
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, this.message, this.width / 2, this.height / 2 - 20, 0xFFFFFF);
        
        // Draw progress bar below the message
        this.drawRect(this.width / 2 - 50, this.height / 2, this.width / 2 - 50 + progress, this.height / 2 + 10, 0xFF00FF00);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void updateScreen() {
        if (progress < 100) {
            progress += 1;
        }
    }
}
