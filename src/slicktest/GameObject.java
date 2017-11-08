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
    
    //Colisiones
    boolean topLeft,topRight,bottomLeft,bottomRight,ground = true;
    int destX, destY,tempX,tempY;
    
    Image sprite;
    
    public GameObject(int _x,int _y){
        x = _x;
        y = _y;
    }
    
    public abstract void update(GameContainer gc,int delta);
    
    public abstract void render(GameContainer gc,Graphics g);
    
    void calculateCorners(int x,int y,Handler h){
        int top = (y-140)/32,bottom = ((y-140+32)/32), left = x/32, right = (x+32-1)/32;
        if(bottom < 0){
            bottom = 0;
        }
        
        if(top < 0){
            top = 0;
        }
        
        topLeft = h.obstacles[left][top];
        topRight = h.obstacles[right][top];
        bottomLeft = h.obstacles[left][bottom];
        bottomRight = h.obstacles[right][bottom];
    }
    
    void collisions(Handler h){ //VERSION NUMERO 0.0.1 
        int columna = (y-140)/32,fila = x/32; 
        
        calculateCorners(x,destY,h);
        if(velY > 0){
            if(bottomLeft||bottomRight){
                velY=0;
                tempY = columna*32+140+31;
                ground = true;
            }else{
                ground = false;
                tempY+=velY;
            }
        }
        if(velY < 0){
            if(topLeft||topRight){
                velY = 0;
                tempY = columna*32+140;
            }else{
                tempY+=velY;
            }
        }
        
        calculateCorners(destX,y,h);
        
        if(velX < 0){
            if(bottomLeft||topLeft){
                velX=0;
                tempX = fila*32;
            }else{
                tempX+=velX;
            }
        }
        if(velX > 0){   
            if(bottomRight||topRight){
                velX=0;
                tempX = (destX-tempX)>32?(fila)*32:tempX;
            }else{
                tempX+=velX;
            }
        }
        
        destX = tempX;
        destY = tempY;
    }
    
    public boolean intersects(GameObject obj){
        return getBounds().intersects(obj.getBounds());
    }
    
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
