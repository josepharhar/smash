package game;

import hitbox.Hitbox;

import java.util.ArrayList;
import java.util.List;

// Contains information (hitboxes) about the structures that appear on the map
public class Map {
    
    List<Hitbox> hitboxes;
    
    public Map() {
        hitboxes = new ArrayList<Hitbox>();
    }
    
    public void addHitbox(Hitbox hitbox) {
        hitboxes.add(hitbox);
    }
    
    // This is called to check if a given hitbox is colliding with the map.
    // If it isnt colliding, it returns null
    // If it is colliding, it returns the piece of map that its colliding with
    public Hitbox checkCollision(Hitbox other) {
        for (Hitbox hitbox : hitboxes) {
            if (hitbox.collidesWith(other)) {
                return hitbox;
            }
        }
        return null;
    }
}
