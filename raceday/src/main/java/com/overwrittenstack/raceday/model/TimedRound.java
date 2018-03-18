/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.model;

/**
 *
 * @author Travis
 */
public class TimedRound {
    private int timedRoundId;
    private int roundId;
    private int vechicleId;
    private int lane;
    private float time;
    private boolean completed;
    

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
     * @return the vechicleId
     */
    public int getVechicleId() {
        return vechicleId;
    }

    /**
     * @param vechicleId the vechicleId to set
     */
    public void setVechicleId(int vechicleId) {
        this.vechicleId = vechicleId;
    }

    /**
     * @return the lane
     */
    public int getLane() {
        return lane;
    }

    /**
     * @param lane the lane to set
     */
    public void setLane(int lane) {
        this.lane = lane;
    }

    /**
     * @return the time
     */
    public float getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(float time) {
        this.time = time;
    }

    /**
     * @return the timedRoundId
     */
    public int getTimedRoundId() {
        return timedRoundId;
    }

    /**
     * @param timedRoundId the timedRoundId to set
     */
    public void setTimedRoundId(int timedRoundId) {
        this.timedRoundId = timedRoundId;
    }

    /**
     * @return the completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
}
