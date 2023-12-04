package coterie.pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import coterie.AbstractComponents.AbstractComponent;

public class LoginPageChrome extends AbstractComponent {
	WebDriver driver;

	public LoginPageChrome(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//main/div//input[@name='email']")
	WebElement txtbx_login_email;

	@FindBy(xpath = "//main/div//button[@type='submit']")
	WebElement btn_login_Next;

	@FindBy(xpath = "//main/div//input[@name='password']")
	WebElement txtbx_login_password;

	@FindBy(xpath = "//main/div//button[@type='submit']")
	WebElement btn_login_Submit;

	public void titleVal(String actualPageTitle, String expectedPageTitle) {
		waitforTitleIs(actualPageTitle);
		assertEquals(actualPageTitle, expectedPageTitle);
		if (actualPageTitle.equalsIgnoreCase(expectedPageTitle)) {
			System.out.println("LogIn Page, Actual Title Matches with the Expected Title");
		} else {
			System.out.println("LogIn Page, Actual Title doesn't Matches with the Expected Title");
		}
	}

	public String getTitle(String actualPageTitle) {
		waitforTitleIs(actualPageTitle);
		String expectedTitle = driver.getTitle().trim();
		return expectedTitle;
	}

	public void userName(String email) throws Exception {
		waitForElementToAppear(txtbx_login_email);
		Thread.sleep(2000);
		txtbx_login_email.sendKeys(email);
	}

	public void passWord(String psWd) throws Exception {
		waitForElementToAppear(txtbx_login_password);
		Thread.sleep(2000);
		txtbx_login_password.sendKeys(psWd);
	}

	public void nextBtn() throws Exception {
		assertTrueElementDisplayed(btn_login_Next);
		Thread.sleep(2000);
		btn_login_Next.click();
		Thread.sleep(2000);
	}

	public UserDashboardPageChrome submitBtn() throws Exception {
		assertTrueElementDisplayed(btn_login_Submit);
		Thread.sleep(2000);
		btn_login_Submit.click();
		Thread.sleep(2000);
		UserDashboardPageChrome userDBPage = new UserDashboardPageChrome(driver);
		return userDBPage;
	}

	public UserDashboardPageChrome pageAssertAll() {
		assertAll();
		UserDashboardPageChrome userDBPage = new UserDashboardPageChrome(driver);
		return userDBPage;
	}

}