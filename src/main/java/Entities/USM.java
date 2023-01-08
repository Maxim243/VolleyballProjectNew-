package Entities;

import Services.MajorLeagueService1;

import java.util.Objects;

public final class USM extends MajorLeagueService1 {
    private final String name;

    private final String players;
    public static USM usm = new USM("USM", "Gorea,Valera,Danik,Doinel");

    private USM(String name, String players) {
        this.name = name;
        this.players = players;
    }


    public String getName() {
        return name;
    }

    public String getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        USM usm = (USM) o;
        return Objects.equals(name, usm.name) && Objects.equals(players, usm.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, players);
    }
}
