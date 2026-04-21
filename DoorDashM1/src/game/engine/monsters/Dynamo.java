package game.engine.monsters;

import game.engine.Role;

public class Dynamo extends Monster {
	
	public Dynamo(String name, String description, Role role, int energy) {
		super(name, description, role, energy);
	}
	
	public void executePowerupEffect(Monster opponentMonster)
	{
		opponentMonster.setFrozen(true);
	}
	
	public void setEnergy(int energy)
	{
		this.alterEnergy((energy-this.getEnergy())*2 + this.getEnergy());
	}
}
