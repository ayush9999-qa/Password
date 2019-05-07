package Agoda.SimilarPassword;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Tests for Password check.
 */
public class PasswordTest {
	Password passwordCheck = new Password();
	
    @DataProvider(name = "newPasswords")
    public String[] passwordSet() {
    	return new String[] {
    			"qwertyuiopasdfghjklA!1",
    			"qwertyuiopasdfghjklA!2",
    			"qwertyuiopasdfghjkl",
    			"12345678901234567890",
    			"!@#$%^&*()!@#$%^&*()",
    			"Qwertyuiop1234567890!@#",
    			"QWERTYUIOPASDFGHJKL",
    			"qwertyuiop!@#!@#123Q",
    			"QwErTyUiOp1@3$&8",
    			"Qwertyuiopasdfgh@1234",
    			"qwertyuiopASDF$%^&*4567",
    			"Qwertyuiopasdfgh@121"};
    }
    
    // Test set for new password is provided with data provider
    @Test(dataProvider = "newPasswords")
    public void testPassword(String newPassword) {
    	System.out.println("New Password - " + newPassword);
    	System.out.println("Old Password - " + getOldPassword());
    	Assert.assertTrue(passwordCheck.changePassword(getOldPassword(), newPassword));    	
    }
    
    // Mock function to return old password
    public String getOldPassword() {
    	return "Qwertyuiopasdfgh@123";
    }
}
