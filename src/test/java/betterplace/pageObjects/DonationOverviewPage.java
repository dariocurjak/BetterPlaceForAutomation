package betterplace.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import betterplace.utilities.CommonMethods;
import betterplace.utilities.ReadTestData;
import testNG_Engine.DriverFactory;

public class DonationOverviewPage extends DriverFactory {

	CommonMethods com;
	ReadTestData readTestData;
	String title = "Unterstütze Skateistan - Sport & Bildung für Kinder";
	
	public DonationOverviewPage() {
		PageFactory.initElements(driver, this);
		com = new CommonMethods();
		readTestData = new ReadTestData();
	}
	
	
	/* -------------------------------------------------------------------------------- */
	//Page elements
	
	@FindBy(xpath = "//h2[@class='m-0 text-3xl']")
	private WebElement pageHeader;
	
	@FindBy(xpath = "(//div[@class='project-context'])[1]")
	private WebElement pageInfo;
	
	@FindBy(className = "ytp-error")
	private WebElement videoFrame;
	
	@FindBy(xpath = "(//a[contains(text(),'Jetzt spenden')])[1]")
	private WebElement button_JetztSpenden;
	
	@FindBy(xpath = "//button[.='Projekt teilen']")
	private WebElement button_ProjektTeilen;
	
	@FindBy(xpath = "//a[contains(.,'Über uns')]")
	private WebElement link_AboutUs;
	
	
	/* -------------------------------------------------------------------------------- */
	//Methods
	
	/**
	 * 
	 * 
	 * 
	 * */
	public void checkPageUI() {
		
		String pageTitle = com.getTitle();
		if (pageTitle.contains(title)) {
			
			/*===============================================================================*/
			
			logger.assertLog(true, "Page title matches with expected title: "+title);
			System.out.println("| PASS | Page title matches with expected title: "+title);
			
			/*===============================================================================*/
			
			com.isElementPresent(pageHeader, "Page Header");
			com.isElementPresent(link_AboutUs, "Link - About Us - Über uns");
			com.isElementPresent(videoFrame, "Video Frame");
			com.isElementPresent(button_JetztSpenden, "Button - Jetzt spenden");
			com.isElementPresent(button_ProjektTeilen, "Button - Projekt Teilen");
			
			
			/*===============================================================================*/
			
			String pageHeaderText = com.getText(pageInfo);
			
			/*===============================================================================*/
		
			com.verifyPageText(pageHeaderText, readTestData.getDonationInfoText());
			
			/*===============================================================================*/
		
		}
		
	}
	
	/* -------------------------------------------------------------------------------- */
	/* -------------------------------------------------------------------------------- */
	/* -------------------------------------------------------------------------------- */
	
}
