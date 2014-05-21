package cmt3319.TheTreasureHunter;
/**
 * This class is the timer for the Game.
 * @author Rabi Thapa
 * @since 2014-04-29
 */
public class GameTimer{

	public int delay = 1000;
	public boolean isPaused = false;
	Model model;
	
	public GameTimer(World world){   
		
		model = world.model;
		//totalTimeLeft = model.totalTimeLeft;
    }
	
	/**
	 * runs the timer (decrements the total time left)
	 */
	public void runTimer(){
		int t = model.getTotalTimeLeft();
		if(t > 0){
			
				try {
					Thread.sleep(delay);
					model.setTotalTimeLeft(t-1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
		}

		
	}

	public int getTimeLeft(){
		return model.getTotalTimeLeft();
	}
	
	public void pause(){
		isPaused = true;
	}
	
	public void resume(){
		isPaused = false;
	}

}
