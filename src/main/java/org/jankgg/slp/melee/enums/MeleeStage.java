package org.jankgg.slp.melee.enums;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum MeleeStage {
	
    FOUNTAIN_OF_DREAMS(2, "Fountain of Dreams"),
    POKEMON_STADIUM(3, "Pokemon Stadium"),
    PRINCESS_PEACHS_CASTLE(4, "Princess Peach's Castle"),
    KONGO_JUNGLE(5, "Kongo Jungle"),
    BRINSTAR(6, "Brinstar"),
    CORNERIA(7, "Corneria"),
    YOSHIS_STORY(8, "Yoshi's Story"),
    ONETT(9, "Onett"),
    MUTE_CITY(10, "Mute City"),
    RAINBOW_CRUISE(11, "Rainbow Cruise"),
    JUNGLE_JAPES(12, "Jungle Japes"),
    GREAT_BAY(13, "Great Bay"),
    HYRULE_TEMPLE(14, "Hyrule Temple"),
    BRINSTAR_DEPTHS(15, "Brinstar Depths"),
    YOSHIS_ISLAND(16, "Yoshis Island"),
    GREEN_GREENS(17, "Green Greens"),
    FOURSIDE(18, "Fourside"),
    MUSHROOM_KINGDOM_I(19, "Mushroom Kingdom I"),
    MUSHROOM_KINGDOM_II(20, "Mushrom Kingdom II"),
    VENOM(22, "Venom"),
    POKE_FLOATS(23, "Poke Floats"),
    BIG_BLUE(24, "Big Blue"),
    ICICLE_MOUNTAIN(25, "Icicle Mountain"),
    ICETOP(26, "Icetop"),
    FLAT_ZONE(27, "Flat Zone"),
    DREAM_LAND_N64(28, "Dream Land"),
    YOSHIS_ISLAND_N64(29, "Yoshi's Island"),
    KONGO_JUNGLE_N64(30, "Kong Jungle 64"),
    BATTLEFIELD(31, "Battlefield"),
    FINAL_DESTINATION(32, "Final Destination");

    private int num;
    private String pretty;
    private static final Map<Integer, MeleeStage> ENUM_MAP;
    
    private MeleeStage(int num, String pretty) {
        this.num = num;
        this.pretty = pretty;
    }
    
    public int getNum() {
        return num;
    }

    static {
        Map<Integer, MeleeStage> map = new HashMap<Integer, MeleeStage>();
        for (MeleeStage instance : MeleeStage.values()) {
            map.put(instance.getNum(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }
 
  public static MeleeStage fromInt(int num) {
      return ENUM_MAP.get(num);
  }
  
  public static MeleeStage fromIntString(String num) {
      return ENUM_MAP.get(Integer.parseInt(num));
  }

    public String toString() {
        return pretty;
    }
}
