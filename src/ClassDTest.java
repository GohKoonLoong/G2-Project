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
	public void beforeTest(){
		System.setProperty("Webdriver.com.driver","C:\\selenium webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		  driver.manage().window().maximize();
		  
		   //step 1
	        // Navigate to the FIMS login page
	        driver.get("https://fimsclone.kerisi.my/login.php?a=NTg2MTEw");

	        // Locate username, password, and login button elements
	        WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"userID\"]"));
	        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"userPassword\"]"));
	        WebElement signInButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));
	        
	       //Step 2
	        //input username, password, login button
	        usernameInput.sendKeys("Entry3");
	        passwordInput.sendKeys("qwertyuiop");
	        signInButton.click();
	        
	     // Account Receivable and Receipt links
	        WebElement accountReceivableLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1024\"]"));
	        WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1044\"]"));
	        
	        //Step 3
	        // Click on the links in sequence
	        accountReceivableLink.click();
	        wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();
	        
	}
	

	@Test
	public void FIMS_31() throws InterruptedException {
		WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();
        
		//wait 2 second
        Thread.sleep(2000);
        
        //enter to list of receipt
		WebElement receiptListLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptListLink)).click();
        
		//wait 2 second
        Thread.sleep(2000);


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
        WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();
        
		//wait 2 second
        Thread.sleep(2000);
        
      //enter to list of receipt
        WebElement receiptListLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptListLink)).click();

        Thread.sleep(2000);


        // Locate the search input field and enter the search data
        WebElement searchData = driver.findElement(By.xpath("//*[@id=\"dt_listing_filter\"]/label/div/div/input"));
        searchData.sendKeys("SISUKA");
        
        //wait 3 second
        Thread.sleep(3000);

        // Locate the information about the number of records shown
        WebElement dataShow = driver.findElement(By.xpath("//*[@id=\"dt_listing_info\"]"));

        // Verify if the expected data is not shown in the search result information
        String actualText = dataShow.getText();
        String expectedText = "0";

        Assert.assertEquals("Search data is shown", expectedText, actualText);
        System.out.println("No record found");
		
	}
	
	@Test
	 public void FIMS_33() throws InterruptedException{

        //Locate and click receipt  Link
        WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();

        Thread.sleep(2000);
        
      //enter to list of receipt
        WebElement receiptlistLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptlistLink)).click();

        Thread.sleep(2000);
        
        // Locate the smart filter Button and click on it
        WebElement smartFilterButton = driver.findElement(By.xpath("//*[@id=\"dt_listing_filter\"]/label/div/div/div"));
        smartFilterButton.click();
  
        
     // Verify if popUp box is display
        WebElement smartFilterBox = driver.findElement(By.xpath("//*[@id=\"dt_listing_filter\"]/label/div/div/div"));
        Assert.assertTrue("popUp box are not display", smartFilterBox.isDisplayed());
        System.out.println("popUp box is display");
        Thread.sleep(2000);
        
        // Close the pop-up message
        WebElement closePopUpBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"dt_listingSmartFilter\"]/div/div/div[1]/button")));
        closePopUpBox.click();
	}
	
	
	
	@Test
	 public void FIMS_34() throws InterruptedException{

       //Locate and click receipt  Link
       WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
       wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();

       Thread.sleep(2000);
       
     //enter to list of receipt
       WebElement receiptlistLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
       wait.until(ExpectedConditions.elementToBeClickable(receiptlistLink)).click();

       Thread.sleep(2000);
     //open display dropDown button
       WebElement openDisplayDropDown = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[1]/label/span/span[2]/span/span[1]"));
       wait.until(ExpectedConditions.elementToBeClickable(openDisplayDropDown)).click();

       Thread.sleep(2000);
       //user preference number
       WebElement userPreference = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[4]"));
       wait.until(ExpectedConditions.elementToBeClickable(userPreference)).click();
       Assert.assertTrue("user preference is not correct", userPreference.isDisplayed());
       System.out.println("user preference is correct");

       Thread.sleep(2000);
       
       
	}
	
	@Test
    public void FIMS_35() throws InterruptedException{
		
		 //Locate and click receipt  Link
	   WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
	   wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();
	   
       //wait 2 second
	   Thread.sleep(2000);
		 
        //Locate and click list of receipt Link
        WebElement receiptListLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptListLink)).click();
        
		//wait 2 second
        Thread.sleep(2000);

        // Locate the print Button and click on it
        WebElement printButton = driver.findElement(By.xpath("//*[@id=\"dt_listing\"]/tbody/tr[1]/td[11]/a[1]"));
        printButton.click();
        
        //wait 2 second
        Thread.sleep(2000);

        // Verify if the print receipt is displayed
        WebElement printReceipt = driver.findElement(By.xpath("//*[@id=\\\"dt_listing\\\"]/tbody/tr[1]/td[11]/a[1]"));
        Assert.assertTrue("print receipt unsuccessful", printReceipt.isDisplayed());
        System.out.println("print receipt successful");
    }
	
	@Test
    public void FIMS_36() throws InterruptedException{
		
		// Capture the current URL before clicking on the "Download" button
        String currentUrlBeforeDownload = driver.getCurrentUrl();
		
		 //Locate and click receipt  Link
	  // WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1044\"]"));
	//   wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();
	   
       //wait 2 second
	 //  Thread.sleep(2000);
		 
        //Locate and click receipt Link
        WebElement receiptListLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptListLink)).click();
        
		//wait 2 second
        Thread.sleep(2000);

        //Select any check box for users
      		WebElement tickActionSelected = driver.findElement(By.xpath("//*[@id=\"dt_listing\"]/tbody/tr[3]/td[12]/div/label"));
      		tickActionSelected.click();
      		
              Thread.sleep(2000);
              
           // Locate the download Button and click on it
              WebElement dowloadSummaryReportButton = driver.findElement(By.xpath("//*[@id=\"cm_print\"]/div[1]"));
              dowloadSummaryReportButton.click();
              
              //wait 2 second
              Thread.sleep(2000);
             
        		WebElement confirmDownload = driver.findElement(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[3]/button"));
        		confirmDownload.click();
                Thread.sleep(2000);

              
              //Capture the URL after clicking on the "Download" button
              String currentUrlAfterDownload = driver.getCurrentUrl();
              
              //Assert that if URL not same, it mean the PDF file URL has been opened
              Assert.assertNotEquals(currentUrlBeforeDownload, currentUrlAfterDownload,
                      "Navigation to PDF not detected after clicking the Download button"); 
              Thread.sleep(8000);      
      	 	}
	
	@Test
	public void FIMS_37() throws InterruptedException{
		WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();

        Thread.sleep(2000);

        // Locate the next Page Button and click on it
        WebElement nextPageButton = driver.findElement(By.xpath("//*[@id=\"dt_listing_next\"]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(nextPageButton)).click();												
      //*[@id="dt_listing_next"]/a
        Thread.sleep(2000);
        
        WebElement previousPageButton = driver.findElement(By.xpath("//*[@id=\"dt_listing_previous\"]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(previousPageButton)).click();												
      //*[@id="dt_listing_previous"]/a
        Thread.sleep(2000);
        
        WebElement randomPageButton = driver.findElement(By.xpath("//*[@id=\"dt_listing_paginate\"]/ul/li[4]/a"));
        wait.until(ExpectedConditions.elementToBeClickable(randomPageButton)).click();  
        Thread.sleep(2000);
         
	}
	
	 @Test
	    public void FIMS_38() throws InterruptedException{
			 
	        //Locate and click receipt Link
	        WebElement receiptLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_1590\"]"));
	        wait.until(ExpectedConditions.elementToBeClickable(receiptLink)).click();
	        
			//wait 2 second
	        Thread.sleep(2000);

	        // Locate the New Button and click on it
	        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"cm_print\"]/a/span"));
	        newButton.click();

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

	        Thread.sleep(2000);

	        // Locate the New Button and click on it
	        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"cm_print\"]/a/span"));
	        newButton.click();
	        
	        Thread.sleep(2000);

	        // Locate the back icon and click on it
	        WebElement backIcon = driver.findElement(By.xpath("//*[@id=\"breadcrumbs\"]/back"));
	        backIcon.click();
	        
	        Thread.sleep(2000);

	        // Verify if the list of receipt is displayed after navigating back
	        WebElement listOfReceipt = driver.findElement(By.xpath("//*[@id=\"dt_listing_container\"]"));
	        Assert.assertTrue("Navigation unsuccessful", listOfReceipt.isDisplayed());
	        System.out.println("Navigation successful");
		}
	@Test
    public void FIMS_40() throws InterruptedException{
		 
        //Locate and click receipt Link bad debt
        WebElement receiptBadDebtLink = driver.findElement(By.xpath("//*[@id=\"list_menu_id_2991\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(receiptBadDebtLink)).click();
        
		//wait 2 second
        Thread.sleep(2000);

        // Locate the view Icon Button and click on it
        WebElement viewIconButton = driver.findElement(By.xpath("//*[@id=\"dt_listing\"]/tbody/tr[2]/td[11]/a[2]/i"));
        viewIconButton.click();

        // Verify if the view is displayed
        WebElement listOfBadReceipt = driver.findElement(By.xpath("//*[@id=\"dt_listing\"]/tbody/tr[2]/td[11]/a[2]/i"));
        Assert.assertTrue("View unsuccessful", listOfBadReceipt.isDisplayed());
        System.out.println("View successful");
      //wait 2 second
        Thread.sleep(2000);
        
    }
    
        
   
    
   
	
	
	
	
	@After
	    public void closeDriver(){
	        driver.close();
	    }
}
