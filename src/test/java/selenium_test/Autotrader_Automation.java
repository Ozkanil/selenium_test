package selenium_test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Autotrader_Automation extends TestBase{

    @Test
    public void autotraderAutomation() throws InterruptedException {

        extentTest.pass("First page passed");

        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://www.autotrader.co.uk/?refresh=true");

        driver.findElement(By.id("postcode")).sendKeys("PO16 7GZ");

       // Thread.sleep(4000);

        WebElement distance= driver.findElement(By.id("distance"));
        distance.click();

        select=new Select(distance);
        select.selectByIndex(3);

        WebElement make= driver.findElement(By.id("make"));
        new Select(make).selectByIndex(3);

        Thread.sleep(3000);

        WebElement model= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("model")));

        new Select(model).selectByIndex(2);

        driver.findElement(By.xpath("(//label)[5]")).click();

        WebElement minPrice= driver.findElement(By.id("minPrice"));
        new Select(minPrice).selectByIndex(1);
        Thread.sleep(3000);
        WebElement maxPrice= driver.findElement(By.id("maxPrice"));
        new Select(maxPrice).selectByIndex(3);
        Thread.sleep(3000);
        WebElement searchButton= driver.findElement(By.xpath("(//button)[2]"));
        String searchButtonText=searchButton.getText();
        searchButton.click();

        List<WebElement> totalNumber= driver.findElements(By.xpath("//div[@class='product-card__inner']"));

        List<WebElement> ads= driver.findElements(By.xpath("//span[@class='listings-standout']"));

        String digits= searchButtonText.replaceAll("[^0-9]","");
        int number= Integer.parseInt(digits);

        int allCarsListSize= totalNumber.size();
        int adCars=ads.size();

        boolean equals= number==(allCarsListSize-adCars);

        Assert.assertTrue(equals);
    }
}
