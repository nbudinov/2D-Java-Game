package elsys.A11.project10.game.entity.mob;


import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class NPC extends Mob {
	public int xMove = 1;
	public int yMove = 1;
	private int offset = 13;
	public int hbx1, hby1;
	public int hbx2, hby2;
	public int hbx3, hby3;
	public int hbx4, hby4;

	public NPC(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public void tick() {

		// System.out.println(" hbx1 "+hbx1+" hbx2 "+hbx2+" hxb3 "+ hbx3 +
		// " hbx4 "+ hbx4);
		// System.out.println(" hby1 "+hby1+" hby2 "+hby2+" hby3 "+ hby3 +
		// " hby4 "+ hby4);
		// System.out.println(hp);
		hbx1 = (x - offset);
		hby1 = (y - offset);
		hbx2 = (x + offset);
		hby2 = (y - offset);
		hbx3 = (x + offset);
		hby3 = (y + offset);
		hbx4 = (x - offset);
		hby4 = (y + offset);
		if (!dead) {
			this.x += xMove;
			this.y += yMove;
		}
	}

	public void render(int x, int y, Screen screen) {
		if (!dead)
			screen.renderPlayer(x, y, Sprite.npcFrontStill, true, false);
		else
			screen.renderPlayer(x, y, Sprite.npcDead, false, false);
	}

}
