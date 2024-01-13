import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestCase6 {
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

    // Method for selecting a date in the date picker
    public void datePicker()  {
        // Locate the date icon and click on it to open the date picker
        WebElement dateIcon = driver.findElement(By.xpath("//*[@id=\"cheque_chequeDate\"]"));
        dateIcon.click();

        // Select the month in the date picker
        WebElement monthDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[18]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]")));
        monthDropdown.click();
        WebElement monthSelector = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[18]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]/option[2]")));
        monthSelector.click();

        // Select the year in the date picker
        WebElement yearDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[18]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]")));
        yearDropdown.click();
        WebElement yearSelector = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[18]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]/option[99]")));
        yearSelector.click();

        // Select the date in the date picker
        WebElement dateSelector = driver.findElement(By.xpath("/html/body/div[18]/div[2]/div[1]/table/tbody/tr[4]/td[2]"));
        dateSelector.click();

        // Verify if the selected date matches the expected date
        String actualText = dateIcon.getAttribute("value");
        String expectedText = "22/02/2022";

        Assert.assertEquals("The date shown is not correct", expectedText, actualText);
        System.out.println("The date shown is 22/02/2022");
    }

    // Main method to execute the test case
    public static void main(String[] args) throws InterruptedException {
        TestCase6 obj = new TestCase6();
        obj.launchBrowser();
        obj.login("ENTRY2", "qwertyuiop");
        obj.navigateChequeRegistry();
        Thread.sleep(3000);
        obj.navigationNewChequeRegistry();
        obj.datePicker();
    }
}
