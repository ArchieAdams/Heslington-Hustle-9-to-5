package eng1.group9.gamestate;

import eng1.group9.gamestate.activities.Activity;
import eng1.group9.gamestate.activities.Sleep;
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
     * @param map MapGraph the graph of all the tiles, and then the associated edges that connect each connected tile
     * @param playerPosition TilePosition, the location of the player
     * @param character Player, the local instance of the Player class, used to call getters/setters
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
    private boolean gameOver = false;



    //constructor
    public GamesState(int eng, int tim, Player play, MapGraph gameMap){

        this.energy = eng;
        this.maxEnergy = eng;
        this.time = tim;
        this.timeInDay = tim;
        this.character = play;
        this.map = gameMap;
        this.day = 0;
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

    public boolean getGameOver(){
        return this.gameOver;
    }

    public ArrayList<Activity> getActivityList(){

        return this.activityList;
    }

    private boolean getPlayerPosition(){

        playerPosition = character.getPlayerPosition();

        return true;
    }


    /**
     * The function to update the player position
     * @param direction String, the input to determine what direction to move
     * @return boolean if the move has taken place or not
     */
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

    /**
     * Checks the current map to see if the player can move to the desired tile that is inputted
     * @return boolean if the player can move in that direction or not
     */
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
                    //returns true if the tile to move to is a valid path to move to
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
                    //returns true if the tile to move to is a valid path to move to
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
                    //returns true if the tile to move to is a valid path to move to
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
                    //returns true if the tile to move to is a valid path to move to
                    if(((T1.getRow() == playerPosition.getRow() + 1) && (T1.getColumn() == playerPosition.getColumn() ))){
                        return true;
                    }
                }
            }
        }

        return false;
    }


    //the function to perform an activity the user wants
    public boolean performActivity(Activity act) {
        ArrayList<Activity> tempActList = this.getActivities();


        for (Activity tempAct : tempActList) {

                //if the activity is Sleep, the time and energy is reset
                //and the counter of the day increments
                if ((act instanceof Sleep) && (tempAct.getClass() == act.getClass()))  {

                    //if the day counter is greater than 6 the game ends
                    if(this.day > 6){
                        this.gameOver = true;
                        return true;
                    }

                    //time and energy reset otherwise
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


    /**
     * The function to return the activity or activities that the player can perform while...
     * ... on the current playerposition
     * @return ArrayList<Activity> the list of activities the player can perform
     */
    public ArrayList<Activity> getActivities() {

        //gets the player position and stores it locally
        playerPosition = character.getPlayerPosition();

        //gets the map of the nodes and stores it locally
        Map<TilePosition, Node> tempNodeMap = map.getNodeMap();

        //loops through each tile to try and find the tile that the player is currently on
        for (Map.Entry<TilePosition, Node> entry : tempNodeMap.entrySet()) {

            TilePosition tempTile = entry.getKey();
            Node tempNode = entry.getValue();

            //if the tile is found, then the list of activities the player can perform is returned
            if ((tempTile.getRow() == playerPosition.getRow()) && (tempTile.getColumn() == playerPosition.getColumn())) {
                return tempNode.getActivities();
            }
        }

        //if no list is found then the empty list is returned
        ArrayList<Activity> emptyList = new ArrayList<>();

        return emptyList;
    }

    /**
     * Function to calculate the score. Currently, this counts how many times each activity has taken place
     * @return ArrayList</Integer> of the number of times an activity has been performed
     */
    public ArrayList<?> scoreCalculation(){

        Integer sleepCounter = 0;
        Integer eatCounter = 0;
        Integer studyCounter = 0;
        Integer recreationalCounter = 0;
        ArrayList<Integer> counterList = new ArrayList<>();


        //goes through the array list one activity at a time and increments the corresponding counter
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

        //adds the final counts to the arraylist and returns it
        counterList.add(sleepCounter);
        counterList.add(eatCounter);
        counterList.add(studyCounter);
        counterList.add(recreationalCounter);

        return counterList;
    }
}