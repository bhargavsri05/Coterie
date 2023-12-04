package coterie.TestFiles;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import coterie.Utils.Readxls;
import coterie.pageObjectModel.ArtPrintPageChrome;
import coterie.pageObjectModel.ClassicCapPageChrome;
import coterie.pageObjectModel.CrewNeckPageChrome;
import coterie.pageObjectModel.DiaperBagPageChrome;
import coterie.pageObjectModel.DiaperPageChrome;
import coterie.pageObjectModel.InformationPageChrome;
import coterie.pageObjectModel.LandingPageChrome;
import coterie.pageObjectModel.LoginPageChrome;
import coterie.pageObjectModel.MerchPageChrome;
import coterie.pageObjectModel.NewBornGiftPageChrome;
import coterie.pageObjectModel.OrderConfirmationPageChrome;
import coterie.pageObjectModel.PantPageChrome;
import coterie.pageObjectModel.PaymentPageChrome;
import coterie.pageObjectModel.ShippingPageChrome;
import coterie.pageObjectModel.TotePageChrome;
import coterie.pageObjectModel.UserDashboardPageChrome;
import coterie.pageObjectModel.WipePageChrome;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v117.emulation.Emulation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login_Test {

	WebDriver driver;
	LandingPageChrome landingPage;
	DiaperPageChrome diaperPage;
	PantPageChrome pantPage;
	WipePageChrome wipePage;
	NewBornGiftPageChrome nbGPage;
	DiaperBagPageChrome dbPage;
	MerchPageChrome merchPage;
	ClassicCapPageChrome ccPage;
	CrewNeckPageChrome cnPage;
	TotePageChrome totePage;
	ArtPrintPageChrome apPage;
	InformationPageChrome infoPage;
	ShippingPageChrome shippingPage;
	PaymentPageChrome paymentPage;
	OrderConfirmationPageChrome ocPage;
	LoginPageChrome loginPage;
	UserDashboardPageChrome userDBPage;

	ExtentReports extent;
	ExtentSparkReporter spark;

	@BeforeMethod
	public void extentSetup() throws InterruptedException {
		Path path = Paths.get("target/extentReport");
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		extent = new ExtentReports();

		spark = new ExtentSparkReporter("target/extentReport/AutomationReports.html");
		spark.config().setTheme(Theme.DARK);
		extent.attachReporter(spark);

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		DevTools devtools = ((HasDevTools) driver).getDevTools();
		devtools.createSession();

		// ***********send commands to the CDP Methods -> CDP methods will get invoke
		// and access to *******
		// *********** chrome dev tools ***********
		devtools.send(Emulation.setDeviceMetricsOverride(390, 844, 50, true, Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty()));

	}

	@Test(priority = 1, dataProviderClass = Readxls.class, dataProvider = "Data")
	public void invokeApplication1(String loadingURL, String login_UserName, String login_Password,
			String userDBPageTitle) throws Exception {

		driver.get(loadingURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		ExtentTest test = extent.createTest("Landing/Home Page title validation.").assignAuthor("Parthasarathi");

		try {
			test.info("Validating the Humburger icon in Landing/Home page by clicking.");
			landingPage = new LandingPageChrome(driver);
			landingPage.hamBurger_Home();
			test.pass("Hamburger icon is clicked successfully.");
		} catch (Exception e) {
			test.fail("Unexpected error while clicking the Hamburger icon in Home/Landing Page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}

		try {
			test.info("Validating the User icon in Landing/Home page by clicking.");
			landingPage = new LandingPageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			loginPage = landingPage.userIcon();
			test.pass("User icon is clicked successfully.");
		} catch (Exception e) {
			test.fail("Unexpected error while clicking the User icon in Home/Landing Page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}

		try {
			test.info("Validating the username field is enabled and user is able to enter username");
			loginPage = new LoginPageChrome(driver);
			loginPage.userName(login_UserName);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			loginPage.nextBtn();
			test.pass("Username is entered successfuly and Next button is clicked");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			test.fail("Unexpected error while entering username and clicking on Next button in Login Page: "
					+ e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}

		try {
			test.info("Validating the password field is enabled and user is able to enter password");
			loginPage = new LoginPageChrome(driver);
			loginPage.passWord(login_Password);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			userDBPage = loginPage.submitBtn();
			test.pass("password is entered successfuly and Next button is clicked");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			test.fail("Unexpected error while entering password and clicking on Next button in Login Page: "
					+ e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}

		try {
			test.info("Validating the User Dashboard Page title against the Expected Title.");
			userDBPage = new UserDashboardPageChrome(driver);
			Thread.sleep(5000);
			userDBPage.titleVal(userDBPageTitle, loginPage.getTitle(userDBPageTitle));
			test.pass("User Dashboard Page Title is validated sucessfully.");
		} catch (InterruptedException e) {
			test.fail("Unexpected error while validating the User Dashboard page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	private String takeScreenshot(WebDriver driver) throws IOException {

		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destination = new File("src/../Screens/screenshots" + System.currentTimeMillis() + ".png");
		String absolutePath = destination.getAbsolutePath();
		FileUtils.copyFile(screenShot, destination);
		return absolutePath;
	}

	@AfterMethod
	public void teardown() {

		extent.flush();
		driver.quit();
	}

}
