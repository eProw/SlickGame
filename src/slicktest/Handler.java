/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

import java.util.LinkedList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author eProw
 */
public class Handler {
    private LinkedList<GameObject> objects;
    public boolean[][] obstacles;
    
    public Handler(){
        objects = new LinkedList<GameObject>();
    }
    
    public void update(GameContainer gc, int delta){
        for(GameObject obj:objects){
            obj.update(gc, delta);
        }
    }
    
    public void render(GameContainer gc, Graphics g){
        for(GameObject obj:objects){
            obj.render(gc, g);
        }
    }
    
    public void add(GameObject obj){
        objects.add(obj);
    }
    
    public void remove(GameObject obj){
        objects.remove(obj);
    }
    
    void generateObstacles(TiledMap map) throws SlickException{
        obstacles = new boolean[map.getWidth()][map.getHeight()];
        for(int x=0; x < map.getWidth();x++){
            for(int y=0; y < map.getHeight();y++){
                obstacles[x][y] = map.getTileId(x, y, 1)!=0;
            }
        }
        
        for(GameObject obj:objects){
            if(obj.getClass() == Block.class){
                obstacles[obj.getX()/32][(obj.getY()-140)/32] = true;
            }
        }
        
        /*for(int x=0; x < map.getWidth();x++){
            for(int y=0; y < map.getHeight();y++){
                if(obstacles[x][y])
                add(new Block(x*32,y*32,blockID.HARD));
            }
        }*/
    }
}
