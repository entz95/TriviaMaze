package question;

import java.io.Serializable;
import java.util.ArrayList;

public class MultipleChoice extends Question implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9198620724096163343L;
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
