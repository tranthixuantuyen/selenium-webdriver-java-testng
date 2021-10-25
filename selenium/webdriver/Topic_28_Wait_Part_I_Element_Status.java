package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Wait_Part_I_Element_Status {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath+ "\\selenium\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		explicitWait = new WebDriverWait(driver,20);
	}

	//@Test
	public void TC_01_Visiable() {
		driver.get("https://www.facebook.com/");
			
		//Wait cho 1 element hiển thị trong khoản thời gian 15s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		
		//Verify cho 1 element hiển thị
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
	}
	//@Test
	public void TC_02_Invisiable_Undisplayed() {
		driver.get("https://www.facebook.com/");
		
		//Wait cho button Tạo tài khoản có thể được click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")));
		
		//Action
		driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();
		
		// Ko có trên UI nhưng vẫn có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email__']")));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		// Ko có trên UI và không có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='reg']")));
	}

	//@Test
	public void TC_03_Presence() {
		driver.get("https://www.facebook.com/");
		
		//Hiển thị trên UI và có trong Dom -> Pass
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")));
		driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();
		
		//Ko hiển thị trên UI vẫn có trong Dom -> Pass
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email__']")));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		//Ko hiển thị trên UI ko có trong Dom -> Fail
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//form[@id='reg']")));
	}
	
	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")));
		driver.findElement(By.xpath("//a[contains(text(),'Tạo tài khoản mới')]")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id='reg']")));
		//Hiển thị trên UI và có trong DOM
		WebElement confirmEmail = driver.findElement(By.xpath("//input[@name='reg_email__']"));
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		// Tại thời điểm này - mong muốn không còn trong DOM nữa
		explicitWait.until(ExpectedConditions.stalenessOf(confirmEmail));

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