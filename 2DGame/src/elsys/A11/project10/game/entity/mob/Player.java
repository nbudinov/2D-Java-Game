package elsys.A11.project10.game.entity.mob;

import java.util.Random;

import elsys.A11.project10.game.Game;
import elsys.A11.project10.game.entity.Projectile;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;
import elsys.A11.project10.game.input.KeyHandler;
import elsys.A11.project10.game.input.Mouse;

public class Player extends Mob {
	private KeyHandler input;
	private int anim = 0;
	public boolean walking = false;
	public boolean shooting = false;
	private int rateOfFire = Projectile.getRateOfFire();
	Random rand = new Random();

	public int xp = 0;
	public int heroLvl = 1;
	public double heroSpeed = 2;
	public int maxHeroSpeed = 2;
	public int maxDmg = 20;
	public int maxHP = 100;
	public int lvlCheck = 2;
	public int neededXP = 1200;
	public int givenXP = 400;

	public Player(int x, int y, KeyHandler input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}

	public void tick() {
		double direction = Math.atan2((Mouse.getMouseY() - Game.getWindowHeight() / 2 + (Game.iy * Game.getScale())), (Mouse.getMouseX() - Game.getWindowWidth() / 2 + (Game.ix * Game.getScale())) - 15);
		if (rateOfFire > 0) rateOfFire--;
		int xa = 0, ya = 0;

		if (anim == 60) {
			spawnNpc();
		}

		if (anim == 180) {
			spwnPot();
		}
		if (anim < 420)
			anim++;
		else
			anim = 0;
		if (input.isUp()) ya -= heroSpeed;
		if (input.isDown()) ya += heroSpeed;
		if (input.isRight()) xa += heroSpeed;
		if (input.isLeft()) xa -= heroSpeed;
		if (input.isSpace()) {
			hp = maxHP;
			mana = 100;
			dead = false;

		}

		dieCollis(xa, ya);
		getPotion();

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

		if (!dead) {
			if (Mouse.getMouseB() == 1 && rateOfFire <= 0) {
				shoot(x, y, direction);
				rateOfFire = Projectile.getRateOfFire();
			}
		}
		hurtNpc();
		hurtPlayer();
		increaseMana();
	}

	public void render(Screen screen) {
		boolean pace = anim % 20 > 10;
		if (!dead) {
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
		} else if (hp <= 0) {
			screen.renderPlayer(x, y, Sprite.playerDead, false, false);
		}
	}

	private void spawnNpc() {
		int r, r1;
		if (level.npcs.size() < 20) {
			r = 0 + (int) (Math.random() * ((128 * 16 - 0) + 1));
			r1 = 0 + (int) (Math.random() * ((128 * 16 - 0) + 1));
			if (!collision(r, r1) && !dieColl(r, r1)) createNpc(r, r1);

		}
	}

	public void spwnPot() {
		if (level.potions.size() < 10) {
			int p = rand.nextInt(128 * 16 - 0) + 0;
			int p1 = rand.nextInt(128 * 16 - 0) + 0;

			int type = rand.nextInt(4 - 0) + 0;
			// System.out.println("type === " + type);
			if (level.potions.size() < 10 && !collision(p, p1)) spawnPotion(p, p1, type);
		}

	}

	private void hurtNpc() {
		int n = 0;
		for (int p = 0; p < level.projectiles.size(); p++) {
			for (n = 0; n < level.npcs.size(); n++) {
				if (!level.npcs.get(n).dead) if (isProjInNpcX(p, n) && isProjInNpcY(p, n)) {
					level.npcs.get(n).hp -= Projectile.getDmg();
					if (level.npcs.get(n).hp < 0) {
						level.npcs.get(n).dead = true;
						incXpAndLvl();
					}
					level.projectiles.remove(p);
					// if(level.npcs.get(n).hp == 0) level.npcs.remove(n);
					break;
				}
			}
		}
	}

	public void incXpAndLvl() {
		xp += givenXP;

		if (xp >= neededXP && lvlCheck == 2) {
			heroLvl = 2;
			xp = 0;
			lvlCheck = 3;
			incStats();
			incNeededXP();
			incNpcHpDmgSpd();
			incGivenXP();
		} else if (xp >= neededXP && lvlCheck == 3) {
			heroLvl = 3;
			incStats();
			xp = 0;
			lvlCheck = 4;
			incNeededXP();
			incNpcHpDmgSpd();
			incGivenXP();
		} else if (xp >= neededXP && lvlCheck == 4) {
			heroLvl = 4;
			incStats();
			xp = 0;
			incNeededXP();
			lvlCheck = 5;
			incNpcHpDmgSpd();
			incGivenXP();
		} else if (xp >= neededXP && lvlCheck == 5) {
			heroLvl = 5;
			incStats();
			incNpcHpDmgSpd();
			incNpcHpDmgSpd();
			// incNeededXP();
			lvlCheck = 6;
		}
	}

	public void incNpcHpDmgSpd() {
		hpNPC += 50;
		for (int i = 0; i < level.npcs.size(); i++) {
			level.npcs.get(i).hp = hpNPC;
			// System.out.println("it is " + level.npcs.get(i).hp );
		}
		dmgNPC += 1;
		spdNPC += 0.2;
	}

	public void incStats() {
		heroSpeed += 0.5;
		maxHeroSpeed += 1;
		Projectile.dmg += 10;
		maxDmg += 20;
		maxHP += 25;
		regenerateMana += 0.5;
	}

	public void incNeededXP() {
		neededXP *= 2;
	}

	public void incGivenXP() {
		givenXP += 200;
	}

	/*
	 * public void whenDie() { // not used yet if(heroLvl > 1) { if(dead ==
	 * true) { xp -= 400; if(xp <= 0) { heroLvl--; reduceStats();
	 * //System.out.println("U dyed! Lvl and stats reduced"); xp = 0; dead =
	 * false; } } } }
	 * 
	 * public void reduceStats() { heroSpeed -= 0.5; maxHeroSpeed -= 1;
	 * Projectile.dmg -= 10; maxDmg -= 20; maxHP -= 25; regenerateMana -= 0.5; }
	 */

	private Boolean isProjInNpcX(int i, int n) {
		boolean hit = false;
		if (level.projectiles.get(i).x > level.npcs.get(n).hbx1 == true && level.projectiles.get(i).x < level.npcs.get(n).hbx2 == true) hit = true;
		return hit;
	}

	private Boolean isProjInNpcY(int i, int n) {
		return level.projectiles.get(i).y > level.npcs.get(n).hby2 && level.projectiles.get(i).y < level.npcs.get(n).hby3;
	}

	private void hurtPlayer() {
		if (level.npcs.size() > 0) for (int n = 0; n < level.npcs.size(); n++) {
			if (!level.npcs.get(n).dead) if (isNpcInPlayerX(n) && isNpcInPlayerY(n)) hp -= dmgNPC;
			if (hp < 0) {
				hp = 0;
				dead = true;
				// whenDie();
		}
	}

	}

	private Boolean isNpcInPlayerX(int n) {
		return ((level.npcs.get(n).x > this.x - 10) && (level.npcs.get(n).x < this.x + 10));
	}

	private Boolean isNpcInPlayerY(int n) {
		return ((level.npcs.get(n).y > this.y - 10) && (level.npcs.get(n).y < this.y + 10));
	}

	public void getPotion() {
		for (int p = 0; p < level.potions.size(); p++) {
			if (isPlayerInPotionY(p) == true && isPlayerInPotionX(p) == true) {

				if (level.potions.get(p).type == 0) {
					if (heroSpeed < maxHeroSpeed) heroSpeed += 0.5;

				}
				if (level.potions.get(p).type == 1) {
					if (Projectile.dmg < maxDmg) Projectile.dmg += 10;

				}
				if (level.potions.get(p).type == 2) {
					if (hp < 150) hp += 25;
				}

				if (level.potions.get(p).type == 3) {
					if (mana < 150) mana += 25;
				}

				level.potions.remove(p);
			}
		}
	}

	public boolean isPlayerInPotionX(int n) {
		boolean gotPotion = false;
		if (x > level.potions.get(n).px1 == true && x < level.potions.get(n).px2 == true) {
			gotPotion = true;
			// System.out.println("player X = " + x + " POTION " +
			// level.potions.get(n).px2);
		}
		return gotPotion;
	}

	public boolean isPlayerInPotionY(int n) {
		boolean gotPotion = false;
		if (y > level.potions.get(n).py2 == true && y < level.potions.get(n).py3 == true) {
			gotPotion = true;
			// System.out.println("player Y = " + y + " POTION " +
			// level.potions.get(n).py2);
		}
		return gotPotion;
	}

}
