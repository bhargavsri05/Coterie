package coterie.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import coterie.AbstractComponents.AbstractComponent;

public class ShippingPageChrome extends AbstractComponent {
	WebDriver driver;

	public ShippingPageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div/button[@id='continue_button']")
	WebElement btn_ShippmentPage_Continue;

	public void titleVal(String actualPageTitle, String expectedPageTitle) {

		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("Shipping Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("Shipping Page, Actual Title doesn't Matches with the Expected Title");
		}
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);
		String expectedTitle = driver.getTitle().trim();
		return expectedTitle;
	}

	public PaymentPageChrome shippingPageContinue() throws Exception {
		assertTrueElementDisplayed(btn_ShippmentPage_Continue);
		scrollToElement(btn_ShippmentPage_Continue);
		Thread.sleep(2000);
		btn_ShippmentPage_Continue.click();
		PaymentPageChrome paymentPage = new PaymentPageChrome(driver);
		return paymentPage;
	}

	public PaymentPageChrome pageAssertAll() {
		assertAll();
		PaymentPageChrome paymentPage = new PaymentPageChrome(driver);
		return paymentPage;
	}

}