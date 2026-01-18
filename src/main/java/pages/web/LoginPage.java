package pages.web;

import core.actions.ElementActions;
import org.openqa.selenium.By;

public class LoginPage {

    private final ElementActions actions;

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login");

    public LoginPage() {
        this.actions = new ElementActions();
    }

    public LoginPage enterUsername(String user) {
        actions.type(username, user);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        actions.type(password, pass);
        return this;
    }

}
