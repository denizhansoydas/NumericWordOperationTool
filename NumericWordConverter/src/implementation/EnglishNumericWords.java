package implementation;

import java.util.Arrays;
import java.util.List;

public class EnglishNumericWords extends LanguageNumericWords{
	public EnglishNumericWords() {
		// super();
		allowedStrings = Arrays.asList("and", "zero", "one", "two", "three", "four", "five",
	            "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
	            "seventeen", "eighteen", "nineteen", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
	            "ninety", "hundred", "thousand", "million", "billion", "trillion");
		regexCharSet = "[^a-zA-Z\\s]";
		units = new String[]{
	            "", "one", "two", "three", "four", "five", "six", "seven",
	            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
	            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
	    };
		tens = new String[]{
	            "",        // 0
	            "",        // 1
	            "twenty",  // 2
	            "thirty",  // 3
	            "forty",   // 4
	            "fifty",   // 5
	            "sixty",   // 6
	            "seventy", // 7
	            "eighty",  // 8
	            "ninety"   // 9
	    };
	}

	@Override
	int convert(String num) {
		return Integer.parseInt(convertTextualNumbersInDocument(num));
	}

	@Override
	String convert(int num) {
		if(num == 0)
			return "zero";
		return convertHelper(num);
	}
	
	private String convertHelper(int num) {
		{
	        if (num < 0) {
	            return "minus " + convertHelper(-num);
	        }

	        if (num < 20) {
	            return units[num];
	        }

	        if (num < 100) {
	            return tens[num / 10] + ((num % 10 != 0) ? " " : "") + units[num % 10];
	        }

	        if (num < 1000) {
	            return units[num / 100] + " hundred" + ((num % 100 != 0) ? " " : "") + convertHelper(num % 100);
	        }

	        if (num < 1000000) {
	            return convertHelper(num / 1000) + " thousand" + ((num % 1000 != 0) ? " " : "") + convertHelper(num % 1000);
	        }

	        if (num < 1000000000) {
	            return convertHelper(num / 1000000) + " million" + ((num % 1000000 != 0) ? " " : "") + convertHelper(num % 1000000);
	        }

	        return convertHelper(num / 1000000000) + " billion"  + ((num % 1000000000 != 0) ? " " : "") + convertHelper(num % 1000000000);
	    }
	}

	@Override
	long convertWordsToNum(List<String> words) {
		long finalResult = 0;
        long intermediateResult = 0;
        for (String str : words) {
            // clean up string for easier processing
            str = str.toLowerCase().replaceAll("[^a-zA-Z\\s]", "");
            if (str.equalsIgnoreCase("zero")) {
                intermediateResult += 0;
            } else if (str.equalsIgnoreCase("one")) {
                intermediateResult += 1;
            } else if (str.equalsIgnoreCase("two")) {
                intermediateResult += 2;
            } else if (str.equalsIgnoreCase("three")) {
                intermediateResult += 3;
            } else if (str.equalsIgnoreCase("four")) {
                intermediateResult += 4;
            } else if (str.equalsIgnoreCase("five")) {
                intermediateResult += 5;
            } else if (str.equalsIgnoreCase("six")) {
                intermediateResult += 6;
            } else if (str.equalsIgnoreCase("seven")) {
                intermediateResult += 7;
            } else if (str.equalsIgnoreCase("eight")) {
                intermediateResult += 8;
            } else if (str.equalsIgnoreCase("nine")) {
                intermediateResult += 9;
            } else if (str.equalsIgnoreCase("ten")) {
                intermediateResult += 10;
            } else if (str.equalsIgnoreCase("eleven")) {
                intermediateResult += 11;
            } else if (str.equalsIgnoreCase("twelve")) {
                intermediateResult += 12;
            } else if (str.equalsIgnoreCase("thirteen")) {
                intermediateResult += 13;
            } else if (str.equalsIgnoreCase("fourteen")) {
                intermediateResult += 14;
            } else if (str.equalsIgnoreCase("fifteen")) {
                intermediateResult += 15;
            } else if (str.equalsIgnoreCase("sixteen")) {
                intermediateResult += 16;
            } else if (str.equalsIgnoreCase("seventeen")) {
                intermediateResult += 17;
            } else if (str.equalsIgnoreCase("eighteen")) {
                intermediateResult += 18;
            } else if (str.equalsIgnoreCase("nineteen")) {
                intermediateResult += 19;
            } else if (str.equalsIgnoreCase("twenty")) {
                intermediateResult += 20;
            } else if (str.equalsIgnoreCase("thirty")) {
                intermediateResult += 30;
            } else if (str.equalsIgnoreCase("forty")) {
                intermediateResult += 40;
            } else if (str.equalsIgnoreCase("fifty")) {
                intermediateResult += 50;
            } else if (str.equalsIgnoreCase("sixty")) {
                intermediateResult += 60;
            } else if (str.equalsIgnoreCase("seventy")) {
                intermediateResult += 70;
            } else if (str.equalsIgnoreCase("eighty")) {
                intermediateResult += 80;
            } else if (str.equalsIgnoreCase("ninety")) {
                intermediateResult += 90;
            } else if (str.equalsIgnoreCase("hundred")) {
                intermediateResult *= 100;
            } else if (str.equalsIgnoreCase("thousand")) {
                intermediateResult *= 1000;
                finalResult += intermediateResult;
                intermediateResult = 0;
            } else if (str.equalsIgnoreCase("million")) {
                intermediateResult *= 1000000;
                finalResult += intermediateResult;
                intermediateResult = 0;
            } else if (str.equalsIgnoreCase("billion")) {
                intermediateResult *= 1000000000;
                finalResult += intermediateResult;
                intermediateResult = 0;
            } else if (str.equalsIgnoreCase("trillion")) {
                intermediateResult *= 1000000000000L;
                finalResult += intermediateResult;
                intermediateResult = 0;
            }
        }

        finalResult += intermediateResult;
        intermediateResult = 0;
        return finalResult;
	}
}
