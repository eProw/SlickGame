package slicktest.GameDialogue;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Dialogue {
    public Dialogue(){
        
    }
    
    public void print(Graphics g,GameContainer gc, String msg){
        int i = 0;
        String printed="";
        while(i < msg.length()){
            printed += msg.charAt(i);
            g.drawString(printed, 0, 300);
            gc.sleep(20);
            i++;
        }
        gc.sleep(1000);
    }
}
