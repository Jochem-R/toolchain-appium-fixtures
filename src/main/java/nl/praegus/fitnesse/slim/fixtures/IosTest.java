package nl.praegus.fitnesse.slim.fixtures;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.ios.IOSTouchAction;
import nl.hsac.fitnesse.fixture.slim.web.annotation.TimeoutPolicy;
import nl.hsac.fitnesse.fixture.slim.web.annotation.WaitUntil;
import nl.praegus.fitnesse.slim.util.IosHelper;
import org.openqa.selenium.WebElement;
/**
 * Specialized class to test iOS applications using Appium.
 */
public class IosTest extends AppiumTest<IOSElement, IOSDriver<IOSElement>> {
    public IosTest() {
        super();
    }

    public IosTest(int secondsBeforeTimeout) {
        super(secondsBeforeTimeout);
    }

    @Override
    protected IosHelper getAppiumHelper() {
        return (IosHelper) super.getAppiumHelper();
    }

    public boolean resetApp() {
        getDriver().resetApp();
        return true;
    }

    @WaitUntil
    public boolean dragTo(String dragPlace, String targetPlace) {
        return dragTo(dragPlace, targetPlace, new IOSTouchAction(getDriver()));
    }

    @Override
    @WaitUntil(TimeoutPolicy.RETURN_FALSE)
    public boolean isVisible(String place) {
        return isVisibleIn(place, null);
    }

    /**
     * Determines whether element can be see in window.
     *
     * @param place     element to check.
     * @param container parent of place.
     * @return true if element is displayed and in viewport.
     */
    @Override
    @WaitUntil(TimeoutPolicy.RETURN_FALSE)
    public boolean isVisibleIn(String place, String container) {
        return isVisibleImpl(place, container, true);
    }

    /**
     * Determines whether element is somewhere in window.
     *
     * @param place element to check.
     * @return true if element is displayed.
     */
    @Override
    @WaitUntil(TimeoutPolicy.RETURN_FALSE)
    public boolean isVisibleOnPage(String place) {
        return isVisibleOnPageIn(place, null);
    }

    /**
     * Determines whether element is somewhere in window.
     *
     * @param place     element to check.
     * @param container parent of place.
     * @return true if element is displayed.
     */
    @Override
    @WaitUntil(TimeoutPolicy.RETURN_FALSE)
    public boolean isVisibleOnPageIn(String place, String container) {
        return isVisibleImpl(place, container, false);
    }

    @Override
    protected boolean isVisibleImpl(String place, String container, boolean checkOnScreen) {
        WebElement element = getElementToCheckVisibility(place, container);
        AppiumDriver<IOSElement> driver = getDriver();
        if (element == null) {
            return false;
        } else {
            int elementLocation = element.getLocation().getY();
            int windowLocation = driver.manage().window().getSize().getHeight();

            if (elementLocation > 0 && elementLocation < windowLocation) {
                return true;
            } else {
                return false;
            }
        }

    }

}
