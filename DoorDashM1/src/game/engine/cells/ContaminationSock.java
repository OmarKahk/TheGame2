package game.engine.cells;

import java.util.Random;

import game.engine.Constants;
import game.engine.interfaces.CanisterModifier;
import game.engine.monsters.Monster;

public class ContaminationSock extends TransportCell implements CanisterModifier {

	public ContaminationSock(String name, int effect) {
		super(name, effect);
	}
	
	
	public void onLand(Monster landingMonster, Monster opponentMonster) {

		transport(landingMonster);
	
		
	}
	
	public void transport(Monster monster) {
			monster.setPosition(getEffect() + monster.getPosition());	
			if(monster.isShielded() == true) 
				monster.setShielded(false);
			else
				modifyCanisterEnergy(monster, monster.getEnergy() - Constants.SLIP_PENALTY);
				
			
	}


	
	public void modifyCanisterEnergy(Monster monster, int canisterValue) {
		monster.setEnergy(canisterValue);
		
	}
	
	
	

}

