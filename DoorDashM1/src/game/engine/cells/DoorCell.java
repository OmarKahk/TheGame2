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
	
	public void onLand(Monster landingMonster, Monster OpponentMonster) {
		if(isActivated())
			return;
		if(!isActivated()) {
			if(this.role == landingMonster.getRole()) {
				modifyCanisterEnergy(landingMonster, this.getEnergy());
				ArrayList<Monster> a = Board.getStationedMonsters();
				for(int i = 0; i<a.size(); i++) {
					if(a.get(i).getRole() == landingMonster.getRole())
						modifyCanisterEnergy(a.get(i), this.getEnergy());
				}
				setActivated(true);
			}
			
			else {
				if(landingMonster.isShielded()) {
					landingMonster.setShielded(false);
					return;
				}
				else {
					modifyCanisterEnergy(landingMonster, -this.getEnergy());
					ArrayList<Monster> a = Board.getStationedMonsters();
					for(int i = 0; i<Board.getStationedMonsters().size(); i++) {
						if(a.get(i).getRole() == landingMonster.getRole())
							modifyCanisterEnergy(a.get(i), -this.getEnergy());
					}
					setActivated(true);
				}
			}
		}
	}

	
	public void modifyCanisterEnergy(Monster monster, int canisterValue) {
		monster.alterEnergy(canisterValue);
	}
	
	

	

}
