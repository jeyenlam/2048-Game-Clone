import javax.swing.*;
import java.awt.*;

public class Tile extends JComponent {

    Tile next;
    int val;

    static final int SCALE = 100;
    static final int BORDER = SCALE/20;
    static final int FONT_SIZE = (int) (SCALE*0.5);
    static final Font FONT = new Font("Arial", Font.PLAIN, FONT_SIZE);


    //default constructor
    public Tile() {
        //val = 2;
        setFont(FONT);
        setPreferredSize(new Dimension(SCALE,SCALE));
    }

    //constructor that take a value and set
    //the Tile value to that value
    public Tile(int val){
        if (power2(val)){
            this.val = val;
        }
        setFont(FONT);
        setPreferredSize(new Dimension(SCALE,SCALE));
    }

    //setters & getters
    public void setVal(int val){
        this.val = val;
    }

    public int getVal(){
        return val;
    }

    //recursive
    public boolean power2(double value){
        //base case
        if (value ==2){
            return true;
        }
        else if (value > 2){
            return power2(value/2);
        }
//        else {
//            throw new IllegalArgumentException();
//        }
        return false;
    }



    public String toString(){
        return String.valueOf(val);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        ((Graphics2D)g).setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        g.fillRect(0,0,getWidth(),getHeight());

        FontMetrics metrics = getFontMetrics(FONT);

        g.setColor(Color.black);
        g.fillRoundRect(0,0,100, 100, 50,50);

        Color color;
        switch (val){
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
            default :
                color = new Color(36, 91, 255);

        }
        g.setColor(color);
        //g.setColor(Color.orange);
        g.fillRoundRect(3,3,94,94,50,50);

        g.setColor(Color.BLACK); //font color
        if (val != 0){
            String txt = Integer.toString(val);
            g.drawString(txt,
                    (getWidth() - metrics.stringWidth(txt)) / 2,
                    getHeight() / 2 + metrics.getAscent() / 3);
        }


    }

    public static void main(String[] args){

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        Tile tile = new Tile();
        tile.setVal(4);
        panel.add(tile);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
