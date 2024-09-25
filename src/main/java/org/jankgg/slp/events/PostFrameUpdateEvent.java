package org.jankgg.slp.events;

import org.jankgg.slp.util.SlippiByteBuffer;

import java.nio.ByteBuffer;

public class PostFrameUpdateEvent {
    public static final byte code = 0x38;
    private int frameNumber;
    private short playerIndex;
    private byte isFollower;
    private short internalCharacterId;
    private int actionStateId;
    private float xPosition;
    private float yPosition;
    private float facingDirection;
    private float percent;
    private float shieldSize;
    private short lastHittingAttackId;
    private short currentComboCount;
    private short lastHitByPlayer;
    private short stocksRemaining;
    private float actionStateFrameCounter;
    private short stageBitFlags1;
    private short stageBitFlags2;
    private short stageBitFlags3;
    private short stageBitFlags4;
    private short stageBitFlags5;
    private float hitStunRemaining;
    private byte grounded;
    private int lastGroundId;
    private short jumpsRemaining;
    private short lCancelStatus;  // 0 = none, 1 = success, 2 = fail
    private short hurtboxCollisionState; //0 = vulnerable, 1 = invulnerable, 2 = intangible
    private float selfInducedAirSpeedX;
    private float selfInducedSpeedY;
    private float attackBasedXSpeed; //Negative means left, Positive means right
    private float attackBasedYSpeed; //Negative means left, Positive means right
    private float selfInducedGroundSpeedX;    //Negative means left, Positive means right
    private float hitlagFramesRemaining; //0 means "not in hitlag"
    private long animationIndex;
    private int instanceHitBy;
    private int indexId;

    public PostFrameUpdateEvent(SlippiByteBuffer buffer, int cmdSize) {
        System.out.println("Reading postframeupdate, expected size: " + cmdSize);
        System.out.println("Reading postframeupdate, buffer position: " + buffer.position());
        int startPosition = buffer.position();
        frameNumber = buffer.getInt();
        playerIndex = buffer.getUnsigned();
        isFollower = buffer.get();
        internalCharacterId = buffer.getUnsigned();
        actionStateId = buffer.getUnsignedShort();
        xPosition = buffer.getFloat();
        yPosition = buffer.getFloat();
        facingDirection = buffer.getFloat();
        percent = buffer.getFloat();
        shieldSize = buffer.getFloat();
        lastHittingAttackId = buffer.getUnsigned();
        currentComboCount = buffer.getUnsigned();
        lastHitByPlayer = buffer.getUnsigned();
        stocksRemaining = buffer.getUnsigned();
        actionStateFrameCounter = buffer.getFloat();
        stageBitFlags1 = buffer.getUnsigned();
        stageBitFlags2 = buffer.getUnsigned();
        stageBitFlags3 = buffer.getUnsigned();
        stageBitFlags4 = buffer.getUnsigned();
        stageBitFlags5 = buffer.getUnsigned();
        hitStunRemaining = buffer.getFloat();
        grounded = buffer.get();
        lastGroundId = buffer.getUnsignedShort();
        jumpsRemaining = buffer.getUnsigned();
        lCancelStatus = buffer.getShort();
        hurtboxCollisionState = buffer.getShort();
        selfInducedAirSpeedX = buffer.getFloat();
        selfInducedSpeedY = buffer.getFloat();
        attackBasedXSpeed = buffer.getFloat();
        attackBasedYSpeed = buffer.getFloat();
        selfInducedGroundSpeedX = buffer.getFloat();
        hitlagFramesRemaining = buffer.getFloat();
        animationIndex = buffer.getUnsignedInt();
        instanceHitBy = buffer.getUnsignedShort();
        indexId = buffer.getUnsignedShort();

        System.out.println("Actual position:" + buffer.position());
        System.out.println("Intended position:" + (startPosition + cmdSize));
        //we're missing 2 bytes for some reason so just fix for now, no time to debug wooo
        buffer.position(startPosition + cmdSize);
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public short getPlayerIndex() {
        return playerIndex;
    }

    public byte getIsFollower() {
        return isFollower;
    }

    public short getInternalCharacterId() {
        return internalCharacterId;
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

    public float getPercent() {
        return percent;
    }

    public float getShieldSize() {
        return shieldSize;
    }

    public short getLastHittingAttackId() {
        return lastHittingAttackId;
    }

    public short getCurrentComboCount() {
        return currentComboCount;
    }

    public short getLastHitByPlayer() {
        return lastHitByPlayer;
    }

    public short getStocksRemaining() {
        return stocksRemaining;
    }

    public float getActionStateFrameCounter() {
        return actionStateFrameCounter;
    }

    public short getStageBitFlags1() {
        return stageBitFlags1;
    }

    public short getStageBitFlags2() {
        return stageBitFlags2;
    }

    public short getStageBitFlags3() {
        return stageBitFlags3;
    }

    public short getStageBitFlags4() {
        return stageBitFlags4;
    }

    public short getStageBitFlags5() {
        return stageBitFlags5;
    }

    public float getHitStunRemaining() {
        return hitStunRemaining;
    }

    public byte getGrounded() {
        return grounded;
    }

    public int getLastGroundId() {
        return lastGroundId;
    }

    public short getJumpsRemaining() {
        return jumpsRemaining;
    }

    public short getlCancelStatus() {
        return lCancelStatus;
    }

    public short getHurtboxCollisionState() {
        return hurtboxCollisionState;
    }

    public float getSelfInducedAirSpeedX() {
        return selfInducedAirSpeedX;
    }

    public float getSelfInducedSpeedY() {
        return selfInducedSpeedY;
    }

    public float getAttackBasedXSpeed() {
        return attackBasedXSpeed;
    }

    public float getAttackBasedYSpeed() {
        return attackBasedYSpeed;
    }

    public float getSelfInducedGroundSpeedX() {
        return selfInducedGroundSpeedX;
    }

    public float getHitlagFramesRemaining() {
        return hitlagFramesRemaining;
    }

    public long getAnimationIndex() {
        return animationIndex;
    }

    public int getInstanceHitBy() {
        return instanceHitBy;
    }

    public int getIndexId() {
        return indexId;
    }
}
