package betterplace.utilities;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import testNG_Engine.DriverFactory;


public class CommonMethods extends DriverFactory {
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	protected WebDriver driver;

	public boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();

		} catch (Exception e) {
			 System.out.println(e);
			return false;
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public boolean click(Object element, String desc) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			elem.click();
			bool = true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (!desc.equals("")) {
				if (bool) {
					System.out.println("| PASS | " + desc + " is clicked.");
				} else {
					System.out.println("| FAIL | " + desc + "' object is NOT clicked, due to some exception.");
				}
			}
		}
		return bool;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void verifyPageText(String textOnPage, String expectedText) {
		
		if(textOnPage.contentEquals(expectedText)) {
			org.testng.Assert.assertTrue(true, "Text displayed on page matches with expected text: " +expectedText);
			System.out.println("| PASS | Text displayed on page matches with expected text: "+expectedText);
		} else {
			System.out.println("| FAIL | Text displayed on page doesn't matches with expected text: "+expectedText);
			org.testng.Assert.fail("Text displayed on page : | "+textOnPage+" | doesn't match with the expected text...");
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	WebElement getWebElement(Object element) {

		WebElement elem = null;
		WebDriver driver = this.driver = super.getDriver();
		if (element instanceof By) {
			By byObj = (By) element;
			elem = driver.findElement(byObj);
		} else if (element instanceof WebElement) {
			elem = (WebElement) element;
		}
		return elem;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getAttribute(Object element, String name) {
		String val = null;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		if (name.toLowerCase().contains("innertext")) {
			val = getText(element);
		} else {
			try {
				WebElement elem = getWebElement(element);
				val = elem.getAttribute(name);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return val;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public boolean sendKeys(Object element, CharSequence... keysToSend) {
		return sendKeys("", element, keysToSend);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean sendKeys(String desc, Object element, CharSequence... keysToSend) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			elem.clear();
			elem.sendKeys(keysToSend);
			bool = true;
			

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (!desc.equals("")) {
				checkBlank(element, desc);
			}
		}
		return bool;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean checkBlank(Object element, String objName) {
		boolean bool = false;
		String val = "";
		try {
			WebElement elem = getWebElement(element);

			val = elem.getAttribute("value").trim();
			if (!"".equalsIgnoreCase(val)) {
				bool = true;
			}
	
		} catch (Exception e) {
			 System.out.println(e);
		} finally {
			if (bool) {
				System.out.println("| PASS |  Value: '" + val + "' is filled in " + objName);
			} else {
				System.out.println("| FAIL |  Value is Not filled in " + objName);
			}
		}
		return bool;
	}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	/**
	 * Get the visible (i.e. not hidden by CSS) innerText of this element,
	 * including sub-elements, without any leading or trailing whitespace.
	 * 
	 * @param element
	 *            The By/WebElement Object
	 * @return The innerText of this element.
	 */
	public String getText(Object element) {
		String str = "";
		try {
			WebElement elem = getWebElement(element);
			str = elem.getText().trim();
		} catch (Exception e) {
			 System.out.println(e);
		}
		return str;
	}

	/**
	 * Returns the title of the current page.
	 *
	 * @return The title of the current page, with leading and trailing
	 *         whitespace stripped, or null if one is not already set
	 */
	public String getTitle() {
		String title = null;
		try {
			WebDriver driver = DriverFactory.getDriver();
			title = driver.getTitle();
		} catch (Exception e) {
			 System.out.println(e);
		}
		return title;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean isSelected(Object element, String desc) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			if (elem.isSelected()) {
				bool = true;
				
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (!desc.equals("")) {
				if (bool) {
					System.out.println("| PASS | " + desc + "' is displayed as selected");
					logger.assertLog(true, "'" + desc + "' is displayed as selected");
				} else {
					System.out.println("| FAIL | " + desc + "' is NOT displayed as selected");
					logger.assertLog(false, "'" + desc + "' is NOT displayed as selected");
				}
			}
		}
		return bool;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean isSelected(Object element) {
		return isSelected(element, "");
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean isElementPresent(Object element, String desc) {
		boolean bool = false;
		boolean reportFlag = false;
		WebElement elem = null;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			elem = getWebElement(element);
			if (elem.isDisplayed() || elem.isEnabled()) {
				bool = true;
				reportFlag = true;
			}
			
		} catch (Exception e) {
			bool = false;
			reportFlag = false;
			System.out.println(e);
		} finally {
			if (!desc.equals("")) {
				if (reportFlag) {
					System.out.println("| PASS | " + desc + "' is displayed");
					logger.assertLog(true, "'" + desc + "' is displayed");
				} else {
					System.out.println("| FAIL | " + desc + "' is NOT displayed");
					logger.assertLog(false, "'" + desc + "' is NOT displayed");
				}
			}
		}

		return bool;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Clicks on the checkbox to select it only if it is not already checked
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * 
	 * @return True if the element is successfully selected
	 */
	public boolean check_Checkbox(Object element) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			if (!isSelected(elem)) {
				javaScript_Click(elem);
			}
			bool = true;
		} catch (Exception e) {
			 System.out.println(e);
		}
		return bool;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Clicks on the checkbox to deselect/uncheck it only if it is already
	 * checked
	 * 
	 * @param element
	 *            A WebElement/By Object
	 * 
	 * @return True if the element is successfully selected
	 */
	public boolean uncheck_Checkbox(Object element) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			if (isSelected(elem)) {
				javaScript_Click(elem);
			}
			bool = true;
			
		} catch (Exception e) {
			 System.out.println(e);
		}
		return bool;
	}


	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public boolean isDisplayed(Object element) {
		boolean bool = false;
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		String methodName = ste[1].getMethodName();
		try {
			WebElement elem = getWebElement(element);
			if (elem.isDisplayed()) {
				bool = true;
				System.out.println("| PASS | Element is displayed.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return bool;
	}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		/**
		 * Will perform java script click on the passed element Object
		 * 
		 * @param element
		 *            A WebElement/By object
		 * 
		 */
		public void javaScript_Click(Object element) {
			javaScript_Click(element,"");
		}

		public void javaScript_Click(Object element,String desc) {
			boolean bool=false;
			StackTraceElement[] ste =Thread.currentThread().getStackTrace();
			String methodName=ste[1].getMethodName();
			try {
				WebElement elem = getWebElement(element);
				((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].click();", elem);
				bool=true;
			} catch (Exception e) {
				System.out.println(e);
			}finally {
				if (!desc.equals("")) {
					if (bool) {
						System.out.println("| PASS | " + desc +" is clicked");
						logger.assertLog(true, desc +" is clicked");
					} else {
						System.out.println("| FAIL | " + desc +" is NOT clicked");
						logger.assertLog(false, desc +" is NOT clicked");
					}

				}
			}

		}

}
