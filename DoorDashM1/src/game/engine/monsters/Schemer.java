package game.engine.monsters;

import game.engine.Board;
import game.engine.Constants;
import game.engine.Role;

public class Schemer extends Monster {
	
	public Schemer(String name, String description, Role role, int energy) {
		super(name, description, role, energy);
	}
	
	 private int stealEnergyFrom(Monster target)
	 {
		 int tmp = target.getEnergy();
		 if(target.getEnergy()<Constants.SCHEMER_STEAL)
		 {
			 target.setEnergy(0);
			 return tmp;
		 }
		 else
		 {
			 target.setEnergy(getEnergy()-Constants.SCHEMER_STEAL);
			 return Constants.SCHEMER_STEAL;
		 }
	 }
	 
	 public void setEnergy(int energy)
	 {
		 super.setEnergy(energy+10);
	 }
	 
	 public void executePowerupEffect(Monster opponentMonster)
	 {
		 int total = 0;
		 for(int i = 0; i<Board.getStationedMonsters().size();i++)
		 {
			 total += stealEnergyFrom(Board.getStationedMonsters().get(i));
		 }
		 this.setEnergy(total+this.getEnergy());
	 }
}
