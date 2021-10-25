package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_User_Interaction_Part_I {
	WebDriver driver;
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\selenium\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	//@Test
	public void TC_01_Hover1() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	
	}
	//@Test
	public void TC_02_Hover2() {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(3);
		
		//Hàm click Webelement thông thường của Selenium
		//driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		
		//Hàm Action click
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
	}
	
	//@Test
	public void TC_02_Hover3() {
		driver.get("https://hn.telio.vn/");
		
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Đồ ăn vặt']"))).perform();
		sleepInSecond(3);
		
		//Hàm click Webelement thông thường của Selenium
		//driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		
		//Hàm Action click
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
	}

	//@Test
	public void TC_02_Click_And_Hold() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> rectangles = driver.findElements(By.cssSelector(".ui-selectee"));
		//Lay ra duoc 12 element
		action.clickAndHold(rectangles.get(0)) // Click vào ô số 1 (chưa nhả chuột trái ra)
		.moveToElement(rectangles.get(3))      // Di chuột đến ô số 4
		.release()							   // Nhả chuột trái ra
		.perform();							  // Thực thi các action
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectee.ui-selected")).size(),4);
	}
	//@Test
	public void TC_02_Click_And_Hold_2() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List<WebElement> rectangles = driver.findElements(By.cssSelector(".ui-selectee"));
			
		action.keyDown(Keys.CONTROL).perform(); //Nhan phim control xuong (chua Nhả ra)
		action.click(rectangles.get(0))
		.click(rectangles.get(2))
		.click(rectangles.get(5))
		.click(rectangles.get(10)).perform();
		action.keyUp(Keys.CONTROL).perform(); //Nhả phím ctrl ra 

		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector(".ui-selectee.ui-selected")).size(),4);
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