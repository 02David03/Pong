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

    public static Player player;
    public static Enemy enemy;
    public static Ball ball;

    public BufferedImage layer = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);


    public static void main(String[] args){
        Game game = new Game();
        new Thread(game).start();
    }

    public Game(){
        this.screen();
        this.addKeyListener(this);
        player = new Player(15, height*scale/2 - 70);
        enemy = new Enemy(width*scale - 45, (double)height*scale/2 - 70);
        ball = new Ball(((double)(width*scale)/2) - 30,(double)height*scale/2 -30 );
    }

    public void screen(){
        this.setPreferredSize(new Dimension(width*scale,height*scale));
        JFrame screen = new JFrame("Pong");
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.add(this);
        screen.pack();
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);
    }

    public void colision(){
        Rectangle boundsBall = new Rectangle((int) (ball.getX()+(ball.getDx()*ball.getSpeed())), (int) (ball.getY()+(ball.getDy() * ball.getSpeed())), ball.getWidth(),ball.getHeight());
        Rectangle boundsPlayer = new Rectangle(player.getX(), player.getY(), player.getWidth(), player.getHeight());
        Rectangle boundsEnemy = new Rectangle(enemy.getX(),(int) enemy.getY(), enemy.getWidth(), enemy.getHeight());

        if(boundsBall.intersects(boundsPlayer)) ball.setDx(ball.getDx() * -1);
        else if (boundsBall.intersects(boundsEnemy)) ball.setDx(ball.getDx() * -1);
    }
////
    public void tick(){
        player.tick();
        enemy.tick();
        ball.tick();
        //This is responsible in make the player, the enemy and the ball don't get away from the screen
        if (player.getY() + player.getHeight() > this.height*scale) player.setY(this.height*scale - player.getHeight());
        else if (player.getY() < 0) player.setY(0);
        if ( ball.getY() + ball.getHeight() > this.height*scale) ball.setDy(ball.getDy() * -1);
        else if( ball.getY() < 0) ball.setDy(ball.getDy() * -1);
        if (enemy.getY() + enemy.getHeight() > this.height*scale) enemy.setY(this.height*scale - enemy.getHeight());
        else if (enemy.getY() < 0) enemy.setY(0);
        //Aplication of the colision oh the enemy and the player
        this.colision();
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graph = layer.getGraphics();
        //Black BackGround render
        graph.setColor(Color.black);
        graph = bs.getDrawGraphics();
        graph.drawImage(layer,0,0,width*scale,height*scale,null);
        //Player render
        player.render(graph);
        //Enemy render
        enemy.render(graph);
        //Ball render
        ball.render(graph);
        //show Graphics
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
