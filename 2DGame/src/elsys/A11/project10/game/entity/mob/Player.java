package elsys.A11.project10.game.entity.mob;

import elsys.A11.project10.game.entity.Projectile;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;
import elsys.A11.project10.game.input.KeyHandler;

public class Player extends Mob {
	private KeyHandler input;
	private int anim = 0;
	public boolean walking = false;
	public boolean shooting = false;
	private int rateOfFire = Projectile.rateOfFire;

	public Player(int x, int y, KeyHandler input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}

	public void tick() {
		if(rateOfFire > 0) rateOfFire--;
		int xa = 0, ya = 0;
		int offset = 3;
		if (anim < 20)
			anim++;
		else
			anim = 0;
		// System.out.println(anim);
		if (input.isUp()) ya -= offset;
		if (input.isDown()) ya += offset;
		if (input.isRight()) xa += offset;
		if (input.isLeft()) xa -= offset;
		if (input.isSpace()) {
			hp = 100;
			dead = false;
			System.out.println("You are Full ");
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		if (input.isShift() && rateOfFire <= 0) {
			shoot(x, y, this.direction);
			rateOfFire = Projectile.rateOfFire;
		}
	}

	public void render(Screen screen) {
		boolean pace = anim % 20 > 10;
		if(!dead) {
			if (walking) {

				if (direction == 4 || direction == 5 || direction == 6) {
					if (pace)
						screen.renderPlayer(x, y, Sprite.playerMoveUp, false, false);
					else
						screen.renderPlayer(x, y, Sprite.playerMoveUp, true, false);
				}
				if (direction == 3 || direction == 7 || direction == 8) {
					if (pace)
						screen.renderPlayer(x, y, Sprite.playerMoveDown, false, false);
					else
						screen.renderPlayer(x, y, Sprite.playerMoveDown, true, false);
				}
				if (direction == 2) {
					if (pace)
						screen.renderPlayer(x, y, Sprite.playerSideStill, true, false);
					else
						screen.renderPlayer(x, y, Sprite.playerMoveSide, true, false);
				}
				if (direction == 1) {
					if (pace)
						screen.renderPlayer(x, y, Sprite.playerSideStill, false, false);
					else
						screen.renderPlayer(x, y, Sprite.playerMoveSide, false, false);
				}
			} else {
			
				if (direction == 1) screen.renderPlayer(x, y, Sprite.playerSideStill, false, false);
				if (direction == 2) screen.renderPlayer(x, y, Sprite.playerSideStill, true, false);
				if (direction == 3 || direction == 7 || direction == 8) screen.renderPlayer(x, y, Sprite.playerFrontStill, true, false);
				if (direction == 4 || direction == 5 || direction == 6) screen.renderPlayer(x, y, Sprite.playerBackStill, false, false);
			
			}
		} else screen.renderPlayer(x, y, Sprite.playerDead, false, false);
	}

}
