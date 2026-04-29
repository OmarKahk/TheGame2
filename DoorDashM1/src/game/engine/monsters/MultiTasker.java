package game.engine.monsters;

import game.engine.Role;

public class MultiTasker extends Monster {
	private int normalSpeedTurns;
	
	public MultiTasker(String name, String description, Role role, int energy) {
		super(name, description, role, energy);
		this.normalSpeedTurns = 0;
	}

	public int getNormalSpeedTurns() {
		return normalSpeedTurns;
	}

	public void setNormalSpeedTurns(int normalSpeedTurns) {
		this.normalSpeedTurns = normalSpeedTurns;
	}
	
	public void move(int distance)
	{
		if(this.normalSpeedTurns>0)
		{
			super.move(distance);
			this.normalSpeedTurns--;
		}
		else
			super.move(distance/2);
	}
	
	public void setEnergy(int energy)
	{
		super.setEnergy(energy+200);
	}
	
	public void executePowerupEffect(Monster opponentMonster)
	{
		this.setNormalSpeedTurns(2);;
	}

}