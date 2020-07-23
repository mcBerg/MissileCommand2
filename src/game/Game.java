package game;

import game.input.KeyInput;
import game.input.MouseInput;
import game.objects.Enemy;
import game.objects.Handler;
import game.objects.Player;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {


  public static final int WIDTH = 1080;
  public static final int HEIGHT = (WIDTH / 12) * 9;
  private Thread thread;
  private boolean running = false;
  private Random r;
  private Handler handler;

  public static void main(String[] args) {
    new Game();
  }

  public Game() {
    handler = new Handler();
    this.addKeyListener(new KeyInput(handler));
    this.addMouseListener(new MouseInput(handler));

    new Window(WIDTH, HEIGHT, "Missile Command", this);

    r = new Random(System.nanoTime());

    handler.addObject(new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player));
    handler.addObject(new Enemy(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Enemy));
  }

  public synchronized void start() {
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public synchronized void stop() {
    try {
      thread.join();
      running = false;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while (running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        tick();
        delta--;
      }
      if (running) {
        render();
      }
      frames++;

      if (System.currentTimeMillis() - timer > 1000) {
        timer += 1000;
        System.out.println("FPS: " + frames);
        System.out.printf("Player: %s,%s", handler.getPlayer().getX(), handler.getPlayer().getY());
        frames = 0;
      }
    }
    stop();
  }

  private void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    Graphics g = bs.getDrawGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, WIDTH, HEIGHT);

    handler.render(g);

    g.dispose();
    bs.show();
  }

  private void tick() {
    handler.tick();
  }
}
