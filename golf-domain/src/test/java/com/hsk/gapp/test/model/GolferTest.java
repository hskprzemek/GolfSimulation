package com.hsk.gapp.test.model;

import junit.framework.TestCase;

import com.hsk.gapp.model.Golfer;

public class GolferTest extends TestCase {

	public void testPersonalFields() {
		Golfer golfer = new Golfer();
		int age = 41;
		Double handicap = new Double(5.8);
		String first = "Jurek";
		String last = "Dudek";
		golfer.setFirstName(first);
		golfer.setLastName(last);
		golfer.setHandicap(handicap);
		golfer.setAge(age);

		assertEquals(golfer.getAge(), age);
		assertEquals(golfer.getHandicap(), handicap);
		assertEquals(golfer.getFirstName(), first);
		assertEquals(golfer.getLastName(), last);
	}

}
