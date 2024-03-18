package eng1.group9.GameState;
import eng1.group9.GameState.Activities.Activity;

import java.util.ArrayList;

/**
 * This class is the class for the Node, which contains the list of activities at that node
 */
public class Node{
    private ArrayList<eng1.group9.GameState.Activities.Activity> listOfActivities;

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