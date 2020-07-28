package org.berg.missile.command.objects.player;

import org.berg.missile.command.Settings;
import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;
import org.berg.missile.command.objects.weapon.Missile;

import java.awt.*;

public class MissileBase extends GameObject {

  public MissileBase(int x, int y, Handler handler) {
    super(x, y, ID.Player, handler);
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);
    if (getTicks() > 10000) {
      setTicks(0);
    }
    if (getTicks() % Settings.getMissileSpeed() == 0) {
      getHandler()
          .addObject(
              new Missile(
                  this,
                  getHandler().getClosestEnemy(new Point(this.getX(), this.getY())),
                  getHandler()));
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.BLUE);
    g.drawPolygon(
        new int[] {getX() + 16, getX(), getX() + 32},
        new int[] {getY(), getY() + 32, getY() + 32},
        3);
    g.setColor(Color.white);
    String text =
        String.format("Speed: %s Fuel: %s", Settings.getMissileSpeed(), Settings.getMissileFuel());
    g.drawChars(text.toCharArray(), 0, text.toCharArray().length, getX() + 32, getY() - 32);
  }

  @Override
  public void addPoints(int points) {
    this.setPoints(getPoints() + points);
  }
}
