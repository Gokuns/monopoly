package domain.model;

import java.util.ArrayList;
import java.util.List;

public class SquareIterable implements SquareIteratator {
	private List<List<Square>> Squares = new ArrayList<List<Square>>();

	@Override
	public void add(Square arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Square next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Square previous() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void set(Square arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasOuter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasInnter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Square outer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Square inner() {
		// TODO Auto-generated method stub
		return null;
	}

}
