package pages.base;

import driver.MyDriver;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import utils.WaitUtils;

@Getter
@Slf4j
public class BasePage <T extends LoadableComponent<T>> extends LoadableComponent<T> {
    protected MyDriver driver;

    @FindBy(xpath = ".//span[contains(@id,'menu-open')]")
    private WebElement openLeftMenuButton;

    public BasePage(MyDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);

        log.info("PageFactory init elements.");
    }

    @Override
    protected void load() {
        // implement not needed
    }

    @Override
    protected void isLoaded() throws Error {
        // implement not needed
    }

    public T waitLoaded(int timeToWait) {
        long timeout = System.currentTimeMillis() + timeToWait * 1000L;
        while (System.currentTimeMillis() < timeout) {
            try {
                isLoaded();

                return (T) this;
            } catch (TimeoutException timeoutException) {
                // continue waiting
            }
        }
        throw new TimeoutException(String.format("Page %s wasn't loaded.", getClass().getSimpleName()));
    }

    public T waitLoaded() {
        return waitLoaded(WaitUtils.LONG_TIMEOUT);
    }
}
