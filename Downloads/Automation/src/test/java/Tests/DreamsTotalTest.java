package Tests;

import WebPages.DreamsDairyPage;
import WebPages.DreamsTotalPage;
import WebPages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import Initilization.DriverBase;

import java.util.*;

public class DreamsTotalTest extends DriverBase {

    DreamsTotalPage totalPage;
    DreamsDairyPage dairyPage;

    @BeforeClass
    public void openDreamsTotalPage() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForMyDreamsButton();
        homePage.clickMyDreams();
        Reporter.log("✅ Clicked on 'My Dreams' button", true);

        // Switch to dreams-total.html tab
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            String url = driver.getCurrentUrl();
            Reporter.log("🔁 Switched to tab with URL: " + url, true);
            if (url.contains("dreams-total.html")) {
                Reporter.log("✅ Switched to dreams-total.html", true);
                break;
            }
        }

        totalPage = new DreamsTotalPage(driver);
    }

   
// Verify the correct stats : Good Dreams: 6     

    @Test(priority = 1)
    public void verifyGoodDreamsCount() {
        SoftAssert softAssert = new SoftAssert();
        int good = totalPage.getGoodDreamsCount();
        System.out.println();
        Reporter.log("🟩 Good dreams count: " + good, true);
        softAssert.assertEquals(good, 6, "Good dreams count should be 6");
        Reporter.log("✅ Verified Good dreams count is 6", true);
        softAssert.assertAll();
        System.out.println();

    }

    
// Verify the correct stats : Bad Dreams: 4 
    @Test(priority = 2)
    public void verifyBadDreamsCount() {
        SoftAssert softAssert = new SoftAssert();
        int bad = totalPage.getBadDreamsCount();
        Reporter.log("🟥 Bad dreams count: " + bad, true);
        softAssert.assertEquals(bad, 4, "Bad dreams count should be 4");
        Reporter.log("✅ Verified Bad dreams count is 4", true);
        softAssert.assertAll();
        System.out.println();

    }

 
// Verify the correct stats : Total Dreams: 10 
    @Test(priority = 3)
    public void verifyTotalDreamsCount() {
        SoftAssert softAssert = new SoftAssert();
        int total = totalPage.getTotalDreamsCount();
        Reporter.log("📊 Total dreams count: " + total, true);
        softAssert.assertEquals(total, 10, "Total dreams count should be 10");
        Reporter.log("✅ Verified Total dreams count is 10", true);
        softAssert.assertAll();
        System.out.println();

    }
    

// Verify the correct stats : Recurring Dreams: 2 

    @Test(priority = 4)
    public void verifyRecurringDreamsCount() {
        SoftAssert softAssert = new SoftAssert();
        int recurring = totalPage.getRecurringDreamsCount();
        Reporter.log("🔁 Recurring dreams count: " + recurring, true);
        softAssert.assertEquals(recurring, 2, "Recurring dreams count should be 2");
        Reporter.log("✅ Verified Recurring dreams count is 2", true);
        softAssert.assertAll();
        System.out.println();

    }
    

 // Assert that “Flying over mountains” and “Lost in maze” are counted as recurring 
    
    @Test(priority = 5)
    public void verifyRecurringDreamsAreCorrect() {
        // Switch to dreams-diary.html tab
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl().contains("dreams-diary.html")) {
                Reporter.log("✅ Switched to dreams-diary.html", true);
                break;
            }
        }

        dairyPage = new DreamsDairyPage(driver);
        Map<String, Integer> dreamNameCounts = new HashMap<>();
        for (WebElement row : dairyPage.getAllRows()) {
            String name = row.findElement(By.cssSelector("td:nth-child(1)")).getText().trim();
            dreamNameCounts.put(name, dreamNameCounts.getOrDefault(name, 0) + 1);
        }

        Reporter.log("📃 Dream name frequency map: " + dreamNameCounts.toString(), true);
        System.out.println();

        List<String> recurringDreams = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : dreamNameCounts.entrySet()) {
            if (entry.getValue() > 1) {
                recurringDreams.add(entry.getKey());
            }
        }

        Reporter.log("🔁 Recurring dreams detected: " + recurringDreams, true);

        // Hard assertion only for critical validation
        Assert.assertTrue(recurringDreams.contains("Flying over mountains"),
                "'Flying over mountains' should be marked as recurring.");
        Assert.assertTrue(recurringDreams.contains("Lost in maze"),
                "'Lost in maze' should be marked as recurring.");

        Reporter.log("✅ Verified recurring dreams: " + recurringDreams, true);
        System.out.println();
    }
}
