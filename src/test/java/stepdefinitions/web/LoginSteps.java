package stepdefinitions.web;

import io.cucumber.java.en.Given;
import pages.web.LoginPage;

public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    @Given("user logs in with valid credentials")
    public void userLogsIn() {
        loginPage
                .enterUsername("admin")
                .enterPassword("password");
                //.clickLogin();
    }
}
