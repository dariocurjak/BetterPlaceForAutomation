package betterplace.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.Assertion;
import com.aventstack.extentreports.util.Assert;

import betterplace.utilities.CommonMethods;
import betterplace.utilities.ReadTestData;
import testNG_Engine.DriverFactory;

public class DonationFormPage extends DriverFactory{
	
	CommonMethods com;
	ReadTestData readTestData;
	static String title = "Deine Spende an „Unterstütze Skateistan - Sport & Bildung für Kinder";
	
	public DonationFormPage() {
		PageFactory.initElements(driver, this);
		 com = new CommonMethods();
		 readTestData = new ReadTestData();
	}
	
	
	/* -------------------------------------------------------------------------------- */
	//Page elements
	
	@FindBy(className = "header-logo")
	private WebElement donationPageHeaderLogo;
	
	@FindBy(xpath = "//a[contains(.,'Sport & Bildung')]")
	private WebElement link_toDonationOverviewPage;
	
	@FindBy(xpath = "(//input[@class='form-control'])[1]")
	private WebElement inputText_Amount;
	
	@FindBy(id = "single")
	private WebElement radioButton_Einmalig;
	
	@FindBy(id = "monthly")
	private WebElement radioButton_Monatlich;
	
	@FindBy(id = "yearly")
	private WebElement radioButton_Jahrlich;
	
	@FindBy(id = "paypal")
	private WebElement radioButton_PAYPAL;
	
	@FindBy(xpath = "//label[@for='stripe_sepa_debit']")
	private WebElement radioButton_SEPA;
	
	@FindBy(id = "stripe_cc")
	private WebElement radioButton_KREDITKARTE;
	
	@FindBy(id = "paydirekt")
	private WebElement radioButton_PayDirect;
	
	@FindBy(id = "first_name")
	private WebElement inputText_FirstName;
	
	@FindBy(id = "last_name")
	private WebElement inputText_LastName;
	
	@FindBy(id = "email")
	private WebElement inputText_Email;
	
	@FindBy(xpath = "//iframe[contains(@name,'privateStripeFrame')]")
	private WebElement iframe_IBAN;
	
	@FindBy(xpath = "//input[@aria-label='IBAN']")
	private WebElement inputText_IBAN;
	
	@FindBy(xpath = "//button[contains(@class,'submit-donation')]")
	private WebElement button_SubmitDonation;
	
	@FindBy(xpath = "//button[contains(text(),'Nein, danke')]")
	private WebElement button_Cookies_NeinDanke;
	
	/* -------------------------------------------------------------------------------- */
	//Methods

	public void verifyPageUI() {
		
		String pageTitle = com.getTitle();
		if (pageTitle.contentEquals(title)) {
			/*===============================================================================*/
			
			logger.assertLog(true, "Page title matches with expected title: "+title);
			System.out.println("| PASS | Page title matches with expected title: "+title);
			
			/*===============================================================================*/
			
			com.isElementPresent(donationPageHeaderLogo, "Donation Page -> Header Logo");
			com.isElementPresent(link_toDonationOverviewPage, "Link to -> Donation Overview Page");
			com.isElementPresent(inputText_Amount, "Input -> Amount");
			com.isElementPresent(link_toDonationOverviewPage, "Button -> Create your Daily Todo list");
			
			com.isElementPresent(radioButton_Einmalig, "Radio Button -> Einmalig");
			com.isElementPresent(radioButton_Monatlich, "Radio Button -> Monatlich");
			com.isElementPresent(radioButton_Jahrlich, "Radio Button -> Jahrlich");
			
			com.isElementPresent(radioButton_PAYPAL, "Radio Button -> PAYPAL");
			com.isElementPresent(radioButton_SEPA, "Radio Button -> SEPA");
			com.isElementPresent(radioButton_KREDITKARTE, "Radio Button -> KREDITKARTE");
			com.isElementPresent(radioButton_PayDirect, "Radio Button -> Pay Direct");
			
			com.isElementPresent(inputText_FirstName, "Input Text -> First Name");
			com.isElementPresent(inputText_LastName, "Input Text -> Last Name");
			com.isElementPresent(inputText_Email, "Input Text -> Email");
			
			
			/*===============================================================================*/

		
			/*===============================================================================*/
			
			
			/*===============================================================================*/
			
		} else {
		
			logger.error("Page title not as expected: "+title);
			System.out.println("| FAIL | Page title not as expected:: "+title);
			
		}
	}
	
	/* --------------------------------------------------------------------------------------------------------------------- */
	
	public void fillDonationForm() {
	
		String pageTitle = com.getTitle();
		if (pageTitle.contains(title)) {
			/*===============================================================================*/
			try {
			logger.assertLog(true, "Page title matches with expected title: "+title);
			System.out.println("| PASS | Page title matches with expected title: "+title);
			
			/*===============================================================================*/
			
			com.click(button_Cookies_NeinDanke, "Button - Cookies - Nein, Danke");
			
			/*===============================================================================*/
			//Filling in data...
			inputText_Amount.sendKeys(Keys.BACK_SPACE);
			//inputText_Amount.sendKeys(Keys.BACK_SPACE);
			//com.sendKeys(inputText_Amount, readTestData.getAmount());
			
			com.click(radioButton_SEPA, "Radio Button - SEPA");
			
			com.sendKeys(inputText_FirstName, readTestData.getFirstName());
			com.sendKeys(inputText_LastName, readTestData.getLastName());
			com.sendKeys(inputText_Email, readTestData.getEmail());
			
			
			driver.switchTo().frame(iframe_IBAN);
			com.sendKeys(inputText_IBAN, readTestData.getIBAN());
			driver.switchTo().parentFrame();
			/*===============================================================================*/
			//Submitting donation...
			
			com.click(button_SubmitDonation, "Button - Submit Donation");

			com.wait(5);

			if (com.getTitle().contains(readTestData.getDankePageTitle())) {
				System.out.println("| PASS | Page title matches with expected title: "+readTestData.getDankePageTitle());
			} else {
				System.out.println("| FAIL | Page title not as expected:: "+com.getTitle());
			}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	/* --------------------------------------------------------------------------------------------------------------------- */
	public void navigateTo_DonationOverviewPage() {
		String pageTitle = com.getTitle();
		if (pageTitle.contentEquals(title)) {
			com.click(link_toDonationOverviewPage, "Link To - Donation Overview Page");
		}
	}
	/* --------------------------------------------------------------------------------------------------------------------- */
		
		
}
