package cucumber;

import managers.FileReaderManager;
import managers.PageObjectManager;
import managers.WebDriverManager;

public class TestContext {
    private WebDriverManager webDriverManager;
    private PageObjectManager pageObjectManager;
    private FileReaderManager fileReaderManager;
    private ScenarioContext scenarioContext;

    public TestContext() {
        webDriverManager = new WebDriverManager();
        pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
        scenarioContext = new ScenarioContext();
    }

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public FileReaderManager getFileReaderManager() {
        return fileReaderManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
