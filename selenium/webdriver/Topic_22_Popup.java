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

public class Topic_22_Popup {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

//	@Test
	public void TC_01_ZingPoll() {
		driver.get("https://www.zingpoll.com/");
		By signInPopup = By.cssSelector(".modal_dialog_custom");
		driver.findElement(By.xpath("//a[@id='Loginform']")).click();
		Assert.assertTrue(driver.findElement(signInPopup).isDisplayed());
		
		driver.findElement(By.cssSelector(".modal_dialog_custom .close")).click();
		sleepInSecond(2);
		
		//Verify signin popup is not displayed
		Assert.assertFalse(driver.findElement(signInPopup).isDisplayed());
	}

//	@Test
	public void TC_01_Shopee() {
		driver.get("https://shopee.vn/");
		By homePopup = By.cssSelector("img[alt='home_popup_banner']");
		
		//Verify home popup is not displayed
		 Assert.assertTrue(isElementDisplayed(homePopup));
		 driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		 sleepInSecond(3);
		 
		 //Verify home popup is not displayed
		 Assert.assertFalse(isElementDisplayed(homePopup));
	}

//	@Test
	public void TC_02_Random_Popup_In_DOM() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(30);
		
		if (isElementDisplay(By.cssSelector(".mailch-wrap"))) {
		driver.findElement(By.cssSelector("#close-mailch")).click();
		}
		
		driver.findElement(By.cssSelector("section input[class='search-field']")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("section span[class='glass']")).click();
		
		List <WebElement> postArticles = driver.findElements(By.xpath("//h3[@class='post-title']/a"));
		// 8 articles
		
		for (WebElement article : postArticles) {
			Assert.assertTrue(article.getText().contains("Selenium"));
		}
	}
	
	@Test
	public void TC_02_Random_Popup_Not_In_DOM() {
		driver.get("https://shopee.vn/");
		String searchKeyWord = "Macbook Pro";
		By homePopup = By.cssSelector("img[alt='home_popup_banner']");
		
		if (isElementDisplay(homePopup)) {
		driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
		sleepInSecond(2);
		}
		
		driver.findElement(By.cssSelector(".shopee-searchbar-input input")).sendKeys("Macbook Pro");
		driver.findElement(By.cssSelector(".btn-solid-primary")).click();
		
		List <WebElement> products = driver.findElements(By.cssSelector(".shopee-search-item-result__item div[data-sqe='name'] .yQmmFK"));
		// 50 results
		
		for (WebElement product : products) {
			String productName = product.getText().toLowerCase();
			Assert.assertTrue(productName.contains(searchKeyWord.split(" ")[0].toLowerCase()) || productName.contains(searchKeyWord.split(" ")[1].toLowerCase()));
		}
		

	}
	
	public boolean isElementDisplayed(By by) {
		try {
			// Nó sẽ tìm element trong vòng 10s
			//Khi nào hết 10s mới throw exception
			return driver.findElement(by).isDisplayed();
			
		} catch (Exception e) {
			//Catch bắt exception lại
			return false;
		}
	}
	
	// Cách 2
	public boolean isElementDisplay(By by) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		List<WebElement> elements = driver.findElements(by);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if (elements.size() == 0) {
			return false;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return false;
		} else {
			return true;
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