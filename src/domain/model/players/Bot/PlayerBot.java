package domain.model.players.Bot;

import domain.model.players.Player;

public class PlayerBot extends Player{
	private Behaviour behaviour;
	private int type;
	

	public PlayerBot(String name, int ID, int type) {
		super(name,ID);
		this.setBot(true);
		this.setType(type);
		if(type==0) this.setBehaviour(new Thrifty());
		if(type==1) this.setBehaviour(new Random());
		if(type==2) this.setBehaviour(new Greedy());
	}
	
	public boolean tryToAct() {
		return behaviour.act();
		
	}

	/**
	 * @return the behaviour
	 */
	public Behaviour getBehaviour() {
		return behaviour;
	}

	/**
	 * @param behaviour the behaviour to set
	 */
	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

}
