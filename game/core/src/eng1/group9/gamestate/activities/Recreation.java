package eng1.group9.gamestate.activities;

/**
 * The class for the activity recreation
 *
 */

public class Recreation extends Activity {


    public Recreation(int time, int energy) {
        super(time, energy);
    }

    @Override
    public String toString() {
        return String.format("Gym, %1d time, %2d energy", getTimeConsumption(), getEnergyConsumption());
    }
}