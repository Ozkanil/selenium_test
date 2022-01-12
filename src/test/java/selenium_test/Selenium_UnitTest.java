package selenium_test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.stream.Collectors;

public class Selenium_UnitTest extends TestBase {

    @Test
    public void testCase() throws InterruptedException {

        driver.get("https://www.hepsiburada.com/");
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("kulaklık"+ Keys.ENTER);
        Thread.sleep(3000);
        WebElement searchButton= driver.findElement(By.xpath("//*[@id=\"SortingBox\"]/div/div/div/div/div[1]/div/label"));
        searchButton.click();
        driver.findElement(By.linkText("Artan Fiyat")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@class='searchbox-searchInput']")).sendKeys("sony"+Keys.ENTER);
        Thread.sleep(5000);
        WebElement checkbox= driver.findElement(By.xpath("//*[@id=\"markalar\"]/div/div/div/div/div[2]/div/div/div/div/label/input"));
        Thread.sleep(3000);
        checkbox.click();
        String urtl= driver.getCurrentUrl();
        driver.switchTo().newWindow(WindowType.TAB).get(urtl);
        Thread.sleep(5000);
        Actions actions=new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(5000);

        List<WebElement> numberOfSony= driver.findElements(By.xpath("//h3[@type='comfort']"));
        Thread.sleep(5000);
        int numberOfSonyHeadphones=+numberOfSony.size();
        Assert.assertEquals(78,numberOfSonyHeadphones);
        System.out.println("The number of Sony headphones are "+numberOfSonyHeadphones);

    }
}
