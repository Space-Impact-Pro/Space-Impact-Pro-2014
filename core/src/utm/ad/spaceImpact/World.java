package utm.ad.spaceImpact;

import java.util.ArrayList;
import java.util.List;
//import java.util.Random;

//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.utils.Timer;
//import com.badlogic.gdx.utils.Timer.Task;

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
	
	public static enum WorldState{
		
	}
	public static final int WORLD_STATE_WAITING = -1;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;
	public static final int WORLD_STATE_FINISHED = 3;
	
	public final List<Enemy> enemies;
	public final List<Boss> boss;
	public List<Bullet> bullets;
	
	public final Ship ship;
	public final WorldListener listener;
	
	public float distanceSoFar;
	public int score;
	public int state;
	private int bulletDelay=0;
	
	public World (WorldListener listener){
		this.ship = new Ship(5,1);
		this.listener=listener;
		this.enemies = new ArrayList<Enemy>();
		this.boss = new ArrayList<Boss>();
		
		this.enemies.add(new Enemy(5,5));
		this.enemies.add(new Enemy(8,9));
		
		this.boss.add(new Boss(5,11));
		
		this.bullets = new ArrayList<Bullet>();
		
		
		this.score =0;
		this.state = WORLD_STATE_WAITING;
	}
	
	public void update(float deltaTime, float velocityX, float velocityY){
		
		updateShip(deltaTime, velocityX, velocityY);
		updateEnemy(deltaTime);
		updateBullet(deltaTime);
		
		if (ship.state != Ship.SHIP_STATE_HIT) checkCollision();
		if (ship.state == Ship.SHIP_STATE_FIRING) {
			bulletDelay++;
			if (bulletDelay ==8){ 
				bullets.add(ship.fire());
				bulletDelay =0;
			}
			
		}
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
	
	private void updateBullet (float deltaTime){
		int len = bullets.size();
		for (int i = 0; i < len; i++) {
			Bullet bullet = bullets.get(i);
			if (bullet.isDecayed()){
				bullets.remove(bullet);
				len--;
			}
			bullet.update(deltaTime);
		}
	}
	
	
	private void checkCollision(){
		checkEnemyCollision();
		checkBulletCollision();
	}
	
	private void checkEnemyCollision(){
		int len = enemies.size();
		for (int i = 0; i < len; i++) {
			Enemy enemy = enemies.get(i);
			if (enemy.bounds.overlaps(ship.bounds)) {
				ship.hitEnemy();
				state = WORLD_STATE_GAME_OVER;
			}
			if (boss.size() > 0){
				if (boss.get(0).bounds.overlaps(ship.bounds))
				{
					ship.hitEnemy();
					state = WORLD_STATE_GAME_OVER;
				}
			}
		}
	}
	
	private void checkBulletCollision(){	
		int enemyLength = enemies.size();
		int bulletLength = bullets.size();
		for (int i = 0; i < enemyLength; i++) {
			Enemy enemy = enemies.get(i);
			for (int j = 0; j < bulletLength; j++) {
				Bullet bullet= bullets.get(j);
				if (enemy.bounds.overlaps(bullet.bounds)) {
					bullet.hitEnemy(enemy);
					bullets.remove(bullet);
					if (enemy.getHp() == 0)
						enemies.remove(enemy);
					enemyLength--;
					bulletLength--;
				}
			}
		}
		
		if (this.boss.size() != 0){
			Boss boss = this.boss.get(0);
			for (int j = 0; j <bulletLength ; j++){
				Bullet bullet = bullets.get(j);
				if (boss.bounds.overlaps(bullet.bounds)) {
					bullet.hitEnemy(boss);
					bullets.remove(bullet);
					if (boss.getHp() == 0)
						this.boss.remove(boss);
					bulletLength--;
				}
			}
		}
	}
	
	
	
	
//	public void makeGameOver(){
//		state = WORLD_STATE_GAME_OVER;
//	}
}
