package coterie.pageObjectModel;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v117.emulation.Emulation;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import coterie.AbstractComponents.AbstractComponent;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LandingPageChrome extends AbstractComponent {
	WebDriver driver;

	public LandingPageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@id,'c11486aaf34c')]//ul/li/label/div/span[1]")//div[contains(@id,'c11486aaf34c')]//ul/li/div/label/div
	List<WebElement> diaperSizes;

	@FindBy(xpath = "//div[contains(@id,'c9cefb1920cb')]//ul/li/label/div/span[1]")
	List<WebElement> pantSizes;

	@FindBy(xpath = "//div[contains(@id,'5fdf65d51adf')]//ul/li/label/div/span[1]")
	List<WebElement> wipeSizes;

	@FindBy(xpath = "//div[@id='accordion__panel-c11486aaf34c']/div/div/select/option")
	List<WebElement> noOfProducts;

	@FindBy(xpath = "//div[@aria-hidden='false']//button[text()='Quick Buy']")
	WebElement btn_QuickBuy;

	@FindBy(xpath = "//div[@class='quickBuy_heading__FRyAA']/div/div/div/span[contains(text(),'Diapers')]")
	WebElement txt_DiaperHeading;

	@FindBy(xpath = "//input[contains(@id,'3')]//parent::div/label")
	WebElement opt_DiaperSize;

	@FindBy(xpath = "(//div[contains(@class,'quickBuy_bottom')]/span)[1]")
	WebElement txt_DiaperPriceDisplay;

	@FindBy(xpath = "(//div[contains(@class,'quickBuy_bottom')]/span)[3]")
	WebElement txt_PantPriceDisplay;

	@FindBy(xpath = "(//div[contains(@class,'quickBuy_bottom')]/span)[5]")
	WebElement txt_WipesPriceDisplay;

	@FindBy(xpath = "//div[text()='The Diaper']")
	WebElement txt_DiaperHeading_Change;

	@FindBy(xpath = "(//div[contains(@class,'quickBuy_bottom')]//span[contains(@class,'oldPrice')])[1]")
	WebElement txt_DiaperOldPrice;

	@FindBy(xpath = "(//div[contains(@class,'quickBuy_bottom')]//span[@class='blue'])[1]")
	WebElement txt_DiaperNewPrice;

	@FindBy(xpath = "(//div[contains(@class,'quickBuy_bottom')]//span[contains(@class,'oldPrice')])[3]")
	WebElement txt_PantOldPrice;

	@FindBy(xpath = "(//div[contains(@class,'quickBuy_bottom')]//span[@class='blue'])[3]")
	WebElement txt_PantNewPrice;

	@FindBy(xpath = "(//div[contains(@class,'quickBuy_bottom')]//span[contains(@class,'oldPrice')])[5]")
	WebElement txt_WipeOldPrice;

	@FindBy(xpath = "(//div[contains(@class,'quickBuy_bottom')]//span[@class='blue'])[5]")
	WebElement txt_WipeNewPrice;

	@FindBy(xpath = "//div[contains(@class,'quickBuy_accordion')]/div[1]/div[1]/div[2]/div/select")
	WebElement opt_NoOfDiapers_AfterMovingToNextProduct;

	@FindBy(xpath = "//div[contains(@id,'c11486aaf34c')]/div/div/select")
	WebElement opt_NoOfDiapers_duringProductSizeSelect;

	@FindBy(xpath = "//div[contains(@class,'quickBuy_accordion')]/div[2]/div[1]/div[2]/div/select")
	WebElement opt_NoOfPants_AfterMovingToNextProduct;

	@FindBy(xpath = "//div[contains(@id,'c9cefb1920cb')]/div/div/select")
	WebElement opt_NoOfPants_duringProductSizeSelect;

	@FindBy(xpath = "//div[contains(@class,'quickBuy_accordion')]/div[3]/div[1]/div[2]/div/select")
	WebElement opt_NoOfWipes_AfterMovingToNextProduct;

	@FindBy(xpath = "//div[contains(@id,'5fdf65d51adf')]/div/div/select")
	WebElement opt_NoOfWipes_duringProductSizeSelect;

	@FindBy(xpath = "//div[@class='quickBuy_heading__FRyAA']/div/div/div/span[contains(text(),'Pants')]")
	WebElement txt_PantHeading;

	@FindBy(xpath = "//div[text()='The Pant']")
	WebElement txt_PantHeading_Change;

	@FindBy(xpath = "//input[contains(@id,'5')]//parent::div")
	WebElement opt_PantSize;

	@FindBy(xpath = "//div[@class='quickBuy_heading__FRyAA']/div/div/div/span[contains(text(),'Wipes')]")
	WebElement txt_WipesHeading;

	@FindBy(xpath = "//div[text()='The Wipe']")
	WebElement txt_WipeHeading_Change;

	@FindBy(xpath = "//input[contains(@id,'4-packs')]//parent::div")
	WebElement opt_WipesPack;

	@FindBy(xpath = "//div[contains(@class,'minicart_summary')]/div/ul/li[1]/div[1]")
	WebElement txt_SavedLabel;

	@FindBy(xpath = "//div[contains(@class,'minicart_summary')]/div/ul/li[1]/div[2]/span[1]")
	WebElement txt_SavedPerYear;

	@FindBy(xpath = "//div[contains(@class,'minicart_summary')]/div/ul/li[1]/div[2]/span[2]")
	WebElement txt_SavedCost;

	@FindBy(xpath = "//div[contains(@class,'minicart_summary')]/div/ul/li[2]/div[1]")
	WebElement txt_ShippingLabel;

	@FindBy(xpath = "//div[contains(@class,'minicart_summary')]/div/ul/li[2]/div[2]")
	WebElement txt_ShippingCost;

	@FindBy(xpath = "//div[contains(@class,'minicart_summary')]/div/ul/li[3]/div[1]")
	WebElement txt_TaxesLabel;

	@FindBy(xpath = "//div[contains(@class,'minicart_summary')]/div/ul/li[3]/div[2]")
	WebElement txt_TaxesCost;

	@FindBy(xpath = "//div[contains(@class,'minicart_summary')]/div/ul/li[4]/div[1]")
	WebElement txt_OrderSTLabel;

	@FindBy(xpath = "//div[contains(@class,'minicart_summary')]/div/ul/li[4]/div[2]")
	WebElement txt_OrderSTCost;

	@FindBy(xpath = "//div/button[contains(text(),'Confirm Checkout')]")
	WebElement btn_CheckOut;

	@FindBy(xpath = "//div[@aria-hidden='false' and contains(@class,'header')]//button[@title='Hamburger']")
	WebElement menu_HamBurgerHome;

	@FindBy(xpath = "//nav/div[1]//div[contains(@class,'sideNav_icons')]/a")
	WebElement icon_UserIcon;

	@FindBy(xpath = "//div[contains(@class,'sideNav')]/a[contains(@href,'diaper')]")
	WebElement navMenu_Diaper;

	public void btn_QuickBuy_Click() throws Exception {
		waitForElementToBeClickable(btn_QuickBuy);
		assertTrueElementDisplayed(btn_QuickBuy);
		btn_QuickBuy.click();
		Thread.sleep(2000);
	}

	public void add_Diaper(String sizeDiaper) throws Exception {
		String headingBeforeSelection = txt_DiaperHeading.getText();
		waitForElementToBeClickable(txt_DiaperHeading);
		assertTrueElementDisplayed(txt_DiaperHeading);
		txt_DiaperHeading.click();
		Thread.sleep(2000);
		for (WebElement size : diaperSizes) {

			if (size.getText().equalsIgnoreCase(sizeDiaper)) {
				waitForElementToBeClickable(size);
				size.click();
				break;
			}

		}
		Thread.sleep(2000);

		/*
		 * waitForElementToAppear(opt_DiaperSize); opt_DiaperSize.click();
		 */

		assertTrueElementDisplayed(txt_DiaperHeading_Change);

		waitForElementToAppear(txt_DiaperPriceDisplay);
		assertTrueElementDisplayed(txt_DiaperPriceDisplay);

		String headingAfterSelection = txt_DiaperHeading_Change.getText();

		try {
			assertNotEquals(headingBeforeSelection, headingAfterSelection);
			System.out.println("Assertion passed: Diaper Heading got changed as Expected based on the selection.");
		} catch (AssertionError e) {
			// Handle assertion failure
			System.err.println("Assertion failed, Diaper Heading not changed as Expected based on the selection: "
					+ e.getMessage());
		}
	}

	public double price_DiaperVal() {
		waitForElementToAppear(txt_DiaperOldPrice);
		String oldPrice = txt_DiaperOldPrice.getText().replace("$", "");
		double doubleDOP = Double.parseDouble(oldPrice);
		waitForElementToAppear(txt_DiaperNewPrice);
		String newPrice = txt_DiaperNewPrice.getText().replace("$", "");
		double doubleDNP = Double.parseDouble(newPrice);
		System.out.println("Old Price of the Diaper $ " + doubleDOP);
		System.out.println("New Price of the Diaper $ " + doubleDNP);
		double percentage = 10.00;
		double result = (percentage / 100) * doubleDOP;
		double discountedPrice = doubleDOP - result;
		assertEquals(doubleDNP, discountedPrice);
		if (doubleDNP == discountedPrice) {
			System.out.println("Calculated discounted price for Diaper is $ " + discountedPrice
					+ " is matched with the price of Diaper $ " + doubleDNP + " calculated in site");
		} else {
			System.out.println("Calculated discounted price for Diaper is $ " + discountedPrice
					+ " is not matched with the price of Diaper $ " + doubleDNP + " calculated in site");
		}
		return discountedPrice;
	}

	public double price_PantVal() {
		waitForElementToAppear(txt_PantOldPrice);
		String oldPrice = txt_PantOldPrice.getText().replace("$", "");
		double doublePOP = Double.parseDouble(oldPrice);
		waitForElementToAppear(txt_PantNewPrice);
		String newPrice = txt_PantNewPrice.getText().replace("$", "");
		double doublePNP = Double.parseDouble(newPrice);
		System.out.println("Old Price of the Pant $ " + doublePOP);
		System.out.println("New Price of the Pant $ " + doublePNP);
		double percentage = 10.00;
		double result = (percentage / 100) * doublePOP;
		double discountedPrice = doublePOP - result;
		double roundOff = Math.round(discountedPrice);
		assertEquals(doublePNP, roundOff);
		if (doublePNP == roundOff) {
			System.out.println("Calculated discounted price for Pant is $ " + roundOff
					+ " is matched with the price of Pant $ " + doublePNP + " calculated in site");
		} else {
			System.out.println("Calculated discounted price for Pant is $ " + roundOff
					+ " is not matched with the price of Pant $ " + doublePNP + " calculated in site");
		}
		return roundOff;
	}

	public double price_wipesVal() {
		waitForElementToAppear(txt_WipeOldPrice);
		String oldPrice = txt_WipeOldPrice.getText().replace("$", "");
		double doubleWOP = Double.parseDouble(oldPrice);
		waitForElementToAppear(txt_WipeNewPrice);
		String newPrice = txt_WipeNewPrice.getText().replace("$", "");
		double doubleWNP = Double.parseDouble(newPrice);
		System.out.println("Old Price of the Wipes $ " + doubleWOP);
		System.out.println("New Price of the Wipes $ " + doubleWNP);
		double percentage = 10.00;
		double result = (percentage / 100) * doubleWOP;
		double discountedPrice = doubleWOP - result;
		if (doubleWNP == discountedPrice) {
			assertTrue(true);
			System.out.println("Calculated discounted price for Wipes is $ " + discountedPrice
					+ " is matched with the price of Wipes $ " + doubleWNP + " calculated in site");
		} else {
			assertTrue(false);
			System.out.println("Calculated discounted price for Wipes is $ " + discountedPrice
					+ " is not matched with the price of Wipes $ " + doubleWNP + " calculated in site");
		}
		return discountedPrice;
	}

	public void noOf_Diapers(String i) throws Exception {
		waitForElementToAppear(opt_NoOfDiapers_duringProductSizeSelect);
		Select sel = new Select(opt_NoOfDiapers_duringProductSizeSelect);
		// ****** for selecting the value between 1 to N ******//
		sel.selectByVisibleText(i);
		Thread.sleep(2000);
	}

	public void noOf_Pants(String i) throws Exception {
		waitForElementToAppear(opt_NoOfPants_duringProductSizeSelect);
		Select sel = new Select(opt_NoOfPants_duringProductSizeSelect);
		// ****** for selecting the value between 1 to N ******//
		sel.selectByVisibleText(i);
		Thread.sleep(2000);
	}

	public void noOf_Wipes(String i) throws Exception {
		waitForElementToAppear(opt_NoOfWipes_duringProductSizeSelect);
		Select sel = new Select(opt_NoOfWipes_duringProductSizeSelect);
		// ****** for selecting the value between 1 to N ******//
		sel.selectByVisibleText(i);
		Thread.sleep(2000);
	}

	public void add_Pant(String sizePant) throws Exception {
		assertTrueElementDisplayed(txt_PantHeading);
		String headingBeforeSelection = txt_PantHeading.getText();
		waitForElementToBeClickable(txt_PantHeading);
		txt_PantHeading.click();
		Thread.sleep(2000);
		for (WebElement size : pantSizes) {

			if (size.getText().equalsIgnoreCase(sizePant)) {
				waitForElementToBeClickable(size);
				size.click();
				break;
			}

		}
		Thread.sleep(2000);

		/*
		 * waitForElementToAppear(opt_PantSize); opt_PantSize.click();
		 */
		waitForElementToAppear(txt_PantPriceDisplay);
		assertTrueElementDisplayed(txt_PantPriceDisplay);
		assertTrueElementDisplayed(txt_PantHeading_Change);
		String headingAfterSelection = txt_PantHeading_Change.getText();
		try {
			assertNotEquals(headingBeforeSelection, headingAfterSelection);
			System.out.println("Assertion passed: Pant Heading got changed as Expected based on the selection.");
		} catch (AssertionError e) {
			System.err.println("Assertion failed: " + e.getMessage());
		}
	}

	public void add_Wipe(String sizeWipe) throws Exception {
		assertTrueElementDisplayed(txt_WipesHeading);
		String headingBeforeSelection = txt_WipesHeading.getText();
		waitForElementToBeClickable(txt_WipesHeading);
		txt_WipesHeading.click();

		for (WebElement size : wipeSizes) {

			if (size.getText().equalsIgnoreCase(sizeWipe)) {
				waitForElementToBeClickable(size);
				size.click();
				break;
			}

		}
		Thread.sleep(2000);

		/*
		 * waitForElementToAppear(opt_WipesPack); opt_WipesPack.click();
		 */
		assertTrueElementDisplayed(txt_WipesPriceDisplay);
		waitForElementToAppear(txt_WipesPriceDisplay);
		assertTrueElementDisplayed(txt_WipeHeading_Change);
		String headingAfterSelection = txt_WipeHeading_Change.getText();
		try {
			assertNotEquals(headingBeforeSelection, headingAfterSelection);
			System.out.println("Assertion passed: Wipes Heading got changed as Expected based on the selection.");
		} catch (AssertionError e) {
			// Handle assertion failure
			System.err.println("Assertion failed: " + e.getMessage());
		}
	}

	public void saved_Cost() {
		waitForElementToAppear(txt_SavedLabel);
		waitForElementToAppear(txt_SavedLabel);
		waitForElementToAppear(txt_SavedCost);
		String savedLabel = txt_SavedLabel.getText();
		String savedPerYear = txt_SavedPerYear.getText();
		String savedCost = txt_SavedCost.getText();
		System.out.println(savedLabel + " is " + savedCost + " " + savedPerYear);
	}

	public void shipping_Cost() {
		waitForElementToAppear(txt_ShippingLabel);
		waitForElementToAppear(txt_ShippingCost);
		String shippingLabel = txt_ShippingLabel.getText();
		String shippingCost = txt_ShippingCost.getText();
		System.out.println(shippingLabel + " is " + shippingCost);
	}

	public void taxes_Cost() {
		waitForElementToAppear(txt_TaxesLabel);
		waitForElementToAppear(txt_TaxesCost);
		String taxesLabel = txt_TaxesLabel.getText();
		String taxCost = txt_TaxesCost.getText();
		System.out.println(taxesLabel + " is " + taxCost);
	}

	public void orderSubTotal_Cost() {
		waitForElementToAppear(txt_OrderSTCost);
		waitForElementToAppear(txt_OrderSTLabel);
		String orderSubTotal = txt_OrderSTCost.getText().replace("$", "");
		String orderSubTotalLabel = txt_OrderSTLabel.getText();
		System.out.println(orderSubTotalLabel + " is $ " + orderSubTotal);
	}

	public double orderSTVal() {
		waitForElementToAppear(txt_OrderSTCost);
		String orderSubTotal = txt_OrderSTCost.getText().replace("$", "");
		double orderST = Double.parseDouble(orderSubTotal);
		return orderST;
	}

	public InformationPageChrome landingPage_CheckOut() throws Exception {
		waitForElementToBeClickable(btn_CheckOut);
		assertTrueElementDisplayed(btn_CheckOut);
		scrollToElement(btn_CheckOut);
		btn_CheckOut.click();
		Thread.sleep(2000);
		InformationPageChrome infoPage = new InformationPageChrome(driver);
		return infoPage;
	}

	public void hamBurger_Home() throws Exception {
		waitForElementToBeClickable(menu_HamBurgerHome);
		Thread.sleep(2000);
		menu_HamBurgerHome.click();
		Thread.sleep(2000);
	}

	public LoginPageChrome userIcon() throws Exception {
		Thread.sleep(2000);
		icon_UserIcon.click();
		LoginPageChrome loginPage = new LoginPageChrome(driver);
		return loginPage;
	}

	public DiaperPageChrome nav_DiaperOpt() throws Exception {
		assertTrueElementDisplayed(navMenu_Diaper);
		Thread.sleep(2000);
		navMenu_Diaper.click();
		DiaperPageChrome diaperPage = new DiaperPageChrome(driver);
		return diaperPage;
	}

	public void titleVal(String actualPageTitle, String expectedPageTitle) {
		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("Landing Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("Landing Page, Actual Title doesn't Matches with the Expected Title");
		}
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);
		String expectedTitle = driver.getTitle().trim();
		return expectedTitle;
	}

	public DiaperPageChrome pageAssertAllDiaper() {
		assertAll();
		DiaperPageChrome diaperPage = new DiaperPageChrome(driver);
		return diaperPage;
	}

	public InformationPageChrome pageAssertAllInfo() {
		assertAll();
		InformationPageChrome infoPage = new InformationPageChrome(driver);
		return infoPage;
	}

	public LoginPageChrome pageAssertAllLogin() {
		assertAll();
		LoginPageChrome loginPage = new LoginPageChrome(driver);
		return loginPage;
	}

	public LandingPageChrome goTo(String loadingURL) {

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
		LandingPageChrome landingPage=new LandingPageChrome(driver);
		return landingPage;
	}

}