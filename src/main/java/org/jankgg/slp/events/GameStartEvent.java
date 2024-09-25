package org.jankgg.slp.events;

import java.io.IOException;
import java.util.Arrays;

import org.jankgg.slp.util.SlippiByteBuffer;

public class GameStartEvent {
  public static final byte code = 0x36;
  // major.minor.build.unused
  private short[] version = new short[4];
  private String versionString;
  private GameInfoBlock gameInfoBlock;
  private boolean online;
  
  public GameStartEvent(SlippiByteBuffer buffer, int size) throws IOException {
    int gameStartPosition = buffer.position();
    // read version
    for (int i = 0; i < 4; i++) {
      version[i] = buffer.getUnsigned();
    }
    gameInfoBlock = new GameInfoBlock(buffer, gameStartPosition);
    
    if (Arrays.equals(version, new short[]{3,7,0,0})) {
      buffer.position(gameStartPosition - 1 + 0x1A4);
      online = buffer.getUnsigned() == 8 ? true : false;
    }

    // Skip the remaining stuff for now, maybe we'll care about this data later
    buffer.position(gameStartPosition + size);
  }


  public short[] getVersion() {
    return version;
  }
  
  public String getVersionString() {
    return version[0] + "." + version[1] + "." + version[2] + "." + version[3];
  }

  public GameInfoBlock getGameInfoBlock() {
    return gameInfoBlock;
  }

  public boolean isOnline() {
    return online;
  }
}
