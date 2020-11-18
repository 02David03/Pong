import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {

    public final int width = 260;
    public final int height = 160;
    private final int scale = 4;

    public Player player;

    public BufferedImage layer = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

    public static void main(String[] args){
        Game game = new Game();
        new Thread(game).start();
    }

    public Game(){
        this.Screen();
        this.addKeyListener(this);
        player = new Player(15, height*scale/2 - 90);
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

    public void tick(){
        player.tick();
        if (player.getY() + player.getHeight() > this.height*scale) player.setY(this.height*scale - player.getHeight());
        else if (player.getY() < 0) player.setY(0);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> player.setUp(true);
            case KeyEvent.VK_DOWN -> player.setDown(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> player.setUp(false);
            case KeyEvent.VK_DOWN -> player.setDown(false);
        }
    }
}
