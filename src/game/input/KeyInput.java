package game.input;

import game.objects.GameObject;
import game.objects.Handler;
import game.objects.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static game.Settings.*;
import static game.objects.GameObjectFactory.spawnLightningBase;
import static game.objects.GameObjectFactory.spawnMissileBase;

public class KeyInput extends KeyAdapter {

  private Handler handler;

  public KeyInput(Handler handler) {
    this.handler = handler;
  }

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_ENTER) {
      spawnLightningBase(handler);
    }

    if (key == KeyEvent.VK_Q) {
      System.exit(0);
    }
    if (key == KeyEvent.VK_SPACE) {
      spawnMissileBase(handler);
    }
    if (key == KeyEvent.VK_UP) {
      setAim(getAim() + 1);
    }
    if (key == KeyEvent.VK_DOWN) {
      setAim(getAim() - 1);
    }
    if (key == KeyEvent.VK_RIGHT) {
      setDistance(getDistance() + 1);
    }
    if (key == KeyEvent.VK_LEFT) {
      setDistance(getDistance() - 1);
    }
  }

  public void keyReleased(KeyEvent r) {
    for (GameObject obj : handler.object) {
      if (obj.getId() == ID.Player) {}
    }
  }
}
