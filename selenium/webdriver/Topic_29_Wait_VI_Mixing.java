package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Date;

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

public class Topic_29_Wait_VI_Mixing {
	WebDriver driver;
	WebDriverWait explicitwait;
	String projectLocaion = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Firefox 48 trở lên + Selenium 3xx + Dùng geckodriver
		System.setProperty("webdriver.gecko.driver", projectLocaion + "\\selenium\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

	}

	public void TC_01_Element_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitwait = new WebDriverWait(driver, 30);
		
		driver.get("https://www.facebook.com/");
		 
		showDateTimeNow("Start explicit");
		explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[id='email']")));
		showDateTimeNow("End explicit - start implicit");
		driver.findElement(By.xpath("//input[id='email']")).sendKeys("automation@gmail.com");
		showDateTimeNow("End implicit");
	}


	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.facebook.com/");

		showDateTimeNow(" start implicit");
		try {
			driver.findElement(By.xpath("//input[id='email_ko_co']")).sendKeys("automation@gmail.com");
		} catch (Exception e) {
			e.printStackTrace();
		}
		showDateTimeNow("End implicit");
	}
	
	
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitwait = new WebDriverWait(driver, 10);
		
		driver.get("https://www.facebook.com/");
		
		//Find Element trước
		//apply điều kiện
		//Implicit sẽ ảnh hưởng vào các step có dùng explicit

		showDateTimeNow("Start explicit");
		try {
			explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[id='email_ko_co']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		showDateTimeNow("End explicit ");
		// Dù có dùng explicit timeout thì không ảnh hường vào việc findElement
	}
	
	@Test
	public void TC_04_Element_Not_Found_Explicit_By() {
		explicitwait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");

		showDateTimeNow("Start explicit (By): ");
		try {
			explicitwait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[id='email_ko_co']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		showDateTimeNow("End explicit (By): ");
	}
	
	@Test
	public void TC_05_Element_Not_Found_Explicit_Param_WebElement() {
		explicitwait = new WebDriverWait(driver, 5);
		
		driver.get("https://www.facebook.com/");

		showDateTimeNow("Start explicit (WebElement): ");
		try {
			explicitwait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[id='email_ko_co']"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		showDateTimeNow("End explicit (WebElement): ");
	}
	
	public void showDateTimeNow(String status) {
		Date date = new Date();
		System.out.println("------------"+ status +":" + date.toString() + "------");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}