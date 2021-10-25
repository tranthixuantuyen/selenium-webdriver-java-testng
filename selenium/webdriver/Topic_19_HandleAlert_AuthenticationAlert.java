package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_19_HandleAlert_AuthenticationAlert {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	By resultValue = By.xpath("//p[@id='result']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
//		//1. Khi nó xuất hiện mới swich
//		alert = driver.switchTo().alert();
		
		//2. Vừa wait vùa switch vào
		alert= explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Get text
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		//Acceept Alert
		alert.accept();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(resultValue).getText(),"You clicked an alert successfully");
	}

	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		//2. Vừa wait vùa switch vào
		alert= explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Get text
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		//Acceept Alert
		alert.dismiss();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(resultValue).getText(),"You clicked: Cancel");
	}

	@Test
	public void TC_03_Prompt_Arlert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String alertValue = "tranthixuantuyen";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		//2. Vừa wait vùa switch vào
		alert= explicitWait.until(ExpectedConditions.alertIsPresent());
		
		//Get text
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		//Acceept Alert
		alert.sendKeys(alertValue);
		sleepInSecond(5);
		alert.accept();
		Assert.assertEquals(driver.findElement(resultValue).getText(),"You entered: "+ alertValue);
	}
	
	//@Test
	public void TC_04_Authentication_Arlert() {
		//Cach 1: Truyen thang truc tiep trong url http://usename:password@the-internet.herokuapp.com/basic_auth
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		
		Assert.assertTrue(driver.findElement(By.xpath(".//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	//@Test
	public void TC_05_Authentication_Arlert() {
		driver.get("http://the-internet.herokuapp.com/");
		String basicAuthenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");	
		System.out.println(basicAuthenUrl);
		
		driver.get(passInforToUrl(basicAuthenUrl));
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		
	}
	
	public String passInforToUrl(String url) {
		String[] urlValue = url.split("//");
		url = urlValue[0] + "//" + "admin" + ":" + "admin" + "@" + urlValue[1];
		return url;
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