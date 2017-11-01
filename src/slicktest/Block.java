/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

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
    
    Animation qAnim;
    
    public Block(int _x, int _y,blockID _id) throws SlickException {
        super(_x, _y);
        id=_id;
        
        question = new Image[]{new Image("./environment/block1.gif"),
        new Image("./environment/block2.gif"),
        new Image("./environment/block3.gif")
        };
        
        switch(id.name()){
            case "QUESTION":
                qAnim = new Animation(question,200);
            break;
            
            default:
                qAnim = new Animation(new Image[]{new Image("./environment/x1.gif")},1000);
                break;
        }
    }

    public void update(GameContainer gc,int delta) {
        x+=velX;
        y+=velY;
        
        qAnim.update(delta);
    }

    public void render(GameContainer gc,Graphics g) {
        qAnim.draw(x,y);
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,32,32);
    }
}

enum blockID{
    QUESTION,
    BRICK,
    HARD
}
