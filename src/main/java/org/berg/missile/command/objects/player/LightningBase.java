package org.berg.missile.command.objects.player;

import org.berg.missile.command.Settings;
import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;
import org.berg.missile.command.objects.weapon.Lightning;

import java.awt.*;

import static org.berg.missile.command.Settings.*;

public class LightningBase extends GameObject {

  public LightningBase(int x, int y, Handler handler) {
    super(x, y, ID.Player, handler);
    this.setY(HEIGHT - 32);
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);
    if (getTicks() > 10000) {
      setTicks(0);
    }
    if (getTicks() % Settings.getLightningSpeed() == 0) {
      getHandler()
          .addObject(
              new Lightning(
                  this,
                  getHandler().getClosestEnemy(new Point(this.getX(), this.getY())),
                  getHandler()));
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(getX(), getY(), 32, 32);
    String text = String.format("Accuracy: %s Distance: %s", getAim(), getDistance());
    String text2 = String.format(" %s ", getPoints());
    g.setColor(Color.red);
    g.setFont(Font.getFont(Font.SERIF));
    g.drawChars(text.toCharArray(), 0, text.toCharArray().length, getX(), getY());
    g.setColor(new Color(50, 100, 50));
    g.drawChars(text2.toCharArray(), 0, text2.toCharArray().length, getX() + 5, getY() + 15);
  }

  @Override
  public void addPoints(int points) {
    setPoints(getPoints() + points);
  }
}
