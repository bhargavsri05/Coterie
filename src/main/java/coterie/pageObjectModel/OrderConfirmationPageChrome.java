package coterie.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import coterie.AbstractComponents.AbstractComponent;

public class OrderConfirmationPageChrome extends AbstractComponent {
	WebDriver driver;

	public OrderConfirmationPageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[@id='main-header']")
	WebElement txt_ThankMsg;

	@FindBy(xpath = "//div[@class='step']//div/span")
	WebElement txt_OrderID;

	public void titleVal(String actualPageTitle, String expectedPageTitle) {

		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("Order Confirmation Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("Order Confirmation Page, Actual Title doesn't Matches with the Expected Title");
		}
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);
		String expectedTitle = driver.getTitle().trim();
		return expectedTitle;
	}

	public LandingPageChrome orderConf() {
		assertTrueElementDisplayed(txt_ThankMsg);
		assertTrueElementDisplayed(txt_OrderID);

		System.out.println(txt_ThankMsg.getText() + " : " + txt_OrderID.getText());
		LandingPageChrome landingPage= new LandingPageChrome(driver);
		return landingPage;
	}
	
	public void pageAssertAll() {
		assertAll();
	}

}