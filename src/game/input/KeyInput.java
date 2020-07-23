package game.input;

import game.ID;
import game.objects.GameObject;
import game.objects.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

  private Handler handler;

  public KeyInput(Handler handler) {
    this.handler = handler;
  }

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();

    for (GameObject obj : handler.object) {
      if (obj.getId() == ID.Player) {
        // key events
        if (key == KeyEvent.VK_W) {
          obj.setVelY(obj.getVelY() - 5);
        }
        if (key == KeyEvent.VK_S) {
          obj.setVelY(obj.getVelY() + 5);
        }
        if (key == KeyEvent.VK_D) {
          obj.setVelX(obj.getVelX() + 5);
        }
        if (key == KeyEvent.VK_A) {
          obj.setVelX(obj.getVelX() - 5);
        }
      }
    }
  }

  public void keyReleased(KeyEvent r) {
    for (GameObject obj : handler.object) {
      if (obj.getId() == ID.Player) {}
    }
  }
}
