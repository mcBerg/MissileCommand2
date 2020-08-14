package org.berg.missile.command.objects.enemy;

import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.GameObjectFactory;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

import static org.berg.missile.command.Settings.*;

public class Ufo extends GameObject {

  public Ufo(int x, int y, Handler handler) {
    super(x, y, ID.Enemy, handler);
    setVelX(1 + r.nextInt(10));
    setTicks(r.nextInt(1000));
    setPoints(10);
  }

  @Override
  public void tick() {
    countTicks();
    changeDirection(ALIEN_SPAWN_SPEED, 15, 2);
    stopMovement(ALIEN_SPAWN_SPEED / 2 + 1);
    move();
    bounceOffEdges(0, WIDTH, UFO_MAX_HEIGHT, UFO_MAX_HEIGHT + HEIGHT / 10);
    die();
    if (isAlive() && getTicks() % ALIEN_SPAWN_SPEED == 0) {
      GameObjectFactory.spawnAlien(
          getX() + 16, ALIEN_MAX_HEIGHT + r.nextInt(HEIGHT / 10), getHandler());
    }
    resetTicks(ALIEN_SPAWN_SPEED);
  }

  @Override
  public void render(Graphics g) {
    g.setColor(new Color(255, 100, 255));
    if (!isAlive()) {
      flicker(g);
    }
    g.fillOval(getX(), getY(), 32, 16);

    g.setColor(Color.white);
    if (!isAlive()) {
      flicker(g, Color.RED, Color.orange, Color.YELLOW);
    }
    int offset = 4;
    if (getTicks() % 10 < 5) {
      offset = 0;
    }
    g.fillOval(getX() + 0 + offset, getY() + 6, 4, 4);
    g.fillOval(getX() + 12 + offset, getY() + 6, 4, 4);
    g.fillOval(getX() + 24 + offset, getY() + 6, 4, 4);

    if (isAlive()) {
      if (getTicks() % ALIEN_SPAWN_SPEED > ALIEN_SPAWN_SPEED - ALIEN_SPAWN_SPEED / 10
          && getTicks() % ALIEN_SPAWN_SPEED < ALIEN_SPAWN_SPEED) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(getX() + 14, getY() + 16, getX() + 14 - 6, HEIGHT);
        g.drawLine(getX() + 18, getY() + 16, getX() + 18 + 6, HEIGHT);
      }
      if (getTicks() % ALIEN_SPAWN_SPEED > ALIEN_SPAWN_SPEED - ALIEN_SPAWN_SPEED / 10 * 2
          && getTicks() % ALIEN_SPAWN_SPEED < ALIEN_SPAWN_SPEED - ALIEN_SPAWN_SPEED / 10) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(getX() + 14, getY() + 16, getX() + 14 - 5, getY() + ((HEIGHT - getY()) / 4) * 3);
        g.drawLine(getX() + 18, getY() + 16, getX() + 18 + 5, getY() + ((HEIGHT - getY()) / 4) * 3);
      }
      if (getTicks() % ALIEN_SPAWN_SPEED > ALIEN_SPAWN_SPEED - ALIEN_SPAWN_SPEED / 10 * 3
          && getTicks() % ALIEN_SPAWN_SPEED < ALIEN_SPAWN_SPEED - ALIEN_SPAWN_SPEED / 10 * 2) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(getX() + 14, getY() + 16, getX() + 14 - 4, getY() + ((HEIGHT - getY()) / 4) * 2);
        g.drawLine(getX() + 18, getY() + 16, getX() + 18 + 4, getY() + ((HEIGHT - getY()) / 4) * 2);
      }
      if (getTicks() % ALIEN_SPAWN_SPEED > ALIEN_SPAWN_SPEED - ALIEN_SPAWN_SPEED / 10 * 4
          && getTicks() % ALIEN_SPAWN_SPEED < ALIEN_SPAWN_SPEED - ALIEN_SPAWN_SPEED / 10 * 3) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(getX() + 14, getY() + 16, getX() + 14 - 2, getY() + ((HEIGHT - getY()) / 4) * 1);
        g.drawLine(getX() + 18, getY() + 16, getX() + 18 + 2, getY() + ((HEIGHT - getY()) / 4) * 1);
      }
    }
  }

  @Override
  public void addPoints(int points) {}
}
