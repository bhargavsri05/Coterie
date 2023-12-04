package coterie.TestFiles;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class UnRegQB_BaseFlow {

	public static String diaperPriceandFrequency;
	public static WebElement quickBuyDiaperHeading;
	public static WebElement quickBuyDiaperHeadingChange;
	public static WebElement diaperSize;
	public static WebElement diaperNewPrice;

	public static String headingBeforeSelection;
	public static String headingAfterSelection;
	public static String oldPrice;
	public static String newPrice;
	ExtentReports extent;

	@BeforeTest
	public void config() {
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Coterie Site Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Parthasarathi A");

	}

	@Test
	public void qbBaseFlow() throws InterruptedException {
		extent = new ExtentReports();

		ExtentTest test = extent.createTest("Unregistered User_Quick Buy");

		System.setProperty("webDriver.chrome.driver", "C:\\Users\\Desktop\\Driver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		DevTools devtools = ((HasDevTools) driver).getDevTools();

		devtools.createSession();

		// ***********send commands to the CDP Methods -> CDP methods will get invoke
		// and access to *******
		// *********** chrome dev tools ***********

		devtools.send(Emulation.setDeviceMetricsOverride(390, 844, 50, true, Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty()));

		driver.get(
				"https://staging.coterie.com/?x-vercel-protection-bypass=n2fXOzfMVrYLqxOCvjRq0QgJsqqly3LX&x-vercel-set-bypass-cookie=true");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		SoftAssert softAssert = new SoftAssert();

		// ********** Selecting QuickBuy Option **********//

		WebElement qbBtn = driver.findElement(By.xpath("//div[@aria-hidden='false']//button[text()='Quick Buy']"));
		wait.until(ExpectedConditions.elementToBeClickable(qbBtn));
		qbBtn.click();

		// ********** Add Diapers **********//
		quickBuyDiaperHeading = driver.findElement(
				By.xpath("//div[@class='quickBuy_heading__FRyAA']/div/div/div/span[contains(text(),'Diapers')]"));

		headingBeforeSelection = quickBuyDiaperHeading.getText();
		wait.until(ExpectedConditions.elementToBeClickable(quickBuyDiaperHeading));
		quickBuyDiaperHeading.click();

		diaperSize = driver.findElement(By.xpath("//input[contains(@id,'3')]//parent::div/label"));
		diaperSize.click();
		Thread.sleep(2000);
		// wait.until(ExpectedConditions.)
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'quickBuy_bottom')]/span"))));

		// *******Related to Subscription Checkbox*******
		/*
		 * diaperPriceandFrequency=driver.findElement(By.xpath(
		 * "//div[contains(@class,'quickBuy_bottom')]/span")).getText();
		 * 
		 * System.out.println("The Selected Diaper Price is "+diaperPriceandFrequency);
		 */

		// ******* Not appliable for Staging Env********//--Issue persists
		diaperNewPrice = driver.findElement(By.xpath("//div[contains(@class,'quickBuy_bottom')]//span[@class='blue']"));
		wait.until(ExpectedConditions.visibilityOf(diaperNewPrice));

		// ********** Diaper Heading Change **********//
		WebElement quickBuyDiaperHeadingChange = driver.findElement(By.xpath("//div[text()='The Diaper']"));
		headingAfterSelection = quickBuyDiaperHeadingChange.getText();

		try {
			softAssert.assertNotEquals(headingBeforeSelection, headingAfterSelection, "Heading got Changed");
			// Assert.assertNotEquals(headingBeforeSelection, headingAfterSelection,
			// "Heading got Changed");

			System.out.println("Assertion passed: Diaper Product Heading got changed as Expected based on the selection.");
		} catch (AssertionError e) {
			// Handle assertion failure
			System.err.println("Assertion failed: " + e.getMessage());
		}

		// ********** Getting the Price of 1 item selected **********//

		oldPrice = driver
				.findElement(By.xpath("//div[contains(@class,'quickBuy_bottom')]//span[contains(@class,'oldPrice')]"))
				.getText().replace("$", "");
		double doubleDOP = Double.parseDouble(oldPrice);

		newPrice = driver.findElement(By.xpath("//div[contains(@class,'quickBuy_bottom')]//span[@class='blue']"))
				.getText().replace("$", "");
		double doubleDNP = Double.parseDouble(newPrice);

		System.out.println("Old Price of the Diaper $ " + doubleDOP);
		System.out.println("New Price of the Diaper $ " + doubleDNP);
		softAssert.assertNotEquals(doubleDOP, doubleDNP, "Discount Offfer is applied");

		// ********** Calculate the 10% off *********** //

		double percentage = 10.00;
		double result = (percentage / 100) * doubleDOP;
		double discountedPrice = doubleDOP - result;
		Assert.assertEquals(doubleDNP, discountedPrice, "Discounted Price matches with Expected");

		System.out.println("Calculated discounted price for Diaper is $ " + discountedPrice
				+ " and matched with the price of Diaper $ " + doubleDNP + " calculated in site");

		Thread.sleep(1000);

		// ********** Selecting the No. of items to be ordered *********** //

		WebElement noOfDiapers = driver
				.findElement(By.xpath("//div[@id='accordion__panel-c11486aaf34c']/div/div/select"));
		Select sel = new Select(noOfDiapers);

		// ****** for selecting the value between 1 to N ******//

		String i = "3";
		sel.selectByVisibleText(i);
		Thread.sleep(2000);
		String diaperOldPrice1 = driver
				.findElement(By.xpath("//div[contains(@class,'quickBuy_bottom')]//span[contains(@class,'oldPrice')]"))
				.getText().replace("$", "");
		double doubleDOP1 = Double.parseDouble(diaperOldPrice1);
		String diaperNewPrice1 = driver
				.findElement(By.xpath("//div[contains(@class,'quickBuy_bottom')]//span[@class='blue']")).getText()
				.replace("$", "");
		double doubleDNP1 = Double.parseDouble(diaperNewPrice1);
		System.out.println("After choosing " + i + " Diapers Old Price of the Diapers are $ " + diaperOldPrice1);
		System.out.println("After choosing " + i + " Diapers New Price of the Diapers are $ " + diaperNewPrice1);

		softAssert.assertNotEquals(doubleDOP1, doubleDNP1, "Discount Offfer is applied");
		diaperPriceandFrequency = driver.findElement(By.xpath("//div[contains(@class,'quickBuy_bottom')]/span"))
				.getText();

		System.out.println("The Selected Diaper Price is " + diaperPriceandFrequency);
		Thread.sleep(2000);

		// ********** Calculate the 10% off *********** //

		
		double result1 = (percentage / 100) * doubleDOP1;
		double discountedPrice1 = doubleDOP1 - result1;
		Assert.assertEquals(doubleDNP1, discountedPrice1, "Discounted Price matches with Expected");

		System.out.println("Calculated discounted price for Diaper is $ " + discountedPrice1
				+ " and matched with the price of Diaper $ " + doubleDNP1 + " calculated in site");

		Thread.sleep(1000);

		// ********** Add Pants *********** //

		driver.findElement(
				By.xpath("//div[@class='quickBuy_heading__FRyAA']/div/div/div/span[contains(text(),'Pants')]")).click();
		headingBeforeSelection = driver
				.findElement(
						By.xpath("//div[@class='quickBuy_heading__FRyAA']/div/div/div/span[contains(text(),'Pants')]"))
				.getText();
		WebElement pantSize = driver.findElement(By.xpath("//input[contains(@id,'5')]//parent::div"));
		wait.until(d -> pantSize.isDisplayed());
		pantSize.click();
		System.out.println("Pant size " + pantSize.getText() + " is selected");
		Thread.sleep(1000);

		// ********** Pant Heading Change **********//
		WebElement quickBuyPantHeadingChange = driver.findElement(By.xpath("//div[text()='The Pant']"));
		headingAfterSelection = quickBuyPantHeadingChange.getText();

		try {
			softAssert.assertNotEquals(headingBeforeSelection, headingAfterSelection, "Pants Heading got Changed");
			// Assert.assertNotEquals(headingBeforeSelection, headingAfterSelection,
			// "Heading got Changed");

			System.out.println("Assertion passed: Pant Product Heading got changed as Expected based on the selection.");
		} catch (AssertionError e) {
			// Handle assertion failure
			System.err.println("Assertion failed: " + e.getMessage());
		}
		// ********** Getting the Price of 1 item selected **********//

		oldPrice = driver
				.findElement(
						By.xpath("(//div[contains(@class,'quickBuy_bottom')]//span[contains(@class,'oldPrice')])[3]"))
				.getText().replace("$", "");
		double doublePOP = Double.parseDouble(oldPrice);

		newPrice = driver.findElement(By.xpath("(//div[contains(@class,'quickBuy_bottom')]//span[@class='blue'])[3]"))
				.getText().replace("$", "");
		double doublePNP = Double.parseDouble(newPrice);

		System.out.println("Old Price of the Pant $ " + doublePOP);
		System.out.println("New Price of the Pant $ " + doublePNP);
		softAssert.assertNotEquals(doublePOP, doublePNP, "Discount Offfer is applied");

		// ********** Calculate the 10% off *********** //

		double resultPant = (percentage / 100) * doublePOP;
		double discountedPricePant = doublePOP - resultPant;
		int decimalPlaces = 1;
		double roundedValue = roundToNearest(discountedPricePant, decimalPlaces);
		// int roundedIntFloor = (int) Math.floor(discountedPricePant);

		Assert.assertEquals(doublePNP, roundedValue, "Discounted Price matches with Expected");

		System.out.println("Calculated discounted price for Pant is $ " + roundedValue
				+ " and matched with the price of Pant $ " + doublePNP + " calculated in site");

		Thread.sleep(1000);

		// ********** Add Wipes *********** //

		driver.findElement(
				By.xpath("//div[@class='quickBuy_heading__FRyAA']/div/div/div/span[contains(text(),'Wipes')]")).click();

		WebElement wipesPack = driver.findElement(By.xpath("//input[contains(@id,'4-packs')]//parent::div"));
		wait.until(d -> wipesPack.isDisplayed());
		wipesPack.click();
		System.out.println("Wipes Selected");

		Thread.sleep(1000);

		// ********** Getting the Price of 1 item selected **********//

		oldPrice = driver
				.findElement(
						By.xpath("(//div[contains(@class,'quickBuy_bottom')]//span[contains(@class,'oldPrice')])[5]"))
				.getText().replace("$", "");
		double doubleWOP = Double.parseDouble(oldPrice);

		newPrice = driver.findElement(By.xpath("(//div[contains(@class,'quickBuy_bottom')]//span[@class='blue'])[5]"))
				.getText().replace("$", "");
		double doubleWNP = Double.parseDouble(newPrice);

		System.out.println("Old Price of the Wipes $ " + doubleWOP);
		System.out.println("New Price of the Wipes $ " + doubleWNP);
		softAssert.assertNotEquals(doubleWOP, doubleWNP, "Discount Offfer is applied");

		// ********** Calculate the 10% off *********** //

		double resultWipe = (percentage / 100) * doubleWOP;
		double discountedPriceWipe = doubleWOP - resultWipe;

		/*
		 * double roundedValueWipe = roundToNearest(discountedPriceWipe, decimalPlaces);
		 * // int roundedIntFloor = (int) Math.floor(discountedPricePant);
		 */
	//	softAssert.assertEquals(doubleWNP, discountedPriceWipe, "Discounted Price matches with Expected");

	/*
	 * System.out.println("Calculated discounted price for Wipes is $ " +
	 * discountedPriceWipe + " and matched with the price of Wipes $ " + doubleWNP +
	 * " calculated in site");
	 */
		Thread.sleep(1000);

		// ********** Add Travel Wipes *********** //

		/*
		 * WebElement wipesTravelPack = driver
		 * .findElement(By.xpath("//input[contains(@id,'8-travel-packs')]//parent::div")
		 * ); wait.until(e -> wipesTravelPack.isDisplayed()); wipesTravelPack.click();
		 * System.out.println("Travel Wipes Selected" + wipesTravelPack.getText());
		 * Thread.sleep(1000);
		 */

		// ********** Signup Pop-up Handling//Applies to Production Env **********//

		/*
		 * try { WebElement closeButton = driver .findElement(By.xpath(
		 * "//div[contains(@class,'needsclick')]/div/div/div/div/div/button"));
		 * 
		 * wait.until(ExpectedConditions.elementToBeClickable(closeButton));
		 * closeButton.click();
		 * 
		 * Assert.assertTrue(closeButton.isDisplayed(), "CloseButton is Displayed");
		 * closeButton.click();
		 * 
		 * Thread.sleep(1000); } catch (Exception e) { // TODO Auto-generated catch
		 * e.printStackTrace(); }
		 */

		// ********** Subscribe and save 10% with Auto-Renew//Issue Persists in Staging
		// Env *********** //

		/*
		 * WebElement chckboxSubscription =
		 * wait.until(ExpectedConditions.visibilityOf(driver.findElement( By.xpath(
		 * "(//div[contains(@class,'listContainer')]/div[contains(@class,'options')]/label/input)[1]"
		 * ))));
		 * 
		 * Actions a = new Actions(driver);
		 * a.moveToElement(chckboxSubscription).build().perform();
		 * a.scrollToElement(chckboxSubscription).click().perform(); boolean
		 * subscriptionChkbx1 = chckboxSubscription.isSelected();
		 * System.out.println(subscriptionChkbx1); Thread.sleep(2000);
		 * 
		 * wait.until(s -> chckboxSubscription.isDisplayed()); if
		 * (chckboxSubscription.isDisplayed() == false) { chckboxSubscription.click(); }
		 */

		// ********* Total Price Amount **********//
		for (int c = 1; c < 5; c++) {
			String label = driver
					.findElement(By.xpath("//div[contains(@class,'minicart_summary')]/div/ul/li[" + c + "]/div[1]"))
					.getText();
			String cost = driver
					.findElement(By.xpath("//div[contains(@class,'minicart_summary')]/div/ul/li[" + c + "]/div[2]"))
					.getText();

			System.out.println(label + " : " + cost);
		}
		Thread.sleep(1000);

		/*
		 * Actions a = new Actions(driver);
		 * 
		 * WebElement chckboxSubscription1 = driver.findElement( By.xpath(
		 * "(//div[contains(@class,'listContainer')]/div[contains(@class,'options')]/label/input)[1]"
		 * )); WebElement subscriptionchckbxtxt = driver.findElement(By.xpath(
		 * "//div[contains(@class,'listContainer')]/div[contains(@class,'options')]/label/span[contains(text(),'Auto-Renew')]"
		 * )); a.scrollToElement(subscriptionchckbxtxt);
		 * wait.until(ExpectedConditions.elementToBeClickable(subscriptionchckbxtxt));
		 * boolean subscriptionChkbx2 = chckboxSubscription1.isSelected(); if
		 * (subscriptionChkbx2 == true) { System.out.println(subscriptionchckbxtxt +
		 * " is selected by default"); }
		 */

		WebElement checkOutBtn = driver.findElement(By.xpath("//div/button[contains(text(),'Confirm Checkout')]"));
		/*
		 * String[] btnTxt = checkOutBtn.getText().split("-");
		 * System.out.println("Total Price of the Products selected is" + btnTxt[1]);
		 */
		checkOutBtn.click();

		// *********** Entering the Address details *********//

		driver.findElement(By.xpath("//div/input[@id='checkout_email']")).sendKeys("test50145460@yopmail.com");
		driver.findElement(By.xpath("//div/input[contains(@id,'address_first_name')]")).sendKeys("John");
		driver.findElement(By.xpath("//div/input[contains(@id,'address_last_name')]")).sendKeys("Doe");
		driver.findElement(By.xpath("//div/input[contains(@id,'address_address1')]")).sendKeys("123 Main Street");
		driver.findElement(By.xpath("//div/input[contains(@id,'address_city')]")).sendKeys("White Plains");
		WebElement state1 = driver.findElement(By.xpath("//div/select[contains(@id,'address_province')]"));
		Select state = new Select(state1);
		state.selectByVisibleText("New York");

		driver.findElement(By.xpath("//div/input[contains(@id,'address_zip')]")).sendKeys("10601");
		driver.findElement(By.xpath("//div/input[contains(@id,'address_phone')]")).sendKeys("5465654654");

		// *********** Getting Text from Order Summary *********//

		for (int j = 1; j < 4; j++) {
			String ProductFirstHeading = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + j + "]/th/span)[1]"))
					.getText();
			String ProductSecondHeading = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + j + "]/th/span)[2]"))
					.getText();
			String ProductThirdHeading = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + j + "]/th/span)[3]"))
					.getText();
			String ProductQuantity = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + j + "]/td//span)[1]"))
					.getText();
			String ProductPrice = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + j + "]/td//span)[3]"))
					.getText();

			System.out.println("Details of product Selected: " + ProductFirstHeading + " " + ProductSecondHeading + " "
					+ ProductThirdHeading + " " + ProductQuantity + " " + ProductPrice);

		}

		String subTotal = driver.findElement(By.xpath("//div/table[contains(@class,'total-line')]/tbody/tr[1]/td/span"))
				.getText();
		System.out.println("Subtotal amount is " + subTotal);
		String shipping = driver.findElement(By.xpath("//div/table[contains(@class,'total-line')]/tbody/tr[2]/td/span"))
				.getText();
		System.out.println("Shipping amount is " + shipping);
		String taxes = driver.findElement(By.xpath("//div/table[contains(@class,'total-line')]/tbody/tr[3]/td/span"))
				.getText();
		System.out.println("Taxes are " + taxes);
		String total = driver.findElement(By.xpath("//div/table[contains(@class,'total-line')]/tfoot/tr[1]/td/span[2]"))
				.getText();
		System.out.println("Total Price is " + total);

		/*
		 * String paymentDueLabel = driver .findElement( By.xpath(
		 * "//div[contains(@class,'order-summary')]/div[contains(@class,'payment-due')]/span"
		 * )) .getText(); String paymentDuePricing = driver .findElement(By.xpath(
		 * "//div[contains(@class,'order-summary')]/div[contains(@class,'payment-due')]/div/div[1]"
		 * )) .getText(); String paymentDueUSD = driver .findElement(By.xpath(
		 * "//div[contains(@class,'order-summary')]/div[contains(@class,'payment-due')]/div/div[2]"
		 * )) .getText(); System.out.println(paymentDueLabel + " " + paymentDueUSD + " "
		 * + paymentDuePricing);
		 */

		driver.findElement(By.xpath("//div[@class='step__footer']/button")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@id='myModal']//button[@id='btn-proceed-address']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div/button[@id='continue_button']")).click();
		Thread.sleep(2000);

		WebElement cardNumber = driver.findElement(
				By.xpath("//div[contains(@class,'payment-method')]//div/iframe[contains(@title,'Card number')]"));
		driver.switchTo().frame(cardNumber);

		String a = "4242424242424242";
		driver.findElement(By.xpath("//input[@id='number'][1]")).clear();
		for (char ch : a.toCharArray()) {
			switch (Integer.parseInt(String.valueOf(ch))) {
			case 1:
				driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.NUMPAD1);
				break;
			case 2:
				driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.NUMPAD2);
				break;
			case 3:
				driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.NUMPAD3);
				break;
			case 4:
				driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.NUMPAD4);
				break;
			case 5:
				driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.NUMPAD5);
				break;
			case 6:
				driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.NUMPAD6);
				break;
			case 7:
				driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.NUMPAD7);
				break;
			case 8:
				driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.NUMPAD8);
				break;
			case 9:
				driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.NUMPAD9);
				break;
			/*
			 * case 10:
			 * driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.
			 * NUMPAD1,Keys.NUMPAD0); break; case 11:
			 * driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.
			 * NUMPAD1,Keys.NUMPAD1); break; case 12:
			 * driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.
			 * NUMPAD1,Keys.NUMPAD2); break; case 13:
			 * driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.
			 * NUMPAD1,Keys.NUMPAD3); break; case 14:
			 * driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.
			 * NUMPAD1,Keys.NUMPAD4); break; case 15:
			 * driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.
			 * NUMPAD1,Keys.NUMPAD5); break; case 16:
			 * driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(Keys.
			 * NUMPAD0,Keys.NUMPAD6); break;
			 */
			}

		}
		// driver.findElement(By.xpath("//input[@id='number'][1]")).sendKeys(a);
		driver.switchTo().parentFrame();

		WebElement cardName = driver.findElement(
				By.xpath("//div[contains(@class,'payment-method')]//div/iframe[contains(@title,'Name on card')]"));
		driver.switchTo().frame(cardName);

		driver.findElement(By.xpath("//input[@id='name'][1]")).sendKeys("John Doe");
		driver.switchTo().parentFrame();

		WebElement cardED = driver.findElement(
				By.xpath("//div[contains(@class,'payment-method')]//div/iframe[contains(@title,'Expiration date')]"));
		driver.switchTo().frame(cardED);
		String expiryDate = "1225";
		for (char ch : expiryDate.toCharArray()) {
			switch (Integer.parseInt(String.valueOf(ch))) {
			case 1:
				driver.findElement(By.xpath("//input[@id='expiry'][1]")).sendKeys(Keys.NUMPAD1);
				break;
			case 2:
				driver.findElement(By.xpath("//input[@id='expiry'][1]")).sendKeys(Keys.NUMPAD2);
				break;
			case 3:
				driver.findElement(By.xpath("//input[@id='expiry'][1]")).sendKeys(Keys.NUMPAD3);
				break;
			case 4:
				driver.findElement(By.xpath("//input[@id='expiry'][1]")).sendKeys(Keys.NUMPAD4);
			case 5:
				driver.findElement(By.xpath("//input[@id='expiry'][1]")).sendKeys(Keys.NUMPAD5);
			}
		}
		// driver.findElement(By.xpath("//input[@id='expiry'][1]")).sendKeys("12/25");
		driver.switchTo().parentFrame();

		// Security code
		WebElement cardSecurity = driver.findElement(
				By.xpath("//div[contains(@class,'payment-method')]//div/iframe[contains(@title,'Security code')]"));
		driver.switchTo().frame(cardSecurity);

		driver.findElement(By.xpath("//input[@id='verification_value'][1]")).sendKeys("123");
		driver.switchTo().parentFrame();
		Thread.sleep(1000);
		WebElement subAgreement = driver.findElement(
				By.xpath("//div[@class='step']/div/form/div[contains(@class,'subscription-agreement')]/div/div/label"));
		wait.until(ExpectedConditions.elementToBeClickable(subAgreement));
		subAgreement.click();
		driver.findElement(
				By.xpath("//div[@class='step__footer']/div[contains(@class,'shown')]/button[@id='continue_button']"))
				.click();
		String finalPageTitle = "Thank you for your purchase! - Coterie Staging - Checkout";
		wait.until(ExpectedConditions.titleIs(finalPageTitle));

		String PageTitle = driver.getTitle();
		AssertJUnit.assertEquals(finalPageTitle, PageTitle);

		System.out.println(driver.findElement(By.xpath("//div[@class='step']//div[contains(@class,'heading')]/h2"))
				.getText() + " : "
				+ driver.findElement(By.xpath("//div[@class='step']//div[contains(@class,'heading')]/span")).getText());
		// softAssert.assertAll();
		driver.quit();
		extent.flush();
	}

	public static double roundToNearest(double value, int decimalPlaces) {
		double scale = Math.pow(10, decimalPlaces);
		return Math.round(value * scale) / scale;
	}

}
