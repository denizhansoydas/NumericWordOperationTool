package old;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class EnglishNamingRules_old extends LanguageNamingRules_old{

	String[] tens;
	public EnglishNamingRules_old() {
		super();
		tens = new String[BASE];
		
		digits[0] = "zero";
		digits[1] = "one";
		digits[2] = "two";
		digits[3] = "three";
		digits[4] = "four";
		digits[5] = "five";
		digits[6] = "six";
		digits[7] = "seven";
		digits[8] = "eight";
		digits[9] = "nine";
		
		
		multiplies_of_ten[1] = "ten";
		multiplies_of_ten[2] = "twenty";
		multiplies_of_ten[3] = "thirty";
		multiplies_of_ten[4] = "fourty";
		multiplies_of_ten[5] = "fifty";
		multiplies_of_ten[6] = "sixty";
		multiplies_of_ten[7] = "seventy";
		multiplies_of_ten[8] = "eighty";
		multiplies_of_ten[9] = "ninety";
		
		powers_of_ten[1] = "ten";
		powers_of_ten[2] = "hundred";
		powers_of_ten[3] = "thousand";
		//powers_of_ten[4] = "ten thousand";
		//powers_of_ten[5] = "hundred thousand";
		powers_of_ten[6] = "million";
		//powers_of_ten[7] = "ten million";
		//powers_of_ten[8] = "hundred million";
		powers_of_ten[9] = "billion";
		
		
		tens[0] = "ten";
		tens[1] = "eleven";
		tens[2] = "twelve";
		tens[3] = "thirteen";
		tens[4] = "fourteen";
		tens[5] = "fifteen";
		tens[6] = "sixteen";
		tens[7] = "seventeen";
		tens[8] = "eighteen";
		tens[9] = "nineteen";
	}
	
}
