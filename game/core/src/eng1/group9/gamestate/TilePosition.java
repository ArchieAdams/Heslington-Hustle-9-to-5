package eng1.group9.gamestate;

/** Holds a grid position. Zero-based */
public class TilePosition{
    int column;
    int row;



    /**
     * @param row row position on map grid of tile, starting at 0
     * @param column column position on map grid of tile, starting at 0
     */
    public TilePosition(int column, int row){
        this.column = column;
        this.row = row;
    }

    public int getColumn(){
        return column;
    }
    public int getRow(){
        return row;
    }

    public void setColumn(int column){
        this.column = column;
    }
    public void setRow(int row){
        this.row = row;
    }
    public void set(int column, int row) {
        this.column = column;
        this.row = row;
    }

}