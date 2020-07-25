package game.objects;

import java.awt.*;

import static game.Settings.getMissileFuel;
import static game.Settings.getMissileSpeed;

public class MissileBase extends GameObject {

  public MissileBase(int x, int y, Handler handler) {
    super(x, y, ID.Player, handler);
  }

  @Override
  public void tick() {
    ticks++;
    if (ticks > 10000) {
      ticks = 0;
    }
    if (ticks % getMissileSpeed() == 0) {
      handler.addObject(
          new Missile(this, handler.getClosestEnemy(new Point(this.getX(), this.getY())), handler));
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.BLUE);
    g.drawPolygon(new int[] {x + 16, x, x + 32}, new int[] {y, y + 32, y + 32}, 3);
    g.setColor(Color.white);
    String text = String.format("Speed: %s Fuel: %s", getMissileSpeed(), getMissileFuel());
    g.drawChars(text.toCharArray(), 0, text.toCharArray().length, x + 32, y - 32);
  }

  @Override
  public void addPoints(int points) {
    this.points += points;
  }
}
