package orangHRM;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.tagName("button");
    private By errorMessage =  By.cssSelector("div.oxd-alert-content--error p.oxd-alert-content-text");

    public void setUsername(String username){
        set(usernameField, username);
    }

    public void setPassword(String password){
        set(passwordField, password);
    }

    public void clickLoginButton(){
        click(loginButton);
    }
}
