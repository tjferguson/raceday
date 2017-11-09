/*
 * Travis Ferguson - - Apache 2.0 licensed
 * 
 */
package com.overwrittenstack.raceday.model;

/**
 *
 * @author Travis <tjferguson>
 */
public class Participant {
    private int participantId;
    private String name;
    private int raceId;
    private Race race;

    /**
     * @return the participantId
     */
    public int getParticipantId() {
        return participantId;
    }

    /**
     * @param participantId the participantId to set
     */
    public void setParticipantId(int participantId) {
        this.participantId = participantId;
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
     * @return the race
     */
    public Race getRace() {
        return race;
    }

    /**
     * @param race the race to set
     */
    public void setRace(Race race) {
        this.race = race;
    }
    
}
