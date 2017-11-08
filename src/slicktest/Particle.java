/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author eProw
 */
public class Particle extends GameObject{
    Image img;
    public Particle(int _x, int _y){
        super(_x, _y);
        try {
            img = new Image("./environment/drop.png");
        } catch (SlickException ex) {

        }
    }
    
    public void update(GameContainer gc, int delta) {
        x+=velX;
        y+=velY;
        
        if(y < 600){
            velY+=1;
        }else{
            velY = 0;
            y=600;
        }
    }

    
    public void render(GameContainer gc, Graphics g) {
        img.draw(x,y);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,0,0);
    }
    
}
