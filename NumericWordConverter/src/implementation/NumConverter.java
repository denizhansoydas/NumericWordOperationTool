package implementation;

import definition.NumConverterI;
import exceptions.InvalidNumericWordException;

public class NumConverter implements NumConverterI {

    @Override
    public int convertToInt(String locale, String sentence) throws InvalidNumericWordException{
        LanguageNumericWords numericWords = null;
    	if(locale.equals("en")) {
    		numericWords = new EnglishNumericWords();        	
        }
    	else if(locale.equals("tr")) {
        	numericWords = new TurkishNumericWords();
        }
    	else {
    		numericWords = new EnglishNumericWords(); //english by default. Well, you are right, there is no need for the first if. However, I want it to be temporarily there to make the code more readable.
    	}
        return numericWords.convert(sentence);
    }

    @Override
    public String convertToString(String locale, int num) {
    	LanguageNumericWords numericWords = null;
    	if(locale.equals("en")) {
    		numericWords = new EnglishNumericWords();    
        }
    	else if(locale.equals("tr")) {
        	numericWords = new TurkishNumericWords();
        }
    	else {
    		numericWords = new EnglishNumericWords(); //english by default. Well, you are right, there is no need for the first if. However, I want it to be temporarily there to make the code more readable.
    	}
    	return numericWords.convert(num);
    }

    public String checkInputs(String locale, int num) {
    	return "You've requested for: " + locale + " , " +num;
    }
    public String checkInputs(String locale, String num) {
    	return "You've requested for: " + locale + " , " +num;
    }
}
