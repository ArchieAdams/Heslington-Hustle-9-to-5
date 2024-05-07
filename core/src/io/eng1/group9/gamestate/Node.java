package io.eng1.group9.gamestate;

import io.eng1.group9.gamestate.activities.Activity;
import java.util.ArrayList;

/**
 * A place on the map the player can be.
 * A node may have one or more activities available to perform.
 */
public class Node {
  private final ArrayList<Activity> listOfActivities;

  /**
   * Instantiates a new Node.
   */
  public Node() {
    listOfActivities = new ArrayList<>();
  }

  /**
   * Instantiates a new Node.
   *
   * @param activity the activity
   */
  public Node(Activity activity) {
    listOfActivities = new ArrayList<>();
    listOfActivities.add(activity);
  }

  /**
   * Returns a list of activities available at this node.
   *
   * @return the activities
   */
  public ArrayList<Activity> getActivities() {
    return listOfActivities;
  }

}