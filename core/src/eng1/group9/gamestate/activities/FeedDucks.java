package eng1.group9.gamestate.activities;

/**
 * A recreation activity
 */
public class FeedDucks extends Activity {

    /**
     * @param time   How much time the activity takes
     * @param energy How much energy the activity takes
     * @param name   Name of the activity
     */
    public FeedDucks(int time, int energy, String name) {
        super(time, energy, name);
    }

    @Override
    public String toString() {
        return String.format("Feed Ducks, %1d time, %2d energy", getTimeConsumption(), getEnergyConsumption());
    }
}