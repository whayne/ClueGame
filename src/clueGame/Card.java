package clueGame;

public class Card {
	public enum CardType {ROOM, WEAPON, PERSON};
	
	public String name;
	
	public Card() {
		super();
	}
	
	public Card(String name) {
		super();
		this.name = name;
	}
}
