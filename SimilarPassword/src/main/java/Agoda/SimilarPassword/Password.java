package Agoda.SimilarPassword;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This is the main class which holds the functionality of changing password 
 * By - Ayush Goel
 */
public class Password 
{
	/**
	 * Function to change password after check
	 * @param oldPassword
	 * @param newPassword
	 * @return true/false if password can/cannot be changed
	 */
	public boolean changePassword(String oldPassword, String newPassword) {
		return validateNewPassword(newPassword) && comparePasswords(oldPassword, newPassword);
	}
	
	/**
	 * Function to validate new password
	 * @param newPassword
	 * @return true if password is validated against the requirements
	 */
    private boolean validateNewPassword(String newPassword) {
    	
    	// Check if length of Password is greater than 18
    	if (newPassword.length() < 18) {
    		System.out.println("Minimum length of password should be 18");
    		return false;
    	}    		
    	
    	// Check if password is alphanumeric and only !@#$&* characters are allowed
    	if (!Pattern.matches("^[a-zA-Z0-9!@#$&*]*$", newPassword)) {
    		System.out.println("Unwanted characters should not be available");
    		return false;
    	}    		
    	
    	// check if password contains atleast 1 uppercase, 1 lowercase, 1 digit and 1 special character
    	if (!checkPasswordChars(newPassword)) {
    		System.out.println("Password should contain 1 uppercase, 1 lowercase, 1 digit and 1 special character");
    		return false;
    	}    		
    	
    	// Check max character occurrence is not more than 4
    	if (getMaxCharOccurrence(newPassword) > 4) {
    		System.out.println("No character should have more than 4 occurences");
    		return false;
    	}    		
    	
    	// Check if not more than 4 special characters are present
    	if (getSpecialCharCount(newPassword) > 4) {
    		System.out.println("Special characters should not be more than 4");
    		return false;
    	}    		    	
    	
    	// Check max number count <= 50% of string
    	if (getDigitCount(newPassword) > (newPassword.length()/2)) {
    		System.out.println("Maximum digit could less than 50% of total length");
    		return false;
    	}
    	
    	return true;
    }
    
    /**
     * Function to count number of special characters
     * @param password
     * @return count of special characters
     */
    private int getSpecialCharCount(String password) {
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
    
    /**
     * Function to calculate maximum length of set of characters in the given string
     * @param password
     * @return max count of occurrences of any character
     */
    private int getMaxCharOccurrence(String password) {
    	
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
    
    /**
     * Function to count number of digits in the string
     * @param password
     * @return count of digits
     */
    private int getDigitCount(String password) {
    	int numberCount = 0;
    	char[] chars = password.toCharArray();
    	for (char ch : chars) {
    		if (Character.isDigit(ch))
    			numberCount++;
    	}
    	return numberCount;
    }
    
    /**
     * Function to check if string contains at least 1 upper, 1 lower case character, 1 special character and 1 digit 
     * @param password
     * @return true if condition is adhered
     */
    private boolean checkPasswordChars(String password) {
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

    /**
     * Function to compare similarity between 2 passwords
     * @param oldPassword
     * @param newPassword
     * @return true if similarity is less than 80%
     */
    private boolean comparePasswords(String oldPassword, String newPassword) {
    	if (oldPassword.contains(newPassword) || newPassword.contains(oldPassword)) {
    		System.out.println("New password should not be part of old password and vice versa");
    		return false;
    	}    		
    	
    	int oldPasswordLength = oldPassword.length();
    	int newPasswordLength = newPassword.length();
    	int similarityIndex = 0;
    	
    	if (oldPasswordLength >= newPasswordLength)
    		similarityIndex = compareStrings(oldPassword, newPassword);
    	else
    		similarityIndex = compareStrings(newPassword, oldPassword);
    	
    	int max = Integer.max(newPasswordLength, oldPasswordLength);
    	double percent = (double)similarityIndex / max;
    	System.out.println("Percent similarity: " + percent*100 + " %");
    	if (((percent) * 100) > 80)
    		return false;
    	else
    		return true;    	
    }

    /**
     * Function to compare maximum similarity between 2 strings
     * @param baseString
     * @param newString
     * @return maximum similarity count
     */
	private int compareStrings(String baseString, String newString) {
		
		// Similarity index is the measure of similarity
    	int similarityIndex = 0;
    	
    	// To match characters, converting both strings to character arrays
    	char[] baseStringChars = baseString.toCharArray();
    	char[] newStringChars = newString.toCharArray();
    	
    	List<Integer> similarityIndexes = new ArrayList<Integer>();
    	int increased = 0;
    	for (int j = 0; j < newStringChars.length; j = ((increased == 0)?(j + 1) : j)) {
    		increased = 0;
    		similarityIndex = 0;
    		for (int i = 0; i < baseStringChars.length; i++) {
    			if (j < newStringChars.length && baseStringChars[i] == newStringChars[j]) {
        			j++;
        			increased = 1;
        			similarityIndex++;
        		}
    		}
    		similarityIndexes.add(similarityIndex);
    	}
    	List<Integer> sortedList = new ArrayList<Integer>(similarityIndexes);
    	Collections.sort(sortedList);
		return sortedList.get(sortedList.size() - 1);
	}
}
