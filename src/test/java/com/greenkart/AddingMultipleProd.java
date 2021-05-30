package com.greenkart;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddingMultipleProd {
	WebDriver driver;
	WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		// Initializing browser
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();

		// Wait for Synchronization
		wait = new WebDriverWait(driver, 5);

	}

	@Test(priority =3)
	public void addMultipleProducts() {
		// Open the test page
		String url = "https://rahulshettyacademy.com/seleniumPractise/";
		driver.get(url);
		
		
		//Selecting multiple products
		String[] veggies = { "Mango", "Carrot", "Cucumber", "Orange", "Almonds", "Walnuts" };
		addItems(driver, veggies);

		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
		
		//Applying Promo code
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoCode")));
		driver.findElement(By.cssSelector(".promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("button.promoBtn")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));
		String expectedInfo = "Code applied ..!";
		String actualInfo = driver.findElement(By.cssSelector("span.promoInfo")).getText();
		System.out.println(actualInfo);
		Assert.assertEquals(actualInfo, expectedInfo);

	}
	// Getting the product name 
	public static void addItems(WebDriver driver, String[] veggies) {
		int j = 0;
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

		for (int i = 0; i < products.size(); i++) {
			String[] name = products.get(i).getText().split("-");
			String formattedName = name[0].trim();

			List veggiesList = Arrays.asList(veggies);

			if (veggiesList.contains(formattedName)) {
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (j == veggies.length) {
					break;
				}
			}
		}
	}
	@AfterMethod
	// Closing browser
	public void closeWindow()
	{
		driver.quit();
	}
	
}
