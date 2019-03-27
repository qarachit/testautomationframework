package com.timebank.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.timebank.Utility.DriverTestCase;



public class TC01_NonSignedIn extends DriverTestCase {

	@Test
	public void UserLogIn() throws InterruptedException, IOException {

		try {

			// Click on Login button
			nonSignedInHelper.clickOnLoginButton();

			// Get username and password from property file
			String username = propertyReader.readApplicationFile("TimeBankUser");
			String password = propertyReader.readApplicationFile("TimeBankPassword");

			// User is able to Login
			nonSignedInHelper.LogIn(username, password);


		} catch (Error e) {
			captureScreenshot("UserLogIn");
			// verificationErrors = e.getMessage();
			throw e;
		} /*
			 * catch (Exception e) {
			 * captureScreenshot("testCreateNewProject_exception");
			 * ExecutionLog.LogExceptionMessage(e); throw e; }
			 */
	}

	

}
