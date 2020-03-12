import Utils.ReusableMethods;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Basepage {

    private static Logger log = LogManager.getLogger(FBLoginPage.class.getName());

    ////button[@type="submit"]/span[text()='Post']

    @FindBy(xpath = "//textarea[contains(@title, 'Write something')]")
    public WebElement writePostclick;

    @FindBy(xpath = "//div[contains(text(), 'Write something')]")
    public WebElement writePostText;

    @FindBy(xpath = "//button[@type='submit']/span[text()='Post']")
    public WebElement postBtn;


    public Basepage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickOnpost(WebDriver driver) {

        ReusableMethods.waitForElement(driver, writePostclick);
        writePostclick.click();

    }

    public void writeOnpost(WebDriver driver , String writePost) {
        writePostText.sendKeys(writePost);
    }

    public void clickOnpostBtn(WebDriver driver) {

        ReusableMethods.clickElementNormalClick(driver, postBtn );

    }






}
