package com.lms.twofoldselector;

import java.util.HashMap;
import java.util.Map;

public class DataProvider {

	public static final String[] summaries = {
		"A",
		"B",
		"C",
		"D",
		"E",
		"F",
		"G",
		"H"
	};
	
	public static final Map<String, String[]> details = new HashMap<>(); 
	
	static {
		 details.put("A",new String[] {"A1", "A2", "A3"});
		 details.put("B",new String[] {"B1", "B2", "B3"});
		 details.put("C",new String[] {"C1", "C2", "C3"});
		 details.put("D",new String[] {"D1", "D2", "D3"});
		 details.put("E",new String[] {"E1", "E2", "E3"});
		 details.put("F",new String[] {"F1", "F2", "F3"});
		 details.put("G",new String[] {"G1", "G2", "G3"});
		 details.put("H",new String[] {"G1", "H2", "H3"});
	}
		
}
