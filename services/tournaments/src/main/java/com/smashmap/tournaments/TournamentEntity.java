package com.smashmap.tournaments;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TournamentEntity {

    @Id
    long id;

    String name;

    long countryId;

    LocalDateTime endAt;

    LocalDateTime createdAt;
}