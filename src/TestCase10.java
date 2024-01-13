import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class TestCase10 {
    WebDriver driver;
    WebDriverWait wait;

    // Method for launching the browser and navigating to the specified URL
    public void launchBrowser() {
        // Setting up Microsoft Edge WebDriver
        System.setProperty("webdriver.msedge.driver", "/Users/gohkoonloong/Downloads/msedgedriver");
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the FIMS login page
        driver.get("https://fimsclone.kerisi.my/login.php?a=NTg2MTEw");
    }

    // Method for logging into the application
    public void login(String username, String password) {
        // Locate username, password, and login button elements
        WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"userID\"]"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"userPassword\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));

        // Enter username and password
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        // Click on the login button
        loginButton.click();
    }

    // Method for navigating to the Cheque List section
    public void navigateChequeList() {
        // Locate Account Receivable, Cheque, and Cheque List links
        WebElement accountReceivableLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1024\"]"));
        WebElement chequeLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1655\"]"));
        WebElement chequeListLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1720\"]"));

        // Click on the links in sequence
        accountReceivableLink.click();
        wait.until(ExpectedConditions.elementToBeClickable(chequeLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(chequeListLink)).click();
    }

    // Method for clicking the Download button and verifying the downloaded file
    public void clickDownloadButton() throws InterruptedException {
        // Locate and click the Download button
        WebElement downloadButton = driver.findElement(By.xpath("//*[@id=\"download\"]"));
        downloadButton.click();

        // Verify the file is downloaded to the specified directory
        String downloadDirectory = "/Users/gohkoonloong/Downloads";
        String fileName = "List of Cheque.csv";

        Thread.sleep(5000);
        File downloadedFile = new File(downloadDirectory, fileName);

        // Assert if the file exists in the specified directory
        Assert.assertTrue("File not found in the specified directory", downloadedFile.exists());
        System.out.println("File found in the specified directory");
    }

    // Main method to execute the test case
    public static void main(String[] args) throws InterruptedException {
        TestCase10 obj = new TestCase10();
        obj.launchBrowser();
        obj.login("ENTRY2", "qwertyuiop");
        obj.navigateChequeList();
        Thread.sleep(3000);
        obj.clickDownloadButton();
    }
}
