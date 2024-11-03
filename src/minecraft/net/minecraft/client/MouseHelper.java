package net.minecraft.client;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

public final class MouseHelper {
	private Component windowComponent;
	private Robot robot;
	private int mouseX;
	private int mouseY;
	private Cursor cursor;
	public int deltaX;
	public int deltaY;
	private int mouseInt = 10;

	public MouseHelper(Component component) {
		this.windowComponent = component;
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		IntBuffer bufferZero = BufferUtils.createIntBuffer(1).put(0);
		bufferZero.flip();
		IntBuffer intBuffer = BufferUtils.createIntBuffer(1024);

		try {
			this.cursor = new Cursor(32, 32, 16, 16, 1, intBuffer, bufferZero);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}

	public final void grabMouseCursor() {
		try {
			Mouse.setNativeCursor(this.cursor);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		this.ungrabMouseCursor();
		this.deltaX = 0;
		this.deltaY = 0;
	}

	public final void ungrabMouseCursor() {
		Point currentLocation = MouseInfo.getPointerInfo().getLocation();
		Point componentLocation = this.windowComponent.getLocationOnScreen();

		this.mouseX = componentLocation.x + this.windowComponent.getWidth() / 2;
		this.mouseY = componentLocation.y + this.windowComponent.getHeight() / 2;
		
		this.robot.mouseMove(this.mouseX, this.mouseY);

		if (this.mouseInt == 0) {
			this.deltaX = currentLocation.x - this.mouseX;
			this.deltaY = currentLocation.y - this.mouseY;
		} else {
			this.deltaX = 0;
			this.deltaY = 0;
			this.mouseInt--;
		}
	}
}
