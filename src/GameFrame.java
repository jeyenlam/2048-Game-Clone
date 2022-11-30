import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements ActionListener, KeyListener {

    Board board;
    Text2048 text2048;
    GameController gc;
    GameStatus status;

    int size, winningVal;

    int round=1, score=0, bestScore = 0;

    JFrame frame; JMenuBar menu;

    JMenuItem reset, quit, resize;

    JPanel mainPanel, gamePanel, keyPanel, statusPanel;

    JButton upButton, downButton, leftButton, rightButton;

    JLabel roundLabel, scoreLabel, bestScoreLabel;

    public GameFrame() {

        text2048 = new Text2048();
        gc = text2048.gc;
        size = gc.size;
        winningVal = gc.winningVal;
        board = gc.board;
        status = gc.status;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame = new JFrame("Let's 2048 ヽ(>∀ <☆)ノ");
        frame.setLayout(null);
        frame.setSize(414, 590);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.blue);
        mainPanel.setSize(413, 590);
        mainPanel.setLayout(null);

        // MENU
        menu = new JMenuBar();
        frame.setJMenuBar(menu);

        resize = new JMenuItem("           Resize");
        reset = new JMenuItem("             Reset");
        quit = new JMenuItem("              Quit");

        menu.add(resize);
        menu.add(reset);
        menu.add(quit);

        resize.addActionListener(this);
        reset.addActionListener(this);
        quit.addActionListener(this);

        // STATUS PANEL
        statusPanel = new JPanel();
        statusPanel.setSize(414,30);

        roundLabel = new JLabel("Round + " + round);
        scoreLabel = new JLabel("Score " + score);
        bestScoreLabel = new JLabel("Best " + bestScore);

        statusPanel.add(roundLabel);
        statusPanel.add(scoreLabel);
        statusPanel.add(bestScoreLabel);

        mainPanel.add(statusPanel);

        // GAME PANEL
        gamePanel = new JPanel();
        gamePanel.setLocation(0,30);
        gamePanel.setSize(400, 400);

        gamePanel.setLayout(new GridLayout(size,size));
        updateBoard();
        mainPanel.add(gamePanel);

        // KEY PANEL
        keyPanel = new JPanel();
        keyPanel.setLayout(new GridBagLayout());
        keyPanel.setLocation(0, 430);
        keyPanel.setSize(414, 100);
        keyPanel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();

        upButton = new JButton("    UP    ");
        downButton = new JButton("DOWN");
        leftButton = new JButton("LEFT");
        rightButton = new JButton("RIGHT");

        c.gridx = 1; c.gridy = 0;
        keyPanel.add(upButton, c);

        c.gridx = 0; c.gridy = 1;
        keyPanel.add(leftButton, c);

        c.gridx = 2; c.gridy = 1;
        keyPanel.add(rightButton, c);

        c.gridx = 1; c.gridy = 2;
        keyPanel.add(downButton, c);

        mainPanel.add(keyPanel);

        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        upButton.addActionListener(this);
        downButton.addActionListener(this);

        //
        frame.add(mainPanel);
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    public void updateBoard(){

        score = gc.score;
        bestScore = gc.bestScore;

        roundLabel.setText("Round " + round + "            ");
        scoreLabel.setText("Score " + score+ "            ");
        bestScoreLabel.setText("Best Score " + bestScore+ "            ");

        gamePanel.removeAll();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getTile(i, j) == null) {
                    gamePanel.add(new Tile());
                } else {
                    gamePanel.add(board.getTile(i, j));
                }
            }
        }
        gamePanel.validate();
        gamePanel.repaint();
    }

    public void checkStatus(){

        status = gc.status;

        if (status == GameStatus.WON){
            JOptionPane.showMessageDialog(
                    null,
                    "You won the game!",
                    "CONGRATULATIONS °˖✧◝(⁰▿⁰)◜✧˖°",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        else if ( status == GameStatus.LOST){
            JOptionPane.showMessageDialog(
                    null,
                    "You lost :(",
                    "GOOD LUCK ON NEXT ROUND へ[ •́ ‸ •̀ ]ʋ",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (status != GameStatus.IN_PROGRESS){
            round += 1;
            int input = JOptionPane.showConfirmDialog(null,
                    "Do you want to continue playing?",
                    "Next round? ٩(◕‿◕)۶",
                    JOptionPane.YES_NO_OPTION);
            if (input == 1){
                System.exit(0);
            }
            gc.reset();
            updateBoard();
        }
    }

    public void move(JButton button){
        for (int index = 0; index < size; ++index){
            if (button == leftButton){
                gc.recurseLeft(index);
            }
            else if (button == rightButton){
                gc.recurseRight(index);
            }
            else if (button == upButton){
                gc.recurseUp(index);
            }
            else if (button == downButton){
                gc.recurseDown(index);
            }
        }
        updateBoard();
        checkStatus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==leftButton){
            move(leftButton);
        }
        if (e.getSource()==rightButton){
            move(rightButton);
        }
        if (e.getSource()==upButton){
            move(upButton);
        }
        if (e.getSource()==downButton){
            move(downButton);
        }

        if (e.getSource() == reset){
            int input = JOptionPane.showConfirmDialog(null,
                    "Do you want to reset the game?",
                    "Reset ｢(ﾟﾍﾟ) <",
                    JOptionPane.YES_NO_OPTION);
            if (input == 0){
                gc.reset();
                updateBoard();
            }
        }
        if (e.getSource() == quit){
            int input = JOptionPane.showConfirmDialog(null,
                    "Do you want to quit?",
                    "Quit ｡･ﾟﾟ*(>д <)*ﾟﾟ･｡",
                    JOptionPane.YES_NO_OPTION);
            if (input == 0){
                System.exit(0);
            }
        }
        if (e.getSource() == resize){
            text2048.setSize();
            size = text2048.size;
            gc = new GameController(size, winningVal);
            board = gc.board;

            gamePanel.setLayout(new GridLayout(size,size));
            updateBoard();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            move(leftButton);
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            move(rightButton);
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            move(upButton);
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            move(downButton);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
