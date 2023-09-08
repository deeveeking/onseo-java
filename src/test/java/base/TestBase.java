package base;

import driver.MyDriver;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.SelectDriver;

@Slf4j
public abstract class TestBase {

    protected MyDriver driver;

    @BeforeClass(alwaysRun = true)
    public void init() {
        driver = SelectDriver.getChromeDriver();

        log.info("Init browser on beforeClass");
    }

    @AfterClass(alwaysRun = true)
    public void cleanUp() {
        try {
            driver.quit();
        } catch (NullPointerException nullPointerException) {
            log.warn("Cannot quite from browser.");
        }
    }
}
