package eng1.group9.GameState;

import eng1.group9.GameState.Activities.*;

import java.util.ArrayList;

/*
This class is the main game logic class. The 'game state', i.e. the record of the energy
and time, etc of the player is kept and modified in this class.

energy int, the remaining energy of the player
maxEnergy int, the energy the player starts with in a day
time int, the remaining time of the player
timeInDay int, the total time in a day
counter int, the counter of the days
activityList, the list of activities taken place


 */

public class GamesState {

    private int energy;
    private int maxEnergy;
    private int time;
    private int timeInDay;
    private int counter;
    private ArrayList<Activity> activityList = new ArrayList<>();


    //constructor
    public GamesState(int eng, int tim){

        this.energy = eng;
        this.maxEnergy = eng;
        this.time = tim;
        this.timeInDay = tim;
    }


    //getters
    public int getEnergy(){

        return this.energy;
    }
    public int getTime(){

        return this.time;
    }

    public int getCounter(){

        return this.counter;
    }

    public ArrayList<Activity> getActivityList(){

        return this.activityList;
    }


    //the function to update the player position
    public boolean move(String direction){

        if((direction.equals("up")) && (this.canMoveUp())){

            //TODO;implement this
            return true;
        }

        if((direction.equals("down")) && (this.canMoveDown())){

            //TODO;implement this
            return true;
        }

        if((direction.equals("left")) && (this.canMoveLeft())){

            //TODO;implement this
            return true;
        }

        if((direction.equals("right")) && (this.canMoveRight())){

            //TODO;implement this
            return true;
        }

        return false;
    }


    //the functions to check whether the player is capable of moving in
    //that direction
    private boolean canMoveUp(){


        return false;
    }

    private boolean canMoveDown(){


        return false;
    }

    private boolean canMoveLeft(){


        return false;
    }

    private boolean canMoveRight(){


        return false;
    }


    //the function to perform an activity the user wants
    public boolean performActivity(Activity act){


        //if the activity is Sleep, the time and energy is reset
        //and the counter of the day increments
        if(act instanceof Sleep){

            this.counter = this.counter + 1;
            this.energy = this.maxEnergy;
            this.time = this.timeInDay;

            return true;
        }


        //if the activity is Eat, study or recreation, then the time and energy is decremented
        //by the desired amount and the activity is recorded in the ArrayList
        if((act.getEnergyConsumption() < this.energy) && (act.getTimeConsumption() < this.time)) {

            int tempTime = act.getTimeConsumption();
            int tempEnergy = act.getEnergyConsumption();

            activityList.add(act);
            this.time = this.time - tempTime;
            this.energy = this.energy - tempEnergy;

            return true;

        }else {

            return false;
        }
    }


    public boolean getActivities(){


        return true;
    }

    private boolean getPlayerPosition(){

        return true;
    }

}