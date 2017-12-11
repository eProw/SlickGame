package slicktest.GameDialogue;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Dialogue {
    int i=0;
    String printed="";
    public boolean done=false,continuar = true;
    double delay=20,time=0;
    int charPerLine=0;
    
    int POSX =30 , POSY = 400;
    
    
    java.awt.Font UIFont1;
    UnicodeFont uniFont;
    
    Sound sound,cont;
    Random r;
    
    String word;
    
    public Dialogue(){
        loadFont();
        try {
            sound = new Sound("./Sound/dialogTalk.ogg");
            cont = new Sound("./Sound/continue.ogg");
        } catch (SlickException ex) {
        }
        
        r = new Random();
    }
    
    public void print(Graphics g,GameContainer gc, String msg){
        g.setColor(Color.white);
        g.setFont(uniFont);
        
        g.drawString(printed, POSX,POSY);
        if(i < msg.length()){
            if(time<System.currentTimeMillis() && continuar){
                
                if(msg.charAt(i)!='~'){
                    printed += msg.charAt(i);
                }
                
                if(msg.charAt(i)=='.'||msg.charAt(i)=='!'){
                    time = System.currentTimeMillis()+1000;
                }else if(msg.charAt(i)==','){
                    time = System.currentTimeMillis()+300;
                }else{
                    if(msg.charAt(i)!=' '&&msg.charAt(i)!='\n' && msg.charAt(i)!='~'){
                        sound.play(r.nextFloat()+0.6f,1);
                    }
                    time = System.currentTimeMillis()+20;
                }
                
                if(msg.charAt(i)==' '){
                    word="";
                } else {
                    word+=msg.charAt(i);
                }
                
                if(msg.charAt(i)=='~'){
                    continuar = false;
                }
                
                if(msg.charAt(i)=='\n'){
                    charPerLine=0;
                }
                
                if(charPerLine >= 50){
                    charPerLine = 0;
                    if(word.length()>0){
                        printed = printed.substring(0, printed.length()-word.length())+"\n"+word;
                    }else{
                        printed+="\n";
                    }
                }
                if(continuar){
                    charPerLine++;
                    i++;
                }
            }
            
            if(!continuar){
                try {
                    Image img = new Image("./assets/enter_key.png");
                    img.setFilter(Image.FILTER_NEAREST);
                    img = img.getScaledCopy(3);
                    g.drawImage(img, 720,500+(System.currentTimeMillis()%500>250?10:0));
                } catch (SlickException ex) {
                
                }
                    
                Input input = gc.getInput();

                if(input.isKeyPressed(Input.KEY_ENTER)){
                    continuar = true;
                    cont.play();

                    printed="";
                    charPerLine=0;
                    i++;
                }
            }
        }else{
            done = true;
            i=0;
            printed="";
    
            time=0;
        }
    }
    
    void loadFont(){
        try{
        UIFont1 = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
        org.newdawn.slick.util.ResourceLoader.getResourceAsStream("./assets/04B_03__.TTF"));
        UIFont1 = UIFont1.deriveFont(java.awt.Font.PLAIN, 25.f); //You can change "PLAIN" to "BOLD" or "ITALIC"... and 16.f is the size of your font

        uniFont = new org.newdawn.slick.UnicodeFont((java.awt.Font) UIFont1);
        uniFont.addAsciiGlyphs();
        uniFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE)); //You can change your color here, but you can also change it in the render{ ... }
        uniFont.addAsciiGlyphs();
        uniFont.loadGlyphs();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
