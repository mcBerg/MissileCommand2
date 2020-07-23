package game.objects;

import game.ID;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

  public LinkedList<GameObject> object = new LinkedList<>();

  public void tick() {
    for (GameObject obj : object) {
      obj.tick();
    }
  }

  public void render(Graphics g) {
    for (GameObject obj : object) {
      obj.render(g);
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
}
