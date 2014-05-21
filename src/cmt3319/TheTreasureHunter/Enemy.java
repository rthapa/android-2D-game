package cmt3319.TheTreasureHunter;

import java.util.Random;
/**
 * This class is the Enemy class also known as AI (Artificial Intelligence) class.
 * @author Rabi Thapa
 * @since 2014-04-29
 */
public class Enemy {
	
	  public static final int UP = 0;
	  public static final int LEFT = 1;
	  public static final int DOWN = 2;
	  public static final int RIGHT = 3;
	    
	public int direction; 
	public boolean[][] verticalLines;
	public boolean[][] horizontalLines;
	public int enemyCurrentX, enemyCurrentY;  
	Model model;
	public int enemyType;
	public int enemyType2;
	public int enemyType3;
	int countTouch = 0;
	int countTouch2 = 0;
	int countTouch3 = 0;
	
	public Enemy (World world, int type){   
		model = world.model;
        verticalLines = model.getVerticalLines();
        horizontalLines = model.getHorizontalLines();
        
        enemyType = type;
        enemyType2 = model.getEnemyType2();
        enemyType3 = model.getEnemyType3();
    }
	
	
	/**
	 * This function activates the enemy 1
	 */
	public void activateEnemy(){
		
		if(enemyType == 1){
			int x = model.getEnemyX();
			int y= model.getEnemyY();
			
			if(y != 0 && !horizontalLines[y-1][x] && countTouch == 0) {
				direction = UP;
				try {
					Thread.sleep(500);
					model.setEnemyY(y -= 1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    	}else{
	    		countTouch = 1;
	    	}

			if(y != y-1 && !horizontalLines[y][x] && countTouch == 1){
				direction = DOWN;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		model.setEnemyY(y += 1);
			}else{
				countTouch = 0;
			}
			
		}
	}
	
	/**
	 * This function activates the enemy 2
	 */
	public void activateEnemy2(){
		if(enemyType2 == 2){
			int x = model.getEnemyX2();
			int y= model.getEnemyY2();
			
			if(x != x-1 && !verticalLines[y][x] && countTouch2 == 0) {
				direction = RIGHT;
				try {
					Thread.sleep(500);
					model.setEnemyX2(x += 1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    	}else{
	    		countTouch2 = 1;
	    	}

			if(x != 0 && !verticalLines[y][x-1] && countTouch2 == 1){
				direction = LEFT;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		model.setEnemyX2(x -= 1); 
			}else{
				countTouch2 = 0;
			}
			
		}
	}
	
	//smart roaming enemy
	/**
	 * This function activates the enemy 3 (Smart AI)
	 */
	public void activateEnemy3(){
		if(enemyType3 == 3){
		
			int x = model.getEnemyX3();
			int y= model.getEnemyY3();
			
			direction = UP;
		
			//go up
			if(y != 0 && !horizontalLines[y-1][x] && countTouch3 == 0) {
				direction = UP;
				try {
					Thread.sleep(200);
					model.setEnemyY3(y -= 1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(horizontalLines[y-1][x]){
					//upCheck = true;
					countTouch3 = 1;
				}
	    		
	    	}else{
	    		if(countTouch3 == 0){
	    		countTouch3 = 1;
	    		}
	    	}
			
			//go right
			if(x != x-1 && !verticalLines[y][x] && countTouch3 == 1) {
				direction = RIGHT;
				try {
					Thread.sleep(200);
					model.setEnemyX3(x += 1);
					//upCheck = false;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(verticalLines[y][x]){
					countTouch3 = 0;
				}
	    		
	    	}else{
	    		if(countTouch3 == 1){
	    			countTouch3 = 2;
	    		}
	 
	    	}
			
			//go left
			if(x != 0 && !verticalLines[y][x-1] && countTouch3 == 2){
				direction = LEFT;
				try {
					Thread.sleep(200);
		    		model.setEnemyX3(x -= 1); 

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//checks for array out of bound
				if((x-1)<0){
					countTouch3 = 0;
				}else{
					if(x == 0 && verticalLines[y][x-1]){
						countTouch3 = 0;
					}
				}
			}else{
				if(countTouch3 == 2){
					countTouch3 = 3;
				}
			}
			
			//go down
			if(y != y-1 && !horizontalLines[y][x] && countTouch3 == 3){
				direction = DOWN;
				try {
					Thread.sleep(200);
		    		model.setEnemyY3(y += 1);
		    		//countTouch3 = 1;
		    		
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//if has exit right take it!
				if(x != x-1 && !verticalLines[y][x]) {
					direction = RIGHT;
					try {
						Thread.sleep(200);
						model.setEnemyX3(x += 1);
						//upCheck = false;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(verticalLines[y][x]){
						countTouch3 = 0;
					}
		    		
		    	}
				
				if(horizontalLines[y][x]){
					countTouch3 = getRandom(1, 2);//2; //0;
				}
			}else{
				if(countTouch3 == 3){
					countTouch3 = 0;
				}
			}
	
		}
	}
	
	/**
	 * Returns a random number from one int to another int eg:(5 to 8  would return random number
	 * between 5 to 8)
	 * @param from
	 * @param to
	 * @return
	 */
	public int getRandom(int from, int to) {
	    if (from < to)
	        return from + new Random().nextInt(Math.abs(to - from));
	    return from - new Random().nextInt(Math.abs(to - from));
	}
	
}
