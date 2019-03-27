package com.timebank.Page;

import com.timebank.Utility.DriverHelper;
import com.timebank.Utility.LocatorReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TC01_NonSignedInPagesHelper extends DriverHelper {

	private LocatorReader nonSignedInPageLocator;

	public TC01_NonSignedInPagesHelper(WebDriver driver) {

		super(driver);

		nonSignedInPageLocator = new LocatorReader("AllLocator.xml");
	}

	// verify login page
	public String verifyLoginPage() {
		String locator = nonSignedInPageLocator.getLocator("Login.VerifyLogIn");
		String userName = getText(locator);
		Assert.assertTrue(userName.contains("Test!"));
		return userName;
	}

	// verify login page
	public void verifyLogoutPage() {
		String locatorLogInBtn = nonSignedInPageLocator
				.getLocator("Header.LoginButton");
		String verifyLogInBtnOnLogOut = getText(locatorLogInBtn);
		Assert.assertTrue(verifyLogInBtnOnLogOut.contains("LOGIN"));
	}

	// enter user name
	public void enterUserName(String user) {
		String locator = nonSignedInPageLocator.getLocator("Login.UserName");
		sendKeys(locator, user);
	}

	// enter password
	public void enterPossword(String password) {
		String locator = nonSignedInPageLocator.getLocator("Login.Password");
		sendKeys(locator, password);
	}

	// click on sign in
	public void clickOnSignIn() {
		String locator = nonSignedInPageLocator.getLocator("Login.SignIn");
		clickOn(locator);
		// getWebDriver().manage().timeouts() .pageLoadTimeout(60,
		// TimeUnit.SECONDS);

	}

	public void clickOnSignout() {

		// Click on Image
		String locator = nonSignedInPageLocator.getLocator("Login.isclicked");
		clickOn(locator);

		// Click on Log out
		String locatorlogout = nonSignedInPageLocator
				.getLocator("Login.BtnLogOut");
		clickOn(locatorlogout);

		// getWebDriver().manage().timeouts() .pageLoadTimeout(60,
		// TimeUnit.SECONDS);
	}

	// verify that validation message
	public String verifyLoginValidationWebAPI() {
		String locator = nonSignedInPageLocator
				.getLocator("Login.ValidationMessage");
		String text = getText(locator);
		return text;
	}

	// verify that validation message
	// verify that validation message
	public String verifyLoginValidation() {
		String locator = nonSignedInPageLocator
				.getLocator("Login.ValidationMessage");
		String text = getText(locator);
		return text;
	}

	// Login
	public void LogIn(String User, String Pass) {
		enterUserName(User);

		// Enter Password
		enterPossword(Pass);

		// Click on Login button
		clickOnSignIn();

	}

	// Click on Login button
	public void clickOnLoginButton() {

		String locator = nonSignedInPageLocator
				.getLocator("Header.LoginButton");
		clickOn(locator);
	}

	// Click on HowIt Works at Home Page
	public void clickOnHowItWorks() {
		String locator = nonSignedInPageLocator
				.getLocator("Allpagesload.HowItWorks");
		clickOn(locator);
	}

	// Verifiy Text After How It Works Clicks
	public String verifyHowItWorksClicks() {
		String locator = nonSignedInPageLocator
				.getLocator("Allpagesload.Howitworks5simplesteps");
		String verifyHowItWorksTextAfterClick = getText(locator);
		Assert.assertTrue(verifyHowItWorksTextAfterClick
				.contains("How it works: 5 simple steps"));
		return verifyHowItWorksTextAfterClick;
	}

	// Click on Who Can Join at Home Page
	public void clickOnwhoCanJoinButton() {
		String locator = nonSignedInPageLocator
				.getLocator("Allpagesload.WhoCanJoin");
		clickOn(locator);
	}

	// Verifiy Text After Who Can Join button Clicks
	public String verifyWhoCanJoinClicks() {
		String locator = nonSignedInPageLocator
				.getLocator("Allpagesload.WhoCanJoinAfterClick");
		String verifyHowItWorksTextAfterClick = getText(locator);
		Assert.assertTrue(verifyHowItWorksTextAfterClick
				.contains("Who Can Join"));
		return verifyHowItWorksTextAfterClick;
		// Assert.assertTrue(verifyHowItWorksTextAfterClick
		// .contains("Who Can Join"));

	}

	// Click on SignUp button at Home Page
	public void clickOnSignUpButton() {
		String locator = nonSignedInPageLocator
				.getLocator("Allpagesload.SignUp");
		clickOn(locator);
	}

	// Verifiy Text After SignUp Button Clicks
	public String verifySignUpClicks() {
		String locator = nonSignedInPageLocator
				.getLocator("Allpagesload.SignUpAfterClick");
		String verifyHowItWorksTextAfterClick = getText(locator);
		Assert.assertTrue(verifyHowItWorksTextAfterClick.contains("SIGN UP"));
		return verifyHowItWorksTextAfterClick;
	}

	// Verify Video Button On Page
	public void VerifyWatchVideoButton() {
		WebElement element = getWebDriver()
				.findElement(
						By.xpath("//a[@title='Watch a video about how TimeBanking works']"));
		System.out.println("true-----------");
		((JavascriptExecutor) getWebDriver()).executeScript(
				"arguments[0].scrollIntoView();", element);
		element.click();
	}

	// Click on Video Play Icon
	public void ClickOnPlayVideo() {
		// String locator =
		// nonSignedInPageLocator.getLocator("Allpagesload.PlayVideo");
		// clickOn(locator);

		WebElement element = getWebDriver()
				.findElement(
						By.xpath("//div[@class='modal-header']/button[@class='close']"));
		System.out.println("" + element);
		element.click();
		// getWebDriver().switchTo().frame("cq-cf-frame");
		// getWebDriver().findElement(By.css("#extdd-9 > div.tblRow >
		// input.edititem").click();
	}

	// Verify User Gets Logged In
	public void verifyLUserLoggedIn() {
		String locator = nonSignedInPageLocator
				.getLocator("LogIn.ViewProfileButton");
		String VerifyViewProfile = getText(locator);
		Assert.assertTrue(VerifyViewProfile.contains("View profile"));
	}

}
