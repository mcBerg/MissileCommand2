package org.berg.missile.command.objects.enemy;

import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.GameObjectFactory;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

import static org.berg.missile.command.Settings.*;

public class Mothership extends GameObject {

  public Mothership(int x, int y, Handler handler) {
    super(x, y, ID.Enemy, handler);
    setVelX(1 + r.nextInt(11) - 5);
    setTicks(r.nextInt(1000));
    setPoints(100);
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);
    if (getTicks() > 10000) {
      setTicks(0);
    }
    if (getTicks() % 500 == 0) {
      GameObjectFactory.spawnUfo(getX(), UFO_MAX_HEIGHT + r.nextInt(HEIGHT / 10), getHandler());
    }
    move();
    bounceOffEdges();
    die();
  }

  @Override
  public void render(Graphics g) {
    g.setColor(new Color(50, 50, 50));
    g.fillPolygon(
        new Polygon(
            new int[] {getX(), getX() - 6, getX() + 128},
            new int[] {getY(), getY() + 32, getY() + 16},
            3));
  }
}
