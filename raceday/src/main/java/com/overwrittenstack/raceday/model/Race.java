/*
 * Travis Ferguson - - Apache 2.0 licensed
 * 
 */
package com.overwrittenstack.raceday.model;

/**
 *
 * @author Travis <tjferguson>
 */
public class Race {
    private int raceId;
    private String name;

    /**
     * @return the raceId
     */
    public int getRaceId() {
        return raceId;
    }

    /**
     * @param raceId the raceId to set
     */
    public void setRaceId(int raceId) {
        this.raceId = raceId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
