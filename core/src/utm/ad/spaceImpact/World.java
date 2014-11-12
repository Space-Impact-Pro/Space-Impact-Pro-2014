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
	
	public final List<Enemy> enemies;
	
//	public Enemy enemy;
//	public Enemy enemy2;
	
	public final Ship ship;
	public final WorldListener listener;
	
	public float distanceSoFar;
	public int score;
	public int state;
	
	public World (WorldListener listener){
		this.ship = new Ship(5,1);
		this.listener=listener;
		this.enemies = new ArrayList<Enemy>();
		
		this.enemies.add(new Enemy(7,7));
		this.enemies.add(new Enemy(8,9));
		
		
		this.score =0;
		this.state = WORLD_STATE_RUNNING;
	}
	
	public void update(float deltaTime, float velocityX, float velocityY){
		updateShip(deltaTime, velocityX, velocityY);//todo add the movement variable
		updateEnemy(deltaTime);
		if (ship.state != Ship.SHIP_STATE_HIT) checkCollision();
	}
	
	public void updateShip(float deltaTime, float velocityX, float velocityY){
		ship.velocity.x = velocityX;
		ship.velocity.y = velocityY;  //////6.11/////
		ship.update(deltaTime);
	}
	
	private void updateEnemy (float deltaTime) {
		int len = enemies.size();
		for (int i = 0; i < len; i++) {
			Enemy enemy = enemies.get(i);
			enemy.update(deltaTime);
		}
	}
	
	
	private void checkCollision(){
		checkEnemyCollision();
	}
	
	private void checkEnemyCollision(){
		int len = enemies.size();
		for (int i = 0; i < len; i++) {
			Enemy enemy = enemies.get(i);
			if (enemy.bounds.overlaps(ship.bounds)) {
				ship.hitEnemy();
				state = WORLD_STATE_GAME_OVER;
			}
		}
	}
	
//	public void makeGameOver(){
//		state = WORLD_STATE_GAME_OVER;
//	}
}
