/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Block extends GameObject{
    blockID id;
    Image[] question;
    Image brick;
    Handler h;
    int finalY;
    public boolean exists=true;
    
    Animation anim;
    
    public Block(int _x, int _y,blockID _id,Handler h) throws SlickException {
        super(_x, _y);
        id=_id;
        this.h = h;
        finalY = y;
        question = new Image[]{new Image("./environment/block1.gif"),
        new Image("./environment/block2.gif"),
        new Image("./environment/block3.gif")
        };
        
        updateImage();
    }

    public void update(GameContainer gc,int delta) {
        x+=velX;
        y+=velY;
        
        if(y < finalY){
            velY+=1;
        }else{
            velY = 0;
            y=finalY;
        }
        
        if(h.p != null){
            if(intersects(h.p)){
                if(h.p.y>y+32/2){
                    switch(id.name()){
                        case "QUESTION":
                            velY-=3;
                            id = blockID.HARD;
                            break;
                            
                        case "BRICK":
                            
                            exists=false;
                            h.obstacles[x/32][(y-140)/32]=false;
                            break;
                    }
                    //id = blockID.HARD;
                    try {
                        updateImage();
                    } catch (SlickException ex) {

                    }
                }
            }
        }
        
        anim.update(delta);
    }

    public void render(GameContainer gc,Graphics g) {
        if(exists)
        anim.draw(x,y);
    }
    
    public void updateImage() throws SlickException{
        switch(id.name()){
            case "QUESTION":
                anim = new Animation(question,200);
            break;
            
            case "HARD":
                anim = new Animation(new Image[]{new Image("./environment/x3.gif")},1000);
            break;
            
            case "BRICK":
                anim = new Animation(new Image[]{new Image("./environment/x1.gif")},1000);
            break;
            
            default:
                anim = new Animation(new Image[]{new Image("./environment/x1.gif")},1000);
                break;
        }
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x+1,y,30,40);
    }
}

enum blockID{
    QUESTION,
    BRICK,
    HARD
}
