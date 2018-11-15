package domain.model;

import java.util.ListIterator;

public interface ISquareIteratator extends ListIterator<Square> {
	
	boolean hasOuter();
	boolean hasInner();
	Square outer();
	Square inner();
	public int outerIndex();
	public int innerIndex();
	void add(int layer, Square arg0);
}
