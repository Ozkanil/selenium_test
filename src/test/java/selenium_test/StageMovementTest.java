package selenium_test;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.DatagramPacket;
import java.time.Duration;

public class StageMovementTest extends TestBase{

    @Test
    public void stageMovements() throws InterruptedException {

        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("http://automationpractice.com/index.php");
        WebElement firstProduct= driver.findElement(By.xpath("//img[@class='replace-2x img-responsive']"));
        Actions actions=new Actions(driver);
        actions.moveToElement(firstProduct).perform();
        driver.findElement(By.linkText("Add to cart")).click();

        WebElement addToChartT= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='icon-ok']")));
        String color= driver.findElement(By.xpath("//span[@id='layer_cart_product_attributes']")).getText();
        String justColor= color.substring(0, color.indexOf(','));
        String justSize= color.substring(color.indexOf(',')+1).trim();
        String quantity= driver.findElement(By.xpath("//span[@id='layer_cart_product_quantity']")).getText();
        String price= driver.findElement(By.xpath("//span[@id='layer_cart_product_price']")).getText();
        String shipping= driver.findElement(By.xpath("//span[@class='ajax_cart_shipping_cost']")).getText();
        String totalPrice=driver.findElement(By.xpath("//span[@class='ajax_block_cart_total']")).getText();

        Assert.assertTrue(addToChartT.isDisplayed());
        Assert.assertEquals("Orange", justColor);
        Assert.assertEquals("S",justSize);
        Assert.assertEquals("$16.51",price);
        Assert.assertEquals("1", quantity);
        Assert.assertEquals("$2.00",shipping);
        Assert.assertEquals("$18.51",totalPrice);

        driver.findElement(By.partialLinkText("Proceed")).click();

        WebElement summaryVerification= driver.findElement(By.id("cart_title"));
        Assert.assertTrue(summaryVerification.isDisplayed());

        String inStockVerification= driver.findElement(By.xpath("//span[@class='label label-success']")).getText();
        Assert.assertEquals("In stock", inStockVerification);

        String finalPrice= driver.findElement(By.id("total_price")).getText();
        Assert.assertEquals(totalPrice,finalPrice);

        WebElement plusButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='icon-plus']")));
        plusButton.click();

        Thread.sleep(5000);

        String priceOfTwoProducts= driver.findElement(By.id("total_product_price_1_1_0")).getText().substring(1);
        double priceMultiplyByTwo= Double.parseDouble(price.substring(1))*2;

        Assert.assertEquals(priceOfTwoProducts, Double.toString(priceMultiplyByTwo));

        driver.findElement(By.partialLinkText("Proceed")).click();

        WebElement signInPage=driver.findElement(By.xpath("//h1[@class='page-heading']"));
        Assert.assertTrue(signInPage.isDisplayed());

        driver.findElement(By.id("email")).sendKeys("myfakeaccount123@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("abc123!");
        WebElement signInButton= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='icon-lock left']")));
        signInButton.click();

        WebElement addressPageHeading=driver.findElement(By.xpath("//h1[@class='page-heading']"));
        Assert.assertTrue(addressPageHeading.isDisplayed());

        actions.sendKeys(Keys.PAGE_DOWN).perform();
        driver.findElement(By.xpath("(//i[@class='icon-chevron-right right'])[7]")).click();
        String shippingPageHeading=driver.findElement(By.xpath("//h1[@class='page-heading']")).getText();
        Assert.assertEquals("SHIPPING",shippingPageHeading);
        WebElement checkoutButton =driver.findElement(By.xpath("(//i[@class='icon-chevron-right right'])[3]"));
        checkoutButton.click();

        String alertMessage= driver.findElement(By.xpath("//p[@class='fancybox-error']")).getText();

        Assert.assertEquals("You must agree to the terms of service before continuing.",alertMessage);
        driver.findElement(By.xpath("//a[@title='Close']")).click();
        driver.findElement(By.id("cgv")).click();
        checkoutButton.click();
        String shippingPageHeader= driver.findElement(By.xpath("//h1[@class='page-heading']")).getText();
        Assert.assertEquals("PLEASE CHOOSE YOUR PAYMENT METHOD", shippingPageHeader);

    }}
