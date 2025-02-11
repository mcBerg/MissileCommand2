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
    setVelX(r.nextBoolean() ? 1 : -1);
    setVelY(r.nextBoolean() ? 1 : -1);
    this.setPoints(1000);
    setTicks(r.nextInt(250));
  }

  @Override
  public void tick() {
    countTicks();
    stopMovement(500);
    changeDirection(520, 2, 1, false);
    if (getTicks() % 510 == 0) {
      GameObjectFactory.spawnMothership(
          getX(), MOTHERSHIP_MAX_HEIGHT + r.nextInt(HEIGHT / 10), getHandler());
    }

    move();
    bounceOffEdges(0, WIDTH, PORTAL_MAX_HEIGHT, PORTAL_MAX_HEIGHT + HEIGHT / 10);
    die();
    resetTicks(550);
  }

  @Override
  public void render(Graphics g) {
    g.setColor(new Color(155, 0, 0));
    g.drawOval(getX(), getY() - 64, 128, 64);
    g.setColor(new Color(30, 0, 30));
    if (getVelX() == 0) {
      flicker(g, new Color(30, 0, 30), new Color(50, 0, 50), new Color(75, 0, 75));
    }
    g.fillOval(getX() + 2, getY() - 62, 124, 62);
  }
}
