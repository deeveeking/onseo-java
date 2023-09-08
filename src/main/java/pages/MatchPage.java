package pages;

import driver.MyDriver;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

@Getter
public class MatchPage extends BasePage<MatchPage> {

    @FindBy(xpath = ".//span[@id='score-or-time']")
    private WebElement matchScoreOrTime;

    @FindBy(xpath = ".//span[contains(@id,'status')]")
    private WebElement matchDate;

    public MatchPage(MyDriver driver) {
        super(driver);
    }

    @Override
    public void isLoaded() {
        assertThat(driver.isElementsVisibleAfterShortWait(matchScoreOrTime))
                .as("Match time or score should be visible")
                .isTrue();
    }

    @Override
    public void load() {
        driver.waitForElementToBeDisplayedAfterShortWait(matchScoreOrTime);
    }
}
