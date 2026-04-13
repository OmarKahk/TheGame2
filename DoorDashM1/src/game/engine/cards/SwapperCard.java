package game.engine.cards;

import game.engine.monsters.Monster;

public class SwapperCard extends Card {

	public SwapperCard(String name, String description, int rarity) {
		super(name, description, rarity, true);
	}
	
	public void performAction(Monster player, Monster opponent)
	{
		if(player.getPosition()<opponent.getPosition())
		{
			int tmp = player.getPosition();
			player.setPosition(opponent.getPosition());
			opponent.setPosition(tmp);
		}
	}
	
}
