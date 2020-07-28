package org.berg.missile.command.objects.weapon;

import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

public class Nuke extends GameObject {

  private GameObject player;
  private GameObject enemy;

  public Nuke(GameObject player, GameObject enemy, Handler handler) {
    super(player.getX(), player.getY(), ID.Weapon, handler);
    this.player = player;
    this.enemy = enemy;
  }

  @Override
  public void tick() {}

  @Override
  public void render(Graphics g) {}
}
