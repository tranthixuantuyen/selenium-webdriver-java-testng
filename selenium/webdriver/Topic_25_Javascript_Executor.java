package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Javascript_Executor {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	WebElement element;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	
	String emailAddress, loginPageUrl, userID, password, gender;
	String name, dateofBirth, address, city, state, pin, phone, customerID;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;
	By nameTextbox = By.name("name");
	By genderRadio = By.xpath("//input[@value='m']");
	By genderTextbox = By.name("gender");
	By dobTextbox = By.name("dob");
	By addressTextArea = By.name("addr");
	By cityTextbox = By.name("city");
	By stateTextbox = By.name("state");
	By pinTextbox = By.name("pinno");
	By phoneTextbox = By.name("telephoneno");
	By emailTextbox = By.name("emailid");
	By passwordTextbox = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath+ "\\selenium\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public void TC_01_Live_Guru() {
		navigateToUrlByJS("http://live.demoguru99.com/"); 
		
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
		
		String liveGuruUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGuruUrl, "http://live.demoguru99.com/");
		
		clickToElementByJS("//a[text()='Mobile']");
		
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		
		Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		clickToElementByJS("//a[text()='Customer Service']");
		Assert.assertEquals(getTitleOfPage(), "Customer Service");
		
		scrollToElement("//input[@id='newsletter']");
		
		sendkeyToElementByJS("//input[@id='newsletter']", getRandomEmail());
		
		clickToElementByJS("//span[text()='Subscribe']");
		
		Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		Assert.assertEquals((String) executeForBrowser("return document.domain;"), "demo.guru99.com");	
	}

	public void TC_02_() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
		
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Vui lòng điền vào trường này.");
		
		sendkeyToElementByJS("//input[@id='fname']", "Automation FC");
		sleepInSecond(2);
		
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Vui lòng điền vào trường này.");
		
		sendkeyToElementByJS("//input[@id='pass']", "123456");
		sleepInSecond(2);
		
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Vui lòng điền vào trường này.");
		
		sendkeyToElementByJS("//input[@id='em']", "123");
		sleepInSecond(2);
		
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Vui lòng điền vào trường này.");
		
		sendkeyToElementByJS("//input[@id='em']", "123@abc");
		sleepInSecond(2);
		
		clickToElementByJS("//input[@name='submit-btn']");
		sleepInSecond(2);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Vui lòng điền vào trường này.");
	}

	@Test
	public void TC_03_New_Customer() {
		driver.get("http://live.demoguru99.com/");
		
		loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(nameTextbox).sendKeys(name);
		driver.findElement(genderRadio).click();
		
		removeAttributeInDOM("//input[@name=dob']", "type");
		sleepInSecond(3);
		driver.findElement(dobTextbox).sendKeys(dateofBirth);
		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(emailAddress);
		driver.findElement(passwordTextbox).sendKeys(password);

		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='heading3']")).getText(),"Customer Registered Successfully!!!");
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dateofBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailAddress);

	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}
	
	public String getTitleOfPage() {
		return jsExecutor.executeScript("return document.title;").toString();
	}

	
	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
	
	public String getRandomEmail() {
		Random rand = new Random();
		return "johndeep" + rand.nextInt(9999) + "@hotmail.com";
		// johndeep2345@hotmail.com
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