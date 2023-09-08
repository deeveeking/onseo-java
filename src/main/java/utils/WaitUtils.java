package utils;

import static org.assertj.core.api.Assertions.fail;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public interface WaitUtils extends WebDriver {

    int SHORT_TIMEOUT = 5;

    default WebElement waitForElementToBeDisplayed(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(this, Duration.ofSeconds(timeout));

        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    default WebElement waitForElementToBeDisplayedAfterShortWait(WebElement element) {
        return waitForElementToBeDisplayed(element, SHORT_TIMEOUT);
    }

    default boolean isElementsVisibleAfterShortWait(WebElement element) {
        return isElementsVisible(element, SHORT_TIMEOUT);
    }

    default boolean isElementsVisible(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(this, Duration.ofSeconds(timeout));

        try {
            return wait.until(el -> element.isDisplayed());
        } catch (TimeoutException e) {
            return false;
        }
    }

    default void clickWhenReadyAfterShortWait(WebElement element) {
        clickWhenReady(element, SHORT_TIMEOUT);
    }

    default void clickWhenReady(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(this, Duration.ofSeconds(timeout));

        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    default List<WebElement> getListElementsWhenVisibleAfterShortWait(List<WebElement> elements) {
        return getListElementsWhenVisible(elements, SHORT_TIMEOUT);
    }

    default List<WebElement> getListElementsWhenVisible(List<WebElement> elements, int timeout) {
        for (WebElement element : elements) {
            if (!isElementsVisible(element, timeout)) {
                fail("One element from list not visible ", element);
            }
        }
        return elements;
    }
}
