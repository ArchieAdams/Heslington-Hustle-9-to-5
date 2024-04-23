package eng1.group9.gamestate;

import eng1.group9.gamestate.activities.Activity;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private final List<Activity> activities = new ArrayList<>();

    public Day() {
    }

    /**
     * Add activity.
     *
     * @param activity the activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * Counts number of actives of a given name
     *
     * @param name Simple name of the activity class you want to count
     * @return The count of the number of times that activity appears
     */
    public int getNumberOfActivity(String name) {
        return (int) activities.stream().filter(activity -> activity.getClass().getSimpleName().equals(name)).count();
    }
}
