package org.berg.missile.command;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Settings {

  public static final int WIDTH = 1920;
  public static final int HEIGHT = (WIDTH / 16) * 9;
  public static final Random r = new Random(System.nanoTime());
  private static int laserLevel = 2;
  private static int lightningSpeed = 25;
  private static int aim = 5;
  private static int distance = 10;
  private static int laserTime = 50;
  public static final int ALIEN_MAX_HEIGHT = HEIGHT / 5 * 4 + HEIGHT / 10;
  public static final int UFO_MAX_HEIGHT = HEIGHT / 5 * 3;
  public static final int MOTHERSHIP_MAX_HEIGHT = HEIGHT / 5 * 2;
  public static final int PORTAL_MAX_HEIGHT = HEIGHT / 5 * 1;
  public static final int CONSTRUCTION_MAX_HEIGHT = 0;
  public static final int ALIEN_SPAWN_SPEED = 100;
  private static int missileFuel = 60;
  private static int missileSpeed = 100;

  public static void setAim(int aim) {
    Settings.aim = aim;
    if (aim > 25) {
      Settings.aim = 25;
    }
  }

  public static void setDistance(int distance) {
    Settings.distance = distance;
    if (distance > 30) {
      Settings.distance = 30;
    }
  }

  public static void setMissileFuel(int missileFuel) {
    Settings.missileFuel = missileFuel;
    if (missileFuel > 100) {
      Settings.missileFuel = 100;
    }
  }

  public static void setMissileSpeed(int missileSpeed) {
    Settings.missileSpeed = missileSpeed;
    if (missileSpeed < 10) {
      Settings.missileSpeed = 10;
    }
  }

  public static int getLightningSpeed() {
    return lightningSpeed;
  }

  public static int getLaserLevel() {
    return laserLevel;
  }

  public static void setLaserLevel(int laserLevel) {
    if (laserLevel > 5) {
      Settings.laserLevel = 5;
      return;
    }
    if (laserLevel < 1) {
      Settings.laserLevel = 1;
      return;
    }
    Settings.laserLevel = laserLevel;
  }

  public static void setLightningSpeed(int lightningSpeed) {
    if (lightningSpeed < 2) {
      Settings.lightningSpeed = 2;
    } else {
      Settings.lightningSpeed = lightningSpeed;
    }
  }

  public static int getAim() {
    return aim;
  }

  public static int getDistance() {
    return distance;
  }

  public static int getMissileFuel() {
    return missileFuel;
  }

  public static int getMissileSpeed() {
    return missileSpeed;
  }

  public static int getLaserTime() {
    return laserTime;
  }

  public static void setLaserTime(int laserTime) {
    Settings.laserTime = laserTime;
  }
}
