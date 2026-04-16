package game.engine.cards;

import game.engine.Role;
import game.engine.monsters.Monster;

public class ConfusionCard extends Card {
	private int duration;
	
	public ConfusionCard(String name, String description, int rarity, int duration) {
		super(name, description, rarity, false);
		this.duration = duration;
	}
	
	public int getDuration() {
		return duration;
	}
	public void performAction(Monster player, Monster opponent)
	{
		if(duration>0) 
		{
		Role t = player.getRole();
		player.setRole(opponent.getRole());
		opponent.setRole(t);
		}
		else
		{
			Role t = player.getRole();
			player.setRole(opponent.getRole());
			opponent.setRole(t);
		}
		
	}
	
}
