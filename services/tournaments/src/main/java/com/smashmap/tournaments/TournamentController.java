package com.smashmap.tournaments;

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

  public TournamentController(StartGG startgg) {
    this.startgg = startgg;
  }

  @GetMapping
  public String get() {
    return "Hello world";
  }

  @GetMapping("{id}")
  public String getById(@PathVariable Long id) {
    return "Hello world" + id;
  }

  @PostMapping
  public String post() {
    return "Hello world";
  }

  @Scheduled(cron = "0 * * * * *")
  @SchedulerLock(name = "tournamentsLock", lockAtMostFor = "10m")
  public void syncTournaments() {
    String query = """
    query TournamentsByCountry {
    tournaments(query: {
        perPage: 512
        filter: {
          afterDate: 1689098512
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
          createdAt
          endAt
        }
      }
    }
    """;
    System.out.println(startgg.sendRequest(query, HttpMethod.POST));
  }

}