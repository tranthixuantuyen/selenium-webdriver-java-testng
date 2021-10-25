package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Button_Radio_Checkbox {
	WebDriver driver;
	By loginButton = By.cssSelector(".fhs-btn-register");
	By usernameTextbox = By.cssSelector("#login_username");
	By passwordTextbox = By.cssSelector("#login_password");
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		//Verify login button disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		driver.findElement(usernameTextbox).sendKeys("automation@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("123456");
		sleepInSecond(2);
		
		//Verify login button enable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		driver.navigate().refresh();
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		//remove disable of login button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButton));
		sleepInSecond(4);
		
		//Verify login button enable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		driver.findElement(loginButton).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_username']/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@id='login_password']/parent::div/following-sibling::div")).getText(), "Thông tin này không thể để trống");
		
		
	}

	//@Test
	public void TC_02_Hidden() {
		driver.get("https://www.fahasa.com/customer/account/create");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[contains(@class,'background-menu-none-homepage')]//span[text()='Sách Trong Nước']")));
		sleepInSecond(5);
	}

	@Test
	public void TC_03_Default_Radio_Checkbox() {
		//Radio
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='1.8 Petrol, 118kW']/preceding-sibling::input")).isSelected());
		
		driver.findElement(By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input")).click();
		sleepInSecond(3);
		
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input")).isSelected());
		
		//Checkbox
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		driver.findElement(By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input")).click();
		driver.findElement(By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input")).click();
		driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).click();
		sleepInSecond(3);
		
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Heated front and rear seats']/preceding-sibling::input")).isSelected());
		Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
	}
	//@Test
	public void TC_04_Select_All_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//input[@type='checkbox' and not (@disabled)]"));
		
		//Select all checkbox
		for (WebElement checkbox : allCheckboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(2);
			}
			Assert.assertTrue(checkbox.isSelected());	
		}
		
		//Delete all select
		for (WebElement checkbox : allCheckboxes) {
			if (checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(2);
			}
			Assert.assertFalse(checkbox.isSelected());	
		}
	}
	@Test
	public void TC_05_Custom_Radio_Checkbox() {
		driver.get("https://material.angular.io/components/radio/examples");
		//Input is hidden
//		//1. Input để click + Input để verify (input không click được)
//		driver.findElement(By.xpath("//span[contains(text(),'Spring')]/preceding-sibling::span/input")).click();
//		sleepInSecond(5);
//		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Spring')]/preceding-sibling::span/input")).isSelected());
//		
//		//2. Label để click + Label để verify (Label lại không verify được)
//		//vì span luôn luôn trả về false, nên label không verify được
//		driver.findElement(By.xpath("//span[contains(text(),'Spring')]")).click();
//		sleepInSecond(5);
//		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Spring')]")).isSelected());
		
		//3. Label để click + Input để verify
		driver.findElement(By.xpath("//span[contains(text(),'Spring')]")).click();
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//span[contains(text(),'Spring')]/preceding-sibling::span/input")).isSelected());
		
		//4. Input để click (Javascripy) + Input để verify
		By springRadio = By.xpath("//span[contains(text(),'Spring')]/preceding-sibling::span/input]");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(springRadio));
		
	}
	@Test
	public void TC_06_Custom_Radio_Checkbox_Google_Form() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		driver.findElement(By.xpath("//span[text()='Cần Thơ']/ancestor::div[@class='docssharedWizToggleLabeledContent']/preceding-sibling::div")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
		
		//Select all checkbox start with text ='Quảng'
		List<WebElement> allCheckboxes = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng')]/div[contains(@class,'exportInnerBox')]"));
		for (WebElement checkbox : allCheckboxes) {
			checkbox.click();
			sleepInSecond(1);
			
		}
		//Verify
		List<WebElement> allCheckboxesSelected = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng') and @aria-checked='true' and @role='checkbox']"));
		for (WebElement checkbox : allCheckboxesSelected) {
			Assert.assertTrue(checkbox.isDisplayed());
		}
		
		//Verify attribute
		allCheckboxesSelected = driver.findElements(By.xpath("//div[starts-with(@aria-label,'Quảng') and @role='checkbox']"));
		for (WebElement checkbox : allCheckboxesSelected) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
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