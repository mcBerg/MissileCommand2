package org.berg.missile.command.objects.weapon;

import org.berg.missile.command.Settings;
import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

public class Missile extends GameObject {

  private GameObject player;
  private GameObject enemy;

  public Missile(GameObject player, GameObject enemy, Handler handler) {
    super(player.getX() + 16, player.getY(), ID.Weapon, handler);
    this.player = player;
    this.enemy = enemy;
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);

    if (getTicks() < Settings.getMissileFuel() / 4) {
      setVelY(0);
    }
    if (getTicks() > Settings.getMissileFuel() / 4 && isAlive()) {
      setVelY(-(getTicks() / 25) * 4);
    }
    setVelX(0);
    move();
    enemy = didCollide();

    if (getTicks() > Settings.getMissileFuel()) {
      setAlive(false);
    }
    if (enemy != null) {
      setAlive(false);
      enemy.setAlive(false);
      player.addPoints(enemy.getPoints());
    }
    if (!isAlive()) {
      setId(ID.Dead);
      setVelY(0);
      setHP(getHP() - 1);
    }
    if (getHP() < 0) {
      getHandler().removeObject(this);
    }
  }

  @Override
  public void render(Graphics g) {
    if (isAlive()) {
      g.setColor(Color.ORANGE);
      g.drawLine(getX(), getY(), getX(), getY() + 8);
      flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
      g.drawLine(getX(), getY() + 8, getX() + 4, getY() + 12);
      flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
      g.drawLine(getX(), getY() + 8, getX() - 4, getY() + 12);
      flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
      g.drawLine(getX(), getY() + 8, getX() + Settings.r.nextInt(9) - 4, getY() + 12);
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

  @Override
  public void addPoints(int points) {}
}
