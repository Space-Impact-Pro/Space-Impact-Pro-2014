package utm.ad.spaceImpact;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import utm.ad.spaceImpact.World.WorldListener;


public class GameScreen extends ScreenAdapter {
	
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;
	
	SpaceImpactPro game;
	
	int state;
	float score;
	OrthographicCamera guiCam;
	Vector3 touchPoint;
	World world;
	WorldListener worldListener;
	WorldRenderer renderer;
	Rectangle backBounds;
	
	Rectangle moveLeftRegion;
	Rectangle moveRightRegion;
	
	Rectangle moveUpRegion;
	Rectangle moveDownRegion;
	
	private String name;
	private boolean doneInput;
	private boolean done = false;


	public GameScreen (SpaceImpactPro game) {
		this.game = game;
		done = false;
		
		state = GAME_READY;

		guiCam = new OrthographicCamera(320, 480);
		guiCam.position.set(320 / 2, 480 / 2, 0);
		backBounds = new Rectangle(0, 0, 64, 64);
		touchPoint = new Vector3();
		
		world = new World(worldListener);
		renderer = new WorldRenderer(game.batcher, world);
		
		moveLeftRegion = new Rectangle(0,0, 40,40);
		moveRightRegion = new Rectangle(320-40,0, 40 ,40);
		
		moveUpRegion = new Rectangle(40,80,160,300);
		moveDownRegion = new Rectangle(40,0,160,80);
		
		backBounds = new Rectangle(300, 460 , 20, 20);
		
		score = 0;

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
		//////6.11/////
		switch (state){
			case GAME_READY:
				updateReady();
				break;
			case GAME_RUNNING:
				updateRunning(deltaTime);
				break;
			case GAME_OVER:
				updateGameOver();
		}
		
	}
	
	private void updateReady () {
		if (Gdx.input.justTouched()) {
			state = GAME_RUNNING;
		}
		if (Gdx.input.isKeyPressed(Keys.B))
			state = GAME_RUNNING;
	}
	
	private void updateRunning (float deltaTime){
		float velocityX = 0;
		float velocityY = 0;  ////////6.11/////
		
		float posY = 0, h = 0;
		ApplicationType appType = Gdx.app.getType();
		posY = world.ship.getPositionY() * 34;//GET THE ship position in pixels
		world.ship.state = 0;
//		if (appType == ApplicationType.Android)
//		{
			boolean activeTouch = false;
			if (Gdx.input.isTouched()) {
				guiCam.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));
				
				
				if (moveLeftRegion.contains(touchPoint.x, touchPoint.y)) {
//					Assets.playSound(Assets.clickSound);
//					state = GAME_PAUSED;
//					return;
					velocityX = -5f;
				}
				if (moveRightRegion.contains(touchPoint.x, touchPoint.y)) {
//					Assets.playSound(Assets.clickSound);
//					state = GAME_PAUSED;
//					return;
					velocityX = 5f;
				}
				if (moveUpRegion.contains(touchPoint.x, touchPoint.y)) {//////6.11/////
//					Assets.playSound(Assets.clickSound);
//					state = GAME_PAUSED;
//					return;
					velocityY = 5f;
					moveUpRegion.set(40,posY,240,470-posY);
				}
				if (moveDownRegion.contains(touchPoint.x, touchPoint.y)) {//////6.11/////
//					Assets.playSound(Assets.clickSound);
//					state = GAME_PAUSED;
//					return;
					velocityY = -5f;
					moveDownRegion.set(40,0,240,posY);
				}
				
				activeTouch = true;
			}
			else{
				activeTouch = false;
			}
//		}
//		else{
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {velocityX = -5f; }
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {velocityX = 5f; }
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP)) {velocityY = 5f;}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {velocityY = -5f;}
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {world.ship.state = Ship.SHIP_STATE_FIRING;}
		score = score + 1;
		System.out.println(score); 	//debugging
//		}
		
		world.update(deltaTime, velocityX, velocityY);
		///later on will be changed to collision for changing the state to GO
		
		if (world.state == World.WORLD_STATE_GAME_OVER){
			state = GAME_OVER;
		}
		
		
	}
	
	private void updateGameOver () {//////6.11/////
		if (Gdx.input.justTouched()) {
					
			String title = "Name";
			String initialText = "Name here";
			//////////////////////////////////////////////////////////////////////
			Gdx.input.getTextInput(new TextInputListener() {  //get the name input
				
				String message;
				
	            
	            @Override
	            public void input(String text) {
	            name = text;
	            doneInput =true;
	            }
	           
	            @Override
	            public void canceled() {
	            doneInput =true;
	            }
			}, title, initialText);
				
			}
			if (doneInput){
				SpaceImpactPro.highscore.addScore(name, (int)score);  //add name to highscore
				doneInput = false;
				Timer.schedule(new Task(){		//delay the screen switch for 1 second
				    @Override
				    public void run() {
						game.setScreen(new MainMenuScreen(game));
						
				    }
				},1);
				
			}
		//if (Gdx.input.justTouched()) {
		
//			if(!done){
//				done = true;
//				String title = "Name";
//				String initialText = "Name here";
//				
//				Gdx.input.getTextInput(new TextInputListener() {	//get the name
//					
//					String message;
//					
//		            
//		            @Override
//		            public void input(String text) {
//		            name = text;
//		            doneInput =true;
//		            }
//		           
//		            @Override
//		            public void canceled() {
//		            doneInput =true;
//		            }
//		     }, title, initialText);
//			
//		}
//		//}
//		if (doneInput){			//add name to highscore
//			
//			SpaceImpactPro.highscore.addScore(name, (int)score);
//			if (Gdx.input.justTouched()){
//			game.setScreen(new MainMenuScreen(game));
//	    	
//			//Timer.schedule(new Task(){
//			    //@Override
//			    //public void run() {
//			    	
//			    }
//			doneInput = false;
//			//}, 2);
//			
			
//		}
	}
	
	private void presentGameRunning(){
	}
	
	private void presentGameOver(){//////6.11///// present the game over item
		Assets.font.draw(game.batcher,"GAME OVER" , 60, 350);
		if (doneInput)
			Assets.font.draw(game.batcher,name +"    "+score , 60, 310);
	}

	public void draw () {
		GL20 gl = Gdx.gl;
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
		guiCam.update();
		
		game.batcher.setProjectionMatrix(guiCam.combined);
		game.batcher.enableBlending();
		game.batcher.begin();
		
		switch (state){
		case GAME_RUNNING:
			presentGameRunning();
			break;
		case GAME_OVER:
			presentGameOver();
			break;
		}
		
		game.batcher.end();
		
		/*
		game.batcher.setProjectionMatrix(guiCam.combined);
		game.batcher.disableBlending();
		game.batcher.begin();
		game.batcher.draw(Assets.backgroundRegionGameScreen, 0, 0, 320, 480);
		game.batcher.end();
		*/
	}
	
	
	@Override
	public void render (float delta) {
		update(delta);
		draw();
	}
}
