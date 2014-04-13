package elsys.A11.project10.game.entity;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class Projectile extends Entity {
	private int direction = 1;
	private Sprite sprite;
	private int nx, ny;
	private int speed = 5;
	private int dmg = 10;
	public static int rateOfFire = 10;

	public Projectile(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		sprite = Sprite.projectile;

		setDir(direction);
	}

	public void tick() {
		move();

	}

	private void move() {
		this.x+=nx*speed;
		this.y+=ny*speed;
	}

	public void render(Screen screen) {
		screen.renderTile(this.x, this.y, sprite);
	}

	private void setDir(int direction) {
		if (direction == 8) {
			this.nx = 1;
			this.ny = 1;
		}
		if (direction == 7) {
			this.nx = -1;
			this.ny = 1;
		}
		if (direction == 6) {
			this.nx = 1;
			this.ny = -1;
		}
		if (direction == 5) {
			this.nx = -1;
			this.ny = -1;
		}
		if (direction == 4) {
			this.nx = 0;
			this.ny = -1;
		}
		if (direction == 3) {
			this.nx = 0;
			this.ny = 1;
		}
		if (direction == 2) {
			this.nx = -1;
			this.ny = 0;
		}
		if (direction == 1) {
			this.nx = 1;
			this.ny = 0;
		}
	}
}
