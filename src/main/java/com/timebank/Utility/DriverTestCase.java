package com.timebank.Utility;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import com.timebank.Page.*;

public abstract class DriverTestCase {

	public PropertyReader propertyReader;
	public TC01_NonSignedInPagesHelper nonSignedInHelper;
	private WebDriver driver;

	enum DriverType {
		Firefox, IE, Chrome, Headless
	}

	@BeforeSuite
	public void setUp() {

		propertyReader = new PropertyReader();

		String driverType = propertyReader.readApplicationFile("BROWSER");
		System.out.println("the browser type is:" + driverType);

		if (DriverType.Firefox.toString().equals(driverType)) {
			driver = new FirefoxDriver();

		} else if (DriverType.IE.toString().equals(driverType)) {

			String strDriverPath = System.getProperty("user.dir")
					+ "\\webdriverexe\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", strDriverPath);
			driver = new InternetExplorerDriver();

		} else if (DriverType.Chrome.toString().equals(driverType)) {

			String strDriverPath = System.getProperty("user.dir")
					+ "\\webdriverexe\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", strDriverPath);

			driver = new ChromeDriver();
		} else if (DriverType.Headless.toString().equals(driverType)) {

			//driver = new HtmlUnitDriver(true);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			driver = new ChromeDriver(options);
		}

		else {
			driver = new FirefoxDriver();

		}

		// Launch the URL
		String url = propertyReader.readApplicationFile("WebURL");
		getWebDriver().navigate().to(url);

		// Maximize window
		driver.manage().window().maximize();

		// Delete cookies
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		nonSignedInHelper = new TC01_NonSignedInPagesHelper(driver);
	}

	@AfterSuite
	public void afterMainMethod() throws Exception {

		nonSignedInHelper = null;
		// logOutHelper =null;
		// driver.quit();
		/*
		 * 
		 * Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
		 * Thread.sleep(5000); Runtime.getRuntime().exec(
		 * "taskkill /F /IM plugin-container.exe"); Runtime.getRuntime().exec(
		 * "taskkill /F /IM WerFault.exe");
		 */
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public PropertyReader getProperty() {
		return propertyReader;
	}

	public String switchPreviewWindow() {
		Set<String> windows = getWebDriver().getWindowHandles();
		Iterator<String> iter = windows.iterator();
		String parent = iter.next();
		getWebDriver().switchTo().window(iter.next());
		return parent;
	}

	public void captureScreenshot(String fileName) {

		String date = getCurrentDate();
		// System.out.println("getapplication SetUp: "+date);

		String path = propertyReader.getPath();
		System.out.println("Path:------> " + path);
		File fl = new File(path + "//Screenshot" + "//Screenshot" + date);

		fl.mkdirs();

		System.out.println(fl);
		try {

			String screenshotName = fileName;

			FileOutputStream out = new FileOutputStream(
					"Screenshot//Screenshot" + date + "//" + screenshotName
							+ ".png");
			// Convert web driver object to TakeScreenshot

			TakesScreenshot scrShot = ((TakesScreenshot) driver);

			// Call getScreenshotAs method to create image file

			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			// Move image file to new destination

			File DestFile = new File(fl + "/" + screenshotName + ".png");

			// Copy file at destination

			FileUtils.copyFile(SrcFile, DestFile);

			out.close();
		} catch (Exception e) {

			System.out.println("Error in Creating Screenshot");

		}

	}

	public String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String str = simpleDateFormat.format(date);
		return str;
	}
}
