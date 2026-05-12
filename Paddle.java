import java.awt.*;
public class Paddle{
    private int x;
    private int y; 
    private int size;
    private int speed;
    private int player;
    private int velocity;
    public Paddle(int x, int y, int size, int player){
        this.x = x;
        this.y = y;
        this.size = size;
        speed = 10;
        this.player = player;
        velocity = 0;
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getSize(){
        return size;
    }
    public void setSize(int size){
        this.size = size; 
    }
    public void drawPaddle(Graphics g){
        g.fillRect(x ,y, size, 5*size);
    }
    public void setVelocity(int velocity){
        this.velocity =  velocity;
    }
    public void move(boolean up){
        if(up){
            y-= speed;
            y = Math.max(y, 0);
            velocity = -speed;

        }else{
            y+= speed;
            y = Math.min(352 -  5*size, y);
            velocity  = speed;
        }
    }
    public void update(Ball ball){
        if (player  == 1){
            if((ball.getX()<= x + size)&&(ball.getX() > x)&&(ball.getY() + ball.getSize() > y)&&(ball.getY()<y + 5*size) &&  (ball.getXVelocity() < 0)){
            ball.bounceX();
            Main.score += 1;
        }
        }if (player  ==  2){
            if((ball.getX()>= x - size )&&(ball.getX() < x + size)&&(ball.getY() + ball.getSize() > y)&&(ball.getY()<y + 5*size) &&  (ball.getXVelocity() > 0)){
            ball.bounceX();
            Main.score += 1;
        }
        }
    }
    public void changeVelocity(Ball ball){
        if (player  == 1){
            if((ball.getX()<= x + size)&&(ball.getX() > x)&&(ball.getY() + ball.getSize() > y)&&(ball.getY()<y + 5*size) &&  (ball.getXVelocity() < 0)){
            ball.setYVelocity(ball.getYVelocity() + velocity/3  - 1);
        }
        }if (player  ==  2){
            if((ball.getX()>= x - size )&&(ball.getX() < x + size)&&(ball.getY() + ball.getSize() > y)&&(ball.getY()<y + 5*size) &&  (ball.getXVelocity() > 0)){
            ball.setYVelocity(ball.getYVelocity() + velocity/3 -1);
        }
        }
    }
}