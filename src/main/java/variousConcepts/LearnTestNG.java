package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LearnTestNG {

	WebDriver driver;
	String browser = "chrome";
	//String url =null;
	
	/*public void noAnnotation() {
		System.out.println("without annotation");
	}
	/*@BeforeSuite
	public void beforeSuite() {
		noAnnotation();
		System.out.println("I am before Suite");
	}
	@BeforeTest
	public void beforeClass() {
		noAnnotation();
		System.out.println("I am before class");
	}*/
	
	@BeforeSuite
	public void readConfig() {
		Properties prop = new Properties();
		// FileReader //inputStrem // BufferReader// Scanner - These different class can read file.
		
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			String browser = prop.getProperty("browser");
			System.out.println("Used Browser: " + ("browser"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser == "firefox") {
			System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.get("http://www.techfios.com/billing/?ng=login/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	public void loginTest1() {
		// storing web element
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Login page not found!!");

		WebElement USERNAME_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD_ELEMENT = driver.findElement(By.xpath("//input[@id=\"password\"]"));
		WebElement SIGNIN_BUTTON_ELEMENT = driver.findElement(By.xpath("/html/body/div/div/div/form/div[3]/button"));

		// storing web element using By class

		USERNAME_FIELD_ELEMENT.clear();
		USERNAME_FIELD_ELEMENT.sendKeys("demo@techfios.com");
		PASSWORD_FIELD_ELEMENT.clear();
		PASSWORD_FIELD_ELEMENT.sendKeys("abc123");
		SIGNIN_BUTTON_ELEMENT.click();

		WebElement DASHBOARD_BUTTON_ELEMENT = driver.findElement(By.linkText("Dashboard"));
		String actualDashboardElement = DASHBOARD_BUTTON_ELEMENT.getText();
		
		By DASHBOARD_BUTTON = By.xpath("//span[contains(text(), 'Dashboard')]");
		waitForElement(driver, 3, DASHBOARD_BUTTON );
		Assert.assertEquals(DASHBOARD_BUTTON, "Dashboard","Dashboard page not found!!");
	}

	@Test(priority = 2)
	public void addCustomerTest() {
		
		// Element Library
		By USER_NAME_FIELD = By.id("username");
		By PASSWORD_FIELD = By.id("password");
		By SIGNIN_BUTTON = By.name("login");
		By DASHBOARD_BUTTON = By.xpath("//span[contains(text(), 'Dashboard')]");
		By CUSTOMERS_BUTTON = By.xpath("//span[contains(text(), 'Customers')]");
		By ADD_CUSTOMER_BUTTON = By.xpath("//a[contains(text(), 'Add Customer')]");
		By ADD_CONTACT_LOCATOR = By.xpath("//h5[contains(text(),'Add Contact')]");
		By FULL_NAME_FIELD = By.xpath("//input[@id='account']");
		By COMPANY_NAME_FIELD = By.xpath("//input[@id='company']");
		By EMAIL_FIELD = By.xpath("//input[@id='email']");
		By PHONE_FIELD = By.xpath("//input[@id='phone']");
		By ADDRESS_FIELD = By.xpath("//input[@id='address']");
		By CITY_FIELD = By.xpath("//input[@id='city']");
		By STATE_REGION_FIELD = By.xpath("//input[@id='state']");
		By ZIP_FIELD = By.xpath("//input[@id='zip']");
		By SUBMIT_BUTTON = By.xpath("//button[@class='btn btn-primary']");
		By LIST_CONTACTS_BUTTON = By.xpath("//a[contains(text(),'List Contacts')]");

		// Login Data
		String loginId = "demo@techfios.com";
		String password = "abc123";
		
		//Test Data -at real work we keep this in separate file.
		String fullName = "Test August";
		String companyName = "Techfios";
		String emailAddress = "test@gmail.com";
		String phoneNumber = "5551212";
		
		Assert.assertEquals(driver.getTitle(), "Login - iBilling", "Login page not found!!");
		
		//login 
		driver.findElement(USER_NAME_FIELD).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON).click();
		
		WebElement DASHBOARD_BUTTON_ELEMENT = driver.findElement(By.linkText("Dashboard"));
		String actualDashboardElement = DASHBOARD_BUTTON_ELEMENT.getText();
		//Assert.assertEquals("Dashboard page not found", "Dashboard",
		//actualDashboardElement);
					
		waitForElement(driver, 4, DASHBOARD_BUTTON);
		Assert.assertEquals(actualDashboardElement, "Dashboard", "Dashboard page not found!!");
		
		driver.findElement(CUSTOMERS_BUTTON).click();
		waitForElement(driver, 2, ADD_CUSTOMER_BUTTON );
		driver.findElement(ADD_CUSTOMER_BUTTON).click();
		
		//to avoid overlaping use random number
		Random rnd = new Random();
		int generatedNo = rnd.nextInt(999);
						
		waitForElement(driver, 3, FULL_NAME_FIELD );
		
		driver.findElement(FULL_NAME_FIELD).sendKeys(fullName + generatedNo);
		driver.findElement(EMAIL_FIELD).sendKeys(generatedNo + emailAddress );
		
	}
	
	public void waitForElement(WebDriver driver, int timeInSeconds, By locator) {
		
		WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	//@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();

	}
}