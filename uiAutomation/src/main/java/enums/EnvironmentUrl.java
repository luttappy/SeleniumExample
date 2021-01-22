package enums;

public enum EnvironmentUrl {
    QA("https://qa.com"),
    DEV("https://qa.com"),
    CERT("https://qa.com"),
    PROD("https://qa.com");

    private final String envurl;

    EnvironmentUrl(String envurl) {
        this.envurl = envurl;
    }

    public String getEnvurl() {
        return envurl;
    }
}
