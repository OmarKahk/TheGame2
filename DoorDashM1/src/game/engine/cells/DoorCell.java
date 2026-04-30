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
	
	public void onLand(Monster landingMonster, Monster opponentMonster) {
	    this.setMonster(landingMonster);
	    if (isActivated()) {
	        return;
	    }
	    boolean tmp = false;
	    if (landingMonster.getRole() != this.getRole())
	        if (landingMonster.isShielded()) {
	            landingMonster.setShielded(false);
	            return;
	        }

	    ArrayList<Monster> a = Board.getStationedMonsters();
	    int e1 = landingMonster.getEnergy();

	    modifyCanisterEnergy(landingMonster, this.getEnergy());

	    if (e1 != landingMonster.getEnergy())
	        tmp = true;

	    for (int i = 0; i < a.size(); i++) {
	        int e = a.get(i).getEnergy();

	        if (a.get(i).getRole() == landingMonster.getRole()) {
	            modifyCanisterEnergy(a.get(i), this.getEnergy());

	            if (e != a.get(i).getEnergy())
	                tmp = true;
	        }
	    }

	    if (tmp) {
	        setActivated(true);
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
