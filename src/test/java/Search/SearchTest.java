package Search;

import Utils.ExcleUtils;
import Utils.SingletonWD;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SearchTest {
    WebDriver driver;
    WebDriverWait wait;
    PomSearch pomSearch;
    ExcleUtils excleUtils;
    List<String> ls;

    @BeforeClass
    public void setUpDriver() {

        driver = SingletonWD.getTheDriver(2);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        pomSearch = new PomSearch(driver, wait);
        excleUtils = new ExcleUtils();
        pomSearch.goToPage();
        ls = excleUtils.readExcelSearchFile("src/test/java/Utils/Registration.xlsx");
        pomSearch.getDriver().manage().window().maximize();
    }

    @Test(priority = 1, description = "isRightLstBySearch")
    public void searchTestCase1() {
        pomSearch.clear();
        Assert.assertEquals(pomSearch.isRightLstBySearch(ls.get(0)), true);
    }
    @Test(priority = 2, description = "specificFilter")
    public void searchTestCase2() {
        pomSearch.clear();
        Assert.assertEquals(pomSearch.specificFilter(ls.get(2)), true);
    }
    @Test(priority = 3, description = "filterByPrice")
    public void searchTestCase3() {
        pomSearch.clear();
        Assert.assertEquals(pomSearch.filterByPrice(ls.get(3)), true);
    }
    @Test(priority = 4, description = "filterByIsNew")
    public void searchTestCase4() {
        pomSearch.clear();
        Assert.assertEquals(pomSearch.filterByIsNew(ls.get(4)), true);
    }
}
