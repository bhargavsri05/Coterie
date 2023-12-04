package coterie.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import coterie.AbstractComponents.AbstractComponent;

public class ArtPrintPageChrome extends AbstractComponent {
	WebDriver driver;

	public ArtPrintPageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'genericPdpHero')]/button[@type='submit']")
	WebElement btn_Submit;

	@FindBy(xpath = "//div[contains(@class,'sideNav')]/a[contains(@href,'merch')]")
	WebElement navMenu_Merch;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[1]/div[2]")
	WebElement first_ProductDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[1]/div[3]/p")
	WebElement first_packDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[1]/div[4]/div/div[1]")
	WebElement first_orderTypeVal;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[1]/div[4]/div/div[2]")
	WebElement first_orderFrequency;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[2]/div[2]")
	WebElement second_ProductDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[2]/div[3]/p")
	WebElement second_packDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[2]/div[4]/div/div[1]")
	WebElement second_orderTypeVal;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[2]/div[4]/div/div[2]")
	WebElement second_orderFrequency;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[3]/div[2]")
	WebElement third_ProductDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[3]/div[3]/p")
	WebElement third_packDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[3]/div[4]/div/div[1]")
	WebElement third_orderTypeVal;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[3]/div[4]/div/div[2]")
	WebElement third_orderFrequency;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[1]/div[2]")
	WebElement fourth_ProductDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[4]/div[4]/div/div[1]")
	WebElement fourth_orderTypeVal;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[4]/div[4]/div/div[2]")
	WebElement fourth_orderFrequency;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[5]/div[2]")
	WebElement fifth_ProductDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[5]/div[4]/div/div[1]")
	WebElement fifth_orderTypeVal;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[5]/div[4]/div/div[2]")
	WebElement fifth_orderFrequency;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[6]/div[2]")
	WebElement sixth_ProductDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[6]/div[4]/div/div[1]")
	WebElement sixth_orderTypeVal;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[6]/div[4]/div/div[2]")
	WebElement sixth_orderFrequency;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[7]/div[2]")
	WebElement seventh_ProductDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[7]/div[4]/div/div[1]")
	WebElement seventh_orderTypeVal;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[7]/div[4]/div/div[2]")
	WebElement seventh_orderFrequency;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[8]/div[2]")
	WebElement eighth_ProductDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[8]/div[4]/div/div[1]")
	WebElement eighth_orderTypeVal;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[8]/div[4]/div/div[2]")
	WebElement eighth_orderFrequency;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[9]/div[2]")
	WebElement nineth_ProductDetails;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[9]/div[4]/div/div[1]")
	WebElement nineth_orderTypeVal;

	@FindBy(xpath = "//div[contains(@class,'pdpContainer')]/div[9]/div[4]/div/div[2]")
	WebElement nineth_orderFrequency;

	@FindBy(xpath = "//aside/div[2]/div[4]/div[2]/ul/li[1]/div[1]")
	WebElement shippingLabel;

	@FindBy(xpath = "//aside/div[2]/div[4]/div[2]/ul/li[1]/div[2]")
	WebElement shippingPrice;

	@FindBy(xpath = "//aside/div[2]/div[4]/div[2]/ul/li[2]/div[1]")
	WebElement taxesLabel;

	@FindBy(xpath = "//aside/div[2]/div[4]/div[2]/ul/li[2]/div[2]")
	WebElement taxesPrice;

	@FindBy(xpath = "//aside/div[2]/div[4]/div[2]/ul/li[3]/div[1]")
	WebElement orderSTLabel;

	@FindBy(xpath = "//aside/div[2]/div[4]/div[2]/ul/li[3]/div[2]")
	WebElement orderSTPrice;

	@FindBy(xpath = "//div[contains(@class,'minicart_buttons')]/button[text()='Confirm Checkout']")
	WebElement btn_confirmCheckout;

	public void titleVal(String actualPageTitle, String expectedPageTitle) {
		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("Art Print Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("Art Print Page, Actual Title doesn't Matches with the Expected Title");
		}
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);
		String expectedTitle = driver.getTitle().trim();
		return expectedTitle;
	}

	public void artPrint_Submit() throws Exception {
		moveToElement(btn_Submit);
		waitForElementToBeClickable(btn_Submit);
		assertTrueElementDisplayed(btn_Submit);
		btn_Submit.click();

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

	public void second_ProductDetails() {
		waitForElementToAppear(second_ProductDetails);
		System.out.println(second_ProductDetails.getText());
		waitForElementToAppear(second_packDetails);
		System.out.println(second_packDetails.getText());
		waitForElementToAppear(second_orderTypeVal);
		System.out.println(second_orderTypeVal.getText());
		waitForElementToAppear(second_orderFrequency);
		System.out.println(second_orderFrequency.getText());
	}

	public void third_ProductDetails() {
		waitForElementToAppear(third_ProductDetails);
		System.out.println(third_ProductDetails.getText());
		waitForElementToAppear(third_packDetails);
		System.out.println(third_packDetails.getText());
		waitForElementToAppear(third_orderTypeVal);
		System.out.println(third_orderTypeVal.getText());
		waitForElementToAppear(third_orderFrequency);
		System.out.println(third_orderFrequency.getText());
	}

	public void fourth_ProductDetails() {

		waitForElementToAppear(fourth_ProductDetails);
		System.out.println(fourth_ProductDetails.getText());
		waitForElementToAppear(fourth_orderTypeVal);
		System.out.println(fourth_orderTypeVal.getText());
		waitForElementToAppear(fourth_orderFrequency);
		System.out.println(fourth_orderFrequency.getText());
	}

	public void fifth_ProductDetails() {
		waitForElementToAppear(fifth_ProductDetails);
		System.out.println(fifth_ProductDetails.getText());
		waitForElementToAppear(fifth_orderTypeVal);
		System.out.println(fifth_orderTypeVal.getText());
		waitForElementToAppear(fifth_orderFrequency);
		System.out.println(fifth_orderFrequency.getText());
	}

	public void sixth_ProductDetails() {
		waitForElementToAppear(sixth_ProductDetails);
		System.out.println(sixth_ProductDetails.getText());
		waitForElementToAppear(sixth_orderTypeVal);
		System.out.println(sixth_orderTypeVal.getText());
		waitForElementToAppear(sixth_orderFrequency);
		System.out.println(sixth_orderFrequency.getText());
	}

	public void seventh_ProductDetails() {
		waitForElementToAppear(seventh_ProductDetails);
		System.out.println(seventh_ProductDetails.getText());
		waitForElementToAppear(seventh_orderTypeVal);
		System.out.println(seventh_orderTypeVal.getText());
		waitForElementToAppear(seventh_orderFrequency);
		System.out.println(seventh_orderFrequency.getText());
	}

	public void eighth_ProductDetails() {
		waitForElementToAppear(eighth_ProductDetails);
		System.out.println(eighth_ProductDetails.getText());
		waitForElementToAppear(eighth_orderTypeVal);
		System.out.println(eighth_orderTypeVal.getText());
		waitForElementToAppear(eighth_orderFrequency);
		System.out.println(eighth_orderFrequency.getText());
	}

	public void nineth_ProductDetails() {
		waitForElementToAppear(nineth_ProductDetails);
		System.out.println(nineth_ProductDetails.getText());
		waitForElementToAppear(nineth_orderTypeVal);
		System.out.println(nineth_orderTypeVal.getText());
		waitForElementToAppear(nineth_orderFrequency);
		System.out.println(nineth_orderFrequency.getText());
	}

	public void shipping_Details() {
		waitForElementToAppear(shippingLabel);
		waitForElementToAppear(shippingPrice);
		System.out.println(shippingLabel.getText() + " : " + shippingPrice.getText());
	}

	public void taxes_Details() {
		waitForElementToAppear(taxesLabel);
		waitForElementToAppear(taxesPrice);
		System.out.println(taxesLabel.getText() + " : " + taxesPrice.getText());
	}

	public void orderST_Details() {
		waitForElementToAppear(orderSTLabel);
		waitForElementToAppear(orderSTPrice);
		System.out.println(orderSTLabel.getText() + " : " + orderSTPrice.getText());
	}

	public InformationPageChrome confirmCheckout() throws Exception {
		waitForElementToAppear(btn_confirmCheckout);
		moveToElement(btn_confirmCheckout);
		btn_confirmCheckout.click();
		InformationPageChrome infoPage = new InformationPageChrome(driver);
		return infoPage;
	}

	public InformationPageChrome pageAssertAll() {
		assertAll();
		InformationPageChrome infoPage = new InformationPageChrome(driver);
		return infoPage;
	}

}