package org.berg.missile.command.objects.player;

import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

public class RailGunBase extends GameObject {
  public RailGunBase(int x, int y, Handler handler) {
    super(x, y, ID.Player, handler);
  }

  @Override
  public void tick() {}

  @Override
  public void render(Graphics g) {
    g.setColor(Color.YELLOW);
    g.fillRect(getX(), getY(), 32, 32);
  }
}
