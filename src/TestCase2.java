import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class TestCase2 {
    WebDriver driver;
    WebDriverWait wait;

    // Method to launch the browser and navigate to the specified URL
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

    // Method for searching input data in the Cheque Registry
    public void searchInput(String data) throws InterruptedException {
        // Locate the search input field and enter the search data
        WebElement searchData = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry_filter\"]/label/div/div/input"));
        searchData.sendKeys(data);
        Thread.sleep(3000);

        // Verify if the expected data is shown in the search result information
        WebElement dataShow = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry_info\"]"));
        String actualText = dataShow.getText();
        String expectedText = "31 records";

        Assert.assertEquals("Search data is not shown", expectedText, actualText);
        System.out.println("Search data is found");
    }

    // Main method to execute the test case
    public static void main(String[] args) throws InterruptedException {
        TestCase2 obj = new TestCase2();
        obj.launchBrowser();
        obj.login("ENTRY2", "qwertyuiop");
        obj.navigateChequeRegistry();
        Thread.sleep(3000);
        obj.searchInput("ABDULLAH");
    }
}
