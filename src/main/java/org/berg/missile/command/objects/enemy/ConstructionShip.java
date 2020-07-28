package org.berg.missile.command.objects.enemy;

import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.GameObjectFactory;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

import static org.berg.missile.command.Settings.*;

public class ConstructionShip extends GameObject {

  public ConstructionShip(int x, int y, Handler handler) {
    super(x, y, ID.Enemy, handler);
    setVelX(1);
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);
    if (getTicks() > 10000) {
      setTicks(0);
    }
    if (getTicks() % 500 == 0) {
      GameObjectFactory.spawnPortal(
          getX(), PORTAL_MAX_HEIGHT + r.nextInt(HEIGHT / 10), getHandler());
    }

    move();
    bounceOffEdges();
    die();
  }

  @Override
  public void render(Graphics g) {
    g.setColor(new Color(175, 190, 175));
    if (!isAlive()) {
      flicker(g);
    }
    g.fillOval(getX() - 32, getY() - 32, 64, 64);
  }
}
