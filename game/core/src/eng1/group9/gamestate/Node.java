package eng1.group9.gamestate;
import eng1.group9.gamestate.activities.Activity;

import java.util.ArrayList;

/**
 * This class is the class for the Node, which contains the list of activities at that node
 */
public class Node{
    private ArrayList<eng1.group9.gamestate.activities.Activity> listOfActivities;

    //Constructor
    /**
     * @param listOfActivities ArrayList<Activity>, list of activities at this node
     */
    public Node(){
        listOfActivities = new ArrayList<>();
    }
    //Getter
    public ArrayList<Activity> getActivities(){
        return listOfActivities;
    }
    //Setter
    public void setActivities(ArrayList<Activity> activities) {
        this.listOfActivities = activities;
    }
}