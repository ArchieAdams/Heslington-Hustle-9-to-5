package eng1.group9.gamestate.activities;

/**
 * Base class for the activity types
 */
public abstract class Activity {

    private final int timeConsumption;
    private final int energyConsumption;
    private final String name;

    /**
     * Instantiates a new Activity.
     *
     * @param time   How much time the activity takes
     * @param energy How much energy the activity takes
     * @param name   Name of the activity
     */
    public Activity(int time, int energy, String name) {
        this.timeConsumption = time;
        this.energyConsumption = energy;
        this.name = name;
    }

    /**
     * Returns how much time the activity takes
     *
     * @return the time consumption
     */
    public int getTimeConsumption() {
        return timeConsumption;
    }

    /**
     * Returns how much energy the activity takes
     *
     * @return the energy consumption
     */
    public int getEnergyConsumption() {
        return energyConsumption;
    }

    /**
     * Returns the name of the activity
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}

