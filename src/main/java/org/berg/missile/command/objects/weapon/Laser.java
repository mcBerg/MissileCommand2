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
    setY(r.nextInt(HEIGHT));
    setVelX(r.nextInt(WIDTH / 25) - WIDTH / 50);
    setVelY(r.nextInt(HEIGHT / 25) - HEIGHT / 50);
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);
    if (getTicks() > 25) {
      setHP(0);
      setAlive(false);
    }
    die();
    move();
    bounceOffEdges();
    if (getY() > ALIEN_MAX_HEIGHT) {
      setY(ALIEN_MAX_HEIGHT);
      setVelY(-getVelY());
    }
    if (getY() < ((HEIGHT - PORTAL_MAX_HEIGHT) / getLaserLevel())) {
      setY((HEIGHT - PORTAL_MAX_HEIGHT) / getLaserLevel());
      setVelY(-getVelY());
    }
    enemy = didCollide();
    if (enemy != null) {
      addPoints(enemy.getPoints());
      enemy.setAlive(false);
    }
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
      g.drawLine(getX() + i, getY(), player.getX() + i, player.getY());
    }
  }
}
