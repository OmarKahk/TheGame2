package game.engine.cells;

import game.engine.Board;
import game.engine.cards.Card;
import game.engine.monsters.Monster;

public class CardCell extends Cell {
	
	public CardCell(String name) {
        super(name);
    }
	
	public void onLand(Monster landingMonster, Monster opponentMonster) {
		Card CardDrawn = Board.drawCard();
		CardDrawn.performAction(landingMonster, opponentMonster);
	}
   
}
