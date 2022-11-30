public class Board {
    int size;
    Tile tile;

    LinkedList<LinkedList<Tile>> board = new LinkedList<LinkedList<Tile>>();

    /**
     * Set up a by-default game board
     */
    public Board(){
        this.size = 4;
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
        if (size < 4 || size > 10){
            throw new IllegalArgumentException("Board(int size): Invalid size value");
        }
        this.size = size;

        for (int row =0; row < size; ++row){
            LinkedList<Tile> ll = new LinkedList<>();
            for (int col=0; col < size; ++col){
                ll.add(col,null);
            }
            board.add(row,ll);
        }
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
     * Getting the tile of the specific position indicated by players
     * @param row position of the tile in terms of rows
     * @param col position of the tile in terms of columns
     * @return tile indicated by players
     */
    public Tile getTile(int row, int col){
        if (row >= 0 && row < size && col >= 0 && col < size){
            tile = board.get(row).get(col);
            return tile;
        }
        else {
            throw new IllegalArgumentException("Invalid row & col input");
        }
    }

    /**
     * Set the positionally indicated tile to the Tile t
     * @param row position of the tile in terms of rows
     * @param col position of the tile in terms of column
     * @param t the tile needed to be set at the indicated position
     */
    public void setTile(int row, int col, Tile t){
        if (row >= 0 && row < size && col >= 0 && col < size){
            LinkedList<Tile> ll = board.get(row);
            ll.set(col,t);
            board.set(row,ll);
        }
        else {
            throw new IllegalArgumentException("Invalid row & col input");
        }
    }

    /**
     * Get the value of the positionally indicated tile
     * @param row position of the tile in terms of rows
     * @param col position of the tile in terms of columns
     * @return value the tile holds or -1 if no tile founded at the indicated position
     */
    public int getValue(int row, int col){
        if (row >= 0 && row < size && col >=0 && col < size){
            tile = getTile(row,col);
            if (tile != null){
                return tile.getVal(tile);
            }
            return -1;
        }
        else {
            throw new IllegalArgumentException("Invalid row & col input");
        }
    }
}
