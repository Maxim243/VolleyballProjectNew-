package Services;

import Entities.Game;
import Repository.ChampionshipRepository;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static Entities.DOR.dor;
import static Entities.USM.usm;
import static Entities.UTM.utm;

public class MajorLeagueService1 implements ChampionshipRepository {
    private String name;
    private List<String> teamList = new ArrayList<>();
    private List<Game> games = new ArrayList<>();


    public MajorLeagueService1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public MajorLeagueService1() {
    }

    public static Map<String, String> resultMap = new HashMap<>();

    public static Set<Object> teamsSet = new HashSet<>();

    public static Map<String, String> scheduleMap = new HashMap<>();

    {
        teamsSet.add(dor);
        teamsSet.add(usm);
        teamsSet.add(utm);
    }

    static {
        resultMap.put("DOR-USM", "3:1");
        resultMap.put("UTM-DOR", "3:1");
    }


    {
        String team1 = "DOR";
        String team2 = "UTM";
        String team3 = "USM";
        teamList.add(team1);
        teamList.add(team2);
        teamList.add(team3);
        Game game1 = new Game(team1, team2, 1, 3);
        Game game2 = new Game(team3, team2, 2, 3);
        games.add(game1);
        games.add(game2);
    }


    @Override

    public Map<String, String> scoreFileReader(String pathFile) {
        Set<String> set = new HashSet<>();
        String str = "";
        String filter = "";
        try (Scanner scanner = new Scanner(new File(pathFile))) {
            while (scanner.hasNextLine()) {
                str = str.concat(scanner.nextLine() + " ");
            }
        } catch (IOException exception) {
            System.out.println("Сaught IO EXCEPTION");
        }

        for (int i = 0; i < str.length(); i++) {
            if ('-' == str.charAt(i)) {
                filter = str.replace("-", " ");
            }
        }
        String[] arrayFilter = filter.split(" ");
        set.add(dor.getName());
        set.add(usm.getName());
        set.add(utm.getName());


        for (int i = 0; i < arrayFilter.length; i++) {
            if (!(set.contains(arrayFilter[i])) && (i + 1) % 3 != 0) {
                throw new RuntimeException("PROZOROV, there is no such team in the championship : " + arrayFilter[i]);
            }
        }
        String[] arrayResult = str.split(" ");
        for (int i = 0; i < arrayResult.length; i++) {
            resultMap.put(arrayResult[i], arrayResult[i + 1]);
            i++;
        }
        return resultMap;
    }

    @Override
    public void getResultOfAllMatches() {
        resultMap.forEach((s, s1) -> {
            System.out.println(s);
            System.out.println(s1);
        });
    }

    @Override
    public int getResultScoreByTeam(MajorLeagueService1 team) {
        String teamExample = "";
        String scoreExample = "";
        int count = 0;


        for (Map.Entry<String, String> entry : MajorLeagueService1.resultMap.entrySet()) {
            teamExample = teamExample.concat(entry.getKey() + "-");
            scoreExample = scoreExample.concat(entry.getValue() + ":");
        }

        String[] teamsArray = teamExample.split("-");
        String[] scoreArray = scoreExample.split(":");

        for (int i = 0; i < teamsArray.length; i++) {
            if (teamsArray[i].equals(team.getName())) {
                count = count + Integer.parseInt(scoreArray[i]);
            }
        }
        return count;
    }

    @Override
    public void schedule() {
        scheduleMap.forEach((s, s1) -> {
            System.out.println(s);
            System.out.println(s1);
        });
    }

    public void scheduleFileReader(String path) {
        String str = "";
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                str = str.concat(scanner.nextLine() + " ");
            }
        } catch (IOException exception) {
            System.out.println("Сaught IO EXCEPTION");
        }
        String[] array = str.split(" ");

        for (int i = 0; i < array.length - 1; i++) {
            scheduleMap.put(array[i], array[i + 1]);
            i++;
        }
    }


    @Override
    public void putMatches() {
        String teams = "";
        String score = "";
        Scanner scanner = new Scanner(System.in);
        do {
            teams = scanner.next();
            if (teams.equals("stop")) {
                break;
            }
            score = scanner.next();
            resultMap.put(teams, score);
        } while (true);
    }

    public void action() {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Please choose action: ");
            System.out.println("0 - statistics");
            System.out.println("1 - enter results of the game");
            System.out.println("2 - edit result of match");
            System.out.println("'-1' - EXIT");
            int count = scanner.nextInt();
            MajorLeagueService1 majorLeague = new MajorLeagueService1();
            if (count == -1) {
                break;
            }
            if (count == 0) {
                majorLeague.statistic();
            }
            if (count == 1) {
                majorLeague.enterResultsOfTheGame();
            }
            if (count == 2) {
                majorLeague.editResultsOfTheGame();
            }

        }
        while (true);
    }

    // Pasha task

    public void statistic() {
        System.out.println("TEAM STATISTIC");
        System.out.println("Team | Matches | Points");
        for (int i = 0; i < teamList.size(); i++) {
            int count = 0;
            int score = 0;
            for (int j = 0; j < games.size(); j++) {
                if (teamList.get(i).equals(games.get(j).getTeam1()) || teamList.get(i).equals(games.get(j).getTeam2())) {
                    count++;
                }

                if (teamList.get(i).equals(games.get(j).getTeam1()) || teamList.get(i).equals(games.get(j).getTeam2())) {

                    if (teamList.get(i).equals(games.get(j).getTeam1())) {
                        score = score + games.get(j).getScoreTeam1();
                    } else score = score + games.get(j).getScoreTeam2();
                }
            }
            System.out.println(teamList.get(i) + " | " + count + " | " + score);
        }
    }

    public void enterResultsOfTheGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter result of the game");
        System.out.println("To go to the main menu, write number '-1'");
        do {
            System.out.print("First Team: ");
            String team1 = scanner.next();
            if (team1.equals("-1")) break;
            System.out.print("Second Team: ");
            String team2 = scanner.next();
            if (team2.equals("-1")) break;
            System.out.print("First Team Points: ");
            int scoreTeam1 = scanner.nextInt();
            if (scoreTeam1 == -1) break;
            System.out.print("Second Team Points: ");
            int scoreTeam2 = scanner.nextInt();
            if (scoreTeam2 == -1) break;
            Game game = new Game(team1, team2, scoreTeam1, scoreTeam2);
            games.add(game);
        } while (true);
    }

    public void editResultsOfTheGame() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("To go to the main menu, enter number '-1'");
            System.out.println("Write number of the match you want to edit: ");
            for (int i = 0; i < games.size(); i++) {
                System.out.println(i + " - " + games.get(i));
            }
            int numberOfMatch = scanner.nextInt();
            if (numberOfMatch == -1) break;
            for (int i = 0; i < games.size(); i++) {
                System.out.println(i + " - " + games.get(i));
                if (numberOfMatch == games.indexOf(games.get(i))) {
                    System.out.print("You chose game: ");
                    System.out.println(games.get(i));
                    System.out.println();
                    System.out.println("Please enter edited match:");
                    System.out.print("Enter first team: ");
                    String team1 = scanner.next();
                    if (team1.equals("-1")) break;
                    System.out.print("Enter second team: ");
                    String team2 = scanner.next();
                    if (team2.equals("-1")) break;
                    System.out.print("Enter first team Points: ");
                    int scoreTeam1 = scanner.nextInt();
                    if (scoreTeam1 == -1) break;
                    System.out.print("Enter second team Points: ");
                    int scoreTeam2 = scanner.nextInt();
                    if (scoreTeam2 == -1) break;
                    Game game = new Game(team1, team2, scoreTeam1, scoreTeam2);
                    games.set(numberOfMatch, game);
                    System.out.println("THE GAME WAS EDITED");
                }
            }
        } while (true);
    }
}


