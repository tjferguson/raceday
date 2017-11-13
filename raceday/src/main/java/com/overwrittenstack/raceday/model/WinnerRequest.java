/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.model;

/**
 *
 * @author Travis
 */
public class WinnerRequest {
    private int winner;
    private int bracketId;

    /**
     * @return the winner
     */
    public int getWinner() {
        return winner;
    }

    /**
     * @param winner the winner to set
     */
    public void setWinner(int winner) {
        this.winner = winner;
    }

    /**
     * @return the bracketId
     */
    public int getBracketId() {
        return bracketId;
    }

    /**
     * @param bracketId the bracketId to set
     */
    public void setBracketId(int bracketId) {
        this.bracketId = bracketId;
    }
    
}
