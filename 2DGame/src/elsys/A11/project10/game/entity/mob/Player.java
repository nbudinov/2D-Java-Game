package elsys.A11.project10.game.entity.mob;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;
import elsys.A11.project10.game.input.KeyHandler;

public class Player extends Mob {
	private KeyHandler input;
	private int anim = 0;
	public boolean walking = false;

	public Player(int x, int y, KeyHandler input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}

	public void tick() {
		int xa = 0, ya = 0;
		int offset = 3;
		if (anim < 20000)
			anim++;
		else
			anim = 0;
	//	System.out.println(anim);
		if (input.isUp()) ya -= offset;
		if (input.isDown()) ya += offset;
		if (input.isRight()) xa += offset;
		if (input.isLeft()) xa -= offset;
		if (input.isSpace()) {
			hp = 100;
			System.out.println("You are Full  ");
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}
	
	

	public void render(Screen screen) {
		boolean pace = anim % 20 > 8;
		if (walking) {
			
			if (direction == 4) {
				if (pace)
					screen.renderPlayer(x, y, Sprite.playerMoveUp, false, false);
				else
					screen.renderPlayer(x, y, Sprite.playerMoveUp, true, false);
			}
			if (direction == 3) {
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
			if (direction == 3) screen.renderPlayer(x, y, Sprite.playerFrontStill, true, false);
			if (direction == 4) screen.renderPlayer(x, y, Sprite.playerBackStill, false, false);
			if (hp == 0) {
				screen.renderPlayer(x, y, Sprite.playerDead, false, false);
				


			}
		}
	}
	
}
