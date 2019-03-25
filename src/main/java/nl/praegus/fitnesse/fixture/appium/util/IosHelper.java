package nl.praegus.fitnesse.fixture.appium.util;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import nl.praegus.fitnesse.fixture.appium.util.by.AppiumHeuristicBy;
import nl.praegus.fitnesse.fixture.appium.util.by.IOSBy;
import nl.praegus.fitnesse.fixture.appium.util.by.IsDisplayedFilter;
import nl.praegus.fitnesse.fixture.appium.util.scroll.IosScrollHelper;
import org.openqa.selenium.By;

import java.util.function.Function;

import static nl.hsac.fitnesse.fixture.util.FirstNonNullHelper.firstNonNull;
import static nl.hsac.fitnesse.fixture.util.selenium.by.TechnicalSelectorBy.byIfStartsWith;

/**
 * Specialized helper to deal with appium's iOS web getDriver.
 */
public class IosHelper extends AppiumHelper<IOSElement, IOSDriver<IOSElement>> {
    private static final Function<String, By> IOS_UI_AUTOMATION_BY = byIfStartsWith("uiAutomator", MobileBy::IosUIAutomation);
    private static final Function<String, By> IOS_CLASS_CHAIN_BY = byIfStartsWith("iOSClassChain", MobileBy::iOSClassChain);
    private static final Function<String, By> IOS_NS_PREDICATE_STRING_BY = byIfStartsWith("iOSNsPredicate", MobileBy::iOSNsPredicateString);

    public IosHelper() {
        setScrollHelper(new IosScrollHelper(this));
    }

    @Override
    public By placeToBy(String place) {
        return firstNonNull(place,
                super::placeToBy,
                IOS_UI_AUTOMATION_BY,
                IOS_CLASS_CHAIN_BY,
                IOS_NS_PREDICATE_STRING_BY);
    }

    protected By getElementBy(String place) {
        return IOSBy.heuristic(place);
    }

    protected By getClickBy(String place) {
        return new AppiumHeuristicBy<>(IOSBy.buttonHeuristic(place), IOSBy.heuristic(place));
    }

    protected By getContainerBy(String container) {
        return IOSBy.heuristic(container);
    }

    protected By getElementToCheckVisibilityBy(String text) {
        return new AppiumHeuristicBy<>(new IsDisplayedFilter<IOSElement>(), MobileBy.AccessibilityId(text), IOSBy.partialText(text));
    }
}
