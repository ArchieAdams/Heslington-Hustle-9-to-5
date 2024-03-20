package eng1.group9.gamestate;

/** Holds a grid position. Zero-based */
public class TilePosition{
    int row;
    int column;

    /**
     * @param row row position on map grid of tile, starting at 0
     * @param column column position on map grid of tile, starting at 0
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
    public void set(int row, int column) {
        this.row = row;
        this.column = column;
    }

}