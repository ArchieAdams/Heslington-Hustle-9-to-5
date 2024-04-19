package eng1.group9.gamestate;

/** Stores information about the player */
public class Player {
    TilePosition playerPosition;

    /** Create a new player at the given position
     *
     * @param playerPosition the TilePosition specifying the player's starting location on the grid
     */
    public Player(TilePosition playerPosition){
        this.playerPosition = playerPosition;
    }

    /** Create a new player with a starting position of row 2, column 1 */
    public Player() {
        this(new TilePosition(2, 7));
    }

    public TilePosition getPosition(){
        return playerPosition;
    }

    public void setPosition(TilePosition playerPosition){
        this.playerPosition = playerPosition;
    }
}
