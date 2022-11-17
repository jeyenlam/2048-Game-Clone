import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    Tile tile;
    Board board;
    Text2048 text2048;
    int size;

    JFrame frame;

    JPanel mainPanel;
    JPanel gamePanel;
    JPanel keyPanel;
    JMenuBar menu;
    JMenuItem reset;
    JMenuItem quit;
    JMenuItem resize;

    JButton upButton;
    JButton downButton;
    JButton leftButton;
    JButton rightButton;

    public GameFrame() {

        text2048 = new Text2048();
        this.size = text2048.size;
        board = text2048.gc.board;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame = new JFrame("Let's 2048");
        frame.setLayout(null);
        frame.setSize(413, 600);

        mainPanel = new JPanel();
        mainPanel.setSize(403, 540);
        mainPanel.setLayout(null);


        ////////////////////// MENU & OPTIONS //////////////////////////
        menu = new JMenuBar();
        frame.setJMenuBar(menu);

        resize = new JMenuItem("Resize");
        reset = new JMenuItem("Reset");
        quit = new JMenuItem("Quit");

        menu.add(resize);
        menu.add(reset);
        menu.add(quit);

        //////////////////////// KEY PANEL /////////////////////////
        keyPanel = new JPanel();
        keyPanel.setLayout(new GridBagLayout());
        keyPanel.setLocation(0, 400);
        keyPanel.setSize(400, 150);
        keyPanel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();


        upButton = new JButton("    UP    ");
        downButton = new JButton("DOWN");
        leftButton = new JButton("LEFT");
        rightButton = new JButton("RIGHT");


        c.gridx = 1;
        c.gridy = 0;
        keyPanel.add(upButton, c);

        c.gridx = 0;
        c.gridy = 1;
        keyPanel.add(leftButton, c);

        c.gridx = 2;
        c.gridy = 1;
        keyPanel.add(rightButton, c);

        c.gridx = 1;
        c.gridy = 2;
        keyPanel.add(downButton, c);
        mainPanel.add(keyPanel);


        //////////////////////// GAME PANEL /////////////////////////

        gamePanel = new JPanel();
        gamePanel.setSize(400, 400);

        gamePanel.setLayout(new GridLayout(4, 4));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //System.out.println(board.getTile(i, j));
                //board.setTile(i,j,new Tile(2));
                if (board.getTile(i,j) == null){
                    gamePanel.add(new Tile());
                }
                else {
                    gamePanel.add(board.getTile(i,j));
                }
            }
        }


        mainPanel.add(gamePanel);

        /////////////////////////////////////////////////////////////////

        frame.add(mainPanel);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {

        //Text2048 text2048 = new Text2048();
        GameFrame frame = new GameFrame();
        //System.out.println(board.getTile(0,3));

        for (int i =0; i < frame.size; i++){
            for (int j=0; j < frame.size; j++){
                System.out.println(frame.board.getTile(i,j));
                }
            }
        }
    }
