package slicktest.GameDialogue;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Dialogue {
    public Dialogue(){
        
    }
    
    public void print(Graphics g,GameContainer gc, String msg){
        int i = 0;
        String printed="";
        double delay=20,time=0;
        while(i < msg.length()){
            if(time<System.currentTimeMillis()){
                printed += msg.charAt(i);
                g.drawString(printed, 0, 300);
                i++;
                time = System.currentTimeMillis()+20;
            }
        }

    }
}
