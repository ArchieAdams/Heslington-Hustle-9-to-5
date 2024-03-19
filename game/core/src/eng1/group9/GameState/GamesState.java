package eng1.group9.GameState;

import eng1.group9.GameState.Activities.Activity;
import eng1.group9.GameState.Activities.Sleep;
import eng1.group9.MapGraph;
//import eng1.group9.GameState.TilePosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GamesState {

    /**
     * This class is the main game logic class. The 'game state', i.e. the record of the energy
     * and time, etc. of the player is kept and modified in this class.
     * @param energy int, the remaining energy of the player
     * @param maxEnergy int the energy the player starts with in a day
     * @param time int the remaining time of the player
     * @param timeInDay int the total time in a day
     * @param day int the counter of the days
     * @param activityList int the list of activities taken place
     */

    private int energy;
    private int maxEnergy;
    private int time;
    private int timeInDay;
    private int day;
    private ArrayList<Activity> activityList = new ArrayList<>();
    private MapGraph map;
    private TilePosition playerPosition;
    private Player character;



    //constructor
    public GamesState(int eng, int tim, Player play, MapGraph gameMap){

        this.energy = eng;
        this.maxEnergy = eng;
        this.time = tim;
        this.timeInDay = tim;
        this.character = play;
        this.map = gameMap;
    }


    //getters
    public int getEnergy(){

        return this.energy;
    }
    public int getTime(){

        return this.time;
    }

    public int getDay(){

        return this.day;
    }

    public ArrayList<Activity> getActivityList(){

        return this.activityList;
    }


    //the function to update the player position
    public boolean move(String direction){

        if((direction.equals("up")) && (this.canMoveUp())){

            playerPosition = character.getPlayerPosition();

            playerPosition.setColumn(playerPosition.getColumn() + 1);

            character.setPlayerPosition(playerPosition);

            return true;
        }

        if((direction.equals("down")) && (this.canMoveDown())){

            playerPosition = character.getPlayerPosition();

            playerPosition.setColumn(playerPosition.getColumn() - 1);

            character.setPlayerPosition(playerPosition);

            return true;
        }

        if((direction.equals("left")) && (this.canMoveLeft())){

            playerPosition = character.getPlayerPosition();

            playerPosition.setRow(playerPosition.getRow() - 1);

            character.setPlayerPosition(playerPosition);

            return true;
        }

        if((direction.equals("right")) && (this.canMoveRight())){

            playerPosition = character.getPlayerPosition();

            playerPosition.setRow(playerPosition.getRow() + 1);

            character.setPlayerPosition(playerPosition);

            return true;
        }

        return false;
    }


    //the functions to check whether the player is capable of moving in
    //that direction
    private boolean canMoveUp(){

        //gets player position and stores in locally
        playerPosition = character.getPlayerPosition();

        //stores the current mapGraph locally
        HashMap<TilePosition, List<TilePosition>> tempMap = new HashMap<TilePosition, List<TilePosition>>();
        tempMap = map.getFullMap();


        //Iterates through every instance of the node until it finds the player's position node
        for (Map.Entry<TilePosition, List<TilePosition>> entry : tempMap.entrySet()) {

            //stores temporary variables used in the comparison
            TilePosition tempTile = entry.getKey();
            ArrayList<TilePosition> tempEdges = (ArrayList<TilePosition>) entry.getValue();


            //If the current node is connected to the node above, return true
            if ((tempTile.getRow() == playerPosition.getRow()) && (tempTile.getColumn() == playerPosition.getColumn())) {
                for(TilePosition T1 : tempEdges){
                    if(((T1.getRow() == playerPosition.getRow()) && (T1.getColumn() == playerPosition.getColumn() + 1))){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean canMoveDown(){

        //gets player position and stores in locally
        playerPosition = character.getPlayerPosition();

        //stores the current mapGraph locally
        HashMap<TilePosition, List<TilePosition>> tempMap = new HashMap<TilePosition, List<TilePosition>>();
        tempMap = map.getFullMap();


        //Iterates through every instance of the node until it finds the player's position node
        for (Map.Entry<TilePosition, List<TilePosition>> entry : tempMap.entrySet()) {

            //stores temporary variables used in the comparison
            TilePosition tempTile = entry.getKey();
            ArrayList<TilePosition> tempEdges = (ArrayList<TilePosition>) entry.getValue();


            //If the current node is connected to the node below, return true
            if ((tempTile.getRow() == playerPosition.getRow()) && (tempTile.getColumn() == playerPosition.getColumn())) {
                for(TilePosition T1 : tempEdges){
                    if(((T1.getRow() == playerPosition.getRow()) && (T1.getColumn() == playerPosition.getColumn() - 1))){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean canMoveLeft(){

        //gets player position and stores in locally
        playerPosition = character.getPlayerPosition();

        //stores the current mapGraph locally
        HashMap<TilePosition, List<TilePosition>> tempMap = new HashMap<TilePosition, List<TilePosition>>();
        tempMap = map.getFullMap();


        //Iterates through every instance of the node until it finds the player's position node
        for (Map.Entry<TilePosition, List<TilePosition>> entry : tempMap.entrySet()) {

            //stores temporary variables used in the comparison
            TilePosition tempTile = entry.getKey();
            ArrayList<TilePosition> tempEdges = (ArrayList<TilePosition>) entry.getValue();


            //If the current node is connected to the node to the left, return true
            if ((tempTile.getRow() == playerPosition.getRow()) && (tempTile.getColumn() == playerPosition.getColumn())) {
                for(TilePosition T1 : tempEdges){
                    if(((T1.getRow() == playerPosition.getRow() - 1) && (T1.getColumn() == playerPosition.getColumn()))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean canMoveRight(){

        //gets player position and stores in locally
        playerPosition = character.getPlayerPosition();

        //stores the current mapGraph locally
        HashMap<TilePosition, List<TilePosition>> tempMap = new HashMap<TilePosition, List<TilePosition>>();
        tempMap = map.getFullMap();


        //Iterates through every instance of the node until it finds the player's position node
        for (Map.Entry<TilePosition, List<TilePosition>> entry : tempMap.entrySet()) {

            //stores temporary variables used in the comparison
            TilePosition tempTile = entry.getKey();
            ArrayList<TilePosition> tempEdges = (ArrayList<TilePosition>) entry.getValue();


            //If the current node is connected to the node to the right, return true
            if ((tempTile.getRow() == playerPosition.getRow()) && (tempTile.getColumn() == playerPosition.getColumn())) {
                for(TilePosition T1 : tempEdges){
                    if(((T1.getRow() == playerPosition.getRow() + 1) && (T1.getColumn() == playerPosition.getColumn() ))){
                        return true;
                    }
                }
            }
        }


        return false;
    }


    //the function to perform an activity the user wants
    public boolean performActivity() {
        ArrayList<Activity> tempActList = this.getActivities();

        for (Activity act : tempActList) {

                //if the activity is Sleep, the time and energy is reset
                //and the counter of the day increments
                if (act instanceof Sleep) {

                    this.day = this.day + 1;
                    this.energy = this.maxEnergy;
                    this.time = this.timeInDay;

                    return true;
                }


                //if the activity is Eat, study or recreation, then the time and energy is decremented
                //by the desired amount and the activity is recorded in the ArrayList
                if ((act.getEnergyConsumption() < this.energy) && (act.getTimeConsumption() < this.time)) {

                    int tempTime = act.getTimeConsumption();
                    int tempEnergy = act.getEnergyConsumption();

                    activityList.add(act);
                    this.time = this.time - tempTime;
                    this.energy = this.energy - tempEnergy;

                    return true;

                }
            }



        return false;
    }


    public ArrayList<Activity> getActivities() {

        playerPosition = character.getPlayerPosition();

        Map<TilePosition, Node> tempNodeMap = map.getNodeMap();

        for (Map.Entry<TilePosition, Node> entry : tempNodeMap.entrySet()) {

            TilePosition tempTile = entry.getKey();
            Node tempNode = entry.getValue();

            if ((tempTile.getRow() == playerPosition.getRow()) && (tempTile.getColumn() == playerPosition.getColumn())) {
                return tempNode.getActivities();
            }
        }

        ArrayList<Activity> emptyList = new ArrayList<>();

        return emptyList;
    }

    private boolean getPlayerPosition(){

        playerPosition = character.getPlayerPosition();

        return true;
    }

    public ArrayList<?> scoreCalculation(){

        Integer sleepCounter = 0;
        Integer eatCounter = 0;
        Integer studyCounter = 0;
        Integer recreationalCounter = 0;
        ArrayList<Integer> counterList = new ArrayList<>();

        for(int i = 0; i < activityList.size(); i++){

            Activity acti = activityList.get(i);

            switch (acti.getClass().getSimpleName()) {

                case "Sleep":
                    sleepCounter = sleepCounter + 1;
                    break;
                case "Eat":
                    eatCounter = eatCounter + 1;
                    break;
                case "Study":
                    studyCounter = studyCounter + 1;
                    break;
                case "Recreational":
                    recreationalCounter = recreationalCounter + 1;
            }
        }

        counterList.add(sleepCounter);
        counterList.add(eatCounter);
        counterList.add(studyCounter);
        counterList.add(recreationalCounter);

        return counterList;
    }
}