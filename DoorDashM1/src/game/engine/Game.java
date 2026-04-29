package game.engine;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;

import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InvalidMoveException;
import game.engine.exceptions.OutOfEnergyException;
import game.engine.monsters.*;

public class Game {
	private Board board;
	private ArrayList<Monster> allMonsters; 
	private Monster player;
	private Monster opponent;
	private Monster current;
	
	public Game(Role playerRole) throws IOException {
		this.board = new Board(DataLoader.readCards());
		
		this.allMonsters = DataLoader.readMonsters();
		
		this.player = selectRandomMonsterByRole(playerRole);
		this.opponent = selectRandomMonsterByRole(playerRole == Role.SCARER ? Role.LAUGHER : Role.SCARER);
		this.current = player;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public ArrayList<Monster> getAllMonsters() {
		return allMonsters; 
	}
	
	public Monster getPlayer() {
		return player;
	}
	
	public Monster getOpponent() {
		return opponent;
	}
	
	public Monster getCurrent() {
		return current;
	}
	
	public void setCurrent(Monster current) {
		this.current = current;
	}
	
	private Monster selectRandomMonsterByRole(Role role) {
		Collections.shuffle(allMonsters);
	    return allMonsters.stream()
	    		.filter(m -> m.getRole() == role)
	    		.findFirst()
	    		.orElse(null);
	}
	
	private Monster getCurrentOpponent()
	{
		if(current == player)
			return opponent;
		else
			return player;
	}
	
	private int rollDice()
	{
		double x = (Math.random()*6)+1;
		return (int)x;
	}
	
	public void usePowerup() throws OutOfEnergyException
	{	
		if(getCurrent().getEnergy()>=Constants.POWERUP_COST)
		{
			getCurrent().executePowerupEffect(getCurrentOpponent());	
			getCurrent().setEnergy(getCurrent().getEnergy() - 500);
		}
	}
	
	
	private void switchTurn() 
	{
		if(current == player)
			this.current = opponent;
		else
			this.current = player;
	}
	
	public void playTurn() throws InvalidMoveException
	{
		if(this.current.isFrozen()==true)
		{
			current.setFrozen(false);
			switchTurn();
			return;
		}
		else
		{
			current.move(rollDice());
		}
		
	}
	
	private boolean checkWinCondition(Monster monster) {
		if(monster.getPosition() == 99 && monster.getEnergy() >= 1000)
			return true;
		else
			return false;	
	}
	
	public Monster getWinner() {
		if(checkWinCondition(getPlayer()) == true)
			return getPlayer();
		else if(checkWinCondition(getOpponent()) == true)
			return getOpponent();
		else
			return null;
	}
	
}