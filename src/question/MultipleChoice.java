package question;

import java.util.ArrayList;

public class MultipleChoice extends Question {
	
	ArrayList<String> options;
	
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
