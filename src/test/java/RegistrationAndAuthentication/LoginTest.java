package RegistrationAndAuthentication;

import DataForExcle.LoginData;
import Utils.ExcleUtils;
import Utils.SingletonWD;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;
    LogInPage pomLogIn;
    ExcleUtils excleUtils;
    List<LoginData> lst;
    LoginData l;

    @BeforeClass
    public void setUpDriver() {
        driver = SingletonWD.getTheDriver(2);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        pomLogIn = new LogInPage(driver, wait);
        pomLogIn.goToPage();
        excleUtils = new ExcleUtils();
        l = new LoginData();
        lst = excleUtils.readLoginExcelFile("src/test/java/Utils/Registration.xlsx");
    }

    @Test(priority = 1,description = "checkInputPassword")
    void logInTestCase1() {
        pomLogIn.clear();
        Assert.assertEquals(pomLogIn.checkInputPassword(lst.get(2)), true);
    }

    @Test(priority = 2,description = "checkEmptyEmail")
    void logInTestCase2() {
        pomLogIn.clear();
        Assert.assertEquals(pomLogIn.checkInputEmail(lst.get(0)), true);
    }

    @Test(priority = 3,description = "checkInputEmail")
    void LogInTestCase3() {
        pomLogIn.clear();
        Assert.assertEquals(pomLogIn.checkInputEmail(lst.get(1)), true);
    }

    @Test(priority = 4,description = "LogInProcess")
    void logInTestCase4() {
        pomLogIn.clear();
        Assert.assertEquals(pomLogIn.logIn(lst.get(3)), true);
    }
}
