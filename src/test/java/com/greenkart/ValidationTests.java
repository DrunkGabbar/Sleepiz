package com.greenkart;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ValidationTests {
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		// Initializing browser
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test (priority =1)
	public void validTest() {

		//Open the test page
		String actualUrl = "https://rahulshettyacademy.com/seleniumPractise/#/";
		driver.get(actualUrl);
		
		//Validating the url
		String expectedUrl = "https://rahulshettyacademy.com/seleniumPractise/#/";
		Assert.assertEquals(actualUrl, expectedUrl, "Actual URL is not same as expected");
		
		// Validating the Title
		String expectedTitle = "GreenKart - veg and fruits kart";
		String actualTitle= driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Actual title is not same as expected");

	}
	@AfterMethod
	// Closing browser
	public void closeWindow()
	{
		driver.quit();
	}

}
