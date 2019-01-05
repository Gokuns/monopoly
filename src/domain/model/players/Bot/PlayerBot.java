package domain.model.players.Bot;

import domain.model.players.Player;

public class PlayerBot extends Player{
	private Behaviour behaviour;
	private String name;
	private int ID;
	private int type;
	

	public PlayerBot(String name, int ID, int type) {
		super(name,ID);
		this.type = type;
		if(type==0) this.behaviour = new Thrifty();
		if(type==1) this.behaviour = new Random();
		if(type==2) this.behaviour = new Greedy();
	}
	
	public void tryToAct() {
		
		
	}

}
