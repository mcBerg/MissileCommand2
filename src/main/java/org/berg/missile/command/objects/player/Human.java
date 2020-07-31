package org.berg.missile.command.objects.player;

import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

import static org.berg.missile.command.Settings.*;

public class Human extends GameObject {

  private GameObject base;
  private GameObject enemy;
  private Color color;

  public Human(int x, int y, Handler handler) {
    super(x, y, ID.Player, handler);
    color = new Color(r.nextInt(100), r.nextInt(100), r.nextInt(100));
    setVelX(1 + r.nextInt(4));
    setVelY(1 + r.nextInt(4));
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);
    base = getHandler().getClosestBase(new Point(this.getX(), this.getY()));
    moveToward(base);
    if (getX() == base.getX() && getY() == base.getY()) {
      base.addPoints(1);
      getHandler().removeObject(this);
      setHp(0);
    }
    if (isAlive()) {
      enemy = didCollide();
      if (enemy != null && enemy.isAlive()) {
        setHp(getHp() - 5);
        color = new Color(color.getRed() + 50, color.getGreen(), color.getBlue());
        if (getHp() <= 0) {
          setAlive(false);
        }
      }
      move();
    }
    die();

    bounceOffEdges(0, WIDTH, ALIEN_MAX_HEIGHT, HEIGHT);
  }

  @Override
  public void render(Graphics g) {
    g.setColor(color);
    if (!isAlive()) {
      flicker(g, Color.RED, Color.PINK, color);
    }
    g.fillRect(getX() - 5, getY(), 10, 10);
    g.drawLine(getX(), getY(), getX(), getY() + 20);
    g.drawLine(getX(), getY() + 20, getX() + 5, getY() + 25);
    g.drawLine(getX(), getY() + 20, getX() - 5, getY() + 25);
    g.drawLine(getX() - 5, getY() + 15, getX() + 5, getY() + 15);
  }
}
