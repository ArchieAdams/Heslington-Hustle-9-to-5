package eng1.group9;
import java.util.*;
public class MapGraph {
    private Map<Tile, List<Tile>> FullMap;

    MapGraph() {
        this.FullMap = new HashMap<Tile, List<Tile>>();
        /*
        a hash map is used to contain the map data with the structure where each Tile has a list of what Tiles that it is connected to
         */
    }

    void addTile(int X, int Y) {
        FullMap.putIfAbsent(new Tile(X, Y), new ArrayList<>());
    }

    void addEdge(int X1, int Y1, int X2, int Y2) {
        Tile T1 = new Tile(X1, Y1);
        Tile T2 = new Tile(X2, Y2);
        FullMap.get(T1).add(T2);
        FullMap.get(T1).add(T2);
    }

    class Tile {

        int XCord;
        int YCord;

        public Tile(int X, int Y){

            this.XCord = X;
            this.YCord = Y;

        }

    }

    public  void CreateMap(){
        /*
            Code for all Tiles within our game each Tile is giving an X and Y coordinate that responds to its position on the map
         */
        addTile(3,2);
        addTile(3,3);
        addTile(3,4);
        addTile(3,5);
        addTile(3,8);

        addTile(4,4);
        addTile(4,5);
        addTile(4,6);
        addTile(4,7);
        addTile(4,8);

        addTile(5,3);
        addTile(5,4);
        addTile(5,6);
        addTile(5,8);

        addTile(6,3);
        addTile(6,5);
        addTile(6,6);
        addTile(6,8);

        addTile(7,3);
        addTile(7,4);
        addTile(7,5);
        addTile(7,7);
        addTile(7,8);
        addTile(7,9);

        addTile(8,5);
        addTile(8,7);
        addTile(8,9);

        addTile(9,5);
        addTile(9,7);
        addTile(9,9);

        addTile(10,5);
        addTile(10,6);
        addTile(10,7);
        addTile(10,9);

        addTile(11,6);
        addTile(11,9);

        addTile(12,6);
        addTile(12,7);
        addTile(12,8);
        addTile(12,9);

        addTile(13,2);
        addTile(13,3);
        addTile(13,4);
        addTile(13,5);
        addTile(13,6);
        addTile(13,7);

        addTile(14,2);
        addTile(14,3);
        addTile(14,7);

        addTile(15,3);
        addTile(15,4);
        addTile(15,5);
        addTile(15,6);
        addTile(15,7);

        addEdge(3,2,3,3);
        addEdge(3,3,3,4);
        addEdge(3,4,3,5);
        addEdge(3,4,4,4);
        addEdge(3,5,4,5);
        addEdge(3,8,4,8);

        addEdge(4,4,5,4);
        addEdge(4,5,4,6);
        addEdge(4,6,4,7);
        addEdge(4,6,5,6);
        addEdge(4,7,4,8);
        addEdge(4,8,5,8);

        addEdge(5,3,5,4);
        addEdge(5,3,6,3);
        addEdge(5,6,6,6);
        addEdge(5,8,6,8);

        addEdge(6,3,7,3);
        addEdge(6,5,6,6);
        addEdge(6,5,7,5);
        addEdge(6,8,7,8);

        addEdge(7,3,7,4);
        addEdge(7,4,7,5);
        addEdge(7,5,8,5);
        addEdge(7,7,7,8);
        addEdge(7,7,8,7);
        addEdge(7,8,7,9);
        addEdge(7,9,8,9);

        addEdge(8,5,9,5);
        addEdge(8,7,9,7);
        addEdge(8,9,9,9);

        addEdge(9,5,10,5);
        addEdge(9,7,10,7);
        addEdge(9,9,10,9);

        addEdge(10,5,10,6);
        addEdge(10,6,10,7);
        addEdge(10,6,11,6);
        addEdge(10,9,11,9);

        addEdge(11,6,12,6);
        addEdge(11,9,12,9);

        addEdge(12,6,13,6);
        addEdge(12,7,13,7);
        addEdge(12,7,12,8);
        addEdge(12,8,12,9);

        addEdge(13,2,14,2);
        addEdge(13,2,13,3);
        addEdge(13,3,13,4);
        addEdge(13,4,13,5);
        addEdge(13,5,13,6);
        addEdge(13,6,13,7);
        addEdge(13,7,14,7);

        addEdge(14,2,14,3);
        addEdge(14,3,15,3);
        addEdge(14,7,15,7);

        addEdge(15,3,15,4);
        addEdge(15,4,15,5);
        addEdge(15,5,15,6);
        addEdge(15,6,15,7);

    }







}
