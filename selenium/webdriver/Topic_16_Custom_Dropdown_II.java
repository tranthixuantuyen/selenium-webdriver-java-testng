package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Custom_Dropdown_II {
	WebDriver driver;//interface
	WebDriverWait explicitWait; // class
	JavascriptExecutor jsExcutor;//interface
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath+"/selenium/browserDrivers/chromedriver.exe");
		driver = new ChromeDriver();
//		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExcutor = (JavascriptExecutor) driver; // import add to cast
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemCustomDropDown("//span[@id='number-button']","//ul[@id='number-menu']//div","8");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text()='8']")).isDisplayed());
		
		selectItemCustomDropDown("//span[@id='number-button']","//ul[@id='number-menu']//div","16");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text()='16']")).isDisplayed());
		
		selectItemCustomDropDown("//span[@id='number-button']","//ul[@id='number-menu']//div","3");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text()='3']")).isDisplayed());
	}

	//@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemCustomDropDown("//i[@class='dropdown icon']","//div[@role='option']//span","Justen Kitsune");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selection')]/div[@class='divider text' and text()='Justen Kitsune']")).isDisplayed());
		
		selectItemCustomDropDown("//i[@class='dropdown icon']","//div[@role='option']//span","Stevie Feliciano");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selection')]/div[@class='divider text' and text()='Stevie Feliciano']")).isDisplayed());

		selectItemCustomDropDown("//i[@class='dropdown icon']","//div[@role='option']//span","Matt");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selection')]/div[@class='divider text' and text()='Matt']")).isDisplayed());
		
		
	}

	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemCustomDropDown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","First Option");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		selectItemCustomDropDown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Second Option");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		
		selectItemCustomDropDown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Third Option");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());
	}
	
	//@Test
	public void TC_05_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		
		selectItemCustomDropDown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]","//ul[@id='games_options']/li","Basketball");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']//input")).getAttribute("aria-label"),"Basketball");
		
		selectItemCustomDropDown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]","//ul[@id='games_options']/li","Tennis");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']//input")).getAttribute("aria-label"),"Tennis");
		
		selectItemCustomDropDown("//ejs-dropdownlist[@id='games']//span[contains(@class,'e-search-icon')]","//ul[@id='games_options']/li","Football");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']//input")).getAttribute("aria-label"),"Football");
	}
	
	//@Test
	public void TC_06_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemCustomDropDown("//div[@role='combobox']/input","//div[@role='option']/span","American Samoa");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selection')]/div[@class='divider text' and text()='American Samoa']")).isDisplayed());
	
		selectItemCustomDropDown("//div[@role='combobox']/input","//div[@role='option']/span","Belgium");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selection')]/div[@class='divider text' and text()='Belgium']")).isDisplayed());
	
	}
	
	//@Test
	public void TC_07_Tab_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		tabItemEditableDropDown("//div[@role='combobox']/input","American Samoa");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selection')]/div[@class='divider text' and text()='American Samoa']")).isDisplayed());
	
		tabItemEditableDropDown("//div[@role='combobox']/input","Belgium");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'selection')]/div[@class='divider text' and text()='Belgium']")).isDisplayed());
	
	}
	
	@Test
	public void TC_08_Multiple_Select() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		String [] lessMonth = {"April", "August", "September"};
		String [] moreMonth = {"March", "April", "August", "September", "November"};
		
		selectMultiItemDropDown("(//button[@class='ms-choice'])[1]","(//button[@class='ms-choice'])[1]/following-sibling::div//span",lessMonth);
		sleepInSecond(2);
		Assert.assertTrue(areItemSelected(lessMonth));
		
		driver.navigate().refresh();
		
		selectMultiItemDropDown("(//button[@class='ms-choice'])[1]","(//button[@class='ms-choice'])[1]/following-sibling::div//span",moreMonth);
		sleepInSecond(2);
		Assert.assertTrue(areItemSelected(moreMonth));					
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void selectItemCustomDropDown (String parentXpath, String childXpath, String expectedTextItem) {
		// Click vào thẻ (cha) cho nó xổ ra các item con bên trong
		driver.findElement(By.xpath(parentXpath)).click();
		
		//Chờ cho tất cả các item được load ra hết
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		System.out.println("Item size = "+ allItems.size());
		
		// Nếu chọn 1 item nào đó -> getText của item đó ra so sánh vs text mà mình mong muốn
		// Nếu như bằng cái mình mong muốn thì scroll xuống và click vào
		// Nếu như chưa bằng thì duyệt cái item thiếp theo
		
		for (WebElement item : allItems) {
			System.out.println("Text of item = "+ item.getText());
			//trim để cắt khoảng trắng đầu cuối, bài tập vuejs
			if(item.getText().trim().equals(expectedTextItem)) {
				jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}	
		}
	}
	
	public void selectItemEditableDropDown (String parentXpath, String childXpath, String expectedTextItem) {
		// Click vào textbox để nó hiện con trỏ chuột
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);
		
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		//Chờ cho tất cả các item được load ra hết
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		System.out.println("Item size = "+ allItems.size());
		
		// Nếu chọn 1 item nào đó -> getText của item đó ra so sánh vs text mà mình mong muốn
		// Nếu như bằng cái mình mong muốn thì scroll xuống và click vào
		// Nếu như chưa bằng thì duyệt cái item thiếp theo
		
		for (WebElement item : allItems) {
			System.out.println("Text of item = "+ item.getText());
			//trim để cắt khoảng trắng đầu cuối, bài tập vuejs
			if(item.getText().trim().equals(expectedTextItem)) {
				jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}	
		}
	}

	public void tabItemEditableDropDown (String parentXpath, String expectedTextItem) {
		// Click vào textbox để nó hiện con trỏ chuột
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);
		
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedTextItem);
		sleepInSecond(1);
		
		//Tab chọn dropdown
		driver.findElement(By.xpath(parentXpath)).sendKeys(Keys.TAB);
		sleepInSecond(1);
		
	}

	public void selectMultiItemDropDown (String parentXpath, String childXpath, String[] expectedValueItem) {
		//1. Click vào dropdown cho nó xổ hết tất cả các giá trị ra
		driver.findElement(By.xpath(parentXpath)).click();
		
		//2.Chờ tất cả các giá trị trong dropdown load được thành công
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//Duyệt qua tất cả các phần tử cho đến khi thỏa mãn điều kiện
		for (WebElement childElement : allItems) {
			//"january, april
			for(String item : expectedValueItem ) {
				if(childElement.getText().equals(item)) {
					//3. Scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì không cần scroll)
					jsExcutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);
					//4. Click vào item cần chọn
					childElement.click();
					sleepInSecond(1);
					
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = "+itemSelected.size());
					if (expectedValueItem.length == itemSelected.size())
					{
						break;
					}
				}	
				
			}	
		}
	}
	
	public boolean areItemSelected (String[] expectedValueItem) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();
		
		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]")).getText();
		System.out.println("Text đã chọn=" + allItemSelectedText);
		
		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			boolean status = true;
			for (String item : expectedValueItem) {
				if(!allItemSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected >= 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected <12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='"+ numberItemSelected +" of 12 selected']")).isDisplayed();
		} else {
			return false;
		}
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