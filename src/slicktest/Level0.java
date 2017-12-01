package slicktest;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Level0 extends Handler{
    public boolean end = false;
    double timeTrigger,dotTrigger,cmdTrigger;
    
    Sound startUp;
    Sound startAmbience;
    Sound warn;
    Sound amb;
    
    Music loop;
    
    java.awt.Font UIFont1;
    UnicodeFont uniFont;
    
    int phase=0;
    
    String dots= "";
    String[] cmd1 = new String[]{"Inicializando kernel...",
            "Sistema operativo EPR-OS todos los derechos reservados",
            "EP.R-OS (C)",
            "Fecha BIOS: 8/11/17 22:43",
            "-------\nRevision Firmware 23.0.14",
            "CPU MEGAQUBIT CORP. v.8n X480 @5.03 ZHz",
            "Inicializando controladores interfaz cerebro-maquina...(20%)",
            "Inicializando controladores interfaz cerebro-maquina...(40%)",
            "Inicializando controladores interfaz cerebro-maquina...(80%)",
            "Inicializando controladores interfaz cerebro-maquina...(85%)",
            "Inicializando controladores interfaz cerebro-maquina...(100%)",
            "\nEspacio disponible en el sistema: 32.3 Yb",
            "\nDireccion IP: EN DESUSO",
            "\nAutodetectando dispositivos de almacenamiento USB...",
            "Dispositivos detectados en rutas de acceso: 0xF2245; 0xA2140; 0xFFFFF",
            "Dispositivos detectados en rutas de acceso: 0x3CB38; 0xFBA3C; 0x245E0",
            "",
            ""
    };
    
    String[] cmd2 = new String[]{
        "",
        "Buscando drivers en el sistema...(50%)",
        "Buscando drivers en el sistema...(90%)",
        "Buscando drivers en el sistema...(99%)",
        "TIEMPO DE ESPERA AGOTADO- Reintentando.",
        "Buscando drivers en el sistema...(99%)",
        "TIEMPO DE ESPERA AGOTADO- Reintentando.",
        "Buscando drivers en el sistema...(99%)",
        "Se ha detectado una anomalia en el driver:\nSLICKTEST.EXE",
        "Procediendo al analisis de archivo...",
        "Leyendo...",
        "",
        "",
        ""
        
        
    };
    
    String[] process = new String[]{
        "-","\\","|","/"
    };
    
    String[] cmd3 = new String[]{
"  @@@@@@ @@@      @@@  @@@@@@@ @@@  @@@ @@@@@@@ @@@@@@@@  @@@@@@ @@@@@@@\n" +
" !@@     @@!      @@! !@@      @@!  !@@   @@!   @@!      !@@       @@!  \n" +
"  !@@!!  @!!      !!@ !@!      @!@@!@!    @!!   @!!!:!    !@@!!    @!!  \n" +
"     !:! !!:      !!: :!!      !!: :!!    !!:   !!:          !:!   !!:  \n" +
" ::.: :  : ::.: : :::  :: :: :  :   :::    :    : :: ::: ::.: :     :   "
    ,
        "Iniciando sistema...",
        "Calibrando sensores(70%)",
        "Calibrando sensores(100%)",
        ""
    };
    
    String options = "Seleccione una opcion:\n"
            + "Proceder a simulacion (1)\n"+
            "Proceder a simulacion (2)\n"+
            "Proceder a simulacion (3)";
    
    int i = 0;
    
    String cmdOut = "";
    
    Random r;
    Cinematic eprow;
    float alpha = 0;
    
    boolean playedCin=false;
    int y=0;
    int numOfLines=10;
    
    long errTime=0;
    long finishTime = 0;
    
    public Level0(){
        eprow = new Cinematic(0,0,"./assets/cinematics/eprow",30,33,false);
        //CINEMATIC: POSITION X & Y, FRAMES PATH, NUM OF FRAMES, MILLIS PER FRAME, CAN LOOP
        timeTrigger = System.currentTimeMillis()+5000;
        
        loadFont(java.awt.Color.WHITE);
        r = new Random();
        
        try {
            startUp = new Sound("./Sound/startup.ogg");
            warn = new Sound("./Sound/warn.ogg");
            startAmbience = new Sound("./Sound/StartupAmbience.ogg");
            amb = new Sound("./Sound/amb.ogg");
            loop = new Music("./Sound/loop.ogg");
        } catch (SlickException ex) {
        }
        
        startAmbience.play();
    }
    
    public void update(GameContainer gc, int delta){
        for(int i = 0; i < objects.size();i++){
            GameObject obj = objects.get(i);
            obj.update(gc, delta);
        }
        
        if(timeTrigger-3000<System.currentTimeMillis() && timeTrigger-2000>System.currentTimeMillis()){
            if(!startAmbience.playing()){
                startAmbience.play();
            }
        }
        
        if(timeTrigger+15000<System.currentTimeMillis()&&phase<4){
            if(!loop.playing()){
                loop.play();
                loop.loop();
            }
            
            if(timeTrigger+16000>System.currentTimeMillis()){
                if(!amb.playing()){
                    amb.play();
                }
            }
        }
        
        if(timeTrigger-3000 < System.currentTimeMillis()){
            if(alpha < 1){
                alpha+=.01f;
            }else{
                alpha = 1;
            }
        }
        
        if(timeTrigger < System.currentTimeMillis()){
            if(!playedCin){
                add(eprow);
                startUp.play();
                playedCin = true;
            }
        }
    }
    
    public void render(GameContainer gc, Graphics g){
        g.setBackground(new Color(0,0,0));
        g.drawString("",320, 500);
      
        for(GameObject obj:objects){
            obj.render(gc, g);
        }
        if(phase==0){
            if(timeTrigger+3000 < System.currentTimeMillis() && System.currentTimeMillis() < timeTrigger+8000){

                g.setFont(uniFont);
                g.setColor(Color.green);
                g.drawString("Cargando"+dots,320, 500);
                g.setColor(Color.white);

                if(dotTrigger < System.currentTimeMillis()){
                    dots+=".";
                    if(dots.length()>=4){
                        dots="";
                    }
                    dotTrigger = System.currentTimeMillis()+300;  
                }
            }

            if(timeTrigger+8000 < System.currentTimeMillis()&& phase == 0){
                remove(eprow);
                
                if(i==0){
                    cmdTrigger = System.currentTimeMillis()+3000;
                    i++;
                }
                if(cmdTrigger < System.currentTimeMillis()){
                    cmdOut+=cmd1[i]+"\n";

                    int delay=0;
                    if(i<3){
                        delay = (r.nextInt(1000)+1000);
                    }else{
                        delay = (r.nextInt(1000)+500);
                    }
                    cmdTrigger = System.currentTimeMillis()+delay;
                    if(i+1 < cmd1.length){
                        i++;
                    }else{
                        cmdOut = "";
                        phase++;
                        i=0;
                    }
                }


                g.setFont(uniFont);
                g.drawString(cmdOut+(System.currentTimeMillis()%400>200?"_":""),0, 0);
            }
        }
        
        if(phase==1){
            if(cmdTrigger < System.currentTimeMillis()){
                    cmdOut+=cmd2[i]+"\n";

                    int delay=0;
                    if(i==0){
                        delay = (r.nextInt(1000)+2000);
                    }else if(i==7||i==8){
                        delay = (r.nextInt(1000)+5000);
                    }else if(i==10){
                        delay = (3000);
                    }else{
                        delay = (r.nextInt(1000)+1000);
                    }
                    cmdTrigger = System.currentTimeMillis()+delay;
                    if(i < cmd2.length-1){
                        i++;
                    }
            }
            
            g.setFont(uniFont);
            
            if(i==9){
                if(!warn.playing()){
                    warn.play();
                    errTime = System.currentTimeMillis();
                }
            }
            
            if(i > 8){
                g.setColor(Color.red);
                g.drawString("(!!!)", 170, 230+y);
                g.setColor(Color.white);
            }
            
            if(i>11){
                cmdOut+=(char)(r.nextInt(100)+100);
                if((cmdOut.length()%(r.nextInt(20)+11))==0){
                    cmdOut+=";\n";
                    numOfLines++;
                    if(numOfLines>15){
                        y-=20;
                    }
                }
            }
            
            if(i>12){
                int n = r.nextInt(10);
                switch (n){
                    case 0:
                        g.setColor(Color.green);
                        break;
                        
                    case 1:
                        g.setColor(Color.white);
                        break;
                        
                    case 2:
                        g.setColor(Color.red);
                        break;
                        
                    case 3:
                        y=r.nextInt(400)-420;
                        break;
                        
                    default:
                        break;
                }
            }
            
            if(errTime + 14500 < System.currentTimeMillis() && errTime != 0){
                phase++;
                i=0;
                cmdOut="";
                y=0;
            }
            
            g.drawString(cmdOut+(System.currentTimeMillis()%400>200?"_":"")+(i==11?process[(int)(System.currentTimeMillis()/2)%4]:""),0, y);
        }
        
        if(phase == 2){
            if(cmdTrigger < System.currentTimeMillis()){
                    cmdOut+=cmd3[i]+"\n";
                    g.setColor(Color.green);
                    int delay=0;
                    if(i==0){
                        delay = (r.nextInt(1000)+2000);
                    }else{
                        g.setFont(uniFont);
                        delay = (r.nextInt(1000)+1000);
                    }
                    cmdTrigger = System.currentTimeMillis()+delay;
                    if(i < cmd3.length-1){
                        i++;
                    }else{
                        phase++;
                        i=0;
                    }
            }
            
            g.drawString(cmdOut+(System.currentTimeMillis()%400>200?"_":"")+(i==11?process[-(int)(System.currentTimeMillis()/2)%4]:""),0, y);
        }
        
        if(phase == 3){
            if(i==0){
                cmdOut += options+"\n";
                i++;
            }
            
            if(i==1){
                Input input = gc.getInput();
                boolean enterKey = false;
                
                if(input.isKeyPressed(Input.KEY_1)||input.isKeyPressed(Input.KEY_2)||input.isKeyPressed(Input.KEY_3)){
                    i++;
                    cmdOut+="Cargando...\nSeleccione archivo de datos a arrancar:\n1. 31MB datos (e-20% en el sistema)\n2. Archivo vacio\n3. Archivo vacio\n";
                }
            }
            
            if(i==2){
                Input input = gc.getInput();
                if(input.isKeyPressed(Input.KEY_1)){
                    phase++;
                    finishTime = System.currentTimeMillis();
                    cmdOut+="Iniciando...\n";
                    
                }
                
                if(input.isKeyPressed(Input.KEY_3)){
                    cmdOut+="No existen datos en el sector\n";
                    y-=20;
                }
                
                if(input.isKeyPressed(Input.KEY_2)){
                    cmdOut+="No existen datos en el sector\n";
                    y-=20;
                }
            }
            
            g.drawString(cmdOut+(System.currentTimeMillis()%400>200?"_":"")+(i==11?process[-(int)(System.currentTimeMillis()/2)%4]:""),0, y);
        }
        
        if(phase==4){
            if(alpha >0){
                 alpha-=.02f;
                 loop.setVolume(alpha);
             }else{
                 alpha = 0;
                 
                 loop.stop();
                 endScene();  
             }

             if(finishTime+500>System.currentTimeMillis())
             g.drawString(cmdOut+(System.currentTimeMillis()%400>200?"_":"")+(i==11?process[-(int)(System.currentTimeMillis()/2)%4]:""),0, y);
        }
        
        
        try {
            Image dirt = new Image("./assets/dirt.png").getScaledCopy(.8f);
            Image reflect = new Image("./assets/reflect.jpg").getScaledCopy(1);
            
            dirt.setAlpha(alpha);
            reflect.setAlpha(alpha/5);
            
            dirt.draw();
            reflect.draw();
            
        } catch (SlickException ex) {

        }
    }
    
    public void add(GameObject obj){
        objects.add(obj);
    }
    
    public void remove(GameObject obj){
        objects.remove(obj);
    }
    
    void loadFont(java.awt.Color color){
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
    
    public void endScene(){
        end = true;
        startUp.stop();
        startAmbience.stop();
        warn.stop();
        amb.stop();
    }
}
