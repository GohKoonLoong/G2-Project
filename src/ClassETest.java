import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassETest {
    WebDriver driver;
    WebDriverWait wait;

    // Method to launch the browser and navigate to the specified URL
    @Before
    public void beforeTest() {
        // Setting up Microsoft Edge WebDriver
        System.setProperty("webdriver.msedge.driver", "/Users/gohkoonloong/Downloads/msedgedriver");
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Maximize the browser window
        driver.manage().window().maximize();

        // Navigate to the FIMS login page
        driver.get("https://fimsclone.kerisi.my/login.php?a=NTg2MTEw");

        // Locate username, password, and login button elements
        WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"userID\"]"));
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"userPassword\"]"));
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));

        // Enter username and password
        usernameInput.sendKeys("ENTRY2");
        passwordInput.sendKeys("qwertyuiop");

        // Click on the login button
        loginButton.click();

        // Locate Account Receivable and Cheque links
        WebElement accountReceivableLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1024\"]"));
        WebElement chequeLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1655\"]"));

        // Click on the links in sequence
        accountReceivableLink.click();
        wait.until(ExpectedConditions.elementToBeClickable(chequeLink)).click();

    }

    @Test
    public void FIMS_41() throws InterruptedException {
        //Locate and click Cheque Registry Link
        WebElement chequeRegistryLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1652\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(chequeRegistryLink)).click();

        Thread.sleep(2000);

        // Locate the Edit icon and click on it
        WebElement editIcon = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry\"]/tbody/tr[1]/td[14]/a"));
        editIcon.click();

        Thread.sleep(3000);

        // Locate the type dropdown and select the Student option
        WebElement typeDropdown = driver.findElement(By.xpath("//*[@id='inputArea_cheque_custType']/span"));
        WebElement studentOption = driver.findElement(By.xpath("//*[@id=\"cheque_custType\"]/option[2]"));
        WebElement dropdownOptions = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_custType\"]/span/span[2]/span"));

        typeDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(studentOption)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dropdownOptions)).click();

        // Locate the ID field, input, and select the dropdown option
        WebElement idField = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_custID\"]/span"));
        idField.click();

        WebElement idInput = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        wait.until(ExpectedConditions.elementToBeClickable(idInput)).sendKeys("1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"select2-cheque_custID-results\"]/li[2]"))).click();

        // Locate the bank field, input, and select the dropdown option
        WebElement bankField = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_IssueBank\"]/span/span[2]/span"));
        bankField.click();

        WebElement bankInput = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        wait.until(ExpectedConditions.elementToBeClickable(bankInput)).sendKeys("B");
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"select2-cheque_IssueBank-results\"]/li[2]"))).click();

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

    @Test
    public void FIMS_42() throws InterruptedException{
        //Locate and click Cheque Registry Link
        WebElement chequeRegistryLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1652\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(chequeRegistryLink)).click();

        Thread.sleep(2000);

        // Locate the search input field and enter the search data
        WebElement searchData = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry_filter\"]/label/div/div/input"));
        searchData.sendKeys("ABDULLAH");
        Thread.sleep(3000);

        // Verify if the expected data is shown in the search result information
        WebElement dataShow = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry_info\"]"));
        String actualText = dataShow.getText();
        String expectedText = "31 records";

        Assert.assertEquals("Search data is not shown", expectedText, actualText);
        System.out.println("Search data is found");
    }

    @Test
    public void FIMS_43() throws InterruptedException{
        //Locate and click Cheque Registry Link
        WebElement chequeRegistryLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1652\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(chequeRegistryLink)).click();

        Thread.sleep(2000);

        // Locate the search input field and enter the search data
        WebElement searchData = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry_filter\"]/label/div/div/input"));
        searchData.sendKeys("TAYLOR");
        Thread.sleep(3000);

        // Locate the information about the number of records shown
        WebElement dataShow = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry_info\"]"));

        // Verify if the expected data is not shown in the search result information
        String actualText = dataShow.getText();
        String expectedText = "";

        Assert.assertEquals("Search data is shown", expectedText, actualText);
        System.out.println("Search data does not exist in the table");
    }

    @Test
    public void FIMS_44() throws InterruptedException{
        //Locate and click Cheque Registry Link
        WebElement chequeRegistryLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1652\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(chequeRegistryLink)).click();

        Thread.sleep(2000);

        // Locate the New Button and click on it
        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"addNewCheque\"]"));
        newButton.click();

        // Verify if the New Cheque Registry form is displayed
        WebElement registryForm = driver.findElement(By.xpath("//*[@id=\"Cheque_Registry_page\"]"));
        Assert.assertTrue("Navigation unsuccessful", registryForm.isDisplayed());
        System.out.println("Navigation successful");
    }

    @Test
    public void FIMS_45() throws InterruptedException{
        //Locate and click Cheque Registry Link
        WebElement chequeRegistryLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1652\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(chequeRegistryLink)).click();

        Thread.sleep(2000);

        // Locate the New Button and click on it
        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"addNewCheque\"]"));
        newButton.click();

        // Locate the back icon and click on it
        WebElement backIcon = driver.findElement(By.xpath("//*[@id=\"breadcrumbs\"]/back"));
        backIcon.click();

        // Verify if the Cheque Registry list is displayed after navigating back
        WebElement registryForm = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Registry_container\"]"));
        Assert.assertTrue("Navigation unsuccessful", registryForm.isDisplayed());
        System.out.println("Navigation successful");
    }

    @Test
    public void FIMS_46() throws InterruptedException{
        //Locate and click Cheque Registry Link
        WebElement chequeRegistryLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1652\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(chequeRegistryLink)).click();

        Thread.sleep(2000);

        // Locate the New Button and click on it
        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"addNewCheque\"]"));
        newButton.click();

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

    @Test
    public void FIMS_47() throws InterruptedException{
        //Locate and click Cheque Registry Link
        WebElement chequeRegistryLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1652\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(chequeRegistryLink)).click();

        Thread.sleep(2000);

        // Locate the New Button and click on it
        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"addNewCheque\"]"));
        newButton.click();

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

    @Test
    public void FIMS_48() throws InterruptedException{
        //Locate and click Cheque Registry Link
        WebElement chequeRegistryLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1652\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(chequeRegistryLink)).click();

        Thread.sleep(2000);

        // Locate the New Button and click on it
        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"addNewCheque\"]"));
        newButton.click();

        // Locate and click the type dropdown
        WebElement typeDropdown = driver.findElement(By.xpath("//*[@id='inputArea_cheque_custType']/span"));
        typeDropdown.click();

        // Locate and click the specific type option (e.g., student)
        WebElement studentOption = driver.findElement(By.xpath("//*[@id=\"cheque_custType\"]/option[5]"));
        wait.until(ExpectedConditions.elementToBeClickable(studentOption)).click();

        // Close the dropdown options
        WebElement dropdownOptions = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_custType\"]/span/span[2]/span"));
        wait.until(ExpectedConditions.elementToBeClickable(dropdownOptions)).click();

        // Locate and click the ID field
        WebElement idField = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_custID\"]/span"));
        idField.click();

        // Locate and enter ID value
        WebElement idInput = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        wait.until(ExpectedConditions.elementToBeClickable(idInput));
        idInput.sendKeys("D10");

        // Select a specific option from the dropdown
        WebElement dropdownOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"select2-cheque_custID-results\"]/li[2]")));
        dropdownOption.click();

        // Locate and click the bank dropdown
        WebElement bankField = driver.findElement(By.xpath("//*[@id=\"inputArea_cheque_IssueBank\"]/span/span[2]/span"));
        bankField.click();

        // Locate and enter bank type
        WebElement bankInput = driver.findElement(By.xpath("/html/body/span/span/span[1]/input"));
        wait.until(ExpectedConditions.elementToBeClickable(bankInput));
        bankInput.sendKeys("A");
        Thread.sleep(2000);

        // Select a specific bank option from the dropdown
        WebElement bankOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"select2-cheque_IssueBank-results\"]/li[4]")));
        bankOption.click();

        WebElement inputBranchName = driver.findElement(By.xpath("//*[@id=\"cheque_branchName\"]"));
        inputBranchName.sendKeys("Kuala Lumpur");

        WebElement inputChequeID = driver.findElement(By.xpath("//*[@id=\"cheque_chequeNo\"]"));
        inputChequeID.sendKeys("105008");

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

        WebElement chequeAmount = driver.findElement(By.xpath("//*[@id=\"cheque_chequeAmount\"]"));
        chequeAmount.sendKeys("1000");

        WebElement referenceNo = driver.findElement(By.xpath("//*[@id=\"cheque_invoiceNo\"]"));
        referenceNo.sendKeys("123456");

        WebElement validity = driver.findElement(By.xpath("//*[@id=\"cheque_validity\"]"));
        validity.sendKeys("3");

        WebElement remark = driver.findElement(By.xpath("//*[@id=\"cheque_remark\"]"));
        remark.sendKeys("MAY-10803");

        // Locate and click the Received Date field
        WebElement receivedDate = driver.findElement(By.xpath("//*[@id=\"cheque_receiveDate\"]"));
        receivedDate.click();

        // Select a specific month, year, and date from the date picker
        WebElement monthDropdown2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[19]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]")));
        monthDropdown2.click();
        WebElement monthSelector2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[19]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[1]/option[1]")));
        monthSelector2.click();

        WebElement yearDropdown2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[19]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]")));
        yearDropdown2.click();
        WebElement yearSelector2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[19]/div[2]/div[1]/table/thead/tr[1]/th[2]/select[2]/option[101]")));
        yearSelector2.click();

        WebElement dateSelector2 = driver.findElement(By.xpath("/html/body/div[19]/div[2]/div[1]/table/tbody/tr[4]/td[1]"));
        dateSelector2.click();

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

    @Test
    public void FIMS_49() throws InterruptedException{
        //Locate and click Cheque Release Link
        WebElement chequeReleaseLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1656\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(chequeReleaseLink)).click();

        Thread.sleep(2000);

        // Locate and click the checkbox for the first row in the Cheque Release table
        WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"List_of_Cheque_Release\"]/tbody/tr[1]/td[7]/div/label"));
        checkBox.click();
        // Locate and click the Cheque Release button
        WebElement chequeRelease = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"btnSubmit\"]")));
        chequeRelease.click();

        // Wait for the successful message to appear
        WebElement successfulMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[2]")));
        String actualText = successfulMessage.getText();
        String expectedText = "The information was successfully updated. Transaction was completed.";

        // Verify if the actual message matches the expected message
        Assert.assertEquals("Cheque Release is not successfully", expectedText, actualText);
        System.out.println("Cheque Release is successfully");

        // Close the pop-up message
        WebElement closePopUpMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[3]/button")));
        closePopUpMessage.click();

    }

    @Test
    public void FIMS_50() throws InterruptedException{
        //Locate and click Cheque List Link
        WebElement chequeListLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1720\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(chequeListLink)).click();

        Thread.sleep(2000);

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
    @After
    public void closeDriver(){
        driver.close();
    }

}
