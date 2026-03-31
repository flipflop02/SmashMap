package com.smashmap.tournaments.controller;

import com.smashmap.tournaments.api.StartGG;
import com.smashmap.tournaments.entity.TournamentEntity;
import com.smashmap.tournaments.repository.TournamentRepository;
import com.smashmap.tournaments.wrapper.GraphQLResponse;
import com.smashmap.tournaments.wrapper.JsonTournament;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@RestController
@RequestMapping("/")
public class TournamentController {

  private final StartGG startgg;

  private final TournamentRepository tournamentRepository;

  public TournamentController(StartGG startgg, TournamentRepository tournamentRepository) {
    this.startgg = startgg;
    this.tournamentRepository = tournamentRepository;
  }

  @GetMapping
  public List<TournamentEntity> get() {
    return tournamentRepository.findAll();
  }

  @GetMapping("{id}")
  public TournamentEntity getById(@PathVariable Long id) {
    return tournamentRepository.findById(id).get();
  }

  @PostMapping
  public String post() {
    return "Hello world";
  }

  @Scheduled(cron = "* */30 * * * *")
  @SchedulerLock(name = "tournamentsLock", lockAtMostFor = "10m")
  public void syncTournaments() {

    String query = """
    query TournamentsByCountry {
    tournaments(query: {
        perPage: 512
        sortBy: "startAt asc"
        filter: {
          upcoming: true
          countryCode: "FR"
                videogameIds: [
                    1386
                ]
        }
      }) {
        nodes {
          id
          name
          city
          countryCode
          startAt
          createdAt
          endAt
        }
      }
    }
    """;

    GraphQLResponse response = startgg.sendRequest(query, HttpMethod.POST);

    List<JsonTournament> tournaments = response.getData().getTournaments().getNodes();


    for (JsonTournament element : tournaments) {
        LocalDateTime startTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(element.getCreatedAt()), ZoneId.systemDefault());
        LocalDateTime startAt = LocalDateTime.ofInstant(Instant.ofEpochSecond(element.getStartAt()), ZoneId.systemDefault());

        LocalDateTime endTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(element.getEndAt()), ZoneId.systemDefault());
        TournamentEntity newEntity = TournamentEntity.builder().name(element.getName()).id(element.getId()).startAt(startAt).createdAt(startTime).endAt(endTime).build();
        
        tournamentRepository.save(newEntity);
    }
  }

}