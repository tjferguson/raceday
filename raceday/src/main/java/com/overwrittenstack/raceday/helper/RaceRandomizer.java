/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.helper;

import com.overwrittenstack.raceday.model.Bracket;
import com.overwrittenstack.raceday.model.Participant;
import com.overwrittenstack.raceday.model.Vehicle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Travis
 */
public class RaceRandomizer {
    private static final int[] squares = new int[] {
        0, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096
    };
    
    /**
     * Calculate the number of bye rounds needed to get the list of players/vehicles
     * into a perfect square next round; will return 0 if already in perfect square, or -1 if a bracket
     * race is not possible (i.e. players.size() <= 1 or players.size() > 4096); supports up to 4096 players, which is
     * rather absurd.
     * @param players
     * @return 
     */
    public static int getNumberOfByes(List<Vehicle> players) {
        if(players == null || players.size() <= 1) return -1;
        if(players.size() > 4096) return -1;
        int size = players.size();
        
        for(int i = 0; i < squares.length; i++ ) {
            if(size > squares[i] && size <= squares[i+1]) {
                return squares[i+1]-size;
            }
        }
        return -1;
    }
    
    /**
     * Take an input of vehicles, and make a set of brackets (including byes) for it
     * 
     * @param players
     * @param roundId
     * @param lastBracketId
     * @param shuffle
     * @return 
     */
    public static List<Bracket> makeBracket(List<Vehicle> players, int roundId, int lastBracketId, boolean shuffle) {
       System.out.println(players);
        if(shuffle) Collections.shuffle(players);
       System.out.println(players);
        int byes = getNumberOfByes(players);
        List<Bracket> brackets = new ArrayList<>();
        
        //Construct a list (players-byes)/2 long
        int vsPlayers = (players.size()-byes);  //This should by definition always be an even number
        for(int i = 0; i < vsPlayers; i+= 2) {
            Vehicle v1 = players.get(i);
            Vehicle v2 = players.get(i+1);
            Bracket b = new Bracket();
            b.setRoundId(roundId);
            b.setLastBracketId(lastBracketId);
            b.setVech1(v1);
            b.setVech1Id(v1.getVehicleId());
            b.setVech2(v2);
            b.setVech2Id(v2.getVehicleId());
            brackets.add(b);
        }
        Participant byeP = new Participant();
        byeP.setName("Bye");
        byeP.setParticipantId(-1);
        
        for(int i = vsPlayers; i < players.size(); i++) {
            Vehicle v1 = players.get(i);
            Vehicle v2 = new Vehicle();
            
            v2.setVehicleId(-1);
            v2.setParticipant(byeP);
            
            Bracket b = new Bracket();
            b.setRoundId(roundId);
            b.setLastBracketId(lastBracketId);
            b.setVech1(v1);
            b.setVech1Id(v1.getVehicleId());
            b.setVech2(v2);
            b.setWinner(v1);
            brackets.add(b);
        }
        
        return brackets;
    }
    
}
