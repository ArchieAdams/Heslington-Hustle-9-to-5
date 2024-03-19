package eng1.group9.gamestate.activities;

/**
 * The class for the activity sleep
 *
 */

public class Study extends Activity{


    public Study(int time, int energy) {
        super(time, energy);
    }

    @Override
    public String toString() {
        return String.format("Study, %1d time, %2d energy", getTimeConsumption(), getEnergyConsumption());
    }
}