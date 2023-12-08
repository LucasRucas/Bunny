package bots;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.lang.Math;
import arena.BattleBotArena;
import arena.BotInfo;
import arena.Bullet;

public class Bunny extends Bot{
    private String name;

	private boolean overheat = false;

	private int sleep = (int)(Math.random()*5+1);
	private int moveCount = 99;
	private int msgCounter = 0;
	private int move = BattleBotArena.RIGHT;

	private int playstyle = 0;

	/**
	 * Bot image
	 */
	private Image current, up, down, right, left;
	
	/**
	 * Store the images loaded by the arena
	 */
	public void loadedImages(Image[] images)
	{
		if (images != null)
		{
			if (images.length > 0)
				up = images[0];
			if (images.length > 1)
				down = images[1];
			if (images.length > 2)
				right = images[2];
			if (images.length > 3)
				left = images[3];
			current = up;
		}
	}

	BotInfo[] liveBots;
	BotInfo[] allyBots = new BotInfo[4];
	BotInfo[] deadBots;
	BotInfo me;

    public int getMove(BotInfo myself, boolean shotOK, BotInfo[] alive, BotInfo[] dead, Bullet[] bullets){
		liveBots = alive;
		me = myself;
		deadBots = dead;

		// for overheating
		//if (overheat){try{Thread.sleep(sleep);}catch (Exception e){}}

		//play closest robot
		if(playstyle % 2 == 0){

		}
		//play lowest score
		else{

		}

		if(tombstoneObstacle() == 2 || tombstoneObstacle() == 4){
			return BattleBotArena.UP;
		}else if(tombstoneObstacle() == 1 || tombstoneObstacle() == 3){
			return BattleBotArena.LEFT;
		}


		return BattleBotArena.RIGHT;
	}

	// Lucas
	public void setAlly(){
		int allyNumber=0;
		for(int i = 0; i < liveBots.length; i++){
			if(liveBots[i].getTeamName().equals(getTeamName())){
				allyBots[allyNumber] = liveBots[i];
				allyNumber++;
			}
		}

		// bot 0 and 2 will play for closest enemy
		// bot 1 and 3 will play for lowest score enemy
		for(int i = 0; i < 3; i++){
			if(me.getBotNumber() > allyBots[i].getBotNumber()){
				playstyle++;
			}
		}
	}
	// Faraz
	public void getAlly()
	{
		for(int i=0; i < allyBots.length; i++)
		{
			System.out.println(allyBots[i]);
		}
	}
	// Lucas
	public int tombstoneObstacle(){
		//0 no stone
		//1 to the left
		//2 up
		//3 to the right
		//4 down
		BotInfo tombstone = closestTomb();
		double hypotenuse = Math.sqrt((me.getX() - tombstone.getX())*(me.getX() - tombstone.getX())) + ((me.getY() - tombstone.getY()) * (me.getY() - tombstone.getY()));
		double distanceX = Math.abs(me.getX() - tombstone.getX());
		double distanceY = Math.abs(me.getY() - tombstone.getY());

		if(hypotenuse < 100){
			if(distanceX < distanceY){
				if(me.getX() < tombstone.getX()){
					return 3;
				}else{
					return 1;
				}
			}else{
				if(me.getY() < tombstone.getY()){
					return 4;
				}else{
					return 2;
				}
			}
		}
		return 0;
	}

	public BotInfo closestTomb(){
		BotInfo closestStone = null;
		double hypotenuse;
		double smallestHypotenuse =1000;
		for(int i = 0; i < deadBots.length; i++ ){
			hypotenuse = Math.sqrt((me.getX() - deadBots[i].getX())*(me.getX() - deadBots[i].getX())) + ((me.getY() - deadBots[i].getY()) * (me.getY() - deadBots[i].getY()));
			if(hypotenuse <= smallestHypotenuse){
				smallestHypotenuse = hypotenuse;
				closestStone = deadBots[i];
			}
		}
		return closestStone;
	}


	/* public void trackBullet(BotInfo me, Bullet[] bullets){
		double hypotenuse = 1000;
		double mx;
		double my;
		double bx;
		double by;
		for(int i = 0; i < bullets.length; i++){
			mx= me.getX();
			my= me.getY();
			bx = bullets[i].getX();
			by = bullets[i].getY();
			hypotenuse = Math.sqrt(((mx - bx)*(mx - bx)) + ((my - by) * (my - by)));
		}
	} */

	/**
	 * This bot does nothing to prepare for the next round.
	 */
	public void newRound()
	{
		setAlly();
		current = right;
	}

	/**
	 * This bot does not send messages.
	 */
	public String outgoingMessage()
	{
		return null;
	}

	/**
	 * Construct and return my name
	 */
	public String getName()
	{
		if (name == null)
			name = "Bunny"+(botNumber<10?"0":"")+botNumber;
		return name;
	}

	/**
	 * Team "Bunnies"
	 */
	public String getTeamName()
	{
		return "Bunnies";
	}

	/**
	 * Draws the bot at x, y
	 * @param g The Graphics object to draw on
	 * @param x Left coord
	 * @param y Top coord
	 */
	public void draw (Graphics g, int x, int y)
	{
		if (current != null)
			g.drawImage(current, x, y, Bot.RADIUS*2, Bot.RADIUS*2, null);
		else
		{
			g.setColor(Color.lightGray);
			g.fillOval(x, y, Bot.RADIUS*2, Bot.RADIUS*2);
		}
	}

	/**
	 * This bot does not use incoming messages.
	 */
	public void incomingMessage(int botNum, String msg)
	{
	}

	/**
	 * Image names
	 */
	public String[] imageNames()
	{
		String[] images = {"bunnyUp.png","bunnyDown.png","bunnyRight.png","bunnyLeft.png"};
		return images;
	}
}
