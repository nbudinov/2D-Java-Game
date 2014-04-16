package elsys.A11.project10.game.entity.mob;

import java.util.Random;

import elsys.A11.project10.game.entity.Projectile;
import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class NPC extends Mob {
	Random rand = new Random();
	private int hbx1;
	private int hby1;
	private int hbx2;
	private int hby2;
	private int hbx3;
	private int hby3;
	private int hbx4;
	private int hby4;

	public NPC(int x, int y) {
		this.x = x;
		this.y = y;
		hbx1 = (x-5);
		hby1 = (y-5);
		hbx2 = (x+5);
		hby2 = (y-5);
		hbx3 = (x+5);
		hby3 = (y+5);
		hbx4 = (x-5);
		hby4 = (y+5);
	}

	public void tick() {
		System.out.println(" hbx1 "+hbx1+" hbx2 "+hbx2+" hxb3 "+ hbx3 + " hbx4 "+ hbx4);
		System.out.println(" hby1 "+hby1+" hby2 "+hby2+" hby3 "+ hby3 + " hby4 "+ hby4);
	}

	public void render(int x, int y, Screen screen) {
		if (hp > 0)
			screen.renderPlayer(x, y, Sprite.playerFrontStill, true, false);
		else
			screen.renderPlayer(x, y, Sprite.playerDead, false, false);
	}
}
