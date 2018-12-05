package domain.model;

import domain.model.dice.faceValue;

public abstract class Card {
	private String desc;
	
	public Card(String desc) {
		this.desc = desc;
	}
	protected abstract void action();

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
	
}


