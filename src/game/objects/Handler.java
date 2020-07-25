package game.objects;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

  public LinkedList<GameObject> object = new LinkedList<>();

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

  public GameObject getPlayer() {
    for (GameObject obj : object) {
      if (obj.getId() == ID.Player) {
        return obj;
      }
    }
    return null;
  }

  public GameObject getEnemy() {
    for (GameObject obj : object) {
      if (obj.getId() == ID.Enemy) {
        return obj;
      }
    }
    return null;
  }

  public GameObject getClosestEnemy(Point point) {
    return object.stream()
        .filter(enemy -> enemy.getId() == ID.Enemy)
        .min(
            (o1, o2) -> {
              Point p1 = new Point(o1.x, o1.y);
              Point p2 = new Point(o2.x, o2.y);
              return (int) point.distance(p1) - (int) point.distance(p2);
            })
        .orElse(null);
  }

  public int getPlayerCount() {
    int count = 0;
    for (GameObject object : object) {
      if ((object.getId() == ID.Player)) {
        count++;
      }
    }
    return count;
  }
}
