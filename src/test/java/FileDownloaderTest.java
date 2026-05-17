import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.util.HashMap;


public class FileDownloaderTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups ", 0);
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);
    }

    @Test
    public void checkFileDownload() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/download");
        driver.findElement(By.xpath("//a[text()='LambdaTest.txt']")).click();
        Thread.sleep(500);
        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File downloadedFile = null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String fileName = file.getName();
                if (fileName.equals("LambdaTest.txt")) {
                    downloadedFile = file;
                    found = true;
                    break;
                }
            }
        }
        Assert.assertTrue(found, "Downloaded file was not found.");
        if (downloadedFile != null) {
            downloadedFile.delete();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
