package eng1.group9.Activities;

abstract class Activity {

    private int timeConsumption;
    private int energyConsumption;


    //Constructor
    public Activity(time, energy){

        this.timeConsumption = time;
        this.energyConsumption = energy;
    }


    //getters
    public int getTimeConsumption(){

        return  timeConsumption;
    }
    public int getEnergyConsumption(){

        return energyConsumption;
    }


    //setters
    public int setTimeConsumption(int value) {

        timeConsumption = value;
    }
    public int setEnergyConsumption(int value) {

        energyConsumption = value;
    }
}