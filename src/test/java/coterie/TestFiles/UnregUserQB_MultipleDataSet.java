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

public class UnregUserQB_MultipleDataSet {

	WebDriver driver;
	LandingPageChrome landingPage;
	InformationPageChrome infoPage;
	ShippingPageChrome shippingPage;
	PaymentPageChrome paymentPage;
	OrderConfirmationPageChrome ocPage;

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

		spark = new ExtentSparkReporter("target/extentReport/AutomationReports_UnregUserQB_MultipleDataSet.html");
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
	public void unRegUserQB(String loadingURL, String sizeDiaper, String noOfDiapers, String sizePant, String noOfPants,
			String sizeWipe, String noOfWipes, String infopage_Email, String infopage_FirstName,
			String infopage_LastName, String infopage_Address, String infopage_City, String infopage_Province,
			String infopage_ZipCode, String infopage_PhoneNumber, String ccNum, String ccName, String ccExpDate,
			String ccSecCode) throws Exception {
		ExtentTest test = extent.createTest("Landing/Home Page title validation.").assignAuthor("Parthasarathi");
		try {
			driver.get(loadingURL);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			Thread.sleep(2000);
			test.info("Validating the Quick buy button click in Landing/Home Page.");
			landingPage = new LandingPageChrome(driver);
			landingPage.btn_QuickBuy_Click();
			test.pass("Quick buy button click is validated sucessfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);

			test.info("Validating the Adding Diaper to cart through QuickBuy from Landing/Home Page.");
			Thread.sleep(2000);
			landingPage.add_Diaper(sizeDiaper);
			landingPage.price_DiaperVal();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			landingPage.noOf_Diapers(noOfDiapers);
			landingPage.price_DiaperVal();
			test.pass("Diaper is added to the cart sucessfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);

			test.info("Validating the Adding Pant to cart through QuickBuy from Landing/Home Page.");
			Thread.sleep(2000);
			landingPage.add_Pant(sizePant);
			landingPage.price_PantVal();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			landingPage.noOf_Pants(noOfPants);
			landingPage.price_PantVal();
			test.pass("Pant is added to the cart sucessfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);

			test.info("Validating the Adding Wipe to cart through QuickBuy from Landing/Home Page.");
			Thread.sleep(2000);
			landingPage.add_Wipe(sizeWipe);
			landingPage.price_wipesVal();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			landingPage.noOf_Wipes(noOfWipes);
			landingPage.price_wipesVal();
			test.pass("Wipe Pack is added to the cart sucessfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			
			infoPage = landingPage.landingPage_CheckOut();
			test.pass("Checkout button click is validated sucessfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			
			test.info("Validate whether the user is able to enter the contact details in Information Page.");
			infoPage = new InformationPageChrome(driver);
			infoPage.input_Email(infopage_Email);
			infoPage.input_FirstName(infopage_FirstName);
			infoPage.input_LastName(infopage_LastName);
			infoPage.input_Address(infopage_Address);
			infoPage.input_City(infopage_City);
			infoPage.Select_Province(infopage_Province);
			infoPage.input_Zip(infopage_ZipCode);
			infoPage.input_PhoneNum(infopage_PhoneNumber);
			test.pass("User entered the contact information successfully in Information Page.");
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
			Thread.sleep(2000);
			paymentPage.subCheckBox();
			Thread.sleep(2000);
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
			Assert.fail("Unexpected error happened: " + e.getMessage());
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
				"testCoterie@gmail.com", "uvou jrnj ezyo rcyt", "target/extentReport/AutomationReports_UnregUserQB_MultipleDataSet.html");
		
		driver.quit();
	}

}
