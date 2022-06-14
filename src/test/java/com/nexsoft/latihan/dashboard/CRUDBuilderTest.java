package com.nexsoft.latihan.dashboard;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class CRUDBuilderTest {
	WebDriver driver;
	JavascriptExecutor jse;

	public String screenshoot() {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String waktu = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String namaFile = "D:\\TestScreenshoot\\" + waktu + ".png";
		File screenshoot = new File(namaFile);

		try {
			FileUtils.copyFile(srcFile, screenshoot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return namaFile;
	}

	@BeforeClass
	public void init() {
		System.setProperty("url", "http://localhost/cicool");
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new ChromeDriver();
		jse = (JavascriptExecutor) driver;
		driver.get(System.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void login() {
		driver.findElement(By.cssSelector(".fa.fa-sign-in")).click();

		driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("albertustitang@gmail.com");
		// input[placeholder='Password']
		driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("@Yogyakarta99");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		String username = driver.findElement(By.cssSelector("span[class='hidden-xs']")).getText();
		Assert.assertEquals(username, "Albertustitang");

	}

	@Test(dataProvider = "getUserData", dataProviderClass = com.nexsoft.latihan.dataprovider.DataProviderUser.class)
	public void main(String param1, String param2, String param3, String param4) {
		System.out.println(param1 + param2 + param3 + param4);
//		Reporter.log("Nama: " + param1+ "Email: " + param2 + "Location: "+ param3);

		driver.findElement(By.xpath("//*[@id=\"app\"]/aside/div/section/ul/li[3]/a/span[1]")).click();

		driver.findElement(By.xpath("//*[@id=\"tbody_crud\"]/tr[2]/td[5]/a[1]")).click();

		driver.findElement(By.id("btn_add_new")).click();

		driver.findElement(By.cssSelector("#first_name")).click();

		driver.findElement(By.cssSelector("#first_name")).sendKeys(param1);

		driver.findElement(By.cssSelector("#last_name")).click();

		driver.findElement(By.cssSelector("#last_name")).sendKeys(param2);

		driver.findElement(By.cssSelector("#email")).click();

		driver.findElement(By.cssSelector("#email")).sendKeys(param3);

		driver.findElement(By.cssSelector("#gender")).click();

		driver.findElement(By.cssSelector("#gender")).sendKeys(param4);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement image = driver.findElement(By.cssSelector("input[title='file input']"));
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Random r = new Random();
		int result = r.nextInt(4 - 1) + 1;
		switch (result) {
		case 1:
			image.sendKeys("D:\\Bootcamp\\com.nexsoft.latihan\\src\\test\\resources\\gebian.jpg");
			break;
		case 2:
			image.sendKeys("D:\\Bootcamp\\com.nexsoft.latihan\\src\\test\\resources\\angie.jpg");
			break;
		case 3:
			image.sendKeys("D:\\Bootcamp\\com.nexsoft.latihan\\src\\test\\resources\\raissa.jpg");
			break;
		default:
			image.sendKeys("D:\\Bootcamp\\com.nexsoft.latihan\\src\\test\\resources\\gebian.jpg");
			break;
		}

		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(By.linkText("Save and go to list")).click();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.get("http://localhost/cicool/administrator/userbiodata");

		String file = "<img src='file://" + screenshoot() + "'/>";
		Reporter.log(file);

	}

}
