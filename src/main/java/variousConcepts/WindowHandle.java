package variousConcepts;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

	public class WindowHandle {
		
		WebDriver driver;
//		@Before
//
//		public void launchBrowser() {
//
//			// Setting up the property
//			System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
//			// Creating web driver instance
//			driver = new ChromeDriver();
//			// Maximizing Browser
//		//	driver.manage().window().maximize();
//			// Get to the site
//			
//			driver.get("https://www.yahoo.com/");
//			driver.manage().deleteAllCookies();
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		@Test
		public void loginTest()  {

				// Setting up the property
				System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
				// Creating web driver instance
				driver = new ChromeDriver();
				// Maximizing Browser
			//	driver.manage().window().maximize();
				// Get to the site
				
				driver.get("https://www.yahoo.com/");
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				System.out.println(driver.getTitle());
				String handle = driver.getWindowHandle();
				System.out.println(handle);
			
			
			// Identifying the username field pass username
	
			driver.findElement(By.xpath("//*[@id=\"ybar-sbq\"]")).sendKeys("xpath");
			driver.findElement(By.xpath("//*[@id=\"ybar-search\"]")).click();
			
			System.out.println(driver.getTitle());
			String handle1 = driver.getWindowHandle();
			System.out.println(handle1);
			
		   //Thread.sleep(2000);
			driver.findElement(By.linkText("XPath Tutorial - W3Schools")).click();
			
			System.out.println(driver.getTitle());
			Set<String> handles = driver.getWindowHandles();
			System.out.println(handles);
						
			for(String i : driver.getWindowHandles()) {
				System.out.println(i);
				//to transfer driver to other window
				driver.switchTo().window(i);
			}
			//Here driver will moved to 'xpath tutorial' 
			// and if ask to go back to 'yahoo search result' window, do this code 
			
//			System.out.println(driver.getTitle());
//			driver.switchTo().window(handle1);
//			System.out.println(driver.getTitle());
//		}
//		
		//to make sure we can now click another element after driver has moved into 'xpath tutorial' page
			driver.findElement(By.linkText("CSS")).click();
			//driver from css page will give title of the page
			System.out.println(driver.getTitle());
			}
		
	}
	


