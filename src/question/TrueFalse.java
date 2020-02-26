package question;

import java.util.ArrayList;

public class TrueFalse extends Question {

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
