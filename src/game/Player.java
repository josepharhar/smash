package game;

import hitbox.Hitbox;
import hitbox.RectHitbox;

public class Player {
    
    // Location and Size
    public RectHitbox hitbox;
    
    // Velocity
    private float vx;
    private float vy;
    
    // Acceleration (gravity n stuff)
    private float ax;
    private float ay;
    
    Map map;
    
    
    public Player(float x, float y, float width, float height, Map map) {
        this.map = map;
        vx = 0;
        vy = 0;
        ax = -9.8f;
        ay = 0;
        hitbox = new RectHitbox(x, y, width, height);
    }
    
    public float getx() {
        return hitbox.x;
    }
    
    public float gety() {
        return hitbox.y;
    }
    
    public void setx(float x) {
        hitbox.x = x;
    }
    
    public void sety(float y) {
        hitbox.y = y;
    }
    
    public void setvx(float vx) {
        this.vx = vx;
    }
    
    public void setvy(float vy) {
        this.vy = vy;
    }
    
    // apply acceleration or something?
    public void update() {
        float newx = getx() + vx;
        float newy = gety() + vy;
        
        //making a new hitbox per character per frame probably wont cause too much lag
        RectHitbox newHitbox = new RectHitbox(newx, newy, hitbox.width, hitbox.height);
        
        Hitbox collision = map.checkCollision(newHitbox);
        
        if (collision == null) {
            hitbox = newHitbox;
        } else {
            System.out.println("player colliding with map: " + System.currentTimeMillis());
            
            // If this player moved according to its velocity,
            // then it would collide with the hitbox "collision."
            // We have to update the velocity accordingly and
            // move our hitbox to be adjacent to "collision"
            
            //figure out which side of the hitbox we are hitting
            
            //TODO for now, we will assume that we are colliding with a rectangle
            RectHitbox rectCollision = (RectHitbox) collision;
            
            
        }
    }
}
