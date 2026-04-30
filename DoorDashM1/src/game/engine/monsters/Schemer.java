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
	    target.alterEnergy(-stolen);
	    return stolen;
	}

	public void executePowerupEffect(Monster opponentMonster) {
	    int totalStolen = 0;
	    totalStolen += stealEnergyFrom(opponentMonster);
	    ArrayList<Monster> s = Board.getStationedMonsters();
	    if (s != null) {
	        for (int i =0;i<s.size();i++) {
	        		Monster m = s.get(i);
	            if (m != null) {
	                totalStolen += stealEnergyFrom(m);
	            }
	        }
	    }
	    this.alterEnergy(totalStolen);
	    
	}
}

