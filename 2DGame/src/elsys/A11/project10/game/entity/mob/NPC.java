package elsys.A11.project10.game.entity.mob;


import java.util.Random;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;
import elsys.A11.project10.game.level.Level;

public class NPC extends Mob {
	public double x, y;
	public double xMove = 3;
	public double yMove = 3;
	public static final int range = 200;
	private int offset = 10;
	public double hbx1, hby1;
	public double hbx2, hby2;
	public double hbx3, hby3;
	public double hbx4, hby4;
	private int anim;
	private boolean walking = false;
	int xRand = 1, yRand = 1;
	public int time;

	public NPC(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public void tick() {
		time ++;
		move((int)xMove, (int) yMove);

		// System.out.println(" hbx1 "+hbx1+" hbx2 "+hbx2+" hxb3 "+ hbx3 +
		// " hbx4 "+ hbx4);
		// System.out.println(" hby1 "+hby1+" hby2 "+hby2+" hby3 "+ hby3 +
		// " hby4 "+ hby4);
		// System.out.println(hp);
		anim++;
		hbx1 = (x - offset);
		hby1 = (y - offset);
		hbx2 = (x + offset);
		hby2 = (y - offset);
		hbx3 = (x + offset);
		hby3 = (y + offset);
		hbx4 = (x - offset);
		hby4 = (y + offset);
		if (!dead) {
			if (xMove != 0 && yMove != 0)
				walking = true;
			else
				walking = false;
			if (!endOfMapCollision(((int) x + (int) xMove), 0)) this.x += xMove;
			if (!endOfMapCollision(0, ((int) y + (int) yMove))) this.y += yMove;
		} else
			walking = false;
	}

	public void render(int x, int y, Screen screen) {
		boolean pace = anim % 20 > 10;
		if (!dead) {
			if (walking) {

				if (direction == 4 || direction == 5 || direction == 6) {
					if (pace)
						screen.renderPlayer(x, y, Sprite.npcMoveUp, false, false);
					else
						screen.renderPlayer(x, y, Sprite.npcMoveUp, true, false);
				}
				if (direction == 3 || direction == 7 || direction == 8) {
					if (pace)
						screen.renderPlayer(x, y, Sprite.npcMoveDown, false, false);
					else
						screen.renderPlayer(x, y, Sprite.npcMoveDown, true, false);
				}
				if (direction == 2) {
					if (pace)
						screen.renderPlayer(x, y, Sprite.npcSideStill, true, false);
					else
						screen.renderPlayer(x, y, Sprite.npcMoveSide, true, false);
				}
				if (direction == 1) {
					if (pace)
						screen.renderPlayer(x, y, Sprite.npcSideStill, false, false);
					else
						screen.renderPlayer(x, y, Sprite.npcMoveSide, false, false);
				}
			} else {

				if (direction == 1) screen.renderPlayer(x, y, Sprite.npcSideStill, false, false);
				if (direction == 2) screen.renderPlayer(x, y, Sprite.npcSideStill, true, false);
				if (direction == 3 || direction == 7 || direction == 8) screen.renderPlayer(x, y, Sprite.npcFrontStill, true, false);
				if (direction == 4 || direction == 5 || direction == 6) screen.renderPlayer(x, y, Sprite.npcBackStill, false, false);
			}
		} else
			screen.renderPlayer(x, y, Sprite.npcDead, false, false);
	}
	
	public void move(int xDir, int yDir) {
		if (endOfMapCollision((int) x + 10, 0)) xMove = 0;
		if (endOfMapCollision((int) 0, (int) y + 10)) yMove = 0;
		if (xDir > 0) direction = 1;
		if (xDir < 0) direction = 2;
		if (yDir > 0) direction = 3;
		if (yDir < 0) direction = 4;
		if (yDir < 0 && xDir < 0) direction = 5;
		if (yDir < 0 && xDir > 0) direction = 6;
		if (yDir > 0 && xDir < 0) direction = 7;
		if (yDir > 0 && xDir > 0) direction = 8;
	}
	
	public void mobRandomMovement(int n) {
		if (time == 60) {
			xRand = new Random().nextInt(3) - 1;
			yRand = new Random().nextInt(3) - 1;
			time = 0;
		}
		xMove = xRand;
		yMove = yRand;
		//System.out.println(time);

	}
	

}
