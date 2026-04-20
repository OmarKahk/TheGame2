package game.engine.cells;

import game.engine.monsters.*;

public class MonsterCell extends Cell {
	private Monster cellMonster;

	public MonsterCell(String name, Monster cellMonster) {
		super(name);
		this.cellMonster = cellMonster;
	}

	public Monster getCellMonster() {
		return cellMonster;
	}
	
	public void onLand(Monster landingMonster, Monster opponentMonster) {
		if(landingMonster.getRole() == this.getCellMonster().getRole())
			landingMonster.executePowerupEffect(opponentMonster);
		else if (landingMonster.getEnergy() > this.getCellMonster().getEnergy()) {
			int temp = landingMonster.getEnergy();
			if(landingMonster.isShielded() == false)
				landingMonster.setEnergy(this.getCellMonster().getEnergy());
			this.getCellMonster().setEnergy(temp);
		}
	}
}
