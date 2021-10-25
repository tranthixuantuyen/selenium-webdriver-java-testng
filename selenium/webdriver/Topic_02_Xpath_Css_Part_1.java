package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_Part_1 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
	}

	@Test
	public void TC_01_ID() {
		//Để thao tác vs 1 element ở trên page
		driver.findElement(By.id("FirstName")).sendKeys("Automation Testing");
		driver.findElement(By.id("Email")).sendKeys("automationtest@gmail.com");
	}

	@Test
	public void TC_02_Classname() {
		driver.findElement(By.className("search-box-text")).sendKeys("Macbook");
	}

	@Test
	public void TC_03_Name() {
		driver.findElement(By.name("Company")).sendKeys("Selenium");
	}
	@Test
	public void TC_04_Tagname() {
		// Dùng khi đếm số lượng elements trên màn hình
		//findElement = 1 element - số ít
		//findElements = nhiều element - số nhiều
		System.out.println(driver.findElements(By.tagName("select")).size() );
	}
	@Test
	public void TC_05_Link_Text() {
		Assert.assertTrue(driver.findElement(By.linkText("Register")).isDisplayed());
	}
	public void TC_06_Partial_Link_Text() {
		Assert.assertTrue(driver.findElement(By.partialLinkText("Digital")).isDisplayed());
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}