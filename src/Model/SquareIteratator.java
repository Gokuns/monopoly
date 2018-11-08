package Model;

import java.util.ListIterator;

public interface SquareIteratator extends ListIterator<Square> {
	
	boolean hasOuter();
	Square outer();
	boolean hasInnter();
	Square inner();
}
