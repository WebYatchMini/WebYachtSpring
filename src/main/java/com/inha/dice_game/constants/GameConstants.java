package com.inha.dice_game.constants;

import lombok.Getter;

import java.util.HashMap;

@Getter
public class GameConstants {
    HashMap<Integer,String> picks = new HashMap<>();

    public GameConstants()
    {
        picks.put(0,"Ones");
        picks.put(1,"Twos");
        picks.put(2,"Threes");
        picks.put(3,"Fours");
        picks.put(4,"Fives");
        picks.put(5,"Sixes");
        picks.put(6,"Bonus");
        picks.put(7,"Choice");
        picks.put(8,"3 of a kind");
        picks.put(9,"4 of a kind");
        picks.put(10,"Full House");
        picks.put(11,"S.Straight");
        picks.put(12,"L.Straight");
        picks.put(13,"Yacht");
    }
}
