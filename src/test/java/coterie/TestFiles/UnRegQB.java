package coterie.TestFiles;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v117.emulation.Emulation;
import org.testng.annotations.AfterTest;
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
import coterie.pageObjectModel.MerchPageChrome;
import coterie.pageObjectModel.NewBornGiftPageChrome;
import coterie.pageObjectModel.OrderConfirmationPageChrome;
import coterie.pageObjectModel.PantPageChrome;
import coterie.pageObjectModel.PaymentPageChrome;
import coterie.pageObjectModel.ShippingPageChrome;
import coterie.pageObjectModel.TotePageChrome;
import coterie.pageObjectModel.WipePageChrome;
import io.github.bonigarcia.wdm.WebDriverManager;

public class UnRegQB {

	WebDriver driver;
	LandingPageChrome landingPage;
	InformationPageChrome infoPage;
	ShippingPageChrome shippingPage;
	PaymentPageChrome paymentPage;
	OrderConfirmationPageChrome ocPage;

	ExtentReports extent;
	ExtentSparkReporter spark;

	@BeforeTest
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
		spark = new ExtentSparkReporter("target/extentReport/AutomationReports_UnRegQB.html");
		spark.config().setTheme(Theme.DARK);
		extent.attachReporter(spark);
	}

	@Test(priority = 1, dataProviderClass = Readxls.class, dataProvider = "Data")
	public void invokeApplication(String loadingURL) {
		// System.setProperty("webDriver.chrome.driver",
		// "C:\\Users\\Desktop\\Driver\\chromedriver.exe");
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
		driver.get(loadingURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@Test(priority = 2, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"invokeApplication" })
	public void titleValLandingPage(String landingPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Landing/Home Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Landing/Home Page title against the Expected Title.");
			landingPage = new LandingPageChrome(driver);
			Thread.sleep(2000);
			landingPage.titleVal(landingPageExpTitle, landingPage.getTitle(landingPageExpTitle));
			test.pass("Landing/Home Page Title is validated sucessfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while validating the Landing/Home page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 3, dependsOnMethods = { "titleValLandingPage" })
	public void quickBuyClick() throws Exception {
		ExtentTest test = extent.createTest("QuickBuy Button click validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Quick buy button click in Landing/Home Page.");
			landingPage = new LandingPageChrome(driver);
			landingPage.btn_QuickBuy_Click();
			test.pass("Quick buy button click is validated sucessfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while clicking the QuickBuy button Landing/Home page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 4, dependsOnMethods = {
			"quickBuyClick" }, dataProviderClass = Readxls.class, dataProvider = "Data")
	public void addDiaper(String sizeDiaper,String noOfDiapers) throws Exception {
		ExtentTest test = extent.createTest("Adding Diaper to cart through QuickBuy Validation")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Adding Diaper to cart through QuickBuy from Landing/Home Page.");
			landingPage = new LandingPageChrome(driver);
			landingPage.add_Diaper(sizeDiaper);
			landingPage.price_DiaperVal();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			landingPage.noOf_Diapers(noOfDiapers);
			landingPage.price_DiaperVal();
			test.pass("Diaper is added to the cart sucessfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while adding Diaper through QuickBuy: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 5, dependsOnMethods = { "addDiaper" }, dataProviderClass = Readxls.class, dataProvider = "Data")
	public void addPant(String sizePant) throws Exception {
		ExtentTest test = extent.createTest("Adding Pant to cart through QuickBuy Validation")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Adding Pant to cart through QuickBuy from Landing/Home Page.");
			landingPage = new LandingPageChrome(driver);
			landingPage.add_Pant(sizePant);
			landingPage.price_PantVal();
			test.pass("Pant is added to the cart sucessfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while adding Pant through QuickBuy: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 6, dependsOnMethods = { "addPant" }, dataProviderClass = Readxls.class, dataProvider = "Data")
	public void addWipe(String sizeWipe) throws Exception {
		ExtentTest test = extent.createTest("Adding Wipe to cart through QuickBuy Validation")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Adding Wipe to cart through QuickBuy from Landing/Home Page.");
			landingPage = new LandingPageChrome(driver);
			landingPage.add_Wipe(sizeWipe);
			landingPage.price_wipesVal();
			test.pass("Wipe Pack is added to the cart sucessfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while adding Wipe Pack through QuickBu: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 7, dependsOnMethods = { "addWipe" })
	public void costVal() throws Exception {
		ExtentTest test = extent.createTest("Selected Products Cost Validation").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Selected Products Costs.");
			landingPage = new LandingPageChrome(driver);
			landingPage.saved_Cost();
			landingPage.shipping_Cost();
			landingPage.taxes_Cost();
			landingPage.orderSubTotal_Cost();
			test.pass("Selected Products Cost is validated sucessfully.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while validating the Selected Products Cost: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 8, dependsOnMethods = { "costVal" })
	public void checkOut() throws Exception {
		ExtentTest test = extent.createTest("Checkout Button click validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Checkout button click in Cart.");
			landingPage = new LandingPageChrome(driver);
			infoPage = landingPage.landingPage_CheckOut();
			Thread.sleep(2000);
			test.pass("Checkout button click is validated sucessfully.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while clicking Checkout Button: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}

	}

	@Test(priority = 9, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = { "checkOut" })
	public void titleValInfoPage(String infoPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Information Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Information Page title against the Expected Title.");
			infoPage = new InformationPageChrome(driver);
			Thread.sleep(2000);
			infoPage.titleVal(infoPageExpTitle, infoPage.getTitle(infoPageExpTitle));
			test.pass("Information Page Title is validated Sucessfully");

		} catch (InterruptedException e) {
			Assert.fail("Unexpected error while validating the Information page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 10, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"titleValInfoPage" })
	public void addressData(String infopage_Email, String infopage_FirstName, String infopage_LastName,
			String infopage_Address, String infopage_City, String infopage_Provience, String infopage_ZipCode,
			String infopage_PhoneNumber) throws Exception {
		ExtentTest test = extent.createTest("Enter the Contact Information in Information Page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to enter the contact details in Information Page.");
			infoPage = new InformationPageChrome(driver);
			infoPage.input_Email(infopage_Email);
			infoPage.input_FirstName(infopage_FirstName);
			infoPage.input_LastName(infopage_LastName);
			infoPage.input_Address(infopage_Address);
			infoPage.input_City(infopage_City);
			infoPage.Select_Province(infopage_Provience);
			infoPage.input_Zip(infopage_ZipCode);
			infoPage.input_PhoneNum(infopage_PhoneNumber);
			test.pass("User entered the contact information successfully in Information Page.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while entering Contact Information in Information Page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 11, dependsOnMethods = { "addressData" })
	public void productDetails() throws Exception {
		ExtentTest test = extent.createTest("Get the product details from Information page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to get the product details in Information page.");
			infoPage = new InformationPageChrome(driver);
			infoPage.first_prod_detailsUsrScenario();
			infoPage.second_prod_detailsUsrScenario();
			infoPage.third_prod_detailsUsrScenario();
			test.pass("User got the product details successfully in Information Page.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while getting the product details from Information page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 12, dependsOnMethods = { "productDetails" })
	public void totalPriceVal() throws Exception {
		ExtentTest test = extent.createTest("Product details validation from Information page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to validate the product details in Information page.");
			infoPage = new InformationPageChrome(driver);
			infoPage.sum();
			infoPage.priceDisplay();
			test.pass("User validated the product details successfully in Information Page.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while validating the product details from Information page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 13, dependsOnMethods = { "totalPriceVal" })
	public void contactInfoSubmission() throws Exception {
		ExtentTest test = extent.createTest("Contact Information submission from Information page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to submit the Contact Information in Information page.");
			infoPage = new InformationPageChrome(driver);
			infoPage.continueInfo();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			shippingPage = infoPage.proceedBtn();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			test.pass("User is able to submit the Contact information successfully in Information Page.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while submitting the Contact Information from Information page: "
					+ e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 14, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"contactInfoSubmission" })
	public void titleValShippingPage(String shippingPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Shipping Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Shipping Page title against the Expected Title.");
			shippingPage = new ShippingPageChrome(driver);
			Thread.sleep(2000);
			shippingPage.titleVal(shippingPageExpTitle, shippingPage.getTitle(shippingPageExpTitle));
			test.pass("Shipping Page Title is validated Sucessfully");
		} catch (Exception e) {
			Assert.fail("Unexpected error while validating the Shipping page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 15, dependsOnMethods = { "titleValShippingPage" })
	public void continuePaymentPage() throws Exception {
		ExtentTest test = extent.createTest("Continue Payment button click in Shippment Page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to click the Continue Payment button in Shippment Page.");
			shippingPage = new ShippingPageChrome(driver);
			paymentPage = shippingPage.shippingPageContinue();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			test.pass("User is able click the Continue Payment button in Shippment Page");
		} catch (Exception e) {
			Assert.fail(
					"Unexpected error while clicking the Continue Payment button in Shippment Page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 16, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"continuePaymentPage" })
	public void titleValPaymentPage(String paymentPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Payment Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Payment Page title against the Expected Title.");
			paymentPage = new PaymentPageChrome(driver);
			Thread.sleep(2000);
			paymentPage.titleVal(paymentPageExpTitle, paymentPage.getTitle(paymentPageExpTitle));
			test.pass("Payment Page Title is validated Sucessfully");
		} catch (Exception e) {
			Assert.fail("Unexpected error while validating the Payment page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 17, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"titleValPaymentPage" })
	public void ccPayment(String ccNum, String ccName, String ccExpDate, String ccSecCode) throws Exception {
		ExtentTest test = extent.createTest("Enter the Credit Card Information in Payment Page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to enter the Credit Card Information in Payment Page.");
			paymentPage = new PaymentPageChrome(driver);
			paymentPage.ccNUm(ccNum);
			paymentPage.ccName(ccName);
			paymentPage.cardExpiry(ccExpDate);
			paymentPage.cardSec(ccSecCode);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			test.pass("User entered the Credit Card information successfully in Payment Page.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while entering Credit Card Information in Payment Page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 18, dependsOnMethods = { "ccPayment" })
	public void payNow() throws Exception {
		ExtentTest test = extent.createTest("Pay Now button click in Payment Page.").assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to click the Pay Now button in Payment Page.");
			paymentPage = new PaymentPageChrome(driver);
			paymentPage.subCheckBox();
			ocPage = paymentPage.payNow();
			test.pass(
					"User is able check the Subscription checkbox and able to click the Pay Now button in Payment Page");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while clicking the Pay Now button in Payment Page " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}

	}

	@Test(priority = 19, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = { "payNow" })
	public void titleValOCPage(String ocPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Order Confirmation Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Order Confirmation Page title against the Expected Title.");
			ocPage = new OrderConfirmationPageChrome(driver);
			Thread.sleep(2000);
			ocPage.titleVal(ocPageExpTitle, ocPage.getTitle(ocPageExpTitle));
			test.pass("Order Confirmation Page Title is validated Sucessfully");
		} catch (Exception e) {
			Assert.fail("Unexpected error while validating the Order Confirmation page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 20, dependsOnMethods = { "titleValOCPage" })
	public void orderSubmissionVal() throws Exception {
		ExtentTest test = extent.createTest("Get the Order ID in Order Confirmation Page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to get the Order ID in Order Confirmation Page.");
			ocPage = new OrderConfirmationPageChrome(driver);
			landingPage = ocPage.orderConf();
			test.pass("User is able to get the Order ID successfully in Order Confirmation Page.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while getting Order ID in Order Confirmation Page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 21, dependsOnMethods = { "orderSubmissionVal" })
	public void AssertAll() throws Exception {
		ExtentTest test = extent.createTest("Asserting all the validations").assignAuthor("Parthasarathi");
		try {
			test.info("All validations assert");
			LandingPageChrome landingPage = new LandingPageChrome(driver);
			infoPage = landingPage.pageAssertAllInfo();

			InformationPageChrome infoPage = new InformationPageChrome(driver);
			shippingPage = infoPage.pageAssertAll();

			ShippingPageChrome shippingPage = new ShippingPageChrome(driver);
			paymentPage = shippingPage.pageAssertAll();

			PaymentPageChrome paymentPage = new PaymentPageChrome(driver);
			ocPage = paymentPage.pageAssertAll();

			OrderConfirmationPageChrome ocPage = new OrderConfirmationPageChrome(driver);
			ocPage.pageAssertAll();
			test.pass("All asserts are passed");
		} catch (Exception e) {
			Assert.fail("Unexpected error while assert the validations: " + e.getMessage());
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

	@AfterTest
	public void teardown() {

		extent.flush();
		ExtentReportListener eRL = new ExtentReportListener();
		ExtentReportListener.sendEmail("parthasarathi.a@globaldigitalnext.com", "testCoterie@gmail.com", "smtp.gmail.com", "587",
				"testCoterie@gmail.com", "uvou jrnj ezyo rcyt", "target/extentReport/AutomationReports_UnRegQB.html");
		
		driver.quit();
	}
}
