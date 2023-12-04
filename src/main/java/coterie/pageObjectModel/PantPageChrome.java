package coterie.pageObjectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import coterie.AbstractComponents.AbstractComponent;

public class PantPageChrome extends AbstractComponent {
	WebDriver driver;

	public PantPageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='__next']/main/section/div/div[2]/form/div[2]/button[contains(@class,'select2')]")
	WebElement opt_ProductSize;

	@FindBy(xpath = "//ul[contains(@class,'select2')]/li[2]")
	WebElement opt_NoOfProd;
	
	@FindBy(xpath="//ul[contains(@class,'select2')]/li/div/span")
	List<WebElement> pant_Sizes;

	@FindBy(xpath = "//h3[contains(text(),'One-time')]")
	WebElement opt_OrderType_Price;

	@FindBy(xpath = "//div[contains(@class,'genericPdpHero')]/button[@type='submit']")
	WebElement btn_Submit;

	@FindBy(xpath = "//div[contains(@class,'funnelHeader')]/button")
	WebElement btn_CartClose;

	@FindBy(xpath = "//div[@id='__next']/div[2]/div/div[1]/div/button[@title='Hamburger']")
	WebElement op_menu_HamBurger;

	@FindBy(xpath = "//div[contains(@class,'sideNav')]/a[contains(@href,'wipe')]")
	WebElement navMenu_Wipe;

	public void titleVal(String actualPageTitle, String expectedPageTitle) {
		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("Pant Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("Pant Page, Actual Title doesn't Matches with the Expected Title");
		}
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);
		String expectedTitle = driver.getTitle().trim();
		return expectedTitle;
	}

	public void pantSizeSelect() throws Exception {
		scrollToElement(opt_ProductSize);
		assertTrueElementDisplayed(opt_ProductSize);
		waitForElementToBeClickable(opt_ProductSize);
		opt_ProductSize.click();
	}

	public void noOfPants(String pantSize) throws Exception {
		assertTrueElementDisplayed(opt_NoOfProd);
		Thread.sleep(2000);
		for (WebElement size : pant_Sizes) {

			if (size.getText().equalsIgnoreCase(pantSize)) {
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

	public void pant_Submit() throws Exception {
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

	public WipePageChrome nav_WipeOpt() throws Exception {
		assertTrueElementDisplayed(navMenu_Wipe);
		Thread.sleep(2000);
		navMenu_Wipe.click();
		WipePageChrome wipePage = new WipePageChrome(driver);
		return wipePage;
	}

	public WipePageChrome pageAssertAll() {
		assertAll();
		WipePageChrome wipePage = new WipePageChrome(driver);
		return wipePage;
	}
}