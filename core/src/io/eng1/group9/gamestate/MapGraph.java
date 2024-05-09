package io.eng1.group9.gamestate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import io.eng1.group9.gamestate.activities.Eat;
import io.eng1.group9.gamestate.activities.Recreation;
import io.eng1.group9.gamestate.activities.Sleep;
import io.eng1.group9.gamestate.activities.Study;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Holds all the information regarding the tile positions of each node in the map.
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


  /**
   * Read the grid from a file.
   */
  private void readGridFromFile() {
    FileHandle fileHandle = Gdx.files.internal(this.filename);
    String fileContents = fileHandle.readString();
    String[] lines = fileContents.split("\\r?\\n");
    // Define the maximum column size of the grid
    int maxY = lines.length;
    // Define the maximum row size of the grid
    int maxX = lines[0].toCharArray().length;

    // Loops through the rows
    for (int y = 0; y < maxY; y++) {
      // Loops through the columns
      for (int x = 0; x < maxX; x++) {
        // Gets current char
        char symbol = lines[y].toCharArray()[x];
        if (symbol == '0') {
          continue;
        }
        // Add 1 to offset x and inverts y
        addTile(x, maxY - y - 1, getNodeFromSymbol(symbol));
      }
    }
    generateEdges();
  }

  /**
   * Creates edges for the all tiles in map.
   */
  private void generateEdges() {
    // Manually define blocked connections, to avoid adjacent nodes incorrectly
    // connecting where a square of X's exist in the text file
    HashMap<Vector2, Vector2> blockedConnections = new HashMap<>();
    blockedConnections.put(new Vector2(2, 2), new Vector2(2, 3));
    blockedConnections.put(new Vector2(11, 1), new Vector2(12, 1));
    blockedConnections.put(new Vector2(10, 4), new Vector2(10, 5));

    // Loops through all tiles in map
    for (Vector2 location : fullMap.keySet()) {
      // Up, Down, Right, Left
      Vector2[] directions =
        {new Vector2(0, 1), new Vector2(0, -1), new Vector2(1, 0), new Vector2(-1, 0)};

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
   * Returns true if nodes should not be connected, also checks reverse direction.
   *
   * @param from               The starting position vector of the proposed connection.
   * @param to                 The ending position vector of the proposed connection.
   * @param blockedConnections A map containing pairs of vectors representing explicitly blocked
   *                           connections.
   * @return true if the connection between the nodes is blocked, false otherwise.
   */
  private boolean isBlocked(Vector2 from, Vector2 to,
                            HashMap<Vector2, Vector2> blockedConnections) {
    return (blockedConnections.containsKey(from) && blockedConnections.get(from).equals(to))
        || (blockedConnections.containsKey(to) && blockedConnections.get(to).equals(from));
  }

  /**
   * Adds the edges if the node exists.
   *
   * @param location  first tile location
   * @param neighbour Second tile location
   */
  private void addIfInMap(Vector2 location, Vector2 neighbour) {
    if (fullMap.containsKey(neighbour)) {
      addEdge(location, neighbour);
    }
  }

  /**
   * Returns the node based on the letter.
   *
   * @param symbol Letter to generate node based on
   * @return The activity node that maps to that symbol
   */
  private Node getNodeFromSymbol(char symbol) {
    //TODO turn to hashmap loaded from jason file
    switch (symbol) {
      case 'C':
        return new Node(new Study(25, 25, "Study"));
      case 'S':
        return new Node(new Sleep(0, 0, "Sleep"));
      case 'G':
        return new Node(new Recreation(10, 25, "Exercise"));
      case 'E':
        return new Node(new Eat(5, 0, "Eat"));
      case 'F':
        return new Node(new Recreation(5, 10, "Feed ducks"));
      case 'L':
        return new Node(new Recreation(5, 10, "Relax at lake"));
      case 'T':
        return new Node(new Recreation(5, 20, "Head to town"));
      default:
        return new Node();
    }
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
   * Add a TilePosition to the fullMap and nodeMap.
   *
   * @param x the row of the Tile
   * @param y the column of the Tile
   * @param nod the list of activities associated with that tile
   */
  private void addTile(int x, int y, Node nod) {
    fullMap.putIfAbsent(new Vector2(x, y), new ArrayList<>());
    nodeMap.putIfAbsent(new Vector2(x, y), nod);
  }


  /**
   * Connect all the paths between tiles.
   *
   * @param tile1 the first vector
   * @param tile2 the second vector
   */
  private void addEdge(Vector2 tile1, Vector2 tile2) {
    fullMap.get(tile1).add(tile2);
    fullMap.get(tile2).add(tile1);
  }


}