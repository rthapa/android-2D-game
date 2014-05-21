package cmt3319.TheTreasureHunter;

import java.util.List;
import java.util.Random;

import android.graphics.Color;
import cmt3319.gameframework.Game;
import cmt3319.gameframework.Graphics;
import cmt3319.gameframework.Input.TouchEvent;
import cmt3319.gameframework.Pixmap;
import cmt3319.gameframework.Screen;

public class GameScreen extends Screen {
    enum GameState {
        Ready,
        Running,
        Paused,
        nextLevel,
        gameComplete,
        GameOver
    }
    
    GameState state = GameState.Ready;
    World world;
    int oldScore = 0;
    String score = "0";
    int currentLevel = 1;   
    Random random;
    
    public GameScreen(Game game) {
        super(game);
        world = new World(currentLevel);
        //world.createMaze(currentLevel);
    }
    
    /**
     * Updates the Gamescreen class according to the game state
     */
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        
        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);  
        if(state == GameState.nextLevel)
        	updateLevelUp(touchEvents);
        if(state == GameState.gameComplete)
        	updateGameComplete(touchEvents);
     
    }
    
    /*
     * updates the ready screen
     */
    private void updateReady(List<TouchEvent> touchEvents) {
        if(touchEvents.size() > 0){
            state = GameState.Running;
        }
        
    }
    
    /**
     * Updates the game running state
     * @param touchEvents
     * @param deltaTime
     */
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {  
    	
    	//update enemy 
    	Runnable r = new Runnable() {
            public void run() {       	    					
					world.enemy.activateEnemy(); 
					world.enemy.activateEnemy2();
					world.enemy.activateEnemy3();
            }
        };
        new Thread(r).start();
        
      //update timer
    	Runnable gameTimer = new Runnable() {
            public void run() {       	  
					world.gameTimer.runTimer();
            }
        };
        new Thread(gameTimer).start();
        

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
        	
            TouchEvent event = touchEvents.get(i);
            
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x < 64 && event.y < 64) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    state = GameState.Paused;
                    return;
                }
            }
            
            
            
            if(event.type == TouchEvent.TOUCH_DOWN) {
                if(event.x < 64 && event.y > 416) {
                    world.ship.turnLeft();
                }
                if(event.x > 256 && event.y > 416) {
                    world.ship.turnRight();
                }
                if(event.x >180 && event.x<236 && event.y > 416) {
                    
                    world.ship.turnUp();
                }
                if(event.x < 140 && event.x >84 && event.y > 416) {
                	world.ship.turnDown();
                }
            }
        }
        
        world.update(deltaTime);
        
        if(world.gameOver) {
            if(Settings.soundEnabled)
                Assets.bitten.play(1);
            state = GameState.GameOver;
        }
        if(oldScore != world.score){
            oldScore = world.score;
            score = "" + oldScore;
            if(Settings.soundEnabled)
                Assets.eat.play(1);
        }
        
        if(world.levelComplete){
        	if(currentLevel == 3){
        		state = GameState.gameComplete;
        	}else{
        		state = GameState.nextLevel;
        	}
        }
        
    }
    
    /*
     * Updates the paused screen
     */
    private void updatePaused(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x > 80 && event.x <= 240) {
                    if(event.y > 100 && event.y <= 148) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        state = GameState.Running;
                        return;
                    }                    
                    if(event.y > 148 && event.y < 196) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        game.setScreen(new MainMenuScreen(game));                        
                        return;
                    }
                }
            }
        }
    }
    
    /**
     * updates the level up state screen
     */
    private void updateLevelUp(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
          
                if(event.x > 80 && event.x <= 240) {
                    if(event.y > 167 && event.y <= 195) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        
               		 	currentLevel++;
               		 	
               		   world = new World(currentLevel);
               		   state = GameState.Running;
               		 	
                        return;
                    }                    
                    if(event.y > 210 && event.y < 232) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        game.setScreen(new MainMenuScreen(game));                        
                        return;
                    }
                }
                
                
                
            }
        }
    }
    
    /**
     * updates the game complete state screen
     * @param touchEvents
     */
    private void updateGameComplete(List<TouchEvent> touchEvents) {
    	if(touchEvents.size() > 0){
    		if(Settings.soundEnabled)
                Assets.click.play(1);
            game.setScreen(new MainMenuScreen(game));                        
            return;
        }
        
    }
    
    /**
     * updates the game over state screen
     * @param touchEvents
     */
    private void updateGameOver(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if(event.type == TouchEvent.TOUCH_UP) {
                if(event.x >= 128 && event.x <= 192 &&
                   event.y >= 200 && event.y <= 264) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }
    }
    
    /**
     * draws the media to the screen
     */
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.background, 0, 0);
        drawWorld(world);
        if(state == GameState.Ready) 
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();
        if (state == GameState.nextLevel)
        	drawNextLevelUI();
        if (state == GameState.gameComplete)
        	drawGameCompleteUI();
        
        if(world.model.hasNoWallPower){
        	//no wall power notification
        	 g.drawPixmap(Assets.nowallmsg, g.getWidth() / 2 - 125, 5); 
        	drawText(g,""+world.model.noWallStepCount, g.getWidth() / 2 + 75, 0);
        }
        
        if(world.model.hasInvincible){
        	g.drawPixmap(Assets.invincibleMsg, g.getWidth() / 2 - 125, 5); 
        	drawText(g,""+world.model.invincibleStepCount, g.getWidth() / 2 + 75, 0);
        }
        
        //drawText(g, score, g.getWidth() / 2 - score.length()*20 / 2, g.getHeight() - 42);   
        drawText(g, ""+world.model.getTotalTimeLeft(), g.getWidth() - 40, 5);   
    }
    
    /**
     * draws the world in the screen
     * @param world
     */
    private void drawWorld(World world) {
    	
        Graphics g = game.getGraphics();
        Ship ship = world.ship;
        Stain stain = world.stain;
        //stains
        Pixmap stainPixmap = null;
        if(stain.type == Stain.TYPE_1)
            stainPixmap = Assets.stain1;
        if(stain.type == Stain.TYPE_2)
            stainPixmap = Assets.stain2;
        if(stain.type == Stain.TYPE_3)
            stainPixmap = Assets.stain3;
        int stainX = stain.x * 32;
        int stainY = stain.y * 32;      
        g.drawPixmap(stainPixmap, stainX, stainY);  
        
        Model model = world.model;
        boolean[][] horizontalLines= model.getHorizontalLines();
	    boolean[][] verticalLines= model.getVerticalLines();
	    
	
	    int modelWidth = model.getModelWidth();
	    int modelHeight = model.getModelHeight();
	    
	       
	    int x, y;
        for(int i = 0; i < modelWidth; i++) {
            for(int j = 0; j < modelHeight; j++){
                     x = j*32;
                     y = i*32;
                    if(j < modelWidth && verticalLines[i][j] == true){
                            //draw a vertical rocks
                    	
                    	g.drawPixmap(Assets.rock1, x+25,y);
                    	g.drawPixmap(Assets.rock2, x+25,y+15);
                    	
                    }
                    if(i < modelHeight  && horizontalLines[i][j] == true) {
                            //draw a horizontal rocks
                    	g.drawPixmap(Assets.rock1, x,y+25); //+32 
                    	g.drawPixmap(Assets.rock2, x+15,y+25); //+32
                   
                    }
            }  
        }
        
      //key
        int keyPointX = model.getKeyPointX(); 
        int keyPointY= model.getKeyPointY();
        Pixmap keyPixmap = null;
       
        keyPixmap = Assets.key;
         x = keyPointX * 32;
         y = keyPointY * 32;      
        g.drawPixmap(keyPixmap, x,y);       
        
        //box
        int boxX = model.getBoxX(); 
        int boxY= model.getBoxY();
        Pixmap boxPixmap = null;
       
        boxPixmap = Assets.box;
         x = boxX * 32;
         y = boxY * 32;      
        g.drawPixmap(boxPixmap, x,y);
        
        int currentX = model.getCurrentX(); 
        int currentY = model.getCurrentY();
        //enemy
        int enemyX = model.getEnemyX();
        int enemyY = model.getEnemyY();
        Pixmap enemyPixmap = null;
        
        enemyPixmap = Assets.enemy;
        x = enemyX * 32;
        y = enemyY * 32;
        g.drawPixmap(enemyPixmap, x,y);
        
        //enemy2
        int enemyX2 = model.getEnemyX2();
        int enemyY2 = model.getEnemyY2();
        Pixmap enemyPixmap2 = null;
        
        enemyPixmap2 = Assets.enemy;
        x = enemyX2 * 32;
        y = enemyY2 * 32;
        g.drawPixmap(enemyPixmap2, x,y);
      //enemy3
        int enemyX3 = model.getEnemyX3();
        int enemyY3 = model.getEnemyY3();
        Pixmap enemyPixmap3 = null;
        
        enemyPixmap3 = Assets.enemyCrazy;
        x = enemyX3 * 32;
        y = enemyY3 * 32;
        g.drawPixmap(enemyPixmap3, x,y);
        
        //ship head position
        Pixmap headPixmap = null;
        if(ship.direction == Ship.UP) 
            headPixmap = Assets.headUp;
        if(ship.direction == Ship.LEFT) 
            headPixmap = Assets.headLeft;
        if(ship.direction == Ship.DOWN) 
            headPixmap = Assets.headDown;
        if(ship.direction == Ship.RIGHT) 
            headPixmap = Assets.headRight;        
        x = currentX * 32 + 16; //head.x
        y = currentY * 32 + 16; //head.y
        //setting the snake start point
        g.drawPixmap(headPixmap, x - headPixmap.getWidth() / 2, y - headPixmap.getHeight() / 2);
    }
    
    /**
     * draws the ready state UI
     */
    private void drawReadyUI() {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.ready, 47, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }
    
    /**
     * draws running screen UI
     */
    private void drawRunningUI() {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
        //g.drawLine(0, 416, 480, 416, Color.BLUE);
        //g.drawPixmap(Assets.buttons, 0, 200, 64, 64, 64, 64);
        g.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
        g.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
        
        //top bot
        g.drawPixmap(Assets.buttons, 185, 416, 64, 192, 64, 128);
        g.drawPixmap(Assets.buttons, 84, 416, 0, 192, 64, 128);
    }
    
    /**
     * draws nextLevel screen UI
     */
    private void drawNextLevelUI(){
    	Graphics g = game.getGraphics();
        g.drawPixmap(Assets.nextLevel, 80, 100);
        //g.drawLine(0, 416, 480, 416, Color.BLACK);
    }
    
    /**
     * draws the paused screen UI
     */
    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.pause, 80, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }
    
    /**
     * draws game complete screen UI
     */
    private void drawGameCompleteUI(){
    	Graphics g = game.getGraphics();
        g.drawPixmap(Assets.gameComplete, 30, 50);
    }
    
    /**
     * draws gameover screen UI
     */
    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        
        g.drawPixmap(Assets.gameOver, 62, 100);
        g.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);
        g.drawLine(0, 416, 480, 416, Color.BLACK);
    }
    
    /**
     * Draws text on the screen 
     * @param g
     * @param line
     * @param x
     * @param y
     */
    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }
    
    /**
     * pauses the games
     */
    public void pause() {
        if(state == GameState.Running)
            state = GameState.Paused;
        
        if(world.gameOver) {
            Settings.addScore(world.score);
            Settings.save(game.getFileIO());
        }
    }

    public void resume() {
        
    }

    public void dispose() {
        
    }
}
