package Entities;
import Services.MajorLeagueService1;

import java.util.Objects;

public final class UTM extends MajorLeagueService1 {
    private final String name;
    private final String players;

    public static UTM utm = new UTM("UTM", "Korsakov,Voleanin,Adrian,Romanov");

    private UTM(String name, String players) {
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
        UTM utm = (UTM) o;
        return Objects.equals(name, utm.name) && Objects.equals(players, utm.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, players);
    }
}
