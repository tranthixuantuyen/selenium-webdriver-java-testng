package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Brower_Method_Excercise {
	WebDriver driver;
	By emailTextbox = By.id("mail");
	By ageUnderRadio = By.id("under_18");
	By educationTextArea = By.id("edu");
	By nameUser5Text = By.xpath("//h5[contains(text(),'Name: User5')]");
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_Displayed() {
		
		Assert.assertTrue(isElementDisplayed(emailTextbox));
		inputToElement(emailTextbox, "Automation Testing");
		
		// education
		Assert.assertTrue(isElementDisplayed(educationTextArea));
		inputToElement(emailTextbox, "Automation Testing");
		
		// Age
		if (isElementDisplayed(ageUnderRadio)) {
			clickElement(educationTextArea);
		}
		// Name user 5
		Assert.assertFalse(isElementDisplayed(nameUser5Text));
	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public boolean isElementDisplayed (By by) {
		if (driver.findElement(by).isDisplayed())
		{
			System.out.println(by + " - is displayed" );
			return true;
		}else {
			System.out.println(by + " - is un-displayed");
			return false;
		}
	}
	
	public void clickElement(By by) {
		driver.findElement(by).click();
	}
	
	public void inputToElement(By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	

	public void sleepInSecond(long Time) {
		try {
			Thread.sleep(Time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}