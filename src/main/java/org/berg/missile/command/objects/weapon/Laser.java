package org.berg.missile.command.objects.weapon;

import lombok.Getter;
import lombok.Setter;
import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

import static org.berg.missile.command.Settings.*;

@Setter
@Getter
public class Laser extends GameObject {

  private GameObject player;
  private GameObject enemy;

  public Laser(GameObject player, GameObject enemy, Handler handler) {
    super(player.getX(), player.getY(), ID.Weapon, handler);
    this.player = player;
    this.enemy = enemy;
    setX(r.nextInt(WIDTH));
    setY(HEIGHT - HEIGHT / 6 * getLaserLevel());
    setVelX(r.nextBoolean() ? 1 : -1);
    setVelY(r.nextBoolean() ? 1 : -1);
  }

  @Override
  public void tick() {
    if (getTicks() == 0) {
      enemy = getHandler().getClosestLowerEnemy(new Point(getX(), getY()));
    }
    setTicks(getTicks() + 1);
    if (getTicks() > 25) {
      setHp(0);
      setAlive(false);
    }
    if (getTicks() == 20 && enemy != null) {
      player.addPoints(enemy.getPoints());
      enemy.setAlive(false);
    }
    die();
  }

  @Override
  public void render(Graphics g) {
    int laserLevel = getLaserLevel();
    if (laserLevel == 1) {
      g.setColor(Color.RED);
    }
    if (laserLevel == 2) {
      g.setColor(Color.GREEN);
    }
    if (laserLevel == 3) {
      g.setColor(Color.BLUE);
    }
    if (laserLevel == 4) {
      g.setColor(new Color(150, 0, 255));
    }

    for (int i = 0; i < laserLevel; i++) {
      if (laserLevel == 5) {
        flicker(g, Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE);
      }
      if (enemy != null) {
        g.drawLine(enemy.getX() + i, enemy.getY(), player.getX() + i, player.getY());
      }
    }
  }
}
