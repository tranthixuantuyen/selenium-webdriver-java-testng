package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_10_Run_On_Browser {
	WebDriver driver;
	String projectLocaion = System.getProperty("user.dir");
	
	@Test
	public void TC01_Run_On_Firefox() {
		// Firefox 47 trở xuống + Selenium 2xx + Ko dùng geckodriver
		//driver = new FirefoxDriver();
		
		// Firefox 48 trở lên + Selenium 3xx + Dùng geckodriver
		System.setProperty("webdriver.gecko.driver", projectLocaion + "\\selenium\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.get("https://www.google.com/");
		driver.quit();
	}
//	@Test
	public void TC01_Run_On_Chrome() {
		// 01. Trên 1 máy
		System.setProperty("webdriver.chrome.driver", "D:\\Class20\\02 - Selenium API\\selenium\\browserDrivers\\chromedriver.exe");
		
		//02. Chạy cho tất cả các máy
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		// . = Đại diện cho project location
		
		//03. Chạy cho tất cả các máy
		System.setProperty("webdriver.chrome.driver", projectLocaion+ "\\selenium\\browserDrivers\\chromedriver.exe");

		driver = new ChromeDriver();
		
		driver.get("https://www.google.com/");
		driver.quit();
	}
}
