package eng1.group9.gamestate;

import com.badlogic.gdx.math.Vector2;
import eng1.group9.gamestate.activities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Holds all the information regarding the tile positions of each node in the map
 */
public class MapGraph {

    // maps a position to reachable adjacent positions
    private final HashMap<Vector2, List<Vector2>> fullMap;

    // maps a position to a node (and therefore what activities can be performed at that position)
    private final HashMap<Vector2, Node> nodeMap;


    public MapGraph() {
        fullMap = new HashMap<>();
        nodeMap = new HashMap<>();
        createMainMap();
    }

    public HashMap<Vector2, List<Vector2>> getFullMap() {
        return fullMap;
    }

    public HashMap<Vector2, Node> getNodeMap() {
        return nodeMap;
    }


    /**
     * Add a TilePosition to the fullMap and nodeMap
     *
     * @param x,   the row of the Tile
     * @param y,   the column of the Tile
     * @param nod, the list of activities associated with that tile
     */
    private void addTile(int x, int y, Node nod) {
        fullMap.putIfAbsent(new Vector2(x, y), new ArrayList<>());
        nodeMap.putIfAbsent(new Vector2(x, y), nod);
    }


    /**
     * Connect all the paths between tilePositions
     *
     * @param x1 the row of the first tile you are connecting
     * @param y1 the column of the first tile you are connecting
     * @param x2 the row of the second tile you are connecting
     * @param y2 the column of the second tile you are connecting
     */
    private void addEdge(int x1, int y1, int x2, int y2) {

        //creates two tiles that will then be used to add to the fullMap
        Vector2 T1 = new Vector2(x1, y1);
        Vector2 T2 = new Vector2(x2, y2);
        if (fullMap.containsKey(T1) && fullMap.containsKey(T2)) {
            fullMap.get(T1).add(T2);
            fullMap.get(T2).add(T1);
        }
    }


    /**
     * Creates all the nodes and edges for the main game map
     */
    private void createMainMap() {

        //creation of the Nodes that will be added to the TilePosition of where the activities are taking place
        Node empty = new Node();

        //Eat activity node
        Node eatNode = new Node();
        Eat eatActivity = new Eat(5, 0, "Eat at Greggs");
        ArrayList<Activity> eatArray = new ArrayList<>();
        eatArray.add(eatActivity);

        //sleep activity node
        Node sleepNode = new Node();
        Sleep sleepActivity = new Sleep(0, 0, "Sleep in Goodricke College");
        ArrayList<Activity> sleepArray = new ArrayList<>();
        sleepArray.add(sleepActivity);

        //study node activity
        Node studyNode = new Node();
        Study studyActivity = new Study(25, 25, "Study in the CS building");
        ArrayList<Activity> studyArray = new ArrayList<>();
        studyArray.add(studyActivity);

        //recreation node activity
        Node recreationNode = new Node();
        Recreation recreationActivity = new Recreation(10, 25, "Exercise in the gym");
        ArrayList<Activity> recreationArray = new ArrayList<>();
        recreationArray.add(recreationActivity);


        //Sets the nodes to have their correct list of activities
        eatNode.setActivities(eatArray);
        sleepNode.setActivities(sleepArray);
        studyNode.setActivities(studyArray);
        recreationNode.setActivities(recreationArray);


        //The addition of all the TilePositions that the player can travel to.
        //If there is an activity that can be performed at that location, the
        //corresponding node is added.
        //Otherwise the node of the empty list is added

        addTile(2, 1, studyNode);
        addTile(2, 2, empty);
        addTile(2, 3, empty);
        addTile(2, 4, empty);
        addTile(2, 7, sleepNode);

        addTile(3, 3, empty);
        addTile(3, 4, empty);
        addTile(3, 5, empty);
        addTile(3, 6, empty);
        addTile(3, 7, empty);

        addTile(4, 2, empty);
        addTile(4, 3, empty);
        addTile(4, 5, empty);
        addTile(4, 7, empty);

        addTile(5, 2, empty);
        addTile(5, 4, empty);
        addTile(5, 5, empty);
        addTile(5, 7, empty);

        addTile(6, 2, empty);
        addTile(6, 3, empty);
        addTile(6, 4, empty);
        addTile(6, 6, empty);
        addTile(6, 7, empty);
        addTile(6, 8, empty);

        addTile(7, 4, empty);
        addTile(7, 6, empty);
        addTile(7, 8, empty);

        addTile(8, 4, empty);
        addTile(8, 6, empty);
        addTile(8, 8, empty);

        addTile(9, 4, empty);
        addTile(9, 5, empty);
        addTile(9, 6, empty);
        addTile(9, 8, empty);

        addTile(10, 5, empty);
        addTile(10, 8, empty);

        addTile(11, 5, empty);
        addTile(11, 6, empty);
        addTile(11, 7, empty);
        addTile(11, 8, empty);

        addTile(12, 1, empty);
        addTile(12, 2, empty);
        addTile(12, 3, empty);
        addTile(12, 4, empty);
        addTile(12, 5, empty);
        addTile(12, 6, empty);

        addTile(13, 1, eatNode);
        addTile(13, 2, empty);
        addTile(13, 6, empty);

        addTile(14, 2, empty);
        addTile(14, 3, empty);
        addTile(14, 4, empty);
        addTile(14, 5, empty);
        addTile(14, 6, recreationNode);


        //this now adds all the edges in the graph.
        //X1 and Y1 correspond to the node the player is currently on.
        //X2 and Y2 correspond to the tiles that the player can move to
        addEdge(2, 1, 2, 2);
        addEdge(2, 2, 2, 3);
        addEdge(2, 3, 2, 4);
        addEdge(2, 3, 3, 3);
        addEdge(2, 4, 3, 4);
        addEdge(2, 7, 3, 7);

        addEdge(3, 3, 4, 3);
        addEdge(3, 4, 3, 5);
        addEdge(3, 5, 3, 6);
        addEdge(3, 5, 4, 5);
        addEdge(3, 6, 3, 7);
        addEdge(3, 7, 4, 7);

        addEdge(4, 2, 4, 3);
        addEdge(4, 2, 5, 2);
        addEdge(4, 5, 5, 5);
        addEdge(4, 7, 5, 7);

        addEdge(5, 2, 6, 2);
        addEdge(5, 4, 5, 5);
        addEdge(5, 4, 6, 4);
        addEdge(5, 7, 6, 7);

        addEdge(6, 2, 6, 3);
        addEdge(6, 3, 6, 4);
        addEdge(6, 4, 7, 4);
        addEdge(6, 6, 6, 7);
        addEdge(6, 6, 7, 6);
        addEdge(6, 7, 6, 8);
        addEdge(6, 8, 7, 8);

        addEdge(7, 4, 8, 4);
        addEdge(7, 6, 8, 6);
        addEdge(7, 8, 8, 8);


        addEdge(8, 4, 9, 4);
        addEdge(8, 6, 9, 6);
        addEdge(8, 8, 9, 8);

        addEdge(9, 4, 9, 5);
        addEdge(9, 5, 9, 6);
        addEdge(9, 5, 10, 5);
        addEdge(9, 8, 10, 8);

        addEdge(10, 5, 11, 5);
        addEdge(10, 8, 11, 8);

        addEdge(11, 5, 12, 5);
        addEdge(11, 6, 12, 6);
        addEdge(11, 6, 11, 7);
        addEdge(11, 7, 11, 8);

        addEdge(12, 1, 13, 1);
        addEdge(12, 1, 12, 2);
        addEdge(12, 2, 12, 3);
        addEdge(12, 3, 12, 4);
        addEdge(12, 4, 12, 5);
        addEdge(12, 5, 12, 6);
        addEdge(12, 6, 13, 6);

        addEdge(13, 1, 13, 2);
        addEdge(13, 2, 14, 2);
        addEdge(13, 6, 14, 6);

        addEdge(14, 2, 14, 3);
        addEdge(14, 3, 14, 4);
        addEdge(14, 4, 14, 5);
        addEdge(14, 5, 14, 6);

    }
}