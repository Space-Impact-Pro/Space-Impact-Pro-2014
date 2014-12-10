package utm.ad.spaceImpact;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//todo import all assets, and make texture map instead of single page of background
public class Assets {

//	public static Texture background;
//	public static TextureRegion backgroundRegion;
//	
//	public static Texture background2;
//	public static TextureRegion backgroundRegion2
    
	public static Texture arrows;
	public static TextureRegion arrowLeft;
	public static TextureRegion arrowRight;
	public static TextureRegion arrowDown;
	public static TextureRegion arrowUp;
	public static TextureRegion arrowBack;
	
	public static Texture backgroundFirstMenu;
	public static TextureRegion backgroundRegionFirstMenu;
	
	public static Texture backgroundMenu;
	public static TextureRegion backgroundRegionMenu;
	
	public static Texture backgroundShipSelect;
	public static TextureRegion backgroundRegionShipSelect;
	
	public static Texture backgroundModeSelect;
	public static TextureRegion backgroundRegionModeSelect;
	
	public static Texture backgroundShipSelect1;
	public static TextureRegion backgroundRegionShipSelect1;
	
	public static Texture backgroundGameScreen;
	public static TextureRegion backgroundRegionGameScreen;
	
	public static Texture background;
	public static TextureRegion backgroundRegion;
	
	public static Texture menuBackground;
	public static TextureRegion menuBackgroundRegion;
	
	public static Texture ship_image;
	public static TextureRegion ship_imageRegion;
	
	public static Texture monster;
	public static TextureRegion monsterRegion;
	
	public static Texture bullet;
	public static TextureRegion bulletRegion;
	
	public static BitmapFont font;
	
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	public static void load () {
		arrows = loadTexture("data/arrows.png");
		arrowDown = new TextureRegion(arrows, 0, 96, 32,32);
		arrowLeft = new TextureRegion(arrows, 31, 96, 32,32);
		arrowRight = new TextureRegion(arrows, 63, 96, 32,32);
		arrowUp = new TextureRegion(arrows, 95, 96, 32,32);
		arrowBack = new TextureRegion(arrows, 0, 64, 32,32);
		
		backgroundFirstMenu = loadTexture("data/Guest.png");	//load image to texture
		backgroundRegionFirstMenu = new TextureRegion(backgroundFirstMenu, 0, 0, 320, 480);		//get the region from the texture
		
		backgroundMenu = loadTexture("data/MainMenu.png");
		backgroundRegionMenu = new TextureRegion(backgroundMenu, 0, 0, 320, 480);
		
		backgroundShipSelect = loadTexture("data/SelectShip-1.png");
		backgroundRegionShipSelect = new TextureRegion(backgroundShipSelect, 0, 0, 320, 480);
	
		backgroundModeSelect = loadTexture("data/ModeSelect.png");
		backgroundRegionModeSelect = new TextureRegion(backgroundModeSelect, 0, 0, 320, 480);
		
		backgroundGameScreen = loadTexture("data/NewGameNext.png");
		backgroundRegionGameScreen = new TextureRegion(backgroundGameScreen,  0, 0, 320, 480);
		
		background = loadTexture("data/GameBG1.jpg");
		backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
		
		menuBackground = loadTexture("data/Highscore.png");
		menuBackgroundRegion = new TextureRegion(menuBackground, 0, 0, 320, 480);
		
		monster = loadTexture("data/enemy/monster.png");
		monsterRegion = new TextureRegion(monster,0,0,128,156);
		
		bullet = loadTexture("data/bullet.png");
		bulletRegion = new TextureRegion(bullet,0,0,16,16);
		
		loadShip(0);
		
		font = new BitmapFont(Gdx.files.internal("font/calibri.fnt"), Gdx.files.internal("font/calibri.png"), false);
		font.setScale(0.75f, 0.75f);
		///// ship implementation in game screen////  --->> moved to method below
//		ship_image = loadTexture("data/ship_box.png");
//		ship_imageRegion = new TextureRegion(ship_image,  0, 0, 32, 32);
		
	}
	
	public static void loadShipSelect (int index) { //change the space ship selection screen background based on passed integer index
		if (index == 0)
			backgroundShipSelect = loadTexture("data/SelectShip-1.png");
		else if (index == 1)
			backgroundShipSelect = loadTexture("data/SelectShip-2.png");
		else if (index == 2)
			backgroundShipSelect = loadTexture("data/SelectShip-3.png");
		else if (index == 3)
			backgroundShipSelect = loadTexture("data/SelectShip-4.png");   //13 NOV
			
			
			backgroundRegionShipSelect = new TextureRegion(backgroundShipSelect, 0, 0, 320, 480);
		
	}
	
	public static void loadShip(int index){
		if (index ==0)
			ship_image = loadTexture("data/ship/ship1.png");
		else if (index ==1)
			ship_image = loadTexture("data/ship/ship2.png");
		else if (index ==2)
			ship_image = loadTexture("data/ship/ship3.png");
		else if (index ==3)
			ship_image = loadTexture("data/ship/ship4.png");
		if (index <= 1)
			ship_imageRegion = new TextureRegion(ship_image, 0, 0, 32, 32);
		else
			ship_imageRegion = new TextureRegion(ship_image, 0, 0, 128, 128);
			
	}
	
	
}

