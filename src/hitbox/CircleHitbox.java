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
            CircleHitbox otherCircle = (CircleHitbox) other;
            double distance = Math.sqrt((otherCircle.x - x) * (otherCircle.x - x) + (otherCircle.y - y) * (otherCircle.y - y));
            if (distance <= r - otherCircle.r) {
                return true;
            } else {
                return false;
            }
        } else if (other instanceof RectHitbox) {
            // Circle-on-Rectangle collision
            // TODO
        }
        
        System.out.println("Unknown Collision");
        return false;
    }

}
