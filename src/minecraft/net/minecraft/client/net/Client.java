package net.minecraft.client.net;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.game.item.ItemStack;

public final class Client {
	public ByteArrayOutputStream levelBuffer;
	public SocketConnection serverConnection;
	public Minecraft minecraft;
	public boolean processData = false;
	public boolean connected = false;
	public HashMap players = new HashMap();

	public Client(Minecraft minecraft, String ip, int port, String user, String session) {
		minecraft.skipRenderWorld = true;
		this.minecraft = minecraft;
		(new ConnectionThread(this, ip, port, user, session, minecraft)).start();
	}

	public final void sendTileUpdated(int x, int y, int z, int id1, int id2) {
	    System.out.println("sendTileUpdated called with parameters: x=" + x + ", y=" + y + ", z=" + z + ", id1=" + id1 + ", id2=" + id2);
	    this.serverConnection.sendPacket(Packet.PLACE_OR_REMOVE_TILE, new Object[]{x, y, z, id1, id2});
	    System.out.println("Packet sent with PLACE_OR_REMOVE_TILE, data={" + x + ", " + y + ", " + z + ", " + id1 + ", " + id2 + "}");
	}
	
	public final void handleException(Exception e) {
		this.serverConnection.disconnect();
		this.minecraft.displayGuiScreen(new GuiErrorScreen("Disconnected!", e.getMessage()));
		e.printStackTrace();
	}

	public final boolean isConnected() {
		SocketConnection socketConnection1;
		return this.serverConnection != null && (socketConnection1 = this.serverConnection).connected;
	}

	public final List getUsernames() {
		ArrayList arrayList1;
		(arrayList1 = new ArrayList()).add(this.minecraft.session.username);
		Iterator iterator3 = this.players.values().iterator();

		while(iterator3.hasNext()) {
			NetworkPlayer networkPlayer2 = (NetworkPlayer)iterator3.next();
			arrayList1.add(networkPlayer2.name);
		}

		return arrayList1;
	}
}