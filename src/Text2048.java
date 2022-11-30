import javax.swing.*;

public class Text2048 {

    GameController gc;
    int size;
    int tempSize;
    int winningVal;
    int tempWinningVal;

    Tile tile = new Tile();

    public Text2048() {
        tempSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter size:"));
        while (tempSize < 4 || tempSize > 10) {
            JOptionPane.showMessageDialog(
                    null,
                    "Pick a size between 4 and 10",
                    "Invalid Size へ[ •́ ‸ •̀ ]ʋ",
                    JOptionPane.ERROR_MESSAGE);
            tempSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter size:"));
        }
        size = tempSize;

        tempWinningVal = Integer.parseInt((JOptionPane.showInputDialog(null, "Winning Value:")));
        while ((!tile.power2(tempWinningVal)) || (tile.power2(tempWinningVal) && tempWinningVal < 16)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Must be a number of power 2 & greater than 16",
                    "Invalid Winning Value へ[ •́ ‸ •̀ ]ʋ",
                    JOptionPane.ERROR_MESSAGE
            );
            tempWinningVal = Integer.parseInt(JOptionPane.showInputDialog(null, "Winning Value:"));
        }
        winningVal = tempWinningVal;

        new Text2048(size, winningVal);
        gc = new GameController(size,winningVal);
    }

    public Text2048(int size, int winningVal) {
        this.size = size;
        this.winningVal = winningVal;
        tile.SCALE = 400 / size;
        gc = new GameController(size, winningVal);
    }

    public void setSize() {
        tempSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter size:"));
        while (tempSize < 4 || tempSize > 10) {
            JOptionPane.showMessageDialog(
                    null,
                    "Pick a size between 4 and 10",
                    "Invalid Size へ[ •́ ‸ •̀ ]ʋ",
                    JOptionPane.ERROR_MESSAGE);
            tempSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter size:"));
        }
        size = tempSize;
        new Text2048(size, winningVal);
    }
}
