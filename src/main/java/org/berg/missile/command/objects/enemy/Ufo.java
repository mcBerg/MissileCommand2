package org.berg.missile.command.objects.enemy;

import org.berg.missile.command.Settings;
import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.GameObjectFactory;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

public class Ufo extends GameObject {

  public Ufo(int x, int y, Handler handler) {
    super(x, y, ID.Enemy, handler);
    setVelX(1 + Settings.r.nextInt(10));
    setTicks(Settings.r.nextInt(1000));
    setPoints(10);
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);
    if (getTicks() == 10000) {
      setTicks(0);
    }
    if (getTicks() % Settings.ALIEN_SPAWN_SPEED == 50) {
      setVelX(Settings.r.nextInt(30) - 15);
      setVelY(Settings.r.nextInt(5) - 2);
    }
    if (getTicks() % Settings.ALIEN_SPAWN_SPEED < 50) {
      setVelX(0);
      setVelY(0);
    }

    move();
    bounceOffEdges();
    if (getY() < Settings.UFO_MAX_HEIGHT) {
      setY(Settings.UFO_MAX_HEIGHT);
      setVelY(-getVelY());
    }
    if (getY() > Settings.UFO_MAX_HEIGHT + Settings.HEIGHT / 10) {
      setY(Settings.UFO_MAX_HEIGHT + Settings.HEIGHT / 10);
      setVelY(-getVelY());
    }
    die();

    if (isAlive() && getTicks() % Settings.ALIEN_SPAWN_SPEED == 50) {
      GameObjectFactory.spawnAlien(
          getX() + 16,
          Settings.ALIEN_MAX_HEIGHT + Settings.r.nextInt(Settings.HEIGHT / 10),
          getHandler());
    }
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
      if (getTicks() % Settings.ALIEN_SPAWN_SPEED > 40
          && getTicks() % Settings.ALIEN_SPAWN_SPEED < 50) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(getX() + 14, getY() + 16, getX() + 14 - 6, Settings.HEIGHT);
        g.drawLine(getX() + 18, getY() + 16, getX() + 18 + 6, Settings.HEIGHT);
      }
      if (getTicks() % Settings.ALIEN_SPAWN_SPEED > 30
          && getTicks() % Settings.ALIEN_SPAWN_SPEED < 40) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(
            getX() + 14,
            getY() + 16,
            getX() + 14 - 5,
            getY() + ((Settings.HEIGHT - getY()) / 4) * 3);
        g.drawLine(
            getX() + 18,
            getY() + 16,
            getX() + 18 + 5,
            getY() + ((Settings.HEIGHT - getY()) / 4) * 3);
      }
      if (getTicks() % Settings.ALIEN_SPAWN_SPEED > 20
          && getTicks() % Settings.ALIEN_SPAWN_SPEED < 30) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(
            getX() + 14,
            getY() + 16,
            getX() + 14 - 4,
            getY() + ((Settings.HEIGHT - getY()) / 4) * 2);
        g.drawLine(
            getX() + 18,
            getY() + 16,
            getX() + 18 + 4,
            getY() + ((Settings.HEIGHT - getY()) / 4) * 2);
      }
      if (getTicks() % Settings.ALIEN_SPAWN_SPEED > 10
          && getTicks() % Settings.ALIEN_SPAWN_SPEED < 20) {
        g.setColor(new Color(115, 115, 255));
        g.drawLine(
            getX() + 14,
            getY() + 16,
            getX() + 14 - 2,
            getY() + ((Settings.HEIGHT - getY()) / 4) * 1);
        g.drawLine(
            getX() + 18,
            getY() + 16,
            getX() + 18 + 2,
            getY() + ((Settings.HEIGHT - getY()) / 4) * 1);
      }
    }
  }

  @Override
  public void addPoints(int points) {}
}
