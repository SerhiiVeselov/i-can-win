package page;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

@Getter
public class MainPage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://textbin.net/";

    @FindBy(xpath = "//span[text()='New Paste ']")
    private WebElement newPasteTitle;

    @FindBy(xpath = "//button[contains(text(), 'I agree')]")
    private WebElement iAgreeBtn;

    @FindBy(xpath = "//textarea[@name='content']")
    private WebElement contentTextBox;

    @FindBy(xpath = "//input[@name='title']")
    private WebElement pasteTitleField;

    @FindBy(xpath = "//select[@name='expire']")
    private WebElement pasteExpirationDropDown;

    @FindBy(xpath = "//button[contains(text(), 'Create New Paste')]")
    private WebElement createNewPasteBtn;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement pasteCreatedMsg;

    @FindBy(xpath = "//h5[@class='mt-0']")
    private WebElement createdPasteTitle;

    @FindBy(xpath = "//span[@class='ace_text ace_xml']")
    private WebElement createdPasteText;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void openPage() {
        driver.get("https://textbin.net/");
    }

    public void closeCookiesPopUp() {
        iAgreeBtn.click();
    }

    public void pasteTextContent() {
        contentTextBox.click();
        contentTextBox.sendKeys("Hello from WebDriver");
    }

    public void enterPasteTitle() {
        pasteTitleField.click();
        pasteTitleField.sendKeys("helloweb");
    }

    public void selectPasteExpiration() {
        Select expirationDrpDwn = new Select(pasteExpirationDropDown);
        expirationDrpDwn.selectByValue("10M");
    }

    public void clickCreateNewPasteBtn() throws InterruptedException {
        createNewPasteBtn.click();
    }

    public String checkPasteTitle() {
        return createdPasteTitle.getText();
    }

    public String checkPasteText() {
        return createdPasteText.getText();
    }

}
