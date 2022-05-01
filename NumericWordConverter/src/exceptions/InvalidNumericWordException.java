package exceptions;

public class InvalidNumericWordException extends Exception{
	String locale;
	String num; 
	public InvalidNumericWordException(String locale, String num){
		this.num = num;
		this.locale = locale;
		System.out.println("Thrown " + getClass().getName() + " for num: " + num);
	}
	@Override
	public String toString(){ //will be improved to use json files in the following updates. Unfortunately, now it is hard-coded.
		if(locale.equals("tr"))
			return num + " için " + getClass().getSimpleName() + " hatasý alýndý.";
		else if(locale.equals("en")) //unnecessary condition because actually default value is for en. However, temporarily I use this to improve readability.
			return getClass().getSimpleName() + " for num: " + num;
		else
			return getClass().getSimpleName() + " for num: " + num;
	}
}
