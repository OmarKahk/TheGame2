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
		if(!isActivated()) {
			if(this.role == landingMonster.getRole()) {
				modifyCanisterEnergy(landingMonster, landingMonster.getEnergy() + this.getEnergy());
				setActivated(true);
				ArrayList<Monster> a = Board.getStationedMonsters();
				for(int i = 0; i<Board.getStationedMonsters().size(); i++) {
					if(a.get(i).getRole() == landingMonster.getRole())
						modifyCanisterEnergy(a.get(i), a.get(i).getEnergy() + this.getEnergy());
				}
			}
			
			else {
				if(landingMonster.isShielded() == true) {
					landingMonster.setShielded(false);
					ArrayList<Monster> a = Board.getStationedMonsters();
					for(int i = 0; i<Board.getStationedMonsters().size(); i++) {
						if(a.get(i).getRole() == landingMonster.getRole())
							a.get(i).setShielded(false);
					}
				}
				else {
					modifyCanisterEnergy(landingMonster, landingMonster.getEnergy() + -this.getEnergy());
					setActivated(true);
					ArrayList<Monster> a = Board.getStationedMonsters();
					for(int i = 0; i<Board.getStationedMonsters().size(); i++) {
						if(a.get(i).getRole() == landingMonster.getRole())
							modifyCanisterEnergy(a.get(i), a.get(i).getEnergy() + -this.getEnergy());
					}
				}
			}
		}
	}

	
	public void modifyCanisterEnergy(Monster monster, int canisterValue) {
		monster.setEnergy(canisterValue);
	}
	
	

	

}
