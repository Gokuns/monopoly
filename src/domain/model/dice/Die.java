package domain.model.dice;

public abstract class Die {
	protected abstract FaceValue roll();
	protected abstract FaceValue getCurrentFaceValue();
}

