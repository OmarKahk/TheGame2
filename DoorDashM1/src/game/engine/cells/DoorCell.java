package game.engine.cells;

import java.util.ArrayList;

import game.engine.Board;
import game.engine.Role;
import game.engine.interfaces.CanisterModifier;
import game.engine.monsters.Monster;

public class DoorCell extends Cell implements CanisterModifier {
	private Role role;
	private int energy;
	private boolean activated;
	
	public DoorCell(String name, Role role, int energy) {
		super(name);
		this.role = role;
		this.energy = energy;
		this.activated = false;
	}
	
	public Role getRole() {
		return role;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean isActivated) {
		this.activated = isActivated;
	}
	
	// DoorCell.java

	@Override
	public void onLand(Monster landingMonster, Monster opponentMonster) {

	    // ALWAYS set the landing monster first
	    super.onLand(landingMonster, opponentMonster);

	    // if already activated → no more effects
	    if (activated) {
	        return;
	    }

	    if (landingMonster == null) {
	        return;
	    }

	    int value;

	    /*
	     If landing monster role matches door role:
	     gain energy

	     Otherwise:
	     lose energy
	    */

	    if (landingMonster.getRole() == this.role) {
	        value = energy;
	    } else {
	        value = -energy;
	    }

	    // landing monster (shield respected)
	    landingMonster.alterEnergy(value);

	    // same-role stationed monsters only
	    ArrayList<Monster> stationed = Board.getStationedMonsters();

	    if (stationed != null) {
	        for (Monster m : stationed) {
	            if (m != null && m.getRole() == landingMonster.getRole()) {
	                m.alterEnergy(value);
	            }
	        }
	    }

	    // activate only if actual gain/loss happened
	    if (value != 0) {
	        activated = true;
	    }
	}
	public void modifyCanisterEnergy(Monster monster, int canisterValue) {
		if(this.getRole() == monster.getRole()) {
			monster.alterEnergy(canisterValue);
		}
		else {
			if(monster.isShielded())
			{
				monster.setShielded(false);
				return;
			}
			else 
				monster.alterEnergy(-canisterValue);
		}
		
	}
}
