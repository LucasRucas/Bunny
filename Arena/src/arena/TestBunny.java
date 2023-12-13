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
		/**
		 * Setup arguments for call to getMove()
		 */
		//making bunny bots at certain locations
		BotInfo me1 = new BotInfo(300, 200, 0, "5");
		BotInfo me2 = new BotInfo(300, 300, 0, "2");
		BotInfo me3 = new BotInfo(200, 200, 0, "14");
		BotInfo me4 = new BotInfo(200, 300, 0, "7");
		
		BotInfo[] liveBots = new BotInfo[5];
		Bullet[] bullets = new Bullet[0];
		BotInfo[] deadBots = new BotInfo[1];
		BotInfo info = new BotInfo(100, 200, 0, "enemy");
		Bunny bunny1 = new Bunny();
		Bunny bunny2 = new Bunny();
		Bunny bunny3 = new Bunny();
		Bunny bunny4 = new Bunny();
		System.out.println();
		deadBots[0] = info;
		liveBots[1] = me1;
		liveBots[2] = me2;
		liveBots[3] = me3;
		liveBots[4] = me4;

		
		
		
		//setting bunny bot teams to use for testing
		me1.setTeamName(bunny1.getTeamName());
		me2.setTeamName(bunny2.getTeamName());
		me3.setTeamName(bunny3.getTeamName());
		me4.setTeamName(bunny4.getTeamName());
		//bunny1.getMove(me1, true, liveBots, deadBots, bullets);
		//bunny2.getMove(me2, true, liveBots, deadBots, bullets);
		//bunny3.getMove(me3, true, liveBots, deadBots, bullets);
		//bunny4.getMove(me4, true, liveBots, deadBots, bullets);
		//System.out.println(bunny1.getTeamName());
		//finds out which bot is an ally
		//bunny1.setAlly();
		//prints out all of the ally bots
		//bunny1.getAlly();

		if(bunny1.getMove(me1, true, liveBots, deadBots, bullets) == BattleBotArena.UP){
			System.out.println("OK");
		}else{
			System.out.println("problem");
		}
	}
}