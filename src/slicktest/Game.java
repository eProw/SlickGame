package slicktest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame{
    
    Handler h;
    
    Block b;
    Player player;
    
    public Game(){
        super("My first game");
    }
    
    TiledMap mapa;
    int x=1;

    public void init(GameContainer gc) throws SlickException {
        
        mapa = new TiledMap("./assets/map.tmx","assets");
        h = new Handler();
        //GENERAR OBSTACULOS
        try {
            new BlockGenerator(System.getProperty("user.dir")+"/src/assets/obstacles.txt",h);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        h.generateObstacles(mapa);
        player = new Player(3*32,17*30+13, h);
        h.add(player);
    }

    public void update(GameContainer gc, int i) throws SlickException {
       h.update(gc, i);
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setBackground(new Color(107,140,255));
        g.translate(-x,0);
        //g.scale(1.4f, 1.4f);
        mapa.render(0, 140);
        
        h.render(gc, g);
        g.drawString("0",(player.getX()/32)*32, (player.getY()/32)*32);
        g.drawString("0",(player.getX()/32)*32+32, (player.getY()/32)*32);
        
        //CAMARA SIGUE AL JUGADOR
        x+=((player.getX()-x)-300/2)*0.05f;
        
        if(x < 0){
            x=0;
        }
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(800, 600, false);
        app.setSmoothDeltas(true);
        app.setVSync(true);
        app.setShowFPS(true);
        app.setTargetFrameRate(60);
        app.start();
    }
}
