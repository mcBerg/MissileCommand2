package game.objects;

import java.awt.*;
import java.util.ArrayList;

import static game.Settings.*;

public class Lightning extends GameObject {

  ArrayList<Point> points;
  GameObject player;
  GameObject enemy;

  public Lightning(GameObject player, GameObject enemy, Handler handler) {
    super(player.getX(), player.getY(), ID.Weapon, handler);
    this.player = player;
    this.enemy = enemy;
    Point start = new Point();
    start.setLocation(player.getX(), player.getY());
    points = new ArrayList<>();
    points.add(start);
    this.handler = handler;
  }

  @Override
  public void tick() {

    if (points.size() > 0) {
      enemy = handler.getClosestEnemy(points.get(points.size() - 1));
      if (enemy == null) {
        handler.removeObject(this);
      } else {
        addNextPoint();
      }
    } else {
      handler.removeObject(this);
      enemy.setAlive(false);
      player.addPoints(enemy.points);
    }
    if (points.size() > 100) {
      handler.removeObject(this);
      points.clear();
      points = null;
      handler = null;
    }
  }

  private void addNextPoint() {
    Point lastPoint = points.get(points.size() - 1);
    int newX = lastPoint.x;
    int newY = lastPoint.y;
    int bound =
        Math.min(
            getDistance(),
            1
                + ((int)
                    Math.floor(
                        Point.distance(
                            lastPoint.getX(), lastPoint.getY(), enemy.getX(), enemy.getY()))));
    int rand = r.nextInt(bound);

    if (enemy.getY() < lastPoint.y && r.nextInt(100) < 75) {
      newY = lastPoint.y - rand;
    } else if (enemy.getY() <= lastPoint.y && r.nextInt(100) > 90) {
      newY = lastPoint.y + rand;
    }

    if (enemy.getY() > lastPoint.y && r.nextInt(100) < 75) {
      newY = lastPoint.y + rand;
    } else if (enemy.getY() >= lastPoint.y && r.nextInt(100) > 90) {
      newY = lastPoint.y - rand;
    }

    if (enemy.getX() > newX && r.nextInt(100) < 75) {
      newX = lastPoint.x + rand;
    } else if (enemy.getX() >= newX && r.nextInt(100) > 90) {
      newX = lastPoint.x - rand;
    }

    if (enemy.getX() < newX && r.nextInt(100) < 75) {
      newX = lastPoint.x - rand;
    } else if (enemy.getX() <= newX && r.nextInt(100) > 90) {
      newX = lastPoint.x + rand;
    }

    Point secondLast = null;
    Point thirdLast = null;
    Point fourthLast = null;

    if (points.size() > 6) {
      secondLast = points.get(points.size() - 2);
      thirdLast = points.get(points.size() - 3);
      fourthLast = points.get(points.size() - 4);
    }
    if (secondLast == null) {
      points.add(new Point(newX, newY));
    } else if (newX != secondLast.x && newY != secondLast.y) {
      if (newX != thirdLast.x && newY != thirdLast.y) {
        if (newX != fourthLast.x && newY != fourthLast.y) {
          points.add(new Point(newX, newY));
        }
      }
    }

    if (r.nextInt(100) > getAim()) {
      int back = r.nextInt(6);
      for (int i = 1; i < points.size() && back > 0; i++) {
        points.add(new Point(points.get(points.size() - i).x, points.get(points.size() - i).y));
        back--;
        i++;
      }
    }
    if (enemy.getX() == newX && enemy.getY() == newY) {
      points.clear();
    }
  }

  @Override
  public void render(Graphics g) {
    var pointIterator = points.iterator();
    Point first = new Point(getX(), getY());
    Point pointNext;
    while (pointIterator.hasNext()) {
      pointNext = pointIterator.next();

      flicker(g, Color.WHITE, Color.BLUE, Color.YELLOW);

      g.drawLine(first.x, first.y, pointNext.x, pointNext.y);
      first = pointNext;
    }
  }

  @Override
  @Deprecated
  public void addPoints(int points) {}
}
