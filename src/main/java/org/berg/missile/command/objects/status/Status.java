package org.berg.missile.command.objects.status;

import org.berg.missile.command.Settings;
import org.berg.missile.command.objects.GameObject;
import org.berg.missile.command.objects.Handler;
import org.berg.missile.command.objects.ID;

import java.awt.*;

public class Status extends GameObject {

  private boolean show = true;
  private String points = "";
  private String humansRescued = "";
  private String humansKilled = "";
  private String ufosKilled = "";
  private String mothershipsKilled = "";
  private String portalsKilled = "";
  private String ConstructionShipsKilled = "";

  private String lightningRate = "";
  private String lightningDistance = "";
  private String lightningAccuracy = "";

  private String missileRate = "";
  private String missileFuel = "";

  private String laserLevel = "";

  public Status(Handler handler) {
    super(Settings.WIDTH - Settings.WIDTH / 10, Settings.HEIGHT / 10, ID.Dead, handler);
  }

  @Override
  public void tick() {
    setTicks(getTicks() + 1);
    points = String.format("Points: %s", getHandler().sumPoints());

    if (getTicks() > 10000) {
      setTicks(0);
    }
  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.gray);
    g.drawRect(getX(), getY(), Settings.WIDTH / 100 * 9, Settings.HEIGHT / 5 * 4);
    g.setColor(new Color(125, 125, 0));
    g.drawString(points, getX() + 25, getY() + 25);
  }
}
