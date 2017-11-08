/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slicktest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.newdawn.slick.SlickException;

/**
 *
 * @author eProw
 */
public class BlockGenerator {
    Handler h;
    String path;
    
    public BlockGenerator(String path,Handler h) throws FileNotFoundException, IOException, SlickException{
        this.h = h;
        this.path = path;
        generateMap();
    }
    
    void generateMap() throws FileNotFoundException, IOException, SlickException{
        BufferedReader br = new BufferedReader(new FileReader(path));
        for(int y = 0;y<15;y++){
            String line = br.readLine();
            for(int x = 0;x<28;x++){
                char c = line.charAt(x);
                switch(c){
                    case'1':
                    h.add(new Block(x*32,y*32+140,blockID.QUESTION,h));
                    break;
                    
                    case'2':
                    h.add(new Block(x*32,y*32+140,blockID.BRICK,h));
                    break;
                    
                    case'3':
                    h.add(new Block(x*32,y*32+140,blockID.HARD,h));
                    break;
                    
                    default:
                        
                        break;
                }
            }
        }
        
        br.close();
    }
}
