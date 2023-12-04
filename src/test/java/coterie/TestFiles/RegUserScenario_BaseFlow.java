package coterie.TestFiles;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
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
import org.testng.annotations.Test;

public class RegUserScenario_BaseFlow {
	public static Actions mouseAction;
	public static WebDriver driver;
	public static WebElement hamBurger;
	public static WebElement userIcon;
	public static WebElement email;
	public static WebElement nextBtn;
	public static WebElement password;
	public static WebElement logIn;
	public static WebElement opHamburger;
	public static WebElement diaperOption;
	public static WebElement pantOption;
	public static WebElement wipesOption;
	public static WebElement newbornOption;
	public static WebElement bagOption;
	public static WebElement merchOption;
	public static WebElement sizeDropdown;
	public static WebElement noOfProduct;
	public static WebElement orderType;
	public static WebElement submitButton;
	public static WebElement closeIcon;
	public static WebElement classicCap;
	public static WebElement colourDropdown;
	public static WebElement chooseColour;
	public static WebElement productDetails;
	public static WebElement packDetails;
	public static WebElement orderTypeVal;
	public static WebElement orderFrequency;
	public static WebElement summaryLabel;
	public static WebElement summaryPrice;
	public static WebElement checkOut;
	public static WebElement cardNumber;
	public static WebElement cardName;
	public static WebElement cardED;
	public static WebElement cardSecurity;
	public String label;
	public String cost;
	public String ProductFirstHeading;
	public String ProductSecondHeading;
	public String ProductQuantity;
	public String ProductPrice;

	@Test
	public void regUser() throws InterruptedException {

		System.setProperty("webDriver.chrome.driver", "C:\\Users\\Desktop\\Driver\\chromedriver.exe");

		driver = new ChromeDriver();
		mouseAction = new Actions(driver);
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

		// Clicking Hamburger icon in Home Page
		hamBurger = driver.findElement(
				By.xpath("//div[@aria-hidden='false' and contains(@class,'header')]//button[@title='Hamburger']"));
		hamBurger.click();

		diaperOption = driver.findElement(By.xpath("//div[contains(@class,'sideNav')]/a[contains(@href,'diaper')]"));
		diaperOption.click();

		sizeDropdown = driver.findElement(
				By.xpath("//div[@id='__next']/main/section/div/div[2]/form/div[2]/button[contains(@class,'select2')]"));
		sizeDropdown.click();
		/*
		 * mouseAction.scrollToElement(sizeDropdown).perform();
		 * wait.until(ExpectedConditions.elementToBeClickable(sizeDropdown));
		 * sizeDropdown.click();
		 */

		noOfProduct = driver.findElement(By.xpath("//ul[contains(@class,'select2')]/li[2]"));

		noOfProduct.click();

		orderType = driver.findElement(By.xpath("//div[@id='pdp-plan-options']/ul/li[2]/div/div"));

		wait.until(ExpectedConditions.elementToBeClickable(orderType));
		orderType.click();

		submitButton = driver.findElement(By.xpath("//div[contains(@class,'genericPdpHero')]/button[@type='submit']"));

		mouseAction.moveToElement(submitButton).perform();
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
		Thread.sleep(2000);

		// Pant

		closeIcon = driver.findElement(By.xpath("//div[contains(@class,'funnelHeader')]/button"));
		closeIcon.click();
		Thread.sleep(2000);
		opHamburger = driver
				.findElement(By.xpath("//div[@id='__next']/div[2]/div/div[1]/div/button[@title='Hamburger']")); //
		// wait.until(ExpectedConditions.elementToBeClickable(opHamburger));
		if (opHamburger.isEnabled() && opHamburger.isDisplayed()) {

			opHamburger.click();
		}
		pantOption = driver.findElement(By.xpath("//div[contains(@class,'sideNav')]/a[contains(@href,'pant')]"));
		pantOption.click();
		Thread.sleep(2000);
		sizeDropdown = driver.findElement(
				By.xpath("//div[@id='__next']/main/section/div/div[2]/form/div[2]/button[contains(@class,'select2')]"));
		sizeDropdown.click();

		noOfProduct = driver.findElement(By.xpath("//ul[contains(@class,'select2')]/li[2]"));
		noOfProduct.click();
		orderType = driver.findElement(By.xpath("//div[@id='pdp-plan-options']/ul/li[2]/div/div"));
		wait.until(ExpectedConditions.elementToBeClickable(orderType));
		orderType.click();
		submitButton = driver.findElement(By.xpath("//div[contains(@class,'genericPdpHero')]/button[@type='submit']"));
		mouseAction.moveToElement(submitButton).perform();
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
		Thread.sleep(2000);

		// Wipes

		closeIcon = driver.findElement(By.xpath("//div[contains(@class,'funnelHeader')]/button"));
		closeIcon.click();
		Thread.sleep(2000);
		opHamburger = driver
				.findElement(By.xpath("//div[@id='__next']/div[2]/div/div[1]/div/button[@title='Hamburger']"));
		// wait.until(ExpectedConditions.elementToBeClickable(opHamburger));
		if (opHamburger.isEnabled() && opHamburger.isDisplayed()) {

			opHamburger.click();
		}
		wipesOption = driver.findElement(By.xpath("//div[contains(@class,'sideNav')]/a[contains(@href,'wipe')]"));
		wipesOption.click();
		Thread.sleep(2000);
		sizeDropdown = driver.findElement(
				By.xpath("//div[@id='__next']/main/section/div/div[2]/form/div[2]/button[contains(@class,'select2')]"));
		sizeDropdown.click();

		noOfProduct = driver.findElement(By.xpath("//ul[contains(@class,'select2')]/li[2]"));
		noOfProduct.click();

		submitButton = driver.findElement(By.xpath("//div[contains(@class,'genericPdpHero')]/button[@type='submit']"));
		mouseAction.moveToElement(submitButton).perform();
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
		Thread.sleep(2000);

		// Newborn

		closeIcon = driver.findElement(By.xpath("//div[contains(@class,'funnelHeader')]/button"));
		closeIcon.click();
		Thread.sleep(2000);
		opHamburger = driver
				.findElement(By.xpath("//div[@id='__next']/div[1]/div/div[1]/div/button[@title='Hamburger']"));
		// wait.until(ExpectedConditions.elementToBeClickable(opHamburger));
		if (opHamburger.isEnabled() && opHamburger.isDisplayed()) {

			opHamburger.click();
		}
		newbornOption = driver.findElement(By.xpath("//div[contains(@class,'sideNav')]/a[contains(@href,'newborn')]"));
		newbornOption.click();
		Thread.sleep(2000);

		submitButton = driver.findElement(By.xpath("//div[contains(@class,'genericPdpHero')]/button[@type='submit']"));
		mouseAction.moveToElement(submitButton).perform();
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
		Thread.sleep(2000);

		// Caraa-bag

		closeIcon = driver.findElement(By.xpath("//div[contains(@class,'funnelHeader')]/button"));
		closeIcon.click();
		Thread.sleep(2000);
		opHamburger = driver
				.findElement(By.xpath("//div[@id='__next']/div[1]/div/div[1]/div/button[@title='Hamburger']"));
		// wait.until(ExpectedConditions.elementToBeClickable(opHamburger));
		if (opHamburger.isEnabled() && opHamburger.isDisplayed()) {

			opHamburger.click();
		}
		bagOption = driver.findElement(By.xpath("//div[contains(@class,'sideNav')]/a[contains(@href,'bag')]"));
		bagOption.click();
		Thread.sleep(2000);

		submitButton = driver.findElement(By.xpath("//div[contains(@class,'genericPdpHero')]/button[@type='submit']"));
		mouseAction.moveToElement(submitButton).perform();
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
		Thread.sleep(2000);

		// Merch

		closeIcon = driver.findElement(By.xpath("//div[contains(@class,'funnelHeader')]/button"));
		closeIcon.click();
		Thread.sleep(2000);
		opHamburger = driver
				.findElement(By.xpath("//div[@id='__next']/div[1]/div/div[1]/div/button[@title='Hamburger']")); //
		// wait.until(ExpectedConditions.elementToBeClickable(opHamburger));
		if (opHamburger.isEnabled() && opHamburger.isDisplayed()) {

			opHamburger.click();
		}
		merchOption = driver.findElement(By.xpath("//div[contains(@class,'sideNav')]/a[contains(@href,'merch')]"));
		merchOption.click();
		Thread.sleep(2000);
		classicCap = driver.findElement(By.xpath("//a[contains(@href,'cap')]/div[2]/div[2]/a"));
		classicCap.click();
		colourDropdown = driver.findElement(
				By.xpath("//div[@id='__next']/main/section/div/div[2]/form/div[2]/button[contains(@class,'select2')]"));
		colourDropdown.click();
		chooseColour = driver.findElement(By.xpath("//ul[contains(@class,'select2')]/li[2]"));
		chooseColour.click();
		submitButton = driver.findElement(By.xpath("//div[contains(@class,'genericPdpHero')]/button[@type='submit']"));
		mouseAction.moveToElement(submitButton).perform();
		wait.until(ExpectedConditions.elementToBeClickable(submitButton));
		submitButton.click();
		Thread.sleep(2000);

		for (int i = 1; i < 4; i++) {
			productDetails = driver
					.findElement(By.xpath("//div[contains(@class,'pdpContainer')]/div[" + i + "]/div[2]"));
			packDetails = driver
					.findElement(By.xpath("//div[contains(@class,'pdpContainer')]/div[" + i + "]/div[3]/p"));
			orderTypeVal = driver
					.findElement(By.xpath("//div[contains(@class,'pdpContainer')]/div[" + i + "]/div[4]/div/div[1]"));
			orderFrequency = driver
					.findElement(By.xpath("//div[contains(@class,'pdpContainer')]/div[" + i + "]/div[4]/div/div[2]"));
			System.out.println(productDetails.getText());
			System.out.println(packDetails.getText());
			System.out.println(orderTypeVal.getText());
			System.out.println(orderFrequency.getText());

		}
		for (int i = 4; i < 7; i++) {
			productDetails = driver
					.findElement(By.xpath("//div[contains(@class,'pdpContainer')]/div[" + i + "]/div[2]"));
			orderTypeVal = driver
					.findElement(By.xpath("//div[contains(@class,'pdpContainer')]/div[" + i + "]/div[4]/div/div[1]"));
			orderFrequency = driver
					.findElement(By.xpath("//div[contains(@class,'pdpContainer')]/div[" + i + "]/div[4]/div/div[2]"));
			System.out.println(productDetails.getText());
			System.out.println(orderTypeVal.getText());
			System.out.println(orderFrequency.getText());

		}
		Thread.sleep(2000);
		for (int j = 1; j < 4; j++) {
			summaryLabel = driver
					.findElement(By.xpath("//div[@id='__next']/aside/div[2]/div[4]/div[2]/ul/li[" + j + "]/div[1]"));
			// wait.until(ExpectedConditions.elementToBeSelected(summaryLabel));
			summaryPrice = driver
					.findElement(By.xpath("//div[@id='__next']/aside/div[2]/div[4]/div[2]/ul/li[" + j + "]/div[2]"));
			// wait.until(ExpectedConditions.elementToBeSelected(summaryPrice));
			System.out.println(summaryLabel.getText() + " : " + summaryPrice.getText());
		}

		checkOut = driver
				.findElement(By.xpath("//div[contains(@class,'minicart_buttons')]/button[text()='Confirm Checkout']"));
		checkOut.click();

		// *********** Getting Text from Order Summary *********//
		WebElement hideBar = driver.findElement(By.xpath("//aside/button//span[contains(text(),'Hide')]"));
		wait.until(ExpectedConditions.elementSelectionStateToBe(hideBar, false));

		for (int k = 1; k < 7; k++) {
			ProductFirstHeading = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + k + "]/th/span)[1]"))
					.getText();
			ProductSecondHeading = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + k + "]/th/span)[2]"))
					.getText();

			ProductQuantity = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + k + "]/td//span)[1]"))
					.getText();
			ProductPrice = driver
					.findElement(By.xpath("(//div/table[contains(@class,'product')]/tbody/tr[" + k + "]/td//span)[3]"))
					.getText();

			System.out.println("Details of product Selected: " + ProductFirstHeading + " " + ProductSecondHeading + " "
					+ ProductQuantity + " " + ProductPrice);

		}
		for (int c = 1; c < 4; c++) {
			label = driver.findElement(By.xpath("//div/table[contains(@class,'total-line')]/tbody/tr[" + c + "]/th"))
					.getText();
			cost = driver
					.findElement(By.xpath("//div/table[contains(@class,'total-line')]/tbody/tr[" + c + "]/td/span"))
					.getText();
			System.out.println(label + " : " + cost);

		}

		String totalLabel = driver
				.findElement(By.xpath("(//div/table[contains(@class,'total-line')]/tfoot/tr/th/span)[1]")).getText();
		String totalCostUnit = driver
				.findElement(By.xpath("(//div/table[contains(@class,'total-line')]/tfoot/tr/td/span[1])[1]")).getText();
		String totalCost = driver
				.findElement(By.xpath("(//div/table[contains(@class,'total-line')]/tfoot/tr/td/span[2])[1]")).getText();
		System.out.println(totalLabel + " : " + totalCostUnit + " : " + totalCost);

		hideBar.click();
		driver.findElement(By.xpath("//div[@class='step']/form/div[contains(@class,'footer')]/button")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div[@id='myModal']//button[@id='btn-proceed-address']")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//div/button[@id='continue_button']")).click();
		Thread.sleep(2000);

		cardNumber = driver.findElement(
				By.xpath("//div[contains(@class,'payment-method')]//div/iframe[contains(@title,'Card number')]"));
		driver.switchTo().frame(cardNumber);

		String b = "4242424242424242";
		driver.findElement(By.xpath("//input[@id='number'][1]")).clear();
		for (char ch : b.toCharArray()) {
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
		/*
		 * WebElement subAgreement = driver.findElement( By.xpath(
		 * "//div[@class='step']/div/form/div[contains(@class,'subscription-agreement')]/div/div/label"
		 * )); wait.until(ExpectedConditions.elementToBeClickable(subAgreement));
		 * subAgreement.click();
		 */
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
