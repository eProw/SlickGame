package slicktest;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
/**
 *
 * @author eProw
 */
public class Player extends GameObject{
    
    
    Animation player, idleR,idleL,runR,jumpR,jumpL,slideR,slideL,death,runL;
    
    double xDelay=0;
    
    boolean isRight = true, ground = true,spacePressed = false,jumpBySpace = false;
    
    final float GRAVITY = 1;
    int animSpeed = 250;
    
    int destX, destY,tempX,tempY;
    
    Handler h;
    
    //COMPROBAR COLISION
    boolean topLeft,topRight,bottomLeft,bottomRight;
    
    public Player(int _x, int _y,Handler h) throws SlickException {
        super(_x, _y);
        
        this.h = h;
        
        idleR = new Animation(new Image[]{new Image("./player/idle.gif")},1000);
        idleL = new Animation(new Image[]{new Image("./player/idle.gif").getFlippedCopy(true, false)},1000);
        death = new Animation(new Image[]{new Image("./player/death.gif")},1000);
        jumpR = new Animation(new Image[]{new Image("./player/jump.gif")},1000);
        jumpL = new Animation(new Image[]{new Image("./player/jump.gif").getFlippedCopy(true, false)},1000);
        slideR = new Animation(new Image[]{new Image("./player/slide.gif")},1000);
        slideL = new Animation(new Image[]{new Image("./player/slide.gif").getFlippedCopy(true, false)},1000);
        runR = new Animation(new Image[]{new Image("./player/2.gif"),
            new Image("./player/3.gif"),
            new Image("./player/4.gif")
        },200);
        runL = new Animation(new Image[]{new Image("./player/2.gif").getFlippedCopy(true, false),
            new Image("./player/3.gif").getFlippedCopy(true, false),
            new Image("./player/4.gif").getFlippedCopy(true, false)
        },200);
        
        player = idleR;
    }

    public void update(GameContainer gc, int delta) {
        tempX = x;
        tempY = y;
        player.update(delta);
        movementUpdate(delta);
        inputUpdate(gc,delta);
        destX = x + velX;
        destY = y + velY;
        collisions();
        x = destX;
        y = destY;
        
    }

    public void render(GameContainer gc, Graphics g) {
        player.draw(x, y+1);
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x,y,32,32);
    }
    
    public void inputUpdate(GameContainer gc,int delta){
        Input input = gc.getInput();
        
        if(input.isKeyDown(Input.KEY_SPACE)){
            if(ground && !spacePressed){
                velY = -18;
                ground = false;
                jumpBySpace = true;
            }
            spacePressed = true;        
        } else if(spacePressed){
            if(velY < -8){
                velY = -8;
            }
            
            spacePressed = false;
        }
        
        if(input.isKeyDown(Input.KEY_A)){
            if(velX>-5){
                if(System.currentTimeMillis() > xDelay){
                    velX--;
                    xDelay = System.currentTimeMillis()+(ground?60:100);
                }
            
            }else
                velX=-5;
            if(ground)
                isRight=false;
            if(ground){
                if(velX>0){
                    player = slideL;
                    player.update(delta); 
                }else{
                    player = runL;
                    player.update(delta);
                }
            }
        }else
        
        if(input.isKeyDown(Input.KEY_D)){
            if(velX<5){
                if(System.currentTimeMillis() > xDelay){
                    velX++;
                    xDelay = System.currentTimeMillis()+(ground?60:100);
                }
            }else
                velX=5;
            
            if(ground)
                isRight=true;
            if(ground){
                if(velX<0){
                    player = slideR;
                    player.update(delta); 
                }else{
                    player = runR;
                    player.update(delta);
                }
            }
        } else {
            //SI NO SE PULSA LA TECLA A NI LA TECLA D LA VELOCIDAD DISMINUIRA A CERO
            if(velX>0){
                if(System.currentTimeMillis() > xDelay){
                    velX--;
                    xDelay = System.currentTimeMillis()+50;
                }
            }
            else if(velX < 0){
                if(System.currentTimeMillis() > xDelay){
                    velX++;
                    xDelay = System.currentTimeMillis()+50;
                }
            }
            animSpeed = 800;
            
            if(ground){
                player = isRight?idleR:idleL;
                player.update(delta);
            }
        }
        
        if(!ground){
            if(jumpBySpace)
                player = isRight?jumpR:jumpL;
            else{
                //La animacion RUN se pausa, tal y como en el juego original
            }
            player.update(delta);
        }
    }
    
    public void movementUpdate(int delta){
        velY += GRAVITY;
        if(Math.abs(velX)>0){
            if(animSpeed > 200){
                animSpeed-=10;
            }else{
                animSpeed=200;
            }
        }else
            animSpeed = 500;
        for(int i = 0;i < 3;i++){
            runR.setDuration(i, animSpeed);
            runL.setDuration(i, animSpeed);
        }
    }
    
    void calculateCorners(int x,int y){
        int top = (y-140)/32,bottom = ((y-140+32)/32), left = x/32, right = (x+32-1)/32;
        topLeft = h.obstacles[left][top];
        topRight = h.obstacles[right][top];
        bottomLeft = h.obstacles[left][bottom];
        bottomRight = h.obstacles[right][bottom];
    }
    
    void collisions(){ //VERSION NUMERO 0.0.1 
        int columna = (y-140)/32,fila = x/32; 
        
        calculateCorners(x,destY);
        if(velY > 0){
            if(bottomLeft||bottomRight){
                velY=0;
                tempY = columna*32+140+31;
                ground = true;
            }else{
                tempY+=velY;
            }
        }
        if(velY < 0){
            if(topLeft||topRight){
                velY = 0;
                tempY = columna*32+140;
            }else{
                tempY+=velY;
            }
        }
        
        calculateCorners(destX,y);
        
        if(velX < 0){
            if(bottomLeft||topLeft){
                velX=0;
                tempX = fila*32;
            }else{
                tempX+=velX;
            }
        }
        if(velX > 0){   
            if(bottomRight||topRight){
                velX=0;
                tempX = (destX-tempX)>32?(fila)*32:tempX;
            }else{
                tempX+=velX;
            }
        }
        
        destX = tempX;
        destY = tempY;
    }
}
