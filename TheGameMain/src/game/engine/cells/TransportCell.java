package game.engine.cells;

public abstract class TransportCell extends Cell {
	
	int effect;
	
	public TransportCell(String name, int effect) {
		super(name);
		this.effect = effect;
	}
}
