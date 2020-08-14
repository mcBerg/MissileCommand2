package org.berg.missile.command.objects.enemy;

import lombok.Getter;
import lombok.Setter;
import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

import static org.berg.missile.command.Settings.*;

@Getter
@Setter
public class Alien extends GameObject {

  public Alien(int x, int y, Handler handler) {
    super(x, y, ID.Enemy, handler);
    setPoints(1);
  }

  @Override
  public void tick() {
    countTicks();
    changeDirection(100, 5, 2);

    if (getTicks() % 100 == 0) {
      setVelX(r.nextInt(11) - 5);
      setVelY(r.nextInt(5) - 2);
    }
    move();
    bounceOffEdges(0, WIDTH, ALIEN_MAX_HEIGHT, HEIGHT);
    die();
    resetTicks(100);
  }

  @Override
  public void render(Graphics g) {

    g.setColor(Color.green);
    if (!isAlive()) {
      flicker(g);
    }
    g.fillRect(getX() - 5, getY(), 10, 10);
    g.drawLine(getX(), getY(), getX(), getY() + 20);
    g.drawLine(getX(), getY() + 20, getX() + 5, getY() + 25);
    g.drawLine(getX(), getY() + 20, getX() - 5, getY() + 25);
    g.drawLine(getX() - 5, getY() + 15, getX() + 5, getY() + 15);
  }
}
