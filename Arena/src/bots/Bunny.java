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
    private int msgCounter = 2;
    private int move = BattleBotArena.STAY;

    long seconds;
    int[] closeBotLRUD = new int[4];
    long lastTripleShot = System.currentTimeMillis()/1000;
    double storedX = 1000;
    double storedY = 1000;
    int counter = 0;

    double closeOverheatDistance;
    boolean overheatSwitch = false;
    
    BotInfo[] liveBots;
    BotInfo[] aliveAllyBots = new BotInfo[4];
    BotInfo[] aliveEnemyBots = new BotInfo[12];
    BotInfo[] deadBots;
    BotInfo me;

    private int playstyle = 0;
    private int countDown = 0;
    private int round = 0;
    private String nextMessage = null;
    boolean once = true;

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

    public int getMove(BotInfo myself, boolean shotOK, BotInfo[] alive, BotInfo[] dead, Bullet[] bullets){
        liveBots = alive;
        me = myself;
        deadBots = dead;
        setAlly(alive, myself);
        setEnemy(alive);
        seconds = System.currentTimeMillis()/1000;
        if (seconds < 10000){
            return BattleBotArena.STAY;
        }

        if (--msgCounter == 0)
        {
            msgCounter = 10;
            return BattleBotArena.SEND_MESSAGE;
        }
        switch(avoidTombstone(trackTombstone(dead))){
            case "up":
                return BattleBotArena.RIGHT;
            case "left":
                return BattleBotArena.DOWN;
            case "down":
                return BattleBotArena.LEFT;
            case "right":
                return BattleBotArena.UP;
        }
    
        switch(avoidBullets(trackBullets(bullets))){
            case "up":
                return BattleBotArena.UP;
            case "left":
                return BattleBotArena.LEFT;
            case "down":
                return BattleBotArena.DOWN;
            case "right":
                return BattleBotArena.RIGHT;
        }

        if (--countDown <= 0 && shotOK){
            countDown = 2;
            switch(shoot(trackClosestEnemy())){
                case "up":
                    return BattleBotArena.FIREUP;
                case "down":
                    return BattleBotArena.FIREDOWN;
                case "left":
                    return BattleBotArena.FIRELEFT;
                case "right":
                    return BattleBotArena.FIRERIGHT;
                case "instaright":
                    countDown = 60;
                    return BattleBotArena.FIRERIGHT;
                case "instaleft":
                    countDown = 60;
                    return BattleBotArena.FIRELEFT;
                case "instadown":
                    countDown = 60;
                    return BattleBotArena.FIREDOWN;
                case "instaup":
                    countDown = 60;
                    return BattleBotArena.FIREUP;
                default:
            }
        }
        
        return move;
    }

    // Lucas
    public void setAlly(BotInfo[] liveBots, BotInfo me){
        int allyNumber=0;
        for(int i = 0; i < aliveAllyBots.length; i++){
            if(aliveAllyBots[i] != null){
                if(aliveAllyBots[i].getTeamName().equals(getTeamName())){
                    aliveAllyBots[i] = null;
                }
            }
        }
        for(int i = 0; i < liveBots.length; i++){
            if(liveBots[i] != null){
                if(liveBots[i].getTeamName().equals(getTeamName())){
                    aliveAllyBots[allyNumber] = liveBots[i];
                    allyNumber++;
                }
            }
        }

        // bot 0 and 2 will play for closest enemy
        // bot 1 and 3 will play for lowest score enemy
        for(int i = 0; i < 3; i++){
            if(me.getBotNumber() > aliveAllyBots[i].getBotNumber()){
                playstyle++;
            }
        }
    }

    // Lucas
    public void setEnemy(BotInfo[] liveBots){
        int enemyNumber=0;
        for(int i = 0; i < aliveEnemyBots.length; i++){
            if(aliveEnemyBots[i] != null){
                if(!aliveEnemyBots[i].getTeamName().equals(getTeamName())){
                    aliveEnemyBots[i] = null;
                }
            }
        }
        for(int i = 0; i < liveBots.length; i++){
            if(liveBots[i] != null){
                if(!liveBots[i].getTeamName().equals(getTeamName())){
                    aliveEnemyBots[enemyNumber] = liveBots[i];
                    enemyNumber++;
                }
            }
        }
    }

    // Faraz
    public void getAlly()
    {
        for(int i=0; i < aliveAllyBots.length; i++)
        {
            System.out.println(aliveAllyBots[i]);
        }
    }

    // Faraz
    public void getEnemy()
    {
        for(int i=0; i < aliveEnemyBots.length; i++)
        {
            System.out.println(aliveEnemyBots[i]);
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

    //Lucas
    public String avoidBullets(Bullet bullet){
        if(bullet != null){
            double distanceX = bullet.getX() - me.getX();
            double distanceY = bullet.getY() - me.getY();
            switch(bulletDirection(bullet)){
                case "right":
                    if(distanceX < 0){
                        if(distanceY < 10)
                            return "down";
                        else
                            return "up";
                    }
                    break;
                case "left":
                    if(distanceX > 0){
                        if(distanceY < 10)
                                return "down";
                        else
                            return "up";
                    }
                    break;
                case "up":
                    if(distanceY > 0){
                        if(distanceX < 10)
                            return "right";
                        else 
                            return "left";
                    }
                    break;
                case "down":
                    if(distanceY < 0){
                        if(distanceX < 10)
                            return "right";
                        else
                            return "left";
                    }
            }
        }
        return "";
    }

    //Lucas
    public String bulletDirection(Bullet bullet){
        if(bullet.getXSpeed() > 0){
            return "right";
        }else if(bullet.getXSpeed() < 0){
            return "left";
        }else if(bullet.getYSpeed() > 0){
            return "down";
        }else if(bullet.getYSpeed() < 0){
            return "up";
        }
        return "";
    }

    //Lucas
    public String shoot(BotInfo target){
        double botSpeed = 1.5;
        double bulletSpeed = 4;
        double distanceX = target.getX() - me.getX();
        double distanceY = target.getY() - me.getY();
        switch(linedShot(target, distanceX, distanceY)){
            case "up":
                if(Math.abs(distanceY) / bulletSpeed <= Math.abs(distanceX) / botSpeed + 0.5 && Math.abs(distanceY) / bulletSpeed > Math.abs(distanceX) / botSpeed - 0.5){
                    if(!obstacle(distanceX, distanceY).equals("up"))
                        return "up";
                }
                break;
            case "down":
                if(Math.abs(distanceY) / bulletSpeed <= Math.abs(distanceX) / botSpeed + 0.5 && Math.abs(distanceY) / bulletSpeed > Math.abs(distanceX) / botSpeed - 0.5){
                    if(!obstacle(distanceX, distanceY).equals("down"))
                        return "down";
                }
                break;
            case "left":
                if(Math.abs(distanceX) / bulletSpeed <= Math.abs(distanceY) / botSpeed + 0.5 && Math.abs(distanceX) / bulletSpeed > Math.abs(distanceY) / botSpeed - 0.5){
                    if(!obstacle(distanceX, distanceY).equals("left"))
                        return "left";
                }
                break;
            case "right":
                if(Math.abs(distanceX) / bulletSpeed <= Math.abs(distanceY) / botSpeed + 0.5 && Math.abs(distanceX) / bulletSpeed > Math.abs(distanceY) / botSpeed - 0.5){
                    if(!obstacle(distanceX, distanceY).equals("right"))
                    return "right";
                }
                break;
            case "instaright":
                return "instaright";
            case "instaleft":
                return "instaleft";
            case "instadown":
                return "instadown";
            case "instaup":
                return "instaup";
        }
        return "";
    }

    //Lucas
    public String linedShot(BotInfo target, double x, double y){
        if(Math.abs(y) < 10){
            if(x > 0 && Math.abs(x) < 50){
                return "instaright";
            }else if(x < 0 && Math.abs(x) < 50){
                return "instaleft";
            }
        }else if(Math.abs(x) < 10){
            if(y > 0 && Math.abs(y) < 50){
                return "instadown";
            }else if(y < 0 && Math.abs(y) < 50){
                return "instaup";
            }
        }
        switch(target.getLastMove()){
            case BattleBotArena.RIGHT:
                if(x < 0 && Math.abs(y) < 3){
                    return "instaleft";
                }
                if(y > 0 && Math.abs(y) < 450){
                    return "down";
                }else if(y < 0 && Math.abs(y) < 450){
                    return "up";
                }
                break;
            case BattleBotArena.LEFT:
                if(x > 0 && Math.abs(y) < 3){
                    return "instaright";
                }
                if(y > 0 && Math.abs(y) < 450){
                    return "down";
                }else if(y < 0 && Math.abs(y) < 450){
                    return "up";
                }
                break;
            case BattleBotArena.UP:
                if(y > 0 && Math.abs(x) < 3){
                    return "instadown";
                }
                if(x > 0 && Math.abs(x) < 400){
                    return "right";
                }else if(x < 0 && Math.abs(x) < 400){
                    return "left";
                }
                break;
            case BattleBotArena.DOWN:
                if(y < 0 && Math.abs(x) < 3){
                    return "instaup";
                }
                if(x > 0 && Math.abs(x) < 400){
                    return "right";
                }else if(x < 0 && Math.abs(x) < 400){
                    return "left";
                }
                break;
            case BattleBotArena.STAY:
                if(Math.abs(y) < 10){
                    if(x > 0 && Math.abs(x) < 500){
                        return "instaright";
                    }else if(x < 0 && Math.abs(x) < 500){
                        return "instaleft";
                    }
                }else if(Math.abs(x) < 10){
                    if(y > 0 && y < 500){
                        return "instadown";
                    }else if(y < 0 && Math.abs(y) < 500){
                        return "instaup";
                    }
                }
                break;
        }
        return "";
    }

    //Lucas
    public String obstacle(double x, double y){
        if(tombstoneInWay().equals("right") && allyInWay().equals("right"))
            return "right";
        else if(tombstoneInWay().equals("left") && allyInWay().equals("left"))
            return "left";
        else if(tombstoneInWay().equals("down") && allyInWay().equals("down"))
            return "down";
        else if(tombstoneInWay().equals("up") && allyInWay().equals("up"))
            return "up";
        return "";
    }

    //Lucas
    public BotInfo trackClosestEnemy(){
        BotInfo closestTarget = null;
        double distanceX;
        double distanceY;
        double hypotenuse;
        double closestHypotenuse =1000;
        for(int i = 0; i < aliveEnemyBots.length; i++ ){
            if(aliveEnemyBots[i] != null){
                distanceX = Math.abs(aliveEnemyBots[i].getX() - me.getX());
                distanceY = Math.abs(aliveEnemyBots[i].getY() - me.getY());
                hypotenuse = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
                if(hypotenuse < closestHypotenuse){
                    closestHypotenuse = hypotenuse;
                    closestTarget = aliveEnemyBots[i];
                }
            }
        }
        return closestTarget;
    }

    //Lucas
    public BotInfo trackLowestEnemy(){
        BotInfo lowestTarget = null;
        double lowestScore =1000;
        for(int i = 0; i < aliveEnemyBots.length; i++ ){
            if(aliveEnemyBots[i] != null){
                if(aliveEnemyBots[i].getScore() < lowestScore){
                    lowestScore = aliveEnemyBots[i].getScore();
                    lowestTarget = aliveEnemyBots[i];
                }
            }
        }
        return lowestTarget;
    }

    //Lucas
    public BotInfo trackTombstone(BotInfo[] deadBots){
        BotInfo tombstone = null;
        double distanceX;
        double distanceY;
        double hypotenuse;
        double closestHypotenuse = 1000;
        for(int i = 0; i < deadBots.length; i++ ){
            if(deadBots[i] != null){
                distanceX = Math.abs(deadBots[i].getX() - me.getX());
                distanceY = Math.abs(deadBots[i].getY() - me.getY());
                hypotenuse = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
                if(hypotenuse < closestHypotenuse && hypotenuse <= 30){
                    closestHypotenuse = hypotenuse;
                    tombstone = deadBots[i];
                }
            }
        }
        return tombstone;
    }

    //Lucas
    public Bullet trackBullets(Bullet [] bullets){
        Bullet closestBullet = null;
        double distanceX;
        double distanceY;
        double hypotenuse;
        double closestHypotenuse = 1000;
        for(int i = 0; i < bullets.length; i++ ){
            if(bullets[i] != null){
                distanceX = Math.abs(bullets[i].getX() - me.getX());
                distanceY = Math.abs(bullets[i].getY() - me.getY());
                hypotenuse = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
                if(hypotenuse < closestHypotenuse && hypotenuse < 60){
                    closestHypotenuse = hypotenuse;
                    closestBullet = bullets[i];
                }
            }
        }
        return closestBullet;
    }

    //Lucas
    public String allyInWay(){
        double obstacleDX;
        double obstacleDY;
        for(int i = 0; i < aliveAllyBots.length; i++ ){
            if(aliveAllyBots[i] != null){
                if(me.getX() <= aliveAllyBots[i].getX() + 1 && me.getX() > aliveAllyBots[i].getX() - 1){
                    if(me.getX() > aliveAllyBots[i].getX()){
                        return "left";
                    }else{
                        return "right";
                    }
                }else if(me.getY() <= aliveAllyBots[i].getY() + 1 && me.getY() <= aliveAllyBots[i].getY() - 1){
                    if(me.getY() > aliveAllyBots[i].getY()){
                        return "up";
                    }else{
                        return "down";
                    }
                }
            }
        }
        return "";
    }

    //Lucas
    public String tombstoneInWay(){
        for(int i = 0; i < deadBots.length; i++ ){
            if(deadBots[i] != null){
                if(me.getX() <= deadBots[i].getX() + 10 && me.getX() > deadBots[i].getX() - 10){
                    if(me.getX() > deadBots[i].getX()){
                        return "left";
                    }else{
                        return "right";
                    }
                }else if(me.getY() <= deadBots[i].getY() + 10 && me.getY() <= deadBots[i].getY() - 10){
                    if(me.getY() > deadBots[i].getY()){
                        return "up";
                    }else{
                        return "down";
                    }
                }
            }
        }
        return "";
    }

    /**
     * This bot does nothing to prepare for the next round.
     */
    public void newRound()
    {
        setAlly(liveBots, me);
        setEnemy(liveBots);
        round++;
        current = right;
        
    }

    /**
     * This bot does not send messages.
     */
    public String outgoingMessage()
    {
        String msg = nextMessage;
		nextMessage = null;
		return msg;
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
    public void incomingMessage(int botNum, String msg){
        if(botNum == BattleBotArena.SYSTEM_MSG && msg.matches("Round " + round + " starting. Good luck!")){
            
        }
        if (botNum == BattleBotArena.SYSTEM_MSG && msg.matches(".*destroyed by "+getName()+".*")){
			nextMessage = "Skill issue (≧◡≦)";
            msgCounter = 10;
		}
        
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

