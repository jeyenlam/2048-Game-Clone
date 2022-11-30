import org.junit.Test;
import static org.junit.Assert.*;

public class Test2048 {
    @Test
    public void TestIsEmpty1(){
        Board board = new Board(4);
        for (int i =0; i < 4; ++i){
            for (int j=0; j < 4; ++j){
                board.setTile(i,j,new Tile(16));
            }
        }
        assertFalse(board.hasEmpty());
    }

    @Test
    public void TestIsEmpty2(){
        Board board = new Board(4);
        Tile t = new Tile(16);
        board.setTile(3,2,new Tile(2));
        assertTrue(board.hasEmpty());
    }

    @Test
    public void TestBoardSize1(){
        Board board = new Board(5);
        assertEquals(board.size,5);
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestBoardSize2(){
        Board board = new Board(2);
    }

    @Test
    public void TestSetTile1(){
        Board board = new Board(4);
        board.setTile(1,1,new Tile(2));
        assertEquals(board.getValue(1,1),2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestSetTile2(){
        Board board = new Board(5);
        board.setTile(1,1,new Tile(3));
    }

    @Test
    public void TestGetTile1(){
        Board board = new Board(4);
        board.setTile(1,1,new Tile(2048));
        Tile tile = board.getTile(1,1);
        assertEquals(board.getTile(1,1),tile);
    }

    @Test (expected = IllegalArgumentException.class)
    public void TestGetTile2(){
        Board board = new Board(4);
        board.getTile(-1,1);
    }

    @Test
    public void TestGetValue1(){
        Board board = new Board(4);
        assertEquals(board.getValue(1,0),-1);
    }

    @Test
    public void TestGetValue2(){
        Board board = new Board(4);
        board.setTile(1,1,new Tile(32));
        assertEquals(board.getValue(1,1),32);
    }
}
