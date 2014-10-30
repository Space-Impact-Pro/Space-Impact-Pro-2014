package utm.ad.spaceImpact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Application.ApplicationType;




import utm.ad.spaceImpact.World.WorldListener;;


public class GameScreen extends ScreenAdapter {
	
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	
	SpaceImpactPro game;
	
	int state;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	World world;
	WorldListener worldListener;
	WorldRenderer renderer;
	Rectangle backBounds;
	Rectangle moveLeftRegion;
	Rectangle moveRightRegion;

	public GameScreen (SpaceImpactPro game) {
		this.game = game;
		
		state = GAME_READY;

		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		backBounds = new Rectangle(0, 0, 64, 64);
		touchPoint = new Vector3();
		
		world = new World(worldListener);
		renderer = new WorldRenderer(game.batcher, world);
		
		moveLeftRegion = new Rectangle(0,0, 40,40);
		moveRightRegion = new Rectangle(320-40,0, 40 ,40);
		
		backBounds = new Rectangle(160 - 150, 240 , 300, 32);

	}

	public void update (float deltaTime) {
		if (Gdx.input.justTouched()) {
			guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

			if (backBounds.contains(touchPoint.x, touchPoint.y)) {
				//Assets.playSound(Assets.clickSound);
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
		
		updateRunning(deltaTime);
	}
	
	private void updateRunning (float deltaTime){
		float velocity = 0;
		ApplicationType appType = Gdx.app.getType();
		
		if (appType == ApplicationType.Android)
		{
			boolean activeTouch = false;
			if (Gdx.input.isTouched()) {
				guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

				if (moveLeftRegion.contains(touchPoint.x, touchPoint.y)) {
//					Assets.playSound(Assets.clickSound);
//					state = GAME_PAUSED;
//					return;
					velocity = -5f;
				}
				if (moveRightRegion.contains(touchPoint.x, touchPoint.y)) {
//					Assets.playSound(Assets.clickSound);
//					state = GAME_PAUSED;
//					return;
					velocity = 5f;
				}
				activeTouch = true;
			}
			else{
				activeTouch = false;
			}
		}
		else{
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) velocity = -5f;
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) velocity = 5f;
		}
		world.update(deltaTime, velocity);
		
	}

	public void draw () {
		GL20 gl = Gdx.gl;
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
		guiCam.update();
		
		game.batcher.setProjectionMatrix(guiCam.combined);
		game.batcher.enableBlending();
		game.batcher.begin();
		
		
		game.batcher.end();
		
		/*
		game.batcher.setProjectionMatrix(guiCam.combined);
		game.batcher.disableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundRegionGameScreen, 0, 0, 320, 480);
		game.batcher.end();
		*/
		
		
//		game.batcher.enableBlending();
//		game.batcher.begin();
//		game.batcher.draw(Assets.highScoresRegion, 10, 360 - 16, 300, 33);

//		float y = 230;
//		for (int i = 4; i >= 0; i--) {
//			Assets.font.draw(game.batcher, highScores[i], xOffset, y);
//			y += Assets.font.getLineHeight();
//		}

//		game.batcher.draw(Assets.arrow, 0, 0, 64, 64);
//		game.batcher.end();
	}
	
	
	@Override
	public void render (float delta) {
		update(delta);
		draw();
	}
}
