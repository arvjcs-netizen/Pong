import java.awt.*;
public class Ball{
    private int x;
    private int y;
    private int size;
    private int xSpeed;
    private int ySpeed;
    private int xInit;
    private int yInit;
    private static final int DEFAULTXSPEED = 10;
    private static final int DEFAULTYSPEED = 4;
    public Ball(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.xInit =x;
        this.yInit = y; 
        this.size = size;
        xSpeed = DEFAULTXSPEED;
        ySpeed = DEFAULTYSPEED;
        if (Math.random() < 0.5){
            xSpeed *= -1;
        }
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
        g.setColor(Color.WHITE);
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
    public int update(){
        int winner = 0; 
        if(ySpeed > 0){
            ySpeed = Math.min(7, ySpeed);
        }else{
            ySpeed = Math.max(-7, ySpeed);
        }
        if(xSpeed>0){
            xSpeed = (int) Math.max(3, Math.pow(130 - Math.pow(ySpeed, 2), 0.5));
            
        }else{
            xSpeed = - ((int) Math.max(3, Math.pow(130 - Math.pow(ySpeed, 2), 0.5)));
        }
        if(y<=1 || y>350- size){
            bounceY();
        }
        if (x <0  || x>600- size){
            if (x<0){
                winner = 1;
            }else{
                winner = -1;
            }
            reset();

            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
            }


        }
        return winner;
    }
    public void reset(){
      x = xInit;
      y = yInit; 
      xSpeed = DEFAULTXSPEED;
      ySpeed = DEFAULTYSPEED;
      if (Math.random() < (0.5)){
        xSpeed *= -1;
      }

      
    }
}
