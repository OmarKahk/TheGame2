package game.engine;
import java.io.*;

import java.util.ArrayList;
import game.engine.dataloader.*;
import game.engine.cards.*;
import game.engine.monsters.Monster;
import java.util.*;
public class Game {
private Board board;
private ArrayList<Monster> allMonsers;
private Monster player;
private Monster opponent;
private Monster current;
	Game(Role playerRole) throws IOException
	{
		ArrayList<Card> cards = DataLoader.readCards() ;
		board = new Board(cards);
		allMonsers = DataLoader.readMonsters();
		player = selectRandomMonsterByRole(player.getRole());
		opponent = selectRandomMonsterByRole(opponent.getRole());
		
	}
	private Monster selectRandomMonsterByRole(Role role) throws IOException
	{
		ArrayList<Monster> mosnters = DataLoader.readMonsters();
		double x = mosnters.size()*(Math.random())+1 ;
		int n = (int)x;
		return mosnters.get(n);
	}
}
