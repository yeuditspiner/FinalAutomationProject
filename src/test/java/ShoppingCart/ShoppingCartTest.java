package ShoppingCart;

import DataForExcle.RegistrationData;
import Search.PomSearch;
import Utils.ExcleUtils;
import Utils.SingletonWD;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ShoppingCartTest {
    WebDriver driver;
    WebDriverWait wait;
    PomShoppingCart pomShoppingCart;
    ExcleUtils excleUtils;
    List<String> lst;

    @BeforeClass
    public void setUpDriver() {
        driver = SingletonWD.getTheDriver(2);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        pomShoppingCart = new PomShoppingCart(driver, wait);
        excleUtils = new ExcleUtils();
        pomShoppingCart.goToPage();
        lst = excleUtils.readExcelShppingCartFile("src/test/java/Utils/Registration.xlsx");
    }

    @Test(priority = 1, description = "checkAddToCart")
    public void shoppingCartTestCase1() {
        pomShoppingCart.clear();
        Assert.assertEquals(pomShoppingCart.checkAddToCart(lst.get(0)), true);
    }

    @Test(priority = 2,description = "checkRemoveFromCart")
    public void shoppingCartTestCase2() {
        pomShoppingCart.clear();
        Assert.assertEquals(pomShoppingCart.checkRemoveFromCart(lst.get(1)), true);
    }
}
