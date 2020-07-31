package org.berg.missile.command.objects;

import org.berg.missile.command.Settings;
import org.berg.missile.command.objects.enemy.*;
import org.berg.missile.command.objects.player.*;

import java.awt.*;

public class GameObjectFactory {

  public static void spawnAlien(int x, int y, Handler handler) {
    if (handler.getAlienCount() <= 50) {
      handler.addObject(new Alien(x, y, handler));
    }
  }

  public static void spawnUfo(int x, int y, Handler handler) {
    if (handler.getUfoCount() <= 10) {
      handler.addObject(new Ufo(x, y, handler));
    }
  }

  public static void spawnMothership(int x, int y, Handler handler) {
    if (handler.getMothershipCount() <= 5) {
      handler.addObject(new Mothership(x, y, handler));
    }
  }

  public static void spawnPortal(int x, int y, Handler handler) {
    if (handler.getPortalCount() <= 3) {
      handler.addObject(new Portal(x, y, handler));
    }
  }

  public static void spawnConstructionShip(int x, int y, Handler handler) {
    if (handler.getConstructionShipCount() <= 2) {
      handler.addObject(new ConstructionShip(x, y, handler));
    }
  }

  public static void spawnNextBase(Handler handler) {
    int count = handler.getBaseCount();
    if (count < 5) {
      spawnLightningBase(handler);
    } else if (count < 10) {
      spawnMissileBase(handler);
    } else if (count < 15) {
      spawnLaserBase(handler);
    } else if (count < 20) {
      spawnRailGunBase(handler);
    } else if (count < 25) {
      spawnNukeBase(handler);
    }
  }

  private static void spawnLightningBase(Handler handler) {
    Point location = getSpawnLocation(handler);
    handler.addObject(new LightningBase(location.x, location.y, handler));
  }

  private static void spawnMissileBase(Handler handler) {
    Point location = getSpawnLocation(handler);
    handler.addObject(new MissileBase(location.x + 32, location.y, handler));
  }

  private static void spawnLaserBase(Handler handler) {
    Point location = getSpawnLocation(handler);
    handler.addObject(new LaserBase(location.x - 32, location.y, handler));
  }

  private static void spawnRailGunBase(Handler handler) {
    Point location = getSpawnLocation(handler);
    handler.addObject(new RailGunBase(location.x + 64, location.y, handler));
  }

  private static void spawnNukeBase(Handler handler) {
    Point location = getSpawnLocation(handler);
    handler.addObject(new NukeBase(location.x - 64, location.y, handler));
  }

  private static Point getSpawnLocation(Handler handler) {
    int count = handler.getBaseCount() % 5;
    int place = 0;
    if (count == 0) {
      place = 3;
    }
    if (count == 1) {
      place = 1;
    }
    if (count == 2) {
      place = 5;
    }
    if (count == 3) {
      place = 2;
    }
    if (count == 4) {
      place = 4;
    }
    return new Point((Settings.WIDTH / 5) * place - Settings.WIDTH / 10, Settings.HEIGHT - 32);
  }

  public static void spawnHuman(int x, int y, Handler handler) {
    if (handler.getHumanCount() <= 10) {
      handler.addObject(new Human(x, y, handler));
    }
  }
}
