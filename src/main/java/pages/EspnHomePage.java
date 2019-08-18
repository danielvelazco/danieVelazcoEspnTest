package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EspnHomePage {
    //WebDriver driver;

    public EspnHomePage(WebDriver driver){
            PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//body//a[@name='&lpos=sitenavdefault+sitenav_nfl']//span//span[1]")
    public WebElement nflMenu;

    @FindBy(xpath = "//a[@name='&lpos=subnav+subnav_nfl_standings']//span[@class='link-text'][contains(text(),'Standings')]")
    public WebElement standingSubmenu;

    @FindBy(name = "google_ads_iframe_/21783347309/espn.com/frontpage/index_1")
    public WebElement popup;

    @FindBy(xpath = "//a[@name='&lpos=espnplus:leftrail:upsellmodule']")
    public WebElement suscribeButtom;

    public void clickNflMenu(){
        nflMenu.click();
    }

    public NflStandingPage clickStandingSubmenu(WebDriver driver){
        standingSubmenu.click();
        return new NflStandingPage(driver);
    }

    public boolean popupDisplayed(){
        return popup.isDisplayed();
    }

    public void moveMouseFromMenu(WebDriver driver, WebElement target){
        Actions builder = new Actions(driver);
        Action mouseOver = builder
                .moveToElement(target)
                .build();
        mouseOver.perform();
    }

}
