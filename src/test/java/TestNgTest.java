import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNgTest {
    public String baseUrl = "https://www.saucedemo.com/";
    public WebDriver driver ;

    @BeforeTest
    public void setUp() {
        System.out.println("Before test lunching Chrome.");
        driver = new ChromeDriver();
        driver.get(baseUrl);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
        //driver.close();
        System.out.println("Closing Chrome.");
    }

    @Test
    public void a_verifyHomepageTitle() {
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("Expected title: Swag Labs is present.");
    }

    @Test
    public void b_validLogIn(){
        By user = By.id("user-name");
        By pass = By.id("password");

        WebElement userInput = driver.findElement(user);
        WebElement passInput = driver.findElement(pass);

        userInput.clear();
        userInput.sendKeys("standard_user");
        passInput.clear();
        passInput.sendKeys("secret_sauce");
        passInput.sendKeys(Keys.ENTER);
    }

    @Test
    public void c_addProductToBasket(){
        By tShirt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
        By cartQuantity = By.xpath("//span[@class='shopping_cart_badge']");

        WebElement addShirtToBasket = driver.findElement(tShirt);
        addShirtToBasket.click();

        String quantity = driver.findElement(cartQuantity).getText();
        Assert.assertEquals(1,Integer.parseInt(quantity));
    }
}
