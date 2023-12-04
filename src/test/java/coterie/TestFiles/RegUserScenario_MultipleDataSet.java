package coterie.TestFiles;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import coterie.Utils.ExtentReportListener;
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

public class RegUserScenario_MultipleDataSet {

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

		spark = new ExtentSparkReporter("target/extentReport/AutomationReports_RegUserScenario_MultipleDataSet.html");
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
	public void regUserScenario(String loadingURL, String login_UserName, String login_Password, String diaperSize,
			String ccNum, String ccName, String ccExpDate, String ccSecCode) throws Exception {
		ExtentTest test = extent.createTest("Landing/Home Page title validation.").assignAuthor("Parthasarathi");
		try {
			driver.get(loadingURL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

			landingPage = new LandingPageChrome(driver);
			landingPage.hamBurger_Home();
			test.pass("Hamburger icon is clicked successfully.");
			loginPage = landingPage.userIcon();
			test.pass("User icon is clicked successfully.");
			Thread.sleep(2000);
			loginPage = new LoginPageChrome(driver);
			loginPage.userName(login_UserName);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			loginPage.nextBtn();
			test.pass("Username is entered successfuly and Next button is clicked");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			test.info("Validating the password field is enabled and user is able to enter password");
			loginPage.passWord(login_Password);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			userDBPage = loginPage.submitBtn();
			test.pass("password is entered successfuly and Next button is clicked");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			userDBPage = new UserDashboardPageChrome(driver);
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			landingPage = userDBPage.HomePagenav();
			test.pass("Page is navigated to Landing/Home Page successfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			landingPage = new LandingPageChrome(driver);
			landingPage.hamBurger_Home();
			test.pass("Hamburger icon is clicked successfully.");
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			diaperPage = landingPage.nav_DiaperOpt();
			Thread.sleep(2000);
			diaperPage = new DiaperPageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			diaperPage.diaperSizeSelect();
			diaperPage.noOfDiapers(diaperSize);
			diaperPage.orderPriceType();
			diaperPage.diaper_Submit();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			diaperPage.first_ProductDetails();
			infoPage = diaperPage.confirmCheckout();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			test.pass("Art Print is added to the cart sucessfully and products details from cart is validated.");
			Thread.sleep(2000);
			test.info("Validate whether the user is able to get the product details in Information page.");
			infoPage = new InformationPageChrome(driver);
			Thread.sleep(2000);
			infoPage.first_prod_detailsUsrScenario();
			test.pass("User got the product details successfully in Information Page.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			test.info("Validate whether the user is able to validate the product details in Information page.");
			infoPage.continueInfo();
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			shippingPage = infoPage.proceedBtn();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			test.pass("User is able to submit the Contact information successfully in Information Page.");
			Thread.sleep(2000);
			test.info("Validate whether the user is able to click the Continue Payment button in Shippment Page.");
			shippingPage = new ShippingPageChrome(driver);
			Thread.sleep(2000);
			paymentPage = shippingPage.shippingPageContinue();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			test.pass("User is able click the Continue Payment button in Shippment Page");
			Thread.sleep(2000);
			test.info("Validate whether the user is able to enter the Credit Card Information in Payment Page.");
			paymentPage = new PaymentPageChrome(driver);
			Thread.sleep(2000);
			paymentPage.ccNUm(ccNum);
			paymentPage.ccName(ccName);
			paymentPage.cardExpiry(ccExpDate);
			paymentPage.cardSec(ccSecCode);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			test.pass("User entered the Credit Card information successfully in Payment Page.");
			Thread.sleep(2000);
			test.info("Validate whether the user is able to click the Pay Now button in Payment Page.");
			paymentPage = new PaymentPageChrome(driver);
			ocPage = paymentPage.payNow();
			test.pass("User is able click the Pay Now button in Payment Page");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			test.info("Validate whether the user is able to get the Order ID in Order Confirmation Page.");
			ocPage = new OrderConfirmationPageChrome(driver);
			landingPage = ocPage.orderConf();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			test.pass("User is able to get the Order ID successfully in Order Confirmation Page.");

		} catch (Exception e) {
			Assert.fail("Unexpected error while clicking the Hamburger icon in Home/Landing Page: " + e.getMessage());
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
		ExtentReportListener eRL = new ExtentReportListener();
		ExtentReportListener.sendEmail("parthasarathi.a@globaldigitalnext.com", "testCoterie@gmail.com", "smtp.gmail.com", "587",
				"testCoterie@gmail.com", "uvou jrnj ezyo rcyt", "target/extentReport/AutomationReports_RegUserScenario_MultipleDataSet.html");
		
		driver.quit();
	}

}
