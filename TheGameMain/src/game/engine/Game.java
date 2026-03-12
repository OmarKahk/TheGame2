package game.engine;
import java.io.*;

import java.util.ArrayList;
import game.engine.dataloader.*;
import game.engine.cards.*;
import game.engine.monsters.Monster;
import java.util.*;
public class Game {
private Board board;
private ArrayList<Monster> allMonsters;
private Monster player;
private Monster opponent;
private Monster current;
	public Game(Role playerRole) throws IOException
	{
		board = new Board(DataLoader.readCards());
		allMonsters = DataLoader.readMonsters();
		player = selectRandomMonsterByRole(playerRole);
		if(playerRole== Role.LAUGHER)
			opponent = selectRandomMonsterByRole(Role.SCARER);
		else
			opponent = selectRandomMonsterByRole(Role.LAUGHER);
		current = player;
	}
	private Monster selectRandomMonsterByRole(Role role) 
	{
		ArrayList<Monster> monsters = new ArrayList<>();
		for(int i = 0; i<allMonsters.size();i++)
		{
			if(allMonsters.get(i).getRole() == role)
			{
				monsters.add(allMonsters.get(i));
			}
		}
		int n = (int)(Math.random()*monsters.size());
		return monsters.get(n);
	}
	public Monster getCurrent() {
		return current;
	}
	public void setCurrent(Monster current) {
		this.current = current;
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
	
	
}
