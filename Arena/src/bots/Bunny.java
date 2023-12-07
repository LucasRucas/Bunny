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

    public int getMove(BotInfo me, boolean shotOK, BotInfo[] alive, BotInfo[] deadBots, Bullet[] bullets){
		liveBots = alive;

		// for overheating
		if (overheat){try{Thread.sleep(sleep);}catch (Exception e){}}

		// increase the move counter
		moveCount++;

		// Is it time to send a message?
		if (--msgCounter == 0)
		{
			move = BattleBotArena.SEND_MESSAGE;
			moveCount = 99;
		}
		// Time to choose a new move?
		else if (moveCount >= 30+(int)Math.random()*60)
		{
			moveCount = 0;
			int choice = (int)(Math.random()*8);
			if (choice == 0)
			{
				move = BattleBotArena.UP;
				current=up;
			}
			else if (choice == 1)
			{
				move = BattleBotArena.DOWN;
				current=down;
			}
			else if (choice == 2)
			{
				move = BattleBotArena.LEFT;
				current=left;
			}
			else if (choice == 3)
			{
				move = BattleBotArena.RIGHT;
				current=right;
			}
			else if (choice == 4)
			{
				move = BattleBotArena.FIREUP;
				moveCount = 99; // make sure we choose a new move next time
				current=up;
			}
			else if (choice == 5)
			{
				move = BattleBotArena.FIREDOWN;
				moveCount = 99; // make sure we choose a new move next time
				current=down;
			}
			else if (choice == 6)
			{
				move = BattleBotArena.FIRELEFT;
				moveCount = 99; // make sure we choose a new move next time
				current=left;
			}
			else if (choice == 7)
			{
				move = BattleBotArena.FIRERIGHT;
				moveCount = 99; // make sure we choose a new move next time
				current=right;
			}
		}
		return move;
	}

	public void setAlly(){
		int allyNumber=0;
		for(int i = 0; i < liveBots.length; i++){
			if(liveBots[i].getTeamName().equals(getTeamName())){
				allyBots[allyNumber] = liveBots[i];
				System.out.println(allyBots[allyNumber].getName());
				allyNumber++;
			}
		}
	}

	public void setTeam(){
		allyBots[0].getX();
		allyBots[0].getY();
		allyBots[1].getX();
		allyBots[1].getY();
		allyBots[2].getX();
		allyBots[2].getY();
		allyBots[3].getX();
		allyBots[3].getY();
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
		current = up;
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
