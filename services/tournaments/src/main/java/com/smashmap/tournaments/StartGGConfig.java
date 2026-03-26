package com.smashmap.tournaments;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "startgg.api")
public class StartGGConfig {

    private String url;
    private String token;

    public String getUrl() { 
        return url; 
    }

    public String getToken() { 
        return token; 
    }

    public void setUrl(String url) { 
        this.url = url;
    }
    
    public void setToken(String token) { 
        this.token = token;
    }
}