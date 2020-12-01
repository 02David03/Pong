import java.awt.*;

public class Enemy {


    private double y;
    private final int x;
    private final int width,height;

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Enemy(int x, double y){
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 100;
    }

    public void tick(){
        y += Game.ball.getY() - y - 6;
    }

    public void render(Graphics graph){
        graph.setColor(Color.white);
        graph.fillRect((int)x,(int)y,width,height);
    }
}
