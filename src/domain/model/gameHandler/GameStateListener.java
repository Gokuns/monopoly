package domain.model.gameHandler;

import java.util.HashMap;

public interface GameStateListener {
	public void update(GameState source, HashMap<String, String> map);
}
