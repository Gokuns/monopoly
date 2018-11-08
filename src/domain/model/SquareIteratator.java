package domain.model;

import java.util.ListIterator;

public interface SquareIteratator extends ListIterator<Square> {
	
	boolean hasOuter();
	boolean hasInnter();
	Square outer();
	Square inner();
}
