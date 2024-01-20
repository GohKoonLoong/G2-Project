package Fufu;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import java.time.Duration;
import org.openqa.selenium.*;

public class ClassBTest {
	
    WebDriver driver;
	WebDriverWait wait;
	
	@Before
	public void beforeTest() throws InterruptedException
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","C:\\selenium webdriver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		System.out.println("Before test");
		
		// Setup driver
		driver = new ChromeDriver();

	    // Maximize the browser window
	    driver.manage().window().maximize();
	    
	    // Wait for browser open
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		   
	    // Perform Login steps
	    // Step 1: Open the FIMS_Clone login page
	    driver.get("https://fimsclone.kerisi.my/login.php?a=NzM2NjI5");
        Thread.sleep(2000);
	    
	    // Step 2: Enter the user name and password 
	    driver.findElement(By.xpath("//*[@id=\"userID\"]")).sendKeys("ENTRY2");
        Thread.sleep(2000);
        
	    driver.findElement(By.xpath("//*[@id=\"userPassword\"]")).sendKeys("qwertyuiop");
        Thread.sleep(2000);
        
        // Step 3: Press the Login button
	    driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
        Thread.sleep(2000);
		
		// Step 4: Click on Account Receivable menu
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[4]/a")).click();	
        Thread.sleep(2000);
	}

	@Test
	public void FIMS_11() throws InterruptedException
	{
        System.out.println("Test1 - Credit Note Page Link");

        // Click on credit note in menu
        WebElement creditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
        creditNoteLink.click();
        Thread.sleep(2000);

        // Re-locate the element after the page refresh
        creditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));

        // Assert that the Credit Note Link is click able
        Assert.assertTrue(creditNoteLink.isEnabled());
        Thread.sleep(8000);
	}
	
	@Test
	public void FIMS_12() throws InterruptedException
	{		
		System.out.println("Test2 - Credit Note Form Page Link");
		
		//Click on credit note form in menu
		WebElement creditNoteFormLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1782\"]"));
		creditNoteFormLink.click();
        Thread.sleep(2000);
        
        // Re-locate the element after the page refresh
        creditNoteFormLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1782\"]"));
        
        //Assert that the Credit Note Form Link is click able
        Assert.assertTrue(creditNoteFormLink.isEnabled());
        Thread.sleep(8000);
	}
	
	@Test
	public void FIMS_13() throws InterruptedException
	{
		System.out.println("Test3 - Credit Note Download CN");
		
		// Capture the current URL before clicking on the "Download" button
        String currentUrlBeforeDownload = driver.getCurrentUrl();
        
		//Click on credit note
		WebElement creditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
        creditNoteLink.click();
        Thread.sleep(2000);
		
        //Select any check box for users
		WebElement actionClickSelect = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList\"]/tbody/tr[3]/td[14]/div/label"));
		actionClickSelect.click();
		
        Thread.sleep(2000);
        
        //Press download button after a button has been selected
		WebElement downloadClickCN = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_container\"]/div[3]/div[1]"));
		downloadClickCN.click();
        Thread.sleep(2000);
        
        //Capture the URL after clicking on the "Download" button
        String currentUrlAfterDownload = driver.getCurrentUrl();
        
        //Assert that if URL not same, it mean the PDF file URL has been opened
        Assert.assertNotEquals(currentUrlBeforeDownload, currentUrlAfterDownload,
                "Navigation to PDF not detected after clicking the Download button"); 
        Thread.sleep(8000);       
	}
	
	@Test
	public void FIMS_14() throws InterruptedException
	{
		System.out.println("Test4 - Credit Note Download Document");

		// Capture the current URL before clicking on the "Download" button
        String currentUrlBeforeDownload = driver.getCurrentUrl();
        
		//Click on credit note
		WebElement creditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
        creditNoteLink.click();
        Thread.sleep(2000);
		
        //Select any check box for users
		WebElement actionClickSelect = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList\"]/tbody/tr[3]/td[14]/div/label"));
		actionClickSelect.click();
		
        Thread.sleep(2000);
        
        //Press download button after a button has been selected
		WebElement downloadClickDownloadDocument = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_container\"]/div[3]/div[2]"));
		downloadClickDownloadDocument.click();
        Thread.sleep(2000);
        
        //Capture the URL after clicking on the "Download" button
        String currentUrlAfterDownload = driver.getCurrentUrl();
        
        //Assert that if URL not same, it mean the PDF file URL has been opened
        Assert.assertNotEquals(currentUrlBeforeDownload, currentUrlAfterDownload,
                "Navigation to PDF not detected after clicking the Download button");
        Thread.sleep(8000);
	}
	
	@Test
	public void FIMS_15() throws InterruptedException
	{
		System.out.println("Test5 - Credit Note Form Page Link");

        String currentUrlBeforeNew = driver.getCurrentUrl();
		//Click on credit note
		WebElement creditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
	    creditNoteLink.click();
	    Thread.sleep(2000);

	    //Click on "New" button
		WebElement newCreditForm = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[3]/a"));
		newCreditForm.click();
	    Thread.sleep(2000);
	    
	    //Capture the URL after clicking on the "New" button
        String currentUrlAfterNew = driver.getCurrentUrl();
        
        //Assert that if URL not same, it mean the New button navigate users into credit note form
        Assert.assertNotEquals(currentUrlBeforeNew, currentUrlAfterNew,
                "Navigation to Credit Note Form not successfull");
        Thread.sleep(8000);
	}

	@Test
	public void FIMS_16() throws InterruptedException
	{
		System.out.println("Test6 - Credit Note Form Page Link");
	
		//Click on credit note
		WebElement creditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
	    creditNoteLink.click();
	    Thread.sleep(2000);
	
	    //Next page button
		WebElement nextArrowButtonPage = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[5]/ul/li[8]/a"));
		nextArrowButtonPage.click();
	    
	    //Assert that the next page button is click able
	    Assert.assertTrue("Next page button is not clickable", nextArrowButtonPage.isEnabled());
	    Thread.sleep(5000);
	    
		//Previous page button
	    WebElement previousArrowButtonPage = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[5]/ul/li[1]/a"));
		previousArrowButtonPage.click();
	    
	    //Assert that the previous page button is click able
	    Assert.assertTrue("Previous page button is not clickable", previousArrowButtonPage.isEnabled());
	    Thread.sleep(5000);
	
	    //Page number 5
	    WebElement numberButtonPage = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[5]/ul/li[6]/a"));
	    numberButtonPage.click();
	    
	    //Assert that the specific page button is click able
	    Assert.assertTrue("Page number 5 button is not clickable", numberButtonPage.isEnabled());
	    Thread.sleep(5000); 
	}
	
	@Test
	public void FIMS_17() throws InterruptedException
	{
		System.out.println("Test7 - Search existing terms");

		//Click on credit note
		WebElement creditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
	    creditNoteLink.click();
	    Thread.sleep(2000);

	    //Input "fazilah" value into search box
		driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_filter\"]/label/input")).sendKeys("fazilah");
	    Thread.sleep(2000);
	    
	    //Get the value from search box
	    String input = driver.findElement(By.xpath("//input[@type='search']")).getAttribute("value");
		assertTrue(input.equals("fazilah"));
		System.out.println("fazilah is entered");
				
		//Verify if the expected data is shown in the search result information
        WebElement dataShow = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_info\"]"));
        String actualText = dataShow.getText();
        String expectedText = "2 records";
        
        //Assertion if data is found
        Assert.assertEquals("Search data is not shown", expectedText, actualText);
        System.out.println("Search data is found");    
        Thread.sleep(8000);            
	}
	
	@Test
	public void FIMS_18() throws InterruptedException
	{
		System.out.println("Test8 - Search non-existing terms");

		//Click on credit note
		WebElement creditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
	    creditNoteLink.click();
	    Thread.sleep(2000);
	    
	    //Input "navia" value into search box
		driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_filter\"]/label/input")).sendKeys("navia");
	    Thread.sleep(2000);
	    
	    //Get the value from search box
	    String input = driver.findElement(By.xpath("//input[@type='search']")).getAttribute("value");
		assertTrue(input.equals("navia"));
		System.out.println("navia is entered");
				
		//Verify if the expected data is shown in the search result information
        WebElement dataShow = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_info\"]"));
        String actualText = dataShow.getText();
        String expectedText = "";
        
        //Assertion if data is not found
        Assert.assertEquals("Search data is not shown", expectedText, actualText);
        System.out.println("Search data does not exist in data");  
        Thread.sleep(8000);      
	}
	
	@Test
	public void FIMS_19() throws InterruptedException
	{
		System.out.println("Test9 - view button");
	    //Capture the URL after clicking on the "Download" button
	    String currentUrlBeforeView = driver.getCurrentUrl();
	    
		//Click on credit note
		WebElement creditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
	    creditNoteLink.click();
	    Thread.sleep(2000);
	    	    
	    WebElement viewButton = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList\"]/tbody/tr[1]/td[13]/a[1]"));
	    viewButton.click();
        Thread.sleep(8000);
	  
        //Capture the URL after clicking on the "view" button
        String currentUrlAfterView = driver.getCurrentUrl();
        
        //Assert that if URL not same, it mean the PDF file URL has been opened
        Assert.assertNotEquals(currentUrlBeforeView, currentUrlAfterView,
                "Navigation to Credit Note Form not successfull");
        Thread.sleep(8000);
	}

	@Test
	public void FIMS_20() throws InterruptedException
	{
		System.out.println("Test10 - save button");

		//Click on credit note
		WebElement creditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
	    creditNoteLink.click();
	    Thread.sleep(2000);
	    
	    //Click on "New" button
	    WebElement newCreditForm = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_container\"]/div[3]/a"));
	    newCreditForm.click();
	    Thread.sleep(2000);
	    
	    //Click drop down for debtor type
	    WebElement debtorType = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[3]/span/span[2]/span"));
	    debtorType.click();		
	    Thread.sleep(4000);
	    
	    //Select an option from the debtor type drop down
	    WebElement selectiona = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
		selectiona.click();		
	    Thread.sleep(4000);
	    
	    //Click drop down for debtor name
	    WebElement debtorName = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[4]/span/span[2]/span"));
	    debtorName.click();		
	    Thread.sleep(4000);
	    
	    //Input value "1" into debtor name text input
	    driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("1");
	    Thread.sleep(4000);
	    
	    //Select an option from the debtor name drop down
	    driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]")).click();
	    Thread.sleep(4000);
	    
	    //Input value "1" into credit note description
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[9]/textarea")).sendKeys("1");
	    Thread.sleep(4000);
	    	    
	    //Click on "Save" button.
	    driver.findElement(By.xpath("/html/body/div[4]/form/div/div[7]/div/div/button[2]")).click();
	    Thread.sleep(4000);
	  
	}

    @After
    public void closeDriver(){
        driver.close();
    }
	
}