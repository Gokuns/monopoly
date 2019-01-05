package domain.model.cards;

import domain.model.players.Player;

//import domain.model.dice.FaceValue;

public abstract class Card {
	private String name;
	private String desc;
	
	public Card(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}
	protected abstract void action(Player p);

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getName() {
		return name;
	}
	
}


