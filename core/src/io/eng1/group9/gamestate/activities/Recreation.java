package io.eng1.group9.gamestate.activities;

/**
 * A recreation activity.
 */
public class Recreation extends Activity {

  /**
   * Instantiates a new Recreation.
   *
   * @param time   How much time the activity takes
   * @param energy How much energy the activity takes
   * @param name   Name of the activity
   */
  public Recreation(int time, int energy, String name) {
    super(time, energy, name);
  }

  @Override
  public String toString() {
    return String.format(super.getName() + ", %1d time, %2d energy", getTimeConsumption(),
        getEnergyConsumption());
  }
}