package Tests;

import WebPages.DreamsDairyPage;
import WebPages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Initilization.DriverBase;

import java.util.Set;
import org.testng.Reporter;

public class DreamsDairyTest extends DriverBase {

    DreamsDairyPage diaryPage;

    @BeforeClass
    public void openDreamsDiaryTab() {
        // Driver setup and opening the base URL is handled in DriverBase

        // Open the Dreams Diary tab
        HomePage homePage = new HomePage(driver);
        homePage.waitForMyDreamsButton();
        homePage.clickMyDreams();

        // Wait for 3 tabs to be open and switch to dreams-diary tab
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl().contains("dreams-diary.html")) {
                diaryPage = new DreamsDairyPage(driver);
                return;
            }
        }
        throw new RuntimeException("Dreams Diary tab not found");
    }
    
 //Validate if there are exactly 10 dream entries. 
    @Test
    public void testNumberOfDreamEntries() {
        int entries = diaryPage.getNumberOfDreamEntries();
        Reporter.log("✅ Total dream entries found: " + entries, true);  // logs in report and console
        System.out.println();
        Assert.assertEquals(entries, 10, "There should be exactly 10 dream entries.");
    }


 //Validate if The dream types are either “Good” or “Bad”. 

    @Test 
    public void testDreamTypesAreValid() {
        boolean allTypesValid = diaryPage.areAllDreamTypesValid();
        Reporter.log("✅ All dream types are either 'Good' or 'Bad': " + allTypesValid, true);
        Assert.assertTrue(allTypesValid, "All dream types must be 'Good' or 'Bad'.");
    }

    
//Validate if each row has all three columns filled: Dream Name, Days Ago, Dream Type. 

    @Test 
    public void testAllColumnsAreFilled() {
        boolean allColumnsFilled = diaryPage.areAllColumnsFilled();
        System.out.println();
        Reporter.log("✅ All rows have Name, Days Ago, and Type columns filled: " + allColumnsFilled, true);
        Assert.assertTrue(allColumnsFilled, "Each row should have all three columns filled.");
    }

}



