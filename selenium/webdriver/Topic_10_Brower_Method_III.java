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

public class Topic_10_Brower_Method_III {
	WebDriver driver;
	WebElement element;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_FindElement() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		//1 element
		// Dung 1 lan khong tao bien
		driver.findElement(By.id("email")).sendKeys("automationtest@gmail.com"); 
		sleepInSecond(3);
		
		// Dung lai nhieu lan nen tao bien de code gon hon
		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear();
		emailTextbox.sendKeys("automationtest@gmail.com");
		
		// List<Element>
		List<WebElement> textboxese = driver.findElements(By.xpath("//input[not@type='hide']"));
		
		// Tong cac phan tu dang co trong list
		textboxese.size();
		for (WebElement textbox : textboxese) {
			textbox.clear();
			textbox.sendKeys("Selenium");
			sleepInSecond(2);
			
		}
	}
	@Test
	public void TC_02_Element_Method() {
		element = driver.findElement(By.xpath(""));
		
		// Dùng cho textbox/ textarea/ dropdown (editable)
		element.clear();
		
		// Nhập liệu cho textbox/ textarea/ dropdown (editable)
		element.sendKeys("");
		
		// Button/ Checkbox/ Radio Button/ Dropdown (custom)/ Link
		element.click();
		
		// Lấy ra giá trị của 1 attribute
		element.getAttribute("placeholder");
		
		//Test GUI: font/ size/ color/ position/ location/ responsive/...
		element.getCssValue("background");
		element.getCssValue("front-size");
		
		// Vị trí của element vs viewport (browser)
		element.getLocation();
		
		// Chiều rộng/ cao của element
		element.getSize();
		
		// Lưu hình trong quá trình chạy bị lỗi và attach vào report html
		//element.getScreenshotAs(arg0): .png/ .jpg/ base64
		
		element = driver.findElement(By.cssSelector("#email"));
		String emailTextboxTagname = element.getTagName();
		//input
		// Đầu ra của hàm này sẽ là đầu ra của 1 locator khác
		driver.findElement(By.xpath("//"+ emailTextboxTagname + "[@name='search']"));
		
		// Lấy được text của label/ header/ li / span/ div
		element.getText();
		
		// Cần verify xem 1 element có hiển thị hay không
		Assert.assertTrue(element.isDisplayed());
		Assert.assertFalse(element.isDisplayed());
		
		// Cần verify xem 1 element có thao tác được hay không disable
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		// Cần verify xem 1 element có được chọn hay chưa (radio/checbox/ dropdown)
		Assert.assertTrue(element.isSelected());
		Assert.assertFalse(element.isSelected());
		
		// Dùng cho thẻ form (thẻ/ tagname form)
		// Tương ứng với nhập xong + nhấn phím enter
		element.submit();

		
	}
	@Test
	public void TC_03_Verify_Navigation() {

	}
	@Test
	public void TC_04_Get_PageSource_Code() {
		
		
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