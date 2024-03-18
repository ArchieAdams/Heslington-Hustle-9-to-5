package eng1.group9.gamestate;

/**
 * This class is for the row and column of a certain tile
 */
public class TilePosition{
    int row;
    int column;

    //Constructor
    /**
     * @param row int, row position on map grid of tile
     * @param column int, columh position on map grid of tile
     */
    public TilePosition(int row, int column){
        this.row = row;
        this.column = column;
    }
    //Getters
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }

    //Setters
    public void setRow(int row){
        this.row = row;
    }
    public void setColumn(int column){
        this.column = column;
    }

}