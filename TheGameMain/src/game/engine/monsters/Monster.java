package game.engine.monsters;

public class Monster {
	private String name;
	private String description;
	private Role role;
	private Role originalRole;
	private int energy;
	private int position;
	private boolean frozen;
	private boolean shielded;
	private int confusionTurns;
	public Monster(String name, String description, Role originalRole, int energy)
	{
		this.name=name;
		this.description=description;
		this.originalRole = originalRole;
		this.energy=energy;
		position = 0;
		confusionTurns=0;
	}
	int compareTo(Monster o)
	{
		if(this.position>o.position)
			return 1;
		else if(this.position<o.position)
			return -1;
		else
			return 0;
	}
	
}
