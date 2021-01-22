package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.en.Given;

import static org.hamcrest.MatcherAssert.assertThat;

import managers.FileReaderManager;
import pageObjects.LoginPage;

public class LoginPageSteps {
    LoginPage loginPage;
    TestContext testContext;
    FileReaderManager fileReaderManager;

    public LoginPageSteps(TestContext context) {
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }


    @Given("Navigate to login page")
    public void navigateToLogin() {
        loginPage.navigateToLoginPage();
    }

}
