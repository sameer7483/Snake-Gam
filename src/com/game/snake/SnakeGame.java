package com.game.snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A Snake class that keeps track of the board state, with 3 items:
    Score (int)
    Apple (Point)
    Snake (List of Point) 
 */
public class SnakeGame {

    /**
     * Args:
     *    rows (int): number of rows of the board
     *    cols (int): number of columns of the board
     *    initialSnake (List of Point): list of points representing snake's initial position ordered by tail first and head last
     *    initialDirection (int): a value between 0-3 representing snake's initial direction
     */
	int rows;
	int cols;
	Point currHead;
	Point currApp;
	List<Point> snake;
	int currScore;
	int currDir;
	boolean gameOver;
    public SnakeGame(int rows, int cols, List<Point> initialSnake, int initialDirection) {
    	this.rows = rows;
    	this.cols = cols;
    	this.snake = new ArrayList<>(initialSnake);
    	currScore = 0;
    	currDir = initialDirection;
    	currHead =initialSnake.get(0);	
    }

    public List<Point> getSnake() {

    	Point newPoint = null;
    	
    	if(currDir == 0) {
    		newPoint = new Point(currHead.row-1, currHead.col);
    	}
    	else if(currDir == 1){
    		newPoint = new Point(currHead.row, currHead.col+1);
    	}
    	else if(currDir == 2) {
    		newPoint = new Point(currHead.row+1, currHead.col);
    	}
    	else if(currDir == 3) {
    		newPoint = new Point(currHead.row, currHead.col-1);
    	}
    	if(snake.contains(newPoint)) {
    		gameOver = true;
    		return snake;
    	}
    	if(newPoint.equals(currApp)) {
    		snake.add(newPoint);
    		currApp  =null;
    		currScore += 1;
    	}
    	else {
        	snake.add(newPoint);
        	snake.remove(0);
        	}
        	currHead = newPoint;		
    	return snake;
    	
    }

    public Point getApple() {
    	
    	if(currApp == null) {
    		Random rand = new Random();
        	Point p = new Point(rand.nextInt(rows), rand.nextInt(cols));
        	while(snake.contains(p)) {
        		p = new Point(rand.nextInt(rows), rand.nextInt(cols));
        	}
        	currApp = p;
    	}
    	return currApp;
    	
    }

    public int getScore() {
    	return this.currScore;
    }

    /**
     * Args:
     *     command (int): a value between 0-4 representing snake's new direction (0-Up, 1-Right, 2-Down, 3-left, 4-zoom)
     */
    public void input(int command) {
    	if(Math.abs(command-currDir)!=2)    	
    		currDir = command;
    }

    /**
     * Returns:
     *     true if game continues
     *     false if snake collides with self or one of the walls
     */
    public boolean tick() {
    	
    	if(currHead.row < 0 || currHead.row >= rows || currHead.col < 0 || currHead.col >= cols)
    		return false;
    	if(gameOver) return false;
    	return true;
    	
    }
}
