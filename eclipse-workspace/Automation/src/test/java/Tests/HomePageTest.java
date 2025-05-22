package Tests;

import WebPages.HomePage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import Tests.Initilization.DriverBase;

import java.time.Duration;
import java.util.Set;

public class HomePageTest extends DriverBase {

    HomePage homePage;

    @BeforeClass
    public void initPageObject() {
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void openHomePage() {
        driver.get(testUrl);  // use testUrl from DriverBase
        Reporter.log("🌐 Opened homepage: " + testUrl, true);
    }

    @AfterMethod
    public void closeExtraTabs() {
        String original = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(original)) {
                driver.switchTo().window(handle).close();
            }
        }
        driver.switchTo().window(original);
    }

    
//Verifies that the loading animation appears and disappears after ~3 seconds. 

    @Test(priority = 1)
    public void verifyLoadingAnimationBehavior() {
        SoftAssert softAssert = new SoftAssert();

        boolean isVisible = homePage.isLoadingAnimationVisible();
        System.out.println();
        Reporter.log("🔄 Loading animation initially visible: " + isVisible, true);
        softAssert.assertTrue(isVisible, "Loading animation should be visible initially");

        long start = System.currentTimeMillis();
        boolean disappeared = homePage.waitForLoadingToDisappear();
        long end = System.currentTimeMillis();
        long duration = end - start;

        Reporter.log("⏱ Loading animation disappeared: " + disappeared +
                     " (Waited ~" + duration + " ms)", true);
        System.out.println();
        softAssert.assertTrue(disappeared, "Loading animation should disappear in reasonable time (~3s)");

        boolean contentVisible = homePage.isMainContentVisible();
        Reporter.log("📄 Main content visible after loader: " + contentVisible, true);
        softAssert.assertTrue(contentVisible, "Main content should be visible after loading");
        System.out.println();
        softAssert.assertAll();
    }

    
//Verifies that the main content visibility and "My Dreams" button become visible.     
    
    @Test(priority = 2)
    public void verifyMainContentVisible() {
        SoftAssert softAssert = new SoftAssert();

        Reporter.log("⌛ Waiting for 'My Dreams' button to be visible...", true);
        homePage.waitForMyDreamsButton();
        boolean contentVisible = homePage.isMainContentVisible();
        Reporter.log("📄 Main content visible after waiting: " + contentVisible, true);
        softAssert.assertTrue(contentVisible, "Main content should be visible");
        System.out.println();
        softAssert.assertAll();
    }

    
//Verifies that the loading animation appears and disappears after ~3 seconds. 
    
    @Test(priority = 3)
    public void verifyMyDreamsButtonOpensNewTabs() {
        SoftAssert softAssert = new SoftAssert();

        String originalWindow = driver.getWindowHandle();
        homePage.waitForMyDreamsButton();
        homePage.clickMyDreams();
        Reporter.log("🖱️ Clicked on 'My Dreams' button", true);
        System.out.println();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> driver.getWindowHandles().size() == 3);

        Set<String> allWindows = driver.getWindowHandles();
        allWindows.remove(originalWindow);
        
        
        
//Verify if Clicking "My Dreams" opens dreams-diary.html and dreams-total.html in new tabs/windows. 

        boolean diaryPageOpened = false;
        boolean totalPageOpened = false;

        for (String handle : allWindows) {
            driver.switchTo().window(handle);
            String currentUrl = driver.getCurrentUrl();
            Reporter.log("🧭 Switched to tab with URL: " + currentUrl, true);
            if (currentUrl.contains("dreams-diary.html")) {
                diaryPageOpened = true;
            }
            if (currentUrl.contains("dreams-total.html")) {
                totalPageOpened = true;
            }
        }
        
        System.out.println();
        driver.switchTo().window(originalWindow);
        Reporter.log("↩️ Switched back to original tab", true);

        softAssert.assertTrue(diaryPageOpened, "dreams-diary.html should be opened in a new tab");
        softAssert.assertTrue(totalPageOpened, "dreams-total.html should be opened in a new tab");
        System.out.println();
        Reporter.log("✅ dreams-diary.html opened: " + diaryPageOpened, true);
        Reporter.log("✅ dreams-total.html opened: " + totalPageOpened, true);
        System.out.println();
        softAssert.assertAll();
    }
}
