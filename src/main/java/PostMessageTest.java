import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class PostMessageTest extends BaseTest{

    @Test
    public void testPost()
    {
        Basepage belements = new Basepage(driver);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //click on post text field
        belements.clickOnpost(driver);

        //write on post text field
        belements.writeOnpost(driver, "Hello world");

        //Click on post btn.
        belements.clickOnpostBtn(driver);

        //verify the entered string is correct or not
        SoftAssert soft = new SoftAssert();

    }
}
