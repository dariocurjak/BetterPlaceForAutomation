package betterplace.testCases;

import static org.junit.Assert.assertTrue;
import org.testng.annotations.Test;

import betterplace.pageObjects.DonationOverviewPage;
import betterplace.pageObjects.DonationFormPage;
import testNG_Engine.DriverFactory;

/**
 * 
 * Unit test for Better Place Web App.
 * 
 */

public class WebApplicationTests extends DriverFactory { 

	/* --------------------------------------------------------------------------------------------------------------------- */
    
    @Test (description = "T01 - Better Place: Donation Page UI")
    public void t01_DonationFormPageUI()
    {
        DonationFormPage wp = new DonationFormPage();
        wp.verifyPageUI();
    }
    
    /* --------------------------------------------------------------------------------------------------------------------- */
    
    @Test (description = "T02 - Better Place: Fill in Donation Form")
    public void t02_DonationFormFill()
    {
    	/*===============================================================================*/
    	
    	DonationFormPage wp = new DonationFormPage();

    	wp.fillDonationForm();
    	/*===============================================================================*/

    }
    
    /* --------------------------------------------------------------------------------------------------------------------- */
}
