package webdriver;

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

public class Topic_29_Wait_III_Implicit {
	WebDriver driver;
	String projectLocaion = System.getProperty("user.dir");
	By startButton = By.cssSelector("#start>button");
	By loadingIcon = By.cssSelector("div#loading"); 
	By helloworldText = By.xpath("//h4[text()='Hello World!']");

	@BeforeClass
	public void beforeClass() {
		// Firefox 48 trở lên + Selenium 3xx + Dùng geckodriver
		System.setProperty("webdriver.gecko.driver", projectLocaion + "\\selenium\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01_Dont_Set() {
		driver.manage().window().maximize();
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());	
	}

	@Test
	public void TC_02_Set_2s() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.findElement(startButton).click();
		
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());	

	}
	
	@Test
	public void TC_03_Set_6s() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		driver.findElement(startButton).click();
		
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());	

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