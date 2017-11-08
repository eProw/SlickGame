package slicktest;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Level0 extends Handler{
    public boolean end = false;
    double t;
    public Level0(){
        Cinematic eprow = new Cinematic(0,0,"./assets/cinematics/eprow",30,33,false);
        //CINEMATIC: POSITION X & Y, FRAMES PATH, NUM OF FRAMES, MILLIS PER FRAME, CAN LOOP
        objects.add(eprow);
        t = System.currentTimeMillis()+5000;
    }
    
    public void update(GameContainer gc, int delta){
        for(int i = 0; i < objects.size();i++){
            GameObject obj = objects.get(i);
            obj.update(gc, delta);
        }
        
        if(t < System.currentTimeMillis()){
            end = true;
        }
    }
    
    public void render(GameContainer gc, Graphics g){
        g.setBackground(new Color(0,0,0));
        
        //g.translate(-renderX,renderY);
        
        //////////////////////////
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
}
