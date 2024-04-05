import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebApplicationTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\rbs\\Desktop\\rbs\\Practice\\TestNG_Assignment1\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://demo.guru99.com/test/newtours/index.php");
    }
    @Test(groups = {"testLogin"})
    public void testLogin() {
        driver.findElement(By.name("userName")).sendKeys("Guddi");
        driver.findElement(By.name("password")).sendKeys("Guddi");
        driver.findElement(By.name("submit")).click();

        boolean login = driver.findElements(By.xpath("//h3[contains(text(), 'Login Successfully')]")).size() > 0;
        Assert.assertTrue(login, "Login failed");
    }

    @Test(groups = {"testFlightBooking"}, dependsOnGroups = {"testLogin"})
    public void testFlightBooking() {
        Assert.assertTrue(true, "Booking failed - placeholder assertion");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

