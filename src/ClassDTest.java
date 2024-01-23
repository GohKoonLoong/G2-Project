package testScript;

import org.junit.*;
//import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import java.io.File;
//import java.io.FilenameFilter;
import java.time.Duration;

public class ClassDTest {
	WebDriver driver;
	WebDriverWait wait;
	@Before
	public void beforeTest() throws InterruptedException{
		System.setProperty("webdriver.chrome.driver","C:\\selenium webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		  driver.manage().window().maximize();
		  
		   //step 1
	        // Navigate to the FIMS login page
	        driver.get("https://fimsclone.kerisi.my/login.php?a=NTg2MTEw");

	        Thread.sleep(6000);
	        // Locate username, password, and login button elements
	        WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"userID\"]"));
	        usernameInput.sendKeys("Entry3");
	        Thread.sleep(2000);
	        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"userPassword\"]"));
	        passwordInput.sendKeys("qwertyuiop");
	        Thread.sleep(2000);
	        WebElement signInButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));	        
	        signInButton.click();
	        Thread.sleep(2000);
	        
	     // Account Receivable and Receipt links
	        WebElement accountReceivableLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1024\"]"));
	        Thread.sleep(2000);
	        WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1044\"]"));
	        Thread.sleep(2000);
	        
	        //Step 3
	        // Click on the links in sequence
	        accountReceivableLink.click();
	        wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();
	        
	}
	

	@Test
	public void FIMS_31() throws InterruptedException {
		//WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
       // wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();
        
		//wait 2 second
        Thread.sleep(2000);
        
        //enter to list of receipt
		WebElement receiptListLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptListLink)).click();
        
		//wait 2 second
        Thread.sleep(5000);


        // Locate the search input field and enter the search data
        WebElement searchData = driver.findElement(By.xpath("//*[@id=\"dt_listing_filter\"]/label/div/div/input"));
        searchData.sendKeys("AMIN");
        Thread.sleep(3000);

        // Verify if the expected data is shown in the search result information
        WebElement dataShow = driver.findElement(By.xpath("//*[@id=\"dt_listing_info\"]"));
        String actualText = dataShow.getText();
        String expectedText = "855 records";

        Assert.assertEquals("Search data is not shown", expectedText, actualText);
        System.out.println("Search data is found");
    }
		
	
	@Test
	public void FIMS_32()  throws InterruptedException{
		//Locate and click receipt Link
      //  WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
      //  wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();
        
		//wait 2 second
      //  Thread.sleep(2000);
        
      //enter to list of receipt
        WebElement receiptListLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptListLink)).click();

        Thread.sleep(5000);


        // Locate the search input field and enter the search data
        WebElement searchData = driver.findElement(By.xpath("//*[@id=\"dt_listing_filter\"]/label/div/div/input"));
        searchData.sendKeys("SISUKA");
        
        //wait 3 second
        Thread.sleep(3000);

        // Locate the information about the number of records shown
        WebElement dataShow = driver.findElement(By.xpath("//*[@id=\"dt_listing_info\"]"));

        // Verify if the expected data is not shown in the search result information
        String actualText = dataShow.getText();
        String expectedText = "";

        Assert.assertEquals("Search data is shown", expectedText, actualText);
        System.out.println("No record found");
		
	}
	
	
	@Test
	 public void FIMS_33() throws InterruptedException{

        //Locate and click receipt  Link
       // WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
       // wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();

        Thread.sleep(2000);
        
      //enter to list of receipt
        WebElement receiptlistLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptlistLink)).click();

        Thread.sleep(3000);
        
        // Locate the smart filter Button and click on it
        WebElement smartFilterButton = driver.findElement(By.xpath("//*[@id=\"dt_listing_filter\"]/label/div/div/div"));
        smartFilterButton.click();
        
        Thread.sleep(3000);
        
     // Verify if popUp box is display
        WebElement smartFilterBox = driver.findElement(By.xpath("//*[@id=\"dt_listing_filter\"]/label/div/div/div"));
        Assert.assertTrue("popUp box are not display", smartFilterBox.isDisplayed());
        System.out.println("popUp box is display");
        
        Thread.sleep(3000);
        
        // Close the pop-up message
        WebElement closePopUpBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dt_listingSmartFilter\"]/div/div/div[1]/button")));
        closePopUpBox.click();
	}
	
	@Test
	public void FIMS_34() throws InterruptedException{

	       //Locate and click receipt  Link
	    // WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
	    //  receiptLink.click();

	       //Thread.sleep (3000);
	       
	     //enter to list of receipt
	       WebElement receiptlistLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
	       wait.until(ExpectedConditions.elementToBeClickable(receiptlistLink)).click();
	       //receiptlistLink.click();

	       Thread.sleep(3000);
	     //open display dropDown button
	       WebElement openDisplayDropDown = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[1]/label/span/span[2]/span/span[1]"));
	       openDisplayDropDown.click();
	       
	     //*[@id="dt_listing_length"]/label/span/span[2]/span/span[2]
	       Thread.sleep(3000);
	       //user preference number
	       WebElement userPreference = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[4]"));
	       wait.until(ExpectedConditions.elementToBeClickable(userPreference)).click();
	      // userPreference.click();

	  
	       //String expectedUserPreferenceText = "125580 records"; 

	       // Assert that the user preference text is correct
	      // Assert.assertEquals("User preference is not correct", expectedUserPreferenceText, userPreference.getText());
	       //System.out.println("User preference is correct");

	       //Thread.sleep(2000);
	       
	       
		}
	
   @Test
    public void FIMS_35() throws InterruptedException {
    	String currentURLBeforePrint=driver.getCurrentUrl();
        // Locate and click the receipt Link
       // WebElement receiptLink = driver.findElement(By.xpath("//*[@id='list_menu_id_1044']"));
     //   wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();

        // Wait for 2 seconds
       // Thread.sleep(2000);

        // Locate and click the list of receipt Link
        WebElement receiptListLink = driver.findElement(By.xpath("//*[@id='list_menu_id_1590']"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptListLink)).click();

        // Wait for 2 seconds
        Thread.sleep(5000);

        // Locate the print Button and click on it
        WebElement printButton = driver.findElement(By.xpath("//*[@id='dt_listing']/tbody/tr[1]/td[11]/a[1]"));
        printButton.click();

        // Wait for 2 seconds
        Thread.sleep(2000);
    	String currentURLAfterPrint=driver.getCurrentUrl();

        // Verify if the print receipt is displayed
        Assert.assertNotEquals(currentURLBeforePrint, currentURLAfterPrint,
                "Navigation to print is not successfull");
        Thread.sleep(2000);
        
    }
    
	@Test
    public void FIMS_36() throws InterruptedException{
		
		// Capture the current URL before clicking on the "Download" button
        String currentURLBeforeDownload = driver.getCurrentUrl();
		
		 //Locate and click receipt  Link
	 //  WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
	 //  wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();
	   
       //wait 2 second
	// Thread.sleep(5000);
		 
        //Locate and click list of receipt Link
        WebElement receiptListLink =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"list_menu_id_1590\"]")));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"list_menu_id_1590\"]")).click();
        receiptListLink.click();
        
		//wait 2 second
        Thread.sleep(2000);

        //Select any check box for users
      		WebElement tickActionSelected = driver.findElement(By.xpath("//*[@id=\"dt_listing\"]/tbody/tr[3]/td[12]/div/label"));
      		tickActionSelected.click();
      		
              Thread.sleep(2000);
              
           // Locate the download Button and click on it
              WebElement dowloadSummaryReportButton = driver.findElement(By.xpath("//*[@id=\"cm_print\"]/div[1]"));
              dowloadSummaryReportButton.click();
              
              //Capture the URL after clicking on the "Download" button
              String currentUrlAfterDownloadReport = driver.getCurrentUrl();
              
              //Assert that if URL not same, it mean the PDF file URL has been opened
              Assert.assertNotEquals(currentURLBeforeDownload, currentUrlAfterDownloadReport,
                      "Navigation to PDF not detected after clicking the Download button"); 
             
              Thread.sleep(2000);      
      	 	}
		
	@Test
	public void FIMS_37() throws InterruptedException{
		try {
		WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();

       Thread.sleep(3000);

        // Locate the next Page Button and click on it
        WebElement nextPageButton = driver.findElement(By.xpath("//*[@id=\"dt_listing_next\"]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(nextPageButton)).click();												
      
        Thread.sleep(3000);
        
        WebElement previousPageButton = driver.findElement(By.xpath("//*[@id=\"dt_listing_previous\"]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(previousPageButton)).click();												
     
        Thread.sleep(3000);
        
        WebElement randomPageButton = driver.findElement(By.xpath("//*[@id=\"dt_listing_paginate\"]/ul/li[4]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(randomPageButton)).click();  
        Thread.sleep(2000);
		} catch (NoSuchElementException | TimeoutException e) {
	        // Handle the exception here
	        e.printStackTrace(); 
	        throw e;
	    }
		
	}
	
	 @Test
	    public void FIMS_38() throws InterruptedException{
			 
	        //Locate and click receipt Link
	        WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
	        wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();
	        
			//wait 2 second
	        Thread.sleep(5000);

	        // Locate the New Button and click on it
	        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"cm_print\"]/a/span"));
	       // newButton.click();
	        wait.until(ExpectedConditions.elementToBeClickable(newButton)).click();

	        // Verify if the entry form is displayed
	        WebElement entryForm = driver.findElement(By.xpath("//*[@id=\"cm_details\"]"));
	        Assert.assertTrue("Navigation unsuccessful", entryForm.isDisplayed());
	        System.out.println("Navigation successful");
	    }
        
	 @Test
	  public void FIMS_39() throws InterruptedException{
	        //Locate and click receipt Registry Link
	     WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
	     wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();

	        Thread.sleep(3000);

	        // Locate the New Button and click on it
	        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"cm_print\"]/a/span"));
	       // newButton.click();
		    wait.until(ExpectedConditions.elementToBeClickable(newButton)).click();
	        Thread.sleep(5000);

	        // Locate the back icon and click on it
	        WebElement backIcon = driver.findElement(By.xpath("//*[@id=\"breadcrumbs\"]/back"));
	        //backIcon.click();
		    wait.until(ExpectedConditions.elementToBeClickable(backIcon)).click();
	        
	        Thread.sleep(5000);

	        // Verify if the list of receipt is displayed after navigating back
	        WebElement listOfReceipt = driver.findElement(By.xpath("//*[@id=\"dt_listing_container\"]"));
	        Assert.assertTrue("Navigation unsuccessful", listOfReceipt.isDisplayed());
	        System.out.println("Navigation successful");
		}
		
		
	@Test
    public void FIMS_40() throws InterruptedException{
		 
		String currentURLBeforeView=driver.getCurrentUrl();
        //Locate and click receipt Link bad debt
        WebElement receiptBadDebtLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_2991\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptBadDebtLink)).click();
        
		//wait 2 second
       Thread.sleep(5000);

        // Locate the view Icon Button and click on it
        WebElement viewIconButton = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[4]/table/tbody/tr[1]/td[11]/a[2]"));
        //viewIconButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(viewIconButton)).click();
        Thread.sleep(2000);
        
      //Capture the URL after clicking on the "View" Icon
        String currentURLAfterView=driver.getCurrentUrl();

        // Verify if the print receipt is displayed
        Assert.assertNotEquals(currentURLBeforeView, currentURLAfterView,
                "Navigation to view is not successfull");
      //wait 2 second
        Thread.sleep(2000);
        
    }
	

	@After
	public void closeDriver(){
	     driver.close();
	    }
}