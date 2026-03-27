package com.smashmap.tournaments.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="tournaments")
@Entity
public class TournamentEntity {

    @Id
    long id;

    String name;

    long countryId;

    LocalDateTime endAt;

    LocalDateTime createdAt;
}