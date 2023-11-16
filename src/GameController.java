import java.util.Random;

public class GameController {

    Board board;
    Tile tile;
    GameStatus status;

    int size;
    int winningVal;
    int score = 0, bestScore = 0;

    /**
     * Default constructor of GameController class
     */
    public GameController(){
        setSize(4);
        board = new Board();
        setWinningVal(2048);
        status = GameStatus.IN_PROGRESS;
        newTile();
        newTile();
    }

    /**
     * Constructor of GameController class
     * @param size size of the game board specified by players' input
     * @param winningVal winning value of the game specified by players' input
     */
    public GameController(int size, int winningVal){
        setSize(size);
        board = new Board(size);
        setWinningVal(winningVal);
        status = GameStatus.IN_PROGRESS;
        newTile();
        newTile();
    }

    /**
     * Getter of the game's status
     * @return status of the game
     */
    public GameStatus getStatus(){
        return status;
    }

    /**
     * Getter of the game board's size
     * @return size of the game board
     */
    public int getSize(){
        return size;
    }

    /**
     * Getter of the game's winning value
     * @return winning value of the game
     */
    public int getWinningVal(){
        return winningVal;
    }

    /**
     * Getter of te game board
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Set the winning value to the argument
     * @param winningVal
     * @throws IllegalArgumentException if the argument is not a value with power of 2
     */
    public void setWinningVal(int winningVal) {
        tile = new Tile();
        if (tile.power2(winningVal)){
            this.winningVal = winningVal;
        }
        else {
            throw new IllegalArgumentException("This is not a number with power of 2");
        }
    }

    /**
     * Set the board's size to the argument
     * @param size
     * @throws IllegalArgumentException if size is invalid
     */
    public void setSize(int size) {
        if (size < 4 || size > 10){
            throw new IllegalArgumentException("Invalid size value");
        }
        this.size = size;
    }

    /**
     * Create a tile at random position in the board
     * and assign it with random number of either 2 or 4
     */
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

    /**
     * Helper method of newTile(), helps randomly pick an
     * available position on the board
     * @return rd a randomly picked position
     */
    public int pickRandom(){
        Random random = new Random();
        int max = size;
        int min = 0;
        int rd = random.nextInt(max-min) + min;
        return rd;
    }

    /**
     * Reset the game board
     */
    public void reset(){
        for (int i =0; i < size; i++){
            for (int j=0; j < size; j++){
                board.setTile(i,j,null);
            }
        }
        newTile();
        newTile();
        status = GameStatus.IN_PROGRESS;
        score = 0;
    }

    /**
     * Check to see if a tile in the board has reached to or over the winning value
     * If yes, change the game status to win. Do nothing if otherwise
     */
    private void checkWin(){
        for (int row=0; row < size; ++row){
            for (int col=0; col < size; ++col){
                if (board.getValue(row,col)>=winningVal){
                    status = GameStatus.WON;
                }
            }
        }
    }

    /**
     * Check to see if the board has no empty tiles left and
     * there is no possible moves to join tiles.
     * If the conditions met, change the status to lost
     */
    private void checkLost(){
        if (!board.hasEmpty()){
            for (int i =0; i < size; i++){
                for (int j=0; j < size; j++){
                    if (findSimilarNeighbors(i,j)){
                        return;
                    }
                }
            }
            status = GameStatus.LOST;
        }
    }

    /**
     * Compare score to bestScore to determine if bestScore should
     * be updated
     * @param score
     * @param bestScore
     */
    private void checkBestScore(int score, int bestScore){
        if (score > bestScore){
            this.bestScore = score;
        }
    }

    /**
     * Check if a tile has neighbors (tiles right above, below, left and right)
     * holding the same value.
     * @param row position of the tile in terms of rows
     * @param col position of the tile in terms of rows
     * @return true if the tile has at least 1 neighbor tile holding the same value
     */
    private boolean findSimilarNeighbors(int row, int col){
        tile = board.getTile(row,col);

        //finding neighbors of the tile when it's a corner tile
        if (col == 0 ){
            if (row ==0){
                return (tile.val == board.getValue(row, col + 1)) ||
                        (tile.val == board.getValue(row + 1, col));
            }
            else if (row == size-1){
                return (tile.val == board.getValue(row, col + 1)) ||
                        (tile.val == board.getValue(row - 1, col));
            }
            else {
                return (tile.val == board.getValue(row, col + 1)) ||
                        (tile.val == board.getValue(row - 1, col)) ||
                        (tile.val == board.getValue(row + 1, col));
            }
        }
        else if (col == size -1){
            if (row ==0){
                return (tile.val == board.getValue(row, col - 1)) ||
                        (tile.val == board.getValue(row + 1, col));
            }
            else if (row == size-1){
                return (tile.val == board.getValue(row, col - 1)) ||
                        (tile.val == board.getValue(row - 1, col));
            }
            else {
                return (tile.val == board.getValue(row, col - 1)) ||
                        (tile.val == board.getValue(row - 1, col)) ||
                        (tile.val == board.getValue(row + 1, col));
            }
        }
        else {
            if (row == 0){
                return ( tile.val == board.getValue(row,col-1) ||
                        tile.val == board.getValue(row,col+1)||
                        tile.val == board.getValue(row+1,col));
            }
            else if (row == size-1){
                return ( tile.val == board.getValue(row,col-1) ||
                        tile.val == board.getValue(row,col+1)||
                        tile.val == board.getValue(row-1,col));
            }
        }

        return ( tile.val == board.getValue(row,col-1) ||
                tile.val == board.getValue(row,col+1)||
                tile.val == board.getValue(row+1,col)) ||
                tile.val == board.getValue(row-1,col);
    }

    /**
     * Move tiles of each row from the right to left until
     * all tiles are orderly pushed to the left
     * @param row position of the tile in terms of rows
     */
    public void recurseLeft(int row){
        for (int index =0; index < size; index++){
            if (board.getTile(row,index)!=null){
                tile = board.getTile(row,index);
                moveLeft(row,index,tile);
            }
        }
        if ( board.hasEmpty() && row == size-1){
            newTile();
        }
        checkWin();
        checkLost();
    }

    /**
     * Helper method of recurseLeft that take a positionally specified tile
     * and move it to the left
     * Whilst the moving progress, combine tiles together if the one next to it
     * in the moving direction matches its value
     * @param row position of the tile in terms of rows
     * @param col position of the tile in terms of columns
     * @param tile the tile of the specified location
     */
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
        else {
            if (findSimilarNeighbors(row,col)
                    && board.getValue(row,col-1) == tile.val){
                tile = new Tile(tile.val*2);
                score += tile.val;
                checkBestScore(score,bestScore);

                board.setTile(row,col-1,tile);
                board.setTile(row,col,null);

                moveLeft(row,col-1,tile);
            }
            return;
        }
        moveLeft(row,col-1,tile);
    }

    /**
     * Move tiles of each row from the left to right until
     * all tiles are orderly pushed to the right
     * @param row position of the tile in terms of rows
     */
    public void recurseRight(int row){
        for (int index = size-1; index >= 0; index--){
            if (board.getTile(row,index)!=null){
                tile = board.getTile(row,index);
                moveRight(row,index,tile);
            }
        }
        if ( board.hasEmpty() && row == size-1){
            newTile();
        }
        checkWin();
        checkLost();
    }

    /**
     * Helper method of recurseRight that take a positionally specified tile
     * and move it to the right
     * Whilst the moving progress, combine tiles together if the one next to it
     * in the moving direction matches its value
     * @param row position of the tile in terms of rows
     * @param col position of the tile in terms of columns
     * @param tile the tile of the specified location
     */
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
        else {
            if (findSimilarNeighbors(row,col)
                    && board.getValue(row,col+1) == tile.val){
                tile = new Tile(tile.val*2);
                score += tile.val;
                checkBestScore(score,bestScore);

                board.setTile(row,col+1,tile);
                board.setTile(row,col,null);

                moveRight(row,col+1,tile);
            }
            return;
        }
        moveRight(row,col+1,tile);
    }

    /**
     * Move tiles of each column from bottom to top until
     * all tiles are orderly pushed to the top
     * @param col position of the tile in terms of columns
     */
    public void recurseUp(int col){
        for (int index=0; index < size; index++){
            if (board.getTile(index,col) != null){
                tile = board.getTile(index,col);
                moveUp(index,col,tile);
            }
        }
        if (board.hasEmpty() && col == size-1){
            newTile();
        }
        checkWin();
        checkLost();
    }

    /**
     * Helper method of recurseLeft that take a positionally specified tile
     * and move it from bottom to top
     * Whilst the moving progress, combine tiles together if the one next to it
     * in the moving direction matches its value
     * @param row position of the tile in terms of rows
     * @param col position of the tile in terms of columns
     * @param tile the tile of the specified location
     */
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
        else {
            if (board.getValue(row-1,col) == tile.val){
                tile = new Tile(tile.val*2);
                score += tile.val;
                checkBestScore(score,bestScore);

                board.setTile(row-1,col,tile);
                board.setTile(row,col,null);

                moveUp(row-1,col,tile);
            }
            return;
        }
        moveUp(row-1,col,tile);
    }

    /**
     * Move tiles of each column from top to bottom until
     * all tiles are orderly pushed to the bottom
     * @param col position of the tile in terms of columns
     */
    public void recurseDown(int col){
        for (int index=size-1; index >= 0; index--){
            if (board.getTile(index,col) != null){
                tile = board.getTile(index,col);
                moveDown(index,col,tile);
            }
        }
        if (board.hasEmpty() && col == size-1){
            newTile();
        }
        checkWin();
        checkLost();
    }

    /**
     * Helper method of recurseLeft that take a positionally specified tile
     * and move it from top to bottom
     * Whilst the moving progress, combine tiles together if the one next to it
     * in the moving direction matches its value
     * @param row position of the tile in terms of rows
     * @param col position of the tile in terms of columns
     * @param tile the tile of the specified location
     */
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
        else {
            if (board.getValue(row+1,col) == tile.val){
                tile = new Tile(tile.val*2);
                score += tile.val;
                checkBestScore(score,bestScore);

                board.setTile(row,col,null);
                board.setTile(row+1,col,tile);

                moveDown(row+1,col,tile);
            }
            return;
        }
        moveDown(row+1,col,tile);
    }
}
