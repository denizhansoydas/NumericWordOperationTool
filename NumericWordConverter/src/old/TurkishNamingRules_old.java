package old;

import java.util.Map;
import java.util.TreeMap;

public class TurkishNamingRules_old extends LanguageNamingRules_old{

	TurkishNamingRules_old() {
		super();
		digits[0] = "sýfýr";
		digits[1] = "bir";
		digits[2] = "iki";
		digits[3] = "üç";
		digits[4] = "dört";
		digits[5] = "beþ";
		digits[6] = "altý";
		digits[7] = "yedi";
		digits[8] = "sekiz";
		digits[9] = "dokuz";
		
		multiplies_of_ten[0] = "sýfýr";
		multiplies_of_ten[1] = "on";
		multiplies_of_ten[2] = "yirmi";
		multiplies_of_ten[3] = "otuz";
		multiplies_of_ten[4] = "kýrk";
		multiplies_of_ten[5] = "elli";
		multiplies_of_ten[6] = "altmýþ";
		multiplies_of_ten[7] = "yetmiþ";
		multiplies_of_ten[8] = "seksen";
		multiplies_of_ten[9] = "doksan";
		
		powers_of_ten[1] = "on";
		powers_of_ten[2] = "yüz";
		powers_of_ten[3] = "bin";
		//powers_of_ten[4] = "on bin";
		//powers_of_ten[5] = "yüz bin";
		powers_of_ten[6] = "milyon";
		//powers_of_ten[7] = "on milyon";
		//powers_of_ten[8] = "yüz milyon";
		powers_of_ten[9] = "milyar";
	}

}
