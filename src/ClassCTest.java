package ui;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.time.Duration;
import java.util.List;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertEquals;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**
 * 
 * @author
 *
 */

public class ClassCTest {
	//Initialize the ChromeDriver
	ChromeDriver driver = new ChromeDriver();
	

	@Before
	public void beforeTest() throws InterruptedException {
        //Set up ChromeDriver using WebDriverManager
		WebDriverManager.chromedriver().setup();
		
		//Maximize the browser window 
		driver.manage().window().maximize();
		
		//Navigate to the FIMS CLONE login page.
		driver.get("https://fimsclone.kerisi.my/ ");
		
		// Enter username and password 
		driver.findElement(By.id("userID")).sendKeys("ENTRY2");
		driver.findElement(By.id("userPassword")).sendKeys("qwertyuiop");
		
		//Click the login button
		driver.findElement(By.id("login")).click();
		
        //Get the current URL and check if it matches the expected URL after login
		String URL = driver.getCurrentUrl();
		assertTrue(URL.equals("https://fimsclone.kerisi.my/index.php?a=NjQ1PUFYWW5WV1B3RjJabDkxZHlGR2N3Vm1jbTBXWnVWWFNFMVRNMGNETW1RR2RmeFdhelJYYXVkMlhpbDNYbkozYjFCWFBoeEdiNTc1"));
		System.out.println("\nLogin into Main Dashboard successfully");
		
		//Click Account Receivable and Invoice menu items
		driver.findElement(By.id("menu_id_1024")).click();
		WebElement invoice = driver.findElement(By.id("menu_id_1040"));
		Thread.sleep(500);
		invoice.click();
	}
	
	/**
	 * The system navigates to the “My Request Form” link when the “New” button is clicked.
	 * @throws InterruptedException
	 * @throws AWTException 
	 */
	//@Ignore
	
	@Test
	public void tc21_NavigationLinkTest() throws InterruptedException{
		//Click My Request menu item
		driver.findElement(By.id("menu_id_1581")).click();
		
		//Pause execution for 5 seconds
		Thread.sleep(5000);
		
		//Click New button in My Request page
		driver.findElement(By.xpath("//*[@id=\"dtInvoiceRequest_container\"]/div[3]/a[5]")).click();
		
		//Assert that the myrequestform menu item is displayed
		WebElement myrequestform = driver.findElement(By.id("menu_id_1582"));
		assertTrue(myrequestform.isDisplayed());
		System.out.println("Navigation link changed from My Request to My Request Form once the New button is clicked");
		
		//Quit the WebDriver and close the browser
		driver.quit();
	}
	
	@Test
	public void  tc22_SearchToolTest()throws InterruptedException{
		//Click My Request menu item
		driver.findElement(By.id("menu_id_1581")).click();
		
		//Search for the text "ISMA"
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("ISMA");
		
		//Assert that the input contains the text "ISMA"
		String input = driver.findElement(By.xpath("//input[@type='search']")).getAttribute("value");
		assertTrue(input.equals("ISMA"));
		System.out.println("ISMA is entered");
		
		
		//Assert that the result found equals "ISMA KEKAL ENTERPRISE"
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement debtorname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[2]/td[5]")));
		String debtorNameText = debtorname.getText();
		assertTrue(debtorNameText.equals("ISMA KEKAL ENTERPRISE"));
		System.out.println("ISMA KEKAL ENTERPRISE is displayed after ISMA input is entered in the search field");

		
		//Pause execution for 5 seconds
		Thread.sleep(5000);
		
		//Clear the search input field to search for another input
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		
		//Pause execution for 5 seconds
		Thread.sleep(5000);
		
		// Assuming you have a WebDriver instance called 'driver'

		// Refresh the current page
		driver.navigate().refresh();

		//Search for the text "D6349"
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("D6349");
		
		
		//Assert that the input contains the text "D6349"
		String input1 = driver.findElement(By.xpath("//input[@type='search']")).getAttribute("value");
		assertTrue(input1.equals("D6349"));
		System.out.println("D6349 is entered");
		
		//Assert that the result found equals "D6349"
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement debtorID = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[2]/td[4]")));
		String debtorIDText = debtorID.getText();
		assertTrue(debtorIDText.equals("D6349"));
		System.out.println("D6349 is displayed after D6349 input is entered in the search field");
		
		//Quit the WebDriver and close the browser
		driver.quit();
	}
	
	@Test
	public void tc23_FormDetailValidationTest()throws InterruptedException{
		//Click My Request menu item
		driver.findElement(By.id("menu_id_1581")).click();
		
		//Pause execution for 5 seconds
		Thread.sleep(5000);
		
		//Click New button in My Request page
		driver.findElement(By.xpath("//*[@id=\"dtInvoiceRequest_container\"]/div[3]/a[5]")).click();
		
		//Enter text "Abc123" in text field of contact person
		WebElement contactperson = driver.findElement(By.id("cim_contact_person"));
		contactperson.sendKeys("Abc123");
		
		Thread.sleep(2000);
		
		// Check for the presence of an alert and its text content using try-catch and assert
		try {
		    System.out.println("Attempting to check for alert for incorrect input for contact person");
		    Alert alert = driver.switchTo().alert();
		    String alertText = alert.getText();
		    Assert.assertFalse("Alert is present but text is empty", alertText.isEmpty());
		    System.out.println("Alert present with text: " + alertText);
		} catch (NoAlertPresentException e) {
		    System.out.println("No alert present");
		    Assert.fail("No alert present");
		}
		
		driver.quit();
	}
	
	@Test
	public void  tc24_DatePickerTest()throws InterruptedException{
		//Click My Request menu item
		driver.findElement(By.id("menu_id_1581")).click();
		
		//Pause execution for 5 seconds
		Thread.sleep(5000);
		
		//Click New button in My Request page
		driver.findElement(By.xpath("//*[@id=\"dtInvoiceRequest_container\"]/div[3]/a[5]")).click();

		// Click on the element to open the calendar
		driver.findElement(By.id("cim_invoice_date")).click();

		// Wait for the calendar or date picker to become visible
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.className("table-condensed")));

		// Locate and select the specific date "11/1/2024"
		WebElement calendar = driver.findElement(By.className("table-condensed"));
		List<WebElement> dates = calendar.findElements(By.tagName("td"));

		// Create an instance of Actions class
		Actions actions = new Actions(driver);

		// Iterate through the dates to find and click on "11/1/2024"
		for (WebElement date : dates) {
		    if (date.getText().equals("11")) { // Check if the day is "11"
		    	Thread.sleep(1000);
		        // Hover over the date element
		        actions.moveToElement(date).perform();
		    	Thread.sleep(1000);
		        date.click();
		        break; // Exit the loop after clicking the date
		    }
		}
		
		// Locate the date input field
		WebElement dateinput = driver.findElement(By.id("cim_invoice_date"));

		// Get the text (input) from the date input field
		String actualDate = dateinput.getAttribute("value");

		// Define the expected date
		String expectedDate = "11/01/2024";

		// Verify if the actual date matches the expected date
		assertEquals(expectedDate, actualDate);

		// Print a message based on the assertion result
		if (expectedDate.equals(actualDate)) {
		    System.out.println("Date is displayed successfully: " + actualDate);
		} else {
		    System.out.println("Date verification failed. Expected: " + expectedDate + ", Actual: " + actualDate);
		}
		;
		driver.quit();
	}
	
	@Test
	public void tc25_CorrespondencePermanentAlignedTest()throws InterruptedException{
		
		Thread.sleep(500);
		//Click Debtor Profile menu item
		driver.findElement(By.id("menu_id_1728")).click();
		
		//Enter Correspondence Address
		driver.findElement(By.id("vcs_address")).sendKeys("65, Jln Pokok Mangga 8, Tmn Pokok Mangga, 75250 Melaka");

		// Locate the Correspondence Address input field
		WebElement coraddress = driver.findElement(By.id("vcs_address"));

		// Get the value from the input field
		String coraddresstext = coraddress.getAttribute("value");

		// Define the expected address
		String expectedAddress = "65, Jln Pokok Mangga 8, Tmn Pokok Mangga, 75250 Melaka";

		// Verify if the actual address matches the expected address
		assertTrue(coraddresstext.equals(expectedAddress));

		// Print a message based on the assertion result
		if (coraddresstext.equals(expectedAddress)) {
		    System.out.println("Correspondence Address is entered correctly: " + coraddresstext);
		} else {
		    System.out.println("Correspondence Address verification failed. Expected: " + expectedAddress + ", Actual: " + coraddresstext);
		}
		
		//Click check box to fill permanent address automatically
		driver.findElement(By.xpath("//*[@id=\"inputArea_selected\"]/div/label")).click();
		
		//Verify if the permanent address is same as correspondence address
		WebElement peraddress = driver.findElement(By.id("vcs_addr1"));
		String peraddresstext = peraddress.getAttribute("value");
		assertTrue(coraddresstext.equals(peraddresstext));
		System.out.println("Check box has worked successfully as the permanent address is aligned with the correspondece address");
		
		driver.quit();
	}
	
	@Test
	public void tc26_FilterToolTest()throws InterruptedException{
		
		Thread.sleep(500);
		
		//Click Recurring List menu item
		driver.findElement(By.id("menu_id_1757")).click();
		
		Thread.sleep(5000);
		driver.findElement(By.className("input-group-append")).click();

		//Wait for the dropdown element to be clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.name("csm_cust_type_desc")));

		// Create a Select object and select STUDENT by customer type
		Select select = new Select(dropdown);
		select.selectByValue("STUDENT");
		
		//Click OK
		driver.findElement(By.xpath("//*[@id=\"dtRecurringSmartFilter\"]/div/div/div[3]/button[2]")).click();
		
		Thread.sleep(1000);
		
		//Verify if the data is now filtered to STUDENT as customer type
		WebElement customertype = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[2]/td[5]")));
		String customertypetext = customertype.getText();
		assertTrue(customertypetext.equals("STUDENT"));
		System.out.println("The filter tool has worked successfully as the customer type has been filtered to STUDENT");
		
		
		driver.quit();

	}
	
	@Test
	public void tc27_DownloadFunctionalityTest() throws InterruptedException {
	    //Set the download directory
	    String downloadDirectory = "C:\\Users\\User\\Downloads";

	    //Configure Chrome to specify the download directory
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("download.default_directory=" + downloadDirectory);

	    Thread.sleep(500);

	    //Click Recurring List menu item
	    driver.findElement(By.id("menu_id_1757")).click();

	    //Pause execution
	    Thread.sleep(5000);

	    //Click Download button
	    driver.findElement(By.id("btnDownload")).click();

	    //Check if any downloaded file with the ".xlsx" extension exists
	    File[] downloadedFiles = new File(downloadDirectory).listFiles(new FilenameFilter() {
	        @Override
	        public boolean accept(File dir, String name) {
	            return name.endsWith(".xlsx");
	        }
	    });

	    Assert.assertTrue("No downloaded files with the '.xlsx' extension found.", downloadedFiles != null);
	    
	    if (downloadedFiles != null ) {
	        System.out.println("Downloaded files with the '.xlsx' extension found.");
	    }
	    
		driver.quit();

	}
	
	@Test
	public void  tc28_NewRecurringListTest() throws InterruptedException{
	    Thread.sleep(500);

	    //Click Recurring List menu item
	    driver.findElement(By.id("menu_id_1757")).click();
	    
	    Thread.sleep(1000);
	    
        // Create a JavascriptExecutor object
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll down the page by a specific pixel value 
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 100);"); // Scroll down by 200 pixels
            Thread.sleep(1000); // Wait for 1 second between scrolls
        }

	    //Click New button
	    driver.findElement(By.xpath("//*[@id=\"dtRecurring_container\"]/div[3]/a")).click();
	    
	    //Click Customer Type text field
	    driver.findElement(By.id("inputArea_csm_cust_type")).click();
	    
		//Wait for the dropdown element to be clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.name("csm_cust_type")));

		// Create a Select object and select STUDENT by customer type
		Select select = new Select(dropdown);
		select.selectByValue("A");
	    driver.findElement(By.id("inputArea_csm_cust_type")).click();

		//Enter and select Name
		driver.findElement(By.id("inputArea_vcs_vendor_code")).click();
		Thread.sleep(500);
		WebElement name = driver.findElement(By.className("select2-search__field"));
		name.sendKeys("Chu Kea Qiu");
		Thread.sleep(15000);
		name.sendKeys(Keys.ENTER);
		
		Thread.sleep(500);
		
		//Enter and select Premise
		driver.findElement(By.id("inputArea_pe_id")).click();
		WebElement premise = driver.findElement(By.className("select2-search__field"));
		premise.sendKeys("010215");
		Thread.sleep(5000);
		premise.sendKeys(Keys.ENTER);

		Thread.sleep(500);
		
		//Enter Offer Letter Ref. No
		driver.findElement(By.id("csm_contract_ref_no")).sendKeys("102349");

		
		Thread.sleep(500);
		
		//Pick a date for Contract Duration: From
		//Click the text field of Start Date to open calender widget
		driver.findElement(By.id("csm_start_date")).click();

		//Wait for up to 5 seconds for the calendar widget to become visible. The widget is identified by the class name 'table-condensed'
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.className("table-condensed")));
		
		//Find the calendar element using its class name and store it in 'startCalendar'
		WebElement startCalendar = driver.findElement(By.className("table-condensed"));
		
		//Find all the 'td' elements within the calendar
		List<WebElement> startDates = startCalendar.findElements(By.tagName("td"));

		//Initialize an Actions object to perform complex mouse actions
		Actions actionsStart = new Actions(driver);

		//Iterate over each date element in the calendar.
		for (WebElement date : startDates) {
		    //Check if the text of the date element is "1"
		    if (date.getText().equals("1")) { 
		        Thread.sleep(1000);
		        //Trigger hover effects
		        actionsStart.moveToElement(date).perform();
		        Thread.sleep(1000);
		        //Select the first day of the month in the calendar
		        date.click();
		        break; 
		    }
		}

		Thread.sleep(1000);
		
		//Pick a date for Contract Duration: To
		driver.findElement(By.id("csm_end_date")).click();
		
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

		for (int i = 0; i < 2; i++) {
		    WebElement nextButton = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[20]/div[2]/div[1]/table/thead/tr[1]/th[3]")));
		    nextButton.click();
		    wait1.until(ExpectedConditions.stalenessOf(nextButton));
		}

		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[20]/div[2]/div[1]/table")));

		WebElement endCalendar = driver.findElement(By.xpath("/html/body/div[20]/div[2]/div[1]/table"));
		List<WebElement> endDates = endCalendar.findElements(By.tagName("td"));

		Actions actionsStart1 = new Actions(driver);

		for (WebElement date1 : endDates) {
		    if (date1.getText().equals("1")) { 
		        Thread.sleep(1000);

		        actionsStart1.moveToElement(date1).perform();
		        Thread.sleep(1000);
		        date1.click();
		        break; 
		    }
		}

		//Choose Yes for Discount Entitlement
	    WebElement discount = driver.findElement(By.id("inputArea_csm_discount_entitlement"));
	    discount.click();	    
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown1 = wait2.until(ExpectedConditions.elementToBeClickable(By.name("csm_discount_entitlement")));
		Select select1 = new Select(dropdown1);
		select1.selectByValue("Y");
		driver.findElement(By.id("inputArea_csm_discount_entitlement")).click();	
		
		//Pick a date for Inv. Generation Date
		driver.findElement(By.id("inputArea_csm_enter_date")).click();
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[21]/div[2]/div[1]/table")));
		WebElement invgenerateCalendar = driver.findElement(By.xpath("/html/body/div[21]/div[2]/div[1]/table"));
		List<WebElement> invgenerateDates = invgenerateCalendar.findElements(By.tagName("td"));

		Actions actionsStart2= new Actions(driver);

		for (WebElement date2 : invgenerateDates) {
		    if (date2.getText().equals("10")) { 
		        Thread.sleep(1000);

		        actionsStart2.moveToElement(date2).perform();
		        Thread.sleep(1000);
		        date2.click();
		        break;
		    }
		}
		
		//Choose No for Deposit
		driver.findElement(By.id("inputArea_deposit_Exist")).click();
		WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement dropdown2 = wait3.until(ExpectedConditions.elementToBeClickable(By.name("deposit_Exist")));
		Select select2 = new Select(dropdown2);
		select2.selectByValue("N");
		driver.findElement(By.id("inputArea_deposit_Exist")).click();

	    
	    Thread.sleep(1000);

	    //Scroll down to find save button
	    for (int i = 0; i < 5; i++) {
	        js.executeScript("window.scrollBy(0, 500);"); // Scroll down by 200 pixels
	        Thread.sleep(1000); // Wait for 1 second between scrolls
	    }
	    
	    //Click save button
	    driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();
	    
	    //Navigate to Recurring List to check if the information is saved successfully
	    driver.findElement(By.xpath("//*[@id=\"list_menu_id_1757\"]")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[@id=\"dtRecurring_filter\"]/label/div/div/input")).click();
	    driver.findElement(By.xpath("//*[@id=\"dtRecurring_filter\"]/label/div/div/input")).sendKeys("Chu Kea Qiu");

	    Thread.sleep(5000);
	    //Verify if the information is saved successfully by searching the customer name in the Recurring List
		WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement customername = wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id=\"dtRecurring\"]//tr[2]/td[4]")));
		String customernameText = customername.getText();
		assertTrue(customernameText.equals("CHU KEA QIU"));
		System.out.println("Information related to customer CHU KEA QIU is saved successfully");
		
		driver.quit();

	}

	@Test
	public void tc29_UploadFileFunctionalityTest() throws InterruptedException, AWTException{
	    Thread.sleep(500);

	    //Click Recurring List menu item
	    driver.findElement(By.id("menu_id_1758")).click();

	    
	    Thread.sleep(1000);
        WebElement div = driver.findElement(By.id("inputArea_contract_doc"));
        div.click();

        //Use Robot class to interact with the file dialog
        Robot robot = new Robot();

        //Press Enter to open the file dialog
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        //Wait for a few seconds to allow the file dialog to open
        Thread.sleep(1000);

        //Type the file path using the Robot class 
        String filePath = "C:\\Users\\User\\Downloads\\Recurring List_20240116 0226AM.xlsx";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection stringSelection = new StringSelection(filePath);
        clipboard.setContents(stringSelection, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        //Press Enter to confirm the file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        Thread.sleep(500);
        
        //Verify that the uploaded file matches the expected file
        WebElement uploadedFileElement = driver.findElement(By.xpath("//*[@id=\"contract_doc\"]/div[2]"));
        String actualFileName = uploadedFileElement.getText();
        
        String normalizedActualFileName = actualFileName.trim().replaceAll("\\s+", " ");
        String expectedFileName = "40.2 KB Recurring List_20240116 0226AM.xlsx";
        assertTrue(normalizedActualFileName.equals(expectedFileName));
        System.out.println("File upload successful");
        
		driver.quit();
	}
	
	@Test
	public void tc30_ResetFunctionalityTest() throws InterruptedException{
	    Thread.sleep(500);

	    //Click Recurring List menu item
	    driver.findElement(By.id("menu_id_1758")).click();
	    
	    //Click date text field to prompt calendar widget
		driver.findElement(By.id("cim_invoice_date")).click();
		
		//Pick 1 January 2024
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.className("table-condensed")));
		WebElement calendar = driver.findElement(By.className("table-condensed"));
		List<WebElement> dates = calendar.findElements(By.tagName("td"));
		Actions actionsStart = new Actions(driver);
		for (WebElement date : dates) {
		    if (date.getText().equals("1")) { 
		        Thread.sleep(1000);
		        actionsStart.moveToElement(date).perform();
		        Thread.sleep(1000);
		        date.click();
		        break; 
		    }
		}
		
		Thread.sleep(500);
		//Check if 1 January 2024 is picked
		WebElement datetextfield = driver.findElement(By.id("cim_invoice_date"));
		String datetext = datetextfield.getAttribute("value");
		assertTrue(datetext.equals("01/01/2024"));
		System.out.println("1 January 2024 is picked");
		
		//Click Reset button
		driver.findElement(By.id("cim_invoice_date")).click();
		driver.findElement(By.xpath("/html/body/div[18]/div[4]/button[1]")).click();
		
		Thread.sleep(500);
		
		//Check if the date text field is empty
		WebElement datetextfieldafterreset = driver.findElement(By.id("cim_invoice_date"));
		String datetextafterreset = datetextfieldafterreset.getAttribute("value");
		assertTrue(datetextafterreset.equals(""));
		System.out.println("1 January 2024 is reset successfully");
		
		driver.quit();
	}
	
}
