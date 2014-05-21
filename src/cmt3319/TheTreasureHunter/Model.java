package cmt3319.TheTreasureHunter;

/**
 * This class is the model class which passes data to other class. It can also be described as a bridge class
 * which initialises other class variables.
 * @author Rabi Thapa
 * @since 2014-04-29
 */

public class Model  {
	public static final int UP = 0, DOWN = 1, RIGHT = 2, LEFT = 3;
	
	public boolean[][] verticalLines;
	public boolean[][] horizontalLines;
	private int modelWidth, modelHeight;         //stores the width and height of the maze
	public int currentX, currentY;   //stores the current location of the snake
	private int keyPointX, keyPointY;       //stores the finishing of the maze
	private int boxPointX, boxPointY;
	private boolean isGameFinish;
	
	private int enemyX, enemyY, enemyType;
	private int enemyX2, enemyY2, enemyType2;
	private int enemyX3, enemyY3, enemyType3;
	
	//powerUps
	 public boolean hasNoWallPower = false;
	 //max 5 steps allowed after power ups?
	 public int noWallStepCount = 8;
	 public int maxNoWallStep = 8;
	 //public int timerPowerUp = 10;
	 public boolean hasInvincible = false;
	 public int invincibleStepCount = 8;
	 public int maxInvincibleStep = 8;
	 
	 public int totalTimeLeft;
	 
	public int getModelWidth() {
		return modelWidth;
	}
	public int getModelHeight() {
		return modelHeight;
	}
	
	public boolean isGameComplete() {
		return isGameFinish;
	}
	public void setGameComplete() {
		isGameFinish =true;
	}
	public void setStartPosition(int x, int y) {
		currentX = x;
		currentY = y;
	}
	public int getKeyPointX() {
		return keyPointX;
	}
	public int getKeyPointY() {
		return keyPointY;
	}
	public int getBoxX() {
		return boxPointX;
	}
	public int getBoxY() {
		return boxPointY;
	}
	public void setKeyPosition(int x, int y) {
		keyPointX = x;
		keyPointY = y;
	}
	public void setBoxPosition(int x, int y) {
		boxPointX = x;
		boxPointY = y;
	}
	public int getCurrentX() {
		return currentX;
	}
	public int getCurrentY() {
		return currentY;
	}
	
	public void setCurrentX(int x) {
		currentX = x;
	}
	public void setCurrentY(int y) {
		currentY = y;
	}
	public boolean[][] getHorizontalLines() {
		return horizontalLines;
	}
	public void setHorizontalLines(boolean[][] lines) {
		horizontalLines = lines;
		modelWidth = horizontalLines.length;
	}
	public boolean[][] getVerticalLines() {
		return verticalLines;
	}
	public void setVerticalLines(boolean[][] lines) {
		verticalLines = lines;
		modelHeight = verticalLines.length;
	}
	/**
	 * Initialises enemy 1
	 * @param x
	 * @param y
	 * @param type
	 */
	public void initEnemy(int x, int y, int type){
		enemyX = x;
		enemyY = y;
		enemyType = type;
	}
	
	public int getEnemyX(){
		return enemyX;
	}
	public int getEnemyY(){
		return enemyY;
	}
	public int getEnemyType(){
		return enemyType;
	}
	
	public void setEnemyX(int x){
		enemyX = x;
	}
	public void setEnemyY(int y){
		enemyY = y;
	}
	
	/**
	 * Initialises enemy 2  
	 * @param x
	 * @param y
	 * @param type
	 */
	public void initEnemy2(int x, int y, int type){
		enemyX2 = x;
		enemyY2 = y;
		enemyType2 = type;
	}
	
	public int getEnemyX2(){
		return enemyX2;
	}
	public int getEnemyY2(){
		return enemyY2;
	}
	public int getEnemyType2(){
		return enemyType2;
	}
	
	public void setEnemyX2(int x){
		enemyX2 = x;
	}
	public void setEnemyY2(int y){
		enemyY2 = y;
	}
	
	/**
	 * Initialises enemy 3
	 * @param x
	 * @param y
	 * @param type
	 */
	public void initEnemy3(int x, int y, int type){
		enemyX3 = x;
		enemyY3 = y;
		enemyType3 = type;
	}
	
	public int getEnemyX3(){
		return enemyX3;
	}
	public int getEnemyY3(){
		return enemyY3;
	}
	public int getEnemyType3(){
		return enemyType3;
	}
	
	public void setEnemyX3(int x){
		enemyX3 = x;
	}
	public void setEnemyY3(int y){
		enemyY3 = y;
	}
	
	public void setTotalTimeLeft(int time){
		totalTimeLeft = time;
	}
	public int getTotalTimeLeft(){
		return totalTimeLeft;
	}

}