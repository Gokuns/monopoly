package domain.model;

import java.util.List;

public class DeckFactory {
	private static DeckFactory deckFactory;
	
	private DeckFactory(){
		
	}
	
	public static synchronized DeckFactory getInstance(){
		if(deckFactory == null){
			return new DeckFactory();
		}
		return deckFactory;
	}
	
}
