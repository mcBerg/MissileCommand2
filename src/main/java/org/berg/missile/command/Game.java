package org.berg.missile.command;

import org.berg.missile.command.input.KeyInput;
import org.berg.missile.command.input.MouseInput;
import org.berg.missile.command.objects.GameObjectFactory;
import org.berg.missile.command.objects.Handler;

import java.awt.*;
import java.awt.image.BufferStrategy;

import static org.berg.missile.command.Settings.r;

public class Game extends Canvas implements Runnable {

  private Thread thread;
  private boolean running = false;
  private Handler handler;
  private Point[] stars;

  public static void main(String[] args) {
    new Game();
  }

  public Game() {
    handler = new Handler();
    this.addKeyListener(new KeyInput(handler));
    this.addMouseListener(new MouseInput(handler));

    stars = new Point[15];
    fillStars();

    new Window(Settings.WIDTH, Settings.HEIGHT, "Missile Command", this);

    GameObjectFactory.spawnConstructionShip(0, 0, handler);
  }

  private void fillStars() {
    for (int i = 0; i < stars.length; i++) {
      stars[i] = new Point(r.nextInt(Settings.WIDTH), r.nextInt(Settings.HEIGHT / 3));
    }
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
        System.out.printf("FPS: %s\n", frames);
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
    g.setColor(new Color(0, 0, 0));
    g.fillRect(0, 0, Settings.WIDTH, Settings.HEIGHT);

    g.setColor(Color.white);
    for (int i = 0; i < 5; i++) {
      g.fillOval(stars[i].x, stars[i].y, 2, 2);
    }

    g.setColor(Color.YELLOW);
    for (int i = 5; i < 10; i++) {
      g.fillOval(stars[i].x, stars[i].y, 2, 2);
    }

    g.setColor(Color.ORANGE);
    for (int i = 10; i < 15; i++) {
      g.fillOval(stars[i].x, stars[i].y, 2, 2);
    }

    g.setColor(new Color(0, 0, 150));
    g.fillOval(
        0 - Settings.WIDTH,
        Settings.HEIGHT - (Settings.HEIGHT / 2),
        Settings.WIDTH * 3,
        Settings.HEIGHT * 2);

    g.setColor(new Color(0, 204, 0));
    g.fillOval(
        0 - Settings.WIDTH / 4,
        Settings.HEIGHT - (Settings.HEIGHT / 5),
        Settings.WIDTH / 2,
        Settings.HEIGHT / 4);

    g.setColor(new Color(0, 153, 0));
    g.fillOval(0, Settings.HEIGHT - (Settings.HEIGHT / 5), Settings.WIDTH * 2, Settings.HEIGHT / 2);

    g.setColor(new Color(0, 102, 0));
    g.fillOval(
        0 - Settings.WIDTH / 4,
        Settings.HEIGHT - (Settings.HEIGHT / 10),
        Settings.WIDTH * 2,
        Settings.HEIGHT / 4);

    g.setColor(new Color(0, 51, 0));
    g.fillOval(
        0 - Settings.WIDTH / 2,
        Settings.HEIGHT - Settings.HEIGHT / 20,
        Settings.WIDTH * 2,
        Settings.HEIGHT / 10);

    handler.render(g);

    g.dispose();
    bs.show();
  }

  private void tick() {
    handler.tick();
  }
}
