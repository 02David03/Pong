import java.awt.*;

public class Player {

    private boolean up,down;
    private int width, height;
    private final int x;
    private int y;
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 100;
    }
    public int getHeight(){
        return height;
    }

    public int getY(){
        return y;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void tick(){
        if (up) y-=5.5;
        else if (down) y+=5.5;
    }
    public void render(Graphics graph){
        graph.setColor(Color.white);
        graph.fillRect(x,y,width,height);
    }
}
