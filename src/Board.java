public class Board {
    int size;
    Tile tile;

    LinkedList<LinkedList<Tile>> board = new LinkedList<LinkedList<Tile>>();


    //default constructor
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

    //constructor
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

    /* method checking if there are any empty cells left
    on the board */
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


    //get a tile at specific given row & col
    public Tile getTile(int row, int col){
        tile = board.get(row).get(col);
        return tile;
    }

    //set the indicated tile to Tile t
    public void setTile(int row, int col, Tile t){
        if (row <= size && col <= size){
            LinkedList<Tile> ll = board.get(row);
            ll.set(col,t);
            board.set(row,ll);
        }
        else {
            throw new IllegalArgumentException("setTile(): Invalid row & col input");
        }
    }

    //get value at a particular pos
    public int getValue(int row, int col){
        if (row <= size && col <= size){
            tile = getTile(row,col);
            if (tile != null){
                return tile.val;
            }
            return -1; //if no Tile founded at the pos
        }
        else {
            throw new IllegalArgumentException("getValue(): Invalid row & col input");
        }
    }

    public static void main(String[] args){
        Board board = new Board(4);
        Tile t = new Tile(16);
        board.setTile(3,0,t);
        for (int i =0; i < 4; ++i){
            for (int j=0; j < 4; ++j){
                board.setTile(i,j,t);
            }
        }
        System.out.println(board.hasEmpty());
        System.out.println(board.getValue(3,0));
    }

}
