package implementation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import exceptions.InvalidNumericWordException;

public abstract class LanguageNumericWords {
	List<String> allowedStrings;
	abstract int convert(String num) throws InvalidNumericWordException;
	abstract String convert(int num);
	String regexCharSet;
	String[] units;
	String[] tens;
	String[] bigPowersOfTen;
	LanguageNumericWords(){
		
	}
    List<String> cleanAndTokenizeText(String sentence) {
        List<String> words = new LinkedList<String>(Arrays.asList(sentence.split(" ")));

        // remove hyphenated textual numbers
        for (int i = 0; i < words.size(); i++) {
            String str = words.get(i);
            if (str.contains("-")) {
                List<String> splitWords = Arrays.asList(str.split("-"));
                // just check the first word is a textual number. Caters for
                // "twenty-five," without having to strip the comma
                if (splitWords.size() > 1 && allowedStrings.contains(splitWords.get(0))) {
                    words.remove(i);
                    words.addAll(i, splitWords);
                }
            }
        }
        return words;
    }
    
    abstract long convertWordsToNum(List<String> words);
	
    List<String> replaceTextualNumbers(List<String> words) {

        // holds each group of textual numbers being processed together. e.g.
        // "one" or "five hundred and two"
        List<String> processingList = new LinkedList<String>();

        int i = 0;
        while (i < words.size() || !processingList.isEmpty()) {

            // caters for sentences only containing one word (that is a number)
            String word = "";
            if (i < words.size()) {
                word = words.get(i);
            }

            // strip word of all punctuation to make it easier to process
            String wordStripped = word.replaceAll(regexCharSet, "").toLowerCase();

            // 2nd condition: skip "and" words by themselves and at start of num
            if (allowedStrings.contains(wordStripped) && !(processingList.size() == 0 && wordStripped.equals("and"))) {
                words.remove(i); // remove from main list, will process later
                processingList.add(word);
            } else if (processingList.size() > 0) {
                // found end of group of textual words to process

                //if "and" is the last word, add it back in to original list
                String lastProcessedWord = processingList.get(processingList.size() - 1);
                if (lastProcessedWord.equals("and")) {
                    words.add(i, "and");
                    processingList.remove(processingList.size() - 1);
                }

                // main logic here, does the actual conversion
                String wordAsDigits = String.valueOf(convertWordsToNum(processingList));

                wordAsDigits = retainPunctuation(processingList, wordAsDigits);
                words.add(i, String.valueOf(wordAsDigits));

                processingList.clear();
                i += 2;
            } else {
                i++;
            }
        }

        return words;
    }
    
    String retainPunctuation(List<String> processingList, String wordAsDigits) {

        String lastWord = processingList.get(processingList.size() - 1);
        char lastChar = lastWord.trim().charAt(lastWord.length() - 1);
        if (!Character.isLetter(lastChar)) {
            wordAsDigits += lastChar;
        }

        String firstWord = processingList.get(0);
        char firstChar = firstWord.trim().charAt(0);
        if (!Character.isLetter(firstChar)) {
            wordAsDigits = firstChar + wordAsDigits;
        }

        return wordAsDigits;
    }
    
    
    String convertTextualNumbersInDocument(String inputText) throws InvalidNumericWordException{

        // splits text into words and deals with hyphenated numbers. Use linked
        // list due to manipulation during processing
    	String input_copy = new String(inputText);
        List<String> words = new LinkedList<String>(cleanAndTokenizeText(inputText));

        boolean illegalRepetition = checkIllegalRepetition(words);
        boolean illegalOrdering = false;
        for(int i = 0; i < words.size(); i++) {
        	int firstBigPowerIndex = -1;
        	for(int k = 0; k < bigPowersOfTen.length; k++) {
        		if(bigPowersOfTen[k].equals(words.get(i))) {
        			firstBigPowerIndex = k;
        		}
        	}
        	
        	for(int j = i + 1; j < words.size(); j++) {
        		int secondBigPowerIndex = -1;
        		for(int k = 0; k < bigPowersOfTen.length; k++) {
        			if(bigPowersOfTen[k].equals(words.get(j))) {
        				secondBigPowerIndex = k;
        			}
        		}
        		if(firstBigPowerIndex != -1 && secondBigPowerIndex != -1 && firstBigPowerIndex < secondBigPowerIndex) {
        			System.out.println("Wrong Ordering!");
        			throw new InvalidNumericWordException(input_copy);
        		}
        	}
        }
        if(illegalRepetition) {
        	throw new InvalidNumericWordException(inputText); 
        }
        	
        // replace all the textual numbers
        words = replaceTextualNumbers(words);

        // put spaces back in and return the string. Should be the same as input
        // text except from textual numbers
        return wordListToString(words);
    }
    String wordListToString(List<String> list) {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (i == 0 && str != null) {
                result.append(list.get(i));
            } else if (str != null) {
                result.append(" " + list.get(i));
            }
        }

        return result.toString();
    }
    
    public boolean checkIllegalRepetition(List<String> list){
    	int[] occurrenceCounts = new int[bigPowersOfTen.length];
    	for(int i = 0; i < list.size(); i++) {
    		for(int j = 0; j < bigPowersOfTen.length; j++) {
    			if(list.get(i).equals(bigPowersOfTen[j])) {
    				occurrenceCounts[j]++;
    			}
    		}
    	}
    	for(int i = 0; i < occurrenceCounts.length; i++) {
    		if(occurrenceCounts[i] >= 2) {
    			return true;
    		}
    	}
    	return false;
    }
}
