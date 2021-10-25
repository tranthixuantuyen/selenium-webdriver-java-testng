package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_Iframe_Frame {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

//	@Test
	public void TC_01_Iframe() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		
		//Switch to facebook iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='fb:page Facebook Social Plugin']")));
		
		//Element of facebook iframe
		String likeNumber = driver.findElement(By.xpath("//a[text()='Automation FC']/parent::div/following-sibling::div")).getText();
		System.out.println(likeNumber);
		
		//Switch to parent
		driver.switchTo().defaultContent();
		
		//Element of parent
		String postTitle =driver.findElement(By.xpath("//h1[@class='post-title']")).getText();
		System.out.println(postTitle);
		
		//Switch google doc iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
		driver.findElement(By.xpath("//div[contains(@data-params,'HỌ TÊN')]//input")).sendKeys("Automation FC");
		
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://v1.hdfcbank.com/assets/popuppages/netbanking.htm");
		driver.findElement(By.xpath("(//div[@class='container']/div/a[text()='Continue to NetBanking'])[1]")).click();
		
		//Switch to login frame
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.name("fldLoginUserId")).sendKeys("automation");
		driver.findElement(By.xpath("//a[contains(@onclick,'fLogon')]/img[@alt='continue']")).click();
		
		Assert.assertTrue(driver.findElement(By.name("fldPassword")).isDisplayed());
		
		//Switch to parent page
		driver.switchTo().defaultContent();
		
		//Switch to footer frame
		driver.switchTo().frame("footer");
		
		driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
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