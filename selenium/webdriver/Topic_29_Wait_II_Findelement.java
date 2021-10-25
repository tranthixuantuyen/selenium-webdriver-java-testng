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

public class Topic_29_Wait_II_Findelement {
	WebDriver driver;
	String projectLocaion = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Firefox 48 trở lên + Selenium 3xx + Dùng geckodriver
		System.setProperty("webdriver.gecko.driver", projectLocaion + "\\selenium\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_FindElement() {
		driver.get("https://www.facebook.com/");
		
		// 1- Có duy nhất 1 element
		// - Không cần chờ hết timeout của implicit 
		// - Tương tác lên element luôn
		driver.findElement(By.id("email")).sendKeys("automationfc.vn@gmail.com");
		
		// 2- Không có element nào hết
		// - Chờ hết timeout của implicit
		// - Trong thời gian chờ cứ mỗi 0.5s cứ tìm lại 1 lần
		// - Khi nào chờ hết timeout của implicit thì sẽ đánh fail testcase và throw exception : NoSuchElement
//		driver.findElement(By.id("address")).sendKeys("Viet Nam");
//		driver.findElement(By.id("pass")).sendKeys("123456");
		
		//3- Nhiều hơn 1 element (>=2)
		// - Ko cần chờ hết timeout của implicit
		//- Trong thời gian chờ cứ 0.5s tìm lại 1 lần
		// - Không quan tâm có bao nhiêu matching note
		System.out.println(driver.findElement(By.xpath("//input")).getAttribute("name"));
		System.out.println(driver.findElement(By.xpath("//input")).getAttribute("type"));
		System.out.println(driver.findElement(By.xpath("//input")).getAttribute("value"));
	}

	@Test
	public void TC_02_FindElements() {
		driver.navigate().refresh();
		
		// 1- Có duy nhất 1 element 
		// Không cần chờ hết timeout của implicit 
		// Tương tác lên element luôn
		driver.findElements(By.id("email")).get(0).sendKeys("automationfc.vn@gmail.com");
		System.out.println(driver.findElements(By.id("email")).size());
		
		// 2- Không có element nào hết -> cần test element không xuất hiện trên UI và không có trong DOM
		// - Chờ hết timeout của implicit
		// - Trong thời gian chờ cứ mỗi 0.5s cứ tìm lại 1 lần
		// - Khi nào chờ hết timeout của implicit thì không đánh fail testcase
		// - Trả về list empty [rỗng/ không có phần tử (web element)] nào hết
		// - Chuyển qua step tiếp theo
		System.out.println(driver.findElements(By.id("address")).size());
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		//3- Nhiều hơn 1 element (>=2)
		// - Ko cần chờ hết timeout của implicit
		//- Lưu hết tất cả các elements trong list
		List<WebElement> footerLinks = driver.findElements(By.cssSelector("ul.pageFooterLinkList a"));
		System.out.println(footerLinks.size());
		for (WebElement link : footerLinks) {
			System.out.println(link.getText());
			
		}
		

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