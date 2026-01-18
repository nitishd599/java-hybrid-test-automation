package pages.mobile;

import core.actions.MobileActions;
import org.openqa.selenium.By;

public class LoginScreen {

    private final MobileActions actions;

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login");

    public LoginScreen() {
        this.actions = new MobileActions();
    }

    public LoginScreen enterUsername(String user) {
        actions.type(username, user);
        return this;
    }

    public LoginScreen enterPassword(String pass) {
        actions.type(password, pass);
        return this;
    }
}
