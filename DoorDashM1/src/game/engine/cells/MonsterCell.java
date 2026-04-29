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
	
	
	@Override
	public void onLand(Monster landingMonster, Monster opponentMonster) {

	    this.setMonster(landingMonster);

	    // Same role → free powerup
	    if (landingMonster.getRole() == cellMonster.getRole()) {
	        landingMonster.executePowerupEffect(opponentMonster);
	        return;
	    }

	    // Different role → landing has more energy
	    if (landingMonster.getEnergy() > cellMonster.getEnergy()) {

	        int landingEnergy = landingMonster.getEnergy();
	        int cellEnergy = cellMonster.getEnergy();

	        // landing monster loses energy down to cell monster's old value
	        // shield respected
	        landingMonster.alterEnergy(-(landingEnergy - cellEnergy));

	        // cell monster gains same amount
	        // passive effects MUST apply
	        cellMonster.alterEnergy(landingEnergy - cellEnergy);
	    }
	}
}
