package coterie.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import coterie.AbstractComponents.AbstractComponent;

//PaymentPageChrome

public class PaymentPageChrome extends AbstractComponent {
	WebDriver driver;

	public PaymentPageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div/button[@id='continue_button']")
	WebElement btn_ShippmentPage_Continue;

	@FindBy(xpath = "//div[contains(@class,'payment-method')]//div/iframe[contains(@title,'Card number')]")
	WebElement frame_cardNum;

	@FindBy(xpath = "//input[@id='number'][1]")
	WebElement cardNum;

	@FindBy(xpath = "//div[contains(@class,'payment-method')]//div/iframe[contains(@title,'Name on card')]")
	WebElement frame_cardName;

	@FindBy(xpath = "//input[@id='name'][1]")
	WebElement cardName;

	@FindBy(xpath = "//div[contains(@class,'payment-method')]//div/iframe[contains(@title,'Expiration date')]")
	WebElement frame_cardExpDate;

	@FindBy(xpath = "//input[@id='expiry'][1]")
	WebElement cardED;

	@FindBy(xpath = "//div[contains(@class,'payment-method')]//div/iframe[contains(@title,'Security code')]")
	WebElement frame_SecCode;

	@FindBy(xpath = "//input[@id='verification_value'][1]")
	WebElement cardSec;

	@FindBy(xpath = "//form/div[3]/div/div/label[contains(@for,'agreement')]")
	WebElement chkbx_SubscriptionAgreement;

	@FindBy(xpath = "//form/div[contains(@class,'footer')]/div[contains(@class,'shown')]/button[@id='continue_button']")
	WebElement btn_PayNow;

	public void switchToFrame(WebElement ele) {
		driver.switchTo().frame(ele);
	}

	public void switchToParentFrame() {
		driver.switchTo().parentFrame();
	}

	public void ccNUm(String creditCarNum) {
		switchToFrame(frame_cardNum);
		cardNum.clear();
		for (char ch : creditCarNum.toCharArray()) {
			switch (Integer.parseInt(String.valueOf(ch))) {
			case 1:
				cardNum.sendKeys(Keys.NUMPAD1);
				break;
			case 2:
				cardNum.sendKeys(Keys.NUMPAD2);
				break;
			case 3:
				cardNum.sendKeys(Keys.NUMPAD3);
				break;
			case 4:
				cardNum.sendKeys(Keys.NUMPAD4);
				break;
			case 5:
				cardNum.sendKeys(Keys.NUMPAD5);
				break;
			case 6:
				cardNum.sendKeys(Keys.NUMPAD6);
				break;
			case 7:
				cardNum.sendKeys(Keys.NUMPAD7);
				break;
			case 8:
				cardNum.sendKeys(Keys.NUMPAD8);
				break;
			case 9:
				cardNum.sendKeys(Keys.NUMPAD9);
				break;
			}

		}
		switchToParentFrame();
	}

	public void ccName(String creditCardName) {
		switchToFrame(frame_cardName);
		cardName.clear();
		cardName.sendKeys(creditCardName);
		switchToParentFrame();
	}

	public void cardExpiry(String expiryDate) {
		switchToFrame(frame_cardExpDate);
		cardED.clear();
		for (char ch : expiryDate.toCharArray()) {
			switch (Integer.parseInt(String.valueOf(ch))) {
			case 1:
				cardED.sendKeys(Keys.NUMPAD1);
				break;
			case 2:
				cardED.sendKeys(Keys.NUMPAD2);
				break;
			case 3:
				cardED.sendKeys(Keys.NUMPAD3);
				break;
			case 4:
				cardED.sendKeys(Keys.NUMPAD4);
			case 5:
				cardED.sendKeys(Keys.NUMPAD5);
			}
		}
		switchToParentFrame();
	}

	public void cardSec(String secNum) {
		switchToFrame(frame_SecCode);
		cardSec.clear();
		cardSec.sendKeys(secNum);
		switchToParentFrame();
	}

	public void subCheckBox() throws Exception {
		assertTrueElementDisplayed(chkbx_SubscriptionAgreement);
		waitForElementToBeClickable(chkbx_SubscriptionAgreement);
		scrollToElement(chkbx_SubscriptionAgreement);
		chkbx_SubscriptionAgreement.click();

	}

	public OrderConfirmationPageChrome payNow() throws Exception {
		assertTrueElementDisplayed(btn_PayNow);
		waitForElementToBeClickable(btn_PayNow);
		scrollToElement(btn_PayNow);
		btn_PayNow.click();
		OrderConfirmationPageChrome ocPage = new OrderConfirmationPageChrome(driver);
		return ocPage;
	}

	public void titleVal(String actualPageTitle, String expectedPageTitle) {
		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("Payment Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("Payment Page, Actual Title doesn't Matches with the Expected Title");
		}
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);
		String expectedTitle = driver.getTitle().trim();
		System.out.println(expectedTitle);
		return expectedTitle;
	}

	public OrderConfirmationPageChrome pageAssertAll() {
		assertAll();
		OrderConfirmationPageChrome ocPage = new OrderConfirmationPageChrome(driver);
		return ocPage;
	}

}