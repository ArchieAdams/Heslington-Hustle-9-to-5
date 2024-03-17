import java.util.ArrayList
/**
 * This class is the class for the Node, which contains the list of activities at that node
 * @param listOfActivities ArrayList<Activitiy>, list of activiteis at this node
 */
public class Node{
    private ArrayList<Activitiy> listOfActivities;

    //Constructor
    public Node(){
        listOfActivities = new ArrayList<>();
    }
    //Getter
    public ArrayList<Activity> getActivities(){
        return listOfActivities
    }
    //Setter
    public void setListOfActivities(ArrayList<Activitiy> listOfActivities) {
        this.listOfActivities = listOfActivities;
    }
}