package eng1.group9.gamestate;

/**
 * Class that holds the current position of the player on the grid
 */
public class Player {
    TilePosition playerPosition;

    /**
     *
     * @param playerPosition holds the instance of TilePosition where the player is currently on the grid
     */
    public Player(TilePosition playerPosition){
        this.playerPosition = playerPosition;
    }

    /**
     * The player's initial starting position is at row 9, column 5
     */
    public Player() {
        this(new TilePosition(2, 1));
    }
    /**
     * returns the current player position when called
     */
    public TilePosition getPlayerPosition(){
        return playerPosition;
    }

    /**
     *
     * @param playerPosition sets the player position
     */
    public void setPlayerPosition(TilePosition playerPosition){
        this.playerPosition = playerPosition;
    }
}
