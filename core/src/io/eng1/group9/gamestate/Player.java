package io.eng1.group9.gamestate;

import com.badlogic.gdx.math.Vector2;

/**
 * Stores information about the player.
 */
public class Player {
  /**
   * The Player position.
   */
  Vector2 playerPosition;

  /**
   * Create a new player at the given position.
   *
   * @param playerPosition the TilePosition specifying the player's starting location on the grid
   */
  public Player(Vector2 playerPosition) {
    this.playerPosition = playerPosition;
  }

  /**
   * Create a new player with a starting position of row 2, column 1.
   */
  public Player() {
    this(new Vector2(1, 7));
  }


  /**
   * Gets position.
   *
   * @return the position
   */
  public Vector2 getPosition() {
    return playerPosition;
  }

  /**
   * Sets position.
   *
   * @param playerPosition the player position
   */
  public void setPosition(Vector2 playerPosition) {
    this.playerPosition = playerPosition;
  }
}
