package game.objects;

import java.awt.*;

import static game.Settings.*;

public class LightningBase extends GameObject {

  public LightningBase(int x, int y, Handler handler) {
    super(x, y, ID.Player, handler);
    this.y = HEIGHT - 32;
  }

  @Override
  public void tick() {
    ticks++;
    if (ticks % 25 == 0) {
      handler.addObject(
          new Lightning(
              this, handler.getClosestEnemy(new Point(this.getX(), this.getY())), handler));
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(x, y, 32, 32);
    String text = String.format("Accuracy: %s Distance: %s", getAim(), getDistance());
    String text2 = String.format(" %s ", points);
    g.setColor(Color.red);
    g.setFont(Font.getFont(Font.SERIF));
    g.drawChars(text.toCharArray(), 0, text.toCharArray().length, x, y);
    g.setColor(new Color(50, 100, 50));
    g.drawChars(text2.toCharArray(), 0, text2.toCharArray().length, x + 5, y + 15);
  }

  @Override
  public void addPoints(int points) {
    this.points += points;
  }
}
