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
        map.addHitbox(new RectHitbox(-4, 2, 8, 4));
        
        player = new Player(0f, 0f, 0f, 0f, map);
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
            glLoadIdentity();
            
            //Keyboard
            if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
                player.sety(player.gety() + .001f);
//                y += .001f;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
                player.sety(player.gety() - .001f);
//                y -= .001f;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                player.setx(player.getx() + .001f);
//                x += .001f;
            } else if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
                player.setx(player.getx() - .001f);
//                x -= .001f;
            }
//            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
//                z += .001f;
//            } else if (Keyboard.isKeyDown(Keyboard.KEY_S)){
//                z -= .001f;
//            }
            
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
        
//        drawCube();
        drawPlayer();
        
        Display.update();
    }
    
    private void drawPlayer() {
        glBegin(GL_QUADS);
            glColor3f(0f, 1f, 0f);
            
            float x = player.getx();
            float y = player.gety();
            
            glVertex3f(x + 1f, y + 1f, 0f);
            glVertex3f(x + 1f, y - 1f, 0f);
            glVertex3f(x - 1f, y - 1f, 0f);
            glVertex3f(x - 1f, y + 1f, 0f);
            
        glEnd();
    }
    
//    private void drawCube() {
//        
//        glBegin(GL_QUADS);
//            glColor3f(0.0f,1.0f,0.0f); // Set The Color To Green
//            glVertex3f( 1.0f, 1.0f,-1.0f); // Top Right Of The Quad (Top)
//            glVertex3f(-1.0f, 1.0f,-1.0f); // Top Left Of The Quad (Top)
//            glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left Of The Quad (Top)
//            glVertex3f( 1.0f, 1.0f, 1.0f); // Bottom Right Of The Quad (Top)
//            
//            glColor3f(1.0f,0.5f,0.0f); // Set The Color To Orange
//            glVertex3f( 1.0f,-1.0f, 1.0f); // Top Right Of The Quad (Bottom)
//            glVertex3f(-1.0f,-1.0f, 1.0f); // Top Left Of The Quad (Bottom)
//            glVertex3f(-1.0f,-1.0f,-1.0f); // Bottom Left Of The Quad (Bottom)
//            glVertex3f( 1.0f,-1.0f,-1.0f); // Bottom Right Of The Quad (Bottom)
//            
//            glColor3f(1.0f,0.0f,0.0f); // Set The Color To Red
//            glVertex3f( 1.0f, 1.0f, 1.0f); // Top Right Of The Quad (Front)
//            glVertex3f(-1.0f, 1.0f, 1.0f); // Top Left Of The Quad (Front)
//            glVertex3f(-1.0f,-1.0f, 1.0f); // Bottom Left Of The Quad (Front)
//            glVertex3f( 1.0f,-1.0f, 1.0f); // Bottom Right Of The Quad (Front)
//            
//            glColor3f(1.0f,1.0f,0.0f); // Set The Color To Yellow
//            glVertex3f( 1.0f,-1.0f,-1.0f); // Bottom Left Of The Quad (Back)
//            glVertex3f(-1.0f,-1.0f,-1.0f); // Bottom Right Of The Quad (Back)
//            glVertex3f(-1.0f, 1.0f,-1.0f); // Top Right Of The Quad (Back)
//            glVertex3f( 1.0f, 1.0f,-1.0f); // Top Left Of The Quad (Back)
//            
//            glColor3f(0.0f,0.0f,1.0f); // Set The Color To Blue
//            glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right Of The Quad (Left)
//            glVertex3f(-1.0f, 1.0f,-1.0f); // Top Left Of The Quad (Left)
//            glVertex3f(-1.0f,-1.0f,-1.0f); // Bottom Left Of The Quad (Left)
//            glVertex3f(-1.0f,-1.0f, 1.0f); // Bottom Right Of The Quad (Left)
//            
//            glColor3f(1.0f,0.0f,1.0f); // Set The Color To Violet
//            glVertex3f( 1.0f, 1.0f,-1.0f); // Top Right Of The Quad (Right)
//            glVertex3f( 1.0f, 1.0f, 1.0f); // Top Left Of The Quad (Right)
//            glVertex3f( 1.0f,-1.0f, 1.0f); // Bottom Left Of The Quad (Right)
//            glVertex3f( 1.0f,-1.0f,-1.0f); // Bottom Right Of The Quad (Right)
//        glEnd();
//    }
    
}