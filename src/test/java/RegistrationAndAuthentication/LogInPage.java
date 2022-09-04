package RegistrationAndAuthentication;

import DataForExcle.LoginData;
import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogInPage extends BasePage {

    By byForEmail;
    By byForPassword;
    By byForSingnInBtn;
    By byForEmailError;
    By byForPasswordError;
    By byForLogInBtn;
    By byForBtnNext;
    By byForSighIn;
    By byForLogInIcon;

    public LogInPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        initBys();
    }

    private void initBys() {
        byForEmail = By.cssSelector("[title=\"Email\"] [name=\"email\"]");
        byForPassword = By.cssSelector("[title=\"סיסמה\"] [name=\"password\"]");
        byForEmailError = By.cssSelector("[class=\"tx-input-error_3aAS login-input-error_19Yp rtl_1fsK\"]");
        byForPasswordError = By.cssSelector("[class=\"tx-input-error_3aAS rtl_1fsK\"]");
        byForSingnInBtn = By.cssSelector("#SignInNow");
        byForLogInBtn = By.cssSelector("[class=\"submit-btn_2rcd button-darkgray_39nN tx-link_29YD btn_1UzJ btn-darkgray_9gFC uppercase_1KUt\"]");
        byForBtnNext = By.cssSelector("[class=\"link-btn\"]");
        byForSighIn = By.cssSelector("[class=\"profile-button_OKk5 tx-link_29YD underline-hover_3GkV\"]");
        byForLogInIcon = By.cssSelector("[class=\"profile-btn-wrapper_EvqV\"] [class=\"profile-button_OKk5 profile-button-icon_1X_h tx-link_29YD\"]");
    }

    public void goToPage() {
        clear();
        getDriver().get("https://www.terminalx.com/");
        waitUntilVisibilityElementLocated(byForSighIn);
        click(byForSighIn);
    }

    public void enterValues(LoginData loginData) {
        waitUntilVisibilityElementLocated(byForSighIn);
        click(byForSighIn);
        waitUntilVisibilityElementLocated(byForEmail);
        typeInto(loginData.getEmail(), byForEmail);
        waitUntilVisibilityElementLocated(byForPassword);
        typeInto(loginData.getPassword(), byForPassword);
        waitUntilVisibilityElementLocated(byForLogInBtn);
        click(byForLogInBtn);
    }

    public boolean checkInputEmail(LoginData loginData) {
        enterValues(loginData);
        takeScreenShot("prScreen/Registration and login/LogIn", "EmailErrorImg", getDriver());
        return (loginData.getError().equals(getText(byForEmailError)));
    }

    public boolean checkInputPassword(LoginData loginData) {
        enterValues(loginData);
        takeScreenShot("prScreen/Registration and login/LogIn", "ErrorPasswordImg", getDriver());
        return loginData.getError().equals(getText(byForPasswordError));
    }

    public boolean logIn(LoginData loginData) {
        enterValues(loginData);

        takeScreenShot("prScreen/Registration and login/LogIn", "LoginProcessImg", getDriver());
        waitUntilVisibilityElementLocated(byForLogInIcon);
        return getText(byForLogInIcon).contains("הי,");
    }
}
