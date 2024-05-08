package io.eng1.group9.gamestate.activities;


/**
 * An eating activity.
 */
public class Eat extends Activity {

  /**
   * Instantiates a new Eat.
   *
   * @param time   How much time the activity takes
   * @param energy How much energy the activity takes
   * @param name   Name of the activity
   */
  public Eat(int time, int energy, String name) {
    super(time, energy, name);
  }

}