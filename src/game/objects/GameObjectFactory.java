package game.objects;

import static game.Settings.*;

public class GameObjectFactory {

  public static void spawnEnemyL1(Handler handler) {
    handler.addObject(new EnemyL1(r.nextInt(WIDTH), r.nextInt(HEIGHT), handler));
  }

  public static void spawnEnemyL1At(int x, int y, Handler handler) {
    handler.addObject(new EnemyL1(x, y, handler));
  }

  public static void spawnEnemyL2(Handler handler) {
    handler.addObject(
        new EnemyL2(r.nextInt(WIDTH), HEIGHT - L2_MAX_HEIGHT - r.nextInt(100), handler));
  }

  public static void spawnLightningBase(Handler handler) {
    int count = handler.getPlayerCount();
    int place = 0;
    if (count < 5) {
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
      handler.addObject(new LightningBase((WIDTH / 5) * place - WIDTH / 10, HEIGHT - 32, handler));
    }
  }

  public static void spawnMissileBase(Handler handler) {
    int count = handler.getPlayerCount();
    int place = 0;
    if (count < 10 && count >= 5) {
      if (count == 5) {
        place = 3;
      }
      if (count == 6) {
        place = 1;
      }
      if (count == 7) {
        place = 5;
      }
      if (count == 8) {
        place = 2;
      }
      if (count == 9) {
        place = 4;
      }
      handler.addObject(
          new MissileBase((WIDTH / 5) * place - WIDTH / 10 + 32, HEIGHT - 32, handler));
    }
  }
}
