package Tests;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverBase {

    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        String testUrl = "https://arjitnigam.github.io/myDreams/";

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

    @AfterSuite
    public void copyReportToDownloads() {
        Path source = Paths.get("test-output/emailable-report.html");
        Path destination = Paths.get(System.getProperty("user.home") + "/Downloads/emailable-report.html");

        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("✅ Emailable report copied to Downloads folder.");
        } catch (IOException e) {
            System.err.println("❌ Failed to copy emailable report: " + e.getMessage());
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
