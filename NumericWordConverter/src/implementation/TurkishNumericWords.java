package implementation;

import java.util.Arrays;
import java.util.List;

import exceptions.InvalidNumericWordException;

public class TurkishNumericWords extends LanguageNumericWords{
	public TurkishNumericWords() {
		super();
		allowedStrings = Arrays.asList("sýfýr", "bir", "iki", "üç", "dört", "beþ",
	            "altý", "yedi", "sekiz", "dokuz", "on", "yirmi", "otuz", "kýrk", "elli", "altmýþ", "yetmiþ", "seksen",
	            "doksan", "yüz", "bin", "milyon", "milyar", "trilyon");
		regexCharSet = "[^a-zA-Z\\sýçþðüö]";
		units = new String[]{
	            "", "bir", "iki", "üç", "dört", "beþ", "altý", "yedi",
	            "sekiz", "dokuz", "on"
	    };
		tens = new String[]{
	            "",        // 0
	            "on",        // 1
	            "yirmi",  // 2
	            "otuz",  // 3
	            "kýrk",   // 4
	            "elli",   // 5
	            "altmýþ",   // 6
	            "yetmiþ", // 7
	            "seksen",  // 8
	            "doksan"   // 9
	    };
		bigPowersOfTen = new String[] {
			"bin",
			"milyon",
			"milyar",
			"trilyon"
		};
	}

	@Override
	int convert(String num) throws InvalidNumericWordException{
		int chIndex = num.indexOf(' ');
		int res = 0;
		if(chIndex != -1 && num.substring(0,chIndex).equals("eksi")) {
			res = -Integer.parseInt(convertTextualNumbersInDocument(num.substring(chIndex + 1, num.length())));
		}else {
			res = Integer.parseInt(convertTextualNumbersInDocument(num)); 
		} 
		if(res == 0 && !num.equals("sýfýr")) {
			throw new InvalidNumericWordException(num);
		}
		return res;
	}

	@Override
	String convert(int num) {
		if(num == 0)
			return "sýfýr";
		return convertHelper(num);
	}
	private String convertHelper(int num) {
		{
	        if (num < 0) {
	            return "eksi " + convertHelper(-num);
	        }
	        
	        if (num < 11) {
	            return units[num];
	        }

	        if (num < 100) {
	            return tens[num / 10] + ((num % 10 != 0) ? " " : "") + units[num % 10];
	        }

	        if (num < 1000) {
	            return units[ num/100 == 1 ? 0 : num / 100] + " yüz" + ((num % 100 != 0) ? " " : "") + convertHelper(num % 100);
	        }

	        if (num < 1000000) {
	            return convertHelper(num/1000 == 1 ? 0 : num / 1000) + " bin" + ((num % 1000 != 0) ? " " : "") + convertHelper(num % 1000);
	        }

	        if (num < 1000000000) {
	            return convertHelper(num / 1000000) + " milyon" + ((num % 1000000 != 0) ? " " : "") + convertHelper(num % 1000000);
	        }

	        return convertHelper(num / 1000000000) + " milyar"  + ((num % 1000000000 != 0) ? " " : "") + convertHelper(num % 1000000000);
	    }
	}

	@Override
	long convertWordsToNum(List<String> words) {
        long finalResult = 0;
        long intermediateResult = 0;
        for (String str : words) {
            // clean up string for easier processing
            str = str.toLowerCase().replaceAll("[^a-zA-Z\\sýçþðüö]", "");
            if (str.equalsIgnoreCase("sýfýr")) {
                intermediateResult += 0;
            } else if (str.equalsIgnoreCase("bir")) {
                intermediateResult += 1;
            } else if (str.equalsIgnoreCase("iki")) {
                intermediateResult += 2;
            } else if (str.equalsIgnoreCase("üç")) {
                intermediateResult += 3;
            } else if (str.equalsIgnoreCase("dört")) {
                intermediateResult += 4;
            } else if (str.equalsIgnoreCase("beþ")) {
                intermediateResult += 5;
            } else if (str.equalsIgnoreCase("altý")) {
                intermediateResult += 6;
            } else if (str.equalsIgnoreCase("yedi")) {
                intermediateResult += 7;
            } else if (str.equalsIgnoreCase("sekiz")) {
                intermediateResult += 8;
            } else if (str.equalsIgnoreCase("dokuz")) {
                intermediateResult += 9;
            } else if (str.equalsIgnoreCase("on")) {
                intermediateResult += 10;
            } else if (str.equalsIgnoreCase("yirmi")) {
                intermediateResult += 20;
            } else if (str.equalsIgnoreCase("otuz")) {
                intermediateResult += 30;
            } else if (str.equalsIgnoreCase("kýrk")) {
                intermediateResult += 40;
            } else if (str.equalsIgnoreCase("elli")) {
                intermediateResult += 50;
            } else if (str.equalsIgnoreCase("altmýþ")) {
                intermediateResult += 60;
            } else if (str.equalsIgnoreCase("yetmiþ")) {
                intermediateResult += 70;
            } else if (str.equalsIgnoreCase("seksen")) {
                intermediateResult += 80;
            } else if (str.equalsIgnoreCase("doksan")) {
                intermediateResult += 90;
            } else if (str.equalsIgnoreCase("yüz")) {
                if(intermediateResult == 0)
                	intermediateResult = 1;
            	intermediateResult *= 100;
            } else if (str.equalsIgnoreCase("bin")) {
            	if(intermediateResult == 0)
                	intermediateResult = 1;
                intermediateResult *= 1000;
                finalResult += intermediateResult;
                intermediateResult = 0;
            } else if (str.equalsIgnoreCase("milyon")) {
            	if(intermediateResult == 0)
                	intermediateResult = 1;
                intermediateResult *= 1000000;
                finalResult += intermediateResult;
                intermediateResult = 0;
            } else if (str.equalsIgnoreCase("milyar")) {
            	if(intermediateResult == 0)
                	intermediateResult = 1;
                intermediateResult *= 1000000000;
                finalResult += intermediateResult;
                intermediateResult = 0;
            } else if (str.equalsIgnoreCase("trilyon")) {
            	if(intermediateResult == 0)
                	intermediateResult = 1;
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
