import Utils.ReusableMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FBLoginPage {

    private static Logger log = LogManager.getLogger(FBLoginPage.class.getName());

    String url= "https://www.facebook.com/";


    @FindBy(xpath = "//input[@id='email']")
    public WebElement userName;

    @FindBy(xpath = "//input[@id='pass']")
    public WebElement password;

    @FindBy(xpath = "//input[@value='Log In']")
    public WebElement logInBtn;

    public FBLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }



    public void loginSteps() {

        userName.sendKeys(BaseTest.prop.getProperty("userName"));
        password.sendKeys(BaseTest.prop.getProperty("passWord"));
        logInBtn.click();

    }


}
