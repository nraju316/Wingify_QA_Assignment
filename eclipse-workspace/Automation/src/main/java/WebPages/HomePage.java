package WebPages;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    // Locators
    private By loadingAnimation = By.id("loadingAnimation");
    private By mainContent = By.cssSelector("main");
    private By myDreamsButton = By.xpath("//button[text()='My Dreams']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    
//Verifies that the loading animation appears and disappears after ~3 seconds. 
    
    public boolean isLoadingAnimationVisible() {
        try {
            boolean visible = driver.findElement(loadingAnimation).isDisplayed();
            System.out.println("Loading animation visible: " + visible);
            return visible;
        } catch (NoSuchElementException e) {
            System.err.println("Loading animation element not found: " + e.getMessage());
            return false;
        }
    }

    public boolean waitForLoadingToDisappear() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            boolean disappeared = wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingAnimation));
            System.out.println("Loading animation disappeared: " + disappeared);
            return disappeared;
        } catch (Exception e) {
            System.err.println("Error waiting for loading animation to disappear: " + e.getMessage());
            return false;
        }
    }

    
//Verifies that the main content visibility and "My Dreams" button become visible.     

    public boolean isMainContentVisible() {
        try {
            boolean visible = driver.findElement(mainContent).isDisplayed();
            System.out.println("Main content visible: " + visible);
            return visible;
        } catch (NoSuchElementException e) {
            System.err.println("Main content element not found: " + e.getMessage());
            return false;
        }
    }

////Verify and click "My Dream" button. 
  
    
    public void waitForMyDreamsButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(myDreamsButton));
            System.out.println("My Dreams button is visible");
        } catch (Exception e) {
            System.err.println("My Dreams button not visible or not found: " + e.getMessage());
            throw e;  // rethrow to fail test if needed
        }
    }

    public void clickMyDreams() {
        try {
            driver.findElement(myDreamsButton).click();
            System.out.println("Clicked on My Dreams button");
        } catch (NoSuchElementException e) {
            System.err.println("Failed to click My Dreams button - element not found: " + e.getMessage());
            throw e; // rethrow for test failure
        } catch (Exception e) {
            System.err.println("Unexpected error clicking My Dreams button: " + e.getMessage());
            throw e; // rethrow for test failure
        }
    }
    
}
