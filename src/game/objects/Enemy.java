package game.objects;

import game.ID;

import java.awt.*;

public class Enemy extends GameObject {
    public Enemy(int x, int y, ID id) {
        super(x,y,id);
    }

    @Override
    public void tick() {
        x+=velX;
        y+=velY;

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillArc(x, y, 30, 30, 0, 45);
    }
}
