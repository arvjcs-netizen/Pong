import java.awt.*;
import javax.swing.JFrame;

public class Main extends Canvas {
    
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400; 
    public static int score = 0;
    private Ball ball;
    private Paddle paddle1;
    private Paddle paddle2;
    private boolean[] keysPressed = new boolean[256];

    public Main(){
        ball = new Ball(WIDTH/2, 20, 23);
        paddle1 = new Paddle(20, 20, 15, 1);
        paddle2 = new Paddle(560, 20, 15, 2);
    }
    @Override
    public boolean keyDown(Event evt, int key) {
        keysPressed[key] = true;
        return super.keyDown(evt, key);
    }
    
    @Override
    public boolean keyUp(Event evt, int key) {
        keysPressed[key] = false;
        return super.keyUp(evt, key);
    }

    public void paint (Graphics g){
        ball.drawBall(g);
        ball.update();
        
        // Handle paddle1 movement
        if ((ball.getX() + ball.getSize()) < 300) {
            if (keysPressed[115]) {  // 's'
                paddle1.move(false);
            } else if (keysPressed[119]) {  // 'w'
                paddle1.move(true);
            } else {
                paddle1.setVelocity(0);
            }
        }
        
        // Handle paddle2 movement
        if ((ball.getX() + ball.getSize()) >= 300) {
            if (keysPressed[1004]) {  // up arrow
                paddle2.move(true);
            } else if (keysPressed[1005]) {  // down arrow
                paddle2.move(false);
            } else {
                paddle2.setVelocity(0);
            }
        }
        
        paddle1.drawPaddle(g);
        paddle1.update(ball);
        paddle1.changeVelocity(ball);
        paddle2.drawPaddle(g);
        paddle2.update(ball);
        paddle2.changeVelocity(ball);
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
            // game.repaint will call the paint method again
            game.repaint();

            try {
                Thread.sleep(16);
            } catch (Exception e){}
        }
    }
}

