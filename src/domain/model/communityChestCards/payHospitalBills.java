package domain.model.communityChestCards;

import domain.model.Board;
import domain.model.CommunityChestCard;
import domain.model.GameState;
import domain.model.Player;

public class payHospitalBills extends CommunityChestCard{

	
	@Override
	protected void action() {
		// TODO Auto-generated method stub
		Board board = Board.getInstance();
		GameState game = GameState.getInstance();
		Player currentPlayer = game.getCurrentPlayer();
		
		int newPoolBalance = board.getPoolBalance() + 100;
		int newPlayerBalance = currentPlayer.getBalance() - 100;
		
		board.setPoolBalance(newPoolBalance);
		currentPlayer.setBalance(newPlayerBalance);
	}

	
	
}
