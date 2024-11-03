package net.minecraft.client.gui;

import net.minecraft.client.GuiMainMenu;

public final class GuiIngameMenu extends GuiScreen {
	public final void initGui() {
		this.controlList.clear();

        // Back to game button at the top
        this.controlList.add(new GuiButton(4, this.width / 2 - 100, this.height / 4, "Back to game"));

        // Options and Generate new level buttons
        this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 24, "Options..."));
        this.controlList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 48, "Generate new level..."));

        // Save level and Load level buttons side by side
        this.controlList.add(new GuiButton(2, this.width / 2 - 102, this.height / 4 + 72, 98, 20, "Save level.."));
        this.controlList.add(new GuiButton(3, this.width / 2 + 2, this.height / 4 + 72, 98, 20, "Load level.."));

        // Exit button at the bottom
        this.controlList.add(new GuiButton(5, this.width / 2 - 100, this.height / 4 + 120, "Exit to main menu"));

		// Disable save/load buttons if no session
		if(this.mc.session == null) {
			((GuiButton)this.controlList.get(2)).enabled = false;
			((GuiButton)this.controlList.get(3)).enabled = false;
		}
	}

	protected final void actionPerformed(GuiButton guiButton1) {
		switch (guiButton1.id) {
			case 0:
				this.mc.displayGuiScreen(new GuiOptions(this, this.mc.options));
				break;
			case 1:
				this.mc.displayGuiScreen(new GuiNewLevel(this));
				break;
			case 2:
				if (this.mc.session != null)
					this.mc.displayGuiScreen(new GuiSaveLevel(this));
				break;
			case 3:
				if (this.mc.session != null)
					this.mc.displayGuiScreen(new GuiLoadLevel(this));
				break;
			case 4:
				this.mc.displayGuiScreen(null);
				this.mc.setIngameFocus();
				break;
			case 5:
				this.mc.theWorld = null;
				this.mc.displayGuiScreen(new GuiMainMenu());
				break;
		}
	}

	public final void drawScreen(int xSize_lo, int ySize_lo, float f3) {
		this.drawDefaultBackground();
		drawCenteredString(this.fontRenderer, "Game menu", this.width / 2, 40, 0xFFFFFF);
		super.drawScreen(xSize_lo, ySize_lo, f3);
	}
}
