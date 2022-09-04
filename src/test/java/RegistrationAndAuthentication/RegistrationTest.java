package RegistrationAndAuthentication;

import DataForExcle.RegistrationData;
import Utils.ExcleUtils;
import Utils.SingletonWD;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;


public class RegistrationTest {
    WebDriver driver;
    WebDriverWait wait;
    RegistrationPage pomRegistration;
    ExcleUtils excleUtils;
    RegistrationData r;
    List<RegistrationData> lst;

    @BeforeClass
    public void setUpDriver() {
        driver = SingletonWD.getTheDriver(2);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        pomRegistration = new RegistrationPage(driver, wait);
        pomRegistration.goToPage();
        excleUtils = new ExcleUtils();
        r = new RegistrationData();
        lst = excleUtils.readRegistrationExcelFile("src/test/java/Utils/Registration.xlsx");
        pomRegistration.normal();
    }

    //firstName
    @Test(priority = 1, description = "checkInputFirstName1")
    public void registrationTestCase1() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputFirstName(lst.get(0)), true);
    }

    //lastName
    @Test(priority = 2, description = "checkInputLastName")
    public void registrationTestCase2() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputLastName(lst.get(1)), true);
    }

    //email
    @Test(priority = 3, description = "checkInputEmail")
    public void registrationTestCase3() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputEmail(lst.get(3)), true);
    }

    //password
    @Test(priority = 4, description = "checkInputPassword1")
    public void registrationTestCase4() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputPassword(lst.get(4)), true);
    }

    @Test(priority = 5, description = "checkInputPassword2")
    public void registrationTestCase5() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputPassword(lst.get(8)), true);

    }

    @Test(priority = 6, description = "checkInputPassword3")
    public void registrationTestCase6() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputPassword(lst.get(9)), true);
    }

    @Test(priority = 7, description = "checkInputPassword4")
    public void registrationTestCase7() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputPassword(lst.get(10)), true);

    }

    @Test(priority = 8, description = "invalidPasswordTest6")
    public void registrationTestCase8() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputPassword(lst.get(11)), true);
    }

    @Test(priority = 9, description = "invalidPasswordTest7")
    public void registrationTestCase9() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputPassword(lst.get(12)), true);
    }

    @Test(priority = 10, description = "checkInputConfirmPassword")
    public void registrationTestCase10() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputConfirmPassword(lst.get(5)), true);
    }

    @Test(priority = 11, description = "checkInputDatOfBirth")
    public void registrationTestCase11() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputDatOfBirth(lst.get(13)), true);
    }

    //Phone:
    @Test(priority = 12, description = "checkInputPhone1")
    public void registrationTestCase12() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputPhone(lst.get(2)), true);
    }

    @Test(priority = 13, description = "checkInputPhone2")
    public void registrationTestCase13() {
        pomRegistration.clear();
        Assert.assertEquals(pomRegistration.checkInputPhone(lst.get(6)), true);
    }

   // @Test(priority = 14, description = "registrationProcess")
   // public void registrationTest() {
     //   pomRegistration.clear();
       // Assert.assertEquals(pomRegistration.registrationProcess(lst.get(14)), true);
    //}


}
