$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/ElsaLogin.feature");
formatter.feature({
  "name": "Login to Application",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@login"
    }
  ]
});
formatter.scenario({
  "name": "A valid email id is allowed to login",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@login"
    }
  ]
});
formatter.step({
  "name": "Navigate to login page",
  "keyword": "Given "
});
formatter.match({
  "location": "stepDefinitions.LoginPageSteps.navigateToLogin()"
});
formatter.result({
  "status": "passed"
});
});