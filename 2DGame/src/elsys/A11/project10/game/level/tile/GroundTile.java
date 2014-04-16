package elsys.A11.project10.game.level.tile;

import elsys.A11.project10.game.graphics.Screen;
import elsys.A11.project10.game.graphics.Sprite;

public class GroundTile extends Tile
{

	public GroundTile(Sprite sprite) {
		super(sprite);
	}


	public void render(int x, int y, Screen screen) {
		screen.renderTile(x * 16, y * 16, Sprite.ground);

	}

	public boolean solid()	{
		return false;
	}
}