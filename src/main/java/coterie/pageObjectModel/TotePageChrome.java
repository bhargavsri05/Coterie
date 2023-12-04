package coterie.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import coterie.AbstractComponents.AbstractComponent;

public class TotePageChrome extends AbstractComponent {
	WebDriver driver;

	public TotePageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'genericPdpHero')]/button[@type='submit']")
	WebElement btn_Submit;

	@FindBy(xpath = "//div[contains(@class,'funnelHeader')]/button")
	WebElement btn_CartClose;

	@FindBy(xpath = "//div[@aria-hidden='false' and contains(@class,'header')]//button[@title='Hamburger']")
	WebElement op_menu_HamBurger;

	@FindBy(xpath = "//div[contains(@class,'sideNav')]/a[contains(@href,'merch')]")
	WebElement navMenu_Merch;

	public void titleVal(String actualPageTitle, String expectedPageTitle) {
		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("Tote Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("Tote Page, Actual Title doesn't Matches with the Expected Title");
		}
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);
		String expectedTitle = driver.getTitle().trim();
		return expectedTitle;
	}

	public void tote_Submit() throws Exception {
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

	public MerchPageChrome nav_merchOpt() throws Exception {
		waitForElementToBeClickable(navMenu_Merch);
		Thread.sleep(2000);
		navMenu_Merch.click();
		MerchPageChrome merchPage = new MerchPageChrome(driver);
		return merchPage;
	}

	public MerchPageChrome pageAssertAll() {
		assertAll();
		MerchPageChrome merchPage = new MerchPageChrome(driver);
		return merchPage;
	}
}