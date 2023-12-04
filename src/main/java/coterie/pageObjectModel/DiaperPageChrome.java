package coterie.pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import coterie.AbstractComponents.AbstractComponent;

public class DiaperPageChrome extends AbstractComponent {
	WebDriver driver;

	public DiaperPageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='__next']/main/section/div/div[2]/form/div[2]/button[contains(@class,'select2')]")
	WebElement opt_ProductSize;

	@FindBy(xpath = "//div[contains(@class,'genericPdpHero')]/form/div/ul[contains(@class,'select2')]/li[2]")
	WebElement opt_NoOfProd;
	
	@FindBy(xpath="//div[contains(@class,'genericPdpHero')]/form/div/ul[contains(@class,'select2')]/li/div/span")
	List<WebElement> diaper_Sizes;

	@FindBy(xpath = "//h3[text()='One-time']")//div[@id='pdp-plan-options']/ul/li[2]/div/div
	WebElement opt_OrderType_Price;

	@FindBy(xpath = "//div[contains(@class,'genericPdpHero')]/button[@type='submit']")
	WebElement btn_Submit;

	@FindBy(xpath = "//div[contains(@class,'funnelHeader')]/button")
	WebElement btn_CartClose;

	@FindBy(xpath = "//div[@id='__next']/div[2]/div/div[1]/div/button[@title='Hamburger']")
	WebElement op_menu_HamBurger;

	@FindBy(xpath = "//div[contains(@class,'sideNav')]/a[contains(@href,'pant')]")
	WebElement navMenu_Pant;
	
	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[1]/div[2]")
	WebElement first_ProductDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[1]/div[3]/p")
	WebElement first_packDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[1]/div[4]/div/div[1]")
	WebElement first_orderTypeVal;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[1]/div[4]/div/div[2]")
	WebElement first_orderFrequency;
	
	@FindBy(xpath = "//div[contains(@class,'minicart_buttons')]/button[text()='Confirm Checkout']")
	WebElement btn_confirmCheckout;


	public void titleVal(String actualPageTitle, String expectedPageTitle) {
		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("Diaper Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("Diaper Page, Actual Title doesn't Matches with the Expected Title");
		}
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);
		String expectedTitle = driver.getTitle().trim();
		return expectedTitle;
	}

	public void diaperSizeSelect() throws Exception {
		scrollToElement(opt_ProductSize);
		assertTrueElementDisplayed(opt_ProductSize);
		waitForElementToBeClickable(opt_ProductSize);
		opt_ProductSize.click();
	}

	public void noOfDiapers(String diaperSize) throws Exception {
		assertTrueElementDisplayed(opt_NoOfProd);
		waitForElementToBeClickable(opt_NoOfProd);
		Thread.sleep(2000);
		for (WebElement size : diaper_Sizes) {

			if (size.getText().equalsIgnoreCase(diaperSize)) {
				waitForElementToBeClickable(size);
				size.click();
				break;
			}

		}
		Thread.sleep(2000);
	}

	public void orderPriceType() throws Exception {
		waitForElementToBeClickable(opt_OrderType_Price);
		assertTrueElementDisplayed(opt_OrderType_Price);
		opt_OrderType_Price.click();
	}

	public void diaper_Submit() throws Exception {
		moveToElement(btn_Submit);
		assertTrueElementDisplayed(btn_Submit);
		waitForElementToBeClickable(btn_Submit);
		btn_Submit.click();
	}

	public void closeCart() throws Exception {
		waitForElementToBeClickable(btn_CartClose);
		Thread.sleep(2000);
		btn_CartClose.click();
	}

	public void dp_HamBurgerOption() throws Exception {
		waitForElementToBeClickable(op_menu_HamBurger);
		if (op_menu_HamBurger.isEnabled() && op_menu_HamBurger.isDisplayed()) {
			Thread.sleep(2000);
			op_menu_HamBurger.click();
		}
	}

	public PantPageChrome nav_PantOpt() throws Exception {
		assertTrueElementDisplayed(navMenu_Pant);
		Thread.sleep(2000);
		navMenu_Pant.click();
		PantPageChrome pantPage = new PantPageChrome(driver);
		return pantPage;
	}
	
	public void first_ProductDetails() {
		waitForElementToAppear(first_ProductDetails);
		System.out.println(first_ProductDetails.getText());
		waitForElementToAppear(first_packDetails);
		System.out.println(first_packDetails.getText());
		waitForElementToAppear(first_orderTypeVal);
		System.out.println(first_orderTypeVal.getText());
		waitForElementToAppear(first_orderFrequency);
		System.out.println(first_orderFrequency.getText());

	}
	
	public InformationPageChrome confirmCheckout() throws Exception {
		waitForElementToAppear(btn_confirmCheckout);
		moveToElement(btn_confirmCheckout);
		btn_confirmCheckout.click();
		InformationPageChrome infoPage = new InformationPageChrome(driver);
		return infoPage;
	}

	public PantPageChrome pageAssertAll() {
		assertAll();
		PantPageChrome pantPage = new PantPageChrome(driver);
		return pantPage;
	}

}