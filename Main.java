import java.awt.*;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends Canvas implements KeyListener{
    
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400; 
    public static int p1Score = 0;
    public static int p2Score = 0;
    private Ball ball;
    private Paddle paddle1;
    private Paddle paddle2;

    private boolean p1UpPressed = false;
    private boolean p1DownPressed = false;
    private boolean p2UpPressed = false;
    private boolean p2DownPressed = false;

    public Main(){
        ball = new Ball(WIDTH/2, 20, 23);
        paddle1 = new Paddle(20, 20, 15, 1);
        paddle2 = new Paddle(560, 20, 15, 2);
        this.addKeyListener(this);
        this.setFocusable(true);
    }
    public void updateInput() {
        // Player 1 controls (W and S)
        if (p1UpPressed) {
            paddle1.move(true);
            paddle1.changeVelocity(ball);
        }
        if (p1DownPressed) {
            paddle1.move(false);
            paddle1.changeVelocity(ball);
        }

        // Player 2 controls (Up and Down Arrows)
        if (p2UpPressed) {
            paddle2.move(true);
            paddle2.changeVelocity(ball);
        }
        if (p2DownPressed) {
            paddle2.move(false);
            paddle2.changeVelocity(ball);
        }
    }

    // 5. Turn flags ON when pressed
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) p1UpPressed = true;
        if (key == KeyEvent.VK_S) p1DownPressed = true;
        if (key == KeyEvent.VK_UP) p2UpPressed = true;
        if (key == KeyEvent.VK_DOWN) p2DownPressed = true;
    }

    // 6. Turn flags OFF when released
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) p1UpPressed = false;
        if (key == KeyEvent.VK_S) p1DownPressed = false;
        if (key == KeyEvent.VK_UP) p2UpPressed = false;
        if (key == KeyEvent.VK_DOWN) p2DownPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but required by KeyListener interface
    }
    public void drawZero(Graphics g, int x, int y){
        g.setColor(Color.BLACK);
        g.fillRect(x,y, 25, 40);
        g.setColor(Color.WHITE);
        g.fillRect(x+5, y+5, 15, 30);
    }
    public void drawOne(Graphics g, int x, int y){
        g.setColor(Color.BLACK);
        g.fillRect(x,y, 8, 40);
    }
    public void drawTwo(Graphics g, int x, int y){
        g.setColor(Color.BLACK);
        g.fillRect(x,y, 25, 40);
        g.setColor(Color.WHITE);
        g.fillRect(x, y + 4, 20, 14);
        g.fillRect(x + 5, y +22, 20, 14);
    }
    public void drawThree(Graphics g, int x, int y){
        g.setColor(Color.BLACK);
        g.fillRect(x,y, 25, 40);
        g.setColor(Color.WHITE);
        g.fillRect(x, y + 4, 20, 14);
        g.fillRect(x, y +22, 20, 14);
    }
    public void paint (Graphics g){
        int toUpdate;
        ball.drawBall(g);
        toUpdate = ball.update();
        if(toUpdate == 1){
            p2Score ++;
            paddle1.reset();
            paddle2.reset();
        }else if(toUpdate == -1){
            p1Score ++;
            paddle1.reset();
            paddle2.reset();
        }
        paddle1.drawPaddle(g);
        paddle1.update(ball);
        paddle2.drawPaddle(g);
        paddle2.update(ball);
        g.fillRect(298,0, 4, 30);
        g.fillRect(298,40, 4, 60);
        g.fillRect(298,110, 4, 60);
        g.fillRect(298, 180, 4, 60);
        g.fillRect(298, 250, 4, 60);
        g.fillRect(298,  320, 4, 40);
        Color darkBlue = new Color(19, 8, 64);
        g.setColor(darkBlue);
        g.fillRect(0,0, 1000,7);
        g.fillRect(0,346, 1000, 7);
        g.fillRect(0,0, 7, 1000);
        g.fillRect(593, 0, 7, 1000);
        //x is 65 for left and 510 for right. 
        switch(p1Score) {
            case 0:
                drawZero(g, 65, 20);
                break;
            case 1:
                drawOne(g, 65, 20);
                break;
            case 2:
                drawTwo(g, 65, 20);
                break;
            case 3:
                drawThree(g, 65, 20);
                break;
            default:
                System.out.println("Game over \nLeft player has won the game with 4 points!");
                System.exit(0);
        }
        switch(p2Score){
            case 0:
                drawZero(g, 510, 20);
                break;
            case 1:
                drawOne(g, 510, 20);
                break;
            case 2:
                drawTwo(g, 510, 20);
                break;
            case 3:
                drawThree(g, 510, 20);
                break;
            default:
                System.out.println("Game Over \nRight player has won the game with 4 points!");
                System.exit(0);

            }
        

    }

    public static void main(String[] args) {
        // create a new window
        JFrame frame = new JFrame("Pong");
        // create a game instance
        Main game = new Main();
        // add the game to the window
        frame.add(game);
        // set the window ti visible
        frame.setVisible(true);
        // set the size of the game
        frame.setSize(WIDTH, HEIGHT);
        // make the game exit on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("Visual output!");
        // forever
        while (true)
        {
            game.updateInput();
            // game.repaint will call the paint method again
            game.repaint();

            try {
                Thread.sleep(16);
            } catch (Exception e){}
        }
    }
}
