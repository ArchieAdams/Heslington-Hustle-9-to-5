/**
 * This class is for the i and j position of a certain tile
 *
 * @param i int, i position on map grid of tile
 * @param j int, j position on map grid of tile
 */
public class TilePosition{
    int i;
    int j;

    //Constructor
    public TilePosition(int i,int j){
        this.i = i;
        this.j = j;
    }
    //Getters
    public int getI(){
        return i;
    }
    public int getJ(){
        return j;
    }

    //Setters
    public void setI(int i){
        this.i = i;
    }
    public void setJ(int J){
        this.j = j;
    }

}