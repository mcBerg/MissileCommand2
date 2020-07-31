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
    setVelX(r.nextInt(11) - 5);
    setVelY(r.nextInt(5) - 2);
    setTicks(r.nextInt(1000));
    setPoints(100);
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);
    if (getTicks() > 10000) {
      setTicks(0);
    }
    if (getTicks() % 550 == 0) {
      GameObjectFactory.spawnUfo(getX(), UFO_MAX_HEIGHT + r.nextInt(HEIGHT / 10), getHandler());
      setVelX(r.nextInt(11) - 5);
      setVelY(r.nextInt(5) - 2);
    }
    if (getTicks() % 500 == 0) {
      setVelX(0);
      setVelY(0);
    }
    move();
    bounceOffEdges(0, WIDTH, MOTHERSHIP_MAX_HEIGHT, MOTHERSHIP_MAX_HEIGHT + HEIGHT / 10);
    die();
  }

  @Override
  public void render(Graphics g) {
    g.setColor(new Color(50, 50, 50));
    g.fillPolygon(
        new Polygon(
            new int[] {getX() - 64, getX() - 72, getX() + 64},
            new int[] {getY() - 16, getY() + 16, getY()},
            3));
  }
}
