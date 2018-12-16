/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import domain.model.dice.Cup;
import domain.model.dice.FaceValue;

/**
 * @author Ege Onat
 *
 */
public class CupTest {
	
	private List<FaceValue> regularFaceValues = new ArrayList<FaceValue>()  {{
	    add(FaceValue.ONE);
	    add(FaceValue.TWO);
	    add(FaceValue.THREE);
	    add(FaceValue.FOUR);
	    add(FaceValue.FIVE);
	    add(FaceValue.SIX);
	}};
	
	private List<FaceValue> speedFaceValues = new ArrayList<FaceValue>()  {{
	    add(FaceValue.ONE);
	    add(FaceValue.TWO);
	    add(FaceValue.THREE);
	    add(FaceValue.BUS);
	    add(FaceValue.MRMONOPOLY);
	}};
	
	@Before
    public void setUp() throws Exception {
		Cup.getInstance().rollCup();
	}

	/**
	 * Test method for {@link domain.model.dice.Cup#rollCup()}.
	 */
	@Test
	public void testRollCup() {
		Cup.getInstance().rollCup();
		List<FaceValue> faceValues = Cup.getInstance().getFaceValues();
		assertEquals(faceValues.size(), 3);
		assertTrue(regularFaceValues.contains(faceValues.get(0)));
		assertTrue(regularFaceValues.contains(faceValues.get(1)));
		assertTrue(speedFaceValues.contains(faceValues.get(2)));
	}

	/**
	 * Test method for {@link domain.model.dice.Cup#convertFaceValueToInt()}.
	 */
	@Test
	public void testConvertFaceValueToInt() {
		assertThat(Cup.getInstance().convertFaceValueToInt().get(0), 
				anyOf(is(1), is(2), is(3), is(4), is(5), is(6)));
		assertThat(Cup.getInstance().convertFaceValueToInt().get(1), 
				anyOf(is(1), is(2), is(3), is(4), is(5), is(6)));
		assertThat(Cup.getInstance().convertFaceValueToInt().get(2), 
				anyOf(is(1), is(2), is(3), is(0)));
	}

	/**
	 * Test method for {@link domain.model.dice.Cup#getFaceValues()}.
	 */
	@Test
	public void testGetFaceValues() {
		List<FaceValue> faceValues = Cup.getInstance().getFaceValues();
		assertEquals(faceValues.size(), 3);
		assertTrue(regularFaceValues.contains(faceValues.get(0)));
		assertTrue(regularFaceValues.contains(faceValues.get(1)));
		assertTrue(speedFaceValues.contains(faceValues.get(2)));
	}

	/**
	 * Test method for {@link domain.model.dice.Cup#setFaceValues(java.util.List)}.
	 */
	@Test
	public void testSetFaceValues() {
		Cup.getInstance().setFaceValues(new ArrayList<FaceValue>() {{
			add(FaceValue.ONE); 
			add(FaceValue.TWO); 
			add(FaceValue.BUS);
			}});
		assertThat(Cup.getInstance().getFaceValues().get(0), is(equalTo(FaceValue.ONE)));
		assertThat(Cup.getInstance().getFaceValues().get(1), is(equalTo(FaceValue.TWO)));
		assertThat(Cup.getInstance().getFaceValues().get(2), is(equalTo(FaceValue.BUS)));
		

		Cup.getInstance().setFaceValues(new ArrayList<FaceValue>() {{
			add(FaceValue.MRMONOPOLY); 
			add(FaceValue.MRMONOPOLY); 
			add(FaceValue.MRMONOPOLY);
			}});
		assertThat(Cup.getInstance().getFaceValues().get(0), is(equalTo(FaceValue.MRMONOPOLY)));
		assertThat(Cup.getInstance().getFaceValues().get(1), is(equalTo(FaceValue.MRMONOPOLY)));
		assertThat(Cup.getInstance().getFaceValues().get(2), is(equalTo(FaceValue.MRMONOPOLY)));
	}

	/**
	 * Test method for {@link domain.model.dice.Cup#roll3Dice()}.
	 */
	@Test
	public void testRoll3Dice() {
		Cup.getInstance().rollCup();
		List<FaceValue> faceValues = Cup.getInstance().getFaceValues();
		assertEquals(faceValues.size(), 3);
		assertTrue(regularFaceValues.contains(faceValues.get(0)));
		assertTrue(regularFaceValues.contains(faceValues.get(1)));
		assertTrue(regularFaceValues.contains(faceValues.get(2)));
	}
}
