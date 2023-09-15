package pages;

import static org.assertj.core.api.Assertions.assertThat;

import driver.MyDriver;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

@Getter
public class MainPage extends BasePage<MainPage> {
    private final String URL = "https://www.livescore.com/en/";

    @FindBy(xpath = ".//button[@id='simpleCookieBarCloseButton']")
    private WebElement closeCookieButton;

    @FindBy(xpath = ".//div[@id='content-center']")
    private WebElement contentOnPage;

    @FindBy(xpath = ".//span[contains(@id,'status-or-time')]")
    private List<WebElement> upcomingMatches;

    @FindBy(xpath = ".//a[contains(@data-testid,'match-calendar')]")
    private List<WebElement> calendarDates;

    @FindBy(xpath = ".//div[contains(@id,'match-row')]//div[@data-testid='spinner']")
    private WebElement loadSpinner;

    public MainPage(MyDriver driver) {
        super(driver);
    }

    @Override
    public void isLoaded() {
        assertThat(driver.isElementsVisibleAfterShortWait(contentOnPage))
                .as("Content should be visible")
                .isTrue();

        if (driver.isElementsVisibleAfterShortWait(closeCookieButton)) {
            driver.clickWhenReadyAfterShortWait(closeCookieButton);
        }
    }

    @Override
    public void load() {
        driver.get(URL);
        driver.waitForElementToBeDisplayedAfterShortWait(contentOnPage);
    }

    public MatchPage openFirstUpcomingMatch() {
        List<WebElement> dates = driver.getListElementsWhenVisibleAfterShortWait(calendarDates);
        List<WebElement> matches;

        if (!dates.isEmpty()) {
            for (int i = dates.size() - 3; i < dates.size() - 1; i++) {
                driver.clickWhenReadyAfterShortWait(dates.get(i));

                matches = driver.getListElementsWhenVisibleAfterShortWait(upcomingMatches);

                if (!matches.isEmpty()) {
                    for (WebElement element : matches) {
                        if (!element.getText().equals("FT")) {
                            driver.clickWhenReadyAfterShortWait(element);
                            return new MatchPage(driver).get();
                        }
                    }
                }
            }
        }
        return new MatchPage(driver).waitLoaded();
    }
}
