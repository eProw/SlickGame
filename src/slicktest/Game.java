package slicktest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

public class Game extends BasicGame{
    Player player;
    int sceneNumber;
    
    LinkedList<Handler> gameScenes;
    
    Level0 menu;
    Level1 level;
    
    
    public Game(){
        super("My first game");
        gameScenes = new LinkedList<Handler>();
        sceneNumber = Scenes.MainMenu.ordinal();
    }
    
    TiledMap mapa;
    int x=1;

    public void init(GameContainer gc) throws SlickException {
        menu = new Level0();
        
        //CONSTRUIR NIVEL PRINCIPAL
            setupLevel();

        
        gameScenes.add(menu);
        gameScenes.add(level);
    }

    public void update(GameContainer gc, int i) throws SlickException {
       gameScenes.get(sceneNumber).update(gc, i);
       
       if(menu.end){
           sceneNumber = Scenes.Level.ordinal();
       }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        gameScenes.get(sceneNumber).render(gc, g);
    }
    
    void setupLevel(){
        try{
            mapa = new TiledMap("assets/map.tmx","assets");
            level = new Level1(mapa);
            //GENERAR OBSTACULOS
            new BlockGenerator(System.getProperty("user.dir")+"/src/assets/obstacles.txt",level);

            
            level.generateObstacles(mapa);
            player = new Player(3*32,17*30+13, level);
            level.add(player);
        }catch(Exception ex){
            //NO HACER NADA, LUEGO ME ENCARGO DE BUSCAR EL ERROR
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

enum Scenes{
    MainMenu,
    Level
}
