import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {

    private final int width = 260;
    private final int height = 160;
    private final int scale = 4;

    public Player player;

    public BufferedImage layer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);

    public Game(){
        this.setPreferredSize(new Dimension(width*scale,height*scale));
        player = new Player();
        this.Screen();
    }
    public void Screen(){
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
        new Thread(game).start();
    }
    public void tick(){

    }
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graph = layer.getGraphics();
        graph.setColor(Color.black);
        graph = bs.getDrawGraphics();
        graph.drawImage(layer,0,0,width*scale,height*scale,null);
        player.render(graph);
        bs.show();
    }
    @Override
    public void run() {
        while (true){
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
