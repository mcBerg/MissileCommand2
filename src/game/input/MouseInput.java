package game.input;

import game.objects.Handler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static game.Settings.*;

public class MouseInput implements MouseListener {

  private Handler handler;

  public MouseInput(Handler handler) {
    this.handler = handler;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

    if (e.getButton() == MouseEvent.BUTTON1) {
      setMissileSpeed(getMissileSpeed() + 1);
    }
    if (e.getButton() == MouseEvent.BUTTON2) {
      setMissileFuel(getMissileFuel() + 1);
    }
    if (e.getButton() == MouseEvent.BUTTON3) {
      setMissileSpeed(getMissileSpeed() - 1);
    }
    if (e.getButton() == 4) {
      setMissileFuel(getMissileFuel() - 1);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    int x = e.getXOnScreen();
    int y = e.getYOnScreen();

    System.out.printf("Pressed: %s, %s\n", x, y);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    int x = e.getXOnScreen();
    int y = e.getYOnScreen();

    System.out.printf("Released: %s, %s\n", x, y);
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();

    System.out.printf("Entered: %s, %s\n", x, y);
  }

  @Override
  public void mouseExited(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();

    System.out.printf("Exited: %s, %s\n", x, y);
  }
}
