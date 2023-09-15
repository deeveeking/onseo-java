package pages;

import static org.assertj.core.api.Assertions.assertThat;

import driver.MyDriver;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

@Getter
public class LeftNavPage extends BasePage<LeftNavPage> {

    @FindBy(xpath = ".//a[contains(@id,'settings')]")
    private WebElement settingsButton;

    @FindBy(xpath = ".//div[contains(@id,'menu-section')]")
    private WebElement menuSection;

    public LeftNavPage(MyDriver driver) {
        super(driver);
    }

    @Override
    public void isLoaded() {
        assertThat(driver.isElementsVisibleAfterShortWait(menuSection))
                .as("'Menu section' should be visible")
                .isTrue();
    }

    @Override
    public void load() {
        driver.waitForElementToBeDisplayedAfterShortWait(menuSection);
    }
}
