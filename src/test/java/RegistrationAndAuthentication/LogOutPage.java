package RegistrationAndAuthentication;

import DataForExcle.LoginData;
import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOutPage extends BasePage {
    By byForSighIn;
    By byForEmail;
    By byForPassword;
    By byForLogInIcon;
    By byForLogOut;
    By byForLogInBtn;


    public LogOutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        initBys();
    }

    private void initBys() {
        byForEmail = By.cssSelector("[title=\"Email\"] [name=\"email\"]");
        byForPassword = By.cssSelector("[title=\"סיסמה\"] [name=\"password\"]");
        byForLogInIcon = By.cssSelector("[class=\"profile-btn-wrapper_EvqV\"] [class=\"profile-button_OKk5 profile-button-icon_1X_h tx-link_29YD\"]");
        byForLogOut = By.cssSelector("[class=\"list-item_2A1s logout_3ZNa\"] [class=\"list-link_323s tx-link_29YD\"]");
        byForSighIn = By.cssSelector("[class=\"profile-button_OKk5 tx-link_29YD underline-hover_3GkV\"]");
        byForLogInBtn = By.cssSelector("[class=\"submit-btn_2rcd button-darkgray_39nN tx-link_29YD btn_1UzJ btn-darkgray_9gFC uppercase_1KUt\"]");
    }

    public void goToPage() {
        clear();
        getDriver().get("https://www.terminalx.com/");
    }

    public void enterValues(LoginData loginData) {
        waitUntilVisibilityElementLocated(byForSighIn);//--עבור לחיצה על התחברות
        click(byForSighIn);
        waitUntilVisibilityElementLocated(byForEmail);
        typeInto(loginData.getEmail(), byForEmail);
        waitUntilVisibilityElementLocated(byForPassword);
        typeInto(loginData.getPassword(), byForPassword);
        waitUntilVisibilityElementLocated(byForLogInBtn);
        click(byForLogInBtn);
    }

    public boolean logOutProcess(LoginData loginData) {
        //first of all - loginProcess:
        logIn(loginData);
        //LogOut Process:
        waitUntilVisibilityElementLocated(byForLogOut);
        click(byForLogOut);
        waitUntilVisibilityElementLocated(byForSighIn);
        takeScreenShot("prScreen/Registration and login/LogOut", "AfterLogOutProcessImg", getDriver());
        return getDriver().getCurrentUrl().equals(loginData.getError());
    }
    void logIn(LoginData loginData) {
        enterValues(loginData);
        waitUntilVisibilityElementLocated(byForLogInIcon);
        click(byForLogInIcon);
        takeScreenShot("prScreen/Registration and login/LogOut", "BeforLogOutProcessImg", getDriver());
    }
}
