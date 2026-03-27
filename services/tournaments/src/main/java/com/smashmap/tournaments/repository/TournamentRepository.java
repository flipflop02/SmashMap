package com.smashmap.tournaments.repository;

import com.smashmap.tournaments.entity.TournamentEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository<TournamentEntity, Long> {

}