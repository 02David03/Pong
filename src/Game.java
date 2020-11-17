import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable {

    private final int width = 260;
    private final int height = 160;
    private final int scale = 4;

    public Game(){
        Screen();
    }
    public void Screen(){
        this.setPreferredSize(new Dimension(width*scale,height*scale));
        JFrame screen = new JFrame("Pong");
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.add(this);
        screen.pack();
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);
    }

    public static void main(String[] args){
        Game game = new Game();
    }


    @Override
    public void run() {

    }
}
