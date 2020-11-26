import java.awt.*;
import java.util.Random;

public class Ball {

    private double x, y;
    private final int width,height;

    private double dx,dy;
    private final double speed = 3.7;

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDy() {
        return dy;
    }

    public int getHeight() {
        return height;
    }

    public double getY() { return y; }

    public Ball(double x, double y){
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 30;

        dx = new Random().nextGaussian();
        dy = new Random().nextGaussian();
    }

    public void tick(){
        x+=dx*speed;
        y+=dy*speed;
    }
    public void render(Graphics graph){
        graph.setColor(Color.white);
        graph.fillOval((int)x,(int)y,width,height);
    }
}
