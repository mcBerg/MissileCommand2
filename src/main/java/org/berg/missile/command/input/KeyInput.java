package org.berg.missile.command.input;

import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static org.berg.missile.command.Settings.*;
import static org.berg.missile.command.objects.GameObjectFactory.spawnNextBase;

public class KeyInput extends KeyAdapter {

  private Handler handler;

  public KeyInput(Handler handler) {
    this.handler = handler;
  }

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();

    if (key == KeyEvent.VK_ENTER) {
      spawnNextBase(handler);
    }

    if (key == KeyEvent.VK_Q) {
      System.exit(0);
    }
    if (key == KeyEvent.VK_SPACE) {
      setLightningSpeed(getLightningSpeed() - 1);
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
    if (key == KeyEvent.VK_COMMA) {
      setLaserLevel(getLaserLevel() + 1);
    }
    if (key == KeyEvent.VK_PERIOD) {
      setLaserLevel(getLaserLevel() - 1);
    }
  }

  public void keyReleased(KeyEvent r) {
    for (GameObject obj : handler.getObject()) {
      if (obj.getId() == ID.Player) {}
    }
  }
}
