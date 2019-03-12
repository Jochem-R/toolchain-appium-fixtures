package nl.hsac.fitnesse.fixture.slim.mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import nl.hsac.fitnesse.fixture.util.mobile.AndroidHelper;

/**
 * Specialized class to test Android applications using Appium.
 */
public class AndroidTest extends MobileTest<AndroidElement, AndroidDriver<AndroidElement>> {
	public AndroidTest() {
		super();
	}

	public AndroidTest(int secondsBeforeTimeout) {
		super(secondsBeforeTimeout);
	}

	@Override
	public boolean pressEnter() {
		getMobileHelper().driver().pressKey(new KeyEvent(AndroidKey.NUMPAD_ENTER));
		return true;
	}

	@Override
	protected AndroidHelper getMobileHelper() {
		return (AndroidHelper) super.getMobileHelper();
	}
}
