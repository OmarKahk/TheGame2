package game.engine.dataloader;
import game.engine.cards.*;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import game.engine.cells.*;
import game.engine.monsters.*;
import game.engine.*;

public class DataLoader {
	
	public static final String CARDS_FILE_NAME = "cards.csv";
	public static final String CELLS_FILE_NAME = "cells.csv";
	public static final String MONSTERS_FILE_NAME = "monsters.csv";
	
	
	public static ArrayList<Card> readCards() throws IOException {
		
		ArrayList<Card> cards = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(CARDS_FILE_NAME));
		String line;
		
		while((line = br.readLine()) != null) {
			String[] data = line.split("," , -1);
			String type = data[0];
	        String name = data[1];
	        String description = data[2];
	        int rarity = Integer.parseInt(data[3]);
	        Card card = null;

	        switch(type) {

	            case "SWAPPER":
	                card = new SwapperCard(name, description, rarity);
	                break;

	            case "SHIELD":
	                card = new ShieldCard(name, description, rarity);
	                break;

	            case "ENERGYSTEAL":
	                int energy = Integer.parseInt(data[4]);
	                card = new EnergyStealCard(name, description, rarity, energy);
	                break;

	            case "STARTOVER":
	                boolean lucky = Boolean.parseBoolean(data[4]);
	                card = new StartOverCard(name, description, rarity, lucky);
	                break;

	            case "CONFUSION":
	                int duration = Integer.parseInt(data[4]);
	                card = new ConfusionCard(name, description, rarity, duration);
	                break;
	        }
	        cards.add(card);
		}
		br.close();
		return cards;
	}
	public static ArrayList<Cell> readCells() throws IOException {

	    ArrayList<Cell> cells = new ArrayList<>();

	    BufferedReader br = new BufferedReader(new FileReader(CELLS_FILE_NAME));
	    String line;

	    while ((line = br.readLine()) != null) {

	        String[] data = line.split("," , -1);

	        Cell cell;

	        if (data.length == 3) {   // DoorCell
	            String name = data[0];
	            Role role = Role.valueOf(data[1]);
	            int energy = Integer.parseInt(data[2]);

	            cell = new DoorCell(name, role, energy);

	        } else {   // TransportCells
	            String name = data[0];
	            int effect = Integer.parseInt(data[1]);

	            if (effect > 0)
	                cell = new ConveyorBelt(name, effect);
	            else
	                cell = new ContaminationSock(name, effect);
	        }

	        cells.add(cell);
	    }

	    br.close();
	    return cells;
	}
	public static ArrayList<Monster> readMonsters() throws IOException {

	    ArrayList<Monster> monsters = new ArrayList<>();

	    BufferedReader br = new BufferedReader(new FileReader(MONSTERS_FILE_NAME));
	    String line;

	    while ((line = br.readLine()) != null) {

	    	String[] data = line.split("," , -1);

	        String type = data[0];
	        String name = data[1];
	        String description = data[2];
	        Role role = Role.valueOf(data[3]);
	        int energy = Integer.parseInt(data[4]);

	        Monster monster = null;

	        switch (type) {

	            case "DASHER":
	                monster = new Dasher(name, description, role, energy);
	                break;

	            case "DYNAMO":
	                monster = new Dynamo(name, description, role, energy);
	                break;
	                
	            case "MULTITASKER":
	                monster = new MultiTasker(name, description, role, energy);
	                break;
	                
	            case "SCHEMER":
	                monster = new Schemer(name, description, role, energy);
	                break;
	                
	        }

	        monsters.add(monster);
	    }

	    br.close();
	    return monsters;
	}
	
	
}
