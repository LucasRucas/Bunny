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
    private int move = BattleBotArena.STAY;


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
    BotInfo[] enemyBots = new BotInfo[16];
    BotInfo[] deadBots;
    BotInfo me;


    public int getMove(BotInfo myself, boolean shotOK, BotInfo[] alive, BotInfo[] dead, Bullet[] bullets){
        liveBots = alive;
        me = myself;
        deadBots = dead;
        setEnemy();


        // for overheating
        //if (overheat){try{Thread.sleep(sleep);}catch (Exception e){}}



        /*switch(avoidTombstone(trackTombstone())){
            case "up":
                return BattleBotArena.RIGHT;
            case "left":
                return BattleBotArena.DOWN;
            case "down":
                return BattleBotArena.LEFT;
            case "right":
                return BattleBotArena.UP;
            default:
        }*/


        switch(shoot(trackClosestEnemy())){
			case "up":
				return BattleBotArena.FIREUP;
			case "down":
				return BattleBotArena.FIREDOWN;
			case "left":
				return BattleBotArena.FIRELEFT;
			case "right":
				return BattleBotArena.FIRERIGHT;
			default:
        }


        /*switch(chaseEnemy()){
            case "up":
                return BattleBotArena.UP;
            case "left":
                return BattleBotArena.LEFT;
            case "down":
                return BattleBotArena.DOWN;
            case "right":
                return BattleBotArena.RIGHT;
            default:
        }*/


        return move;
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

    public void setEnemy(){
        int enemyNumber=0;
        for(int i = 0; i < liveBots.length; i++){
            if(!liveBots[i].getTeamName().equals(getTeamName())){
                enemyBots[enemyNumber] = liveBots[i];
                enemyNumber++;
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
    public String avoidTombstone(BotInfo tombstone){
        if(tombstone != null){
            double distanceX = Math.abs(me.getX() - tombstone.getX());
            double distanceY = Math.abs(me.getY() - tombstone.getY());
            if(distanceX > distanceY){
                if(me.getX() < tombstone.getX()){
                    return "right";
                }else{
                    return "left";
                }
            }else{
                if(me.getY() < tombstone.getY()){
                    return "down";
                }else{
                    return "up";
                }
            }
        }
        return "";
    }


    public String shoot(BotInfo target){
		double botSpeed = 1.5;
		double bulletSpeed = 4;
		double distanceX = target.getX() - me.getX();
		double distanceY = target.getY() - me.getY();
		switch(linedShot(target, distanceX, distanceY)){
			case "up":
				if(Math.abs(distanceY) / bulletSpeed <= Math.abs(distanceX) / botSpeed + 1 && Math.abs(distanceY) / bulletSpeed > Math.abs(distanceX) / botSpeed - 1){
					return "up";
				}
				break;
			case "down":
				if(Math.abs(distanceY) / bulletSpeed <= Math.abs(distanceX) / botSpeed + 1 && Math.abs(distanceY) / bulletSpeed > Math.abs(distanceX) / botSpeed - 1){
					return "down";
				}
				break;
			case "left":
				if(Math.abs(distanceX) / bulletSpeed <= Math.abs(distanceY) / botSpeed + 1 && Math.abs(distanceX) / bulletSpeed > Math.abs(distanceY) / botSpeed - 1){
					return "left";
				}
				break;
			case "right":
				if(Math.abs(distanceX) / bulletSpeed <= Math.abs(distanceY) / botSpeed + 1 && Math.abs(distanceX) / bulletSpeed > Math.abs(distanceY) / botSpeed - 1){
					return "right";
				}
			case "instaright":
				return "right";
			case "instaleft":
				return "left";
			case "instadown":
				return "down";
			case "instaup":
				return "up";
		}

        return "";
    }


    public String linedShot(BotInfo target, double x, double y){
		if(Math.abs(y) < 10){
			if(x > 0 && x < 150){
				return "instaright";
			}else{
				return "instaleft";
			}
		}else if(Math.abs(x) < 10){
			if(y > 0 && y < 150){
				return "instadown";
			}else{
				return "instaup";
			}
		}
        switch(target.getLastMove()){
			case BattleBotArena.RIGHT:
				if(y > 0 && y <800){
					return "down";
				}else{
					return "up";
				}
			case BattleBotArena.LEFT:
				if(y > 0 && y <800){
					return "down";
				}else{
					return "up";
				}
			case BattleBotArena.UP:
				if(x > 0 && x < 800){
					return "down";
				}else{
					return "up";
				}
			case BattleBotArena.DOWN:
				if(x > 0 && x < 800){
					return "down";
				}else{
					return "up";
				}
		}
		return "";
    }


	/*
	 * CHANGE THIS ASHMINA
	 */
    public String chaseEnemy(){
        BotInfo target = null;
        switch(playstyle % 2){
            case 0:
                target = trackClosestEnemy();
                break;
            default:
                target = trackLowestEnemy();
                break;
        }
        if(target != null){
            double distanceX = Math.abs(me.getX() - target.getX());
            double distanceY = Math.abs(me.getY() - target.getY());
            if(distanceX < distanceY){
                if(me.getX() < target.getX()){
                    return "right";
                }else{
                    return "left";
                }
            }else{
                if(me.getY() < target.getY()){
                    return "down";
                }else{
                    return "up";
                }
            }
        }
        return "";
    }


    public BotInfo trackClosestEnemy(){
        BotInfo closestTarget = null;
        double distanceX;
        double distanceY;
        double hypotenuse;
        double closestHypotenuse =1000;
        for(int i = 0; i < enemyBots.length; i++ ){
            if(enemyBots[i] != null){
                distanceX = Math.abs(enemyBots[i].getX() - me.getX());
                distanceY = Math.abs(enemyBots[i].getY() - me.getY());
                hypotenuse = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
                if(hypotenuse < closestHypotenuse){
                    closestHypotenuse = hypotenuse;
                    closestTarget = enemyBots[i];
                }
            }
        }
        return closestTarget;
    }


    public BotInfo trackLowestEnemy(){
        BotInfo lowestTarget = null;
        double lowestScore =1000;
        for(int i = 0; i < enemyBots.length; i++ ){
            if(enemyBots[i] != null){
                if(enemyBots[i].getScore() < lowestScore){
                    lowestScore = enemyBots[i].getScore();
                    lowestTarget = enemyBots[i];
                }
            }
        }
        return lowestTarget;
    }


    public BotInfo trackTombstone(){
        BotInfo tombstone = null;
        double distanceX;
        double distanceY;
        double hypotenuse;
        double closestHypotenuse = 1000;
        for(int i = 0; i < deadBots.length; i++ ){
            distanceX = Math.abs(deadBots[i].getX() - me.getX());
            distanceY = Math.abs(deadBots[i].getY() - me.getY());
            hypotenuse = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
            if(hypotenuse < closestHypotenuse && hypotenuse < 40){
                closestHypotenuse = hypotenuse;
                tombstone = deadBots[i];
            }
        }
        return tombstone;
    }


    public boolean tombstoneInWay(){
        for(int i = 0; i < deadBots.length; i++ ){
            if(me.getX() == deadBots[i].getX() || me.getY() == deadBots[i].getY()){
                return true;
            }
        }
        return false;
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
        setEnemy();
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



