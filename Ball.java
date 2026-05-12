import java.awt.*;
public class Ball{
    private int x;
    private int y;
    private int size;
    private int xSpeed;
    private int ySpeed;
    private int xInit;
    private int yInit;
    public Ball(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.xInit =x;
        this.yInit = y; 
        this.size = size;
        xSpeed = 8;
        ySpeed = 2;
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

    public int getXVelocity(){
        return xSpeed;
    }
    public int getYVelocity(){
        return ySpeed;
    }
    public void setYVelocity(int velocity){
        ySpeed = velocity;
    }
    public void drawBall(Graphics g){
        x += xSpeed;
        y += ySpeed;
        g.fillOval(x,y, size, size);

    }
    public void bounceX(){
        xSpeed = -xSpeed;
    }
    public void bounceY(){
        ySpeed = -ySpeed;
    }
    public void update(){
        if(ySpeed > 0){
            ySpeed = Math.min(7, ySpeed);
        }else{
            ySpeed = Math.max(-7, ySpeed);
        }
        if(xSpeed>0){
            xSpeed = (int) Math.max(3, Math.pow(64 - Math.pow(ySpeed, 2), 0.5));
            
        }else{
            xSpeed = - ((int) Math.max(3, Math.pow(64 - Math.pow(ySpeed, 2), 0.5)));
        }
        if(y<=1 || y>350- size){
            bounceY();
        }
        if (x <0  || x>600){
            reset();
        }
    }
    public void reset(){
      x = xInit;
      y = yInit; 
      System.out.println("Game Over. \nYour score was " + Main.score + "!");
      Main.score = 0;
    }
}