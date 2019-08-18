package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NflStandingPage {
    //WebDriver driver;

    //public NflStandingPage(){}

    public NflStandingPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='dropdown dropdown--md h-100 pageHeading__team-stats-dropdown']//select[@class='dropdown__select']")
    public WebElement resourcesList;


    public NflMachinePage selectItemfromList(String text, WebDriver driver){
        Select resList = new Select(resourcesList);
        resList.selectByVisibleText(text);
        return new NflMachinePage(driver);
    }
}
