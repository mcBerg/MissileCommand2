package game;

import java.util.Random;

public class Settings {

  public static final int WIDTH = 1920;
  public static final int HEIGHT = (WIDTH / 16) * 9;
  public static final Random r = new Random(System.nanoTime());
  private static int aim = 5;
  private static int distance = 10;
  public static final int L1_MAX_HEIGHT = 100;
  public static final int L2_MAX_HEIGHT = 200;
  public static final int L2_SPAWN_SPEED = 100;
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
    if (distance > 25) {
      Settings.distance = 25;
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
}
