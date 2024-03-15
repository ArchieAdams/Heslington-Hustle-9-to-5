package eng1.group9;

public class GameState{

    private int energy;
    private int maxEnergy;
    private int time;
    private int timeIn;

    public GameState(int eng, int tim){

        this.energy = eng;
        this.maxEnergy = eng;
        this.time = tim;
        this.timeInDay = tim;
    }


    public getEnergy(){

        return this.energy;
    }
    public getTime(){

        return this.time;
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

        if((direction == 'up' and this.canMoveUp() == true)){

            //TODO:implement this
            return true;
        }

        if((direction == 'down' and this.canMoveDown() == true)){

            //TODO:implement this
            return true;
        }

        if((direction == 'left' and this.canMoveLeft() == true)){

            //TODO:implement this
            return true;
        }

        if((direction == 'right' and this.canMoveRight() == true)){

            //TODO:implement this
            return true;
        }

        return false;
    }


    private boolean canMoveUp(){


        return false:
    }

    private boolean canMoveDown(){


        return false:
    }

    private boolean canMoveLeft(){


        return false:
    }

    private boolean canMoveRight(){


        return false:
    }

    public performActivity(Activity act){

        if(act instanceof Sleep){

            this.energy = this.maxEnergy;
        }

    }

}