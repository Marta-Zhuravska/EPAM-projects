package pageObject.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.Keys.ENTER;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTests extends BaseTest {
    private static final String SEARCH_KEYWORD = "iPhone 11";
    private static final String EXPECTED_SEARCH_QUERY = "iPhone";

    @Test(priority = 1)//checking the URL corresponds to the search query
    public void checkThatUrlContainsSearchQuery() {
        getHomePage().searchByKeyWord(SEARCH_KEYWORD);
        assertTrue(getDriver().getCurrentUrl().contains(EXPECTED_SEARCH_QUERY));

    }

    @Test(priority = 2)//checking the amount of elements of the product`s page
    public void checkElementsAmountOnSearchPage() {
        getHomePage().searchByKeyWord(SEARCH_KEYWORD);
        getHomePage().implicitWait(30);
        assertEquals(getSearchResultPage().getSearchResultsCount(), 12);
    }

    @Test(priority = 3)//checking all elements on the page contain the search keyword
    public void checkThatSearchResultsContainsSearchWord() {
        getHomePage().searchByKeyWord(SEARCH_KEYWORD);
        for (WebElement webElement : getSearchResultPage().getSearchResultsList()) {
            assertTrue(webElement.getText().contains(SEARCH_KEYWORD));
        }
    }
}
