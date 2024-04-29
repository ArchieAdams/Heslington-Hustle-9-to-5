package eng1.group9.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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

    private final String filename;

    /**
     * Instantiates a new Map graph.
     *
     * @param filename the filename
     */
    public MapGraph(String filename) {
        fullMap = new HashMap<>();
        nodeMap = new HashMap<>();
        this.filename = filename;
        readGridFromFile();
    }


    /** Read the grid from a file
     */
    private void readGridFromFile() {
        FileHandle fileHandle = Gdx.files.internal(this.filename);
        String fileContents = fileHandle.readString();
        String[] lines = fileContents.split("\\r?\\n");
        // Define the maximum column size of the grid
        int MAX_Y = lines.length;
        // Define the maximum row size of the grid
        int MAX_X = lines[0].toCharArray().length;

        // Loops through the rows
        for (int y = 0; y < MAX_Y; y++) {
            // Loops through the columns
            for (int x = 0; x < MAX_X; x++) {
                // Gets current char
                char symbol = lines[y].toCharArray()[x];
                if (symbol == '0') {
                    continue;
                }
                // Add 1 to offset x and inverts y
                System.out.println((x)+","+ (MAX_Y - y - 1));
                addTile(x, MAX_Y - y - 1, getNodeFromSymbol(symbol));
            }
        }
        generateEdges();
    }

    /**
     * Creates edges for the all tiles in map
     *
     */
    private void generateEdges() {
        // Manually define blocked connections, to avoid adjacent nodes incorrectly connecting where a square of
        // X's exist in the text file
        HashMap<Vector2, Vector2> blockedConnections = new HashMap<>();
        blockedConnections.put(new Vector2(2,2), new Vector2(2,3));
        blockedConnections.put(new Vector2(11,1), new Vector2(12,1));
        blockedConnections.put(new Vector2(10,4), new Vector2(10,5));

        // Loops through all tiles in map
        for (Vector2 location : fullMap.keySet()) {
            // Up, Down, Right, Left
            Vector2[] directions = {new Vector2(0, 1), new Vector2(0, -1), new Vector2(1, 0), new Vector2(-1, 0)};

            // Checks if they have adjacent cells and adds them as an edge
            for (Vector2 direction : directions) {
                Vector2 newPosition = location.cpy().add(direction);

                // Skip defined nodes which should not connect
                if (isBlocked(location, newPosition, blockedConnections)) {
                    continue;
                }
                addIfInMap(location, newPosition);
            }
        }
    }

    /**
     * Returns true if nodes should not be connected, also checks reverse direction
     * @param from The starting position vector of the proposed connection.
     * @param to The ending position vector of the proposed connection.
     * @param blockedConnections A map containing pairs of vectors representing explicitly blocked connections.
     * @return true if the connection between the nodes is blocked, false otherwise.
     */
    private boolean isBlocked(Vector2 from, Vector2 to, HashMap<Vector2, Vector2> blockedConnections) {
        return (blockedConnections.containsKey(from) && blockedConnections.get(from).equals(to)) ||
                (blockedConnections.containsKey(to) && blockedConnections.get(to).equals(from));
    }

    /**
     * Adds the edges if the node exists
     * @param location first tile location
     * @param neighbour Second tile location
     */
    private void addIfInMap(Vector2 location, Vector2 neighbour) {
        if (fullMap.containsKey(neighbour)) {
            addEdge(location, neighbour);
        }
    }

    /**
     * Returns the node based on the letter
     * @param symbol Letter to generate node based on
     * @return The activity node that maps to that symbol
     */
    private Node getNodeFromSymbol(char symbol) {
        switch (symbol) {
            case 'X':
                return new Node();
            case 'L':
                return new Node(new Study(25, 25, "Study in the CS building"));
            case 'S':
                return new Node(new Sleep(0, 0, "Sleep in Goodricke College"));
            case 'R':
                return new Node(new Recreation(10, 25, "Exercise in the gym"));
            case 'E':
                return new Node(new Eat(5, 0, "Eat at Greggs"));

        }
        return new Node();
    }


    /**
     * Gets full map.
     *
     * @return the full map
     */
    public HashMap<Vector2, List<Vector2>> getFullMap() {
        return fullMap;
    }

    /**
     * Gets node map.
     *
     * @return the node map
     */
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
     * Connect all the paths between tiles
     *
     * @param T1 the first vector
     * @param T2 the second vector
     */
    private void addEdge(Vector2 T1, Vector2 T2) {
        fullMap.get(T1).add(T2);
        fullMap.get(T2).add(T1);
    }


}