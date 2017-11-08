/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

import java.util.LinkedList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author eProw
 */
public abstract class Handler {
    LinkedList<GameObject> objects;
    public boolean[][] obstacles;
    public Player p;
    
    int renderX=0,renderY = 0;
    
    public Handler(){
        objects = new LinkedList<GameObject>();
    }
    
    public abstract void update(GameContainer gc, int delta);
    public abstract void render(GameContainer gc, Graphics g);
    
    public abstract void add(GameObject obj);
    public abstract void remove(GameObject obj);
    
}
