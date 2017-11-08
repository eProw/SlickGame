package slicktest;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Cinematic extends GameObject{
    
    Animation anim;
    String path;
    int duration;
    int numOfFrames;
    boolean loop;
    
    public Cinematic(int _x, int _y,String animPath,int _numOfFrames,int _duration,boolean _loop) {
        super(_x, _y);
        anim = new Animation();
        path = animPath;
        duration = _duration;
        numOfFrames = _numOfFrames;
        loop = _loop;
        
        generateAnim();
    }
    
    void generateAnim(){
        try {
            for(int i = 0;i < numOfFrames;i++){
                Image img = new Image(path+"/"+i+".png");
                img.setFilter(Image.FILTER_NEAREST);
                anim.addFrame(img.getScaledCopy(6), duration);
            }
        } catch (SlickException ex) {

        }
        anim.setLooping(loop);
    }

    public void update(GameContainer gc, int delta) {

    }

    public void render(GameContainer gc, Graphics g) {
        anim.draw(100,0);
    }

    public Rectangle getBounds() {
        return new Rectangle(0,0,0,0);
    }
    
}
