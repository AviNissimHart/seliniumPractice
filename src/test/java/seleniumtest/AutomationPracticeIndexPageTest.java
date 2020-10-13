package seleniumtest;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationPracticeIndexPageTest {

    private WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "D:\\\\Users\\\\avini\\\\Documents\\\\workspace-spring-tool-suite-4-4.8.0.RELEASE\\\\seleniumtest\\\\src\\\\test\\\\resources\\\\drivers\\\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1366, 768));
    }

    @Test
    public void test() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");
        WebElement searchBar = driver.findElement(By.id("search_query_top"));
        String searchTerm = "Printed";
        searchBar.sendKeys(searchTerm);
        searchBar.submit();

        List<WebElement> products = driver.findElements(By.className("ajax_block_product"));
        for(WebElement product: products) {
            String productName = product.findElement(By.className("product-name")).getText();
            if (!productName.contains(searchTerm)) {
                fail("Non-relevant item returned");
            }
        }
    }

    @After
    public void tearDown() {
        driver.close();
    }
}