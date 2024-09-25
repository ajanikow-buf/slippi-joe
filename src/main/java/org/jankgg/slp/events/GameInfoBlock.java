package org.jankgg.slp.events;

import org.jankgg.slp.melee.enums.MeleeStage;
import org.jankgg.slp.util.SlippiByteBuffer;

public class GameInfoBlock {
	public static int size = 312;
	private MeleeStage stage;
	private boolean teams;
	
	public GameInfoBlock(SlippiByteBuffer buffer, int gameStartPosition) {
	  
    //jfc why does he include the command byte in all his offsets
	  //but not in any of the payload sizes except for the payloads event payload looool
	  //inconsistentawsd9fhsf
	  gameStartPosition--;
	  int gameInfoPosition = buffer.position() - 1;
	  //Skiping gamebit fields
	  buffer.position(gameStartPosition + 0XD);
	  short teamsBoolean = buffer.getUnsigned();
	  teams = teamsBoolean == 1;
	  //skipping item behavior, and self destruct score
	  buffer.position(gameStartPosition + 0X13);
	  int stageNum = buffer.getUnsignedShort();
	  stage = MeleeStage.fromInt(stageNum);
	  //skip the rest for now
	  buffer.position(gameInfoPosition + size);
	}

  public MeleeStage getStage() {
    return stage;
  }

  public boolean isTeams() {
    return teams;
  }

}
