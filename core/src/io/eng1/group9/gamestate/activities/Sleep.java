package io.eng1.group9.gamestate.activities;


/**
 * A sleeping activity.
 */
public class Sleep extends Activity {

  /**
   * Instantiates a new Sleep.
   *
   * @param time   How much time the activity takes
   * @param energy How much energy the activity takes
   * @param name   Name of the activity
   */
  public Sleep(int time, int energy, String name) {
    super(time, energy, name);
  }

}