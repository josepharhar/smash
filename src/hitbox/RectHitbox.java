package hitbox;

public class RectHitbox extends Hitbox {
    public float x;
    public float y;
    public float width;
    public float height;
    
    public RectHitbox(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean collidesWith(Hitbox other) {
        if (other instanceof RectHitbox) {
            // Rectangle-on-Rectangle collison
            return isColliding(this, (RectHitbox) other);
        } else if (other instanceof CircleHitbox) {
            // Circle collision
            return isColliding(this, (CircleHitbox) other);
        }
        
        System.out.println("unknown hitbox collision");
        return false;
    }
}
