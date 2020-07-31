package org.berg.missile.command.objects;

import lombok.Getter;
import org.berg.missile.command.objects.enemy.*;
import org.berg.missile.command.objects.player.Human;
import org.berg.missile.command.objects.player.LightningBase;

import java.awt.*;
import java.util.LinkedList;

@Getter
public class Handler {

  private LinkedList<GameObject> object = new LinkedList<>();

  public void tick() {
    for (int i = 0; i < object.size(); i++) {
      object.get(i).tick();
    }
  }

  public void render(Graphics g) {
    for (int i = 0; i < object.size(); i++) {
      object.get(i).render(g);
    }
  }

  public void addObject(GameObject object) {
    this.object.add(object);
  }

  public void removeObject(GameObject object) {
    this.object.remove(object);
  }

  public GameObject getClosestEnemy(Point point) {
    return object.stream()
        .filter(enemy -> enemy.getId() == ID.Enemy)
        .min(
            (o1, o2) -> {
              Point p1 = new Point(o1.getX(), o1.getY());
              Point p2 = new Point(o2.getX(), o2.getY());
              return (int) point.distance(p1) - (int) point.distance(p2);
            })
        .orElse(null);
  }

  public int getConstructionShipCount() {
    int count = 0;
    for (int i = 0; i < object.size(); i++) {
      if ((object.get(i).getId() == ID.Enemy)) {
        if (object.get(i) instanceof ConstructionShip) {
          count++;
        }
      }
    }
    return count;
  }

  public int getPortalCount() {
    int count = 0;
    for (int i = 0; i < object.size(); i++) {
      if ((object.get(i).getId() == ID.Enemy)) {
        if (object.get(i) instanceof Portal) {
          count++;
        }
      }
    }
    return count;
  }

  public int getMothershipCount() {
    int count = 0;
    for (int i = 0; i < object.size(); i++) {
      if ((object.get(i).getId() == ID.Enemy)) {
        if (object.get(i) instanceof Mothership) {
          count++;
        }
      }
    }
    return count;
  }

  public int getUfoCount() {
    int count = 0;
    for (int i = 0; i < object.size(); i++) {
      if ((object.get(i).getId() == ID.Enemy)) {
        if (object.get(i) instanceof Ufo) {
          count++;
        }
      }
    }
    return count;
  }

  public int getAlienCount() {
    int count = 0;
    for (int i = 0; i < object.size(); i++) {
      if ((object.get(i).getId() == ID.Enemy)) {
        if (object.get(i) instanceof Alien) {
          count++;
        }
      }
    }
    return count;
  }

  public GameObject getClosestLowerEnemy(Point point) {
    return object.stream()
        .filter(enemy -> enemy.getId() == ID.Enemy)
        .filter(enemy -> enemy.getY() >= point.y)
        .min(
            (o1, o2) -> {
              Point p1 = new Point(o1.getX(), o1.getY());
              Point p2 = new Point(o2.getX(), o2.getY());
              return (int) point.distance(p1) - (int) point.distance(p2);
            })
        .orElse(null);
  }

  public Object sumPoints() {
    return object.stream()
        .filter(player -> player.getId() == ID.Player)
        .mapToInt(player -> player.getPoints())
        .sum();
  }

  public GameObject getClosestBase(Point point) {
    return object.stream()
        .filter(player -> player.getId() == ID.Player)
        .filter(player -> player instanceof LightningBase)
        .min(
            (o1, o2) -> {
              Point p1 = new Point(o1.getX(), o1.getY());
              Point p2 = new Point(o2.getX(), o2.getY());
              return (int) point.distance(p1) - (int) point.distance(p2);
            })
        .orElse(null);
  }

  public int getHumanCount() {
    int count = 0;
    for (int i = 0; i < object.size(); i++) {
      if ((object.get(i).getId() == ID.Player)) {
        if (object.get(i) instanceof Human) {
          count++;
        }
      }
    }
    return count;
  }

  public int getBaseCount() {
    int count = 0;
    for (int i = 0; i < object.size(); i++) {
      if ((object.get(i).getId() == ID.Player)) {
        if (!(object.get(i) instanceof Human)) {
          count++;
        }
      }
    }
    return count;
  }
}
