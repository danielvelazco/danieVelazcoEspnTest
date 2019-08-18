import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.EspnHomePage;
import pages.NflMachinePage;
import pages.NflStandingPage;

import java.util.concurrent.TimeUnit;

public class EspnTest {
    WebDriver driver;
    EspnHomePage espnHomePage;
    // NflStandingPage nflStandingPage;
    // NflMachinePage nflMachinePage;

    @BeforeClass
    @Parameters({"app","browser"})
    public void setup(String app, String browser){

        if(browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", "geckodriver");
            driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
            driver = new ChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(app);
    }

    @Test
    @Parameters({"title"})
    public void navigateToNflPlayoffMachine(String machinePageTitle) throws InterruptedException {
        //home page
        espnHomePage = new EspnHomePage(driver);
        if (espnHomePage.popupDisplayed()){
            driver.switchTo().frame(espnHomePage.popup);
            //Thread.sleep(5000);
            driver.close();
            driver.switchTo().defaultContent();
        }

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(espnHomePage.nflMenu));
        espnHomePage.clickNflMenu();
        espnHomePage.moveMouseFromMenu(driver, espnHomePage.suscribeButtom);

        //retrieving standing page
        wait.until(ExpectedConditions.visibilityOf(espnHomePage.standingSubmenu));
        NflStandingPage nflStandingPage = espnHomePage.clickStandingSubmenu(driver);

        //retrieving machine page
        wait.until(ExpectedConditions.visibilityOf(nflStandingPage.resourcesList));
        NflMachinePage nflMachinePage = nflStandingPage.selectItemfromList("Playoff Machine", driver);

        //check redirect
        Assert.assertTrue(nflMachinePage.checkLabelPage());
        Assert.assertEquals(nflMachinePage.getTitleFromMachinePage(driver),machinePageTitle);
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
