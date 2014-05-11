package elsys.A11.project10.game.graphics;

public class Sprite {
	private final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	//Terrain
	public static Sprite voidSprite = new Sprite(16, 0xff77ff);
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.sheet);
	public static Sprite stone = new Sprite(16, 2, 0, SpriteSheet.sheet);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.sheet);
	public static Sprite wall = new Sprite(16, 0, 1, SpriteSheet.sheet);
	public static Sprite lava = new Sprite(16, 1, 1, SpriteSheet.sheet);
	public static Sprite ground = new Sprite(16, 2, 1, SpriteSheet.sheet );
	public static Sprite chair = new Sprite(16, 1, 3, SpriteSheet.sheet );
	public static Sprite table = new Sprite(16, 2, 3, SpriteSheet.sheet );
	public static Sprite bed = new Sprite(16, 0, 3, SpriteSheet.sheet );
	
	
	//npc
	public static Sprite npcDead = new Sprite(16, 6, 14, SpriteSheet.sheet);
	public static Sprite npcFrontStill = new Sprite(16, 4, 14, SpriteSheet.sheet);
	public static Sprite npcMoveUp = new Sprite(16, 0, 14, SpriteSheet.sheet);
	public static Sprite npcMoveDown = new Sprite(16, 1, 14, SpriteSheet.sheet);
	public static Sprite npcSideStill = new Sprite(16, 2, 14, SpriteSheet.sheet);
	public static Sprite npcBackStill = new Sprite(16, 4, 14, SpriteSheet.sheet);
	public static Sprite npcMoveSide = new Sprite(16, 3, 14, SpriteSheet.sheet);

	//Player	
	public static Sprite playerDead = new Sprite(16, 6, 15, SpriteSheet.sheet);
	public static Sprite playerMoveUp = new Sprite(16, 0, 15, SpriteSheet.sheet);
	public static Sprite playerMoveDown = new Sprite(16, 1, 15, SpriteSheet.sheet);
	public static Sprite playerSideStill = new Sprite(16, 2, 15, SpriteSheet.sheet);
	public static Sprite playerFrontStill = new Sprite(16, 4, 15, SpriteSheet.sheet);
	public static Sprite playerBackStill = new Sprite(16, 5, 15, SpriteSheet.sheet);
	public static Sprite playerMoveSide = new Sprite(16, 3, 15, SpriteSheet.sheet);
	
	//Projectiles
	public static Sprite projectile = new Sprite(16, 0, 2, SpriteSheet.sheet);
	public static Sprite projectile20 = new Sprite(16, 1, 2, SpriteSheet.sheet);
	public static Sprite projectile30 = new Sprite(16, 2, 2, SpriteSheet.sheet);
	public static Sprite projectile40 = new Sprite(16, 3, 2, SpriteSheet.sheet);
	public static Sprite projectile50 = new Sprite(16, 4, 2, SpriteSheet.sheet);
	public static Sprite projectileMax = new Sprite(16, 5, 2, SpriteSheet.sheet);



	// Potions
	public static Sprite speedPotionSprite = new Sprite(16, 0, 4, SpriteSheet.sheet);
	public static Sprite dmgPotionSprite = new Sprite(16, 1, 4, SpriteSheet.sheet);
	public static Sprite hpPotionSprite = new Sprite(16, 2, 4, SpriteSheet.sheet);
	public static Sprite manaPotionSprite = new Sprite(16, 3, 4, SpriteSheet.sheet);
	
	
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		
		
		pixels = new int[SIZE*SIZE];
		load();

	}
	
	public Sprite(int size, int colour){
		this.SIZE = size;
		pixels = new int[SIZE*SIZE];
		setColour(colour);
		
	}
	
	public void setColour(int colour){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = colour;
		}
	}

	private void load() {
		for (int y = 0; y < SIZE; y++){
			for (int x = 0; x < SIZE; x++){
				pixels[x+y*SIZE] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.getSIZE()];
				
			}			
		}
		
	}

	public int getSIZE() {
		return SIZE;
	}

	public static Sprite getGrass() {
		return grass;
	}

	public static Sprite getVoidSprite() {
		return voidSprite;
	}



}
