package eng1.group9.gamestate;

import eng1.group9.gamestate.activities.Activity;
import eng1.group9.gamestate.activities.Sleep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the main game logic class. The 'game state', i.e. the record of the energy
 * and time, etc. of the player is kept and modified in this class.
 */
public class GameState {
    private int energy; // remaining energy for the day
    private final int maxEnergy; // the energy the player starts with each a day
    private int time; // remaining time in the day
    private final int timeInDay; // the total time in each day
    private int day;
    private final ArrayList<Activity> activityHistory;
    private MapGraph map;
    private TilePosition playerPosition;
    private final Player character;
    private boolean gameOver = false;


    /**
     *
     * @param energy how much energy the player has each day
     * @param time how much time is in the day
     * @param player the player
     * @param gameMap which map to use
     */
    public GameState(int energy, int time, Player player, MapGraph gameMap){
        this.energy = energy;
        this.maxEnergy = energy;
        this.time = time;
        this.timeInDay = time;
        this.character = player;
        this.map = gameMap;
        this.day = 0;
        this.activityHistory = new ArrayList<>();
    }
    public GameState(){
        this(100, 100, new Player(), new MapGraph());
    }


    public int getEnergy(){
        return this.energy;
    }
    public int getTime(){
        return this.time;
    }
    public int getDay(){
        return this.day;
    }
    public boolean isGameOver(){
        return this.gameOver;
    }
    public ArrayList<Activity> getActivityHistory(){
        return this.activityHistory;
    }
    public TilePosition getPlayerPosition(){
        return character.getPosition();
    }


    /** Attempt to move the player in the specified direction
     *
     * @param direction String, the input to determine what direction to move
     * @return boolean if the move has taken place or not
     */
    public boolean move(Direction direction){
        System.out.println("here1");

        if(direction == Direction.UP && canMoveUp()){
            playerPosition = character.getPosition();
            playerPosition.setColumn(playerPosition.getColumn() + 1);
            character.setPosition(playerPosition);
            return true;
        }
        else if(direction == Direction.DOWN && canMoveDown()){
            playerPosition = character.getPosition();
            playerPosition.setColumn(playerPosition.getColumn() - 1);
            character.setPosition(playerPosition);
            return true;
        }
        else if(direction == Direction.LEFT && canMoveLeft()){
            playerPosition = character.getPosition();
            playerPosition.setRow(playerPosition.getRow() - 1);
            character.setPosition(playerPosition);
            return true;
        }
        else if(direction == Direction.RIGHT && (this.canMoveRight())){
            playerPosition = character.getPosition();
            playerPosition.setRow(playerPosition.getRow() + 1);
            character.setPosition(playerPosition);
            return true;
        }
        else {
            return false;
        }
    }


    /** Checks the current map to see if the player can move up
     *
     * @return boolean if the player can move up or not
     */
    private boolean canMoveUp(){

        //gets player position and stores in locally
        playerPosition = character.getPosition();

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

    /** Checks the current map to see if the player can move down
     *
     * @return boolean if the player can move down or not
     */
    private boolean canMoveDown(){

        //gets player position and stores in locally
        playerPosition = character.getPosition();

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

    /** Checks the current map to see if the player can move left
     *
     * @return boolean if the player can move left or not
     */
    private boolean canMoveLeft(){

        //gets player position and stores in locally
        playerPosition = character.getPosition();

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

    /** Checks the current map to see if the player can move right
     *
     * @return boolean if the player can move right or not
     */
    private boolean canMoveRight(){

        //gets player position and stores in locally
        playerPosition = character.getPosition();

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


    /** Attempt to perform the specified activity
     *
     * @param activity the activity to perform
     * @return true if the activity was successfully performed
     */
    public boolean performActivity(Activity activity) {
        System.out.println("here5");
        ArrayList<Activity> tempActList = this.getActivities();


        for (Activity tempAct : tempActList) {

                //if the activity is Sleep, the time and energy is reset
                //and the counter of the day increments
                if ((activity instanceof Sleep) && (tempAct.getClass() == activity.getClass()))  {

                    //if the day counter is greater than 6 the game ends
                    if(this.day == 6){
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
                if ((activity.getEnergyConsumption() <= this.energy) && (activity.getTimeConsumption() <= this.time)) {

                    int tempTime = activity.getTimeConsumption();
                    int tempEnergy = activity.getEnergyConsumption();

                    activityHistory.add(activity);
                    this.time = this.time - tempTime;
                    this.energy = this.energy - tempEnergy;

                    return true;

                }
            }



        return false;
    }


    /** Returns the list of activities available at the player's current position */
    public ArrayList<Activity> getActivities() {

        //gets the player position and stores it locally
        playerPosition = character.getPosition();

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

    /** Calculate the score.
     * <p>
     * Currently, this just counts how many times each activity has taken place
     * </p>
     * @return A mapping specifying how many times each type of activity has been performed
     */
    public Map<String, Integer> scoreCalculation(){

        int sleepCounter = 0;
        int eatCounter = 0;
        int studyCounter = 0;
        int recreationalCounter = 0;
        Map<String, Integer> activitiesCounter = new HashMap<>();

        //goes through the array list one activity at a time and increments the corresponding counter
        for (Activity activity : activityHistory) {

            switch (activity.getClass().getSimpleName()) {

                case "Sleep":
                    sleepCounter++;
                    break;
                case "Eat":
                    eatCounter++;
                    break;
                case "Study":
                    studyCounter++;
                    break;
                case "Recreation":
                    recreationalCounter++;
            }
        }

        //adds the final counts to the arraylist and returns it
        activitiesCounter.put("Sleep", sleepCounter);
        activitiesCounter.put("Eat", eatCounter);
        activitiesCounter.put("Study", studyCounter);
        activitiesCounter.put("Recreation", recreationalCounter);

        return activitiesCounter;
    }
}