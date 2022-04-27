package old;

import java.util.Map;
import java.util.TreeMap;

public class TurkishNamingRules_old extends LanguageNamingRules_old{

	TurkishNamingRules_old() {
		super();
		digits[0] = "s�f�r";
		digits[1] = "bir";
		digits[2] = "iki";
		digits[3] = "��";
		digits[4] = "d�rt";
		digits[5] = "be�";
		digits[6] = "alt�";
		digits[7] = "yedi";
		digits[8] = "sekiz";
		digits[9] = "dokuz";
		
		multiplies_of_ten[0] = "s�f�r";
		multiplies_of_ten[1] = "on";
		multiplies_of_ten[2] = "yirmi";
		multiplies_of_ten[3] = "otuz";
		multiplies_of_ten[4] = "k�rk";
		multiplies_of_ten[5] = "elli";
		multiplies_of_ten[6] = "altm��";
		multiplies_of_ten[7] = "yetmi�";
		multiplies_of_ten[8] = "seksen";
		multiplies_of_ten[9] = "doksan";
		
		powers_of_ten[1] = "on";
		powers_of_ten[2] = "y�z";
		powers_of_ten[3] = "bin";
		//powers_of_ten[4] = "on bin";
		//powers_of_ten[5] = "y�z bin";
		powers_of_ten[6] = "milyon";
		//powers_of_ten[7] = "on milyon";
		//powers_of_ten[8] = "y�z milyon";
		powers_of_ten[9] = "milyar";
	}

}
