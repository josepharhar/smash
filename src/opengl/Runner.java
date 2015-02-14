package opengl;

import game.*;
import hitbox.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;

//This import allows calls without the GL11.
import static org.lwjgl.opengl.GL11.*;

public class Runner {
    
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;
    
//    private float x = 0;
//    private float y = 0;
//    private float z = 0;
    
    private Player player;
    private Map map;

    double downx = 0.0;
    double downy = 0.0;
    boolean pressed = false;
    float diffx = 0.0f;
    float diffy = 0.0f;
    float rotation = 0.0f;
    
    public Runner() {
        map = new Map();
        map.hitboxes.add(new RectHitbox(-4f, -4f, 8f, 4f));
        
        player = new Player(-1f, 4f, 2f, 2f, map);
    }
    
    public static void main(String[] args) {
        try {
            new Runner().run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception {
        init();
        loop();
    }
    
    private void init() throws Exception {
        //LWJGL 2 setup
        DisplayMode displayMode = new DisplayMode(GAME_WIDTH, GAME_HEIGHT);
        Display.setDisplayMode(displayMode);
        Display.setResizable(true);
        Display.create();
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(-5, 5, -5, 5, -5, 5);
        glMatrixMode(GL_MODELVIEW);
    }
    
    private void loop() {
        
        while (!Display.isCloseRequested()) {

            player.update();
            
            glLoadIdentity();
            
            //Keyboard
            if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                player.setvy(.001f);
            } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                player.setvy(-.001f);
            } else {
                player.setvy(0f);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                player.setvx(.001f);
            } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
                player.setvx(-.001f);
            } else {
                player.setvx(0f);
            }
            
            //Mouse
            if (Mouse.isButtonDown(0)) {
                if (!pressed) {
                    // Just clicked down for the first time, need to store this cursor location
                    pressed = true;
                    downx = Mouse.getX();
                    downy = Mouse.getY();
                } else {
                    // Being held down, need to update rotation relative to init location
                    diffy = (float) -(Mouse.getX() - downx);
                    diffx = (float) -(Mouse.getY() - downy);
                    
                    //total distance cursor has traveled to be used for the rotation
                    double distance = Math.sqrt(diffx * diffx + diffy * diffy);
                    //20 pixels will be 180 degrees of rotation
                    //glRotatef uses DEGREES, NOT RADIANS
                    rotation = (float) (distance / 3.0);
                }
            } else {
                pressed = false;
            }
            
            //Draw
            draw();
        }
    }
    
    private void draw() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
//        glTranslatef(x, y, z);
        
        
//        System.out.println("Rotation: " + rotation);
//        System.out.println("diffx: " + diffx);
//        System.out.println("diffy: " + diffy);
        glRotatef(rotation, diffx, -diffy, 0f);
        
        drawMap();
        drawPlayer();
        
        Display.update();
    }
    
    private void drawPlayer() {
        glBegin(GL_QUADS);
            glColor3f(0f, 1f, 0f);
            
            float x1 = player.getx();
            float y1 = player.gety();
            float x2 = x1 + player.hitbox.width;
            float y2 = y1 + player.hitbox.height;
            
            glVertex3f(x1, y1, 0f);
            glVertex3f(x1, y2, 0f);
            glVertex3f(x2, y2, 0f);
            glVertex3f(x2, y1, 0f);
            
        glEnd();
    }
    
    private void drawMap() {
        for (Hitbox hitbox : map.hitboxes) {
            if (hitbox instanceof RectHitbox) {
                RectHitbox rect = (RectHitbox) hitbox;
                glBegin(GL_QUADS);
                    glColor3f(1f, 0f, 0f);
                    glVertex3f(rect.x, rect.y, 0f);
                    glVertex3f(rect.x + rect.width, rect.y, 0f);
                    glVertex3f(rect.x + rect.width, rect.y + rect.height, 0f);
                    glVertex3f(rect.x, rect.y + rect.height, 0f);
                glEnd();
            }
        }
    }
    
}