package Entities;

import Services.MajorLeagueService1;

import java.util.Objects;

public final class DOR extends MajorLeagueService1 {
    public static DOR dor = new DOR("DOR", "Jenia,Igor,Artur,Gleb");

    private final String name;

    private final String players;

    private DOR(String name, String players) {
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
        DOR dor = (DOR) o;
        return Objects.equals(name, dor.name) && Objects.equals(players, dor.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, players);
    }

}
