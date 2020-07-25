package game.objects;

import java.awt.*;

import static game.Settings.*;

public class EnemyL1 extends GameObject {

  public EnemyL1(int x, int y, Handler handler) {
    super(x, y, ID.Enemy, handler);
    this.y = HEIGHT - y % L1_MAX_HEIGHT;
    points = 1;
  }

  @Override
  public void tick() {
    ticks++;
    if (ticks % 100 == 0) {
      velX = r.nextInt(10) - 5;
    }
    move();
    bounceOffEdges();
    die();
  }

  @Override
  public void render(Graphics g) {

    g.setColor(Color.green);
    if (!alive) {
      flicker(g);
    }
    g.fillRect(x - 5, y, 10, 10);
    g.drawLine(x, y, x, y + 20);
    g.drawLine(x, y + 20, x + 5, y + 25);
    g.drawLine(x, y + 20, x - 5, y + 25);
    g.drawLine(x - 5, y + 15, x + 5, y + 15);
  }

  @Override
  @Deprecated
  public void addPoints(int points) {}
}
