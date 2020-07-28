package org.berg.missile.command.objects.weapon;

import lombok.Getter;
import lombok.Setter;
import org.berg.missile.command.Settings;
import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter
public class Lightning extends GameObject {

  private ArrayList<Point> pointsArray;
  private GameObject player;
  private GameObject enemy;

  public Lightning(GameObject player, GameObject enemy, Handler handler) {
    super(player.getX(), player.getY(), ID.Weapon, handler);
    this.player = player;
    this.enemy = enemy;
    Point start = new Point();
    start.setLocation(player.getX(), player.getY());
    pointsArray = new ArrayList<>();
    pointsArray.add(start);
  }

  @Override
  public void tick() {

    if (pointsArray.size() > 0) {
      enemy = getHandler().getClosestEnemy(pointsArray.get(pointsArray.size() - 1));
      if (enemy == null) {
        getHandler().removeObject(this);
      } else {
        addNextPoint();
      }
    } else {
      getHandler().removeObject(this);
      enemy.setAlive(false);
      player.addPoints(enemy.getPoints());
    }
    if (pointsArray.size() > 150) {
      getHandler().removeObject(this);
      pointsArray.clear();
      pointsArray = null;
      setHandler(null);
    }
  }

  private void addNextPoint() {
    Point lastPoint = pointsArray.get(pointsArray.size() - 1);
    int newX = lastPoint.x;
    int newY = lastPoint.y;
    int bound =
        Math.min(
            Settings.getDistance(),
            1
                + ((int)
                    Math.floor(
                        Point.distance(
                            lastPoint.getX(), lastPoint.getY(), enemy.getX(), enemy.getY()))));

    int rand = Settings.r.nextInt(bound);
    if (enemy.getY() < lastPoint.y && Settings.r.nextInt(100) < 75) {
      newY = lastPoint.y - rand;
    } else if (enemy.getY() < lastPoint.y && Settings.r.nextInt(100) < 10) {
      newY = lastPoint.y + rand;
    }

    rand = Settings.r.nextInt(bound);
    if (enemy.getY() >= lastPoint.y && Settings.r.nextInt(100) < 75) {
      newY = lastPoint.y + rand;
    } else if (enemy.getY() >= lastPoint.y && Settings.r.nextInt(100) < 10) {
      newY = lastPoint.y - rand;
    }

    rand = Settings.r.nextInt(bound);
    if (enemy.getX() > newX && Settings.r.nextInt(100) < 75) {
      newX = lastPoint.x + rand;
    } else if (enemy.getX() > newX && Settings.r.nextInt(100) < 10) {
      newX = lastPoint.x - rand;
    }

    rand = Settings.r.nextInt(bound);
    if (enemy.getX() <= newX && Settings.r.nextInt(100) < 75) {
      newX = lastPoint.x - rand;
    } else if (enemy.getX() <= newX && Settings.r.nextInt(100) < 10) {
      newX = lastPoint.x + rand;
    }

    Point secondLast = null;
    Point thirdLast = null;
    Point fourthLast = null;

    if (pointsArray.size() > 6) {
      secondLast = pointsArray.get(pointsArray.size() - 2);
      thirdLast = pointsArray.get(pointsArray.size() - 3);
      fourthLast = pointsArray.get(pointsArray.size() - 4);
    }
    if (secondLast == null) {
      pointsArray.add(new Point(newX, newY));
    } else if (newX != secondLast.x && newY != secondLast.y) {
      if (newX != thirdLast.x && newY != thirdLast.y) {
        if (newX != fourthLast.x && newY != fourthLast.y) {
          pointsArray.add(new Point(newX, newY));
        }
      }
    }

    if (Settings.r.nextInt(100) > Settings.getAim()) {
      int back = Settings.r.nextInt(6);
      for (int i = 1; i < pointsArray.size() && back > 0; i++) {
        pointsArray.add(
            new Point(
                pointsArray.get(pointsArray.size() - i).x,
                pointsArray.get(pointsArray.size() - i).y));
        back--;
        i++;
      }
    }
    if (enemy.getX() == newX && enemy.getY() == newY) {
      pointsArray.clear();
    }
  }

  @Override
  public void render(Graphics g) {
    var pointIterator = pointsArray.iterator();
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
