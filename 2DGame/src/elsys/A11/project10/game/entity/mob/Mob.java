package elsys.A11.project10.game.entity.mob;

import java.util.ArrayList;
import java.util.List;

import elsys.A11.project10.game.entity.Entity;
import elsys.A11.project10.game.entity.Potion;
import elsys.A11.project10.game.entity.Projectile;
import elsys.A11.project10.game.graphics.Sprite;

public abstract class Mob extends Entity {
	protected Sprite sprite;
	public int direction = 1;
	protected boolean moving = false;
	public int hp = 100;
	public int mana = 100;
	public boolean dead = false;
	int pace = 0;
	public int regenerateMana = 1;
	public int hpNPC = 100;
	public int dmgNPC = 1;
	public double spdNPC = 1.5;

	public List<Projectile> projectiles = new ArrayList<Projectile>();

	public void move(int xDir, int yDir) {
		// hp = 100;
		// System.out.println(" x = " + x + " y = " + y);
		if (xDir > 0) direction = 1;
		if (xDir < 0) direction = 2;
		if (yDir > 0) direction = 3;
		if (yDir < 0) direction = 4;
		if (yDir < 0 && xDir < 0) direction = 5;
		if (yDir < 0 && xDir > 0) direction = 6;
		if (yDir > 0 && xDir < 0) direction = 7;
		if (yDir > 0 && xDir > 0) direction = 8;
		Collis(dead, xDir, yDir);
	}

	public void Collis(boolean dead, int xa, int ya) {
		if (!dead) {
			if (!collision(xa, 0) && !endOfMapCollision(xa, 0)) {
				x += xa;
			}

			if (!collision(0, ya) && !endOfMapCollision(0, ya)) {
				y += ya;
			}
		}
	}

	public void dieCollis(int xa, int ya) {
		if (dieColl(xa, ya)) {
			int i = 0;
			while (i <= 7200) {
				i += 1;
				if (i == 7200) {
					if (hp >= 5) {
						reduceHp();
					} else {
						dead = true;
						hp = 0;
					}
				}
			}
		}
	}

	public boolean dieColl(int xDir, int yDir) {
		boolean die = false;
		int corner;
		for (corner = 0; corner < 4; corner++) {
			int xcor = (((x + xDir) + corner * 2 + 6) / 16);
			int ycor = (((y + yDir) + corner * 2 + 7) / 16);
			if (level.getTile(xcor, ycor).die()) die = true;
		}
		return die;
	}

	protected void shoot(int x, int ys, double direction) {
		if (mana > 10) {
			Projectile p = new Projectile(x, y, direction);
			level.projectiles.add(p);
			reduceMana();
		}

	}

	public void spawnPotion(int x, int y, int type) {
		if (!collision(x, y) && !dieColl(x, y)) {
			Potion p = new Potion(x, y, type);
			level.potions.add(p);
		}
	}

	public void createNpc(int x, int y) {
		NPC e = new NPC(x, y);
		level.npcs.add(e);
	}

	public boolean collision(int xDir, int yDir) {
		boolean solid = false;
		int corner;
		for (corner = 0; corner < 4; corner++) {
			int xcor = (((x + xDir) + corner * 2 + 5) / 16);
			int ycor = (((y + yDir) + corner * 2 + 6) / 16);
			if (level.getTile(xcor, ycor).solid()) solid = true;
		}

		// System.out.println(" = " + (x + xDir) / 16 + " =  "+(y + yDir) / 16);
		return solid;
	}

	public boolean endOfMapCollision(int xDir, int yDir) {
		// System.out.println("x + xDir = " + (x + xDir) + " y + yDir =  " + (y
		// + yDir));
		boolean end = false;
		if (x + xDir <= -10 || y + yDir <= -10) end = true;
		if (x + xDir >= 127 * 16 + 10 || y + yDir >= 127 * 16 + 5) end = true;

		return end;
	}

	public void reduceHp() {
		hp -= 15;
	}

	private void reduceMana() {
		mana -= 10;
	}

	protected void increaseMana() {
		pace++;
		// System.out.println(pace);
		if (pace == 5) {
			mana += regenerateMana;
			pace = 0;
		}
		if (mana >= 100) mana = 100;

	}

}
