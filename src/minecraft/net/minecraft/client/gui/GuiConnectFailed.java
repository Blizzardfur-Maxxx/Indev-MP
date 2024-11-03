package net.minecraft.client.gui;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.GuiMainMenu;

public class GuiConnectFailed extends GuiScreen {
	private String errorMessage;
	private String errorDetail;
	private static final Map<String, String> translations = createTranslations();

	public GuiConnectFailed(String string1, String string2, Object... object3) {
		this.errorMessage = translateKey(string1);
		if (object3 != null) {
			this.errorDetail = translateKeyFormat(string2, object3);
		} else {
			this.errorDetail = translateKey(string2);
		}
	}

	// Method to initialize translations manually instead of using a lang file
	private static Map<String, String> createTranslations() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("disconnect.timeout", "Connection timed out");
		map.put("disconnect.genericReason", "Disconnected from server: %s");
		map.put("disconnect.kicked", "You were kicked from the server");
		map.put("disconnect.banned", "You have been banned from this server");
		map.put("disconnect.unreachable", "Server is unreachable");
		map.put("disconnect.protocolMismatch", "Client-server protocol mismatch");
		map.put("disconnect.disconnected", "Disconnected by Server");
		map.put("disconnect.lost", "Connection Lost");
		map.put("disconnect.timeout", "Timed out");
		map.put("disconnect.closed", "Connection closed");
		map.put("disconnect.loginFailed", "Failed to login");
		map.put("disconnect.loginFailedInfo", "Failed to login: %s");
		map.put("disconnect.quitting", "Quitting");
		map.put("disconnect.endOfStream", "End of stream");
		map.put("disconnect.overflow", "Buffer overflow");
		map.put("gui.toMenu", "Back to title screen");

		return map;
	}

	private String translateKey(String key) {
		// Fetches the translated text for the given key, or returns the key itself if not found
		return translations.getOrDefault(key, key);
	}

	private String translateKeyFormat(String key, Object... args) {
		// Formats the translated string with the provided arguments
		String translated = translateKey(key);
		return String.format(translated, args);
	}

	public void updateScreen() {
	}

	protected void keyTyped(char c1, int i2) {
	}

	public void initGui() {
		this.controlList.clear();
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, translateKey("gui.toMenu")));
	}

	protected void actionPerformed(GuiButton guiButton1) {
		if (guiButton1.id == 0) {
			this.mc.displayGuiScreen(new GuiMainMenu());
		}
	}

	public void drawScreen(int i1, int i2, float f3) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, this.errorMessage, this.width / 2, this.height / 2 - 50, 0xFFFFFF);
		this.drawCenteredString(this.fontRenderer, this.errorDetail, this.width / 2, this.height / 2 - 10, 0xFFFFFF);
		super.drawScreen(i1, i2, f3);
	}
}
