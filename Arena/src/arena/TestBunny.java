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
    // public static void main(String[] args) {
    //     int enemyDirection = 0;
    //     TestBunny test = new TestBunny();
    //     /**
    //      * Setup arguments for call to getMove()
    //      */
    //     //making bunny bots at certain locations
    //     BotInfo me1 = new BotInfo(300, 200, 0, "5");
    //     //BotInfo me2 = new BotInfo(300, 300, 0, "2");
    //     //BotInfo me3 = new BotInfo(200, 200, 0, "14");
    //     //BotInfo me4 = new BotInfo(200, 300, 0, "7");
       
    //     BotInfo[] liveBots = new BotInfo[2];
    //     Bullet[] bullets = new Bullet[0];
    //     BotInfo[] deadBots = new BotInfo[1];
    //     BotInfo enemy = new BotInfo(380, 170, 0, "enemy");
    //     Bunny bunny1 = new Bunny();
    //     Bunny bunny2 = new Bunny();
    //     Bunny bunny3 = new Bunny();
    //     Bunny bunny4 = new Bunny();
    //     CloseBot closebot1 = new CloseBot();
    //     System.out.println();
    //     liveBots[0] = me1;
    //     //liveBots[2] = me3;
    //     //liveBots[4] = me2;
    //     //liveBots[3] = me4;
    //     liveBots[1] = enemy;


    //     if(closebot1.getMove(enemy, true, liveBots, deadBots, bullets) == BattleBotArena.DOWN)
    //         System.out.println("closebot is moving down");
       
    //     //setting bunny bot teams to use for testing
    //     me1.setTeamName(bunny1.getTeamName());
    //     //me2.setTeamName(bunny2.getTeamName());
    //     //me3.setTeamName(bunny3.getTeamName());
    //     //me4.setTeamName(bunny4.getTeamName());
    //     //bunny2.getMove(me2, true, liveBots, deadBots, bullets);
    //     //bunny3.getMove(me3, true, liveBots, deadBots, bullets);
    //     //bunny4.getMove(me4, true, liveBots, deadBots, bullets);
    //     //System.out.println(bunny1.getTeamName());
    //     //finds out which bot is an ally
    //     //bunny1.setAlly();
    //     //prints out all of the ally bots
    //     //bunny1.getAlly();


    //     //making a tombstone to the right of bunny 1
    //     BotInfo dead1 = new BotInfo(20, 220, 0, "16");
    //     //BotInfo dead2 = new BotInfo(270, 200, 0, "15");
    //     //BotInfo dead3 = new BotInfo(300, 230, 0, "14");
    //     //BotInfo dead4 = new BotInfo(400, 170, 0, "13");
    //     deadBots[0] = dead1;
    //     //deadBots[1] = dead2;
    //     //deadBots[2] = dead3;
    //     //deadBots[3] = dead4;
    //     double distanceX = enemy.getX() - me1.getX();
    //     double distanceY = enemy.getY() - me1.getY();
    //     if(closebot1.getMove(enemy, true, liveBots, deadBots, bullets) == BattleBotArena.RIGHT)
    //         enemyDirection = 1;
    //     else if(closebot1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.LEFT)
    //         enemyDirection = 2;
    //     else if(closebot1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.UP)
    //         enemyDirection = 3;
    //     else if(closebot1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.DOWN)
    //         enemyDirection = 4;
    //     double botSpeed = 1.5;
    //     double bulletSpeed = 4;
    //     switch(test.linedShotTest(enemy, distanceX, distanceY, enemyDirection)){
    //         case "up":
    //             if(Math.abs(distanceY) / bulletSpeed <= (Math.abs(distanceX) / botSpeed) + 0.5 && Math.abs(distanceY) / bulletSpeed > (Math.abs(distanceX) / botSpeed) - 0.5){
    //                 //if(!bunny1.obstacle().equals("up"))
    //                     System.out.println("shoot up");
    //             }
    //             break;
    //         case "down":
    //             if(Math.abs(distanceY) / bulletSpeed <= (Math.abs(distanceX) / botSpeed) + 0.5 && Math.abs(distanceY) / bulletSpeed > (Math.abs(distanceX) / botSpeed) - 0.5){
    //                 //if(!bunny1.obstacle().equals("down"))
    //                     System.out.println("shoot down");
    //             }
    //             break;
    //         case "left":
    //             if(Math.abs(distanceX) / bulletSpeed <= (Math.abs(distanceY) / botSpeed) + 0.5 && Math.abs(distanceX) / bulletSpeed >= (Math.abs(distanceY) / botSpeed) - 0.5){
    //                 //if(!bunny1.obstacle().equals("left"))
    //                     System.out.println("shoot left");
    //             }
    //             break;
    //         case "right":
    //             if(Math.abs(distanceX) / bulletSpeed <= (Math.abs(distanceY) / botSpeed) + 0.5 && Math.abs(distanceX) / bulletSpeed >= (Math.abs(distanceY) / botSpeed) - 0.5){
    //                 //if(!bunny1.obstacle().equals("right"))
    //                     System.out.println("shoot right");
    //             }
    //             break;
    //         case "instaright":
    //             System.out.println("shoot right");
    //         case "instaleft":
    //             System.out.println("shoot left");
    //         case "instadown":
    //             System.out.println("shoot down");
    //         case "instaup":
    //             System.out.println("shoot up");
    //     }
    //     if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.UP)
    //         System.out.println("moving up");
    //     else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.RIGHT)
    //         System.out.println("moving right");
    //     else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.DOWN)
    //         System.out.println("moving down");
    //     else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.LEFT)
    //         System.out.println("moving left");
    //     else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.FIREUP)
    //         System.out.println("firing up");
    //     else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.FIRERIGHT)
    //         System.out.println("firing right");
    //     else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.FIREDOWN)
    //         System.out.println("firing down");
    //     else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.FIRELEFT)
    //         System.out.println("firing left");
    //     else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.STAY)
    //         System.out.println("Staying still");
    //     else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.SEND_MESSAGE)
    //         System.out.println("Sending messages");
    // }
    // public String linedShotTest(BotInfo target, double x, double y, int z){
    //     if(Math.abs(y) < 10){
    //         if(x > 0 && Math.abs(x) < 10){
    //             return "instaright";
    //         }else if(x < 0 && Math.abs(x) < 10){
    //             return "instaleft";
    //         }
    //     }else if(Math.abs(x) < 10){
    //         if(y > 0 && y < 10){
    //             return "instadown";
    //         }else if(y < 0 && Math.abs(y) < 10){
    //             return "instaup";
    //         }
    //     }
    //     if(z == 1)
    //     {
    //         if(y > 0 && y <400){
    //             return "down";
    //         }else if(y < 0 && Math.abs(y) < 400){
    //             return "up";
    //         }
    //     }
    //     else if(z == 2)
    //     {
    //         if(y > 0 && y <400){
    //             return "down";
    //         }else if(y < 0 && Math.abs(y) < 400){
    //             return "up";
    //         }
    //     }
    //     else if(z == 3)
    //     {
    //         if(x > 0 && x < 400){
    //             return "right";
    //         }else if(x < 0 && Math.abs(x) < 400){
    //             return "left";
    //         }
    //     }
    //     else if(z == 4)
    //     {
    //         if(x > 0 && x < 400){
    //             return "right";
    //         }else if(x < 0 && Math.abs(x) < 400){
    //             return "left";
    //         }
    //     }
    //     return "";
    // }
// By Faraz
	public static void main(String[] args){
		TestBunny test = new TestBunny();

		/*
		 * Faraz
		 */
		//test.testGetAlly();
		//test.testGetEnemies();
        //test.testTombstone();
        test.testPredictiveShooting();

		/*
		 * Yazan
		 */
		//test.testSendingMessages();
		//test.testStayingStill();
		//test.testMoving();
	}

	public void testGetAlly()
    {
		//@author Faraz
		BotInfo[] liveBots = new BotInfo[16];
		Bunny bunny1 = new Bunny();
        Bunny bunny2 = new Bunny();
        Bunny bunny3 = new Bunny();
        Bunny bunny4 = new Bunny();

		BotInfo enemy = new BotInfo(380, 170, 0, "enemy");

		BotInfo me1 = new BotInfo(300, 200, 0, "5");
        BotInfo me2 = new BotInfo(300, 300, 0, "2");
        BotInfo me3 = new BotInfo(200, 200, 0, "14");
        BotInfo me4 = new BotInfo(200, 300, 0, "7");

		liveBots[0] = me1;
		liveBots[1] = enemy;
        liveBots[2] = me3;
        liveBots[4] = me2;
        liveBots[3] = me4;

		me1.setTeamName(bunny1.getTeamName());
        me2.setTeamName(bunny2.getTeamName());
        me3.setTeamName(bunny3.getTeamName());
        me4.setTeamName(bunny4.getTeamName());

		bunny1.setAlly(liveBots, me1);
		bunny1.getAlly();
	}

	public void testGetEnemies()
    {
		//@author Faraz
		BotInfo[] liveBots = new BotInfo[16];
		Bunny bunny1 = new Bunny();
		Bunny bunny2 = new Bunny();
		CloseBot enemy = new CloseBot();

		BotInfo me1 = new BotInfo(300, 200, 0, "5");
		BotInfo me2 = new BotInfo(100, 400, 0, "5");
        BotInfo enemy1 = new BotInfo(300, 300, 0, "2");
        BotInfo enemy2 = new BotInfo(200, 200, 0, "14");
        BotInfo enemy3 = new BotInfo(200, 300, 0, "7");

		liveBots[0] = me1;
        liveBots[1] = me2;
        liveBots[2] = enemy1;
        liveBots[3] = enemy2;
		liveBots[4] = enemy3;

		me1.setTeamName(bunny1.getTeamName());
        me2.setTeamName(bunny2.getTeamName());
        enemy1.setTeamName(enemy.getTeamName());
        enemy2.setTeamName(enemy.getTeamName());
		enemy3.setTeamName(enemy.getTeamName());

        bunny1.setEnemy(liveBots);
		bunny1.getEnemy();
	}

	public void testTombstone()
    {
		//@author Faraz
		BotInfo[] deadBots = new BotInfo[16];
		BotInfo[] liveBots = new BotInfo[16];
		Bullet[] bullets = new Bullet[0];

		Bunny bunny1 = new Bunny();
		BotInfo me1 = new BotInfo(300, 200, 0, "5");

        //move
		BotInfo dead1 = new BotInfo(20, 220, 0, "16");
        BotInfo dead2 = new BotInfo(290, 200, 0, "15");
        BotInfo dead3 = new BotInfo(300, 210, 0, "14");
        BotInfo dead4 = new BotInfo(400, 200, 0, "13");
		
        deadBots[0] = dead1;
        deadBots[1] = dead2;
        deadBots[2] = dead3;
        deadBots[3] = dead4;
        if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.UP)
            System.out.println("moving up");
        else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.RIGHT)
            System.out.println("moving right");
        else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.DOWN)
            System.out.println("moving down");
        else if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.LEFT)
            System.out.println("moving left");
	}

    public void testPredictiveShooting()
    {	//@author Faraz
        int enemyDirection = 0;
        TestBunny test = new TestBunny();
        CloseBot closebot1 = new CloseBot();
        BotInfo[] liveBots = new BotInfo[16];
        BotInfo[] deadBots = new BotInfo[16];
		Bullet[] bullets = new Bullet[0];
		Bunny bunny1 = new Bunny();
        BotInfo me1 = new BotInfo(300, 200, 0, "5");
        BotInfo enemy = new BotInfo(380, 170, 0, "enemy");
        liveBots[0] = me1;
        liveBots[1] = enemy;
        if(closebot1.getMove(enemy, true, liveBots, deadBots, bullets) == BattleBotArena.RIGHT)
            enemyDirection = 1;
        else if(closebot1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.LEFT)
            enemyDirection = 2;
        else if(closebot1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.UP)
            enemyDirection = 3;
        else if(closebot1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.DOWN)
            enemyDirection = 4;
        double distanceX = enemy.getX() - me1.getX();
        double distanceY = enemy.getY() - me1.getY();
        double botSpeed = 1.5;
        double bulletSpeed = 4;
        switch(test.linedShotTest(enemy, distanceX, distanceY, enemyDirection)){
            case "up":
                if(Math.abs(distanceY) / bulletSpeed <= (Math.abs(distanceX) / botSpeed) + 0.5 && Math.abs(distanceY) / bulletSpeed > (Math.abs(distanceX) / botSpeed) - 0.5){
                    //if(!bunny1.obstacle().equals("up"))
                        System.out.println("shoot up");
                }
                break;
            case "down":
                if(Math.abs(distanceY) / bulletSpeed <= (Math.abs(distanceX) / botSpeed) + 0.5 && Math.abs(distanceY) / bulletSpeed > (Math.abs(distanceX) / botSpeed) - 0.5){
                    //if(!bunny1.obstacle().equals("down"))
                        System.out.println("shoot down");
                }
                break;
            case "left":
                if(Math.abs(distanceX) / bulletSpeed <= (Math.abs(distanceY) / botSpeed) + 0.5 && Math.abs(distanceX) / bulletSpeed >= (Math.abs(distanceY) / botSpeed) - 0.5){
                    //if(!bunny1.obstacle().equals("left"))
                        System.out.println("shoot left");
                }
                break;
            case "right":
                if(Math.abs(distanceX) / bulletSpeed <= (Math.abs(distanceY) / botSpeed) + 0.5 && Math.abs(distanceX) / bulletSpeed >= (Math.abs(distanceY) / botSpeed) - 0.5){
                    //if(!bunny1.obstacle().equals("right"))
                        System.out.println("shoot right");
                }
                break;
            case "instaright":
                System.out.println("shoot right");
            case "instaleft":
                System.out.println("shoot left");
            case "instadown":
                System.out.println("shoot down");
            case "instaup":
                System.out.println("shoot up");
        }
        
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


    public static void testMoving() {
        // @author Yazan
        BotInfo[] liveBots = new BotInfo[5];
        Bullet[] bullets = new Bullet[0];
        BotInfo[] deadBots = new BotInfo[1];
        BotInfo me1 = new BotInfo(300, 200, 0, "bunny");
        Bunny bunny1 = new Bunny();
        me1.setTeamName(bunny1.getTeamName());
        // making an enemy bot above our bot to test if our bot moves right or left
        BotInfo enemy1 = new BotInfo(300, 220, 0, "enemy1");
        // making an enemy bot under our bot to test if our bot moves right or left
        BotInfo enemy2 = new BotInfo(300, 180, 0, "enemy2");
        // making an enemy bot to the right of our bot to test if our bot moves up or down
        BotInfo enemy3 = new BotInfo(270, 200, 0, "enemy3");
        // making an enemy bot to the left of our bot to test if our bot moves up or down
        BotInfo enemy4 = new BotInfo(330, 200, 0, "enemy4");
        liveBots[0] = me1;
        liveBots[1] = enemy1;
        liveBots[2] = enemy2;
        liveBots[3] = enemy3;
        liveBots[4] = enemy4;


        if (bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.UP)
        {
            System.out.println("moving up passed");
        }
        else {
            System.out.println("moving up failed");
        }
        if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.RIGHT)
        {
            System.out.println("moving right passed");
        }
        else {
            System.out.println("moving right failed");
        }
        if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.DOWN)
        {
            System.out.println("moving down passed");
        }
        else {
            System.out.println("moving down failed");
        }
        if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.LEFT)
        {
            System.out.println("moving left passed");
        }
        else {
            System.out.println("moving left failed");
        }
    }

//---------------------------------------------------------------------------------------------------------------------------------

    public void testStayingStill() {
        // @author Yazan
        BotInfo[] liveBots = new BotInfo[1];
        Bullet[] bullets = new Bullet[0];
        BotInfo[] deadBots = new BotInfo[1];
        BotInfo me1 = new BotInfo(300, 200, 0, "bunny");
        Bunny bunny1 = new Bunny();
        me1.setTeamName(bunny1.getTeamName());
        liveBots[0] = me1;
        if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.STAY)
        {
            System.out.println("staying still passed");
        }
        else {
            System.out.println("staying still failed");
        }
    }

	public void testDodging() {
        // @author Yazan
        BotInfo[] liveBots = new BotInfo[1];
        Bullet[] bullets = new Bullet[4];
        BotInfo[] deadBots = new BotInfo[1];
        BotInfo me1 = new BotInfo(300, 200, 0, "bunny");
        Bunny bunny1 = new Bunny();
        me1.setTeamName(bunny1.getTeamName());
        // making a bullet above our bot to check if bot moves right or left
        Bullet bullet1 = new Bullet(300, 220, 4, 4);
        // making a bullet under our bot to test if our bot moves right or left
        Bullet bullet2 = new Bullet(300, 180, 4, 4);
        // making a bullet to the right of our bot to test if our bot moves up or down
        Bullet bullet3 = new Bullet(270, 200, 4, 4);
        // making a bullet to the left of our bot to test if our bot moves up or down
        Bullet bullet4 = new Bullet(330, 200, 4, 4);
        liveBots[0] = me1;
        bullets[0] = bullet1;
        bullets[1] = bullet2;
        bullets[2] = bullet3;
        bullets[3] = bullet4;
        if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.RIGHT || bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.LEFT)
        {
            System.out.println("moving right or left passed");
        }
        else {
            System.out.println("moving right or left failed");
        }
        if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.UP || bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.DOWN)
        {
            System.out.println("moving up or down passed");
        }
        else {
            System.out.println("moving up or down failed");
        }
    }

}
