package com.smashmap.tournaments.wrapper;


public class JsonTournament {
    private Long id;
    private String name;
    private String city;
    private String countryCode;
    private Long startAt;
    private Long createdAt;
    private Long endAt;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getEndAt() {
        return endAt;
    }

    public Long getStartAt() {
        return startAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public void setEndAt(Long endAt) {
        this.endAt = endAt;
    }

    public void setStartAt(Long startAt) {
        this.startAt = startAt;
    }
}