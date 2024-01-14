import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Assert;

public class ClassATest {

    WebDriver driver;
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @Before
    public void setUp() throws InterruptedException, AWTException {
        // Set the path for the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\selenium webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();

        // Wait for browser open
 		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

 		// Perform Login steps
        // Step 1: Open the FIMS_Clone login page
 		System.out.println("Step 1");
        driver.get("https://fimsclone.kerisi.my/login.php?a=NzM2NjI5");
        // Maximize the browser window
  		driver.manage().window().maximize();
  		Thread.sleep(5000);
  		// Simulating a zoom-out action in the browser using the Robot class with CTRL and MINUS keyboard shortcuts.
  		Robot robot = new Robot();
		  	  for (int i = 0; i < 4; i++) {
		  	   robot.keyPress(KeyEvent.VK_CONTROL);
		  	   robot.keyPress(KeyEvent.VK_SUBTRACT);
		  	   robot.keyRelease(KeyEvent.VK_SUBTRACT);
		  	   robot.keyRelease(KeyEvent.VK_CONTROL);
		  	  }
	  	Thread.sleep(1000);
      		

        // Step 2: Enter Username 
 		System.out.println("Step 2");
        driver.findElement(By.id("userID")).sendKeys("ENTRY1");
        Thread.sleep(1000);
        
        // Step 3: Enter Password 
 		System.out.println("Step 3");
        driver.findElement(By.id("userPassword")).sendKeys("qwertyuiop");
        Thread.sleep(1000);
        
        // Step 4: Click the “Sign In” button
 		System.out.println("Step 4");
        driver.findElement(By.id("login")).click();
        Thread.sleep(2000);
        
        // Step 5: Click the “Account Receivable’ item from side navigation menu
 		System.out.println("Step 5");
        driver.findElement(By.id("menu_id_1024")).click();
        Thread.sleep(1000);
        
      	// Step 6: Click the “Authorized Receipting” sub menu item
 		System.out.println("Step 6");
        driver.findElement(By.id("menu_id_1952")).click();
        Thread.sleep(1000);

//        // Navigate to a specific page after login
//        driver.navigate().to("https://fimsclone.kerisi.my/index.php?a=NjQ1PUFYWW5WV1B3RjJabDkxZHlGR2N3Vm1jbTBXWnVWWFNFMVRNMGNETW1RR2RmeFdhelJYYXVkMlhpbDNYbkozYjFCWFBoeEdiNTc1");
//
//        // Assertion for successful login
//        String currentURL = driver.getCurrentUrl();
//        assertTrue(currentURL.equals("https://fimsclone.kerisi.my/index.php?a=NjQ1PUFYWW5WV1B3RjJabDkxZHlGR2N3Vm1jbTBXWnVWWFNFMVRNMGNETW1RR2RmeFdhelJYYXVkMlhpbDNYbkozYjFCWFBoeEdiNTc1"));
//        System.out.println("Login into FIMS main page successful");
    }//end of setUp() 
    
    
    @Test
    public void tc1_ResetSmartFilterInputs() throws InterruptedException {
    	    	
        // Step 7: Click on the smart filter icon
    	System.out.println("Step 7");
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[1]/label/div/div/div/span")).click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement generalOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/form/div/div[2]/div/div"))); 
	    
	    // Step 8: Input various filter criteria into the Smart Filter pop-up
    	System.out.println("Step 8");
	    
    	// Set filter criteria for the "Staff" field
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div/div/div[2]/div/div[1]/span/span[2]/span")).click(); // Click on the "Staff" input field
        WebElement staff = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/span/span/span[1]/input")); 
	    Thread.sleep(1000);	        
	    staff.sendKeys("JOHARI BIN DAN"); // Enter the staff name "JOHARI BIN DAN"
        Thread.sleep(1000);  		
		WebElement staffOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/form/div/div[2]/span/span/span[2]/ul/li"))); 
        staffOption.click(); // Click on the staff shown in the dropdown
	    Thread.sleep(1000);
	    
	    // Set filter criteria for the "PTJ" field
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div/div/div[2]/div/div[2]/span/span[2]/span")).click(); //Click on the "PTJ" input field
        WebElement ptj = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/span/span/span[1]/input"));
	    Thread.sleep(1000);	        
	    ptj.sendKeys("S10800");// Enter the PTJ "S10800"
        Thread.sleep(1000);  		
		driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/span/span/span[2]/ul/li")).click();// Click on the PTJ shown in the dropdown
	    Thread.sleep(1000);
	    
	    // Set filter criteria for the "Status" field
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div/div/div[2]/div/div[3]/span/span[2]/span")).click(); //Click on the "Status" input field
        WebElement statusOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/form/div/div[2]/span/span/span[2]/ul/li[1]"))); 
	    statusOption.click();  // Click on the "ENTRY" option shown in the dropdown
	    Thread.sleep(1000);
	    	    
	    // Step 9: Click on the "Reset" button within the Smart Filter pop-up.
    	System.out.println("Step 9");
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div/div/div[3]/button[1]")).click();
	    Thread.sleep(1000);


	    // Verify that the "Staff" input field is empty after clearing
	    String staffInputFieldValue = driver.findElement(By.id("select2-stf_staff_id_desc-container")).getText();
	    assertTrue("Element is not empty after clearing", staffInputFieldValue.isEmpty());
	    
	    // Verify that the "PTJ" input field is empty after clearing
	    String ptjInputFieldValue = driver.findElement(By.id("select2-oun_code_ptj-container")).getText();
	    assertTrue("Element is not empty after clearing", ptjInputFieldValue.isEmpty());
	    
	    // Verify that the "Status" input field is empty after clearing
	    String statusInputFieldValue = driver.findElement(By.id("select2-are_status-container")).getText();
	    assertTrue("Element is not empty after clearing", statusInputFieldValue.isEmpty());
	  
	    System.out.println("tc1_ResetSmartFilterInputs: Completed");
	    System.out.println(" ");
	    
    }//end of test case 1
    
   
    @Test
    public void tc2_DatePickerInteraction() throws InterruptedException {
    	
    	// Step 7: Click the “+New” button 
    	System.out.println("Step 7"); 
	    WebElement button = driver.findElement(By.cssSelector("a.btn.btn-primary.ml-1"));  // Locate and click on a button using JavaScriptExecutor
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", button);
	   	
	    // Step 8: Locate the "Request Date" input field
	    System.out.println("Step 8"); 
	    // Step 9: Click on the "Request Date" input field to open the date picker
	    System.out.println("Step 9"); 
	    driver.findElement(By.id("createddate")).click();
        
	    // Step 10: Select a specific date (05/02/2024) using the date picker
	    System.out.println("Step 10"); 
	    // Select the desired month
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement monthDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("monthselect")));
	    Select monthSelect = new Select(monthDropdown);
	    monthSelect.selectByVisibleText("February"); 
	      
	    // Select the desired year
	    WebElement yearDropdown = driver.findElement(By.className("yearselect"));
	    Select yearSelect = new Select(yearDropdown);
	    yearSelect.selectByVisibleText("2024");
	      
	    // Click on a specific day in the date picker
	    driver.findElement(By.xpath(" /html/body/div[18]/div[2]/div[1]/table/tbody/tr[2]/td[1]")).click();
	      
		// Verify that the chosen date is correctly reflected in the "Request Date" input field		  
	    WebElement createdDateInput = driver.findElement(By.id("createddate")); // Locate the createddate input field
	    String selectedDate = createdDateInput.getAttribute("value"); // Get the value attribute (selected date) of the input field
	    String expectedDate = "05/02/2024"; // Replace "expectedDate" with the expected date in the format it is displayed in the input fiel
	    assertTrue("Selected date is not correctly reflected in the input field", selectedDate.equals(expectedDate)); // Verify that the chosen date is correctly reflected in the "Request Date" input field
	    
	    System.out.println("tc2_DatePickerInteraction: Completed");
	    System.out.println(" ");
	   
    }//end of test case 2
    
    @Test
    public void tc3_RequiredInputFieldWithoutInput() throws InterruptedException {
 	
    	// Step 7: Click the “+New” button 
    	System.out.println("Step 7"); 
	    WebElement button = driver.findElement(By.cssSelector("a.btn.btn-primary.ml-1"));  // Locate and click on a button using JavaScriptExecutor
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", button);
	    
	    // Step 8: Leave the "Collection Type" required input field empty without input
	    System.out.println("Step 8"); 
	    // Step 9: Click the "Save" button without input anything into the "Collection Type" required input field
	    System.out.println("Step 9"); 
	    driver.findElement(By.id("entrySave")).click();
	    
	    // Verify system response for the "Collection Type" required input field
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set up a WebDriverWait instance to wait for up to 10 seconds for the error message element to be visible
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[19]/div/div/div[2]"))); //Wait until the error message element is visible in the DOM
        String actualErrorMessage = errorMessage.getText(); // Get the actual text of the error message display
        String expectedErrorMessage = "Information failed to be saved."; // Expected error message
        assertTrue("Displayed message doesn't match the expected error message", actualErrorMessage.equals(expectedErrorMessage)); // Verify that the displayed error message matches the expected error message
              
        System.out.println("tc3_RequiredInputFieldWithoutInput: Completed");
	    System.out.println(" ");
	          
    } //end of test case 3
    
    @Test
    public void tc4_RequiredInputFieldWithValidInput() throws InterruptedException {

    	// Step 7: Click the “+New” button 
    	System.out.println("Step 7"); 
	    WebElement button = driver.findElement(By.cssSelector("a.btn.btn-primary.ml-1"));  // Locate and click on a button using JavaScriptExecutor
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", button);
	   	    
	    // Step 8: Enter “GENERAL” as the valid input in the "Collection Type" required input field using the data provided in the dropdown menu
	    System.out.println("Step 8"); 
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[8]/span/span[2]/span")).click(); // Click on  Collection Type input field to display the dropdown menu
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set up a WebDriverWait instance to wait for up to 10 seconds for the dropdown menu to be visible
	    WebElement generalOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span/span/span[2]/ul/li[2]")));  // Wait until the "GENERAL" option in the dropdown menu is visible
	    generalOption.click(); // Click on the "GENERAL" option in the dropdown menu
	    	    
	    // Step 9: Click the "Save" button to save the application 
	    System.out.println("Step 9"); 
	    driver.findElement(By.id("entrySave")).click();
	    
	    // Verify system response for the "Collection Type" required input field
	    WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[19]/div/div/div[2]"))); // Set up a WebDriverWait instance to wait for the success message element to be visible
        String actualMessage = successMessage.getText(); // Get the actual text of the success message
        String expectedMessage = "Information was successfully saved."; // Expected success message
        assertTrue("Displayed message doesn't match the expected message", actualMessage.equals(expectedMessage)); // Verify that the displayed message matches the expected message
        
        System.out.println("tc4_RequiredInputFieldWithValidInput: Completed");
	    System.out.println(" ");
    }// end of test case 4    


    @Test
    public void tc5_ClearFunction() throws InterruptedException {
	    
    	// Step 7: Click the “+New” button 
    	System.out.println("Step 7"); 
	    WebElement button = driver.findElement(By.cssSelector("a.btn.btn-primary.ml-1"));  // Locate and click on a button using JavaScriptExecutor
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", button);
	    	    
		// Step 8: Input data into the “Collection Type” input field 
    	System.out.println("Step 8"); 
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[8]/span/span[2]/span")).click(); // Click on  Collection Type input field to display the dropdown menu
	   
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set up a WebDriverWait instance to wait for up to 10 seconds for the dropdown menu to be visible
	    WebElement generalOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span/span/span[2]/ul/li[2]"))); // Wait until the "GENERAL" option in the dropdown menu is visible
	    generalOption.click();   // Click on the "GENERAL" option in the dropdown menu
	    Thread.sleep(1000);
	    
	    // Step 9: Click on the small cross icon 
    	System.out.println("Step 9"); 		
		driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[8]/span/span[1]")).click();// Click on the small cross icon
	    Thread.sleep(1000);
	    
	    // Verify that the "Collection Type" input field is empty after clearing
	    String inputFieldValue = driver.findElement(By.id("select2-are_purposed_code-container")).getText(); // Get the value of the "Collection Type" input field after clearing
	    assertTrue("Element is not empty after clearing", inputFieldValue.isEmpty());  // Verify that the "Collection Type" input field is empty after clearing
	  
	    System.out.println("tc5_ClearFunction: Completed");
	    System.out.println(" ");
	    
    }// end of test case 5 
    
 
    @Test
    public void tc6_ExceedCollectionPurposeTextAreaLength() throws InterruptedException {
    		    
    	// Step 7: Click the “+New” button 
    	System.out.println("Step 7"); 
	    WebElement button = driver.findElement(By.cssSelector("a.btn.btn-primary.ml-1"));  // Locate and click on a button using JavaScriptExecutor
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", button);
	    	    
		// Step 8: Input data into the “Collection Type” input field 
    	System.out.println("Step 8"); 
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[8]/span/span[2]/span")).click(); // Click on  Collection Type input field to display the dropdown menu
	   
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set up a WebDriverWait instance to wait for up to 10 seconds for the dropdown menu to be visible
	    WebElement generalOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span/span/span[2]/ul/li[2]"))); // Wait until the "GENERAL" option in the dropdown menu is visible
	    generalOption.click();   // Click on the "GENERAL" option in the dropdown menu
	    Thread.sleep(1000);
	    
	    // Step 9: Enter a lengthy "Collection Purpose" that exceeded the allowable length
    	System.out.println("Step 9"); 
	    WebElement collectionPurposeTextArea = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[10]/textarea")); //Locate the "Collection Purpose" textarea element
	    Thread.sleep(1000);	        
        collectionPurposeTextArea.sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam vitae arcu lectus. Nullam augue metus, "
        		+ "tincidunt id turpis sit amet, blandit dictum ligula. Vestibulum facilisis purus vel nibh gravida cursus. Vestibulum eget leo "
        		+ "scelerisque, convallis odio hendrerit, egestas nisi. Nunc ultrices felis in nunc hendrerit aliquet. Nam sem ex, aliquet non "
        		+ "placerat sed, auctor consectetur sapien. Vestibulum quis nisi et purus tincidunt gravida. Vestibulum sapien quam, facilisis "
        		+ "sed nisi sit amet, malesuada sollicitudin libero. Duis scelerisque erat at odio euismod, ullamcorper condimentum risus semper. "
        		+ "Duis mollis magna id ligula mattis, at molestie enim aliquet. Nam hendrerit efficitur massa at feugiat. Vivamus a volutpat mauris. "
        		+ "Suspendisse id quam molestie, efficitur arcu ac, elementum lorem. Sed lacinia sit amet sem quis ultrices. Praesent quis ipsum faucibus, "
        		+ "sodales turpis non, vehicula nisl. Nulla sit amet dolor rutrum nunc rutrum posuere. Etiam ac metus ac metus sagittis efficitur "
        		+ "id non elit. Nam magna sem, iaculis ut lacinia ullamcorper, porta vel felis. Proin condimentum magna nec euismod interdum. "
        		+ "Ut condimentum justo a metus pretium, ac semper risus pharetra.");  //Enter a lengthy text into the "Collection Purpose" textarea
        Thread.sleep(1000);
        
        // Step 10: Click the "Save" button to save the application 
	    System.out.println("Step 10"); 
	    driver.findElement(By.id("entrySave")).click();
	    
	    // Verify system response for the lengthy "Collection Purpose"
	    WebElement failMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[19]/div/div/div[2]"))); 
        String actualMessage = failMessage.getText(); // Get the actual text of the success message
        String expectedMessage = "Information failed to be saved."; // Expected success message
        assertTrue("Displayed message doesn't match the expected message", actualMessage.equals(expectedMessage));  // Verify that the displayed message matches the expected message
        
        System.out.println("tc6_ExceedCollectionPurposeTextAreaLength: Completed");
	    System.out.println(" ");
        
    }// end of test case 6
    
    @Test
    public void tc7_DeleteAuthorizedStaffRecord () throws InterruptedException {
    	    	
    	// Step 7: Click the “+New” button 
    	System.out.println("Step 7"); 
	    WebElement button = driver.findElement(By.cssSelector("a.btn.btn-primary.ml-1"));  // Locate and click on a button using JavaScriptExecutor
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", button);
	   
	    // Step 8: Click the "+New" button on the “Authorized Staff” section to add new staff 
    	System.out.println("Step 8"); 
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div[3]/a")).click();
        Thread.sleep(1000);
        
        // Step 9: Click the “Authorized Receipting Staff” input field  
    	System.out.println("Step 9"); 
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div/div/div[2]/div/div[2]/span/span[2]/span")).click();
        
        // Step 10: Enter “NORIZAN BINTI SALLEH” as staff name into the text input field  
    	System.out.println("Step 10"); 
	    WebElement authorizedReceiptingStaffTextArea = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/span/span/span[1]/input"));
	    Thread.sleep(1000);	        
	    authorizedReceiptingStaffTextArea.sendKeys("NORIZAN BINTI SALLEH");
        Thread.sleep(1000);
      
        // Step 11: Click on the dropdown option containing information about the staff   
    	System.out.println("Step 11"); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement selectedStaff = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/form/div/div[2]/span/span/span[2]/ul/li"))); 
        String textInBTag = selectedStaff.findElement(By.tagName("b")).getText(); // Get the text within the <b> tag
        if ("1409-NORIZAN BINTI SALLEH".equals(textInBTag)) { // Check if the text is "NORIZAN BINTI SALLEH"
        	selectedStaff.click();  // If the text matches, click on the element
        	Thread.sleep(1000);
        } else {
            System.out.println("Text does not match. Expected: NORIZAN BINTI SALLEH, Actual: " + textInBTag); // If the text doesn't match, handle accordingly (e.g., throw an exception)
        }
        
        // Step 12:  Click the "Save" button after retrieving the staff info   
    	System.out.println("Step 12"); 
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div/div/div[2]/div/div[14]/div/button[2]")).click();
	    Thread.sleep(1000);
	    
	    // Step 13:  Locate the newly added record of the authorized staff (“NORIZAN BINTI SALLEH”) from the Authorized Staff table    
    	System.out.println("Step 13"); 
        WebElement staffNameColumn = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div[2]/div[4]/table/tbody/tr/td[3]"));
        String staffName = staffNameColumn.getText(); // Get the text content of the element
        if (staffName.equals("NORIZAN BINTI SALLEH")) {  // Check if the staff name is "NORIZAN BINTI SALLEH"
        	
        	// Step 14:  Click the delete icon to delete one row of staff record    
        	System.out.println("Step 14"); 
            driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div[2]/div[4]/table/tbody/tr/td[13]/a[2]")).click();
    	    Thread.sleep(1000);
    	    
    	    // Step 15:  Click "Ok" button to confirm deleting action    
        	System.out.println("Step 15"); 
    	    driver.findElement(By.xpath("/html/body/div[19]/div/div/div[3]/button[2]")).click();
    	    Thread.sleep(1000);
    	    
    	    // Verify Record is Removed
            WebElement noRecord = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div[2]/div[4]/table/tbody/tr/td/a"));
            assertTrue("The staff record still exist", noRecord.getText().equals("No records")); // Assert that the value is "DRAFT"
    	    
            System.out.println("tc7_DeleteAuthorizedStaffRecord: Completed");
    	    System.out.println(" ");
            
            
        } else {
            System.out.println("Staff name is not NORIZAN BINTI SALLEH");
            // Add actions to perform when the condition is false
        }
	    
    }//end of test case 7
    
    @Test
    public void tc8_CorrectAddAuthorizedReceiptingApplication () throws InterruptedException {
    	
    	// Step 7: Click the “+New” button 
    	System.out.println("Step 7"); 
	    WebElement button = driver.findElement(By.cssSelector("a.btn.btn-primary.ml-1"));  // Locate and click on a button using JavaScriptExecutor
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", button);
	   	
	    // Step 8: Enter valid and complete details into all required fields of the Authorized Receipting Form
	    System.out.println("Step 8"); 
	    
	    
	    // Enter “05/02/2024” for “Request Date” input field
	    driver.findElement(By.id("createddate")).click(); // Click on the "Request Date" input field to open the date picker
	    Thread.sleep(1000);
 	    WebElement monthDropdown = driver.findElement(By.className("monthselect")); // Select the desired month
	    Select monthSelect = new Select(monthDropdown); 
	    monthSelect.selectByVisibleText("February"); 
	    
	    WebElement yearDropdown = driver.findElement(By.className("yearselect")); // Select the desired year
	    Select yearSelect = new Select(yearDropdown);
	    yearSelect.selectByVisibleText("2024");
	     
	    driver.findElement(By.xpath(" /html/body/div[18]/div[2]/div[1]/table/tbody/tr[2]/td[1]")).click(); // Click on a specific day in the date picker
	    
	    
	    // Enter “GENERAL” for “Collection Type” input field.
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[8]/span/span[2]/span")).click(); // Click on  Collection Type input field to display the dropdown menu
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set up a WebDriverWait instance to wait for up to 10 seconds for the dropdown menu to be visible
	    WebElement generalOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span/span/span[2]/ul/li[2]")));  // Wait until the "GENERAL" option in the dropdown menu is visible
	    generalOption.click(); // Click on the "GENERAL" option in the dropdown menu
	    Thread.sleep(1000);
	    
	    
	    // Enter “For transportation purpose.” for "Collection Purpose “input field.
	    WebElement collectionPurposeTextArea = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[10]/textarea"));
	    Thread.sleep(1000);	        
        collectionPurposeTextArea.sendKeys("For transportation purpose.");
        Thread.sleep(1000);
        
        //Add “1262-JOHARI BIN DAN” as authorized staff
        
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div[3]/a")).click(); // Click the "+New" button on the “Authorized Staff” section to add new staff
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div/div/div[2]/div/div[2]/span/span[2]/span")).click(); //Click the “Authorized Receipting Staff” input field
        WebElement authorizedReceiptingStaffTextArea = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/span/span/span[1]/input"));
	    Thread.sleep(1000);	        
	    authorizedReceiptingStaffTextArea.sendKeys("JOHARI BIN DAN"); // // Enter “JOHARI BIN DAN” as staff name into the text input field
        Thread.sleep(1000);
        WebElement selectedStaff = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/form/div/div[2]/span/span/span[2]/ul/li[1]"))); 
        String textInBTag = selectedStaff.findElement(By.tagName("b")).getText();// Get the text within the <b> tag
        if ("1262-JOHARI BIN DAN".equals(textInBTag)) { // Check if the text is "JOHARI BIN DAN"
        	selectedStaff.click();// If the text matches, click on the element
        	Thread.sleep(1000);
        } else {
            System.out.println("Text does not match. Expected: JOHARI BIN DAN, Actual: " + textInBTag); // If the text doesn't match, handle accordingly (e.g., throw an exception)
        }
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div/div/div[2]/div/div[14]/div/button[2]")).click(); //Click the "Save" button after retrieving the staff info
	    
	    
		// Step 9: Click on the "Save" button to save the Authorized Receipting Application
	    System.out.println("Step 9"); 
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[6]/div/div/button[1]")).click();
	    		
	    
	    // Step 10: Click the “Ok” button on the message for confirmation
	    System.out.println("Step 10"); 
	    WebElement failMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[19]/div/div/div[2]"))); 
	    driver.findElement(By.xpath("/html/body/div[19]/div/div/div[3]/button")).click();
	   	Thread.sleep(1000);
		    
		    
	   	// Verify the newly added application display on the “Authorized Receipting Application” table as a “DRAFT”.
	    boolean isTitleMatching = wait.until(ExpectedConditions.titleIs("Account Receivable / Authorized Receipting"));
	        if (isTitleMatching) {// Check whether the system redirect user to Authorized Receipting
	            System.out.println("The title matches: Authorized Receipting");
	            WebElement statusColumn = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[3]/table/tbody/tr[1]/td[8]")); // Locate the first column in the table row
	            assertTrue("The value in the 'Status' column is not as expected", statusColumn.getText().equals("DRAFT")); // Verify that the value is "DRAFT"
	            
	            System.out.println("tc8_CorrectAddAuthorizedReceiptingApplication: Completed");
	    	    System.out.println(" ");
	        } else {
	            System.out.println("The title does not match: " + driver.getTitle());
	        }

    }// end of test case 8
   

    

    @Test
    public void tc9_SaveAndSubmitAuthorizedReceiptingApplication () throws InterruptedException {
    	
    	// Step 7: Click the “+New” button 
    	System.out.println("Step 7"); 
	    WebElement button = driver.findElement(By.cssSelector("a.btn.btn-primary.ml-1"));  // Locate and click on a button using JavaScriptExecutor
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", button);
	   	
	    // Step 8: Enter valid and complete details into all required fields of the Authorized Receipting Form
	    System.out.println("Step 8"); 
	    
	    
	    // Enter “05/02/2024” for “Request Date” input field
	    driver.findElement(By.id("createddate")).click(); // Click on the "Request Date" input field to open the date picker
	    Thread.sleep(1000);
 	    WebElement monthDropdown = driver.findElement(By.className("monthselect")); // Select the desired month
	    Select monthSelect = new Select(monthDropdown); 
	    monthSelect.selectByVisibleText("February"); 
	    
	    WebElement yearDropdown = driver.findElement(By.className("yearselect")); // Select the desired year
	    Select yearSelect = new Select(yearDropdown);
	    yearSelect.selectByVisibleText("2024");
	     
	    driver.findElement(By.xpath(" /html/body/div[18]/div[2]/div[1]/table/tbody/tr[2]/td[1]")).click(); // Click on a specific day in the date picker
	    
	    
	    // Enter “GENERAL” for “Collection Type” input field.
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[8]/span/span[2]/span")).click(); // Click on  Collection Type input field to display the dropdown menu
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set up a WebDriverWait instance to wait for up to 10 seconds for the dropdown menu to be visible
	    WebElement generalOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span/span/span[2]/ul/li[2]")));  // Wait until the "GENERAL" option in the dropdown menu is visible
	    generalOption.click(); // Click on the "GENERAL" option in the dropdown menu
	    Thread.sleep(1000);
	    
	    
	    // Enter “For accommodation purpose.” for "Collection Purpose “input field.
	    WebElement collectionPurposeTextArea = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[10]/textarea"));
	    Thread.sleep(1000);	        
        collectionPurposeTextArea.sendKeys("For accommodation purpose.");
        Thread.sleep(1000);
        
        
        
        //Add “1262-JOHARI BIN DAN” as authorized staff
        
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div[3]/a")).click(); // Click the "+New" button on the “Authorized Staff” section to add new staff
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div/div/div[2]/div/div[2]/span/span[2]/span")).click(); //Click the “Authorized Receipting Staff” input field
        WebElement authorizedReceiptingStaffTextArea = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/span/span/span[1]/input"));
	    Thread.sleep(1000);	        
	    authorizedReceiptingStaffTextArea.sendKeys("SUHANIF BIN OMAR"); // // Enter “JOHARI BIN DAN” as staff name into the text input field
        Thread.sleep(1000);
        WebElement selectedStaff = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/form/div/div[2]/span/span/span[2]/ul/li[1]"))); 
        String textInBTag = selectedStaff.findElement(By.tagName("b")).getText();// Get the text within the <b> tag
        if ("3248-SUHANIF BIN OMAR".equals(textInBTag)) { // Check if the text is "SUHANIF BIN OMAR"
        	selectedStaff.click();// If the text matches, click on the element
        	Thread.sleep(1000);
           
        } else {
            System.out.println("Text does not match. Expected: SUHANIF BIN OMAR, Actual: " + textInBTag); // If the text doesn't match, handle accordingly (e.g., throw an exception)
        }
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div/div/div[2]/div/div[14]/div/button[2]")).click(); //Click the "Save" button after retrieving the staff info
	    Thread.sleep(1000);
	    
		// Step 9: Click on the "Save & Submit" button to save the Authorized Receipting Application
	    System.out.println("Step 9"); 
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[6]/div/div/button[2]")).click();
	    
	    
	    // Step 10: Click on the "Ok" button in the confirmation message
	    System.out.println("Step 10"); 
	    WebElement saveAndSubmitMessages = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[19]/div/div/div[2]"))); 
	    driver.findElement(By.xpath("/html/body/div[19]/div/div/div[3]/button[2]")).click(); //click the OK button for second confirmation message
	    Thread.sleep(1000);
	    		
	    		
	    // Step 11: Click on the "Ok" button and check the status of the saved and submitted application on the “Authorized Receipting Application” table 
	    System.out.println("Step 11"); 
	    driver.findElement(By.xpath("/html/body/div[19]/div/div/div[3]/button")).click();
	    System.out.println("Step 11"); 
	    Thread.sleep(1000);
		    
		
	    //Verify the newly added application display on the “Authorized Receipting Application” table as a “Entry”.
	    boolean isTitleMatching = wait.until(ExpectedConditions.titleIs("Account Receivable / Authorized Receipting"));
  	    if (isTitleMatching) { // Check whether the system redirect user to Authorized Receipting page
            System.out.println("The title matches: Authorized Receipting");
            WebElement statusColumn = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[3]/table/tbody/tr[1]/td[8]")); // Locate the "Status" column in the table row
            assertTrue("The value in the 'Status' column is not as expected", statusColumn.getText().equals("Entry")); // Verify that the value is "Entry"
            
            System.out.println("tc9_SaveAndSubmitAuthorizedReceiptingApplication: Completed");
    	    System.out.println(" ");
    	    
        } else {
            System.out.println("The title does not match: " + driver.getTitle());
        }
    }// end of test case 9
    

    @Test
    public void tc10_BackArrow() throws InterruptedException {

        // Get the initial URL of “Authorized Receipting” page before interacting with the page
        String initialURL = driver.getCurrentUrl();
        System.out.println("Current page URL is " + initialURL);

     	// Step 7: Click the “+New” button to go to “Authorized Receipting Form” page
    	System.out.println("Step 7"); 
	    WebElement button = driver.findElement(By.cssSelector("a.btn.btn-primary.ml-1"));  // Locate and click on a button using JavaScriptExecutor
	    JavascriptExecutor executor = (JavascriptExecutor) driver;
	    executor.executeScript("arguments[0].click();", button);
	   	
	    // Step 8: Click the back arrow icon on the “Authorized Receipting Form” page
	    System.out.println("Step 8"); 
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Set up a WebDriverWait instance to wait for the breadcrumbs div to be visible
        WebElement breadcrumbsDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("breadcrumbs"))); // Wait until the breadcrumbs div is visible
        WebElement backArrow = breadcrumbsDiv.findElement(By.tagName("back")); // Locate the back arrow icon within the breadcrumbs div
        backArrow.click(); //Click the back arrow icon

        // Verify that the URL is back to the initial URL “Authorized Receipting” page after navigating back
        String currentURL = driver.getCurrentUrl();
        Thread.sleep(1000);
        boolean isBackButtonVerified = (driver.getCurrentUrl().equals(initialURL));
        assertTrue("Error - Back button not functioning properly", isBackButtonVerified);
        
        System.out.println("tc10_BackArrow: Completed");
	    System.out.println(" ");
       
    }// end of test case 10
    
   
    @After
	public void afterTest() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
    
}