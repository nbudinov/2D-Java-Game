package elsys.A11.project10.game.entity.mob;

import java.util.Random;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class NPC extends Mob {
	public double x, y;
	public double xMove = 3;
	public double yMove = 3;
	public static final int range = 2000000;
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
			if (xMove != 0 || yMove != 0)
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
		// System.out.println(walking);
		if (!dead) {
			if (walking) {

				if (direction == 4) {
					if (pace)
						screen.renderPlayer(x, y, Sprite.npcMoveUp, false, false);
					else
						screen.renderPlayer(x, y, Sprite.npcMoveUp, true, false);
				}
				if (direction == 3) {
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
		// System.out.println(xDir);
		if (endOfMapCollision((int) x + xDir, 0)) xMove = 0;
		if (endOfMapCollision(0, (int) y + yDir)) yMove = 0;

	}

	public void mobRandomMovement(int n) {
		if (time == 60) {
			xRand = new Random().nextInt(3) - 1;
			yRand = new Random().nextInt(3) - 1;
			time = 0;
		}
		xMove = xRand;
		yMove = yRand;
		// System.out.println(time);

	}

}
