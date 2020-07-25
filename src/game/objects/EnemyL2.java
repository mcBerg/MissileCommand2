package game.objects;

import java.awt.*;

import static game.Settings.*;

public class EnemyL2 extends GameObject {

  public EnemyL2(int x, int y, Handler handler) {
    super(x, y, ID.Enemy, handler);
    velX = 1 + r.nextInt(10);
    ticks = r.nextInt(1000);
    points = 10;
  }

  @Override
  public void tick() {
    ticks++;
    if (ticks == 10000) {
      ticks = 0;
    }
    if (ticks % L2_SPAWN_SPEED == 50) {
      velX = r.nextInt(30) - 15;
    }
    if (ticks % L2_SPAWN_SPEED < 50) {
      velX = 0;
    }

    move();
    bounceOffEdges();
    die();
    if (HP == 0) {
      GameObjectFactory.spawnEnemyL2(handler);
    }
    if (alive && ticks % L2_SPAWN_SPEED == 50) {
      GameObjectFactory.spawnEnemyL1At(x + 16, HEIGHT - r.nextInt(L1_MAX_HEIGHT), handler);
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(new Color(255, 100, 255));
    if (!alive) {
      flicker(g);
    }
    g.fillOval(x, y, 32, 16);

    g.setColor(Color.white);
    if (!alive) {
      flicker(g, Color.RED, Color.orange, Color.YELLOW);
    }
    int offset = 4;
    if (ticks % 10 < 5) {
      offset = 0;
    }
    g.fillOval(x + 0 + offset, y + 6, 4, 4);
    g.fillOval(x + 12 + offset, y + 6, 4, 4);
    g.fillOval(x + 24 + offset, y + 6, 4, 4);

    if (alive) {
      if (ticks % L2_SPAWN_SPEED > 40 && ticks % L2_SPAWN_SPEED < 50) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(x + 14, y + 16, x + 14 - 6, HEIGHT);
        g.drawLine(x + 18, y + 16, x + 18 + 6, HEIGHT);
      }
      if (ticks % L2_SPAWN_SPEED > 30 && ticks % L2_SPAWN_SPEED < 40) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(x + 14, y + 16, x + 14 - 5, y + ((HEIGHT - y) / 4) * 3);
        g.drawLine(x + 18, y + 16, x + 18 + 5, y + ((HEIGHT - y) / 4) * 3);
      }
      if (ticks % L2_SPAWN_SPEED > 20 && ticks % L2_SPAWN_SPEED < 30) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(x + 14, y + 16, x + 14 - 4, y + ((HEIGHT - y) / 4) * 2);
        g.drawLine(x + 18, y + 16, x + 18 + 4, y + ((HEIGHT - y) / 4) * 2);
      }
      if (ticks % L2_SPAWN_SPEED > 10 && ticks % L2_SPAWN_SPEED < 20) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(x + 14, y + 16, x + 14 - 2, y + ((HEIGHT - y) / 4) * 1);
        g.drawLine(x + 18, y + 16, x + 18 + 2, y + ((HEIGHT - y) / 4) * 1);
      }
    }
  }

  @Override
  public void addPoints(int points) {}
}
