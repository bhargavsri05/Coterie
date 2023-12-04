package coterie.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import coterie.AbstractComponents.AbstractComponent;

public class UserDashboardPageChrome extends AbstractComponent {
	WebDriver driver;

	public UserDashboardPageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@id='__next']/div[2]/div/div[1]/div/button[@title='Hamburger']")
	WebElement op_menu_HamBurger;

	@FindBy(xpath = "//div[contains(@class, 'header') and @aria-hidden='false']//nav/a[@title='Coterie Logo']")
	WebElement coterieHPLink;

	public LandingPageChrome HomePagenav() throws Exception {
		waitForElementTobeClicked(coterieHPLink);
		coterieHPLink.click();
		LandingPageChrome landingPage = new LandingPageChrome(driver);
		return landingPage;
	}

	private void waitForElementTobeClicked(WebElement coterieHPLink2) {
		// TODO Auto-generated method stub
		
	}

	public void titleVal(String actualPageTitle, String expectedPageTitle) {
		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("User DashBoard Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("User DashBoard Page, Actual Title doesn't Matches with the Expected Title");
		}
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);
		String expectedTitle = driver.getTitle().trim();
		return expectedTitle;
	}

	public LandingPageChrome pageAssertAll() {
		assertAll();
		LandingPageChrome landingPage = new LandingPageChrome(driver);
		return landingPage;
	}
}