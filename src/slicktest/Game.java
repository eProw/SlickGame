package slicktest;

import org.newdawn.slick.*;

public class Game extends BasicGame{
    
    public Game(){
        super("My first game");
    }

    public void init(GameContainer gc) throws SlickException {

    }

    public void update(GameContainer gc, int i) throws SlickException {

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setBackground(Color.darkGray);
        g.setColor(Color.green);
        g.drawString("Hola mundo :)", 600, 300);
        //g.translate(0, 0);
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(1366, 768, false);
        app.setSmoothDeltas(true);
        app.setVSync(true);
        app.setShowFPS(true);
        app.setTargetFrameRate(60);
        app.start();
    }
}
