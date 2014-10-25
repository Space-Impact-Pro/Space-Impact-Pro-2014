package utm.ad.spaceImpact;


//used to define the ship and its method such as movement, fire, image...
public class Ship extends DynamicGameObject {
	public static final float SHIP_WIDTH=0.8f;
	public static final float SHIP_HEIGHT=0.8f;
	
	int state;
	int stateTime;
	
	
	public Ship(float x, float y){
		super(x,y, SHIP_WIDTH, SHIP_HEIGHT);
	}
	
	public void update(float deltaTime){
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;
		
		stateTime += deltaTime;
	}
	
	public void fire(){
		
	}
	
	
}
