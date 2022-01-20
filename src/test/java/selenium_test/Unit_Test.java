package selenium_test;

import com.google.common.collect.Ordering;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Unit_Test extends TestBase {

    @Test
    public void productNumber () throws InterruptedException {

        driver.get("https://www.hepsiburada.com/");
        WebElement searchBox= driver.findElement(By.xpath("(//input[@type='text'])[1]"));
        searchBox.sendKeys("kulaklık"+ Keys.ENTER);
        driver.findElement(By.xpath("(//*[@data-test-id='dropdown-toggle'])[2]")).click();
        //driver.findElement(By.linkText("Artan Fiyat")).click();
        driver.findElement(By.xpath("//input[@class='searchbox-searchInput']")).sendKeys("sony");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@name='markalar']")).click();
        String sonyUrl= driver.getCurrentUrl();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(sonyUrl);

        do {

            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.PAGE_DOWN).perform();

            List<WebElement> sonyList= driver.findElements(By.xpath("//*[@data-test-id='product-card-image-container']"));
            if(sonyList.size()==88){
            Assert.assertEquals(88,sonyList.size());
            break;}

        }while (true);
    }
}
