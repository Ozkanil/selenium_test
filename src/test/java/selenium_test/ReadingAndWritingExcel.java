package selenium_test;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReadingAndWritingExcel extends TestBase {

    @org.junit.Test
    public void webTable() throws IOException {

        driver.get("https://en.wikipedia.org/wiki/List_of_countries_by_GDP_(nominal)");

        List<WebElement> firstColumn = driver.findElements(By.xpath("//table[@class='wikitable sortable static-row-numbers plainrowheaders srn-white-background jquery-tablesorter']//tbody//tr//td[1]"));
        List<WebElement> secondColumn = driver.findElements(By.xpath("//table[@class='wikitable sortable static-row-numbers plainrowheaders srn-white-background jquery-tablesorter']//tbody//tr//td[3]"));
        Map<String, String> countryGDP = new LinkedHashMap<>();

        for (int i = 0; i < firstColumn.size(); i++) {

            String country = firstColumn.get(i).getText();
            String gDP = secondColumn.get(i).getText();

            countryGDP.put(country, gDP);
            writeToExcel(country,gDP,i);

        }
        System.out.println(countryGDP);
    }
    public void writeToExcel(String a, String b, int index){

        try{

        String path = "./src/main/resources/CountryGDP.xlsx";
        FileInputStream newFile= new FileInputStream(path);
        Workbook workbook= WorkbookFactory.create(newFile);
        Sheet sheet= workbook.getSheetAt(0);

        sheet.createRow(index).createCell(0).setCellValue(a);
        sheet.getRow(index).createCell(1).setCellValue(b);

        FileOutputStream fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);

    }catch (IOException e){
            e.fillInStackTrace();
        }

}}

