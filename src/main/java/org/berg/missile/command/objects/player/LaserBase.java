package org.berg.missile.command.objects.player;

import org.berg.missile.command.Settings;
import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;
import org.berg.missile.command.objects.weapon.Laser;

import java.awt.*;

public class LaserBase extends GameObject {
  public LaserBase(int x, int y, Handler handler) {
    super(x, y, ID.Player, handler);
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);
    if (getTicks() > 10000) {
      setTicks(0);
    }
    if (getTicks() % Settings.getLaserTime() == 0) {
      getHandler()
          .addObject(
              new Laser(
                  this,
                  getHandler().getClosestEnemy(new Point(this.getX(), this.getY())),
                  getHandler()));
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.YELLOW);
    g.fillRect(getX(), getY(), 32, 32);
  }
}
