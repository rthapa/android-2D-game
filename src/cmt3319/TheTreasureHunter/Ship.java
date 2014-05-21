package cmt3319.TheTreasureHunter;

/**
 * This class is the Ship(player) class. It initialises all the methods and variables for the ship.s
 * @author Rabi Thapa
 * @since 2014-04-29
 */

public class Ship {
	
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    
    public int direction;    
    
    Model model;
   
    int x;
    int y;
    public boolean[][] verticalLines;
	public boolean[][] horizontalLines;
	
    public Ship(World world) {   
    	model = world.model;
    	
        direction = RIGHT;
        
        verticalLines = model.getVerticalLines();
        horizontalLines = model.getHorizontalLines();
    }
    
    /**
     * This function turns the ship left with logical algorithm
     */
    public void turnLeft() {
       
    	direction = LEFT;
    	x = model.getCurrentX();
    	y = model.getCurrentY();
    	
    	//wrap
    	if(!(x <= 0)){
    		//checking if player has no wall power ups
    		if(model.hasNoWallPower){
    			model.setCurrentX(x -= 1);
    			model.noWallStepCount--;
    			//reseting the counter if n steps are done
    			if(model.noWallStepCount <= 0){
    				model.hasNoWallPower = false;
    				model.noWallStepCount = model.maxNoWallStep;
    			}
    		}else{
    			if(x != 0 && !verticalLines[y][x-1]) {
    				if(model.hasInvincible){
    					model.invincibleStepCount--;
    	    			if(model.invincibleStepCount <= 0){
    	    				model.hasInvincible = false;
    	    				model.invincibleStepCount = model.maxInvincibleStep;
    	    			}
    				}
    	    		model.setCurrentX(x -= 1); 
    	    	}
    		}
    	}
    	
    }
    /**
     * This function turns the ship right with logical algorithm
     */
    public void turnRight() {

    	direction = RIGHT;
    	x = model.getCurrentX();
    	y = model.getCurrentY();
    	
    	if(!(x >= 9)){
    		//checking if player has no wall power ups
    		if(model.hasNoWallPower){
    			model.setCurrentX(x += 1);
    			model.noWallStepCount--;
    			//reseting the counter if n steps are done
    			if(model.noWallStepCount <= 0){
    				model.hasNoWallPower = false;
    				model.noWallStepCount = model.maxNoWallStep;
    			}
    		}else{
		    	if(x != x-1 && !verticalLines[y][x]) {
		    		if(model.hasInvincible){
    					model.invincibleStepCount--;
    	    			if(model.invincibleStepCount <= 0){
    	    				model.hasInvincible = false;
    	    				model.invincibleStepCount = model.maxInvincibleStep;
    	    			}
    				}
		    		model.setCurrentX(x += 1);
		    	}
    		}
    	}
    }
    
    /**
     * This function turns the ship up with logical algorithm
     */
    public void turnUp(){
    	direction = UP;	
    	y = model.getCurrentY();
    	x = model.getCurrentX();
    	
    	if(!(y <= 1)){
    		//checking if player has no wall power ups
    		if(model.hasNoWallPower){
    			model.setCurrentY(y -= 1);
    			model.noWallStepCount--;
    			//reseting the counter if n steps are done
    			if(model.noWallStepCount <= 0){
    				model.hasNoWallPower = false;
    				model.noWallStepCount = model.maxNoWallStep;
    			}
    		}else{
		    	if(y != 0 && !horizontalLines[y-1][x]) {
		    		if(model.hasInvincible){
    					model.invincibleStepCount--;
    	    			if(model.invincibleStepCount <= 0){
    	    				model.hasInvincible = false;
    	    				model.invincibleStepCount = model.maxInvincibleStep;
    	    			}
    				}
		    		model.setCurrentY(y -= 1);
		    	}
    		}
    	}
    }
    
    /**
     * This function turns the ship down with logical algorithm
     */
    public void turnDown(){
    	direction = DOWN;
    	y = model.getCurrentY();
    	x = model.getCurrentX();
    	
    	if(!(y >=11)){
    		//checking if player has no wall power ups
    		if(model.hasNoWallPower){
    			model.setCurrentY(y += 1);
    			model.noWallStepCount--;
    			//reseting the counter if n steps are done
    			if(model.noWallStepCount <= 0){
    				model.hasNoWallPower = false;
    				model.noWallStepCount = model.maxNoWallStep;
    			}
    		}else{
		    	if(y != y-1 && !horizontalLines[y][x]) {
		    		if(model.hasInvincible){
    					model.invincibleStepCount--;
    	    			if(model.invincibleStepCount <= 0){
    	    				model.hasInvincible = false;
    	    				model.invincibleStepCount = model.maxInvincibleStep;
    	    			}
    				}
		    		model.setCurrentY(y += 1);
		    	}
    		}
    	}
    	
    }
    
    /**
     * Sets horizontal lines logic in order to make the ship move logically.
     * @param lines
     */
    public void setHorizontalLines(boolean[][] lines) {
		horizontalLines = lines;
	}
    
    /**
     * Sets horizontal lines logic in order to make the ship move logically.
     * @param lines
     */
	public void setVerticalLines(boolean[][] lines) {
		verticalLines = lines;
	}
          
}
