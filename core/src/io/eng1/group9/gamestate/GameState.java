package io.eng1.group9.gamestate;

import com.badlogic.gdx.math.Vector2;
import io.eng1.group9.gamestate.activities.Activity;
import io.eng1.group9.gamestate.activities.Sleep;
import io.eng1.group9.scoring.ScoreManager;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * This class is the main game logic class. The 'game state', i.e. the record of the energy
 * and time, etc. of the player is kept and modified in this class.
 */
public class GameState {
  private final int maxEnergy; // the energy the player starts with each a day
  private final int timeInDay; // the total time in each day
  private final MapGraph map;
  private final Player character;
  private final Map<Direction, Vector2> directionOffset =
      Map.ofEntries(new AbstractMap.SimpleEntry<>(Direction.RIGHT, new Vector2(1, 0)),
          new AbstractMap.SimpleEntry<>(Direction.LEFT, new Vector2(-1, 0)),
          new AbstractMap.SimpleEntry<>(Direction.UP, new Vector2(0, 1)),
          new AbstractMap.SimpleEntry<>(Direction.DOWN, new Vector2(0, -1)));
  private final List<Day> week = new ArrayList<>(7);
  private int energy; // remaining energy for the day
  private int time; // remaining time in the day
  private Day currentDay = new Day();
  private Vector2 playerPosition;
  private boolean gameOver = false;
  private String playerName = "";
  private int score = 0;


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
    playerPosition = getPlayerPosition();
    this.map = gameMap;

    if (!map.getFullMap().containsKey(playerPosition)) {
      sendToSpawnPoint();
    }
  }

  /**
   * Instantiates a new Game state.
   */
  public GameState() {
    this(100, 100, new Player(), new MapGraph("map"));
  }

  /**
   * Instantiates a new Game state.
   *
   * @param player  the player
   * @param gameMap the game map
   */
  public GameState(Player player, MapGraph gameMap) {
    this(100, 100, player, gameMap);
  }

  private void sendToSpawnPoint() {
    character.setPosition((Vector2) map.getFullMap().keySet().toArray()[0]);
    playerPosition = getPlayerPosition();
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
  public Day getDay() {
    return currentDay;
  }

  /**
   * Gets number of days.
   *
   * @return the day number
   */
  public int getDayCount() {
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
   * Attempt to move the player.
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
   * Returns whether the player can move in the specified direction.
   */
  private boolean canMove(Direction direction) {
    Vector2 offsetVector = directionOffset.get(direction);

    //stores the current mapGraph locally
    Map<Vector2, List<Vector2>> tempMap = map.getFullMap();

    // Checks if the player is currently on NOT the map should always be false
    if (!tempMap.containsKey(playerPosition)) {
      sendToSpawnPoint();
      System.out.println("Player has Left the Map");
    }
    List<Vector2> tempEdges = tempMap.get(playerPosition);
    //returns true if the tile to move to is a valid path to move to
    return (tempEdges.contains(playerPosition.cpy().add(offsetVector)));
  }

  /**
   * Attempt to perform the specified activity.
   *
   * @param activity the activity to perform
   * @return true if the activity was successfully performed
   */
  public boolean performActivity(Activity activity) {
    //if the activity is Sleep, the time and energy is reset
    //and the counter of the day increments
    if (activity instanceof Sleep) {

      week.add(currentDay);
      //if the day counter is greater than 6 the game ends
      if (week.size() == 7) {
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
    if ((activity.getEnergyConsumption() <= this.energy)
        && (activity.getTimeConsumption() <= this.time)) {

      currentDay.addActivity(activity);

      int tempTime = activity.getTimeConsumption();
      int tempEnergy = activity.getEnergyConsumption();
      this.time = this.time - tempTime;
      this.energy = this.energy - tempEnergy;

      return true;

    }

    return false;
  }


  /**
   * Returns the list of activities available at the player's current position.
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
    // This should never occur as it means the player has left the map
    return new ArrayList<>();
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return playerName;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    playerName = name;
  }

  /**
   * Save score.
   */
  public void saveScore() {
    this.score = ScoreManager.calculateScore(week);
  }

  /**
   * Gets score.
   *
   * @return the score
   */
  public int getScore() {
    return this.score;
  }

  /**
   * Gets score.
   *
   * @return the score
   */
  public Map<String, Integer> getActivityCount() {
    List<Day> tempWeek = new ArrayList<>(week);
    tempWeek.add(currentDay);
    return ScoreManager.scoreCount(tempWeek);
  }
}
