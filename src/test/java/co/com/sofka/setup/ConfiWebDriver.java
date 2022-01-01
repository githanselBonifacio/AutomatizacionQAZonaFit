package co.com.sofka.setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConfiWebDriver {

    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String WEBDRIVER_CHROME_DRIVER_PATH = "src/test/resources/drivers/windows/chrome/chromedriver.exe";

    private static final String ZONA_FIT_URL = "https://zonafit.co//";

    protected WebDriver driver;

    private void setUpWebdriver(){
        System.setProperty(WEBDRIVER_CHROME_DRIVER, WEBDRIVER_CHROME_DRIVER_PATH);
    }

    private void setUpWebdriverUrl(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ZONA_FIT_URL);
    }
    protected void generalSetup(){
        setUpWebdriver();
        setUpWebdriverUrl();
    }
    protected void quiteDriver(){
        driver.quit();
    }

}
