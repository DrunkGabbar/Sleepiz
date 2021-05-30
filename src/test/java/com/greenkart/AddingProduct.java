package com.greenkart;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddingProduct {
	
	WebDriver driver;

	@BeforeMethod
	public void setUp() {
		// Initializing browser
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		driver = new FirefoxDriver();

		// Wait for Synchronization
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}
	@Test (priority = 2)
	public void addingSingleProduct()
	{
			//Open Test page
			String url = "https://rahulshettyacademy.com/seleniumPractise/";
			driver.get(url);
			
			// Selecting a product
			String veggies = "Mango";
			addItems(driver, veggies);

			driver.findElement(By.cssSelector("img[alt='Cart']")).click();
			driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
	}
	
	
	// Getting the product name 
	public static void addItems(WebDriver driver, String veggies) 
	{
		int j = 0;
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

		for (int i = 0; i < products.size(); i++) 
		{
			String[] name = products.get(i).getText().split("-");
			String formattedName = name[0].trim();

			List veggiesList = Arrays.asList(veggies);

			if (veggiesList.contains(formattedName)) 
			{
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				if (j == veggies.length()) 
				{
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
