import org.junit.runners.MethodSorters;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FilenameFilter;
import java.time.Duration;
import org.openqa.selenium.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClassBTest {

	WebDriver driver;
	WebDriverWait wait;

	@Before
	public void beforeTest()
	{
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","/Users/gohkoonloong/Downloads/chromedriver-mac-x64/chromedriver");

		System.out.println("Before test");
		// Setup driver
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		// Wait for browser open
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

		// Maximize the browser window
		driver.manage().window().maximize();

		// Perform Login steps
		// Step 1: Open the FIMS_Clone login page
		driver.get("https://fimsclone.kerisi.my/login.php?a=NzM2NjI5");

		// Step 2: Find element for user name and password
		WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"userID\"]"));
		WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"userPassword\"]"));
		WebElement signInButton = driver.findElement(By.xpath("//*[@id=\"login\"]"));

		// 2.1 - Input user name and password
		usernameInput.sendKeys("entry2");
		passwordInput.sendKeys("qwertyuiop");
		// 2.2 - Click sign in button
		signInButton.click();

		// Step 3: Click on Account Receivable menu
		WebElement accountReceivableLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1024\"]"));
		// 1024 - Account receivable | 1041 - Credit Note | 1782 - Credit Note Form

		wait.until(ExpectedConditions.elementToBeClickable(accountReceivableLink)).click();
	}

	@Test
	public void FIMS_11() throws InterruptedException
	{
		System.out.println("Test1 - Credit Note Page Link");
		WebElement CreditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(CreditNoteLink)).click();

		Thread.sleep(2000);
	}


	@Test
	public void FIMS_12() throws InterruptedException
	{

		System.out.println("Test2 - Credit Note Form Page Link");
		WebElement CreditNoteFormLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1782\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(CreditNoteFormLink)).click();

		Thread.sleep(2000);
	}

	@Test
	public void FIMS_13() throws InterruptedException
	{

		String downloadDirectory = "C:\\Users\\mizane\\Downloads\\DownloadTest";
		// Open the credit note link
		System.out.println("Test3 - Credit Note Download CN");
		WebElement CreditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(CreditNoteLink)).click();

		Thread.sleep(2000);

		//Select any check box for users
		WebElement actionClickSelect = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList\"]/tbody/tr[3]/td[14]/div/label"));
		actionClickSelect.click();

		Thread.sleep(2000);

		//Press download button after a button has been selected
		WebElement downloadClickCN = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_container\"]/div[3]/div[1]"));
		downloadClickCN.click();
		Thread.sleep(2000);

		//Download the file locally
		//Check if any downloaded file with the ".pdf" extension exists
		File[] downloadedFiles = new File(downloadDirectory).listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".pdf");
			}
		});

		Assert.assertTrue("No downloaded files with the '.pdf' extension found.", downloadedFiles != null);

		if (downloadedFiles != null ) {
			System.out.println("Downloaded files with the '.pdf' extension found.");
		}

		Thread.sleep(2000);
	}

	@Test
	public void FIMS_14() throws InterruptedException
	{

		String downloadDirectory = "C:\\Users\\mizane\\Downloads\\DownloadTest";
		// Open the credit note link
		System.out.println("Test4 - Credit Note Download Document");
		WebElement CreditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(CreditNoteLink)).click();

		Thread.sleep(2000);

		//Select any check box for users
		WebElement actionClickSelect = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList\"]/tbody/tr[3]/td[14]/div/label"));
		actionClickSelect.click();

		Thread.sleep(2000);

		//Press download button after a button has been selected
		WebElement downloadClickDownloadDocument = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_container\"]/div[3]/div[2]"));
		downloadClickDownloadDocument.click();
		Thread.sleep(2000);

		//Download the file locally
		//Check if any downloaded file with the ".pdf" extension exists
		File[] downloadedFiles = new File(downloadDirectory).listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".pdf");
			}
		});

		Assert.assertTrue("No downloaded files with the '.pdf' extension found.", downloadedFiles != null);

		if (downloadedFiles != null ) {
			System.out.println("Downloaded files with the '.pdf' extension found.");
		}

		Thread.sleep(2000);
	}

	@Test
	public void FIMS_15() throws InterruptedException
	{
		System.out.println("Test5 - Credit Note Form Page Link");

		WebElement CreditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(CreditNoteLink)).click();

		Thread.sleep(2000);

		WebElement newCreditForm = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_container\"]/div[3]/a"));
		wait.until(ExpectedConditions.elementToBeClickable(newCreditForm)).click();

		Thread.sleep(2000);
	}

	@Test
	public void FIMS_16() throws InterruptedException
	{
		System.out.println("Test6 - Credit Note Form Page Link");

		WebElement CreditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(CreditNoteLink)).click();

		Thread.sleep(2000);
		//Next page button
		WebElement NextArrowButtonPage = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_next\"]/a"));
		wait.until(ExpectedConditions.elementToBeClickable(NextArrowButtonPage)).click();
		Thread.sleep(2000);
		//Previous page button
		WebElement PreviousArrowButtonPage = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_previous\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(PreviousArrowButtonPage)).click();
		Thread.sleep(2000);
		//Page number 5
		WebElement NumberButtonPage = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_paginate\"]/ul/li[6]/a"));
		wait.until(ExpectedConditions.elementToBeClickable(NumberButtonPage)).click();
		Thread.sleep(2000);


	}

	@Test
	public void FIMS_17() throws InterruptedException
	{
		System.out.println("Test7 - Search existing terms");

		WebElement CreditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(CreditNoteLink)).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_filter\"]/label/input")).sendKeys("fazilah");
		Thread.sleep(2000);

		String input = driver.findElement(By.xpath("//input[@type='search']")).getAttribute("value");
		assertTrue(input.equals("fazilah"));
		System.out.println("fazilah is entered");

		// Verify if the expected data is shown in the search result information
		WebElement dataShow = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_info\"]"));
		String actualText = dataShow.getText();
		String expectedText = "2 records";

		Assert.assertEquals("Search data is not shown", expectedText, actualText);
		System.out.println("Search data is found");

	}

	@Test
	public void FIMS_18() throws InterruptedException
	{
		System.out.println("Test8 - Search non-existing terms");

		WebElement CreditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(CreditNoteLink)).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_filter\"]/label/input")).sendKeys("navia");
		Thread.sleep(2000);

		String input = driver.findElement(By.xpath("//input[@type='search']")).getAttribute("value");
		assertTrue(input.equals("navia"));
		System.out.println("navia is entered");

		// Verify if the expected data is shown in the search result information
		WebElement dataShow = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_info\"]"));
		String actualText = dataShow.getText();
		String expectedText = "";

		Assert.assertEquals("Search data is not shown", expectedText, actualText);
		System.out.println("Search data does not exist in data");

	}
	@Test
	public void FIMS_19() throws InterruptedException
	{
		System.out.println("Test9 - view button");

		WebElement CreditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(CreditNoteLink)).click();
		Thread.sleep(2000);

		WebElement viewButton = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList\"]/tbody/tr[1]/td[13]/a[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(viewButton)).click();

	}

	@Test
	public void FIMS_20() throws InterruptedException
	{
		System.out.println("Test9 - view button");

		WebElement CreditNoteLink = driver.findElement(By.xpath("//*[@id=\"menu_id_1041\"]"));
		wait.until(ExpectedConditions.elementToBeClickable(CreditNoteLink)).click();
		Thread.sleep(2000);

		WebElement newCreditForm = driver.findElement(By.xpath("//*[@id=\"DtArCreditNoteList_container\"]/div[3]/a"));
		wait.until(ExpectedConditions.elementToBeClickable(newCreditForm)).click();
		Thread.sleep(2000);

		WebElement debtorType = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[3]/span/span[2]/span"));
		wait.until(ExpectedConditions.elementToBeClickable(debtorType)).click();
		Thread.sleep(2000);

		WebElement selectiona = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]"));
		selectiona.click();
		Thread.sleep(2000);

		WebElement debtorName = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[4]/span/span[2]/span"));
		wait.until(ExpectedConditions.elementToBeClickable(debtorName)).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("1");
		Thread.sleep(2000);

		driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li[1]")).click();
		Thread.sleep(2000);

		driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[9]/textarea")).sendKeys("1");
		Thread.sleep(2000);

		driver.findElement(By.xpath("/html/body/div[4]/form/div/div[7]/div/div/button[2]")).click();
		Thread.sleep(2000);

	}


	@After
	public void closeDriver(){
		driver.close();
	}

}