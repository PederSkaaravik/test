import java.awt.*; 
import java.awt.event.KeyEvent; 
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationHandler;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame; 

/** 
 * Main class for the game 
 */ 
public class GameEngine extends JFrame 
{        
        boolean isRunning = true; 
        int fps = 30; 
        int windowWidth = 500; 
        int windowHeight = 500; 
        
        BufferedImage backBuffer; 
        Insets insets; 
        InputHandler input; 
        
        boolean wallHit = false;
        int x = 0; 
        int y = 50;
        int jumpHeight = 10;
        int posX = 480;
        int posY = 50;
        int test = 0;
        
        int[] myPos = new int[2];
        
    	boolean up = false,down = false,left = false,right = false;

    	

        
        public static void main(String[] args) throws InterruptedException 
        { 
                GameEngine game = new GameEngine(); 
                game.run(); 
                System.exit(0); 
        } 
        
        /** 
         * This method starts the game and runs it in a loop 
         * @throws InterruptedException 
         */ 
        public void run() throws InterruptedException 
        { 
                initialize(); 
                
                while(isRunning) 
                { 
                        long time = System.currentTimeMillis(); 
                        
                        update(); 
                        draw(); 
                        shoot();
                       // shoot();
                        
                        while(right == true)
                            x += 5; 
                    	
                    	while(left == true)
                            x -= 5; 
                        
                    	myPos[0] = x;
                    	myPos[1] = y;
                    	
                    	//wallhit exceptions
                    	if(x == 480 || x == -5)
                    		wallHit = true;
                    	if(y == 480 || y == -5)
                    		wallHit = true;
                    	
                    	if(posX > 0)
                    		posX -= 5;
                    	if(posX == 0)
                    		shoot();
                    	
                        
                        //  delay for each frame  -   time it took for one frame 
                        time = (1000 / fps) - (System.currentTimeMillis() - time); 
                        
                        if (time > 0) 
                        { 
                                try 
                                { 
                                        Thread.sleep(time); 
                                } 
                                catch(Exception e){} 
                        } 
                        
                      
                        
                        
                        if(x == posX && y == posY)
                        	break;
                        
                        if(wallHit)
                        	break;
                } 
                
                setVisible(false); 
        } 
        
        /** 
         * This method will set up everything need for the game to run 
         */ 
        
        void shoot() {
        	
        	Random rand = new Random();
        
        	Graphics bullet = getGraphics();
            Graphics bb = backBuffer.getGraphics(); 
            
            bb.setColor(Color.BLACK); 
            bb.drawOval(posX, posY, 20, 20); 
            
            bullet.drawImage(backBuffer, insets.left, insets.top, this); 
        	
        	
        }
        
        void initialize() 
        { 
                setTitle("Dodge One Ball : The Game"); 
                setSize(windowWidth, windowHeight); 
                setResizable(false); 
                setDefaultCloseOperation(EXIT_ON_CLOSE); 
                setVisible(true); 
                
                insets = getInsets(); 
                setSize(insets.left + windowWidth + insets.right, 
                                insets.top + windowHeight + insets.bottom); 
                
                backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB); 
                input = new InputHandler(this); 
        } 
        
        /** 
         * This method will check for input, move things 
         * around and check for win conditions, etc 
         * @throws InterruptedException 
         */ 
        void update() throws InterruptedException 
        { 
                if (input.isKeyDown(KeyEvent.VK_RIGHT))
                { 
                	x += 5;
                	
                } 
                if (input.isKeyDown(KeyEvent.VK_LEFT)) 
                { 
                	x -= 5;
                	
                } 
                
                
                if (input.isKeyDown(KeyEvent.VK_UP))
                { 
                	y -= 5;
                	
                } 
                if (input.isKeyDown(KeyEvent.VK_DOWN)) 
                { 
                	y += 5;
                	
                } 
                

                	System.out.println("position : "+myPos[0]+" , "+myPos[1]);
              
                
                
                
                
        } 
        
        /** 
         * This method will draw everything 
         */ 
        void draw() 
        {               
                Graphics g = getGraphics(); 
                
                Graphics bbg = backBuffer.getGraphics(); 
                
                bbg.setColor(Color.WHITE); 
                bbg.fillRect(0, 0, windowWidth, windowHeight); 
                
                bbg.setColor(Color.BLACK); 
                bbg.drawOval(x, y, 20, 20); 
                
                g.drawImage(backBuffer, insets.left, insets.top, this); 
        } 
} 