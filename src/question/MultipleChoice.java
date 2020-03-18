package question;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Author: Bryan Wilson
 * 
 * Purpose: This class id designed to handle the MultipleChoice implementation of the Question class. Holds
 * 			an ArrayList of its given options as well as implementing the getOptions method relative to the 
 * 			implementation of a multiple choice question.
 * 
 * Version: 1.0
 * 
 */

public class MultipleChoice extends Question implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9198620724096163343L;
	private ArrayList<String> options;
	
	public MultipleChoice(String ans, String ques, String optA, String optB, String optC, String optD) {
		super(ans, ques);
		options  = new ArrayList<String>();
		options.add(optA);
		options.add(optB);
		options.add(optC);
		options.add(optD);
		
	}

	@Override
	public ArrayList<String> getOptions() {
		return options;
	}

}
