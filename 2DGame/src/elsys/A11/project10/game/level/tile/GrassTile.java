package elsys.A11.project10.game.level.tile;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class GrassTile extends Tile {

	public GrassTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x * 16, y * 16, Sprite.grass);
	}
	
	public boolean solid() {
		return false;
		
	}
}
