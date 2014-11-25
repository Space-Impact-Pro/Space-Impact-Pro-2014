package utm.ad.spaceImpact;




//used to define the ship and its method such as movement, fire, image...
public class Ship extends DynamicGameObject {
	public static final float SHIP_WIDTH=0.8f;
	public static final float SHIP_HEIGHT=0.8f;
	
	public static final int SHIP_STATE_HIT = 2;
	public static final int SHIP_STATE_FIRING = 3;
	
	public static int selectedShip;
	
	int state;
	int stateTime;
	
	
	
	
	public Ship(float x, float y){
		super(x,y, SHIP_WIDTH, SHIP_HEIGHT);
		selectedShip = 1;
	}
	
	public void update(float deltaTime){
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;
		
		stateTime += deltaTime;
		if (position.x <0.5) position.x =0.5f;
		if (position.x > World.WORLD_WIDTH-0.5) position.x =  World.WORLD_WIDTH- 0.5f;
		if (position.y < 0.5) position.y = 0.5f;
		if (position.y > World.WORLD_HEIGHT/20-0.5) position.y =  World.WORLD_HEIGHT/20- 0.5f;
	}
	
	public Bullet fire(){
		Bullet b = new Bullet(position.x - SHIP_WIDTH /2, position.y + SHIP_HEIGHT /2);
		return b;
	}
	
	public void hitEnemy(){
		velocity.set(0,0);
		state = SHIP_STATE_HIT;
	}
	
	public float getPositionX(){
		return position.x;
	}
	
	public float getPositionY(){
		return position.y;
	}
	
}
