package avic;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.Keys.ENTER;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AvicTests {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");//connecting the source of driver
    }

    @BeforeMethod
    public void testSetUp() {
        driver = new ChromeDriver();//new example of chromedriver
        driver.manage().window().maximize();
        driver.get("https://avic.ua/ua");
    }

    @Test(description = "check that button contains HomePage URL")
    public void checkButtonContainsURL() {
        driver.findElement(By.xpath("//img[@src='https://avic.ua/assets/frontend/images/avic.svg']")).click();
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, "https://avic.ua/ua", "URL does not match the HomePage");
    }

    @Test(description = "check Support chat is enabled")
    public void checkSupportChat() {
        driver.findElement(By.xpath("//div[@class='top-links__left flex-wrap']//a[@href='/kondiczioneryi']")).click();//click th 'Conditioners' button
        WebDriverWait wait = new WebDriverWait(driver, 30);//waiter
        wait.until(ExpectedConditions.elementToBeClickable(xpath("//jdiv[@class=\"hoverl_85b0\"]")));
        driver.findElement(By.xpath("//jdiv[@class=\"hoverl_85b0\"]")).isEnabled();//checking the Support chat button is clickable

    }

    @Test(description = "checking the parameter 'Manufacturer' Toshiba")
    public void checkParameterManufacturer() {
        driver.findElement(By.xpath("//div[@class='top-links__left flex-wrap']//a[@href='/kondiczioneryi']")).click();//click th 'Conditioners' button
        driver.findElement(By.xpath("//a[@class=\"filter-link\" and @href=\"https://avic.ua/ua/kondiczioneryi/proizvoditel--toshiba\"]")).click();
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        List<WebElement> elementsList = driver.findElements(xpath("//div[@class='prod-cart__descr']"));
        for (WebElement webElement : elementsList) {
            assertTrue(webElement.getText().contains("Toshiba"));
        }
    }

    @Test(priority = 1)
    public void checkThatUrlContainsSearchQuery() {
        driver.findElement(By.xpath("//input[@id='input_search']")).clear();
        driver.findElement(By.xpath("//input[@id='input_search']")).sendKeys("iPhone 11", Keys.ENTER);
        assertTrue(driver.getCurrentUrl().contains("query=iPhone"));

    }

    @Test(priority = 2)
    public void checkElementsAmountOnSearchPage() {
        driver.findElement(xpath("//input[@id='input_search']")).sendKeys("iPhone 11", ENTER);//вводим в поиск iPhone 11
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//неявное ожидание 30 сек
        List<WebElement> elementsList = driver.findElements(xpath("//div[@class='prod-cart__descr']"));//собрали элементы поиска в лист
        int actualElementsSize = elementsList.size();//узнали количество элементов в листе
        assertEquals(actualElementsSize, 12);//сравнили количество элементов актуальное с тем которое ожидаем
    }

    @Test(priority = 3)
    public void checkThatSearchResultsContainsSearchWord() {
        driver.findElement(xpath("//input[@id='input_search']")).sendKeys("iPhone 11", ENTER);//вводим в поиск iPhone 11
        List<WebElement> elementList = driver.findElements(xpath("//div[@class='prod-cart__descr']"));//собрали элементы поиска в лист
        for (WebElement webElement : elementList) { //прошлись циклом и проверили что каждый элемент листа содержит текс iPhone 11
            assertTrue(webElement.getText().contains("iPhone 11"));
        }
    }

    @Test(priority = 4)
    public void checkAddToCart() {
        driver.findElement(xpath("//span[@class='sidebar-item']")).click();//каталог товаров
        driver.findElement(xpath("//ul[contains(@class,'sidebar-list')]//a[contains(@href, 'apple-store')]")).click();//Apple Store
        driver.findElement(xpath("//div[@class='brand-box__title']/a[contains(@href,'iphone')]")).click();//iphone
        new WebDriverWait(driver, 30).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));//wait for page loading
        driver.findElement(xpath("//a[@class='prod-cart__buy'][contains(@data-ecomm-cart, '64GB Black (MGDX3)')]")).click();//add to cart iphone
        WebDriverWait wait = new WebDriverWait(driver, 30);//ждем пока не отобразится попап с товаром добавленным в корзину
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));

        driver.findElement(xpath("//div[@class='btns-cart-holder']//a[contains(@class,'btn--orange')]")).click();//продолжить покупки
        String actualProductsCountInCart =
                driver.findElement(xpath("//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'cart_count')]")).getText();//получили 1 которая в корзине (один продукт)
        assertEquals(actualProductsCountInCart, "1");
    }


    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
