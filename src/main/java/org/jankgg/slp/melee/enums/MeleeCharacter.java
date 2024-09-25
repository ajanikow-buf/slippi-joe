package org.jankgg.slp.melee.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum MeleeCharacter {
	
    MARIO(0, "Mario"),
    FOX(1, "Fox"),
    CAPTAIN_FALCON(2, "Captain Falcon"),
    DONKEY_KONG(3, "Donkey Kong"),
    KIRBY(4, "Kirby"),
    BOWSER(5, "Bowser"),
    LINK(6, "Link"),
    SHEIK(7, "Sheik"),
    NESS(8, "Ness"),
    PEACH(9, "Peach"),
    POPO(10, "Popo"),
    NANA(11, "Nana"),
    PIKACHU(12, "Pikachu"),
    SAMUS(13, "Samus"),
    YOSHI(14, "Yoshi"),
    JIGGLYPUFF(15, "Jigglypuff"),
    MEWTWO(16, "Mewtwo"),
    LUIGI(17, "Luigi"),
    MARTH(18, "Marth"),
    ZELDA(19, "Zelda"),
    YOUNG_LINK(20, "Young Link"),
    DR_MARIO(21, "Dr. Mario"),
    FALCO(22, "Falco"),
    PICHU(23, "Pichu"),
    GAME_AND_WATCH(24, "Mr. Game & Watch"),
    GANONDORF(25, "Ganondorf"),
    ROY(26, "Roy"),
    MASTER_HAND(27, "Master Hand"),
    CRAZY_HAND(28, "Crazy Hand"),
    WIREFRAME_MALE(29, "Wireframe Male"),
    WIREFRAME_FEMALE(30, "Wireframe Female"),
    GIGA_BOWSER(31, "Giga Bowser"),
    SANDBAG(32, "Sandbag"),
    //not a real character code, used in PlayerMetadata if both nana & popo detected
    ICE_CLIMBERS(-1, "Ice Climbers");
	
    
    private int num;
    private String pretty;
    private static final Map<Integer, MeleeCharacter> ENUM_MAP;
    
    private MeleeCharacter(int num, String pretty) {
        this.num = num;
        this.pretty = pretty;
    }
    
    public int getNum() {
        return num;
    }
    
    //shout out to my main man Joshua Block, dopest design patterns on the mo'fuckin planet
    static {
        Map<Integer, MeleeCharacter> map = new HashMap<Integer, MeleeCharacter>();
        for (MeleeCharacter instance : MeleeCharacter.values()) {
            map.put(instance.getNum(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }
 
  public static MeleeCharacter fromInt(int num) {
      return ENUM_MAP.get(num);
  }
  
  public static MeleeCharacter fromIntString(String num) {
      return ENUM_MAP.get(Integer.parseInt(num));
  }

    public String toString() {
        return pretty;
    }
}
