package utm.ad.spaceImpact;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class World {
	public interface WorldListener {
		public void moveLeft ();
		public void moveRight ();
		public void moveUp ();
		public void moveDown ();
		
		public void fire();
		
		public void hit ();
	}
	
	public static final float WORLD_WIDTH = 10;
	public static final float WORLD_HEIGHT = 15 * 20;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	
	public final Ship ship;
	public final WorldListener listener;
	
	public float distanceSoFar;
	public int score;
	public int state;
	
	public World (WorldListener listener){
		this.ship = new Ship(5,1);
		this.listener=listener;
		
		this.score =0;
		this.state = WORLD_STATE_RUNNING;
	}
	
	public void update(float deltaTime, float velocityX){
		updateShip(deltaTime, velocityX);//todo add the movement variable
	}
	
	public void updateShip(float deltaTime, float velocityX){
		ship.velocity.x = velocityX;
		ship.update(deltaTime);
	}
}
