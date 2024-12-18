package net.minecraft.client;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Canvas;

public class MinecraftApplet extends Applet {
	private Canvas mcCanvas;
	private Minecraft mc;
	private Thread mcThread = null;

	public void init() {
		this.mcCanvas = new CanvasMinecraftApplet(this);
		boolean z1 = false;
		if(this.getParameter("fullscreen") != null) {
			z1 = this.getParameter("fullscreen").equalsIgnoreCase("true");
		}

		this.mc = new Minecraft(this.mcCanvas, this, this.getWidth(), this.getHeight(), z1);
		this.mc.minecraftUri = this.getDocumentBase().getHost();
		if(this.getDocumentBase().getPort() > 0) {
			this.mc.minecraftUri = this.mc.minecraftUri + ":" + this.getDocumentBase().getPort();
		}

		if(this.getParameter("username") != null && this.getParameter("sessionid") != null) {
			this.mc.session = new Session(this.getParameter("username"), this.getParameter("sessionid"));
			if(this.getParameter("mppass") != null) {
				this.mc.session.mpPass = this.getParameter("mppass");

			}
		}

		if(this.getParameter("loadmap_user") != null && this.getParameter("loadmap_id") != null) {
			this.mc.loadMapUser = this.getParameter("loadmap_user");
			this.mc.loadMapID = Integer.parseInt(this.getParameter("loadmap_id"));
		} else if(this.getParameter("server") != null && this.getParameter("port") != null) {
			Minecraft minecraft10000 = this.mc;
			String string10001 = this.getParameter("server");
			int i3 = Integer.parseInt(this.getParameter("port"));
			String string2 = string10001;
			Minecraft minecraft4 = minecraft10000;
			minecraft10000.server = string2;
			minecraft4.port = i3;
		}

		this.mc.appletMode = true;
		this.setLayout(new BorderLayout());
		this.add(this.mcCanvas, "Center");
		this.mcCanvas.setFocusable(true);
		this.validate();
	}

	public final void startMainThread() {
		if(this.mcThread == null) {
			this.mcThread = new Thread(this.mc, "Minecraft main thread");
			this.mcThread.start();
		}
	}

	public void start() {
		this.mc.isGamePaused = false;
	}

	public void stop() {
		this.mc.isGamePaused = true;
	}

	public void destroy() {
		this.shutdown();
	}

	public final void shutdown() {
		if(this.mcThread != null) {
			Minecraft minecraft1 = this.mc;
			this.mc.running = false;

			try {
				this.mcThread.join(1000L);
			} catch (InterruptedException interruptedException3) {
				try {
					this.mc.shutdownMinecraftApplet();
				} catch (Exception exception2) {
					exception2.printStackTrace();
				}
			}

			this.mcThread = null;
		}
	}
}