package cmt3319.TheTreasureHunter;

import java.util.Random;
/**
 * This class sets up world map and world variables.
 * @author Rabi Thapa
 * @since 2014-04-29
 */

public class World {
	
    static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int SCORE_INCREMENT = 1;
    static final float TICK_INITIAL = 0.5f;
    static final float TICK_DECREMENT = 0.05f;
    
    public Model model;
    public int currentX;
    public int currentY;
    public Ship ship;
    public Enemy enemy;
    public Stain stain;
    public boolean gameOver = false;
    public int score = 20;
    public boolean gotKey = false;
    public boolean levelComplete = false;
    public GameTimer gameTimer;
   
    		
    boolean fields[][] = new boolean[WORLD_WIDTH][WORLD_HEIGHT];
    Random random = new Random();
    float tickTime = 0;
    static float tick = TICK_INITIAL;
    
    

    public World(int level) {
    	//createWorld();
    	model = null;
    	createMaze(level);
        ship = new Ship(this);
        enemy = new Enemy(this, 1);
        gameTimer = new GameTimer(this);
        placeStain();
    }
    
    /**
     * This method places the power ups randomly on the map but also checks 
     * if its allowed to put the power ups in there with logical algorithm.
     */
    private void placeStain() {
        for (int x = 0; x < WORLD_WIDTH; x++) {
            for (int y = 0; y < WORLD_HEIGHT; y++) {
                fields[x][y] = false;
            }
        }

        	int playerX = model.getCurrentX();
        	int playerY = model.getCurrentY();
            fields[playerX][playerY] = true;
            
            //prevent the top rows from spawning power ups
            for(int i=0; i< WORLD_WIDTH; i++){
            	int tempY = 0;
            	fields[i][tempY] = true;
            }
            //prevent the bottom rows from spawning power ups
            for(int i=0; i< WORLD_WIDTH; i++){
            	int tempY = WORLD_HEIGHT - 1;
            	fields[i][tempY] = true;
            }

        int stainX = random.nextInt(WORLD_WIDTH);
        int stainY = random.nextInt(WORLD_HEIGHT);
        while (true) {
            if (fields[stainX][stainY] == false)
                break;
            stainX += 1;
            if (stainX >= WORLD_WIDTH) {
                stainX = 0;
                stainY += 1;
                if (stainY >= WORLD_HEIGHT) {
                    stainY = 0;
                }
            }
        }
        stain = new Stain(stainX, stainY, random.nextInt(2));
    }
    
    /**
     * This method draws the maze on map according to the level given.
     * @param level - game level
     */
    public Model createMaze( int level ){
    	model = null;
    	if (level == 1){
    		 model = new Model();
    		 
    	boolean[][] vLines = new boolean[][]{
    			{false,false,false ,false,false,false,false ,false,false,false,false,false},
				{false,false ,false,true,false,false ,false,false,false,false,true ,true },
				{true ,false,false,true,false,true ,false,true,true,true,true ,true },
				{true ,false ,true,false,false,false ,false ,true ,true,true,true ,true },
				{true ,false ,false ,true,true,false ,true ,false,true ,true,true ,true },
				{false,false ,false ,false ,false,false ,false,false,true,true ,true,true},
				{false,false,false,false ,true,false ,false,true ,false,true,true,true},
				{true,true,true ,true,false ,false,true ,true ,true,true ,true,true},
				{true ,true ,true ,false ,false,false ,false ,true,true,true ,true,true},
				{true,true,false,false ,false,false,false ,false ,true,true ,true ,true},
				{false,true,true ,false,false ,true,false ,false,false,true,true,true},
				{false,false,false ,false,false,true ,false,false,false,true,true ,true}
			 };

    		boolean[][] hLines = new boolean[][]{
				{true,true,true,true ,true,true,true,true,true,true ,true ,true },
				{true,true,false,false,false,true,false ,false ,true ,false,true,true},
				{false,false,false ,true ,true ,true,false,false ,false ,false ,true ,true},
				{false,false,false,false ,true ,false ,false,true,false,false ,true,true},
				{false,true,true,true,false ,true,true,false ,false ,false ,true,true},
				{false ,false ,false,false,false,true ,true ,false ,true ,false,true ,true},
				{false,true ,false ,true ,false ,true ,true,true,false,false ,true ,true },
				{false ,false,false,false,false ,true,true ,false,false ,false,true,true},
				{false,false ,false,true,true,true ,true,true ,false ,false ,true ,true },
				{false ,false ,false,true ,true,false ,true ,true,true,false ,true,true},
				{true,true ,false ,true,true ,true,false,false ,false ,false,true ,true },
				{true,true ,true,true,true ,true,true,true ,true ,true ,true,true }
			};

    	model.setVerticalLines(vLines);
    	model.setHorizontalLines(hLines);
    	model.setStartPosition(0, 1);
    	model.setKeyPosition(3, 7);
    	model.setBoxPosition(0, 11);
    	
    	model.initEnemy(9, 11, 1);
    	model.initEnemy2(0, 11, 2);
    	model.initEnemy3(2, 7, 3);
    	model.setTotalTimeLeft(60);
    	}
    	if(level== 2) {
			model = new Model();
			boolean[][] vLines = new boolean[][]{
					{false,false,false,false ,false,false,false,false,  false, false,false,false},
					{false,false,true ,false,true ,false,false, false,true,true,true,true},
					{false,false,true ,true ,false,false,false,false,false,true,true,true},
					{false,false,true ,true ,true ,false,false,false,true,true,true,true},
					{false,false,false ,false,true ,false,false,false,false,true,true,true},
					{false ,false,false,true ,false,true ,false,false,false,true,true,true},
					{true ,false,true ,true ,false,false,false,false,false,true,true,true},
					{true,false,true ,false,false,false,true,false,false,true,true,true },				
					{false,false,true ,true ,false,false,false,false,true, true,true,true},
					{false,false,true ,true ,true ,false,false,false,false,true,true,true},
					{false,false,false ,false,true ,false,false,false,true, true,true,true},
					{false ,false,false,true ,false,true ,false,false,false, true,true,true},
				 };
			boolean[][] hLines = new boolean[][]{
					{true,true ,true,true,true ,true,true,true ,true ,true ,true,true },
					{true ,true ,false,false,true ,true ,true ,false,false,false,true,true},
					{false,true ,true ,false,false,false,true ,true ,false,false,true,true},
					{true ,true ,false,false,false,true ,true ,false,true,false,true,true},
					{false,true ,true ,true ,true ,false,true ,false,false,false,true,true},
					{false,false,true ,false,false,true ,true ,true ,true,false,true,true},
					{false,false,true ,false,false,true ,true ,true ,true,false,true,true},					
					{false,true ,false,false,true ,true ,false,false,true,false,true,true},
					{false,false,true ,false,true ,false,true ,true ,false,true ,true,true},
					{true ,true ,true ,false ,false,true ,true ,false,false,true ,true,true},
					{true,true,false,true ,false,false,true ,true ,false,false ,true ,true},
					{true,true ,true,true,true ,true,true,true ,true ,true ,true,true }
				};
			model.setVerticalLines(vLines);
	    	model.setHorizontalLines(hLines);
	    	model.setStartPosition(0, 1);
	    	model.setKeyPosition(9, 8);
	    	model.setBoxPosition(0, 11);
	    	
	    	model.initEnemy(9, 5, 1);
	    	model.initEnemy2(1, 11, 2);
	    	model.initEnemy3(4, 5, 3);
	    	model.setTotalTimeLeft(60);
		}if(level== 3) {
			model = new Model();
			boolean[][] vLines = new boolean[][]{
					{false,false,false ,false,false,false,false ,false,false,false,false,false},
					{false,false ,false,false,false,true ,false,false,false,true,true ,true },
					{true ,false,false,false,false,true ,false,false,false,true,true ,true },
					{true ,true ,false,false,false,true ,true ,true ,false,true,true ,true },
					{true ,true ,true ,false,false,true ,true ,false,true ,true,true ,true },
					{false,true ,true ,true ,false,true ,false,false,false,true ,true,true},
					{false,false,false,true ,false,false ,true,true ,false,true,true,true},
					{false,false,true ,false,true ,false,true ,true ,false,true ,true,true},
					{true ,true ,true ,true ,false,true ,true ,false,false,true ,true,true},
					{false,false,false,true ,true,false,true ,true ,false,true ,true ,true},
					{false,false,true ,false,true ,false,true ,false,false,true,true,true},
					{false,false,false ,false,false,true ,false,false,false,true,true ,true}
				 };
	    		boolean[][] hLines = new boolean[][]{
	    			{true,true ,true,true,true ,true,true,true ,true ,true ,true,true },
					{false,true ,true ,true ,true ,false ,true ,true ,false , true ,true,true},
					{false,false,true ,true ,true ,false,false,true ,true ,true ,true ,true},
					{false,false,false,true ,true ,true ,false,false,false,true ,true,true},
					{false,false,false,false,true ,false,false,true ,true ,true ,true,true},
					{true ,true ,false,false,false,false ,true ,false ,false ,false,true ,true  },
					{false,true ,true ,true ,true ,true ,false,false,false,true ,true ,true },
					{true ,false,false,false,true ,false,true ,false,true ,false,true,true  },
					{false,true ,false,false,false,true ,false,true ,true ,false ,true ,true },
					{true ,true ,false,true ,false,false ,true ,false,false,true ,true,true },
					{false,true ,true ,false,true ,false,false,true ,true ,false,true ,true  },
					{true,true ,true,true,true ,true,true,true ,true ,true ,true,true }
				};
	    	model.setVerticalLines(vLines);
		    model.setHorizontalLines(hLines);
		    model.setStartPosition(0, 1);
		    model.setKeyPosition(4, 6);
		    model.setBoxPosition(9, 1);
		    	
		    model.initEnemy(4, 6, 1);
		    model.initEnemy2(9, 2, 2);
		    model.initEnemy3(4, 4, 3);
		    model.setTotalTimeLeft(40);
		}
    	
    	return model;
    }
    
    /**
     * This method sets the gotKey boolean to either true or false.
     * @param hasKey - True or false
     */
    public void setGotKey(boolean hasKey){
    	gotKey = hasKey;
    }
    
    /**
     * This method updates the world
     * @param deltaTime
     */
    public void update(float deltaTime) {
        if (gameOver)
            return;
       

        tickTime += deltaTime;

        while (tickTime > tick) {
            tickTime -= tick;
          
            currentX = model.getCurrentX();
            currentY = model.getCurrentY();
            
            //key collision
            if(model.getCurrentX() == model.getKeyPointX() && model.getCurrentY() == model.getKeyPointY()) {
            	setGotKey(true);
            	model.setKeyPosition(0, 0);
            	if(Settings.soundEnabled)
                    Assets.foundkey.play(1);
            }
            
            //box collision
            if(model.getCurrentX() == model.getBoxX() && model.getCurrentY() == model.getBoxY()){
            	if(gotKey){
            		//gameOver = true;
            		levelComplete = true;
            		if(Settings.soundEnabled)
                        Assets.levelup.play(1);
            	}else{
            		//some msg saying get the key first.
            		
            	}
            }
            
            //timer check
            if(model.getTotalTimeLeft() <= 0){
            	gameOver = true;
            }
            
            //enemy collision
            if(model.getCurrentX() == model.getEnemyX() && model.getCurrentY() == model.getEnemyY()){
            	if(!model.hasInvincible){
            		gameOver = true;
            	}
            }
            
            if(model.getCurrentX() == model.getEnemyX2() && model.getCurrentY() == model.getEnemyY2()){
            	if(!model.hasInvincible){
            		gameOver = true;
            	}
            }
            
            if(model.getCurrentX() == model.getEnemyX3() && model.getCurrentY() == model.getEnemyY3()){
            	if(!model.hasInvincible){
            		gameOver = true;
            	}
            }
            
            if (currentX == stain.x && currentY == stain.y) {
                score += SCORE_INCREMENT;
                if(Settings.soundEnabled)
                    Assets.foundkey.play(1);
                if(stain.type == stain.TYPE_1){
                	//score = 90;
                	model.hasNoWallPower = true;
                	//no two power up should be allowed at once
                	model.hasInvincible = false;
                	if(model.noWallStepCount != 8){
                		model.noWallStepCount = 8;
                	}
                	if(model.invincibleStepCount != 8){
                		model.invincibleStepCount = 8;
                	}
                }
                if(stain.type == stain.TYPE_2 || stain.type == stain.TYPE_3){
                	//model.setTotalTimeLeft(model.getTotalTimeLeft()+10);
                	model.hasInvincible = true;
                	model.hasNoWallPower = false;
                	if(model.invincibleStepCount != 8){
                		model.invincibleStepCount = 8;
                	}
                	if(model.noWallStepCount != 8){
                		model.noWallStepCount = 8;
                	}
                }
                
        
                placeStain();


                if (score % 100 == 0 && tick - TICK_DECREMENT > 0) {
                    tick -= TICK_DECREMENT;
                }
            }
        }
    }
}
