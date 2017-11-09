/*
 * Travis Ferguson - Apache 2.0 licensed
 */
package com.overwrittenstack.raceday;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author Travis
 */
@ConfigurationProperties(prefix="settings")
@Component
public class SettingsBean {
    private String cloud;

    /**
     * @return the cloud
     */
    public String getCloud() {
        return cloud;
    }

    /**
     * @param cloud the cloud to set
     */
    public void setCloud(String cloud) {
        this.cloud = cloud;
    }
    
}
