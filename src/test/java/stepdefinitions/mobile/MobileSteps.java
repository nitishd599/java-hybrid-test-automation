package stepdefinitions.mobile;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.mobile.LoginScreen;

public class MobileSteps {

    private final LoginScreen loginScreen = new LoginScreen();

    @Given("user launches the mobile app")
    public void userLaunchesApp() {
        // App already launched by driver
    }

    @When("user logs in with {string} and {string}")
    public void login(String user, String pass) {
        loginScreen.enterUsername(user).enterPassword(pass);
    }
}
