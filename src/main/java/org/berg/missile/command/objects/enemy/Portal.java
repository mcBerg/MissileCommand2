package org.berg.missile.command.objects.enemy;

import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.GameObjectFactory;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

import static org.berg.missile.command.Settings.*;

public class Portal extends GameObject {
  public Portal(int x, int y, Handler handler) {
    super(x, y, ID.Enemy, handler);
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);
    if (getTicks() > 10000) {
      setTicks(0);
    }
    if (getTicks() % 500 == 0) {
      GameObjectFactory.spawnMothership(
          getX(), MOTHERSHIP_MAX_HEIGHT + r.nextInt(HEIGHT / 10), getHandler());
    }
    move();
    bounceOffEdges();
    die();
  }

  @Override
  public void render(Graphics g) {
    g.setColor(new Color(155, 0, 0));
    g.drawOval(getX(), getY() - 64, 128, 64);
    g.setColor(new Color(30, 0, 30));
    g.fillOval(getX() + 2, getY() - 62, 124, 62);
  }
}
