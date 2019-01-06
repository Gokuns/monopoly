package domain.model.players.Bot;

public class Random implements Behaviour {

	public Random() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean act() {
		// TODO Auto-generated method stub
		java.util.Random rand = new java.util.Random();
		int decision = rand.nextInt(2);
		if(decision!=0) {
			return true;
		}else return false;

	}

}
