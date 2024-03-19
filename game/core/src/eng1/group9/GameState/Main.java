package eng1.group9.GameState;

import eng1.group9.GameState.Activities.Eat;
import eng1.group9.GameState.Activities.Recreation;
import eng1.group9.GameState.Activities.Sleep;
import eng1.group9.GameState.Activities.Study;
import eng1.group9.MapGraph;

public class Main {
    public static void main(String[] args) {

        TilePosition tile = new TilePosition(14,6);
        Player play = new Player(tile);
        MapGraph map = new MapGraph();

        GamesState game = new GamesState(100, 100, play, map);

        Eat eatActivity = new Eat(10,10);
        Sleep sleepActivity = new Sleep(10,10);
        Study studyActivity = new Study(10,10);
        Recreation recreationActivity = new Recreation(10,10);

        System.out.println("Time, energy");
        System.out.println(game.getTime());
        System.out.println(game.getEnergy());

//        System.out.println(game.performActivity(sleepActivity));
//        System.out.println("Time, energy");
//        System.out.println(game.getTime());
//        System.out.println(game.getEnergy());
//
//        System.out.println(game.performActivity(eatActivity));
//        System.out.println("Time, energy");
//        System.out.println(game.getTime());
//        System.out.println(game.getEnergy());
//
//        System.out.println(game.performActivity(studyActivity));
//        System.out.println("Time, energy");
//        System.out.println(game.getTime());
//        System.out.println(game.getEnergy());

        System.out.println(game.performActivity(recreationActivity));
        System.out.println("Time, energy");
        System.out.println(game.getTime());
        System.out.println(game.getEnergy());

        System.out.println(play.getPlayerPosition().row);
        System.out.println(play.getPlayerPosition().column);

        System.out.println("Movement");
        System.out.println(game.move("down"));
        System.out.println(play.getPlayerPosition().row);
        System.out.println(play.getPlayerPosition().column);

        System.out.println(game.move("down"));
        System.out.println(play.getPlayerPosition().row);
        System.out.println(play.getPlayerPosition().column);

        System.out.println(game.move("down"));
        System.out.println(play.getPlayerPosition().row);
        System.out.println(play.getPlayerPosition().column);

        System.out.println(game.move("down"));
        System.out.println(play.getPlayerPosition().row);
        System.out.println(play.getPlayerPosition().column);

        System.out.println(game.move("left"));
        System.out.println(play.getPlayerPosition().row);
        System.out.println(play.getPlayerPosition().column);

        System.out.println(game.getTime());
        System.out.println(game.getEnergy());
        System.out.println(game.performActivity(sleepActivity));
        System.out.println("Time, energy");
        System.out.println(game.getTime());
        System.out.println(game.getEnergy());

        System.out.println(game.move("down"));
        System.out.println(play.getPlayerPosition().row);
        System.out.println(play.getPlayerPosition().column);

        System.out.println(game.performActivity(sleepActivity));
        System.out.println("Time, energy");
        System.out.println(game.getTime());
        System.out.println(game.getEnergy());

        System.out.println(play.getPlayerPosition().row);
        System.out.println(play.getPlayerPosition().column);



        int i = 1;


    }
}