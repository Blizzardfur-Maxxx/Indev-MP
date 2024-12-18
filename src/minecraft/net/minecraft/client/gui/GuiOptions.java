package net.minecraft.client.gui;

import net.minecraft.client.GameSettings;

public final class GuiOptions extends GuiScreen {
	
	private GuiScreen parentScreen;
	private String screenTitle = "Options";
	private GameSettings options;

	public GuiOptions(GuiScreen var1, GameSettings var2) {
		this.parentScreen = var1;
		this.options = var2;
	}

	// This method will be called whenever the screen is resized (so the GUI buttons don't break when resizing the window or entering/exiting fullscreen)
	public void resize(int width, int height) {
	    this.width = width;
	    this.height = height;
	    this.initGui(); // Reinitialize GUI elements with the new size
	}

	public final void initGui() {
		// Clear existing controls to avoid duplications
		this.controlList.clear();

		for(int i = 0; i < this.options.numberOfOptions; i++) {
			this.controlList.add(new GuiButton(i, this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), 150, 20, this.options.setOptionString(i)));
		}

		this.controlList.add(new GuiButton(100, this.width / 2 - 100, this.height / 6 + 120 + 12, "Controls..."));
		this.controlList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, "Done"));
	}

	protected final void actionPerformed(GuiButton button) {
		
		if (!button.enabled)
			return;
			
		if (button.id < 100) {
			this.options.setOptionValue(button.id, 1);
			((GuiButton) button).displayString = this.options.setOptionString(button.id);
		}

		else if (button.id == 100) {
			this.mc.displayGuiScreen(new GuiControls(this, this.options));
		}

		else if (button.id == 200) {
			this.mc.displayGuiScreen(this.parentScreen);
		}
	}

	public final void drawScreen(int mouseX, int mouseY, float f3) {
		this.drawDefaultBackground();
		drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 20, 16777215);
		
		super.drawScreen(mouseX, mouseY, f3);
	}
}
