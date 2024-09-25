package org.jankgg.slp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jankgg.slp.melee.enums.MeleeCharacter;

import com.devsmart.ubjson.UBObject;
import com.devsmart.ubjson.UBValue;

public class SlippiMetadata {
	private final int lastFrame;
	private final String playedOn;
	private Date startAt;
	private final List<SlippiPlayer> players;
	
	class SlippiPlayer {
		private Integer port;
		private Map<MeleeCharacter, Integer> characterDurations; 
		private Map<String, String> names;
		private MeleeCharacter character;
		private String netplayCode;
		private String displayName;
		
		public Integer getPort() {
			return port;
		}
		public Map<MeleeCharacter, Integer> getCharacterDurations() {
			return characterDurations;
		}
		public Map<String, String> getNames() {
			return names;
		}
		public MeleeCharacter getCharacter() {
			return character;
		}
		public String getNetplayCode() {
			return netplayCode;
		}
		public String getDisplayName() {
			return displayName;
		}
		@Override
		public String toString() {
			return "SlippiPlayer [port=" + port + ", characterDurations=" + characterDurations + ", names=" + names
					+ ", character=" + character + ", netplayCode=" + netplayCode + ", displayName=" + displayName
					+ "]";
		}
	}
	
	public SlippiMetadata(UBObject metadataObject) {
		this.lastFrame = metadataObject.get("lastFrame").asInt();
		this.playedOn = metadataObject.get("playedOn").asString();
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			this.startAt = dateFormat.parse(metadataObject.get("startAt").asString());
		} catch (ParseException e) {
			//failed to parse startAt date, set to epoch.
			startAt = new Date(0);
			// TODO implement proper logger later
			e.printStackTrace();
		}
		
		//----parse players----
		this.players = new ArrayList<SlippiPlayer>();
		for (Entry<String, UBValue> entry : metadataObject.get("players").asObject().entrySet()) {
			SlippiPlayer player = new SlippiPlayer();
			player.port = Integer.parseInt(entry.getKey()) + 1;
			
			//----parse names----
			UBObject namesObject = entry.getValue().asObject().get("names").asObject();
			if (namesObject.get("code") != null) {
				player.netplayCode = namesObject.get("code").asString();
			}
			if (namesObject.get("netplay") != null) {
				player.displayName = namesObject.get("netplay").asString();
			}
			//put all names into a map in case more values are added later (such as displayname which was recently added)
			player.names = new HashMap<String, String>();
			for (Entry<String, UBValue> nameEntry : namesObject.entrySet()) {
				if (nameEntry.getValue().isString()) {
					player.names.put(nameEntry.getKey(), nameEntry.getValue().asString());
				}
			}
			
			//----parse characters----
			player.characterDurations = new HashMap<MeleeCharacter, Integer>();
			UBObject charactersObject  = entry.getValue().asObject().get("characters").asObject();
			for (Entry<String, UBValue> characterEntry : charactersObject.entrySet()) {
				Integer duration = characterEntry.getValue().asInt();
				MeleeCharacter character = MeleeCharacter.fromIntString(characterEntry.getKey());
				player.characterDurations.put(character, duration);
			}
			//if nana & popo detected set player character to ic's
			if (player.characterDurations.containsKey(MeleeCharacter.POPO) && player.characterDurations.containsKey(MeleeCharacter.POPO)) {
				player.character = MeleeCharacter.ICE_CLIMBERS;
			}
			//if zelda & sheik detected set player character to longest duration
			else if (player.characterDurations.containsKey(MeleeCharacter.SHEIK) && player.characterDurations.containsKey(MeleeCharacter.ZELDA)) {
				if (player.characterDurations.get(MeleeCharacter.SHEIK) > player.characterDurations.get(MeleeCharacter.ZELDA)) {
					player.character = MeleeCharacter.ZELDA;
				}
				else {
					player.character = MeleeCharacter.SHEIK;
				}
			}
			//else use the first character in the duration map (there should only be one unless players did something fucky)
			else {
				player.character = player.characterDurations.keySet().iterator().next();
			}
			this.players.add(player);
		}
	}

	public int getLastFrame() {
		return lastFrame;
	}

	public String getPlayedOn() {
		return playedOn;
	}

	public Date getStartAt() {
		return startAt;
	}

	public List<SlippiPlayer> getPlayers() {
		return players;
	}

	@Override
	public String toString() {
		return "SlippiMetadata [lastFrame=" + lastFrame + ", playedOn=" + playedOn + ", startAt=" + startAt
				+ ", players=" + players + "]";
	}
	
}
