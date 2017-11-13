/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.model;

/**
 * Wrapper for some data obtained during registration process namely data required
 * to complete the participant and vehicle object for creation
 * 
 * @author Travis
 */
public class Registration {
    private String name;
    private String vehicleTag;
    private int raceId;
    
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
     * @return the vehicleTag
     */
    public String getVehicleTag() {
        return vehicleTag;
    }

    /**
     * @param vehicleTag the vehicleTag to set
     */
    public void setVehicleTag(String vehicleTag) {
        this.vehicleTag = vehicleTag;
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
   
}
