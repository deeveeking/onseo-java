package utils;

import driver.MyDriver;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class SelectDriver {

    private SelectDriver() {
        throw new IllegalStateException("This is utility class.");
    }

    public static MyDriver getChromeDriver() {
        ChromeOptions chromeOptions = getChromeOptions();

        return new MyDriver(new ChromeDriver(chromeOptions));
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

        return chromeOptions;
    }
}
