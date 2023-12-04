package coterie.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class AbstractComponent {

	WebDriver driver;
	WebDriverWait wait;
	public SoftAssert softAssert;
	Actions mouseAction;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		this.softAssert = new SoftAssert();
		this.mouseAction = new Actions(driver);
	}

	public void waitForElementToAppear(By findBy) {

		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForPresenceOfElement(By findBy) {

		wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
	}

	public void waitForElementToAppear(WebElement ele) {

		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForElementToBeClickable(WebElement ele) {
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitForFrameToPresent(WebElement ele) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
	}

	public void waitForElementToBeSelected(WebElement ele) {
		wait.until(ExpectedConditions.elementToBeSelected(ele));
	}

	public void assertAll() {
		softAssert.assertAll();
	}

	public boolean assertTrueElementDisplayed(WebElement ele) {
		waitForElementToAppear(ele);
		boolean b = ele.isDisplayed();
		softAssert.assertTrue(b);
		System.out.println(ele.getText() + " is Displayed");
		return b;
	}

	public boolean assertTrueElementEnabled(WebElement ele) {
		waitForElementToAppear(ele);
		boolean b = ele.isEnabled();
		softAssert.assertTrue(b);
		System.out.println(ele.getText() + " is Enabled");
		return b;
	}

	public boolean assertTrueElementSelected(WebElement ele) {
		waitForElementToAppear(ele);
		boolean b = ele.isSelected();
		softAssert.assertTrue(b);
		System.out.println(ele.getText() + " is Selected");
		return b;
	}

	public void assertEquals(String a, String b) {
		softAssert.assertEquals(a, b);
	}

	public void assertEquals(double a, double b) {
		softAssert.assertEquals(a, b);
	}

	public void assertTrue(boolean a) {
		softAssert.assertTrue(a);
	}

	public void assertFalse(boolean a) {
		softAssert.assertFalse(a);
	}

	public void assertNotEquals(String a, String b) {
		softAssert.assertNotEquals(a, b);
	}

	public void waitforTitleIs(String value) {
		wait.until(ExpectedConditions.titleIs(value));

	}

	public void scrollToElement(WebElement ele) {
		waitForElementToAppear(ele);
		mouseAction.scrollToElement(ele).perform();
	}

	public void moveToElement(WebElement ele) {
		waitForElementToAppear(ele);
		mouseAction.moveToElement(ele).perform();
	}
}