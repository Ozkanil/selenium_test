package selenium_test;

import com.google.common.collect.Ordering;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.LinkedList;
import java.util.List;

public class Homework1_AmazonDropdown {

    @Test
    public void dropdownTest() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");

        //Find the element of the dropdown element.
        WebElement dropdownElement = driver.findElement(By.id("searchDropdownBox"));

        //Print the first selected option and assert if it equals “All Departments”
        Select select = new Select(dropdownElement);
        String firstOption = select.getFirstSelectedOption().getText();
        System.out.println(firstOption);
        Assert.assertEquals("All Departments", firstOption);

        //Select the 4th option by index (index of 3) and assert if the name is “Amazon Devices”.
        String forthOption = select.getOptions().get(3).getText();
        Assert.assertEquals("Baby", forthOption);
        Assert.assertNotEquals("Amazon Devices", forthOption);

        //Print all of the dropdown options-Print the total number of options in the dropdown
        List<WebElement> allOptions = select.getOptions();
        allOptions.forEach(w -> System.out.println(w.getText()));
        System.out.println(allOptions.size());

        //Check if Appliances is a drop-down option. Print true if “Appliances” is an option. Print false otherwise
        boolean checkIfExists = true;
        for (WebElement app : allOptions) {
            checkIfExists = app.getText().equals("Appliances");
        } System.out.println("Does 'appliances' exist? "+checkIfExists);

        //BONUS: Check if the dropdown is in Alphabetical Order
        List<String> list= new LinkedList<>();
        for (WebElement w:allOptions){
            list.add(w.getText().toUpperCase());
        }

        boolean isOrdered= Ordering.natural().isOrdered(list);
        System.out.println("Is the dropdown is ordered alphabetically? "+ isOrdered);
    }}