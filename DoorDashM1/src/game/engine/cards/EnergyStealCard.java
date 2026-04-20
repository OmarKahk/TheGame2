package game.engine.cards;

import game.engine.interfaces.CanisterModifier;
import game.engine.monsters.Monster;

public class EnergyStealCard extends Card implements CanisterModifier {
	private int energy;

	public EnergyStealCard(String name, String description, int rarity, int energy) {
		super(name, description, rarity, true);
		this.energy = energy;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void performAction(Monster player, Monster opponent)
	{
		player.alterEnergy(opponent.getEnergy());
		opponent.setEnergy(opponent.getEnergy()-player.getEnergy());
	}
	
	public void modifyCanisterEnergy(Monster monster, int canisterValue) 
	{
		monster.alterEnergy(canisterValue);
	}
}