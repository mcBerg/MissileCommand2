package game.objects;

import game.Settings;

import java.awt.*;

public abstract class GameObject {

  protected int x, y;
  protected ID id;
  protected int velX, velY;
  protected Handler handler;
  protected int points = 0;
  protected boolean alive = true;
  protected int HP = 15;
  protected int ticks = 0;

  public GameObject(int x, int y, ID id, Handler handler) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.handler = handler;
  }

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

  public abstract void tick();

  public abstract void render(Graphics g);

  public abstract void addPoints(int points);

  public void move() {
    x += velX;
    y += velY;
  }

  public void die() {
    if (!alive) {
      velX = 0;
      HP -= 1;
      id = ID.Dead;
      if (HP == 0) {
        handler.removeObject(this);
      }
    }
  }

  public GameObject didCollide() {
    for (int i = 0; i < handler.object.size(); i++) {
      if (Math.abs(x - handler.object.get(i).getX()) < 16
          && Math.abs(y - handler.object.get(i).getY()) < 16) {
        if (handler.object.get(i).getId() == ID.Enemy) {
          return handler.object.get(i);
        }
      }
    }
    return null;
  }

  public void bounceOffEdges() {
    if (x < 0) {
      x = 0;
      velX = -velX;
    }
    if (x > Settings.WIDTH - 32) {
      x = Settings.WIDTH - 32;
      velX = -velX;
    }

    if (y < 0) {
      y = 0;
      velY = -velY;
    }
    if (y > Settings.HEIGHT - 32) {
      y = Settings.HEIGHT - 32;
      velY = -velY;
    }
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public ID getId() {
    return id;
  }

  public void setId(ID id) {
    this.id = id;
  }

  public int getVelX() {
    return velX;
  }

  public void setVelX(int velX) {
    this.velX = velX;
  }

  public int getVelY() {
    return velY;
  }

  public void setVelY(int velY) {
    this.velY = velY;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }
}
