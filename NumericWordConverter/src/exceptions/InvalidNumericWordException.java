package exceptions;

public class InvalidNumericWordException extends Exception{
	String num; 
	public InvalidNumericWordException(String num){
		this.num = num;
		System.out.println("Thrown " + getClass().getName() + " for num: " + num);
	}
	@Override
	public String toString(){
		return getClass().getSimpleName() + " for num: " + num;
	}
}
