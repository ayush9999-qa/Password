package Agoda.SimilarPassword;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class SimilarPassword 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        String test = "Tesqwertttttyuiopsdfgcvbn@1234"; 
//        if (validatePassword(test))
//        	System.out.println("Password correctly validated");
//        else
//        	System.out.println("Password incorrectly validated");
        
        String newPassword = "abcdefghijklm";
        String oldPassword = "abcdefghijkln";
        if (comparePasswords(oldPassword, newPassword))
        	System.out.println("Accepted password");
        else 
        	System.out.println("Rejected password");
    }
    
    public static boolean validatePassword(String newPassword) {
    	
    	// Check if length of Password is greater than 18
    	if (newPassword.length() < 18)
    		return false;
    	
    	// Check if password is alphanumeric and only !@#$&* characters are allowed
    	if (!Pattern.matches("^[a-zA-Z0-9!@#$&*]*$", newPassword))
    		return false;
    	
    	// check if password contains atleast 1 uppercase, 1 lowercase, 1 digit and 1 special character
    	if (!checkPasswordChars(newPassword))
    		return false;
    	
    	// Check if not more than 4 special characters are present
    	if (getSpecialCharCount(newPassword) > 4)
    		return false;
    	
    	// Check max character occurrence is not more than 4
    	if (getMaxCharOccurrence(newPassword) > 4)
    		return false;
    	
    	// Check max number count <= 50% of string
    	if (getDigitCount(newPassword) > (newPassword.length()/2))
    		return false;    	
    	
    	return true;
    }
    
    private static int getSpecialCharCount(String password) {
    	char[] acceptableSpChars = {'!', '@', '#', '$', '&', '*'};
    	int occurance = 0;
    	for (int i = 0; i < acceptableSpChars.length ; i++) {
    		for (int j = 0; j < password.length(); j++) {
        		if (password.charAt(j) == acceptableSpChars[i])
        			occurance++;
        	}
    	}    	
    	return occurance;
    }
    
    private static int getMaxCharOccurrence(String password) {
    	
    	char[] chars = password.toCharArray();
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for(char c : chars)
        {
            if(map.containsKey(c)) {
                int counter = map.get(c) + 1;
                map.put(c, counter);
            } else {
                map.put(c, 1);
            }
        }
         
        // Find max character length
        int maxOccurance = 0;
        for(char c : map.keySet()) {
            if(map.get(c) > maxOccurance) {
                maxOccurance = map.get(c);
            }
        }
    	return maxOccurance;
    }
    
    private static int getDigitCount(String password) {
    	int numberCount = 0;
    	char[] chars = password.toCharArray();
    	for (char ch : chars) {
    		if (Character.isDigit(ch))
    			numberCount++;
    	}
    	return numberCount;
    }
    
    private static boolean checkPasswordChars(String password) {
    	String acceptableSpChars = "!@#$&*";
    	boolean upperCharPresent = false;
    	boolean lowerCharPresent = false;
    	boolean numberPresent = false;
    	boolean specialCharPresent = false;
    	
    	char[] chars = password.toCharArray();
    	for (char ch : chars) {
    		if (Character.isDigit(ch))
    			numberPresent = true;
    		else if (Character.isUpperCase(ch))
    			upperCharPresent = true;
    		else if (Character.isLowerCase(ch))
    			lowerCharPresent = true;
    		else if (acceptableSpChars.contains(String.valueOf(ch)))
    			specialCharPresent = true;
    	}
    	return (upperCharPresent && lowerCharPresent && numberPresent && specialCharPresent);
    }

    private static boolean comparePasswords(String oldPassword, String newPassword) {
    	if (oldPassword.contains(newPassword) || newPassword.contains(oldPassword))
    		return false;
    	
    	int oldPasswordLength = oldPassword.length();
    	int newPasswordLength = newPassword.length();
    	int similarityIndex = 0;
    	
    	if (oldPasswordLength >= newPasswordLength)
    		similarityIndex = comparePasswordsNO(oldPassword, newPassword);
    	else
    		similarityIndex = comparePasswordsNO(newPassword, oldPassword);
    	
    	int max = Integer.max(newPasswordLength, oldPasswordLength);
    	
    	if ((((double)similarityIndex / max) * 100) > 80)
    		return false;
    	else
    		return true;    	
    }

	private static int comparePasswordsNO(String oldPassword, String newPassword) {
		int oldPasswordLength = oldPassword.length();
    	int newPasswordLength = newPassword.length();
    	int similarityIndex = 0;
    	
    	char[] oldPasswordChars = oldPassword.toCharArray();
    	char[] newPasswordChars = newPassword.toCharArray();
    	
//    	for (int j = 0, i = 0; j < newPasswordLength ; i++) {
//    		for (int i = 0; i < oldPasswordLength; i++) {
//    			if (oldPasswordChars[i] == newPasswordChars[j]) {
//    				j++;
//    				similarityIndex++;
//    			}
//    		}
//    	}
    	
    	int j = 0;
    	for (int i = 0; i < oldPasswordChars.length ; i++) {    		
    		if (oldPasswordChars[i] == newPasswordChars[j] && j < newPasswordChars.length) {
    			j++;
    			similarityIndex++;
    		}
    	}
    	
		return similarityIndex;
	}

	private static int comparePasswordsON(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return 0;
	}
}
