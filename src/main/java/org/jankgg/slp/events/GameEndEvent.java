package org.jankgg.slp.events;

import org.jankgg.slp.util.SlippiByteBuffer;

import java.util.Arrays;

public class GameEndEvent {
    public static final byte code = 0x39;
    private short gameEndMethod;
    private byte LRASPlayer;
    private byte[] playerPlacements = new byte[4];
    public GameEndEvent(SlippiByteBuffer buffer, int cmdSize) {
        int startPosition = buffer.position();
        gameEndMethod = buffer.getUnsigned();
        LRASPlayer = buffer.get();
        playerPlacements[0] = buffer.get();
        playerPlacements[1] = buffer.get();
        playerPlacements[2] = buffer.get();
        playerPlacements[3] = buffer.get();
        buffer.position(startPosition + cmdSize);
    }

    public short getGameEndMethod() {
        return gameEndMethod;
    }

    public byte getLRASPlayer() {
        return LRASPlayer;
    }

    public byte[] getPlayerPlacements() {
        return playerPlacements;
    }

    @Override
    public String toString() {
        return "GameEndEvent{" +
                "gameEndMethod=" + gameEndMethod +
                ", LRASPlayer=" + LRASPlayer +
                ", playerPlacements=" + Arrays.toString(playerPlacements) +
                '}';
    }
}
