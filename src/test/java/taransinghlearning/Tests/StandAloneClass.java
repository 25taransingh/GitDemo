package taransinghlearning.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import taransinghlearning.pageObject.LandingPage;

public class StandAloneClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String Product = "ZARA COAT 3";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("writetotaransingh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Taran@123");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));

		List<WebElement> products = driver.findElements(By.className("mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(Product)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink='/dashboard/cart']")));

		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean matchedProduct = cartProducts.stream().anyMatch(addedProduct -> addedProduct.getText().equals(Product));

		Assert.assertTrue(matchedProduct);
		System.out.println(matchedProduct);

		driver.findElement(By.xpath("//*[text()='Checkout']")).click();

		Actions a = new Actions(driver);

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));

		WebElement selectCOuntry = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));

		a.sendKeys(selectCOuntry, "India").build().perform();

		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		
		//Very Very important lines
		WebElement placeOrder = driver.findElement(By.xpath("//a[text()='Place Order ']"));
		js.executeScript("arguments[0].scrollIntoView(true);", placeOrder);
		js.executeScript("arguments[0].click();", placeOrder);



		String confirmationMsg = driver.findElement(By.xpath("//tr/td/h1")).getText();

		Assert.assertEquals(confirmationMsg, "Thankyou for the order.");

		System.out.println(confirmationMsg);

//		Thread.sleep(2000);
//		driver.quit();

	}

}
