package arena;

import bots.CloseBot;
import bots.Bunny;
import arena.BotInfo;

public class TestBunny {

	/**
	 * This method runs all of the tests for CloseBot. When many tests are developed, it may be
	 * helpful to group them into methods that are invoked from main().
	 * @param args
	 */
	public static void main(String[] args) {
		int enemyDirection = 0;
		TestBunny test = new TestBunny();
		/**
		 * Setup arguments for call to getMove()
		 */
		//making bunny bots at certain locations
		BotInfo me1 = new BotInfo(300, 200, 0, "5");
		//BotInfo me2 = new BotInfo(300, 300, 0, "2");
		//BotInfo me3 = new BotInfo(200, 200, 0, "14");
		//BotInfo me4 = new BotInfo(200, 300, 0, "7");
		
		BotInfo[] liveBots = new BotInfo[2];
		Bullet[] bullets = new Bullet[0];
		BotInfo[] deadBots = new BotInfo[1];
		BotInfo enemy = new BotInfo(330, 120, 0, "enemy");
		Bunny bunny1 = new Bunny();
		Bunny bunny2 = new Bunny();
		Bunny bunny3 = new Bunny();
		Bunny bunny4 = new Bunny();
		CloseBot closebot1 = new CloseBot();
		System.out.println();
		liveBots[0] = me1;
		//liveBots[2] = me3;
		//liveBots[4] = me2;
		//liveBots[3] = me4;
		liveBots[1] = enemy;

		if(closebot1.getMove(enemy, true, liveBots, deadBots, bullets) == BattleBotArena.DOWN)
			System.out.println("closebot is moving down");
		
		//setting bunny bot teams to use for testing
		me1.setTeamName(bunny1.getTeamName());
		//me2.setTeamName(bunny2.getTeamName());
		//me3.setTeamName(bunny3.getTeamName());
		//me4.setTeamName(bunny4.getTeamName());
		//bunny2.getMove(me2, true, liveBots, deadBots, bullets);
		//bunny3.getMove(me3, true, liveBots, deadBots, bullets);
		//bunny4.getMove(me4, true, liveBots, deadBots, bullets);
		//System.out.println(bunny1.getTeamName());
		//finds out which bot is an ally
		//bunny1.setAlly();
		//prints out all of the ally bots
		//bunny1.getAlly();

		//making a tombstone to the right of bunny 1
		BotInfo dead1 = new BotInfo(20, 220, 0, "16");
		//BotInfo dead2 = new BotInfo(270, 200, 0, "15");
		//BotInfo dead3 = new BotInfo(300, 230, 0, "14");
		//BotInfo dead4 = new BotInfo(400, 170, 0, "13");
		deadBots[0] = dead1;
		//deadBots[1] = dead2;
		//deadBots[2] = dead3;
		//deadBots[3] = dead4;
		double distanceX = enemy.getX() - me1.getX();
        double distanceY = enemy.getY() - me1.getY();
		if(closebot1.getMove(enemy, true, liveBots, deadBots, bullets) == BattleBotArena.RIGHT)
			enemyDirection = 1;
		else if(closebot1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.LEFT)
			enemyDirection = 2;
		else if(closebot1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.UP)
			enemyDirection = 3;
		else if(closebot1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.DOWN)
			enemyDirection = 4;
        double botSpeed = 1.5;
        double bulletSpeed = 4;
        switch(test.linedShotTest(enemy, distanceX, distanceY, enemyDirection)){
            case "up":
                if(Math.abs(distanceY) / bulletSpeed <= Math.abs(distanceX) / botSpeed + 0.5 && Math.abs(distanceY) / bulletSpeed > Math.abs(distanceX) / botSpeed - 0.5){
                    if(!bunny1.obstacle().equals("up"))
                        System.out.println("up");
                }
                break;
            case "down":
                if(Math.abs(distanceY) / bulletSpeed <= Math.abs(distanceX) / botSpeed + 0.5 && Math.abs(distanceY) / bulletSpeed > Math.abs(distanceX) / botSpeed - 0.5){
                    if(!bunny1.obstacle().equals("down"))
                        System.out.println("down");
                }
                break;
            case "left":
                if(Math.abs(distanceX) / bulletSpeed <= Math.abs(distanceY) / botSpeed + 0.5 && Math.abs(distanceX) / bulletSpeed > Math.abs(distanceY) / botSpeed - 0.5){
                    if(!bunny1.obstacle().equals("left"))
                        System.out.println("left");
                }
                break;
            case "right":
                if(Math.abs(distanceX) / bulletSpeed <= Math.abs(distanceY) / botSpeed + 0.5 && Math.abs(distanceX) / bulletSpeed > Math.abs(distanceY) / botSpeed - 0.5){
                    if(!bunny1.obstacle().equals("right"))
                    System.out.println("right");
                }
                break;
            case "instaright":
                System.out.println("right");
            case "instaleft":
                System.out.println("left");
            case "instadown":
                System.out.println("down");
            case "instaup":
                System.out.println("up");
        }
		if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.UP)
			System.out.println("moving up");
		else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.RIGHT)
			System.out.println("moving right");
		else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.DOWN)
			System.out.println("moving down");
		else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.LEFT)
			System.out.println("moving left");
		else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.FIREUP)
			System.out.println("firing up");
		else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.FIRERIGHT)
			System.out.println("firing right");
		else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.FIREDOWN)
			System.out.println("firing down");
		else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.FIRELEFT)
			System.out.println("firing left");
        else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.STAY)
            System.out.println("Staying still");
        else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.SEND_MESSAGE)
            System.out.println("Sending messages");
 	}
	public String linedShotTest(BotInfo target, double x, double y, int z){
        if(Math.abs(y) < 10){
            if(x > 0 && Math.abs(x) < 10){
                return "instaright";
            }else if(x < 0 && Math.abs(x) < 10){
                return "instaleft";
            }
        }else if(Math.abs(x) < 10){
            if(y > 0 && y < 10){
                return "instadown";
            }else if(y < 0 && Math.abs(y) < 10){
                return "instaup";
            }
        }
        if(z == 1)
		{
            if(y > 0 && y <400){
                return "down";
            }else if(y < 0 && Math.abs(y) < 400){
                return "up";
            }
		}
        else if(z == 2)
		{
            if(y > 0 && y <400){
                return "down";
            }else if(y < 0 && Math.abs(y) < 400){
                return "up";
            }
		}
        else if(z == 3)
		{
            if(x > 0 && x < 400){
                return "right";
            }else if(x < 0 && Math.abs(x) < 400){
                return "left";
            }
		}
        else if(z == 4)
		{
            if(x > 0 && x < 400){
                return "right";
            }else if(x < 0 && Math.abs(x) < 400){
                return "left";
            }
		}
        return "";
    }
}