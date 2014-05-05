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

	public int heroSpeed = 2;
	
	public Player(int x, int y, KeyHandler input) {
		this.x = x;
		this.y = y;
		this.input = input;
	}

	public void tick() {
		double direction = Math.atan2((Mouse.getMouseY()-Game.getWindowHeight()/2), (Mouse.getMouseX()-Game.getWindowWidth()/2));
		//System.out.println("direction " + direction +  " " + Math.toDegrees(direction));
		if (rateOfFire > 0) rateOfFire--;
		int xa = 0, ya = 0;
		int offset = 4;
		if (anim < 20)
			anim++;
		else
			anim = 0;
		// System.out.println(anim);
		if (input.isUp()) ya -= heroSpeed;
		if (input.isDown()) ya += heroSpeed;
		if (input.isRight()) xa += heroSpeed;
		if (input.isLeft()) xa -= heroSpeed;
		if (input.isSpace()) {
			hp = 100;
			mana = 100;
			dead = false;
			int r = rand.nextInt(200 - 0) + 0;
			int r1 = rand.nextInt(200 - 0) + 0;
			if (level.npcs.size() < 7 && !collision(r, r1)) createNpc(r, r1);
			// System.out.println(rand.nextInt(200-0)+0);

		}

		if (input.isP()) {
			int p = rand.nextInt(300 - 0) + 0;
			int p1 = rand.nextInt(300 - 0) + 0;
			
			int type = rand.nextInt(4 - 0) + 0;
			System.out.println("type === "  + type);
			if (level.potions.size() < 10 && !collision(p, p1)) spawnPotion(p, p1, type);
			
				
			
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
			if (Mouse.getMouseB()==1 && rateOfFire <= 0) {
				shoot(x, y, direction);
				rateOfFire = Projectile.getRateOfFire();
			}
		}
		hurtNpc();
		hurtPlayer();
		increaseMana();
		// System.out.println("proj size" + level.projectiles.size());
		// System.out.println("npc size" + level.npcs.size());
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
		} else if (hp <= 0){
			screen.renderPlayer(x, y, Sprite.playerDead, false, false);
		}
	}

	private void hurtNpc() {
		int n = 0;
		for (int p = 0; p < level.projectiles.size(); p++) {
			for (n = 0; n < level.npcs.size(); n++) {

				if (isProjInNpcX(p, n) && isProjInNpcY(p, n)) {
					level.npcs.get(n).hp -= Projectile.getDmg();
					if (level.npcs.get(n).hp < 0) level.npcs.get(n).dead = true;
					level.projectiles.remove(p);
					break;
				}
			}
		}
	}

	private Boolean isProjInNpcX(int i, int n) {
		boolean hit = false;
		if (level.projectiles.get(i).x > level.npcs.get(n).hbx1 == true && level.projectiles.get(i).x < level.npcs.get(n).hbx2 == true) hit = true;
		return hit;
	}

	private Boolean isProjInNpcY(int i, int n) {
		return level.projectiles.get(i).y > level.npcs.get(n).hby2 && level.projectiles.get(i).y < level.npcs.get(n).hby3;
	}

	private void hurtPlayer() {
		if (level.npcs.size() > 0) 
		for (int n = 0; n < level.npcs.size(); n++) {
			if (!level.npcs.get(n).dead) if (isNpcInPlayerX(n) && isNpcInPlayerY(n)) reduceHp();
			if (hp < 0) {
				hp = 0;
				dead = true;
			}
		}

	}

	private Boolean isNpcInPlayerX(int n) {
		// System.out.println(((level.npcs.get(0).x > this.x - 10) &&
		// (level.npcs.get(0).x < this.x + 10)));
		return ((level.npcs.get(n).x > this.x - 10) && (level.npcs.get(n).x < this.x + 10));
	}

	private Boolean isNpcInPlayerY(int n) {
		return ((level.npcs.get(n).y > this.y - 10) && (level.npcs.get(n).y < this.y + 10));
	}




public void getPotion() {
	for(int p = 0; p < level.potions.size() ; p++) {
		if (isPlayerInPotionY(p) == true && isPlayerInPotionX(p) == true ) {
		//	System.out.println("vzimaiiiiiiiiii");
			
			
			if (level.potions.get(p).type == 0) {
				if (heroSpeed < 5) heroSpeed += 1;
				System.out.println("Speed now is  "  + heroSpeed + "  max is 5");

			}
			if (level.potions.get(p).type == 1) {
				if (Projectile.dmg < 50) Projectile.dmg += 10;
			
				System.out.println(" DMG now is " + Projectile.dmg + "  max is 50");
			}
			if (level.potions.get(p).type == 2){
				if(hp < 150) hp += 25  ;
			}
			
			if (level.potions.get(p).type == 3){
				if(mana < 150) mana += 25;
			}
			
			
			level.potions.remove(p);
		}
	}
}



	public boolean isPlayerInPotionX(int n) {
		boolean gotPotion = false;
		if ( x > level.potions.get(n).px1 == true && x < level.potions.get(n).px2 == true) {
			gotPotion = true;
			//System.out.println("player X = "  + x + " POTION " + level.potions.get(n).px2);
		}
	return gotPotion ;
	}

	public boolean isPlayerInPotionY(int n) {
		boolean gotPotion = false;
		if ( y > level.potions.get(n).py2 == true && y < level.potions.get(n).py3 == true) {
			gotPotion = true;
			//System.out.println("player Y = "  + y + " POTION " + level.potions.get(n).py2);
		}
	return gotPotion ;
	}



}





