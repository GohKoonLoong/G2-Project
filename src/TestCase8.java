import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class TestCase8 {
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
    public void navigateChequeRegistry(){
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
    public void navigationNewChequeRegistry(){
        // Locate the New Button and click on it
        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"addNewCheque\"]"));
        newButton.click();
    }

    // Method for choosing the type from a dropdown
    public void chooseType() {
        // Locate and click the type dropdown
        WebElement typeDropdown = driver.findElement(By.xpath("//*[@id='inputArea_cheque_custType']/span"));
        typeDropdown.click();

        // Locate and click the specific type option (e.g., student)
        WebElement studentOption = driver.findElement(By.xpath("//*[@id=\"cheque_custType\"]/option[5]"));
        wait.until(ExpectedConditions.elementToBeClickable(studentOption)).click();

        // Close the dropdown options
        WebElement dropdownOptions = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_custType\"]/span/span[2]/span"));
        wait.until(ExpectedConditions.elementToBeClickable(dropdownOptions)).click();
    }

    // Method for entering ID in the corresponding field
    public void enterID(String idValue) {
        // Locate and click the ID field
        WebElement idField = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_custID\"]/span"));
        idField.click();

        // Locate and enter ID value
        WebElement idInput = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        wait.until(ExpectedConditions.elementToBeClickable(idInput));
        idInput.sendKeys(idValue);

        // Select a specific option from the dropdown
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"select2-cheque_custID-results\"]/li[2]")));
        dropdownOption.click();
    }

    // Method for choosing the bank from a dropdown
    public void chooseBank(String type) throws InterruptedException {
        // Locate and click the bank dropdown
        WebElement bankField = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_IssueBank\"]/span/span[2]/span"));
        bankField.click();

        // Locate and enter bank type
        WebElement bankInput = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        wait.until(ExpectedConditions.elementToBeClickable(bankInput));
        bankInput.sendKeys(type);
        Thread.sleep(2000);

        // Select a specific bank option from the dropdown
        WebElement bankOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"select2-cheque_IssueBank-results\"]/li[4]")));
        bankOption.click();
    }

    // Method for adding branch name
    public void addBranchName(String name) {
        WebElement inputBranchName = driver.findElement(By.xpath("//*[@id=\"cheque_branchName\"]"));
        inputBranchName.sendKeys(name);
    }

    // Method for adding Cheque ID
    public void addChequeID(String chequeID) {
        WebElement inputChequeID = driver.findElement(By.xpath("//*[@id=\"cheque_chequeNo\"]"));
        inputChequeID.sendKeys(chequeID);
    }

    // Method for adding Cheque Date
    public void addChequeDate()  {
        // Locate and click the Cheque Date field
        WebElement chequeDate = driver.findElement(By.xpath("//*[@id=\"cheque_chequeDate\"]"));
        chequeDate.click();

        // Select a specific month, year, and date from the date picker
        WebElement monthDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[18]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]")));
        monthDropdown.click();
        WebElement monthSelector = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[18]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]/option[1]")));
        monthSelector.click();

        WebElement yearDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[18]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]")));
        yearDropdown.click();
        WebElement yearSelector = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[18]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]/option[101]")));
        yearSelector.click();

        WebElement dateSelector = driver.findElement(By.xpath("/html/body/div[18]/div[2]/div[1]/table/tbody/tr[3]/td[3]"));
        dateSelector.click();
    }

    // Method for adding Cheque Amount
    public void addChequeAmount(String amount) {
        WebElement chequeAmount = driver.findElement(By.xpath("//*[@id=\"cheque_chequeAmount\"]"));
        chequeAmount.sendKeys(amount);
    }

    // Method for adding Reference No
    public void addReferenceNo(String no) {
        WebElement referenceNo = driver.findElement(By.xpath("//*[@id=\"cheque_invoiceNo\"]"));
        referenceNo.sendKeys(no);
    }

    // Method for adding Validity
    public void addValidity(String month) {
        WebElement validity = driver.findElement(By.xpath("//*[@id=\"cheque_validity\"]"));
        validity.sendKeys(month);
    }

    // Method for adding Remark
    public void addRemark(String word) {
        WebElement remark = driver.findElement(By.xpath("//*[@id=\"cheque_remark\"]"));
        remark.sendKeys(word);
    }

    // Method for adding Received Date
    public void addReceivedDate() {
        // Locate and click the Received Date field
        WebElement receivedDate = driver.findElement(By.xpath("//*[@id=\"cheque_receiveDate\"]"));
        receivedDate.click();

        // Select a specific month, year, and date from the date picker
        WebElement monthDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[19]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]")));
        monthDropdown.click();
        WebElement monthSelector = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[19]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]/option[1]")));
        monthSelector.click();

        WebElement yearDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[19]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]")));
        yearDropdown.click();
        WebElement yearSelector = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[19]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]/option[101]")));
        yearSelector.click();

        WebElement dateSelector = driver.findElement(By.xpath("/html/body/div[19]/div[2]/div[1]/table/tbody/tr[4]/td[1]"));
        dateSelector.click();
    }

    // Method for clicking the Save Button
    public void clickSaveButton(){
        // Locate and click the Save Button
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"inputArea_btn_saveCheque\"]/div/a"));
        saveButton.click();

        // Wait for the successful message to appear
        WebElement successfulMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[2]")));
        String actualText = successfulMessage.getText();
        String expectedText = "Information was successfully saved.";

        // Verify if the actual message matches the expected message
        Assert.assertEquals("Data is not saved successfully", expectedText, actualText);
        System.out.println("Data is saved successfully");

        // Close the pop-up message
        WebElement closePopUpMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[3]/button")));
        closePopUpMessage.click();
    }

    // Main method to execute the test case
    public static void main(String[] args) throws InterruptedException {
        TestCase8 obj = new TestCase8();
        obj.launchBrowser();
        obj.login("ENTRY2", "qwertyuiop");
        obj.navigateChequeRegistry();
        Thread.sleep(2000);
        obj.navigationNewChequeRegistry();
        obj.chooseType();
        obj.enterID("D10");
        obj.chooseBank("A");
        obj.addBranchName("Kuala Lumpur");
        obj.addChequeID("105008");
        obj.addChequeDate();
        obj.addChequeAmount("1000");
        obj.addReferenceNo("123456");
        obj.addValidity("3");
        obj.addRemark("MAY-10803");
        obj.addReceivedDate();
        obj.clickSaveButton();
    }
}
