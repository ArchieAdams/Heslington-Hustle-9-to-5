package eng1.group9.gamestate;

import eng1.group9.gamestate.activities.Activity;
import java.util.ArrayList;

/** A place on the map the player can be. A node may have one or more activities available to perform. */
public class Node{
    private ArrayList<Activity> listOfActivities;

    public Node(){
        listOfActivities = new ArrayList<>();
    }

    /** Returns a list of activities available at this node */
    public ArrayList<Activity> getActivities(){
        return listOfActivities;
    }

    /** Sets the activities available at this node */
    public void setActivities(ArrayList<Activity> activities) {
        this.listOfActivities = activities;
    }
}