package hitbox;

public class CircleHitbox extends Hitbox {
    public float x;
    public float y;
    public float r;
    
    public CircleHitbox(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public boolean collidesWith(Hitbox other) {
        if (other instanceof CircleHitbox) {
            // Circle-on-Circle collision
            return isColliding(this, (CircleHitbox) other);
        } else if (other instanceof RectHitbox) {
            // Circle-on-Rectangle collision
            return isColliding((RectHitbox) other, this);
        }
        
        System.out.println("Unknown Collision");
        return false;
    }

}
