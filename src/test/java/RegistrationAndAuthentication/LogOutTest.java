package RegistrationAndAuthentication;

import DataForExcle.LoginData;
import Utils.ExcleUtils;
import Utils.SingletonWD;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class LogOutTest {
    WebDriver driver;
    WebDriverWait wait;
    LogOutPage pomLogOut;
    ExcleUtils excleUtils;
    List<LoginData> lst;
    LoginData l;

    @BeforeClass
    public void setUpDriver() {
        driver = SingletonWD.getTheDriver(2);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        pomLogOut = new LogOutPage(driver, wait);
        pomLogOut.goToPage();
        excleUtils = new ExcleUtils();
        l = new LoginData();
        lst = excleUtils.readLoginExcelFile("src/test/java/Utils/Registration.xlsx");

    }

    @Test(priority = 1, description = "logOutProcess")
    public void LogOutTestCase1() {
        pomLogOut.clear();
        Assert.assertEquals(pomLogOut.logOutProcess(lst.get(3)), true);
    }

    @AfterClass
    public void tearDown() {
        System.out.println("finish logout");
    }
}
