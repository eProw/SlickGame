package slicktest.GameDialogue;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Dialogue {
    int i=0;
    String printed="";
    public boolean done=false;
    double delay=20,time=0;
    int charPerLine=0;
    
    int POSX =30 , POSY = 400;
    
    
    java.awt.Font UIFont1;
    UnicodeFont uniFont;
    
    public Dialogue(){
        loadFont();
    }
    
    public void print(Graphics g,GameContainer gc, String msg){
        g.setFont(uniFont);
        
        g.drawString(printed, POSX,POSY);
        if(i < msg.length()-1){
            if(time<System.currentTimeMillis()){
                
                printed += msg.charAt(i);
                
                if(msg.charAt(i)=='.'||msg.charAt(i)=='!'){
                    time = System.currentTimeMillis()+1000;
                }else if(msg.charAt(i)==','){
                    time = System.currentTimeMillis()+300;
                }else{
                    time = System.currentTimeMillis()+20;
                }
                
                i++;
                charPerLine++;
                
                if(msg.charAt(i)=='\n'){
                    charPerLine=0;
                }
                
                if(charPerLine >= 50){
                    printed+="\n";
                    charPerLine = 0;
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
