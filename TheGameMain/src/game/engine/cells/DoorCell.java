package game.engine.cells;
import game.engine.Role;

public class DoorCell extends Cell{
	
	private Role role;
	private int energy;
	public boolean activated;
	
	public DoorCell(String name, Role role, int energy) {
		super(name);
		this.role = role;
		this.energy = energy;
		this.activated = false;
		
	}
}
