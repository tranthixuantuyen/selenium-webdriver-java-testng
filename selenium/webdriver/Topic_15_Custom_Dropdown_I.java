package webdriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Custom_Dropdown_I {
	WebDriver driver;
	Select dayDropdown, monthDropdown,yearDropdown;
	
	String firstName, lastName, email, password, day, month, year;
	
	By maleRadio = By.id("gender-male");
	By firstnameTextbox = By.id("FirstName");
	By lastNameTextbox = By.id("LastName");
	By emailTextbox = By.id("Email");
	By dayDropdownBy = By.name("DateOfBirthDay");
	By monthDropdownBy = By.name("DateOfBirthMonth");
	By yearDropdownBy = By.name("DateOfBirthYear");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		firstName = "Automation";
		lastName = "Tuyen";
		email = getRandomEmail();
		password = "test123456";
		day = "31";
		month = "March";
		year = "1992";
		
	}

	@Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/register");

		driver.findElement(maleRadio).click();
		driver.findElement(firstnameTextbox).sendKeys(firstName);
		driver.findElement(lastNameTextbox).sendKeys(lastName);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		dayDropdown = new Select(driver.findElement(dayDropdownBy));
		dayDropdown.selectByVisibleText(day);
		monthDropdown = new Select(driver.findElement(monthDropdownBy));
		monthDropdown.selectByVisibleText(month);
		yearDropdown = new Select(driver.findElement(yearDropdownBy));
		yearDropdown.selectByVisibleText(year);
		
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector(".ico-account")).click();
		Assert.assertTrue(driver.findElement(maleRadio).isSelected());
		Assert.assertEquals(driver.findElement(firstnameTextbox).getAttribute("value"),firstName );
		Assert.assertEquals(driver.findElement(lastNameTextbox).getAttribute("value"),lastName );
		Assert.assertEquals(driver.findElement(emailTextbox).getAttribute("value"),email );
		
		dayDropdown = new Select(driver.findElement(dayDropdownBy));
		Assert.assertEquals(dayDropdown.getFirstSelectedOption().getText(),day);
		dayDropdown = new Select(driver.findElement(monthDropdownBy));
		Assert.assertEquals(monthDropdown.getFirstSelectedOption().getText(),month);
		dayDropdown = new Select(driver.findElement(yearDropdownBy));
		Assert.assertEquals(yearDropdown.getFirstSelectedOption().getText(),year);
		
	/*	dayDropdown.selectByIndex(12);
		sleepInSecond(3);
		dayDropdown.selectByValue("8");
		sleepInSecond(3);
		
		//Kiểm tra xem đã được chọn được ngày 31 hay chưa
		Assert.assertEquals(dayDropdown.getFirstSelectedOption().getText(),"31");
		
		//Kiểm tra 1 dropdown không phải là multiple
		Assert.assertFalse(dayDropdown.isMultiple());
		
		// Kiểm tra xem dropdown có bao nhiêu items
		Assert.assertEquals(dayDropdown.getOptions(),"32");*/
		
		
	}

	@Test
	public void TC_02_Multiple_Drop_Down() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Select multipleDropdown = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(multipleDropdown.isMultiple());
		multipleDropdown.selectByVisibleText("Manual");
		multipleDropdown.selectByVisibleText("Desktop");
		multipleDropdown.selectByVisibleText("Intergration");
		multipleDropdown.selectByVisibleText("Functional UI");
		sleepInSecond(2);
		
		//Khai báo list element những cái đã chọn rồi
		List<WebElement> selectedItem = multipleDropdown.getAllSelectedOptions();
		
		//Kiểm tra đúng 4 items hay không
		Assert.assertEquals(selectedItem.size(), 4);
		
		// Khai báo mảng để lưu những text đã chọn
		List<String> selectedItemtext = new ArrayList<String>();
		
		// Dùng vòng lặp để duyệt qua get text, lưu vào 1 cái mảng, sau đó lấy cái mảng đi kiểm tra chưa những cái mong muốn hay không
		for (WebElement element : selectedItem) {
			selectedItemtext.add(element.getText());//mỗi lần get text xong đẩy vào list
		}
		Assert.assertTrue(selectedItemtext.contains("Manual"));
		Assert.assertTrue(selectedItemtext.contains("Desktop"));
		Assert.assertTrue(selectedItemtext.contains("Intergration"));
		Assert.assertTrue(selectedItemtext.contains("Functional UI"));
				
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

	public String getRandomEmail() {
		Random rand = new Random();
		return "automation" + rand.nextInt(3333) + "@hotmail.com";
		// johndeep2345@hotmail.com
	}

}