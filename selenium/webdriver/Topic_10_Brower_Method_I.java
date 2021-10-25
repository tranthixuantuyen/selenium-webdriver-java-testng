package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Brower_Method_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() {
		//Mở ra 1 url (app)
		driver.get("http://demo.nopcommerce.com/");
		// Đóng 1 tab/cửa sổ đang active
		driver.close();
		// Đóng cả brower
		driver.quit();

		// Lấy ra url tại page đang đứng
		String homePageUrrl = driver.getCurrentUrl();
		// Lấy tile tại page đang đứng
		String homePageTitle = driver.getTitle();	
		// Lấy pagesource tại page đang đứng
		String homePageSource = driver.getPageSource();	
		//Window/tab ID hiện tại
		String homePageTabID = driver.getWindowHandle();
		// Get ra tất cả các tab/windown ID đang có
		Set<String> allWindowns = driver.getWindowHandles();
		
		// Chờ cho element có thế được tìm thấy trong 1 khoảng thời gian 15s
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		// Chờ page load thành công trong 15s
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		// Chờ 1 đoạn JS được inject thành công trong 15s
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		//Phóng to trình duyệt
		driver.manage().window().maximize();
		
		// Phóng to brower
	    driver.navigate().back();
	    driver.navigate().forward();
	    driver.navigate().refresh();
	    
	    //Alert/windown/frame
	    driver.switchTo().alert();
	    driver.switchTo().frame(1);
	    driver.switchTo().window("");
	    
		
		/* Lấy dữ liệu ra thường bắt đầu từ tiền tố getXXX ---> trả về dữ liệu
		 * Để thao tác/action thì không trả về dữ liệu*/
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