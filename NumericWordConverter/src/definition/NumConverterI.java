package definition;

import exceptions.InvalidNumericWordException;

public interface NumConverterI {
    public int convertToInt(String locale, String num) throws InvalidNumericWordException;
    public String convertToString(String locale, int num);
}
