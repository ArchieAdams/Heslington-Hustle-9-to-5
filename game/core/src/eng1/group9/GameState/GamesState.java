package eng1.group9.GameState;

import eng1.group9.GameState.Activities.*;

import java.util.ArrayList;

public class GamesState {

    private int energy;
    private int maxEnergy;
    private int time;
    private int timeInDay;
    private int counter;
    private ArrayList<Activity> activityList = new ArrayList<>();


    public GamesState(int eng, int tim){

        this.energy = eng;
        this.maxEnergy = eng;
        this.time = tim;
        this.timeInDay = tim;
    }


    public int getEnergy(){

        return this.energy;
    }
    public int getTime(){

        return this.time;
    }

    public int getCounter(){

        return this.counter;
    }

//    public setEnergy(int eng){
//
//        this.energy = eng;
//    }
//    public setTime(int tim){
//
//        this.time = tim;
//    }




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

    public boolean performActivity(Activity act){

        if(act instanceof Sleep){

            this.counter = this.counter + 1;
            this.energy = this.maxEnergy;
            this.time = this.timeInDay;

            return true;
        }


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

}