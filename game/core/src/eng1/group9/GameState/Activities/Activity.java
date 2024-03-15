package eng1.group9.GameState.Activities;


/**
 * This class is the abstract class for the eat, sleep, study, and recreation classes
 *
 *  timeConsumption int, record of how much time is consumed for this activity
 *  energyConsumption int, record of how much energy is consumed
 */
public abstract class Activity {

    private int timeConsumption;
    private int energyConsumption;


    //Constructor
    public Activity(int time, int energy){

        this.timeConsumption = time;
        this.energyConsumption = energy;
    }

    public Activity(){

        this.timeConsumption = 10;
        this.energyConsumption = 20;

    }


    //getters
    public int getTimeConsumption(){

        return  timeConsumption;
    }
    public int getEnergyConsumption(){

        return energyConsumption;
    }

}