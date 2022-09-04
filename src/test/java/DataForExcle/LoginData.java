package DataForExcle;

public class LoginData {

    private String Email;
    private  String Password;
    private  String Error;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public LoginData() {
    }

    public LoginData(String email, String password, String error) {
        Email = email;
        Password = password;
        Error = error;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Error='" + Error + '\'' +
                '}';
    }
}
