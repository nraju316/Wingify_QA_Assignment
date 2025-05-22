package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DreamsTotalPage {
    WebDriver driver;

    public DreamsTotalPage(WebDriver driver) {
        this.driver = driver;
    }

    private By summaryTableRows = By.cssSelector("#dreamsTotal tbody tr");

    private int getCountForLabel(String label) {
        List<WebElement> rows = driver.findElements(summaryTableRows);
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() == 2 && cells.get(0).getText().trim().equalsIgnoreCase(label)) {
                return Integer.parseInt(cells.get(1).getText().trim());
            }
        }
        throw new RuntimeException("Label '" + label + "' not found in summary table.");
    }

    
//Verify the correct stats : Good Dreams: 6     
   
    public int getGoodDreamsCount() {
        return getCountForLabel("Good Dreams");
    }


//Verify the correct stats : Bad Dreams: 4 
    
    public int getBadDreamsCount() {
        return getCountForLabel("Bad Dreams");
    }


//Verify the correct stats : Total Dreams: 10 
    
    public int getTotalDreamsCount() {
        return getCountForLabel("Total Dreams");
    }
    

//Verify the correct stats : Recurring Dreams: 2 


    public int getRecurringDreamsCount() {
        return getCountForLabel("Recurring Dreams");
    }
}
