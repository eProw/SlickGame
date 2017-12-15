package slicktest;

import java.util.LinkedList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.tiled.TiledMap;
import slicktest.Block;
import slicktest.GameObject;
import slicktest.Particle;
import slicktest.Player;
import slicktest.GameDialogue.*;

public class Level1 extends Handler{
    
    TiledMap mapa;
    
    Phase phase;
    int dialogueNum = 0;
    
    Dialogue dialogue;
    
    int i = 0;
    String printed="";
    double delay=20,time=0;
     Sound sound;
     
    int dialogNum = 0;
    
    public Level1(TiledMap _mapa){
        phase = Phase.dialogue;
        mapa = _mapa;
        dialogue = new Dialogue();
        
         try {
            sound = new Sound("./Sound/dialogTalk.ogg");
        } catch (SlickException ex) {
        }
    }
    
    public void update(GameContainer gc, int delta){
        for(int i = 0; i < objects.size();i++){
            GameObject obj = objects.get(i);
            obj.update(gc, delta);
        
            if(obj.getClass() == Block.class){
                if(!((Block)obj).exists){
                    fireworks(obj.x,obj.y);
                    remove(obj);
                }
            }
            
            if(obj.getClass() == Player.class){
                    renderX+=((obj.getX()-renderX)-300/2)*0.05f;
                    
                    if(obj.getX() > 400){
                        obj.setX(400);
                        
                        dialogue.done = false;
                        dialogNum = 1;
                        phase = Phase.dialogue;
                    }
            }
        }
        
        if(renderX < 0){
            renderX=0;
        }
    }
    
    public void render(GameContainer gc, Graphics g){
        if(phase == Phase.game){
            g.setBackground(new Color(107,140,255));
            g.translate(-renderX,renderY);
            mapa.render(0,140);
            //////////////////////////
            for(GameObject obj:objects){
                obj.render(gc, g);
            }
        }
        
        if(phase == Phase.dialogue){
            g.setBackground(Color.black);
            if(!dialogue.done){
            dialogue.print(g,gc,DScript.string[dialogNum]);
            }else{
                phase= Phase.game;
            }            
        }
        if(phase == Phase.g_d){
            
        }
    }
    
    public void add(GameObject obj){
        if(obj.getClass() == Player.class)
            p = (Player) obj;
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
    }
    
    void fireworks(int x,int y){
        Particle p1,p2,p3,p4;

                            p1 = new Particle(x,y);
                            p2 = new Particle(x+32,y);
                            p3 = new Particle(x,y+32);
                            p4 = new Particle(x+32,y+32);
                            
                            p1.velX=-3;
                            p1.velY=-10;
                            
                            p2.velX=3;
                            p2.velY=-5;
                            
                            p3.velX=-3;
                            p3.velY=-3;
                            
                            p4.velX=3;
                            p4.velY=-3;
                            add(new Particle(x,y));
                            add(p2);
                            add(p3);
                            add(p4);
    }
    
    enum Phase{
        game,
        dialogue,
        g_d
    }
}
