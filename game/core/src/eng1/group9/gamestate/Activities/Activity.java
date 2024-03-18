package eng1.group9.gamestate.Activities;



public abstract class Activity {

    /**
     * This class is the abstract class for the eat, sleep, study, and recreation classes
     *
     *  @param timeConsumption int, record of how much time is consumed for this activity
     *  @param energyConsumption int, record of how much energy is consumed
     */

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