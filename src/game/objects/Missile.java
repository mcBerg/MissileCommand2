package game.objects;

import java.awt.*;

import static game.Settings.getMissileFuel;
import static game.Settings.r;

public class Missile extends GameObject {

  GameObject player;
  GameObject enemy;

  public Missile(GameObject player, GameObject enemy, Handler handler) {
    super(player.getX() + 16, player.getY(), ID.Weapon, handler);
    this.player = player;
    this.enemy = enemy;
  }

  @Override
  public void tick() {
    ticks++;

    if (ticks < getMissileFuel() / 4) {
      velY = 0;
    }
    if (ticks > getMissileFuel() / 4 && alive) {
      velY = -(ticks / 25) * 4;
    }
    velX = 0;
    move();
    enemy = didCollide();

    if (ticks > getMissileFuel()) {
      setAlive(false);
    }
    if (enemy != null) {
      setAlive(false);
      enemy.setAlive(false);
      player.addPoints(enemy.points);
    }
    if (!alive) {
      setId(ID.Dead);
      velY = 0;
      HP--;
    }
    if (HP < 0) {
      handler.removeObject(this);
    }
  }

  @Override
  public void render(Graphics g) {
    if (alive) {
      g.setColor(Color.ORANGE);
      g.drawLine(x, y, x, y + 8);
      flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
      g.drawLine(x, y + 8, x + 4, y + 12);
      flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
      g.drawLine(x, y + 8, x - 4, y + 12);
      flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
      g.drawLine(x, y + 8, x + r.nextInt(9) - 4, y + 12);
    }
    if (!alive) {
      for (int i = 0; i < 10; i++) {
        flicker(g, Color.RED, Color.YELLOW, Color.ORANGE);
        g.drawLine(x, y, x + r.nextInt(17) - 8, y + r.nextInt(17) - 8);
      }
    }
  }

  @Override
  public void addPoints(int points) {}
}
