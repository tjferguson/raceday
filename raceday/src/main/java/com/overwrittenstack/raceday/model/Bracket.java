/*
 * Travis Ferguson - Apache 2.0 Licensed
 * 
 */
package com.overwrittenstack.raceday.model;

/**
 *
 * @author fergusontra
 */
public class Bracket {
        private int bracketId;
        private int roundId;
        private Round round;
        private int vech1Id;
        private int vech2Id;
        private Vehicle vech1;
        private Vehicle vech2;
        private int winnerId;
        private Vehicle winner;
        private int lastBracketId;
        private Bracket lastBracket;

    /**
     * @return the round
     */
    public Round getRound() {
        return round;
    }

    /**
     * @param round the round to set
     */
    public void setRound(Round round) {
        this.round = round;
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

    /**
     * @return the roundId
     */
    public int getRoundId() {
        return roundId;
    }

    /**
     * @param roundId the roundId to set
     */
    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    /**
     * @return the vech1
     */
    public Vehicle getVech1() {
        return vech1;
    }

    /**
     * @param vech1 the vech1 to set
     */
    public void setVech1(Vehicle vech1) {
        this.vech1 = vech1;
    }

    /**
     * @return the vech2
     */
    public Vehicle getVech2() {
        return vech2;
    }

    /**
     * @param vech2 the vech2 to set
     */
    public void setVech2(Vehicle vech2) {
        this.vech2 = vech2;
    }

    /**
     * @return the winnerId
     */
    public int getWinnerId() {
        return winnerId;
    }

    /**
     * @param winnerId the winnerId to set
     */
    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

    /**
     * @return the winner
     */
    public Vehicle getWinner() {
        return winner;
    }

    /**
     * @param winner the winner to set
     */
    public void setWinner(Vehicle winner) {
        this.winner = winner;
    }

    /**
     * @return the lastBracketId
     */
    public int getLastBracketId() {
        return lastBracketId;
    }

    /**
     * @param lastBracketId the lastBracketId to set
     */
    public void setLastBracketId(int lastBracketId) {
        this.lastBracketId = lastBracketId;
    }

    /**
     * @return the lastBracket
     */
    public Bracket getLastBracket() {
        return lastBracket;
    }

    /**
     * @param lastBracket the lastBracket to set
     */
    public void setLastBracket(Bracket lastBracket) {
        this.lastBracket = lastBracket;
    }

    /**
     * @return the vech1Id
     */
    public int getVech1Id() {
        return vech1Id;
    }

    /**
     * @param vech1Id the vech1Id to set
     */
    public void setVech1Id(int vech1Id) {
        this.vech1Id = vech1Id;
    }

    /**
     * @return the vech2Id
     */
    public int getVech2Id() {
        return vech2Id;
    }

    /**
     * @param vech2Id the vech2Id to set
     */
    public void setVech2Id(int vech2Id) {
        this.vech2Id = vech2Id;
    }
}
