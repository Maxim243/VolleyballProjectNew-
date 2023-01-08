package Repository;

import Services.MajorLeagueService1;

import java.io.IOException;
import java.util.Map;

public interface ChampionshipRepository {

    Map<String, String> scoreFileReader(String file) throws IOException;

    void putMatches();

    void getResultOfAllMatches();

    int getResultScoreByTeam(MajorLeagueService1 team);

    void schedule();

    void scheduleFileReader(String path);

}
