package dataProviders;

import cucumber.TestContext;
import enums.DriverType;
import enums.EnvironmentType;
import enums.EnvironmentUrl;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ConfigFileReader {
    TestContext testContext;
    private Properties properties;
    private final String propertyFilePath = "src/test/resources/Configuration.properties";

    public ConfigFileReader() {

        BufferedReader reader;
        try {
            URL url = getClass().getResource("Configuration.properties");
//            File file = new File(url.getPath());
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties file not found at " + propertyFilePath);
        }
    }

    public String getDriverPath(String os) {
        String driverPath;
        if (os.contains("Windows"))
            driverPath = properties.getProperty("driverPath");
        else {
            driverPath = new File("").getAbsolutePath();
            System.out.println("driver path is " + driverPath);
        }
        if (driverPath != null)
            return driverPath;
        else
            throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
    }

    public long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (implicitlyWait != null)
            return Long.parseLong(implicitlyWait);
        else
            throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    public String getApplicationUrl() {
        String url = null;
        EnvironmentUrl envurl = null;
        if (properties.getProperty("environment").equalsIgnoreCase("local")) {
            url = properties.getProperty("url");
            if (url != null)
                return url;
            else
                throw new RuntimeException("url not specified in the Configuration.properties file.");
        } else {
            if (System.getenv("url") != null) {
                url = System.getenv("url");
                System.out.println(url);
                if (url.equalsIgnoreCase("QA")) {
                    envurl = EnvironmentUrl.QA;
                    url = envurl.getEnvurl();
                } else if (url.equalsIgnoreCase("DEV")) {
                    envurl = EnvironmentUrl.DEV;
                    url = envurl.getEnvurl();
                } else if (url.equalsIgnoreCase("CERT")) {
                    envurl = EnvironmentUrl.CERT;
                    url = envurl.getEnvurl();
                } else if (url.equalsIgnoreCase("PROD")) {
                    envurl = EnvironmentUrl.PROD;
                    url = envurl.getEnvurl();
                }

                if (url != null)
                    return url;
                else
                    throw new RuntimeException("url not defined in the EnvironmentUrl enum");
            } else {
                if (isValidURL(System.getenv("url"))) {
                    url = System.getenv("url");
                    return url;
                } else
                    throw new RuntimeException("url format not valid ");
            }
        }
    }

    public boolean isValidURL(String urlStr) {
        try {
            URL url = new URL(urlStr);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public DriverType getBrowser() {
        String browserName = properties.getProperty("browser");
        if (browserName == null || browserName.equalsIgnoreCase("chrome"))
            return DriverType.CHROME;
        else if (browserName.equalsIgnoreCase("firefox"))
            return DriverType.FIREFOX;
        else if (browserName.equalsIgnoreCase("safari"))
            return DriverType.SAFARI;
        else
            throw new RuntimeException("Browser Name Key value in config is not matched");
    }

    public EnvironmentType getEnvironment() {
        String environmentName = properties.getProperty("environment");
        if (environmentName == null || environmentName.equals("local"))
            return EnvironmentType.LOCAL;
        else if (environmentName.equalsIgnoreCase("remote"))
            return EnvironmentType.REMOTE;
        else
            throw new RuntimeException("Env name key value in config is not matched");

    }

    public Boolean getWindowSize() {
        String windowSize = properties.getProperty("windowMaximize");
        if (windowSize != null)
            return Boolean.valueOf(windowSize);
        return true;
    }

    public String getReportConfigPath() {
        String reportConfigPath = properties.getProperty("reportConfigPath");
        if (reportConfigPath != null)
            return reportConfigPath;
        else
            throw new RuntimeException("Report Config path not specified in the configuration.properties file");
    }

    public String getselectionCSSColor() {
        String selectionCSSColor = properties.getProperty("selectionCSSColor");
        if (selectionCSSColor != null)
            return selectionCSSColor;
        else
            throw new RuntimeException("selectionCSSColor not specified in the Configuration.property file");
    }
    public String getContentManagerUserId() {
        String contentManagerUserId = properties.getProperty("contentManagerUserId");
        if (contentManagerUserId != null)
            return contentManagerUserId;
        else
            throw new RuntimeException(
                    "Content Manager User Id key value not specified in the Configuration.properties file");
    }

    public String getLoginPassword() {
        String loginPassword = properties.getProperty("loginPassword");
        if (loginPassword != null)
            return loginPassword;
        else
            throw new RuntimeException("LoginPassword key value not specified in Configuration.properties file");
    }

    public String getbrowserStackUserName() {
        String browserStackUserName = properties.getProperty("browserStackUserName");
        if (browserStackUserName != null)
            return browserStackUserName;
        else
            throw new RuntimeException("Browser Stack Username not specified in the Configuration.property file");
    }

    public String getbrowserStackAutomateteKey() {
        String browserStackAutomateteKey = properties.getProperty("browserStackAutomateteKey");
        if (browserStackAutomateteKey != null)
            return browserStackAutomateteKey;
        else
            throw new RuntimeException("Browser Stack key not specified in the Configuration.property file");
    }

    public String getbrowserStackURL() {
        String browserStackURL = properties.getProperty("browserStackURL");
        if (browserStackURL != null)
            return browserStackURL;
        else
            throw new RuntimeException("Browser Stack URL not specified in the Configuration.property file");
    }

    public String getGridURL() {
        String gridURL = properties.getProperty("gridURL");
        if (gridURL != null)
            return gridURL;
        else
            throw new RuntimeException("gridURL key value not specified in Configuration.properties file");

    }

    public String getLocalLogin() {
        String localLogin = properties.getProperty("localLogin");
        if (localLogin != null)
            return localLogin;
        else
            throw new RuntimeException("localLogin key value not specified in Configuration.properties file");

    }



}
