package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_29_Wait_V_Explicit {
	WebDriver driver;
	WebDriverWait explicitwait;
	String projectLocaion = System.getProperty("user.dir");
	By startButton = By.cssSelector("#start>button");
	By loadingIcon = By.cssSelector("div#loading"); 
	By helloworldText = By.xpath("//h4[text()='Hello World!']");

	@BeforeClass
	public void beforeClass() {
		// Firefox 48 trở lên + Selenium 3xx + Dùng geckodriver
		System.setProperty("webdriver.gecko.driver", projectLocaion + "\\selenium\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		explicitwait = new WebDriverWait(driver, 10);
	}

//	@Test
	public void TC_01_Visiable() {
		driver.manage().window().maximize();
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(startButton).click();
		
		// Chờ cho hello world text được hiển thị (visiable)
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(helloworldText));
		
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());	
	}

//	@Test
	public void TC_02_Invisiable() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(startButton).click();
		
		// Chờ cho loading icon biến mất (invisiable)
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		
		Assert.assertTrue(driver.findElement(helloworldText).isDisplayed());	

	}
	
	@Test
	public void TC_03_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//Wait cho Date Picker hiển thị
		explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("ctl00_ContentPlaceholder1_RadCalendar1")));
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		
		//Click vào ngày hiện tại
		explicitwait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='27']")));
		driver.findElement(By.xpath("//td/a[text()='27']")).click();
		
		//Wait cho ajax loading biến mất
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		//Verify ngày đã chọn được selected
		explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[@class='rcSelected']/a[text()='27']")));
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='27']")).isDisplayed());
		
		//Verify today được update lên 'selected dates'
		Assert.assertEquals(driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1")).getText(),"Sunday, June 27, 2021");
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