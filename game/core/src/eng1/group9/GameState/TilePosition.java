package eng1.group9.GameState;

/**
 * This class is for the row and column of a certain tile
 */
public class TilePosition{
    int row;
    int column;

    //Constructor
    /**
     * @param row int, row position on map grid of tile
     * @param column int, column position on map grid of tile
     */
    public TilePosition(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }

    public void setRow(int row){
        this.row = row;
    }
    public void setColumn(int column){
        this.column = column;
    }

}