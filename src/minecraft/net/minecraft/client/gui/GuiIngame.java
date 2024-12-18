package net.minecraft.client.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.ChatLine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.RenderHelper;
import net.minecraft.client.player.EntityPlayerSP;
import net.minecraft.client.render.entity.RenderItem;
import net.minecraft.game.entity.player.InventoryPlayer;
import net.minecraft.game.item.ItemStack;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public final class GuiIngame extends Gui {
	private static RenderItem itemRenderer = new RenderItem();
	private List chatMessageList = new ArrayList();
	private Random rand = new Random();
	private Minecraft mc;
	private int updateCounter = 0;
	public String testMessage = null;
	private Object hoveredUsername;

	public GuiIngame(Minecraft minecraft1) {
		this.mc = minecraft1;
	}

	public final void renderGameOverlay(float f1) {
		ScaledResolution scaledResolution2;
		int i3 = (scaledResolution2 = new ScaledResolution(this.mc.displayWidth, this.mc.displayHeight)).getScaledWidth();
		int i19 = scaledResolution2.getScaledHeight();
		FontRenderer fontRenderer4 = this.mc.fontRenderer;
		this.mc.entityRenderer.setupOverlayRendering();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/gui.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		InventoryPlayer inventoryPlayer5 = this.mc.thePlayer.inventory;
		this.zLevel = -90.0F;
		this.drawTexturedModalRect(i3 / 2 - 91, i19 - 22, 0, 0, 182, 22);
		this.drawTexturedModalRect(i3 / 2 - 91 - 1 + inventoryPlayer5.currentItem * 20, i19 - 22 - 1, 0, 22, 24, 22);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/icons.png"));
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
		this.drawTexturedModalRect(i3 / 2 - 7, i19 / 2 - 7, 0, 0, 16, 16);
		GL11.glDisable(GL11.GL_BLEND);
		boolean z20 = this.mc.thePlayer.heartsLife / 3 % 2 == 1;
		if(this.mc.thePlayer.heartsLife < 10) {
			z20 = false;
		}

		int i6 = this.mc.thePlayer.health;
		int i7 = this.mc.thePlayer.prevHealth;
		this.rand.setSeed((long)(this.updateCounter * 312871));
		int i10;
		int i12;
		if(this.mc.playerController.shouldDrawHUD()) {
			EntityPlayerSP entityPlayerSP8 = this.mc.thePlayer;
			i10 = this.mc.thePlayer.inventory.getPlayerArmorValue();

			int i11;
			int i13;
			for(i11 = 0; i11 < 10; ++i11) {
				i12 = i19 - 32;
				if(i10 > 0) {
					i13 = i3 / 2 + 91 - (i11 << 3) - 9;
					if((i11 << 1) + 1 < i10) {
						this.drawTexturedModalRect(i13, i12, 34, 9, 9, 9);
					}

					if((i11 << 1) + 1 == i10) {
						this.drawTexturedModalRect(i13, i12, 25, 9, 9, 9);
					}

					if((i11 << 1) + 1 > i10) {
						this.drawTexturedModalRect(i13, i12, 16, 9, 9, 9);
					}
				}

				byte b26 = 0;
				if(z20) {
					b26 = 1;
				}

				int i14 = i3 / 2 - 91 + (i11 << 3);
				if(i6 <= 4) {
					i12 += this.rand.nextInt(2);
				}

				this.drawTexturedModalRect(i14, i12, 16 + b26 * 9, 0, 9, 9);
				if(z20) {
					if((i11 << 1) + 1 < i7) {
						this.drawTexturedModalRect(i14, i12, 70, 0, 9, 9);
					}

					if((i11 << 1) + 1 == i7) {
						this.drawTexturedModalRect(i14, i12, 79, 0, 9, 9);
					}
				}

				if((i11 << 1) + 1 < i6) {
					this.drawTexturedModalRect(i14, i12, 52, 0, 9, 9);
				}

				if((i11 << 1) + 1 == i6) {
					this.drawTexturedModalRect(i14, i12, 61, 0, 9, 9);
				}
			}

			if(this.mc.thePlayer.isInsideOfWater()) {
				i11 = (int)Math.ceil((double)(this.mc.thePlayer.air - 2) * 10.0D / 300.0D);
				i12 = (int)Math.ceil((double)this.mc.thePlayer.air * 10.0D / 300.0D) - i11;

				for(i13 = 0; i13 < i11 + i12; ++i13) {
					if(i13 < i11) {
						this.drawTexturedModalRect(i3 / 2 - 91 + (i13 << 3), i19 - 32 - 9, 16, 18, 9, 9);
					} else {
						this.drawTexturedModalRect(i3 / 2 - 91 + (i13 << 3), i19 - 32 - 9, 25, 18, 9, 9);
					}
				}
			}
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_NORMALIZE);
		GL11.glPushMatrix();
		GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glPopMatrix();

		for(i10 = 0; i10 < 9; ++i10) {
			int i25 = i3 / 2 - 90 + i10 * 20 + 2;
			int i21 = i19 - 16 - 3;
			ItemStack itemStack22;
			if((itemStack22 = this.mc.thePlayer.inventory.mainInventory[i10]) != null) {
				float f9;
				if((f9 = (float)itemStack22.animationsToGo - f1) > 0.0F) {
					GL11.glPushMatrix();
					float f25 = 1.0F + f9 / 5.0F;
					GL11.glTranslatef((float)(i25 + 8), (float)(i21 + 12), 0.0F);
					GL11.glScalef(1.0F / f25, (f25 + 1.0F) / 2.0F, 1.0F);
					GL11.glTranslatef((float)(-(i25 + 8)), (float)(-(i21 + 12)), 0.0F);
				}

				itemRenderer.renderItemIntoGUI(this.mc.renderEngine, itemStack22, i25, i21);
				if(f9 > 0.0F) {
					GL11.glPopMatrix();
				}

				itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, itemStack22, i25, i21);
			}
		}

		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_NORMALIZE);

		if(this.mc.options.showFPS) {
		    fontRenderer4.drawStringWithShadow("Minecraft Indev", 2, 2, 0xFFFFFF);

		    // Check if in network mode and add "MP" if true
		    if (this.mc.isOnlineClient()) {
		        // network mode: Show "MP" in green
		        fontRenderer4.drawStringWithShadow("MP", fontRenderer4.getStringWidth("Minecraft Indev") + 3, 2, 0x00FF00);
		    }

		    // Render debug information
		    Minecraft minecraft23 = this.mc;
		    fontRenderer4.drawStringWithShadow(this.mc.renderGlobal.getDebugInfoRenders(), 2, 12, 0xFFFFFF);
		    fontRenderer4.drawStringWithShadow(this.mc.renderGlobal.getDebugInfoEntities(), 2, 22, 0xFFFFFF);
		    fontRenderer4.drawStringWithShadow("P: " + minecraft23.effectRenderer.getStatistics() + ". T: " + minecraft23.theWorld.debugSkylightUpdates(), 2, 32, 0xFFFFFF);

		    long j24 = Runtime.getRuntime().maxMemory();
		    long j27 = Runtime.getRuntime().totalMemory();
		    long j28 = Runtime.getRuntime().freeMemory();
		    long j16 = j24 - j28;
		    String string18 = "Free memory: " + j16 * 100L / j24 + "% of " + j24 / 1024L / 1024L + "MB";
		    drawString(fontRenderer4, string18, i3 - fontRenderer4.getStringWidth(string18) - 2, 2, 14737632);

		    string18 = "Allocated memory: " + j27 * 100L / j24 + "% (" + j27 / 1024L / 1024L + "MB)";
		    drawString(fontRenderer4, string18, i3 - fontRenderer4.getStringWidth(string18) - 3, 12, 14737632);
		} else {
		    fontRenderer4.drawStringWithShadow("Minecraft Indev", 2, 2, 0xFFFFFF);

		    // Render "MP" only if in network mode
		    if (this.mc.isOnlineClient()) {
		        fontRenderer4.drawStringWithShadow("MP", fontRenderer4.getStringWidth("Minecraft Indev") + 3, 2, 0x00FF00);
		    }
		}
	   	int i14 = i3 / 2;
			int i15 = i19 / 2;
			this.hoveredUsername = null;
			if(Keyboard.isKeyDown(Keyboard.KEY_TAB) && this.mc.networkClient != null && this.mc.networkClient.isConnected()) {
				List list22 = this.mc.networkClient.getUsernames();
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.7F);
				GL11.glVertex2f((float)(i14 + 128), (float)(i15 - 68 - 12));
				GL11.glVertex2f((float)(i14 - 128), (float)(i15 - 68 - 12));
				GL11.glColor4f(0.2F, 0.2F, 0.2F, 0.8F);
				GL11.glVertex2f((float)(i14 - 128), (float)(i15 + 68));
				GL11.glVertex2f((float)(i14 + 128), (float)(i15 + 68));
				GL11.glEnd();
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				String string21 = "Connected players:";
				fontRenderer4.drawStringWithShadow(string21, i14 - fontRenderer4.getStringWidth(string21) / 2, i15 - 64 - 12, 0xFFFFFF);

				for(i7 = 0; i7 < list22.size(); ++i7) {
					int i28 = i14 + i7 % 2 * 120 - 120;
					int i17 = i15 - 64 + (i7 / 2 << 3);
					boolean playerAlive = true;
					if(playerAlive && i3 >= i28 && i19 >= i17 && i3 < i28 + 120 && i19 < i17 + 8) {
						this.hoveredUsername = (String)list22.get(i7);
						fontRenderer4.drawString((String)list22.get(i7), i28 + 2, i17, 0xFFFFFF);
					} else {
						fontRenderer4.drawString((String)list22.get(i7), i28, i17, 15658734);
					}
				}
			}

			int chatLineHeight = 10; // Adjust height according to font size
			int maxVisibleLines = 10; // Number of visible chat lines
			for (int i = 0; i < Math.min(this.chatMessageList.size(), maxVisibleLines); i++) {
			    ChatLine chatline = (ChatLine) this.chatMessageList.get(i);
			    String message = chatline.message;

			    int xPos = 2; // X position for chat messages
			    int yPos = i19 - 40 - (i * chatLineHeight); // Y position based on index and height

			    // Parse and handle color codes in the message
			    int color = 0xFFFFFF; // Default color (white)
			    int messageLength = message.length();
			    int currentXPos = xPos;

			    for (int j = 0; j < messageLength; j++) {
			        if (j < messageLength - 1 && message.charAt(j) == '&') {
			            // If we encounter a color code, parse the next character for color
			            char colorCode = message.charAt(j + 1);
			            color = getColorFromCode(colorCode); // Get color based on the code
			            j++; // Skip the color code character
			        } else {
			            // Draw each character with the current color
			            String character = String.valueOf(message.charAt(j));
			            fontRenderer4.drawStringWithShadow(character, currentXPos, yPos, color);
			            currentXPos += fontRenderer4.getCharWidth(message.charAt(j)); // Move to the next character position
			        }
			    }
			}
	}

			// Helper method to get color from a color code using '&' format
			private int getColorFromCode(char colorCode) {
			    switch (colorCode) {
			        case '0': return 0x000000; // Black
			        case '1': return 0x0000AA; // Dark Blue
			        case '2': return 0x00AA00; // Dark Green
			        case '3': return 0x00AAAA; // Dark Aqua
			        case '4': return 0xAA0000; // Dark Red
			        case '5': return 0xAA00AA; // Dark Purple
			        case '6': return 0xFFAA00; // Gold
			        case '7': return 0xAAAAAA; // Gray
			        case '8': return 0x555555; // Dark Gray
			        case '9': return 0x5555FF; // Blue
			        case 'a': return 0x55FF55; // Green
			        case 'b': return 0x55FFFF; // Aqua
			        case 'c': return 0xFF5555; // Red
			        case 'd': return 0xFF55FF; // Light Purple
			        case 'e': return 0xFFFF55; // Yellow
			        case 'f': return 0xFFFFFF; // White
			        default: return 0xFFFFFF; // Default to white if unknown code
			    }
			}
			
    public final void updateChatMessages() {
        this.updateCounter++;

        for (int i = 0; i < this.chatMessageList.size(); i++) {
            ChatLine chatline = (ChatLine) this.chatMessageList.get(i);

            if (chatline.updateCounter > 200) {
                this.chatMessageList.remove(this.chatMessageList.size() - 1);
            } else {
                chatline.updateCounter++;
            }
        }
    }

    public final void addChatMessage(String message) {
        chatMessageList.add(0, new ChatLine(message));
    }
}