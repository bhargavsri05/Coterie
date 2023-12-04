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
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v117.emulation.Emulation;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class UnRegUserScenario {

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
		spark = new ExtentSparkReporter("target/extentReport/AutomationReports_UnRegUserScenario.html");
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
		} catch (Exception e) {
			Assert.fail("Unexpected error while validating the Landing/Home page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 3, dependsOnMethods = { "titleValLandingPage" })
	public void hbIconClick() throws Exception {
		ExtentTest test = extent.createTest("Hamburger icon click validation in Landing/Home Page")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Humburger icon in Landing/Home page by clicking.");
			landingPage = new LandingPageChrome(driver);
			landingPage.hamBurger_Home();
			test.pass("Hamburger icon is clicked successfully.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while clicking the Hamburger icon in Home/Landing Page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 4, dependsOnMethods = { "hbIconClick" })
	public void navmenu_Diaper_Select() throws Exception {
		ExtentTest test = extent.createTest("Selecting Diaper option from Navigation menu.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Diaper option navigates to Diaper page while clicking it from navigation menu.");
			landingPage = new LandingPageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			diaperPage = landingPage.nav_DiaperOpt();
			test.pass("Diaper page is loaded successfully after clicking the Diaper option in Navigation menu.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while navigating to Diaper page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 5, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"navmenu_Diaper_Select" })
	public void titleValDiaperPage(String diaperPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Diaper Page title validation").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Diaper Page title against the Expected Title.");
			diaperPage = new DiaperPageChrome(driver);
			Thread.sleep(2000);
			diaperPage.titleVal(diaperPageExpTitle, diaperPage.getTitle(diaperPageExpTitle));
			test.pass("Diaper Page Title is validated Sucessfully");
		} catch (InterruptedException e) {
			Assert.fail("Unexpected error while validating the Diaper page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 6, dependsOnMethods = { "titleValDiaperPage" }, dataProviderClass = Readxls.class, dataProvider = "Data")
	public void diaper_addCart(String diaperSize) throws Exception {
		ExtentTest test = extent.createTest("Selecting Diaper Size, No. of Diapers, Price and adding Diaper to cart")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Adding Diaper to cart and selecting Pant from Navigation menu");
			diaperPage = new DiaperPageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			diaperPage.diaperSizeSelect();
			diaperPage.noOfDiapers(diaperSize);
			diaperPage.orderPriceType();
			diaperPage.diaper_Submit();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			diaperPage.closeCart();
			diaperPage.dp_HamBurgerOption();
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			pantPage = diaperPage.nav_PantOpt();
			test.pass("Diaper is added to the cart Sucessfully and Navigated to Pant Page from the Navigation menu");
		} catch (Exception e) {
			Assert.fail("Unexpected error while adding Diaper to cart and in navigating to Pant page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 7, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"diaper_addCart" })
	public void titleValPantPage(String pantPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Pant Page title validation").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Pant Page title against the Expected Title.");
			pantPage = new PantPageChrome(driver);
			Thread.sleep(2000);
			pantPage.titleVal(pantPageExpTitle, pantPage.getTitle(pantPageExpTitle));
			test.pass("Pant Page Title is validated Sucessfully");
		} catch (InterruptedException e) {
			Assert.fail("Unexpected error while validating the Pant page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 8, dependsOnMethods = { "titleValPantPage" }, dataProviderClass = Readxls.class, dataProvider = "Data")
	public void pant_addCart(String pantSize) throws Exception {
		ExtentTest test = extent.createTest("Selecting Pant Size, No. of Pants, Price and adding Pant to cart.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Adding Pant to cart and selecting Wipe from Navigation menu.");
			pantPage = new PantPageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			pantPage.pantSizeSelect();
			pantPage.noOfPants(pantSize);
			pantPage.orderPriceType();
			pantPage.pant_Submit();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			pantPage.closeCart();
			pantPage.dp_HamBurgerOption();
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			wipePage = pantPage.nav_WipeOpt();
			test.pass("Pant is added to the cart sucessfully and Navigated to Wipe Page from the Navigation menu.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while adding Pant to cart and in navigating to Wipe page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}

	}

	@Test(priority = 9, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = { "pant_addCart" })
	public void titleValWipePage(String wipePageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Wipe Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Wipe Page title against the Expected Title.");
			wipePage = new WipePageChrome(driver);
			Thread.sleep(2000);
			wipePage.titleVal(wipePageExpTitle, wipePage.getTitle(wipePageExpTitle));
			test.pass("Wipe Page Title is validated Sucessfully");
		} catch (InterruptedException e) {
			Assert.fail("Unexpected error while validating the Wipe page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 10, dependsOnMethods = { "titleValWipePage" }, dataProviderClass = Readxls.class, dataProvider = "Data")
	public void wipe_addCart(String wipesSize) throws Exception {
		ExtentTest test = extent.createTest("Selecting Wipe Size, No. of Wipe Price and adding Wipe to cart")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Adding Wipe to cart and selecting Newborn Gift from Navigation menu");
			wipePage = new WipePageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			wipePage.wipeSizeSelect();
			wipePage.noOfWipes(wipesSize);
			wipePage.wipe_Submit();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			wipePage.closeCart();
			wipePage.dp_HamBurgerOption();
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			nbGPage = wipePage.nav_NewBorn();
			test.pass(
					"Wipe is added to the cart sucessfully and Navigated to Newborn Gift Page from the Navigation menu");
		} catch (Exception e) {
			Assert.fail("Unexpected error while adding Wipe to cart and in navigating to Wipe page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}

	}

	@Test(priority = 11, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"wipe_addCart" }, enabled = false)
	public void titleValnbGiftPage(String nbGigtPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Newborn Gift Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Newborn gift Page title against the Expected Title.");
			nbGPage = new NewBornGiftPageChrome(driver);
			Thread.sleep(2000);
			nbGPage.titleVal(nbGigtPageExpTitle, nbGPage.getTitle(nbGigtPageExpTitle));
			test.pass("Newborn Gift Page Title is validated Sucessfully");
		} catch (InterruptedException e) {
			Assert.fail("Unexpected error while validating the Newborn Gift page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 12, dependsOnMethods = { "wipe_addCart" })
	public void nbGift_addCart() throws Exception {
		ExtentTest test = extent.createTest("Adding Newborn Gift to cart").assignAuthor("Parthasarathi");
		try {
			test.info("Adding Newborn Gift to cart and selecting Diaper Bag from Navigation menu.");
			nbGPage = new NewBornGiftPageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			nbGPage.newBornGift_Submit();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			nbGPage.closeCart();
			nbGPage.dp_HamBurgerOption();
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			dbPage = nbGPage.nav_DiaperBagOpt();
			test.pass(
					"Newborn Gift is added to the cart sucessfully and Navigated to Diaper Bag Page from the Navigation menu.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while adding Newborn Gift to cart and in navigating to Newborn Gift page.: "
					+ e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}

	}

	@Test(priority = 13, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"nbGift_addCart" })
	public void titleValcaraaBagPage(String caraaBagPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Caraa Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the CaraaBag Page title against the Expected Title.");
			dbPage = new DiaperBagPageChrome(driver);
			Thread.sleep(2000);
			dbPage.titleVal(caraaBagPageExpTitle, dbPage.getTitle(caraaBagPageExpTitle));
			test.pass("CaraaBag Page Title is validated Sucessfully");
		} catch (InterruptedException e) {
			Assert.fail("Unexpected error while validating the CaraaBag page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 14, dependsOnMethods = { "titleValcaraaBagPage" })
	public void caraaBag_addCart() throws Exception {
		ExtentTest test = extent.createTest("Adding Caraa Bag to cart.").assignAuthor("Parthasarathi");
		try {
			test.info("Adding Caraa Bag to cart and selecting Merch from Navigation menu.");
			dbPage = new DiaperBagPageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			dbPage.diaperBag_Submit();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			dbPage.closeCart();
			dbPage.dp_HamBurgerOption();
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			merchPage = dbPage.nav_merchOpt();
			test.pass(
					"Caraa Bag is added to the cart sucessfully and Navigated to Merch Page from the Navigation menu.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while adding Caraa Bag to cart and in navigating to Merch page.: "
					+ e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}

	}

	@Test(priority = 15, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"caraaBag_addCart" })
	public void titleValmerchPage(String merchPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Merch Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Merch Page title against the Expected Title.");
			merchPage = new MerchPageChrome(driver);
			Thread.sleep(2000);
			merchPage.titleVal(merchPageExpTitle, merchPage.getTitle(merchPageExpTitle));
			test.pass("Merch Page Title is validated Sucessfully");
		} catch (InterruptedException e) {
			Assert.fail("Unexpected error while validating the Merch page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 16, dependsOnMethods = { "titleValmerchPage" })
	public void merch_ClassicalCap_Select() throws Exception {
		ExtentTest test = extent.createTest("Selecting Classic Cap option from Merch Page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info(
					"Validating the Classic Cap option navigates to Classic Cap page while clicking it from Merch Page.");
			merchPage = new MerchPageChrome(driver);
			ccPage = merchPage.merch_CC_Select();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			test.pass("Classic Cap page is loaded successfully after clicking the Classic Cap option in Merch Page.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while navigating to Classic Cap page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 17, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"merch_ClassicalCap_Select" })
	public void titleValClassicCapPage(String classicCapPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Classic Cap Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Classic Cap Page title against the Expected Title.");
			ccPage = new ClassicCapPageChrome(driver);
			Thread.sleep(2000);
			ccPage.titleVal(classicCapPageExpTitle, ccPage.getTitle(classicCapPageExpTitle));
			test.pass("Classic Cap Page Title is validated Sucessfully");
		} catch (InterruptedException e) {
			Assert.fail("Unexpected error while validating the Classic Cap page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 18, dependsOnMethods = { "titleValClassicCapPage" }, dataProviderClass = Readxls.class, dataProvider = "Data")
	public void merch_ClassicalCap_addCart(String colourCC) throws Exception {
		ExtentTest test = extent.createTest("Selecting Classic Cap Size, colour and adding Pant to cart.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Adding Classic Cap to cart and selecting Merch from Navigation menu.");
			ccPage = new ClassicCapPageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			ccPage.classicCapSizeSelect();
			ccPage.colourChoose(colourCC);
			ccPage.classicCap_Submit();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			ccPage.closeCart();
			ccPage.dp_HamBurgerOption();
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			merchPage = ccPage.nav_merchOpt();
			test.pass(
					"Classic Cap  is added to the cart sucessfully and Navigated to Merch Page from the Navigation menu.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while adding Classic Cap to cart and in navigating to Merch page: "
					+ e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 19, dependsOnMethods = { "merch_ClassicalCap_addCart" })
	public void merch_CrewNeck_Select() throws Exception {
		ExtentTest test = extent.createTest("Selecting Crew Neck option from Merch Page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Crew Neck option navigates to Crew Neck page while clicking it from Merch Page.");
			merchPage = new MerchPageChrome(driver);
			cnPage = merchPage.merch_CN_Select();
			test.pass("Crew Neck page is loaded successfully after clicking the Crew Neck option in Merch Page.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while navigating to Crew Neck page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 20, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"merch_CrewNeck_Select" })
	public void titleValCrewNeckPage(String crewNeckPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Crew Neck Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Crew Neck Page title against the Expected Title.");
			cnPage = new CrewNeckPageChrome(driver);
			Thread.sleep(2000);
			cnPage.titleVal(crewNeckPageExpTitle, cnPage.getTitle(crewNeckPageExpTitle));
			test.pass("Crew Neck Page Title is validated Sucessfully");
		} catch (InterruptedException e) {
			Assert.fail("Unexpected error while validating the CrewNeck  page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 21, dependsOnMethods = { "titleValCrewNeckPage" }, dataProviderClass = Readxls.class, dataProvider = "Data")
	public void merch_CrewNeck_addCart(String sizeCN) throws Exception {
		ExtentTest test = extent.createTest("Selecting Crew Neck Size, colour and adding Pant to cart.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Adding Crew Neck to cart and selecting Merch from Navigation menu.");
			cnPage = new CrewNeckPageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			cnPage.crewNeckSizeSelect();
			cnPage.sizeSelect(sizeCN);
			cnPage.crewNeck_Submit();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			cnPage.closeCart();
			cnPage.dp_HamBurgerOption();
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			merchPage = ccPage.nav_merchOpt();
			test.pass(
					"Crew Neck is added to the cart sucessfully and Navigated to Merch Page from the Navigation menu.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while adding Crew Neck to cart and in navigating to Merch page: "
					+ e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 22, dependsOnMethods = { "merch_CrewNeck_addCart" })
	public void merch_Tode_Select() throws Exception {
		ExtentTest test = extent.createTest("Selecting Tode option from Merch Page.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Tode option navigates to Tode page while clicking it from Merch Page.");
			merchPage = new MerchPageChrome(driver);
			totePage = merchPage.merch_Tode_Select();
			test.pass("Tode page is loaded successfully after clicking the Tode option in Merch Page.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while navigating to Tode page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 23, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"merch_Tode_Select" })
	public void titleValTotePage(String totePageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Tote Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Tote Page title against the Expected Title.");
			totePage = new TotePageChrome(driver);
			Thread.sleep(2000);
			totePage.titleVal(totePageExpTitle, totePage.getTitle(totePageExpTitle));
			test.pass("Tote Page Title is validated Sucessfully");
		} catch (InterruptedException e) {
			Assert.fail("Unexpected error while validating the Tote page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 24, dependsOnMethods = { "titleValTotePage" })
	public void merch_Totte_addCart() throws Exception {
		ExtentTest test = extent.createTest("Adding Tote to cart.").assignAuthor("Parthasarathi");
		try {
			test.info("Adding Tote to cart and selecting Merch from Navigation menu.");
			totePage = new TotePageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			totePage.tote_Submit();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
			totePage.closeCart();
			totePage.dp_HamBurgerOption();
			Thread.sleep(2000);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			merchPage = totePage.nav_merchOpt();
			test.pass("Tote is added to the cart sucessfully and Navigated to Merch Page from the Navigation menu.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while validating the Landing page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 25, dependsOnMethods = { "merch_Totte_addCart" })
	public void merch_ArtPrint_Select() throws Exception {
		ExtentTest test = extent.createTest("Selecting Art Print option from Merch Page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Art Print option navigates to Art Print page while clicking it from Merch Page.");
			merchPage = new MerchPageChrome(driver);
			apPage = merchPage.merch_AP_Select();
			test.pass("Art Print page is loaded successfully after clicking the Art Print option in Merch Page.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while navigating to Art Print page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 26, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"merch_ArtPrint_Select" })
	public void titleValAPPage(String artPrintPageExpTitle) throws Exception {
		ExtentTest test = extent.createTest("Art Printing Page title validation.").assignAuthor("Parthasarathi");
		try {
			test.info("Validating the Art Printing Page title against the Expected Title.");
			apPage = new ArtPrintPageChrome(driver);
			Thread.sleep(2000);
			apPage.titleVal(artPrintPageExpTitle, apPage.getTitle(artPrintPageExpTitle));
			test.pass("Art Printing Page Title is validated Sucessfully");
		} catch (InterruptedException e) {
			Assert.fail("Unexpected error while validating the Art Printing page Title: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 27, dependsOnMethods = { "titleValAPPage" })
	public void merch_ArtPrint_addCart() throws Exception {
		ExtentTest test = extent.createTest("Adding Art Print to cart.").assignAuthor("Parthasarathi");
		try {
			test.info("Adding Art Print to cart and getting the products details from cart.");
			apPage = new ArtPrintPageChrome(driver);
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			apPage.artPrint_Submit();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			apPage.first_ProductDetails();
			Thread.sleep(2000);
			apPage.second_ProductDetails();
			Thread.sleep(2000);
			apPage.third_ProductDetails();
			Thread.sleep(2000);
			apPage.fourth_ProductDetails();
			Thread.sleep(2000);
			apPage.fifth_ProductDetails();
			Thread.sleep(2000);
			apPage.sixth_ProductDetails();
			Thread.sleep(2000);
			apPage.seventh_ProductDetails();
			Thread.sleep(2000);
			apPage.eighth_ProductDetails();
			Thread.sleep(2000);
			apPage.nineth_ProductDetails();
			Thread.sleep(2000);
			apPage.shipping_Details();
			Thread.sleep(2000);
			apPage.taxes_Details();
			Thread.sleep(2000);
			apPage.orderST_Details();
			Thread.sleep(2000);
			infoPage = apPage.confirmCheckout();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			test.pass("Art Print is added to the cart sucessfully and products details from cart is validated.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while adding Art Print to cart and in getting the products details from cart: "
					+ e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 28, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
			"merch_ArtPrint_addCart" })
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

	@Test(priority = 29, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
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
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while entering Contact Information in Information Page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 30, dependsOnMethods = { "addressData" })
	public void productDetails() throws Exception {
		ExtentTest test = extent.createTest("Get the product details from Information page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to get the product details in Information page.");
			infoPage = new InformationPageChrome(driver);
			infoPage.first_prod_detailsUsrScenario();
			infoPage.second_prod_detailsUsrScenario();
			infoPage.third_prod_detailsUsrScenario();
			infoPage.fourth_prod_detailsUsrScenario();
			infoPage.fifth_prod_detailsUsrScenario();
			infoPage.sixth_prod_detailsUsrScenario();
			infoPage.seventh_prod_detailsUsrScenario();
			infoPage.eighth_prod_detailsUsrScenario();
			infoPage.nineth_prod_detailsUsrScenario();
			test.pass("User got the product details successfully in Information Page.");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while getting the product details from Information page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 31, dependsOnMethods = { "productDetails" })
	public void totalPriceVal() throws Exception {
		ExtentTest test = extent.createTest("Product details validation from Information page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to validate the product details in Information page.");
			infoPage = new InformationPageChrome(driver);
			infoPage.sumUS();
			infoPage.priceDisplayUS();
			test.pass("User validated the product details successfully in Information Page.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while validating the product details from Information page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 32, dependsOnMethods = { "totalPriceVal" })
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

	@Test(priority = 33, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
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

	@Test(priority = 34, dependsOnMethods = { "titleValShippingPage" })
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

	@Test(priority = 35, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
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

	@Test(priority = 36, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = {
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

	@Test(priority = 37, dependsOnMethods = { "ccPayment" })
	public void payNow() throws Exception {
		ExtentTest test = extent.createTest("Pay Now button click in Payment Page.").assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to click the Pay Now button in Payment Page.");
			paymentPage = new PaymentPageChrome(driver);
			ocPage = paymentPage.payNow();
			test.pass("User is able click the Pay Now button in Payment Page");
			test.addScreenCaptureFromPath(takeScreenshot(driver));
		} catch (Exception e) {
			Assert.fail("Unexpected error while clicking the Pay Now button in Payment Page " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}

	}

	@Test(priority = 38, dataProviderClass = Readxls.class, dataProvider = "Data", dependsOnMethods = { "payNow" })
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

	@Test(priority = 39, dependsOnMethods = { "titleValOCPage" })
	public void orderSubmissionVal() throws Exception {
		ExtentTest test = extent.createTest("Get the Order ID in Order Confirmation Page.")
				.assignAuthor("Parthasarathi");
		try {
			test.info("Validate whether the user is able to get the Order ID in Order Confirmation Page.");
			ocPage = new OrderConfirmationPageChrome(driver);
			landingPage = ocPage.orderConf();
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			test.pass("User is able to get the Order ID successfully in Order Confirmation Page.");
		} catch (Exception e) {
			Assert.fail("Unexpected error while getting Order ID in Order Confirmation Page: " + e.getMessage());
			test.addScreenCaptureFromPath(takeScreenshot(driver));
			Thread.sleep(2000);
		}
	}

	@Test(priority = 40, dependsOnMethods = { "orderSubmissionVal" })
	public void AssertAll() throws Exception {
		ExtentTest test = extent.createTest("Asserting all the validations").assignAuthor("Parthasarathi");
		try {
			test.info("All validations assert");
			LandingPageChrome landingPage = new LandingPageChrome(driver);
			diaperPage = landingPage.pageAssertAllDiaper();

			DiaperPageChrome diaperPage = new DiaperPageChrome(driver);
			pantPage = diaperPage.pageAssertAll();

			PantPageChrome pantPage = new PantPageChrome(driver);
			wipePage = pantPage.pageAssertAll();

			WipePageChrome wipePage = new WipePageChrome(driver);
			nbGPage = wipePage.pageAssertAll();

			NewBornGiftPageChrome nbGPage = new NewBornGiftPageChrome(driver);
			dbPage = nbGPage.pageAssertAll();

			DiaperBagPageChrome dbPage = new DiaperBagPageChrome(driver);
			merchPage = dbPage.pageAssertAll();

			MerchPageChrome merchPage = new MerchPageChrome(driver);
			ccPage = merchPage.pageAssertAll();

			ClassicCapPageChrome ccPage = new ClassicCapPageChrome(driver);
			merchPage = ccPage.pageAssertAll();

			CrewNeckPageChrome cnPage = new CrewNeckPageChrome(driver);
			merchPage = cnPage.pageAssertAll();

			TotePageChrome totePage = new TotePageChrome(driver);
			merchPage = totePage.pageAssertAll();

			ArtPrintPageChrome apPage = new ArtPrintPageChrome(driver);
			infoPage = apPage.pageAssertAll();

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
				"testCoterie@gmail.com", "uvou jrnj ezyo rcyt", "target/extentReport/AutomationReports_UnRegUserScenario.html");
		driver.quit();
	}
}
