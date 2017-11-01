/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author eProw
 */
public abstract class GameObject {
    int x,y, velX,velY;
    
    Image sprite;
    
    public GameObject(int _x,int _y){
        x = _x;
        y = _y;
    }
    
    public abstract void update(GameContainer gc,int delta);
    
    public abstract void render(GameContainer gc,Graphics g);
    
    public abstract Rectangle getBounds();
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int i){
        x=i;
    }
    
    public void setY(int i){
        y = i;
    }
    
    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
