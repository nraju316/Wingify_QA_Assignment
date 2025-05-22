package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import java.util.List;

public class DreamsDairyPage {

    WebDriver driver;

    public DreamsDairyPage(WebDriver driver) {
        this.driver = driver;
    }

    private By tableRows = By.cssSelector("table tbody tr");
    private By dreamNameColumn = By.cssSelector("td:nth-child(1)");
    private By daysAgoColumn = By.cssSelector("td:nth-child(2)");
    private By dreamTypeColumn = By.cssSelector("td:nth-child(3)");

   
 //Validate if there are exactly 10 dream entries. 
    
    public int getNumberOfDreamEntries() {
        return driver.findElements(tableRows).size();
    }

    public List<WebElement> getAllRows() {
        return driver.findElements(tableRows);
    }
    
    
 //Validate if The dream types are either “Good” or “Bad”. 
    
    public boolean areAllDreamTypesValid() {
        List<WebElement> rows = getAllRows();
        for (WebElement row : rows) {
            try {
                String type = row.findElement(dreamTypeColumn).getText().trim();
                if (!type.equals("Good") && !type.equals("Bad")) {
                    System.err.println("Invalid dream type found: '" + type + "'");
                    return false;
                }
            } catch (NoSuchElementException e) {
                System.err.println("Dream type cell missing in row: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    
 //Validate if each row has all three columns filled: Dream Name, Days Ago, Dream Type. 
    
    public boolean areAllColumnsFilled() {
        List<WebElement> rows = getAllRows();
        for (WebElement row : rows) {
            try {
                String name = row.findElement(dreamNameColumn).getText().trim();
                String days = row.findElement(daysAgoColumn).getText().trim();
                String type = row.findElement(dreamTypeColumn).getText().trim();

                if (name.isEmpty() || days.isEmpty() || type.isEmpty()) {
                    System.err.println("Empty cell found in row — Name: '" + name + "', Days Ago: '" + days + "', Type: '" + type + "'");
                    return false;
                }
            } catch (NoSuchElementException e) {
                System.err.println("Missing column in row: " + e.getMessage());
                return false;
            }
        }
        return true;
    }
}
