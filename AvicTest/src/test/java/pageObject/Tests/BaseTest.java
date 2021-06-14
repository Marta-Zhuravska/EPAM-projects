package pageObject.Tests;

import PageObject.AppleStorePage;
import PageObject.HomePage;
import PageObject.IphonePage;
import PageObject.SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class BaseTest {
    private WebDriver driver;
    private static final String AVIC_URL = "https://avic.ua/ua";

    @BeforeTest
    public void profileSetUp() {
        chromedriver().setup();
    }

    @BeforeMethod
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(AVIC_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HomePage getHomePage() {
        return new HomePage(getDriver());//для виклику методів класу хоумпейдж
    }

    public AppleStorePage getAppleStorePage() {
        return new AppleStorePage(getDriver());
    }

    public IphonePage getIphonePage() {
        return new IphonePage(getDriver());
    }
    public SearchResultsPage getSearchResultPage(){
        return new SearchResultsPage(getDriver());
    }
}
