package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Brower_Method_Create_And_Login {
	WebDriver driver;
	String firstname = "John";
	String lastname = "Deep";
	String email_address = getRandomEmail();
	String password = "123123";
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.demoguru99.com/");
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(email_address);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);	
		driver.findElement(By.id("is_subscribed")).click();
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
		
		String informationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		System.out.println(informationText);
		Assert.assertTrue(informationText.contains(firstname + " " + lastname));
		Assert.assertTrue(informationText.contains(email_address));
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();	
		
	}
	@Test
	public void TC_02_Login() {
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();	
		sleepInSecond(5);
		driver.findElement(By.id("email")).sendKeys(email_address);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click(); 
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + firstname+ " "+ lastname +"!");
		
		String informationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		System.out.println(informationText);
		Assert.assertTrue(informationText.contains(firstname + " " + lastname));
		Assert.assertTrue(informationText.contains(email_address));
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
	return "johndeep" + rand.nextInt(3333)+"@hotmail.com";
	//johndeep2345@hotmail.com
	}
}