package hitbox;

public abstract class Hitbox {
    public abstract boolean collidesWith(Hitbox other);
    
    // Rectangle-on-Rectangle collision
    public static boolean isColliding(RectHitbox one, RectHitbox two) {
        return one.x <= two.x + two.width && one.x + one.width >= two.x &&
               one.y <= two.y + two.height && one.y + one.height >= two.y;
    }
    
    // Circle-on-Circle collision
    public static boolean isColliding(CircleHitbox one, CircleHitbox two) {
        double distance = Math.sqrt((two.x - one.x) * (two.x - one.x) + (two.y - one.y) * (two.y - one.y));
        if (distance <= one.r + two.r) {
            return true;
        }
        return false;
    }
    
    // Rectangle-on-Circle collision
    public static boolean isColliding(RectHitbox rect, CircleHitbox circle) {
        //TODO
        return false;
    }
}
