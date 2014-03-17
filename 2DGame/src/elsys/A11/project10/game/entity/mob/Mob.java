package elsys.A11.project10.game.entity.mob;

import elsys.A11.project10.game.entity.Entity;
import elsys.A11.project10.game.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int direction = 1;
	protected boolean moving = false;

	public void move(int xDir, int yDir) {
		if (xDir > 0) direction = 1;
		if (xDir < 0) direction = 2;
		if (yDir > 0) direction = 3;
		if (yDir < 0) direction = 4;

		if (!collision()) {

			x += xDir;
			y += yDir;
		}
	}

	public void tick() {

	}

	public void render() {

	}

	private boolean collision() {
		return false;
	}

}
