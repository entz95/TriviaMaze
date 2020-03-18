package question;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * Author: Bryan Wilson
 * 
 * Purpose: This class is designed to handle the TrueFalse implementation of the Question class. Holds
 * 			no new data, but implements the getOptions functionality relative to a true or false question.
 * 
 * Version: 1.0
 * 
 */

public class TrueFalse extends Question implements Serializable{


	private static final long serialVersionUID = -4115852299859891575L;

	public TrueFalse(String question, String answer) {
		super(question, answer);
	}

	@Override
	public ArrayList<String> getOptions() {
		ArrayList<String> toRet = new ArrayList<String>();
		toRet.add("TRUE");
		toRet.add("FALSE");
		
		return toRet;
	}

}
