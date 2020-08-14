package org.berg.missile.command.objects;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import static org.berg.missile.command.Settings.r;

@Getter
@Setter
public abstract class GameObject {

  private int x;
  private int y;
  private ID id;
  private int velX;
  private int velY;
  private int accelX;
  private int accelY;
  private int cardinal = 5;
  private Handler handler;
  private int points = 0;
  private boolean alive = true;
  private int hp = 15;
  private int ticks = 0;

  public GameObject(int x, int y, ID id, Handler handler) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.handler = handler;
  }

  public abstract void tick();

  protected void changeDirection(int timing, int maxVelX, int maxVelY, boolean zeroAllowed) {
    if (ticks % timing == 0) {
      changeDirection(timing, maxVelX, maxVelY);
      if (!zeroAllowed) {
        while (velX == 0 && velY == 0) {
          changeDirection(timing, maxVelX, maxVelY);
        }
      }
    }
  }

  protected void changeDirection(int timing, int maxVelX, int maxVelY) {
    if (ticks % timing == 0) {
      setVelX(r.nextInt(maxVelX * 2 + 1) - maxVelX);
      setVelY(r.nextInt(maxVelY * 2 + 1) - maxVelY);
    }
  }

  public void stopMovement(int timing) {
    if (getTicks() % timing == 0) {
      setVelX(0);
      setVelY(0);
    }
  }

  public abstract void render(Graphics g);

  public void countTicks() {
    ticks++;
    if (ticks > 100000) {
      ticks = 0;
    }
  }

  public void resetTicks(int timing) {
    if (ticks > timing) {
      ticks = 0;
    }
  }

  public void flicker(Graphics g, Color... colors) {
    int limit = 5;
    if (colors.length > 0) {
      limit = colors.length;
    }
    switch (r.nextInt(limit)) {
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

  public void accelerate() {
    setVelX(getVelX() + getAccelX());
    setVelY(getVelY() + getAccelY());
  }

  public void accelerateToward(GameObject goal) {

    int distX = getX() - goal.getX();
    int distY = getY() - goal.getY();

    // Cardinal Directions:
    // 1 2 3
    // 4 5 6
    // 7 8 9

    cardinal = 5;
    setAccelX(0);
    setAccelY(0);
    if (distX > 0) {
      goLeft(distX, distY);
      return;
    }
    if (distX < 0) {
      goRight(distX, distY);
    }
  }

  private void goRight(int distX, int distY) {
    // must go right (2, 3, 6, 8, 9)
    if (distY > 0) {
      goUpRightBoth(distX, distY);

    } else if (distY < 0) {
      doDownRightBoth(distX, distY);
    }
  }

  private void goLeft(int distX, int distY) {
    // must go left (2, 1, 4, 7, 8)
    if (distY > 0) {
      goUpLeftBoth(distX, distY);

    } else if (distY < 0) {
      goDownLeftBoth(distX, distY);
    }
  }

  private void doDownRightBoth(int distX, int distY) {
    // go right down (6, 8, 9)
    if (distX / distY >= 2) {
      // straight right
      setAccelX(1);
      cardinal = 6;
    } else if (distY / distX >= 2) {
      // straight down
      setAccelY(1);
      cardinal = 8;
    } else {
      // down right
      setAccelX(1);
      setAccelY(1);
      cardinal = 9;
    }
  }

  private void goUpRightBoth(int distX, int distY) {
    // must go up (2, 3, 6)
    if (distX / distY <= -2) {
      // go straight right
      setAccelX(1);
      cardinal = 6;
    } else if (distY / distX <= -2) {
      // go straight up
      setAccelY(-1);
      cardinal = 2;
    } else {
      // go right/up
      setAccelX(1);
      setAccelY(-1);
      cardinal = 3;
    }
  }

  private void goDownLeftBoth(int distX, int distY) {
    // must go down (4, 7, 8)
    if (distX / distY <= -2) {
      // straight left
      setAccelX(-1);
      cardinal = 4;
    } else if (distY / distX <= -2) {
      // straight down
      setAccelY(1);
      cardinal = 8;
    } else {
      // down left
      setAccelX(-1);
      setAccelY(1);
      cardinal = 7;
    }
  }

  private void goUpLeftBoth(int distX, int distY) {
    // must go up (4, 1, 2)
    if (distX / distY >= 2) {
      // go straight left
      setAccelX(-1);
      cardinal = 4;
    } else if (distY / distX >= 2) {
      // go straight up
      setAccelY(-1);
      cardinal = 2;
    } else {
      // go left/up
      setAccelX(-1);
      setAccelY(-1);
      cardinal = 1;
    }
  }

  public void moveToward(GameObject goal) {
    if (Math.abs(getX() - goal.getX()) < getVelX()) {
      setVelX(1);
    }

    if (Math.abs(getY() - goal.getY()) < getVelY()) {
      setVelY(1);
    }

    if (getX() < goal.getX()) {
      setVelX(Math.abs(getVelX()));
      cardinal = 7;
    }
    if (getX() > goal.getX()) {
      setVelX(-Math.abs(getVelX()));
      cardinal = 1;
    }
    if (getX() == goal.getX()) {
      setVelX(0);
      cardinal = 4;
    }
    if (getY() < goal.getY()) {
      setVelY(Math.abs(getVelY()));
      cardinal += 2;
    }
    if (getY() > goal.getY()) {
      setVelY(-Math.abs(getVelY()));
    }
    if (getY() == goal.getY()) {
      setVelY(0);
      cardinal++;
    }
  }

  public void die() {
    if (!isAlive()) {
      setVelX(0);
      setVelY(0);
      setHp(getHp() - 1);
      setId(ID.Dead);
      if (getHp() <= 0) {
        getHandler().removeObject(this);
      }
    }
  }

  public GameObject didCollide() {
    for (int i = 0; i < getHandler().getObject().size(); i++) {
      if (Math.abs(getX() - getHandler().getObject().get(i).getX()) < 16
          && Math.abs(getY() - getHandler().getObject().get(i).getY()) < 16
          && getHandler().getObject().get(i).getId() == ID.Enemy) {
        return getHandler().getObject().get(i);
      }
    }
    return null;
  }

  public void bounceOffEdges(int minX, int maxX, int minY, int maxY) {
    if (getX() < minX) {
      setX(minX);
      setVelX(-getVelX());
    }
    if (getX() > maxX) {
      setX(maxX);
      setVelX(-getVelX());
    }

    if (getY() < minY) {
      setY(minY);
      setVelY(-getVelY());
    }
    if (getY() > maxY) {
      setY(maxY);
      setVelY(-getVelY());
    }
  }
}
