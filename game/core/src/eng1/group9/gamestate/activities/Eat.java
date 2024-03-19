package eng1.group9.gamestate.activities;


/**
 * The class for the activity eat
 *
 */
public class Eat extends Activity {


    public Eat(int time, int energy) {
        super(time, energy);
    }

    @Override
    public String toString() {
        return String.format("Eat, %1d time, %2d energy", getTimeConsumption(), getEnergyConsumption());
    }
}