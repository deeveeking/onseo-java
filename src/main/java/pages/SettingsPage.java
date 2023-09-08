package pages;

import driver.MyDriver;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Getter
public class SettingsPage extends BasePage<SettingsPage> {

    @FindBy(xpath = ".//button[contains(@data-testid,'apply-button')]")
    private WebElement applySettingsButton;

    @FindBy(xpath = ".//label[contains(@id,'SELECT')]")
    private WebElement selectedTimeZone;

    @FindBy(xpath = ".//div[contains(@class,'selectItem')]")
    private List<WebElement> timeZones;

    public SettingsPage(MyDriver driver) {
        super(driver);
    }

    @Override
    public void isLoaded() {
        assertThat(driver.isElementsVisibleAfterShortWait(applySettingsButton))
                .as("'Apply' button should be visible")
                .isTrue();
    }

    @Override
    public void load() {
        driver.waitForElementToBeDisplayedAfterShortWait(applySettingsButton);
    }

    public void changeTimeZone(String newZone) {
        driver.clickWhenReadyAfterShortWait(selectedTimeZone);

        List<WebElement> timeZonesList = driver.getListElementsWhenVisibleAfterShortWait(timeZones);

        for (WebElement element:timeZonesList) {
            if (element.getText().equals(newZone)) {
                element.click();
                break;
            }
        }

        driver.clickWhenReadyAfterShortWait(applySettingsButton);
    }
}
