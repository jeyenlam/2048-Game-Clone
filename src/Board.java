import java.util.LinkedList;

public class Board {
    int size;
    Tile tile;

    LinkedList<LinkedList<Tile>> board = new LinkedList<LinkedList<Tile>>();

    /**
     * Set up a by-default game board
     */
    public Board(){
        size = 4;
        for (int row =0; row < size; ++row){
            LinkedList<Tile> ll = new LinkedList<>();
            for (int col=0; col < size; ++col){
                ll.add(col,null);
            }
            board.add(row,ll);
        }
    }

    /**
     * set up a game board with a given size
     * @param size  the size that is given by players
     */
    public Board(int size){
        setSize(size);
        for (int row =0; row < size; ++row){
            LinkedList<Tile> ll = new LinkedList<>();
            for (int col=0; col < size; ++col){
                ll.add(col,null);
            }
            board.add(row,ll);
        }
    }

    /**
     * Set size of the game board
     * @param size player input
     * @throws IllegalArgumentException if size is < 4 or > 10
     */
    public void setSize(int size){
        if (size < 4 || size > 10){
            throw new IllegalArgumentException("Board(int size): Invalid size value");
        }
        this.size = size;
    }

    /**
     * Get size of the game board
     * @return size
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Getting the tile of the specific position indicated by players
     * @param row position of the tile in terms of rows
     * @param col position of the tile in terms of columns
     * @return tile indicated by players
     */
    public Tile getTile(int row, int col){
        checkRowCol(row,col);
        tile = board.get(row).get(col);
        return tile;
    }

    /**
     * Set the positionally indicated tile to the Tile t
     * @param row position of the tile in terms of rows
     * @param col position of the tile in terms of column
     * @param t the tile needed to be set at the indicated position
     */
    public void setTile(int row, int col, Tile t){
        checkRowCol(row,col);
        LinkedList<Tile> ll = board.get(row);
        ll.set(col,t);
        board.set(row,ll);
    }

    /**
     * check to see if there is any empty tile left
     * in the board
     * @return true if an empty tile founded and false otherwise
     */
    public boolean hasEmpty(){
        for (int row =0; row < size; row ++){
            for (int col =0; col < size; col ++){
                if (getTile(row,col) == null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get the value of the positionally indicated tile
     * @param row position of the tile in terms of rows
     * @param col position of the tile in terms of columns
     * @return value the tile holds or -1 if no tile founded at the indicated position
     */
    public int getValue(int row, int col){
        checkRowCol(row,col);
        tile = getTile(row,col);
        if (tile != null){
            return tile.getVal(tile);
        }
        return -1;
    }

    /**
     * Check to see if the provided row and column is valid in the board
     * @param row
     * @param col
     * @return true if position founded in the board
     * @throws IllegalArgumentException if inputs of row and col is invalid
     */
    public boolean checkRowCol(int row, int col){
        if (row >= 0 && row < size && col >=0 && col < size){
            return true;
        }
        else {
            throw new IllegalArgumentException("Invalid row and col input");
        }
    }
}
