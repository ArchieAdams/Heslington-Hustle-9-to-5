package io.eng1.group9.gamestate.activities;


/**
 * A studying activity.
 */
public class Study extends Activity {

  /**
   * Instantiates a new Study.
   *
   * @param time   How much time the activity takes
   * @param energy How much energy the activity takes
   * @param name   Name of the activity
   */
  public Study(int time, int energy, String name) {
    super(time, energy, name);
  }

  @Override
  public String toString() {
    return String.format("Study, %1d time, %2d energy", getTimeConsumption(),
        getEnergyConsumption());
  }
}