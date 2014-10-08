package utm.ad.spaceImpact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Rectangle;

//import com.badlogicgames.superjumper.Assets;
//import com.badlogicgames.superjumper.GameScreen;
//import com.badlogicgames.superjumper.HelpScreen;
//import com.badlogicgames.superjumper.HighscoresScreen;
//import com.badlogicgames.superjumper.Settings;
//import com.badlogicgames.superjumper.Assets;
//import com.badlogicgames.superjumper.Settings;


public class MainMenuScreen extends ScreenAdapter {
	SpaceImpactPro game;
	OrthographicCamera guiCam; //screen phone view
	
	
	Rectangle newGameBound;
	Rectangle selectModeBound;
	Rectangle selectShipBound;
	//Rectangle gameExit;
	Vector3 touchPoint;
	
	public MainMenuScreen (SpaceImpactPro game){
		this.game = game;
		
		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		newGameBound = new Rectangle(160 - 150, 240 + 18, 300, 32);  //setting region for touch //(kiri. bawah, width, height)
		selectShipBound = new Rectangle(160 - 150, 240 - 18, 300, 32);
		selectModeBound = new Rectangle(160 - 150, 240 - 18 - 36, 300, 32);
		
		
		touchPoint = new Vector3();
	}
	
	public void update() {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (newGameBound.contains(touchPoint.x, touchPoint.y)) {
//				Assets.playSound(Assets.clickSound);
				game.setScreen(new GameScreen(game));
				return;
			}
			if (selectShipBound.contains(touchPoint.x, touchPoint.y)) {
//				Assets.playSound(Assets.clickSound);
				game.setScreen(new SelectShipScreen(game));
				return;
			}
			if (selectModeBound.contains(touchPoint.x, touchPoint.y)) {
//				Assets.playSound(Assets.clickSound);
				game.setScreen(new SelectModeScreen(game));
				return;
			}
//			if (soundBounds.contains(touchPoint.x, touchPoint.y)) {
//				Assets.playSound(Assets.clickSound);
//				Settings.soundEnabled = !Settings.soundEnabled;
//				if (Settings.soundEnabled)
//					Assets.music.play();
//				else
//					Assets.music.pause();
//			}
		}
	}
	
	public void draw () {
		GL20 gl = Gdx.gl;
		gl.glClearColor(1, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);

		game.batcher.disableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundRegionMenu, 0, 0, 320, 480);
		game.batcher.end();

//		game.batcher.enableBlending();
//		game.batcher.begin();
//		game.batcher.draw(Assets.logo, 160 - 274 / 2, 480 - 10 - 142, 274, 142);
//		game.batcher.draw(Assets.mainMenu, 10, 200 - 110 / 2, 300, 110);
//		game.batcher.draw(Settings.soundEnabled ? Assets.soundOn : Assets.soundOff, 0, 0, 64, 64);
//		game.batcher.end();	
	}
	
	@Override
	public void render (float delta) {
		update();
		draw();
	}
}
