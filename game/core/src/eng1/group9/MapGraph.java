package eng1.group9;

import eng1.group9.GameState.Activities.*;
import eng1.group9.GameState.Node;
import eng1.group9.GameState.TilePosition;

import javax.sound.midi.Receiver;
import java.util.*;

public class MapGraph {


    private HashMap<TilePosition, List<TilePosition>> FullMap;
    private HashMap<TilePosition, Node> nodeMap;

    public HashMap<TilePosition, List<TilePosition>> getFullMap(){
        return this.FullMap;
    }

    public HashMap<TilePosition, Node> getNodeMap(){
        return this.nodeMap;
    }

    public MapGraph() {
        this.FullMap = new HashMap<TilePosition, List<TilePosition>>();
        this.nodeMap = new HashMap<TilePosition, Node>();
        CreateMap();
        /*
        a hash map is used to contain the map data with the structure where each Tile has a list of what Tiles that it is connected to
         */
    }

    void addTile(int X, int Y, Node nod) {
        FullMap.putIfAbsent(new TilePosition(X, Y), new ArrayList<>());
        nodeMap.putIfAbsent(new TilePosition(X, Y), nod);
    }

    void addEdge(int X1, int Y1, int X2, int Y2) {

        TilePosition T1 = null;
        TilePosition T2 = null;
        for (Map.Entry<TilePosition, List<TilePosition>> entry : FullMap.entrySet()) {

            TilePosition tempTile = entry.getKey();
            List<TilePosition> tempTileList = entry.getValue();

            if ((tempTile.getRow() == X1) && (tempTile.getColumn() == Y1)) {
                T1 = tempTile;
            }

            if ((tempTile.getRow() == X2) && (tempTile.getColumn() == Y2)){
                T2 = tempTile;
            }
        }
        FullMap.get(T1).add(T2);
        FullMap.get(T2).add(T1);
    }

//    class Tile {
//
//        int XCord;
//        int YCord;
//
//        public Tile(int X, int Y){
//
//            this.XCord = X;
//            this.YCord = Y;
//
//        }
//
//    }

    public void CreateMap(){
        /*
            Code for all Tiles within our game each Tile is giving an X and Y coordinate that responds to its position on the map
         */

        Node empty = new Node();

        Node eatNode = new Node();
        Eat eatActivity = new Eat(10,10);
        ArrayList<Activity> eatArray = new ArrayList<>();
        eatArray.add(eatActivity);

        Node sleepNode = new Node();
        Sleep sleepActivity = new Sleep(10,10);
        ArrayList<Activity> sleepArray = new ArrayList<>();
        sleepArray.add(sleepActivity);

        Node studyNode = new Node();
        Study studyActivity = new Study(10,10);
        ArrayList<Activity> studyArray = new ArrayList<>();
        studyArray.add(studyActivity);

        Node recreationNode = new Node();
        Recreation RecreationActivity = new Recreation(10,10);
        ArrayList<Activity> recreationArray = new ArrayList<>();
        recreationArray.add(RecreationActivity);




        eatNode.setActivities(eatArray);
        sleepNode.setActivities(sleepArray);
        studyNode.setActivities(studyArray);
        recreationNode.setActivities(recreationArray);


        addTile(2,1, eatNode);
        addTile(2,2, empty);
        addTile(2,3, empty);
        addTile(2,4, empty);
        addTile(2,7, studyNode);

        addTile(3,3, empty);
        addTile(3,4, empty);
        addTile(3,5, empty);
        addTile(3,6, empty);
        addTile(3,7, empty);

        addTile(4,2, empty);
        addTile(4,3, empty);
        addTile(4,5, empty);
        addTile(4,7, empty);

        addTile(5,2, empty);
        addTile(5,4, empty);
        addTile(5,5, empty);
        addTile(5,7, empty);

        addTile(6,2, empty);
        addTile(6,3, empty);
        addTile(6,4, empty);
        addTile(6,6, empty);
        addTile(6,7, empty);
        addTile(6,8, empty);

        addTile(7,4, empty);
        addTile(7,6, empty);
        addTile(7,8, empty);

        addTile(8,4, empty);
        addTile(8,6, empty);
        addTile(8,8, empty);

        addTile(9,4, empty);
        addTile(9,5, empty);
        addTile(9,6, empty);
        addTile(9,8, empty);

        addTile(10,5, empty);
        addTile(10,8, empty);

        addTile(11,5, empty);
        addTile(11,6, empty);
        addTile(11,7, empty);
        addTile(11,8, empty);

        addTile(12,1, empty);
        addTile(12,2, empty);
        addTile(12,3, empty);
        addTile(12,4, empty);
        addTile(12,5, empty);
        addTile(12,6, empty);

        addTile(13,1, sleepNode);
        addTile(13,2, empty);
        addTile(13,6, empty);

        addTile(14,2, empty);
        addTile(14,3, empty);
        addTile(14,4, empty);
        addTile(14,5, empty);
        addTile(14,6, recreationNode);


        addEdge(2,1,2,2);
        addEdge(2,2,2,3);
        addEdge(2,3,2,4);
        addEdge(2,3,3,3);
        addEdge(2,4,3,4);
        addEdge(2,7,3,7);

        addEdge(3,3,4,3);
        addEdge(3,4,3,5);
        addEdge(3,5,3,6);
        addEdge(3,5,4,5);
        addEdge(3,6,3,7);
        addEdge(3,7,4,7);

        addEdge(4,2,4,3);
        addEdge(4,2,5,2);
        addEdge(4,5,5,5);
        addEdge(4,7,5,7);

        addEdge(5,2,6,2);
        addEdge(5,4,5,5);
        addEdge(5,4,6,4);
        addEdge(5,7,6,7);

        addEdge(6,2,6,3);
        addEdge(6,3,6,4);
        addEdge(6,4,7,4);
        addEdge(6,6,6,7);
        addEdge(6,6,7,6);
        addEdge(6,7,6,8);
        addEdge(6,8,7,8);

        addEdge(7,4,8,4);
        addEdge(7,6,8,6);
        addEdge(7,8,8,8);

 

        addEdge(8,4,9,4);
        addEdge(8,6,9,6);
        addEdge(8,8,9,8);

        addEdge(9,4,9,5);
        addEdge(9,5,9,6);
        addEdge(9,5,10,5);
        addEdge(9,8,10,8);

        addEdge(10,5,11,5);
        addEdge(10,8,11,8);

        addEdge(11,5,12,5);
        addEdge(11,6,12,6);
        addEdge(11,6,11,7);
        addEdge(11,7,11,8);

        addEdge(12,1,13,1);
        addEdge(12,1,12,2);
        addEdge(12,2,12,3);
        addEdge(12,3,12,4);
        addEdge(12,4,12,5);
        addEdge(12,5,12,6);
        addEdge(12,6,13,6);

        addEdge(13,1,13,2);
        addEdge(13,2,14,2);
        addEdge(13,6,14,6);

        addEdge(14,2,14,3);
        addEdge(14,3,14,4);
        addEdge(14,4,14,5);
        addEdge(14,5,14,6);

    }
}
