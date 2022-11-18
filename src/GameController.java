import java.util.Random;

public class GameController {
    int size;
    Board board;
    GameStatus status;
    int winningVal;
    Tile tile;

    public GameController(){
        size = 4;
        board = new Board();
        winningVal = 2048;
        status = GameStatus.IN_PROGRESS;

//        if (isEmpty()){
//            newTile();
//            newTile();
//        }
    }

    public GameController(int size, int winningVal){
        this.size = size;
        board = new Board(size);
        this.winningVal = winningVal;
        status = GameStatus.IN_PROGRESS;
        newTile();
        newTile();
    }

    //setters & getters
    public GameStatus getStatus(){
        return status;
    }

    public int getSize(){
        return size;
    }

    public int getWinningVal(){
        return winningVal;
    }

    public Board getBoard() {
        return board;
    }

    public void setWinningVal(int winningVal) {
        this.winningVal = winningVal;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public void newTile(){
        int random = new Random().nextBoolean()? 2 : 4;
        tile = new Tile(random);

        boolean added = false;
        while (!added){
            int row = pickRandom();
            int col = pickRandom();
            if (board.getTile(row,col)==null){
                board.setTile(row,col,tile);
                added=true;
            }
        }

    }

    public int pickRandom(){
        Random random = new Random();
        int max = size;
        int min = 0;
        int rd = random.nextInt(max-min) + min;
        return rd;
    }

    public void reset(){
        board = new Board(size);
        newTile();
        status = GameStatus.IN_PROGRESS;
    }

    private void checkWin(){
        for (int row=0; row < size; ++row){
            for (int col=0; col < size; ++col){
                if (board.getValue(row,col)==winningVal){
                    status = GameStatus.WON;
                }
            }
        }
    }

    private void checkLost(){
        if (!board.hasEmpty()){
            status = GameStatus.LOST;
        }
    }

    private boolean findSimilarNeighbors(int row, int col){
        tile = board.getTile(row,col);
        for (int index =0; index < size; index++){

            //finding horizontally
            if ( board.getTile(row,index).val == tile.val
                    && index != col ){
                return true;
            }
            //finding vertically
            if ( board.getTile(index, col).val == tile.val
                    && index != row ){
                return true;
            }
        }
        return false;
    }

    ////////////// THE FREAKING MOST INTERESTING PART////////////////////
    public void recurseLeft(int row){
        for (int index =0; index < size; index++){
            if (board.getTile(row,index)!=null){
                tile = board.getTile(row,index);
                moveLeft(row,index,tile);
            }
        }

    }

    public void moveLeft(int row, int col, Tile tile){
        if (col == 0){
            if (board.getTile(row,col)== null){
                board.setTile(row,col,tile);
                board.setTile(row,col+1,null);
                return;
            }
            return;
        }
        if (board.getTile(row,col-1)==null){
            board.setTile(row,col-1,tile);
            board.setTile(row,col,null);
        }
        moveLeft(row,col-1,tile);
    }


    public void recurseRight(int row){
        for (int index =0; index < size; index++){
            if (board.getTile(row,index)!=null){
                tile = board.getTile(row,index);
                moveRight(row,index,tile);
            }
        }
    }

    public void moveRight(int row, int col, Tile tile){
        if (col == size-1){
            if (board.getTile(row,col)== null){
                board.setTile(row,col,tile);
                board.setTile(row,col-1,null);
                return;
            }
            return;
        }
        if (board.getTile(row,col+1)==null){
            board.setTile(row,col+1,tile);
            board.setTile(row,col,null);
        }
        moveRight(row,col+1,tile);
    }

    public void recurseUp(int col){
        for (int index=size-1; index >= 0; index--){
            if (board.getTile(index,col) != null){
                tile = board.getTile(index,col);
                moveUp(index,col,tile);
            }
        }
    }

    public void moveUp(int row, int col, Tile tile){
        if (row == 0){
            if (board.getTile(row,col)== null){
                board.setTile(row,col,tile);
                board.setTile(row+1,col,null);
                return;
            }
            return;
        }
        if (board.getTile(row-1,col)==null){
            board.setTile(row-1,col,tile);
            board.setTile(row,col,null);
        }
        moveUp(row-1,col,tile);
    }

    public void recurseDown(int col){
        for (int index=0; index < size; index++){
            if (board.getTile(index,col) != null){
                tile = board.getTile(index,col);
                moveDown(index,col,tile);
            }
        }
    }

    public void moveDown(int row, int col, Tile tile){
        if (row == size-1){
            if (board.getTile(row,col)== null){
                board.setTile(row,col,tile);
                board.setTile(row-1,col,null);
                return;
            }
            return;
        }
        if (board.getTile(row+1,col)==null){
            board.setTile(row+1,col,tile);
            board.setTile(row,col,null);
        }
        moveDown(row+1,col,tile);
    }

    /////////////////////////////////////////////////////


    public static void main(String[] args){
        int roww=0;
        GameController gc = new GameController(4,66);
        for (int row=0; row < 4; row++){
            for (int col=0; col < 4; col++){
                System.out.print(gc.board.getTile(row,col) + " ");
                if (gc.board.getTile(row,col)!=null){
                    roww = row;
                }
            }
            System.out.println();
        }

        System.out.println("-------------------------\n");

        gc.recurseLeft(roww);
        for (int row=0; row < 4; row++){
            for (int col=0; col < 4; col++){
                System.out.print(gc.board.getTile(row,col) + " ");
            }
            System.out.println();
        }


    }


}
