package com.smashmap.tournaments.wrapper;
import java.util.List;


public class JsonTournaments {

    private List<JsonTournament> nodes;

    public void setNodes(List<JsonTournament> nodes) {
        this.nodes = nodes;
    }

    public List<JsonTournament> getNodes() {
        return nodes;
    }
}