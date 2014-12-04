package utm.ad.spaceImpact;

public class Boss extends Enemy {
	public static final float ENEMY_WIDTH = 3;
	public static final float ENEMY_HEIGHT = 3;
	public static final float ENEMY_VELOCITY = 0;
	
	public Boss(float x, float y){
		super(x,y, ENEMY_WIDTH, ENEMY_HEIGHT);
		hp = 100;
	}
	
	
}
