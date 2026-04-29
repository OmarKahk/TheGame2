package game.engine.monsters;

import game.engine.Board;
import game.engine.Constants;
import game.engine.Role;

import java.util.ArrayList;


public class Schemer extends Monster {
	
	public Schemer(String name, String description, Role role, int energy) {
		super(name, description, role, energy);
	}
	 
	private int stealEnergyFrom(Monster target) {
	    int stolen = Math.min(target.getEnergy(), Constants.SCHEMER_STEAL);

	    // target loses energy immediately
	    target.setEnergy(target.getEnergy() - stolen);

	    return stolen;
	}

	@Override
	public void executePowerupEffect(Monster opponentMonster) {
	    int totalStolen = 0;

	    // steal from opponent
	    totalStolen += stealEnergyFrom(opponentMonster);

	    // steal from all stationed monsters
	    ArrayList<Monster> stationed = Board.getStationedMonsters();

	    if (stationed != null) {
	        for (Monster m : stationed) {
	            if (m != null) {
	                totalStolen += stealEnergyFrom(m);
	            }
	        }
	    }

	    // IMPORTANT:
	    // use setEnergy(), NOT alterEnergy()
	    this.setEnergy(this.getEnergy() + totalStolen);
	}
}

