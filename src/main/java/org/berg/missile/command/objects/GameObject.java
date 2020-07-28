package org.berg.missile.command.objects;

import lombok.Getter;
import lombok.Setter;
import org.berg.missile.command.Settings;

import java.awt.*;

@Getter
@Setter
public abstract class GameObject {

  private int x, y;
  private ID id;
  private int velX, velY;
  private Handler handler;
  private int points = 0;
  private boolean alive = true;
  private int HP = 15;
  private int ticks = 0;

  public GameObject(int x, int y, ID id, Handler handler) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.handler = handler;
  }

  public abstract void tick();

  public abstract void render(Graphics g);

  public void flicker(Graphics g, Color... colors) {
    int limit = 5;
    if (colors.length > 0) {
      limit = colors.length;
    }
    switch (Settings.r.nextInt(limit)) {
      case 0:
        g.setColor(Color.white);
        if (colors.length > 0) {
          g.setColor(colors[0]);
        }
        break;
      case 1:
        g.setColor(Color.YELLOW);
        if (colors.length > 1) {
          g.setColor(colors[1]);
        }
        break;
      case 2:
        g.setColor(Color.RED);
        if (colors.length > 2) {
          g.setColor(colors[2]);
        }
        break;
      case 3:
        g.setColor(Color.BLUE);
        if (colors.length > 3) {
          g.setColor(colors[3]);
        }
        break;
      case 4:
        g.setColor(Color.ORANGE);
        if (colors.length > 4) {
          g.setColor(colors[4]);
        }
        break;
      default:
        g.setColor(Color.green);
    }
  }

  public void addPoints(int points) {
    setPoints(getPoints() + points);
  }

  public void move() {
    setX(getX() + getVelX());
    setY(getY() + getVelY());
  }

  public void die() {
    if (!isAlive()) {
      setVelX(0);
      setVelY(0);
      setHP(getHP() - 1);
      setId(ID.Dead);
      if (getHP() <= 0) {
        getHandler().removeObject(this);
      }
    }
  }

  public GameObject didCollide() {
    for (int i = 0; i < getHandler().object.size(); i++) {
      if (Math.abs(getX() - getHandler().object.get(i).getX()) < 16
          && Math.abs(getY() - getHandler().object.get(i).getY()) < 16) {
        if (getHandler().object.get(i).getId() == ID.Enemy) {
          return getHandler().object.get(i);
        }
      }
    }
    return null;
  }

  public void bounceOffEdges() {
    if (getX() < 0) {
      setX(0);
      setVelX(-getVelX());
    }
    if (getX() > Settings.WIDTH - 32) {
      setX(Settings.WIDTH - 32);
      setVelX(-getVelX());
    }

    if (getY() < 0) {
      setY(0);
      setVelY(-getVelY());
    }
    if (getY() > Settings.HEIGHT - 32) {
      setY(Settings.HEIGHT - 32);
      setVelY(-getVelY());
    }
  }
}
