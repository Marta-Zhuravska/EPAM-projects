package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(xpath = "//input[@id='input_search']")//initializing elements using PageFactory
    private WebElement searchInput;
    @FindBy(xpath = "//span[@class='sidebar-item']")
    private WebElement productCatalogueButton;

    @FindBy(xpath = "//img[@src='https://avic.ua/assets/frontend/images/avic.svg']")
    private WebElement homePageButton;
    @FindBy(xpath = "//div[@class='top-links__left flex-wrap']//a[@href='/kondiczioneryi']")
    private WebElement conditionersButton;
    @FindBy(xpath = "//jdiv[@class=\"hoverl_85b0\"]")
    private WebElement supportChatButton;
    @FindBy(xpath = "//a[@href=\"https://avic.ua/ua/kondiczioneryi/proizvoditel--toshiba\"]")
    private WebElement filtersButton;
    private static final String CONDITIONERS_LIST_DESCRIPTION = "//div[@class='prod-cart__descr']";

    private static final String APPLE_STORE_BUTTON = "//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'apple-store')]";
    private static final String AMOUNT_OF_PRODUCTS_IN_CART = "//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'cart_count')]";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchByKeyWord(final String keyword) {
        searchInput.sendKeys(keyword, Keys.ENTER);

    }

    public void clickOnCatalogueButton() {
        productCatalogueButton.click();
    }

    public void clickOnAppleStoreButton() {
        driver.findElement(By.xpath(APPLE_STORE_BUTTON)).click();
    }

    public String getTextOfAmountsProductInCart() {
        return driver.findElement(By.xpath(AMOUNT_OF_PRODUCTS_IN_CART)).getText();
    }


    public void clickOnHomePageButton() {//my test 1, raw 1
        homePageButton.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void clickOnConditionerButton() {
        conditionersButton.click();
    }

    public void supportChatButtonClick() {
        supportChatButton.click();
    }

    public By supportChatPopUp() {
        return By.xpath(String.valueOf(supportChatButton));
    }

    public void supportChatIsClickable() {
        supportChatButton.click();
    }

    public void clickOnFiltersButton() {
        filtersButton.click();
    }

    public List<WebElement> getSearchResultsListOfConditioners() {
        return driver.findElements(By.xpath(CONDITIONERS_LIST_DESCRIPTION));
    }
}
