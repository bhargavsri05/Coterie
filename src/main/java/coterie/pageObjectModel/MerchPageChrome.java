package coterie.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import coterie.AbstractComponents.AbstractComponent;

public class MerchPageChrome extends AbstractComponent {
	WebDriver driver;

	public MerchPageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[contains(@href,'cap')]//div[text()='Shop Now']")
	WebElement merch_ClassicCap;

	@FindBy(xpath = "//a[contains(@href,'neck')]//div[text()='Shop Now']")
	WebElement merch_CrewNeck;

	@FindBy(xpath = "//a[contains(@href,'tote') and text()='Shop Now']")
	WebElement merch_ToteSet;

	@FindBy(xpath = "//a[contains(@href,'art')]//div[text()='Shop Now']")
	WebElement merch_ArtPrint;

	@FindBy(xpath = "//div[@aria-hidden='false' and contains(@class,'header')]//button[@title='Hamburger']")
	WebElement op_menu_HamBurger;

	public void titleVal(String actualPageTitle, String expectedPageTitle) {
		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("Merch Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("Merch Page, Actual Title doesn't Matches with the Expected Title");
		}
		scrollToElement(merch_ClassicCap);
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);
		String expectedTitle = driver.getTitle().trim();
		return expectedTitle;
	}

	public ClassicCapPageChrome merch_CC_Select() throws Exception {
		assertTrueElementDisplayed(merch_ClassicCap);
		scrollToElement(merch_ClassicCap);
		merch_ClassicCap.click();
		Thread.sleep(2000);
		ClassicCapPageChrome ccPage = new ClassicCapPageChrome(driver);
		return ccPage;
	}

	public CrewNeckPageChrome merch_CN_Select() throws Exception {
		assertTrueElementDisplayed(merch_CrewNeck);
		scrollToElement(merch_CrewNeck);
		merch_CrewNeck.click();
		Thread.sleep(2000);
		CrewNeckPageChrome cnPage = new CrewNeckPageChrome(driver);
		return cnPage;
	}

	public TotePageChrome merch_Tode_Select() throws Exception {
		assertTrueElementDisplayed(merch_ToteSet);
		scrollToElement(merch_ToteSet);
		merch_ToteSet.click();
		Thread.sleep(2000);
		TotePageChrome totePage = new TotePageChrome(driver);
		return totePage;
	}

	public ArtPrintPageChrome merch_AP_Select() throws Exception {
		assertTrueElementDisplayed(merch_ArtPrint);
		scrollToElement(merch_ArtPrint);
		merch_ArtPrint.click();
		Thread.sleep(2000);
		ArtPrintPageChrome apPage = new ArtPrintPageChrome(driver);
		return apPage;
	}

	public ClassicCapPageChrome pageAssertAll() {
		assertAll();
		ClassicCapPageChrome ccPage = new ClassicCapPageChrome(driver);
		return ccPage;
	}

}