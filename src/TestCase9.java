import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class TestCase9 {
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

    // Method for navigating to the Cheque Registry section
    public void navigateChequeRegistry() {
        // Locate Account Receivable, Cheque, and Cheque Release links
        WebElement accountReceivableLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1024\"]"));
        WebElement chequeLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1655\"]"));
        WebElement chequeReleaseLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1656\"]"));

        // Click on the links in sequence
        accountReceivableLink.click();
        wait.until(ExpectedConditions.elementToBeClickable(chequeLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(chequeReleaseLink)).click();
    }

    // Method for clicking the checkbox in the Cheque Release section
    public void clickCheckBox() {
        // Locate and click the checkbox for the first row in the Cheque Release table
        WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Release\"]/tbody/tr[1]/td[7]/div/label"));
        checkBox.click();
    }

    // Method for clicking the Cheque Release button
    public void clickChequeReleaseBTN() {
        // Locate and click the Cheque Release button
        WebElement chequeRelease = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnSubmit\"]")));
        chequeRelease.click();

        // Wait for the successful message to appear
        WebElement successfulMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[2]")));
        String actualText = successfulMessage.getText();
        String expectedText = "The information was successfully updated. Transaction was completed.";

        // Verify if the actual message matches the expected message
        Assert.assertEquals("Cheque Release is not saved successfully", expectedText, actualText);
        System.out.println("Cheque Release is saved successfully");

        // Close the pop-up message
        WebElement closePopUpMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[3]/button")));
        closePopUpMessage.click();
    }

    // Main method to execute the test case
    public static void main(String[] args) throws InterruptedException {
        TestCase9 obj = new TestCase9();
        obj.launchBrowser();
        obj.login("ENTRY2", "qwertyuiop");
        obj.navigateChequeRegistry();
        Thread.sleep(3000);
        obj.clickCheckBox();
        obj.clickChequeReleaseBTN();
    }
}
