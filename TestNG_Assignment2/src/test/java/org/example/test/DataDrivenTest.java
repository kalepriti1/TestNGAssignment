package org.example.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class DataDrivenTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\rbs\\Desktop\\rbs\\Practice\\TestNG_Assignment1\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/test/newtours/index.php");
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        String excelPath = "C:\\Users\\rbs\\Desktop\\rbs\\Practice\\TestNG_Assignment2\\src\\test\\java\\org\\example\\Data\\TestNg_Assignment2.xlsx";
        FileInputStream inputStream = new FileInputStream(excelPath);
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("Sheet1");
        int rowCount = sheet.getLastRowNum();
        int colCount = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rowCount][colCount];
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cell.getStringCellValue();
            }
        }
        workbook.close();
        return data;
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password) {
        WebElement usernameInput =driver.findElement(By.name("userName"));

        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(password);

        WebElement submitButton = driver.findElement(By.name("submit"));
        submitButton.click();
        boolean loginSuccess = driver.findElements(By.xpath("//h3[contains(text(), 'Login Successfully')]")).size() > 0;
        boolean loginFailed = driver.findElements(By.xpath("//span[contains(text(), 'Enter your username and password')]")).size() > 0;

        if (loginSuccess) {
            System.out.println("Login successful for username: " + username);

        } else if (loginFailed) {
            System.out.println("Login failed for username: " + username);
        }
        driver.navigate().back();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

