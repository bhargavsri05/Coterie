package coterie.pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import coterie.AbstractComponents.AbstractComponent;

public class InformationPageChrome extends AbstractComponent {
	WebDriver driver;

	public InformationPageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div/input[@id='checkout_email']")
	WebElement inp_Email;

	@FindBy(xpath = "//div/input[contains(@id,'address_first_name')]")
	WebElement inp_FirstName;

	@FindBy(xpath = "//div/input[contains(@id,'address_last_name')]")
	WebElement inp_LastName;

	@FindBy(xpath = "//div/input[contains(@id,'address_address1')]")
	WebElement inp_address1;

	@FindBy(xpath = "//div/input[contains(@id,'address_city')]")
	WebElement inp_City;

	@FindBy(xpath = "//div/select[contains(@id,'address_province')]")
	WebElement inp_Province;

	@FindBy(xpath = "//div/input[contains(@id,'address_zip')]")
	WebElement inp_ZipCode;

	@FindBy(xpath = "//div/input[contains(@id,'address_phone')]")
	WebElement inp_PhoneNum;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[1]/th/span)[1]")
	WebElement txt_FirstProduct_FirstHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[1]/th/span)[2]")
	WebElement txt_FirstProduct_SecondHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[1]/th/span)[3]")
	WebElement txt_FirstProduct_ThirdHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[1]/td//span)[1]")
	WebElement txt_FirstProduct_Quantity;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[1]/td//span)[3]")
	WebElement txt_FirstProduct_Price;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[2]/th/span)[1]")
	WebElement txt_SecondProduct_FirstHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[2]/th/span)[2]")
	WebElement txt_SecondProduct_SecondHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[2]/th/span)[3]")
	WebElement txt_SecondProduct_ThirdHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[2]/td//span)[1]")
	WebElement txt_SecondProduct_Quantity;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[2]/td//span)[3]")
	WebElement txt_SecondProduct_Price;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[3]/th/span)[1]")
	WebElement txt_ThirdProduct_FirstHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[3]/th/span)[2]")
	WebElement txt_ThirdProduct_SecondHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[3]/th/span)[3]")
	WebElement txt_ThirdProduct_ThirdHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[3]/td//span)[1]")
	WebElement txt_ThirdProduct_Quantity;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[3]/td//span)[3]")
	WebElement txt_ThirdProduct_Price;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[4]/th/span)[1]")
	WebElement txt_FourthProduct_FirstHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[4]/th/span)[2]")
	WebElement txt_FourthProduct_SecondHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[4]/th/span)[3]")
	WebElement txt_FourthProduct_ThirdHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[4]/td//span)[1]")
	WebElement txt_FourthProduct_Quantity;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[4]/td//span)[3]")
	WebElement txt_FourthProduct_Price;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[5]/th/span)[1]")
	WebElement txt_FifthProduct_FirstHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[5]/th/span)[2]")
	WebElement txt_FifthProduct_SecondHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[5]/th/span)[3]")
	WebElement txt_FifthProduct_ThirdHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[5]/td//span)[1]")
	WebElement txt_FifthProduct_Quantity;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[5]/td//span)[3]")
	WebElement txt_FifthProduct_Price;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[6]/th/span)[1]")
	WebElement txt_SixthProduct_FirstHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[6]/th/span)[2]")
	WebElement txt_SixthProduct_SecondHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[6]/th/span)[3]")
	WebElement txt_SixthProduct_ThirdHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[6]/td//span)[1]")
	WebElement txt_SixthProduct_Quantity;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[6]/td//span)[3]")
	WebElement txt_SixthProduct_Price;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[7]/th/span)[1]")
	WebElement txt_SeventhProduct_FirstHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[7]/th/span)[2]")
	WebElement txt_SeventhProduct_SecondHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[7]/th/span)[3]")
	WebElement txt_SeventhProduct_ThirdHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[7]/td//span)[1]")
	WebElement txt_SeventhProduct_Quantity;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[7]/td//span)[3]")
	WebElement txt_SeventhProduct_Price;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[8]/th/span)[1]")
	WebElement txt_EighthProduct_FirstHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[8]/th/span)[2]")
	WebElement txt_EighthProduct_SecondHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[8]/th/span)[3]")
	WebElement txt_EighthProduct_ThirdHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[8]/td//span)[1]")
	WebElement txt_EighthProduct_Quantity;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[8]/td//span)[3]")
	WebElement txt_EighthProduct_Price;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[9]/th/span)[1]")
	WebElement txt_NinethProduct_FirstHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[9]/th/span)[2]")
	WebElement txt_NinethProduct_SecondHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[9]/th/span)[3]")
	WebElement txt_NinethProduct_ThirdHeading;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[9]/td//span)[1]")
	WebElement txt_NinethProduct_Quantity;

	@FindBy(xpath = "(//div/table[contains(@class,'product')]/tbody/tr[9]/td//span)[3]")
	WebElement txt_NinethProduct_Price;

	@FindBy(xpath = "//div/table[contains(@class,'total-line')]/tbody/tr[1]/td/span")
	WebElement txt_InfoPage_SubTotal;

	@FindBy(xpath = "//div/table[contains(@class,'total-line')]/tbody/tr[2]/td/span")
	WebElement txt_InfoPage_Shipping;

	@FindBy(xpath = "//div/table[contains(@class,'total-line')]/tbody/tr[3]/td/span")
	WebElement txt_InfoPage_Taxes;

	@FindBy(xpath = "//div/table[contains(@class,'total-line')]/tfoot/tr[1]/td/span[2]")
	WebElement txt_InfoPage_Total;

	@FindBy(xpath = "//div[@class='step__footer']/button")
	WebElement btn_InfoPage_Continue;

	@FindBy(xpath = "//div[@id='myModal']//button[@id='btn-proceed-address']")
	WebElement btn_Proceed;

	By provinceBy = By.xpath("//div/select[contains(@id,'address_province')]");

	public void input_Email(String email) {
		inp_Email.sendKeys(email);
	}

	public void input_FirstName(String fName) {
		inp_FirstName.sendKeys(fName);
	}

	public void input_LastName(String lName) {
		inp_LastName.sendKeys(lName);
	}

	public void input_Address(String address) {
		inp_address1.sendKeys(address);
	}

	public void input_City(String city) {
		inp_City.sendKeys(city);
	}

	public void Select_Province(String province) throws Exception {
		
		Select state = new Select(inp_Province);
		state.selectByVisibleText(province);
	}

	public void input_Zip(String zipCode) {
		inp_ZipCode.sendKeys(zipCode);
	}

	public void input_PhoneNum(String phNum) {
		inp_PhoneNum.sendKeys(phNum);
	}

	public void first_Product_details() {
		waitForElementToAppear(txt_FirstProduct_FirstHeading);
		String firstHeading = txt_FirstProduct_FirstHeading.getText();
		waitForElementToBeClickable(txt_FirstProduct_SecondHeading);
		String secondHeading = txt_FirstProduct_SecondHeading.getText();
		waitForElementToBeClickable(txt_FirstProduct_ThirdHeading);
		String thirdHeading = txt_FirstProduct_ThirdHeading.getText();
		waitForElementToBeClickable(txt_FirstProduct_Quantity);
		String productQuantity = txt_FirstProduct_Quantity.getText();
		waitForElementToBeClickable(txt_FirstProduct_Price);
		String productPrice = txt_FirstProduct_Price.getText().replace("$", "");
		System.out.println("Details of product Selected: " + firstHeading + " " + secondHeading + " " + thirdHeading
				+ " " + productQuantity + " $ " + productPrice);
	}

	public void second_Product_details() {
		waitForElementToAppear(txt_SecondProduct_FirstHeading);
		String firstHeading = txt_SecondProduct_FirstHeading.getText();
		waitForElementToAppear(txt_SecondProduct_SecondHeading);
		String secondHeading = txt_SecondProduct_SecondHeading.getText();
		waitForElementToAppear(txt_SecondProduct_ThirdHeading);
		String thirdHeading = txt_SecondProduct_ThirdHeading.getText();
		waitForElementToAppear(txt_SecondProduct_Quantity);
		String productQuantity = txt_SecondProduct_Quantity.getText();
		waitForElementToAppear(txt_SecondProduct_Price);
		String productPrice = txt_SecondProduct_Price.getText().replace("$", "");
		System.out.println("Details of product Selected: " + firstHeading + " " + secondHeading + " " + thirdHeading
				+ " " + productQuantity + " $ " + productPrice);
	}

	public void third_Product_details() {
		waitForElementToAppear(txt_ThirdProduct_FirstHeading);
		String firstHeading = txt_ThirdProduct_FirstHeading.getText();
		waitForElementToAppear(txt_ThirdProduct_SecondHeading);
		String secondHeading = txt_ThirdProduct_SecondHeading.getText();
		waitForElementToAppear(txt_ThirdProduct_ThirdHeading);
		String thirdHeading = txt_ThirdProduct_ThirdHeading.getText();
		waitForElementToAppear(txt_ThirdProduct_Quantity);
		String productQuantity = txt_ThirdProduct_Quantity.getText();
		waitForElementToAppear(txt_ThirdProduct_Price);
		String productPrice = txt_ThirdProduct_Price.getText().replace("$", "");
		System.out.println("Details of product Selected: " + firstHeading + " " + secondHeading + " " + thirdHeading
				+ " " + productQuantity + " $ " + productPrice);
	}

	public void first_prod_detailsUsrScenario() {
		waitForElementToAppear(txt_FirstProduct_FirstHeading);
		String firstHeading = txt_FirstProduct_FirstHeading.getText();
		waitForElementToAppear(txt_FirstProduct_Quantity);
		String productQuantity = txt_FirstProduct_Quantity.getText();
		waitForElementToAppear(txt_FirstProduct_Price);
		String productPrice = txt_FirstProduct_Price.getText().replace("$", "");
		System.out
				.println("Details of product Selected: " + firstHeading + " " + productQuantity + " $ " + productPrice);
	}

	public void second_prod_detailsUsrScenario() {
		waitForElementToAppear(txt_SecondProduct_FirstHeading);
		String firstHeading = txt_SecondProduct_FirstHeading.getText();
		waitForElementToAppear(txt_SecondProduct_Quantity);
		String productQuantity = txt_SecondProduct_Quantity.getText();
		waitForElementToAppear(txt_SecondProduct_Price);
		String productPrice = txt_SecondProduct_Price.getText().replace("$", "");
		System.out
				.println("Details of product Selected: " + firstHeading + " " + productQuantity + " $ " + productPrice);
	}

	public void third_prod_detailsUsrScenario() {
		waitForElementToAppear(txt_ThirdProduct_FirstHeading);
		String firstHeading = txt_ThirdProduct_FirstHeading.getText();
		waitForElementToAppear(txt_ThirdProduct_SecondHeading);
		String secondHeading = txt_ThirdProduct_SecondHeading.getText();
		waitForElementToAppear(txt_ThirdProduct_Quantity);
		String productQuantity = txt_ThirdProduct_Quantity.getText();
		waitForElementToAppear(txt_ThirdProduct_Price);
		String productPrice = txt_ThirdProduct_Price.getText().replace("$", "");
		System.out.println("Details of product Selected: " + firstHeading + " " + secondHeading + " " + productQuantity
				+ " $ " + productPrice);
	}

	public void fourth_prod_detailsUsrScenario() {
		waitForElementToAppear(txt_FourthProduct_FirstHeading);
		String firstHeading = txt_FourthProduct_FirstHeading.getText();
		waitForElementToAppear(txt_FourthProduct_SecondHeading);
		String secondHeading = txt_FourthProduct_SecondHeading.getText();
		waitForElementToAppear(txt_FourthProduct_Quantity);
		String productQuantity = txt_FourthProduct_Quantity.getText();
		waitForElementToAppear(txt_FourthProduct_Price);
		String productPrice = txt_FourthProduct_Price.getText().replace("$", "");
		System.out.println("Details of product Selected: " + firstHeading + " " + secondHeading + " " + productQuantity
				+ " $ " + productPrice);
	}

	public void fifth_prod_detailsUsrScenario() {
		waitForElementToAppear(txt_FifthProduct_FirstHeading);
		String firstHeading = txt_FifthProduct_FirstHeading.getText();
		waitForElementToAppear(txt_FifthProduct_Quantity);
		String productQuantity = txt_FifthProduct_Quantity.getText();
		waitForElementToAppear(txt_FifthProduct_Price);
		String productPrice = txt_FifthProduct_Price.getText().replace("$", "");
		System.out
				.println("Details of product Selected: " + firstHeading + " " + productQuantity + " $ " + productPrice);
	}

	public void sixth_prod_detailsUsrScenario() {
		waitForElementToAppear(txt_SixthProduct_FirstHeading);
		String firstHeading = txt_SixthProduct_FirstHeading.getText();
		waitForElementToAppear(txt_SixthProduct_Quantity);
		String productQuantity = txt_SixthProduct_Quantity.getText();
		waitForElementToAppear(txt_SixthProduct_Price);
		String productPrice = txt_SixthProduct_Price.getText().replace("$", "");
		System.out
				.println("Details of product Selected: " + firstHeading + " " + productQuantity + " $ " + productPrice);
	}

	public void seventh_prod_detailsUsrScenario() {
		waitForElementToAppear(txt_SeventhProduct_FirstHeading);
		String firstHeading = txt_SeventhProduct_FirstHeading.getText();
		waitForElementToAppear(txt_SeventhProduct_SecondHeading);
		String secondHeading = txt_SeventhProduct_SecondHeading.getText();
		waitForElementToAppear(txt_SeventhProduct_Quantity);
		String productQuantity = txt_SeventhProduct_Quantity.getText();
		waitForElementToAppear(txt_SeventhProduct_Price);
		String productPrice = txt_SeventhProduct_Price.getText().replace("$", "");
		System.out.println("Details of product Selected: " + firstHeading + " " + secondHeading + " " + productQuantity
				+ " $ " + productPrice);
	}

	public void eighth_prod_detailsUsrScenario() {
		waitForElementToAppear(txt_EighthProduct_FirstHeading);
		String firstHeading = txt_EighthProduct_FirstHeading.getText();
		waitForElementToAppear(txt_EighthProduct_SecondHeading);
		String secondHeading = txt_EighthProduct_SecondHeading.getText();
		waitForElementToAppear(txt_EighthProduct_Quantity);
		String productQuantity = txt_EighthProduct_Quantity.getText();
		waitForElementToAppear(txt_EighthProduct_Price);
		String productPrice = txt_EighthProduct_Price.getText().replace("$", "");
		System.out.println("Details of product Selected: " + firstHeading + " " + secondHeading + " " + productQuantity
				+ " $ " + productPrice);
	}

	public void nineth_prod_detailsUsrScenario() {
		waitForElementToAppear(txt_NinethProduct_FirstHeading);
		String firstHeading = txt_NinethProduct_FirstHeading.getText();
		waitForElementToAppear(txt_NinethProduct_SecondHeading);
		String secondHeading = txt_NinethProduct_SecondHeading.getText();
		waitForElementToAppear(txt_NinethProduct_Quantity);
		String productQuantity = txt_NinethProduct_Quantity.getText();
		waitForElementToAppear(txt_NinethProduct_Price);
		String productPrice = txt_NinethProduct_Price.getText().replace("$", "");
		System.out.println("Details of product Selected: " + firstHeading + " " + secondHeading + " " + productQuantity
				+ " $ " + productPrice);
	}

	public double cmp_FirstProduct_PP() {
		waitForElementToAppear(txt_FirstProduct_Price);
		String textContent = txt_FirstProduct_Price.getText().replace("$", "");
		double doubleProdPrice = Double.parseDouble(textContent);
		return doubleProdPrice;
	}

	public double cmp_SecondProduct_PP() {
		waitForElementToAppear(txt_SecondProduct_Price);
		String textContent = txt_SecondProduct_Price.getText().replace("$", "");
		double doubleProdPrice = Double.parseDouble(textContent);
		return doubleProdPrice;
	}

	public double cmp_ThirdProduct_PP() {

		waitForElementToAppear(txt_ThirdProduct_Price);
		String textContent = txt_ThirdProduct_Price.getText().replace("$", "");
		double doubleProdPrice = Double.parseDouble(textContent);
		return doubleProdPrice;
	}

	public double cmp_FourthProduct_PP() {

		waitForElementToAppear(txt_FourthProduct_Price);
		String textContent = txt_FourthProduct_Price.getText().replace("$", "");
		double doubleProdPrice = Double.parseDouble(textContent);
		return doubleProdPrice;
	}

	public double cmp_FifthProduct_PP() {

		waitForElementToAppear(txt_FifthProduct_Price);
		String textContent = txt_FifthProduct_Price.getText().replace("$", "");
		double doubleProdPrice = Double.parseDouble(textContent);
		return doubleProdPrice;
	}

	public double cmp_SixthProduct_PP() {

		waitForElementToAppear(txt_SixthProduct_Price);
		String textContent = txt_SixthProduct_Price.getText().replace("$", "");
		double doubleProdPrice = Double.parseDouble(textContent);
		return doubleProdPrice;
	}

	public double cmp_SeventhProduct_PP() {

		waitForElementToAppear(txt_SeventhProduct_Price);
		String textContent = txt_SeventhProduct_Price.getText().replace("$", "");
		double doubleProdPrice = Double.parseDouble(textContent);
		return doubleProdPrice;
	}

	public double cmp_EighthProduct_PP() {

		waitForElementToAppear(txt_EighthProduct_Price);
		String textContent = txt_EighthProduct_Price.getText().replace("$", "");
		double doubleProdPrice = Double.parseDouble(textContent);
		return doubleProdPrice;
	}

	public double cmp_NinethProduct_PP() {

		waitForElementToAppear(txt_NinethProduct_Price);
		String textContent = txt_NinethProduct_Price.getText().replace("$", "");
		double doubleProdPrice = Double.parseDouble(textContent);
		return doubleProdPrice;
	}

	public double sum() {
		double sum;
		sum = cmp_FirstProduct_PP() + cmp_SecondProduct_PP() + cmp_ThirdProduct_PP();
		return sum;
	}

	public double sumUS() {
		double sum;
		sum = cmp_FirstProduct_PP() + cmp_SecondProduct_PP() + cmp_ThirdProduct_PP() + cmp_FourthProduct_PP()
				+ cmp_FifthProduct_PP() + cmp_SixthProduct_PP() + cmp_SeventhProduct_PP() + cmp_EighthProduct_PP()
				+ cmp_NinethProduct_PP();
		return sum;
	}

	public void priceDisplay() {
		waitForElementToAppear(txt_InfoPage_SubTotal);
		String subTotalPrice = txt_InfoPage_SubTotal.getText();
		System.out.println("Subtotal amount is " + subTotalPrice);
		waitForElementToAppear(txt_InfoPage_Shipping);
		String shippingPrice = txt_InfoPage_Shipping.getText();
		System.out.println("Shipping amount is " + shippingPrice);
		waitForElementToAppear(txt_InfoPage_Taxes);
		String taxesPrice = txt_InfoPage_Taxes.getText();
		System.out.println("Taxes are " + taxesPrice);
		waitForElementToAppear(txt_InfoPage_Total);
		String totalPrice = txt_InfoPage_Total.getText().replace("$", "");
		double calTotalPrice = Double.parseDouble(totalPrice);
		System.out.println("Total Price is $ " + calTotalPrice);
		// LandingPageChrome landingPage = new LandingPageChrome(driver);
		assertEquals(calTotalPrice, sum());
		// softAssert.assertEquals(calTotalPrice, sum(), landingPage.orderSTVal());
		if (calTotalPrice == sum()) {
			System.out.println("Calculated Total Price from formula in Information Page " + sum()
					+ " matches with the Price " + calTotalPrice + " displayed in the Information Page");
			/*
			 * if (calTotalPrice == landingPage.orderSTVal()) { System.out.
			 * println("Calculated Total Price from formula from Information Page " + sum()
			 * + " & Price displayed in Landing Page " + landingPage.orderSTVal() +
			 * " are same as the Price " + calTotalPrice +
			 * " displayed in the Information Page"); }else {
			 * System.out.println("Calculated Total Price from formula in Information Page "
			 * + sum() + " is only matches with the Price " + calTotalPrice +
			 * " displayed in the Information Page and not with the Total Price displayed in Landing Page "
			 * +landingPage.orderSTVal()); }
			 */
		} else {
			System.out.println("Calculated Total Price from formula in Information Page " + sum()
					+ " is not matching with the Price " + calTotalPrice + " displayed in the Information Page");
		}
	}

	public void priceDisplayUS() {
		waitForElementToAppear(txt_InfoPage_SubTotal);
		String subTotalPrice = txt_InfoPage_SubTotal.getText();
		System.out.println("Subtotal amount is " + subTotalPrice);
		waitForElementToAppear(txt_InfoPage_Shipping);
		String shippingPrice = txt_InfoPage_Shipping.getText();
		System.out.println("Shipping amount is " + shippingPrice);
		waitForElementToAppear(txt_InfoPage_Taxes);
		String taxesPrice = txt_InfoPage_Taxes.getText();
		System.out.println("Taxes are " + taxesPrice);
		waitForElementToAppear(txt_InfoPage_Total);
		String totalPrice = txt_InfoPage_Total.getText().replace("$", "");
		double calTotalPrice = Double.parseDouble(totalPrice);
		System.out.println("Total Price is $ " + calTotalPrice);
		// LandingPageChrome landingPage = new LandingPageChrome(driver);
		assertEquals(calTotalPrice, sumUS());
		// softAssert.assertEquals(calTotalPrice, sum(), landingPage.orderSTVal());
		if (calTotalPrice == sumUS()) {
			System.out.println("Calculated Total Price from formula in Information Page " + sumUS()
					+ " matches with the Price " + calTotalPrice + " displayed in the Information Page");
			/*
			 * if (calTotalPrice == landingPage.orderSTVal()) { System.out.
			 * println("Calculated Total Price from formula from Information Page " +
			 * sumUS() + " & Price displayed in Landing Page " + landingPage.orderSTVal() +
			 * " are same as the Price " + calTotalPrice +
			 * " displayed in the Information Page"); }else {
			 * System.out.println("Calculated Total Price from formula in Information Page "
			 * + sumUS() + " is only matches with the Price " + calTotalPrice +
			 * " displayed in the Information Page and not with the Total Price displayed in Landing Page "
			 * +landingPage.orderSTVal()); }
			 */
		} else {
			System.out.println("Calculated Total Price from formula in Information Page " + sumUS()
					+ " is not matching with the Price " + calTotalPrice + " displayed in the Information Page");
		}
	}

	public void continueInfo() throws Exception {
		assertTrueElementDisplayed(btn_InfoPage_Continue);
		scrollToElement(btn_InfoPage_Continue);
		Thread.sleep(2000);
		btn_InfoPage_Continue.click();

	}

	public ShippingPageChrome proceedBtn() throws Exception {
		waitForElementToAppear(btn_Proceed);
		assertTrueElementDisplayed(btn_Proceed);
		Thread.sleep(2000);
		btn_Proceed.click();

		ShippingPageChrome shippingPage = new ShippingPageChrome(driver);
		return shippingPage;
	}

	public void titleVal(String actualPageTitle, String expectedPageTitle) {
		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("Information Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("Information Page, Actual Title doesn't Matches with the Expected Title");
		}
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);

		String expectedTitle = driver.getTitle().trim();
		return expectedTitle;
	}

	public ShippingPageChrome pageAssertAll() {
		assertAll();
		ShippingPageChrome shippingPage = new ShippingPageChrome(driver);
		return shippingPage;
	}

}