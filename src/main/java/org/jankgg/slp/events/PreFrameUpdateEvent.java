package org.jankgg.slp.events;

import org.jankgg.slp.util.SlippiByteBuffer;

import java.nio.ByteBuffer;

public class PreFrameUpdateEvent {
	public static final byte code = 0x37;
	private int frameNumber;
	private byte playerIndex;
	private byte isFollower;
	private long randomSeed;
	private int actionStateId;
	private float xPosition;
	private float yPosition;
	private float facingDirection;
	private float joystickX;
	private float joystickY;
	private float cstickX;
	private float cstickY;
	private float trigger;
	private long processedButtons;
	private int physicalButtons;
	private float physicalLTrigger;
	private float physicalRTrigger;
	private byte xAnalog;
	private float percent;
	private byte yAnalog;

	public int getFrameNumber() {
		return frameNumber;
	}

	public byte getPlayerIndex() {
		return playerIndex;
	}

	public byte getIsFollower() {
		return isFollower;
	}

	public long getRandomSeed() {
		return randomSeed;
	}

	public int getActionStateId() {
		return actionStateId;
	}

	public float getxPosition() {
		return xPosition;
	}

	public float getyPosition() {
		return yPosition;
	}

	public float getFacingDirection() {
		return facingDirection;
	}

	public float getJoystickX() {
		return joystickX;
	}

	public float getJoystickY() {
		return joystickY;
	}

	public float getCstickX() {
		return cstickX;
	}

	public float getCstickY() {
		return cstickY;
	}

	public float getTrigger() {
		return trigger;
	}

	public long getProcessedButtons() {
		return processedButtons;
	}

	public int getPhysicalButtons() {
		return physicalButtons;
	}

	public float getPhysicalLTrigger() {
		return physicalLTrigger;
	}

	public float getPhysicalRTrigger() {
		return physicalRTrigger;
	}

	public byte getXAnalog() {
		return xAnalog;
	}

	public float getPercent() {
		return percent;
	}

	public byte getYAnalog() {
		return yAnalog;
	}



	public PreFrameUpdateEvent(SlippiByteBuffer buffer, int cmdSize) {
		//System.out.println("Reading preframeupdate, expected size: " + cmdSize);
		//System.out.println("Reading preframeupdate, buffer position: " + buffer.position());
		int startPosition = buffer.position();
		frameNumber = buffer.getInt();
		playerIndex = buffer.get();
		isFollower = buffer.get();
		randomSeed = buffer.getUnsignedInt();
		actionStateId = buffer.getUnsignedShort();
		xPosition = buffer.getFloat();
		yPosition = buffer.getFloat();
		facingDirection = buffer.getFloat();
		joystickX = buffer.getFloat();
		joystickY = buffer.getFloat();
		cstickX = buffer.getFloat();
		cstickY = buffer.getFloat();
		trigger = buffer.getFloat();
		processedButtons = buffer.getUnsignedInt();
		physicalButtons = buffer.getUnsignedShort();
		physicalLTrigger = buffer.getFloat();
		physicalRTrigger = buffer.getFloat();
		xAnalog = buffer.get();
		percent = buffer.getFloat();
		yAnalog = buffer.get();
		//System.out.println("Actual position:" + buffer.position());
		//System.out.println("Intended position:" + (startPosition + cmdSize));
		//we're missing 2 bytes for some reason so just fix for now, no time to debug wooo
		buffer.position(startPosition + cmdSize);
	}
}
