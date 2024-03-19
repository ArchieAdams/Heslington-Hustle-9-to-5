package eng1.group9.gamestate.activities;

/**
 * The class for the activity sleep
 *
 */

public class Sleep extends Activity{


    public Sleep(int time, int energy) {
        super(time, energy);
    }

    @Override
    public String toString() {
        return String.format("Sleep, %1d time, %2d energy", getTimeConsumption(), getEnergyConsumption());
    }
}