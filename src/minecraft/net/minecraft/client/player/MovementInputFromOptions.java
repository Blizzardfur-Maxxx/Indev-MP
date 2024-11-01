package net.minecraft.client.player;

import net.minecraft.client.GameSettings;

public final class MovementInputFromOptions extends MovementInput {
	private boolean[] movementKeyStates = new boolean[10];
	private GameSettings gameSettings;

	public MovementInputFromOptions(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
	}

	public final void checkKeyForMovementInput(int i1, boolean z2) {
		byte b3 = -1;
		if(i1 == this.gameSettings.keyBindForward.keyCode) {
			b3 = 0;
		}

		if(i1 == this.gameSettings.keyBindBack.keyCode) {
			b3 = 1;
		}

		if(i1 == this.gameSettings.keyBindLeft.keyCode) {
			b3 = 2;
		}

		if(i1 == this.gameSettings.keyBindRight.keyCode) {
			b3 = 3;
		}

		if(i1 == this.gameSettings.keyBindJump.keyCode) {
			b3 = 4;
		}

		if(b3 >= 0) {
			this.movementKeyStates[b3] = z2;
		}

	}

	public final void resetKeyState() {
		for(int i1 = 0; i1 < 10; ++i1) {
			this.movementKeyStates[i1] = false;
		}

	}

	public final void updatePlayerMoveState() {
		this.moveStrafe = 0.0F;
		this.moveForward = 0.0F;
		if(this.movementKeyStates[0]) {
			++this.moveForward;
		}

		if(this.movementKeyStates[1]) {
			--this.moveForward;
		}

		if(this.movementKeyStates[2]) {
			++this.moveStrafe;
		}

		if(this.movementKeyStates[3]) {
			--this.moveStrafe;
		}

		this.jump = this.movementKeyStates[4];
	}
}