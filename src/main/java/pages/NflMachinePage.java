package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NflMachinePage {
    //WebDriver driver;

    public NflMachinePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[@class='logo']")
    public WebElement playoffMachineLabel;

    public boolean checkLabelPage(){
       boolean value = playoffMachineLabel.isDisplayed();
       return value;
    }

    public String getTitleFromMachinePage(WebDriver driver){
        return driver.getTitle();
    }
}
