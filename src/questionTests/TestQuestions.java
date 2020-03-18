package questionTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import question.*;

/*
 * Author: Bryan Wilson
 * 
 * Purpose: These tests are to make sure the Question classes are functioning properly. Tests not provided
 * 			for getter methods. Tests here are for both TrueFalse and MultipleChoice classes.
 * 
 */

class TestQuestions {
	
	@Test
	void testTrueFalseGetOptions() {
		Question question = new TrueFalse("Sample Question", "TRUE");
		ArrayList<String> expecteds = new ArrayList<String>();
		expecteds.add("TRUE");
		expecteds.add("FALSE");
		ArrayList<String> actuals = question.getOptions();
		
		assertEquals(expecteds, actuals);
	}
	
	@Test
	void testMultipleChoiceGetOptions() {
		Question question = new MultipleChoice("Sample Question", "Answer", "optionA", 
								"optionB", "optionC", "optionD");
		ArrayList<String> expecteds = new ArrayList<String>();
		expecteds.add("optionA");
		expecteds.add("optionB");
		expecteds.add("optionC");
		expecteds.add("optionD");
		ArrayList<String> actuals = question.getOptions();
		
		assertEquals(expecteds, actuals);
	}
	
	@Test
	void testTrueFalseCheckAnswer() {
		Question question = new TrueFalse("Sample Question", "TRUE");
		assertTrue(question.checkAnswer("TRUE"));
		assertTrue(question.checkAnswer("True"));
		assertTrue(question.checkAnswer("tRUe"));
		assertTrue(question.checkAnswer("true"));
		assertFalse(question.checkAnswer("false"));
		assertFalse(question.checkAnswer("0"));
		assertFalse(question.checkAnswer("bfhdebkagj"));
	}
	
	@Test
	void testMultipleChoiceCheckAnswer() {
		Question question = new MultipleChoice("Sample Question", "Answer", "optionA", 
				"optionB", "optionC", "optionD");
		assertTrue(question.checkAnswer("Answer"));
		assertTrue(question.checkAnswer("ANSWER"));
		assertTrue(question.checkAnswer("aNsWeR"));
		assertTrue(question.checkAnswer("answer"));
		assertFalse(question.checkAnswer("optionA"));
		assertFalse(question.checkAnswer("1234"));
		assertFalse(question.checkAnswer("bfhdebkagj"));
	}

}
