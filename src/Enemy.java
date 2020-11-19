import java.awt.*;

public class Enemy {


    private double y;
    private final int x;
    private final int width,height;

    public int getWidth() {
        return width;
    }

    public Enemy(int x, double y){
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 100;
    }

    public void tick(){

    }
    public void render(Graphics graph){
        graph.setColor(Color.white);
        graph.fillRect((int)x,(int)y,width,height);
    }
}
