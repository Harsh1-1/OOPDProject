package smart;

import java.net.URL;
import java.util.regex.Pattern;

interface UserForm {
	default boolean isValidEmail(String email){
		String pattern = "^.+@.+[.].+$";
		return Pattern.matches(pattern, email);
	}
	default boolean isValidContactNumber(String contact){
		String pattern = "^[0-9]+$";
		return Pattern.matches(pattern, contact);
	}
	default boolean isValidURL(String URL){
		try{
			URL url = new URL(URL);
			url.toURI();
			return true;
		}
		catch(Exception ex){
			System.out.println("Could not verify URL");
		}
		return false;
	}
}
