package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AppleStorePage extends BasePage {

    private static final String IPHONE_BUTTON = "//div[@class='brand-box__title']/a[contains(@href,'iphone')]";

    public AppleStorePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnIphoneButton() {
        driver.findElement(By.xpath(IPHONE_BUTTON)).click();
    }
}
