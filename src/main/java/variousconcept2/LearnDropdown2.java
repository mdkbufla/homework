package variousconcept2;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class LearnDropdown2 {
	WebDriver driver;
	//in professional world use init() meanS initialization
	@Before
	public void init(){
		
	System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
	 driver = new ChromeDriver();

	driver.get("http://techfios.com/billing/?ng=admin/");	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//Thread.sleep(2000);
	//driver.close();
	}
	@Test
	public void login() {
		
		WebElement USERNAME_FIELD_ELEMENT = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		WebElement PASSWORD_FIELD_ELEMENT = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		WebElement SINGIN_BUTTON_ELEMENT = driver.findElement(By.xpath("/html/body/div/div/div/form/div[3]/button"));

		USERNAME_FIELD_ELEMENT.sendKeys("demo@techfios.com");
		PASSWORD_FIELD_ELEMENT.sendKeys("abc123");
		SINGIN_BUTTON_ELEMENT.click();
		
		WebElement DASHBOARD_BUTTON_ELEMENT = driver.findElement(By.linkText("Dashboard"));
		WebElement CUSTOMERS_BUTTON_ELEMENT = driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a/span[1]"));
		WebElement ADD_CUSTOMERS_BUTTON_ELEMENT = driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a"));
		
		String actualDashboardElement = DASHBOARD_BUTTON_ELEMENT.getText();
	
		//System.out.println("===" + actualDashboardElement);				
		
		Assert.assertEquals("Dashboard page not found","Dashboard", actualDashboardElement);
		
		CUSTOMERS_BUTTON_ELEMENT.click();
		ADD_CUSTOMERS_BUTTON_ELEMENT.click();
		
		WebElement FULLNAME_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id=\"account\"]"));
		WebElement DROPDOWN_FIELD_ELEMENT = driver.findElement(By.xpath("//select[@id='cid']"));
				
		FULLNAME_FIELD_ELEMENT.sendKeys("Akika");
		Select sel = new Select(DROPDOWN_FIELD_ELEMENT);
		sel.selectByVisibleText("Techfios");
	}
	
//	@After
//	public void tearDown(){
//	driver.close(); //close method close webdriver
//	driver.quit(); // quit method close all browser
//	}
	
}

