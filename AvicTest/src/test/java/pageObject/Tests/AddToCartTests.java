package pageObject.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddToCartTests extends BaseTest {
    private static final String AVIC_URL = "https://avic.ua/ua";
    private static final String TOSHIBA = "Toshiba";

    @Test(description = "check that button contains HomePage URL")// first home task test via PageFactory;
    public void checkButtonContainsURL() {
        getHomePage().clickOnHomePageButton();
        getHomePage().getCurrentUrl();
        assertEquals(getHomePage().getCurrentUrl(), AVIC_URL, "URL does not match the HomePage");
    }

    @Test(description = "check Support chat is enabled")// second home task test via PageFactory;
    public void checkSupportChat() {
        getHomePage().clickOnConditionerButton();
        getHomePage().waitForElementToBeClickable(30);
        getHomePage().supportChatIsClickable();

    }

    @Test(description = "checking the parameter 'Manufacturer' Toshiba")// third home task test via PageFactory;
    public void checkParameterManufacturer() {
        getHomePage().clickOnConditionerButton();
        getHomePage().clickOnFiltersButton();
        getIphonePage().waitForPageLoadingComplete(30);
        getHomePage().getSearchResultsListOfConditioners();
        for (WebElement webElement : getHomePage().getSearchResultsListOfConditioners()) {
            assertTrue(webElement.getText().contains(TOSHIBA));
        }
    }


    private static final String EXPECTED_AMOUNT_OF_PRODUCT_IN_CART = "1";

    @Test(priority = 4)
    public void checkAddToCart() {
        getHomePage().clickOnCatalogueButton();
        getHomePage().clickOnAppleStoreButton();
        getAppleStorePage().clickOnIphoneButton();
        getIphonePage().waitForPageLoadingComplete(30);
        getIphonePage().clickOnAddToCartButton();
        getIphonePage().waitForVisibilityOfElement(30, getIphonePage().getAddToCartPopup());
        getIphonePage().clickOnContinueShoppingButton();
        assertEquals(getHomePage().getTextOfAmountsProductInCart(), EXPECTED_AMOUNT_OF_PRODUCT_IN_CART);
    }
}
