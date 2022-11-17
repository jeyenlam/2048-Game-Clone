import org.junit.Test;
import static org.junit.Assert.*;


public class Test2048 {
    @Test
    public void TestIsEmpty(){
        Board board = new Board(4);
        Tile t = new Tile(16);
        board.setTile(3,0,t);
        for (int i =0; i < 4; ++i){
            for (int j=0; j < 4; ++j){
                board.setTile(i,j,t);
            }
        }
        assertFalse(board.hasEmpty());
    }

    @Test
    public void TestNewTile(){
        GameController gc = new GameController(4,88);
        gc.newTile();
    }

}
