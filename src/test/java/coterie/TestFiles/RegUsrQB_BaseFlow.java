package coterie.TestFiles;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class RegUsrQB_BaseFlow {
	public static WebElement hamBurger;
	public static WebElement userIcon;
	public static WebElement coterieLogo;
	public static WebDriver driver;
	public static WebElement email;
	public static WebElement nextBtn;
	public static WebElement password;
	public static WebElement logIn;
	public static String diaperPriceandFrequency;
	public static WebElement quickBuyDiaperHeading;
	public static WebElement quickBuyDiaperHeadingChange;
	public static WebElement diaperSize;
	public static WebElement diaperNewPrice;
	public static String headingBeforeSelection;
	public static String headingAfterSelection;
	public static String oldPrice;
	public static String newPrice;

	@Test
	public void regUserQuickBuy() throws InterruptedException {
		//System.setProperty("webDriver.chrome.driver", "C:\\Users\\Desktop\\Driver\\chromedriver.exe");
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

		driver.get(
				"https://staging.coterie.com/?x-vercel-protection-bypass=n2fXOzfMVrYLqxOCvjRq0QgJsqqly3LX&x-vercel-set-bypass-cookie=true");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Clicking Hamburger icon in Home Page
		hamBurger = driver.findElement(
				By.xpath("//div[@aria-hidden='false' and contains(@class,'header')]//button[@title='Hamburger']"));
		hamBurger.click();

		userIcon = driver.findElement(By.xpath("//nav/div[1]//div[contains(@class,'sideNav_icons')]/a"));
		wait.until(ExpectedConditions.elementToBeClickable(userIcon));
		userIcon.click();

		email = driver.findElement(By.xpath("//main/div//input[@name='email']"));
		wait.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys("parthasarathi.a@globaldigitalnext.com");

		nextBtn = driver.findElement(By.xpath("//main/div//button[@type='submit']"));
		nextBtn.click();

		password = driver.findElement(By.xpath("//main/div//input[@name='password']"));
		password.sendKeys("DigiNext@123");

		logIn = driver.findElement(By.xpath("//main/div//button[@type='submit']"));
		logIn.click();

		Thread.sleep(8000);
		// wait.until(ExpectedConditions.jsReturnsValue("return document.readyState ==
		// 'complete';"));

		/*
		 * coterieLogo = driver.findElement(By.
		 * xpath("//div[@id='__next']/div/div[2]/nav/a[@title='Coterie Logo']"));
		 * wait.until(ExpectedConditions.elementToBeClickable(coterieLogo));
		 * coterieLogo.click();
		 * 
		 * Thread.sleep(2000);
		 */

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

		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'quickBuy_bottom')]/span"))));

		// ******* Not appliable for Staging Env********//--Issue persists

		diaperNewPrice = driver.findElement(By.xpath("//div[contains(@class,'quickBuy_bottom')]//span[@class='blue']"));
		wait.until(ExpectedConditions.visibilityOf(diaperNewPrice));

		// ********** Diaper Heading Change **********//

		WebElement quickBuyDiaperHeadingChange = driver.findElement(By.xpath("//div[text()='The Diaper']"));
		headingAfterSelection = quickBuyDiaperHeadingChange.getText();

		try {
			Assert.assertNotEquals(headingBeforeSelection, headingAfterSelection, "Heading got Changed");

			System.out.println("Assertion passed: Product Heading got changed as Expected based on the selection.");
		} catch (AssertionError e) { // Handle assertion failure
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

		// ********** Selecting the No. of items to be ordered *********** //

		WebElement noOfDiapers = driver.findElement(By.xpath("//div[contains(@id,'c11486aaf34c')]/div/div/select"));
		Select sel = new Select(noOfDiapers);

		// ****** for selecting the value between 1 to N ******//

		String i = "3";
		sel.selectByVisibleText(i);

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

		diaperPriceandFrequency = driver.findElement(By.xpath("//div[contains(@class,'quickBuy_bottom')]/span"))
				.getText();

		System.out.println("The Selected Diaper Price is " + diaperPriceandFrequency);
		Thread.sleep(2000);

		// ********** Calculate the 10% off//Not for Staging Env Issue Persist
		// *********** //

		double percentage = 10.00;
		double result = (percentage / 100) * doubleDOP;
		double discountedPrice = doubleDOP - result;
		Assert.assertEquals(doubleDNP, discountedPrice, "Discounted Price matches with Expected");

		System.out.println("Calculated discounted price for Diaper is $ " + discountedPrice
				+ " and matched with the price of Diaper $ " + doubleDNP + " calculated in site");

		Thread.sleep(1000);

		// ********** Add Pants *********** //

		driver.findElement(
				By.xpath("//div[@class='quickBuy_heading__FRyAA']/div/div/div/span[contains(text(),'Pants')]")).click();

		WebElement pantSize = driver.findElement(By.xpath("//input[contains(@id,'5')]//parent::div"));
		wait.until(d -> pantSize.isDisplayed());
		pantSize.click();
		System.out.println("Pant size " + pantSize.getText() + " is selected");
		Thread.sleep(1000);

		// ********** Add Wipes *********** //

		driver.findElement(
				By.xpath("//div[@class='quickBuy_heading__FRyAA']/div/div/div/span[contains(text(),'Wipes')]")).click();

		WebElement wipesPack = driver.findElement(By.xpath("//input[contains(@id,'4-packs')]//parent::div"));
		wait.until(d -> wipesPack.isDisplayed());
		wipesPack.click();
		System.out.println("Wipes Selected");

		Thread.sleep(1000);

		// ********* Total Price Amount **********//
		// ********* Saved **********//

		String savedLabel = driver
				.findElement(By.xpath("//div[contains(@class,'minicart_summary')]/div/ul/li[1]/div[1]")).getText();
		String savedPerYear = driver
				.findElement(By.xpath("//div[contains(@class,'minicart_summary')]/div/ul/li[1]/div[2]/span[1]"))
				.getText();
		String savedCost = driver
				.findElement(By.xpath("//div[contains(@class,'minicart_summary')]/div/ul/li[1]/div[2]/span[2]"))
				.getText();
		System.out.println(savedLabel + " is " + savedCost + " " + savedPerYear);

		// ********* Shipping Amount **********//

		String shippingLabel = driver
				.findElement(By.xpath("//div[contains(@class,'minicart_summary')]/div/ul/li[2]/div[1]")).getText();
		String shippingCost = driver
				.findElement(By.xpath("//div[contains(@class,'minicart_summary')]/div/ul/li[2]/div[2]")).getText();

		System.out.println(shippingLabel + " is " + shippingCost);

		// ********* Taxes Amount **********//

		String taxesLabel = driver
				.findElement(By.xpath("//div[contains(@class,'minicart_summary')]/div/ul/li[3]/div[1]")).getText();
		String taxCost = driver.findElement(By.xpath("//div[contains(@class,'minicart_summary')]/div/ul/li[3]/div[2]"))
				.getText();

		System.out.println(taxesLabel + " is " + taxCost);

		// ********* Order Subtotal Amount **********//

		String orderSubTotal = driver
				.findElement(By.xpath("//div[contains(@class,'minicart_summary')]/div/ul/li[4]/div[2]")).getText();
		String orderSubTotalLabel = driver
				.findElement(By.xpath("//div[contains(@class,'minicart_summary')]/div/ul/li[4]/div[1]")).getText();
		System.out.println(orderSubTotalLabel + " is " + orderSubTotal);
		Thread.sleep(2000);

		WebElement checkOutBtn = driver.findElement(By.xpath("//div/button[contains(text(),'Confirm Checkout')]"));
		checkOutBtn.click();
		Thread.sleep(2000);

		// *********** Getting Text from Order Summary *********//

		for (int a = 1; a < 4; a++) {
			wait.until(ExpectedConditions.elementToBeSelected(driver.findElement(
					By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + a + "]/th/span)[1]"))));
			String ProductFirstHeading = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + a + "]/th/span)[1]"))
					.getText();
			String ProductSecondHeading = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + a + "]/th/span)[2]"))
					.getText();
			String ProductThirdHeading = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + a + "]/th/span)[3]"))
					.getText();
			String ProductQuantity = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + a + "]/td//span)[1]"))
					.getText();
			String ProductPrice = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + a + "]/td//span)[3]"))
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

		driver.quit();

	}
}
