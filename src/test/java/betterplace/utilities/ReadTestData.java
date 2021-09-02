package betterplace.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ReadTestData {
	
	Properties pro;
	
	public ReadTestData() {
		
		File src = new File("./TestData/testData");
		
		try {
			FileInputStream fis = new FileInputStream(src); 
			pro = new Properties();
			pro.load(fis);
		} catch(Exception e) {
			System.out.println("Exception is "+ e.getMessage());
		}
	}
	
	public String getAmount() {
		String amount = pro.getProperty("donationForm_Amount");
		return amount;
	}
	
	public String getFirstName() {
		String firstName = pro.getProperty("donationForm_FirstName");
		return firstName;
	}
	
	public String getLastName() {
		String lastName = pro.getProperty("donationForm_LastName");
		return lastName;
	}
	
	public String getEmail() {
		String email = pro.getProperty("donationForm_Email");
		return email;
	}

	public String getIBAN() {
		String iban = pro.getProperty("donationForm_IBAN");
		return iban;
	}
	
	public String getDonationInfoText() {
		String iban = pro.getProperty("donationForm_DonationInfo");
		return iban;
	}
	
	public String getDankePageTitle() {
		String title = pro.getProperty("thankYouPage_title");
		return title;
	}

}
