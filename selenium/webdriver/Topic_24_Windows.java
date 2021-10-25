package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_24_Windows {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", projectPath + "\\selenium\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath+ "\\selenium\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_Github() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Get ra window/tab id tai tab dang duoc active
		String parentTabID = driver.getWindowHandle();
		System.out.println("Parent ID = " + parentTabID);
		
		//Click to google -> open new tab
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		
		//Switch qua google tab
		switchtoWindowByID(parentTabID);
		sleepInSecond(3);
		
		String googleTabID = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium");
		sleepInSecond(3);
		
		//Switch về lại parent tab
		switchtoWindowByID(googleTabID);
		sleepInSecond(3);
		
		//Click to facebook
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		
		//Switch to parent tab
		switchtoWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		sleepInSecond(3);
		
		//Switch to parent tab
		switchtoWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	}

//	@Test
	public void TC_02_Kyna() {
		driver.get("https://kyna.vn/");
		
		String parentTabID = driver.getWindowHandle();
		
		//Click to facebook link at footer -> tab mới
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		
		//Switch qua facebook
		switchtoWindowByID(parentTabID);
		sleepInSecond(5);
		
		//Switch qua parent page (Kyna.vn)
		switchtoWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(2);
		
		//Click vietnamwork lint at footer -> Tab mới
		driver.findElement(By.xpath("//div[@id='k-footer']//a[text()='VietnamWorks']")).click();
		sleepInSecond(3);
		
		switchtoWindowByTitle("Tuyển dụng, việc làm, tìm việc làm nhanh mới nhất | VietnamWorks");
		sleepInSecond(5);
		
		driver.findElement(By.id("search-bar-input")).sendKeys("Automation test");
		sleepInSecond(5);
		
		//Switch qua parent page (Kyna.vn)
		switchtoWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		sleepInSecond(5);
		
		//Click PRIMUS lint at footer -> Tab mới
		driver.findElement(By.xpath("//div[@id='k-footer']//a[text()='PRIMUS']")).click();
		sleepInSecond(5);
		switchtoWindowByTitle("PRIMUS Homepage");
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".btn-register1")).isDisplayed());
		closeAllTabWithoutParent(parentTabID);
		
		
	}

	@Test
	public void TC_03_LiveGuru() {
		driver.get("http://live.demoguru99.com/index.php/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		String parentID = driver.getWindowHandle();	
		
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		
		switchtoWindowByID(parentID);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/catalog/product_compare/index/");
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void switchtoWindowByID(String windowID) {
		// Get hết các id đang có
		Set<String> allTabIDs = driver.getWindowHandles();
		
		//Duyet qua cac gia tri trong tat cả window
		for (String id : allTabIDs) {
			//Kiểm tra đk nếu như khác với windowID truyền vào thì switch
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				//Thoát khỏi vòng lặp
				break;
			}
		}
	}
	public void switchtoWindowByTitle(String tabTitle) {
		// Get hết các id đang có
		Set<String> allTabIDs = driver.getWindowHandles();
		
		//Duyet qua cac gia tri trong tat cả window
		for (String id : allTabIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(tabTitle)) {
				break;
			}
		}
	}
	public void closeAllTabWithoutParent(String parentID) {
		Set<String> allTabIDs = driver.getWindowHandles();

		for (String id : allTabIDs) {
			if(!id.equals(parentID))
			{
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
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