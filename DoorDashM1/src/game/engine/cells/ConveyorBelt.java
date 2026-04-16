package game.engine.cells;

import game.engine.monsters.Monster;

public class ConveyorBelt extends TransportCell {

	public ConveyorBelt(String name, int effect) {
		super(name, effect);
	}
	
	public void onLand(Monster landingMonster, Monster opponentMonster) {
		transport(landingMonster);
	}
	
	public void transport(Monster monster) {
		monster.setPosition(getEffect() + monster.getPosition());
	}

}
