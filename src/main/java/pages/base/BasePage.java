package pages.base;

import driver.MyDriver;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

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
}
