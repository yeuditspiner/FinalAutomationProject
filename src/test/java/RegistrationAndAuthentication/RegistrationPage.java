package RegistrationAndAuthentication;

import DataForExcle.RegistrationData;
import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {

    WebElement forTitle;
    By byForTitle;

    WebElement forFirstName;
    By byForFirstName;

    WebElement forLastName;
    By byForLastName;

    WebElement forEmail;
    By byForEmail;

    WebElement forPassword;
    By byForPassword;
    By ByForConfirmPassword;


    WebElement forTelephon;
    By byForContactTelephone;

    By byForDateOfBirth;
    By byForGender;


//   for errors:

    By byForErrorFirstName;
    By byForErrorLastName;
    By byForErrorEmail;
    By byForErrorPhone;
    By byForErrorPassword;
    By byForErrorBirthDate;
    By byForConfirmError;
    By byForRegistrationBtn;
    By byForCheckBoxEmail;
    By byForRegistrationProcess;
    By byForSighIn;
    By ByForRegistrationBegin;

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        initBys();
    }

    private void initBys() {
        byForTitle = By.cssSelector("#Title");
        byForFirstName = By.cssSelector("[name=\"firstname\"]");
        byForLastName = By.cssSelector("[name=\"lastname\"]");
        byForDateOfBirth = By.cssSelector("[name=\"date_of_birth\"]");
        byForGender = By.cssSelector("[name=\"gender\"]");
        byForEmail = By.cssSelector("[title=\"Email\"] [name=\"email\"]");
        byForPassword = By.cssSelector("[name=\"password\"]");
        ByForConfirmPassword = By.cssSelector("[name=\"new_password_confirmation\"]");
        byForContactTelephone = By.cssSelector("[name=\"telephone\"]");
        byForCheckBoxEmail = By.cssSelector("[title=\"מאשר/ת את תנאי השימוש האתר\"] [name=\"agreements\"]");
        byForRegistrationBtn = By.cssSelector("[class=\"submit_3yFi tx-link_29YD btn_1UzJ btn-yellow_2tf3\"]");
        ByForRegistrationBegin = By.cssSelector("[class=\"tx-link_29YD btn_1UzJ btn-yellow_2tf3\"]");
        byForSighIn = By.cssSelector("[class=\"profile-button_OKk5 tx-link_29YD underline-hover_3GkV\"]");
        byForRegistrationProcess = By.cssSelector("[class=\"link-btn\"]");
//       bys for Errors:
        byForErrorBirthDate = By.cssSelector("[class=\"tx-input-error_3aAS rtl_1fsK\"]");
        byForConfirmError = By.cssSelector("[title=\"אימות סיסמה\"]  [class=\"register-input-wrapper_3gK9\"] + [class=\"tx-input-error_3aAS rtl_1fsK\"]");
        byForErrorFirstName = By.cssSelector("[class=\"tx-input-error_3aAS rtl_1fsK\"]");
        byForErrorLastName = By.cssSelector("[title=\"שם משפחה \"]  [class=\"register-input-wrapper_3gK9\"] + [class=\"tx-input-error_3aAS rtl_1fsK\"]");
        byForErrorEmail = By.cssSelector("[title=\"Email\"]  [class=\"register-input-wrapper_3gK9\"] + [class=\"tx-input-error_3aAS rtl_1fsK\"]");
        byForErrorPassword = By.cssSelector("[title=\"סיסמה\"]  [class=\"register-input-wrapper_3gK9\"] + [class=\"tx-input-error_3aAS rtl_1fsK\"]");
        byForErrorPhone = By.cssSelector("[title=\"טלפון\"]  [class=\"register-input-wrapper_3gK9\"] + [class=\"tx-input-error_3aAS rtl_1fsK\"]");
    }

    public void goToPage() {
        getDriver().get("https://www.terminalx.com/");
        waitUntilVisibilityElementLocated(byForSighIn);
        click(byForSighIn);
        waitUntilVisibilityElementLocated(ByForRegistrationBegin);
        click(ByForRegistrationBegin);
    }

    void enterValues(RegistrationData registrationData) {
        waitUntilVisibilityElementLocated(byForFirstName);
        typeInto(registrationData.getFirstName(), byForFirstName);
        waitUntilVisibilityElementLocated(byForLastName);
        typeInto(registrationData.getLastName(), byForLastName);
        waitUntilVisibilityElementLocated(byForDateOfBirth);
        typeInto(registrationData.getDateOfBirth(), byForDateOfBirth);
        waitUntilVisibilityElementLocated(byForGender);
        typeInto(registrationData.getGender(), byForGender);
        waitUntilVisibilityElementLocated(byForEmail);
        typeInto(registrationData.getEmail(), byForEmail);
        waitUntilVisibilityElementLocated(byForContactTelephone);
        typeInto(registrationData.getContactTelephone(), byForContactTelephone);
        waitUntilVisibilityElementLocated(byForPassword);
        typeInto(registrationData.getPassword(), byForPassword);
        waitUntilVisibilityElementLocated(ByForConfirmPassword);
        typeInto(registrationData.getConfirmPassword(), ByForConfirmPassword);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        click(byForCheckBoxEmail);
        waitUntilVisibilityElementLocated(byForRegistrationBtn);
        click(byForRegistrationBtn);
    }

    //FirstName
    boolean checkInputFirstName(RegistrationData registrationData) {
        enterValues(registrationData);
        takeScreenShot("prScreen/Registration and login/Registration", "FirstNameImg", getDriver());
        waitUntilVisibilityElementLocated(byForErrorFirstName);
        return (registrationData.getError().equals(getText(byForErrorFirstName)));
    }

    public boolean checkInputLastName(RegistrationData registrationData) {
        enterValues(registrationData);
        takeScreenShot("prScreen/Registration and login/Registration", "LastNameImg", getDriver());
        waitUntilVisibilityElementLocated(byForErrorLastName);
        return (registrationData.getError().equals(getText(byForErrorLastName).toString()));
    }

    public boolean checkInputEmail(RegistrationData registrationData) {

        enterValues(registrationData);
        takeScreenShot("prScreen/Registration and login/Registration", "EmailImg", getDriver());
        waitUntilVisibilityElementLocated(byForErrorEmail);
        return (registrationData.getError().equals(getText(byForErrorEmail)));

    }

    public boolean checkInputPassword(RegistrationData registrationData) {

        enterValues(registrationData);
        takeScreenShot("prScreen/Registration and login/Registration", "PasswordImg", getDriver());
        return (registrationData.getError().equals(getText(byForErrorPassword)));

    }

    public boolean checkInputConfirmPassword(RegistrationData registrationData) {
        enterValues(registrationData);
        takeScreenShot("prScreen/Registration and login/Registration", "ConfirmPasswordImg", getDriver());
        waitUntilVisibilityElementLocated(byForConfirmError);
        return (registrationData.getError().equals(getText(byForConfirmError)));
    }

    public boolean checkInputPhone(RegistrationData registrationData) {
        enterValues(registrationData);
        takeScreenShot("prScreen/Registration and login/Registration", "PhoneImg", getDriver());
        return (registrationData.getError().equals(getText(byForErrorPhone)));
    }

    public boolean registrationProcess(RegistrationData registrationData) {
        enterValues(registrationData);
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            Thread.sleep(50000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        String str = getDriver().getCurrentUrl();
        takeScreenShot("prScreen/Registration and login/Registration", "registratioProcesImg", getDriver());
        return (str.equals(registrationData.getError()));
    }

    public boolean checkInputDatOfBirth(RegistrationData registrationData) {

        enterValues(registrationData);
        takeScreenShot("prScreen/Registration and login/Registration", "DatOfBirthErrorImg", getDriver());
        waitUntilVisibilityElementLocated(byForErrorBirthDate);
        return (registrationData.getError().equals(getText(byForErrorBirthDate)));

    }
}
