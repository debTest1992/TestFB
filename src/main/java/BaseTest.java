import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public static Properties prop;
//    public static ExtentHtmlReporter htmlReporter;
//    public static ExtentReports extent;
//    public static ExtentTest test;
    private static Logger log = LogManager.getLogger(BaseTest.class.getName());
    FileInputStream fis;

    @BeforeTest
    public void baseTest() throws IOException, InterruptedException {
        prop = new Properties();
        fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
        prop.load(fis);

        if (prop.getProperty("browser").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
            System.out.println(
                    "\n**** Exection Started ->-> Launching Chrome Driver ****\n");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("test-type");
            options.addArguments("disable-popup-blocking");
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new ChromeDriver(capabilities);
            driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(2000, TimeUnit.SECONDS);

            driver.get("https://www.facebook.com/");
            FBLoginPage loginPage= new FBLoginPage(driver);
            loginPage.loginSteps();

            //Handle popup
            driver.switchTo().alert().dismiss();
        }

    }
}
