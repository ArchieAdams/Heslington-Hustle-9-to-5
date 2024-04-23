package eng1.group9.gamestate;

import com.badlogic.gdx.math.Vector2;
import eng1.group9.gamestate.activities.Activity;
import eng1.group9.gamestate.activities.Sleep;

import java.util.*;

import static eng1.group9.gamestate.Direction.*;

/**
 * This class is the main game logic class. The 'game state', i.e. the record of the energy
 * and time, etc. of the player is kept and modified in this class.
 */
public class GameState {
    private final int maxEnergy; // the energy the player starts with each a day
    private final int timeInDay; // the total time in each day
    private final ArrayList<Activity> activityHistory;
    private final MapGraph map;
    private final Player character;
    private final Map<Direction, Vector2> directionOffset = Map.ofEntries(new AbstractMap.SimpleEntry<>(RIGHT, new Vector2(1, 0)), new AbstractMap.SimpleEntry<>(LEFT, new Vector2(-1, 0)), new AbstractMap.SimpleEntry<>(UP, new Vector2(0, 1)), new AbstractMap.SimpleEntry<>(DOWN, new Vector2(0, -1)));
    private int energy; // remaining energy for the day
    private int time; // remaining time in the day
    private final List<Day> week = new ArrayList<>(7);
    private Day currentDay = new Day();
    private Vector2 playerPosition;
    private boolean gameOver = false;


    /**
     * Instantiates a new Game state.
     *
     * @param energy  how much energy the player has each day
     * @param time    how much time is in the day
     * @param player  the player
     * @param gameMap which map to use
     */
    public GameState(int energy, int time, Player player, MapGraph gameMap) {
        this.energy = energy;
        this.maxEnergy = energy;
        this.time = time;
        this.timeInDay = time;
        this.character = player;
        this.map = gameMap;
        this.activityHistory = new ArrayList<>();
    }

    /**
     * Instantiates a new Game state.
     */
    public GameState() {
        this(100, 100, new Player(), new MapGraph());
    }


    /**
     * Gets energy.
     *
     * @return the energy
     */
    public int getEnergy() {
        return this.energy;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public int getTime() {
        return this.time;
    }

    /**
     * Gets day.
     *
     * @return the day
     */
    public int getDay() {
        return week.size();
    }

    /**
     * Is game over boolean.
     *
     * @return the boolean
     */
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * Gets player position.
     *
     * @return the player position
     */
    public Vector2 getPlayerPosition() {
        return character.getPosition();
    }


    /**
     * Attempt to move the player
     *
     * @param direction the direction
     * @return if they moved
     */
    public boolean move(Direction direction) {
        if (canMove(direction)) {
            Vector2 offsetVector = directionOffset.get(direction);
            playerPosition.add(offsetVector);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Returns whether the player can move in the specified direction
     */
    private boolean canMove(Direction direction) {
        Vector2 offsetVector = directionOffset.get(direction);

        //stores the current mapGraph locally
        HashMap<Vector2, List<Vector2>> tempMap = map.getFullMap();

        // Checks if the player is currently on NOT the map should always be false
        if (!tempMap.containsKey(playerPosition)) {
            throw new RuntimeException("Player has Left the Map");
        }
        List<Vector2> tempEdges = tempMap.get(playerPosition);
        //returns true if the tile to move to is a valid path to move to
        return (tempEdges.contains(playerPosition.cpy().add(offsetVector)));

    }

    /**
     * Attempt to perform the specified activity
     *
     * @param activity the activity to perform
     * @return true if the activity was successfully performed
     */
    public boolean performActivity(Activity activity) {
        ArrayList<Activity> tempActList = this.getActivities();


        for (Activity tempAct : tempActList) {

            //if the activity is Sleep, the time and energy is reset
            //and the counter of the day increments
            if ((activity instanceof Sleep) && (tempAct.getClass() == activity.getClass())) {

                week.add(currentDay);
                //if the day counter is greater than 6 the game ends
                if (week.size()==7) {
                    this.gameOver = true;
                    return true;
                }

                //time and energy reset otherwise
                this.currentDay = new Day();
                this.energy = this.maxEnergy;
                this.time = this.timeInDay;

                return true;
            }


            //if the activity is Eat, study or recreation, then the time and energy is decremented
            //by the desired amount and the activity is recorded in the ArrayList
            if ((activity.getEnergyConsumption() <= this.energy) && (activity.getTimeConsumption() <= this.time)) {

                int tempTime = activity.getTimeConsumption();
                int tempEnergy = activity.getEnergyConsumption();
                currentDay.addActivity(activity);
                activityHistory.add(activity);
                this.time = this.time - tempTime;
                this.energy = this.energy - tempEnergy;

                return true;

            }
        }
        return false;
    }


    /**
     * Returns the list of activities available at the player's current position
     *
     * @return the activities
     */
    public ArrayList<Activity> getActivities() {

        //gets the player position and stores it locally
        playerPosition = character.getPosition();

        //gets the map of the nodes and stores it locally
        Map<Vector2, Node> tempNodeMap = map.getNodeMap();

        //loops through each tile to try and find the tile that the player is currently on
        if (tempNodeMap.containsKey(playerPosition)) {
            return tempNodeMap.get(playerPosition).getActivities();
        }

        //if no list is found then the empty list is returned
        return new ArrayList<>();
    }


    /**
     * Calculate the score.
     *
     * @return The score calculated.
     */
    public int calculateScore() {
        int studyCount = 0;
        int dayStudiedOnce = 0;
        int dayRelaxedOnce = 0;
        int dayEatenCount = 0;
        int maxScore = 100;
        int score;
        for (Day day : week) {
            studyCount += day.getNumberOfActivity("Study");
            if (day.getNumberOfActivity("Study") >= 1) {
                dayStudiedOnce++;
            }
            if (day.getNumberOfActivity("Eat")>= 2) {
                dayEatenCount++;
            }
            if (day.getNumberOfActivity("Recreation") > 0) {
                dayRelaxedOnce++;
            }
        }

        score = studyCount * 10;
        score = Math.min(score, maxScore);

        // Apply penalties
        if (dayStudiedOnce != 7 && (dayStudiedOnce != 6 || studyCount < 7)) {
            score = dayStudiedOnce*10;
            score = Math.min(score, 50);
        }

        if (dayEatenCount < 7) {
            score -= 10; // Penalty for not eating enough
        }

        if (dayRelaxedOnce < 7) {
            score -= 10; // Penalty for not relaxing enough
        }

        // Cap the score at maxScore
        score = Math.max(score, 0);
        return score;
    }


    /**
     * Calculate the score.
     * <p>
     * Currently, this just counts how many times each activity has taken place
     * </p>
     *
     * @return A mapping specifying how many times each type of activity has been performed
     */
    public Map<String, Integer> scoreCalculation() {

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