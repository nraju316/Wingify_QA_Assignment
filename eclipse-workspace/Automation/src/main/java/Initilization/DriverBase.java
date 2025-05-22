package Initilization;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverBase {
	
//GEmeni API key
   
	public static final String API_KEY = "AIzaSyB6GOefTcQWux5zWLm413IAUHWb2XnjApA";
    public static final String ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;
	
	
//Base URl
	
    protected WebDriver driver;
    // Make testUrl a protected class field (not local variable)
    protected String testUrl = "https://arjitnigam.github.io/myDreams/";
    
//Checks URL connectivity
    
    @BeforeClass
    public void setup() {
        System.out.println("🌐 Checking website accessibility: " + testUrl);
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(testUrl).openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            if (responseCode < 200 || responseCode >= 400) {
                throw new RuntimeException("❌ Website is not accessible: " + testUrl);
            }
            System.out.println("✅ Website is accessible. Proceeding with test setup...");
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to check website accessibility: " + e.getMessage());
        }

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(testUrl);
    }
    
    
    
//Copies Test report to Downloads folder    
    @AfterSuite
    public void copyEmailableReportToDownloads() {
        // Path to the generated emailable report after tests are run
    	Path source = Paths.get("test-output/emailable-report.html");

        // Generate a timestamp like 20250522_160045
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        // Destination filename with timestamp
        String fileNameWithTimestamp = "emailable-report_" + timestamp + ".html";
        Path destination = Paths.get(System.getProperty("user.home"), "Downloads", fileNameWithTimestamp);

        try {
            // Copy the file, replacing if needed (though filename is unique)
            Files.copy(source, destination);
            System.out.println("✅ Emailable report copied to Downloads as: " + fileNameWithTimestamp);
        } catch (IOException e) {
            System.err.println("❌ Failed to copy emailable report: " + e.getMessage());
        }
    }

    
    
//Quits chrome
    
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
