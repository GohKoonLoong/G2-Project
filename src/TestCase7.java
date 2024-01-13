import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestCase7 {
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
        // Locate Account Receivable, Cheque, and Cheque Registry links
        WebElement accountReceivableLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1024\"]"));
        WebElement chequeLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1655\"]"));
        WebElement chequeRegistryLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1652\"]"));

        // Click on the links in sequence
        accountReceivableLink.click();
        wait.until(ExpectedConditions.elementToBeClickable(chequeLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(chequeRegistryLink)).click();
    }

    // Method for navigating to the New Cheque Registry form
    public void navigationNewChequeRegistry() {
        // Locate the New Button and click on it
        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"addNewCheque\"]"));
        newButton.click();
    }

    // Method for attempting to save without entering compulsory details
    public void saveWithoutInput() {
        // Locate the Save Button and click on it
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"inputArea_btn_saveCheque\"]/div/a"));
        saveButton.click();

        // Wait for the compulsory message to appear
        WebElement compulsoryMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[2]")));

        // Verify if the actual message matches the expected message
        String actualText = compulsoryMessage.getText();
        String expectedText = "Compulsory";
        Assert.assertEquals("Data is saved successfully", expectedText, actualText);
        System.out.println("Please fill in the compulsory details");

        // Close the pop-up message
        WebElement closePopUpMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[3]/button")));
        closePopUpMessage.click();
    }

    // Main method to execute the test case
    public static void main(String[] args) throws InterruptedException {
        TestCase7 obj = new TestCase7();
        obj.launchBrowser();
        obj.login("ENTRY2", "qwertyuiop");
        obj.navigateChequeRegistry();
        Thread.sleep(3000);
        obj.navigationNewChequeRegistry();
        obj.saveWithoutInput();
    }
}
