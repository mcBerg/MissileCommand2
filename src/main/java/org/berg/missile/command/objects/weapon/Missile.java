package org.berg.missile.command.objects.weapon;

import org.berg.missile.command.Settings;
import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

public class Missile extends GameObject {

  private final GameObject player;
  private GameObject enemy;

  public Missile(GameObject player, GameObject enemy, Handler handler) {
    super(player.getX() + 16, player.getY(), ID.Weapon, handler);
    this.player = player;
    this.enemy = enemy;
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);

    if (getTicks() < Settings.getMissileFuel() / 4 && isAlive()) {
      setVelY(-5);
    }

    if (getTicks() > Settings.getMissileFuel() / 4 && enemy == null) {
      enemy = getHandler().getClosestEnemy(new Point(getX(), getY()));
    }

    if (getTicks() > Settings.getMissileFuel() / 4 && isAlive() && enemy != null) {
      accelerateToward(enemy);
    }

    if (isAlive()) {
      accelerate();
      move();
    }
    if (getVelX() > 10) {
      setVelX(10);
    }
    if (getVelX() < -10) {
      setVelX(-10);
    }
    if (getVelY() > 10) {
      setVelY(10);
    }
    if (getVelY() < -10) {
      setVelY(-10);
    }

    GameObject collide = didCollide();

    if (getTicks() > Settings.getMissileFuel()) {
      setAlive(false);
    }

    if (collide != null) {
      setAlive(false);
      collide.setAlive(false);
      player.addPoints(collide.getPoints());
    }
    if (!isAlive()) {
      setId(ID.Dead);
      setVelY(0);
      setVelX(0);
      setHp(getHp() - 1);
    }
    if (getHp() < 0) {
      getHandler().removeObject(this);
    }
  }

  @Override
  public void render(Graphics g) {
    if (isAlive()) {
      g.setColor(Color.ORANGE);

      // Cardinal Directions
      // 1 2 3
      // 4 5 6
      // 7 8 9

      // 0 x inf
      // y
      // inf
      switch (getCardinal()) {
        case 1:
          g.drawLine(getX(), getY(), getX() - 8, getY() - 8);

          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() + 4, getY() + 2);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() + 2, getY() + 4);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() + 4, getY() + 4);

          break;
        case 5:
        case 2:
          g.drawLine(getX(), getY(), getX(), getY() - 8);

          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() + 2, getY() + 4);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() - 2, getY() + 4);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX(), getY() + 4);

          break;
        case 3:
          g.drawLine(getX(), getY(), getX() + 8, getY() - 8);

          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() - 4, getY() + 2);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() - 2, getY() + 4);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() - 4, getY() + 4);

          break;
        case 4:
          g.drawLine(getX(), getY(), getX() - 8, getY());

          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() + 4, getY() + 2);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() + 4, getY() - 2);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() + 4, getY());

          break;

        case 6:
          g.drawLine(getX(), getY(), getX() + 8, getY());

          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() - 4, getY() + 2);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() - 4, getY() - 2);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() - 4, getY());

          break;
        case 7:
          g.drawLine(getX(), getY(), getX() - 8, getY() + 8);

          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() + 4, getY() - 2);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() + 2, getY() - 4);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() + 4, getY() - 4);
          break;
        case 8:
          g.drawLine(getX(), getY(), getX(), getY() + 8);

          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() - 2, getY() - 4);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() + 2, getY() - 4);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX(), getY() - 4);
          break;
        case 9:
          g.drawLine(getX(), getY(), getX() + 8, getY() + 8);

          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() - 4, getY() - 2);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() - 2, getY() - 4);
          flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
          g.drawLine(getX(), getY(), getX() - 4, getY() - 4);
          break;
        default:
      }
    }
    if (!isAlive()) {
      for (int i = 0; i < 10; i++) {
        flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
        g.drawLine(
            getX(),
            getY(),
            getX() + Settings.r.nextInt(17) - 8,
            getY() + Settings.r.nextInt(17) - 8);
      }
    }
  }
}
