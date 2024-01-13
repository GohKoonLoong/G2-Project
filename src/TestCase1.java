import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.Select;

public class TestCase1 {
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
    public void navigateChequeRegistry() throws InterruptedException {
        // Locate Account Receivable, Cheque, and Cheque Registry links
        WebElement accountReceivableLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1024\"]"));
        WebElement chequeLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1655\"]"));
        WebElement chequeRegistryLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1652\"]"));

        // Click on the links in sequence
        accountReceivableLink.click();
        wait.until(ExpectedConditions.elementToBeClickable(chequeLink)).click();
        wait.until(ExpectedConditions.elementToBeClickable(chequeRegistryLink)).click();
    }

    // Method for clicking the Edit icon in the Cheque Registry
    public void clickEditIcon() {
        // Locate the Edit icon and click on it
        WebElement editIcon = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry\"]/tbody/tr[1]/td[14]/a"));
        editIcon.click();
    }

    // Method for choosing the type in the Edit Cheque Registry form
    public void chooseType() {
        // Locate the type dropdown and select the Student option
        WebElement typeDropdown = driver.findElement(By.xpath("//*[@id='inputArea_cheque_custType']/span"));
        WebElement studentOption = driver.findElement(By.xpath("//*[@id=\"cheque_custType\"]/option[2]"));
        WebElement dropdownOptions = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_custType\"]/span/span[2]/span"));

        typeDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(studentOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dropdownOptions)).click();
    }

    // Method for entering ID in the Edit Cheque Registry form
    public void enterID(String idValue) throws InterruptedException {
        // Locate the ID field, input, and select the dropdown option
        WebElement idField = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_custID\"]/span"));
        idField.click();

        WebElement idInput = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        wait.until(ExpectedConditions.elementToBeClickable(idInput)).sendKeys(idValue);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"select2-cheque_custID-results\"]/li[2]"))).click();
    }

    // Method for choosing the bank in the Edit Cheque Registry form
    public void chooseBank(String type) throws InterruptedException {
        // Locate the bank field, input, and select the dropdown option
        WebElement bankField = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_IssueBank\"]/span/span[2]/span"));
        bankField.click();

        WebElement bankInput = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        wait.until(ExpectedConditions.elementToBeClickable(bankInput)).sendKeys(type);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"select2-cheque_IssueBank-results\"]/li[2]"))).click();
    }

    // Method for clicking the Save button in the Edit Cheque Registry form
    public void clickSaveButton() throws InterruptedException {
        // Locate the Save button and handle the success alert
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"inputArea_btn_saveCheque\"]/div/a"));
        saveButton.click();

        WebElement saveSuccessfully = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[3]/button")));
        saveSuccessfully.click();

        Thread.sleep(5000);

        // Verify the edited values in the Cheque Registry table
        WebElement debtorID = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry\"]/tbody/tr[1]/td[2]"));
        WebElement drawer = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry\"]/tbody/tr[1]/td[3]"));
        WebElement issuerBank = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry\"]/tbody/tr[1]/td[4]"));

        String debtorIDText = debtorID.getText();
        String drawerText = drawer.getText();
        String issuerBankText = issuerBank.getText();

        String actualText = debtorIDText + "," + drawerText + "," + issuerBankText;
        String expectedText = "10,W ABDULLAH BIN WAN OMAR,ABB-i";

        // Verify if the edit was successful
        Assert.assertEquals("Edit was unsuccessful!", actualText, expectedText);
        System.out.println("Edit was successful");
    }

    // Main method to execute the test case
    public static void main(String[] args) throws InterruptedException {
        TestCase1 obj = new TestCase1();
        obj.launchBrowser();
        obj.login("ENTRY2", "qwertyuiop");
        obj.navigateChequeRegistry();
        Thread.sleep(2000);
        obj.clickEditIcon();
        Thread.sleep(3000);
        obj.chooseType();
        obj.enterID("1");
        obj.chooseBank("B");
        obj.clickSaveButton();
    }
}
