package Utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void waitUntilVisibilityElementLocated(By locator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        catch (Exception ex){
            System.out.println(ex.toString()+"at page "+getDriver().getCurrentUrl());
        }
    }

    public WebElement findElement(By locator) {
        WebElement element=null;
        try {
           element = driver.findElement(locator);
        }
        catch (TimeoutException ex)
        {
            System.out.println("not found the locator: "+getText(locator).toString()+" at page: "+getDriver().getCurrentUrl().toString());
        }
        return element;
    }

    public List<WebElement> findElemnts(By locator) {
        return driver.findElements(locator);
    }

     public String getText(WebElement elemnt) {
        return elemnt.getText();
    }

    public String getText(By locator) {
        return findElement(locator).getText();
    }

    public String getAttribute(By locator, String att) {
        return driver.findElement(locator).getAttribute(att);
    }

    public WebElement typeInto(String inputText, By locator) {
        findElement(locator).sendKeys(inputText);
        return findElement(locator);
    }

    public void click(By locator) {
        WebElement elemnt;
        try {
            elemnt = findElement(locator);
            elemnt.click();
        }
        catch (Exception ex){
            System.out.println(ex.toString()+"click "+ getDriver().toString());
        }
    }

    public void mouseHover(WebDriver wd) {
        WebDriver driver = wd;
        Actions action = new Actions(driver);
    }

    public boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void selectFromDropDownListByValue(By locator, String value) {
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(value);
    }

    public void selectFromDropDownListByVisableText(By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.selectByVisibleText(text);
    }

    public void selectFromDropDownListByIndex(By locator, int index) {
        Select select = new Select(driver.findElement(locator));
        select.selectByIndex(index);
    }

    public void visit(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void clear() {
        driver.navigate().refresh();
    }

    public void normal(){driver.manage().window().maximize();}

    static public void takeScreenShot(String filePathStr, String nameForTheImageFile, WebDriver driver) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {

            FileUtils.copyFile(file, new File(filePathStr + "/" + nameForTheImageFile + "_1" + ".png"));

        } catch (IOException e1) {

            // TODO Auto-generated catch block
            e1.printStackTrace();

        }

    }
    public void setScreenSize()
    {
        getDriver().manage().window().maximize();
    }

    public void close() {
        getDriver().quit();
    }
}
