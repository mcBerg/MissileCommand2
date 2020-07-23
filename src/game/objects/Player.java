package game.objects;

import game.ID;

import java.awt.*;

public class Player extends GameObject {

  public Player(int x, int y, ID id) {
    super(x, y, id);
  }

  @Override
  public void tick() {
    x += velX;
    y += velY;
    if (velX > 0) {
      velX -= 1;
    }
    if (velX < 0) {
      velX += 1;
    }
    if (velY > 0) {
      velY -= 1;
    }
    if (velY < 0) {
      velY += 1;
    }

    if (x < 0) {
      x = 0;
      velX = -velX;
    }
    if (x > 640 - 32) {
      x = 640 - 32;
      velX = -velX;
    }
    if (y < 0) {
      y = 0;
      velY = -velY;
    }
    if (y > 480 - 64) {
      y = 480 - 64;
      velY = -velY;
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(x, y, 32, 32);
  }
}
