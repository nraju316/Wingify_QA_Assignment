package Tests;

import WebPages.AI_BASED_VALIDATION;
import WebPages.DreamsDairyPage;
import WebPages.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import Initilization.DriverBase;

import java.util.*;

public class AI_BASED_VALIDATION_TEST extends DriverBase {

    DreamsDairyPage dairyPage;

    @BeforeClass
    public void navigateToDreamsDiaryPage() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForMyDreamsButton();
        homePage.clickMyDreams();
        Reporter.log("✅ Clicked on 'My Dreams' button", true);

        // Switch to dreams-diary.html
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            String url = driver.getCurrentUrl();
            if (url.contains("dreams-diary.html")) {
                Reporter.log("✅ Switched to dreams-diary.html", true);
                break;
            }
        }

        dairyPage = new DreamsDairyPage(driver);
    }

    @Test
    public void classifyDreamNamesFromUI() {
        List<WebElement> rows = dairyPage.getAllRows();
        Assert.assertTrue(rows.size() > 0, "❌ No dreams found on the UI");

        for (WebElement row : rows) {
            String dreamName = row.findElement(By.cssSelector("td:nth-child(1)")).getText().trim();

            // DEBUG: Print all columns to verify which contains actual classification
            List<WebElement> cells = row.findElements(By.tagName("td"));
            Reporter.log("📝 Row columns data:", true);
            for (int i = 0; i < cells.size(); i++) {
                Reporter.log("   Column " + (i + 1) + ": " + cells.get(i).getText().trim(), true);
            }

            // Actual dream type is in 3rd column (Good/Bad)
            String actualType = row.findElement(By.cssSelector("td:nth-child(3)")).getText().trim();
            Reporter.log("🔍 Validating dream: " + dreamName, true);
            Reporter.log("🎯 Actual dream type: " + actualType, true);

            String classification = AI_BASED_VALIDATION.classifyDreamName(dreamName);
            Reporter.log("🤖 AI classification for '" + dreamName + "' is: " + classification, true);

            Assert.assertTrue(
                classification.equalsIgnoreCase(actualType),
                "❌ AI classification '" + classification + "' does NOT match actual type '" + actualType + "' for dream: " + dreamName
            );
        }

        Reporter.log("✅ All dreams were successfully classified by AI and matched actual types", true);
    }
}
