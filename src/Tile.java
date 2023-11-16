import javax.swing.*;
import java.awt.*;

public class Tile extends JComponent {

    int val;

    static int SCALE = 100;
    static final int FONT_SIZE = (int) (SCALE*0.5);
    static final Font FONT = new Font("Arial", Font.PLAIN, FONT_SIZE);

    /**
     * Default constructor of Tile
     */
    public Tile() {
        setFont(FONT);
        setPreferredSize(new Dimension(SCALE,SCALE));
    }

    /**
     * Constructor of tile that set a tile
     * to the value
     * Set font and graphic of the tile
     * @param val
     */
    public Tile(int val){
        setVal(val);
        setFont(FONT);
        setPreferredSize(new Dimension(SCALE,SCALE));
    }

    /**
     * Set value of a tile to the argument
     * after checking if the argument is valid
     * @param val
     * @throws IllegalArgumentException if value is not power of 2
     */
    public void setVal(int val){
        if (power2(val)){
            this.val = val;
        }
        else {
            throw new IllegalArgumentException("Invalid input");
        }
    }

    /**
     * Get value of a tile
     * @return val value of the tile
     */
    public int getVal(Tile tile){
        return tile.val;
    }

    /**
     * Check if a value is a number of power 2
     * @param value
     * @return true if the argument is a number of power 2 and false otherwise
     */
    public boolean power2(double value){
        if (value ==2){
            return true;
        }
        else if (value > 2){
            return power2(value/2);
        }
        return false;
    }

    /**
     * Covert a tile's value into string
     * @return val as a string
     */
    public String toString(){
        return String.valueOf(val);
    }

    /**
     * Visualize tiles as squares of different colors
     * based on the value it holds
     * @param g an object of Graphic class
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ((Graphics2D) g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        FontMetrics metrics = getFontMetrics(FONT);

        g.setColor(Color.black);
        g.fillRoundRect(0, 0, SCALE, SCALE, SCALE - 50, SCALE - 50);

        Color color;
        switch (val) {
            case 0:
                color = Color.WHITE;
                break;
            case 2:
                color = new Color(255, 248, 117);
                break;
            case 4:
                color = new Color(255, 141, 133);
                break;
            case 8:
                color = new Color(171, 133, 255);
                break;
            case 16:
                color = new Color(157, 255, 71);
                break;
            case 32:
                color = new Color(255, 148, 241);
                break;
            case 64:
                color = new Color(189, 132, 0);
                break;
            case 128:
                color = new Color(128, 67, 177);
                break;
            case 256:
                color = new Color(0, 255, 187);
                break;
            case 512:
                color = new Color(92, 157, 255);
                break;
            case 1024:
                color = new Color(255, 109, 51);
                break;
            case 2048:
                color = new Color(255, 36, 58);
                break;
            default:
                color = new Color(36, 91, 255);
                break;
        }
        g.setColor(color);
        g.fillRoundRect(3, 3, SCALE - 6, SCALE - 6, SCALE - 50, SCALE - 50);

        g.setColor(Color.BLACK); //font color
        if (val != 0) {
            String txt = Integer.toString(val);
            g.drawString(txt,
                    (getWidth() - metrics.stringWidth(txt)) / 2,
                    getHeight() / 2 + metrics.getAscent() / 3);
        }
    }
}
