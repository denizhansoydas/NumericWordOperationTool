package old;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public abstract class LanguageNamingRules_old {
	
	public String[] digits;
	String[] multiplies_of_ten;
	public String[] powers_of_ten;
	final static int BASE = 10;
	
	LanguageNamingRules_old(){		
		digits = new String[BASE];
		multiplies_of_ten = new String[BASE];
		powers_of_ten = new String[BASE];
		
	}
	public int getIndex(String[] arr, String num) {
		int index = - 1;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i].equals(num))
				index = i;
		}
		return index;
	}
}
