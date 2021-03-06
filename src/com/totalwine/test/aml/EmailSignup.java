package com.totalwine.test.aml;

/*
 * Web Account Registration Workflow
 * Workflow:
 * 	1. Click the "Account" link in top nav
 * 	2. Assert the presence of all links in the popup screen
 * 	3. Click the "Sign Up" link and navigate to the Registration screen
 * 	4. Complete the form with registration information and submit the form
 * 	5. Validate the presence of elements on the registration confirmation screen
 * 	6. Complete preferences and save the information
 *  7. Validate the elements presented on the Account home page.
 *  8. Log out and attempt to re-login using the same credentials
 *  9. Validate the logged in state ensuring the credentials work.
 *  
 * Technical Modules:
 * 	1. BeforeMethod (Test Pre-requisites):
 * 			Invoke webdriver
 * 			Maximize browser window
 * 	2. Test (Workflow)
 * 	3. AfterMethod
 * 			Take screenshot, in case of failure
 * 			Close webdriver
 * 	4. AfterClass
 * 			Quit webdriver
 */
//@author=rsud

import java.io.IOException;
import java.util.Random;

import jxl.read.biff.BiffException;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageEmailSignupModal;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.trials.Browser;

public class EmailSignup extends Browser {
	
	String IP="71.193.51.0";
	
	@DataProvider(name="EmailSignup")
    public Object[][] createData() {
    	Object[][] retObjArr=ConfigurationFunctions.getTableArray(ConfigurationFunctions.resourcePath,"EmailSignup", "emailaddresses");
        return(retObjArr);
    } 
//	
	@Test //(dataProvider = "EmailSignup") //Existing Email Address
	public void EmailSignupTest () throws InterruptedException, BiffException, IOException {
		//String [] emailAddresses = {"automate1@totalwine.com","automate2@totalwine.com","automate3@totalwine.com","automate4@totalwine.com"};
		logger=report.startTest("Email Signup (Existing Email Address)");
		SiteAccess.ActionAccessSite(driver, IP);
    	driver.findElement(PageGlobal.FooterEmailSignup).click();
    	Thread.sleep(2000);
    	driver.switchTo().frame(driver.findElement(PageEmailSignupModal.Modal));
	    driver.findElement(PageEmailSignupModal.EmailField).clear();
	    driver.findElement(PageEmailSignupModal.EmailField).sendKeys("automate1@totalwine.com");
	    driver.findElement(PageEmailSignupModal.ConfirmEmailField).clear();
	    driver.findElement(PageEmailSignupModal.ConfirmEmailField).sendKeys("automate1@totalwine.com");
	    //driver.findElement(By.cssSelector("label")).click();
	    driver.findElement(PageEmailSignupModal.ConfirmEmailField).click();
	    //driver.findElement(By.id("emailuserregister")).click();
	    Actions action = new Actions(driver);
	    action.moveToElement(driver.findElement(PageEmailSignupModal.Register)).doubleClick().build().perform(); //Double-click
	    Thread.sleep(3000);
	    Assert.assertEquals("The email provided matches an existing account. Please try again.", driver.findElement(By.cssSelector("div.email-container-signin > div.notice")).getText());
	    logger.log(LogStatus.PASS, "Email signup using existing email address presented an error notification");
	}
	
	@Test //New Email Address
	public void NewEmailSignupTest () throws InterruptedException {
		logger=report.startTest("Email Signup (New Email Address");
		Random rand = new Random();
	    int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
	    int randomNum2 = rand.nextInt((1000 - 1) + 1) + 1;
	    SiteAccess.ActionAccessSite(driver, IP);
    	driver.findElement(PageGlobal.FooterEmailSignup).click();
    	Thread.sleep(2000);
    	driver.switchTo().frame(driver.findElement(PageEmailSignupModal.Modal));
	    driver.findElement(PageEmailSignupModal.EmailField).clear();
	    driver.findElement(PageEmailSignupModal.EmailField).sendKeys("test"+randomNum+"_"+randomNum2+"@totalwine.com");
	    driver.findElement(PageEmailSignupModal.ConfirmEmailField).clear();
	    driver.findElement(PageEmailSignupModal.ConfirmEmailField).sendKeys("test"+randomNum+"_"+randomNum2+"@totalwine.com");
	    //driver.findElement(By.cssSelector("label")).click();
	    driver.findElement(PageEmailSignupModal.Checkbox21Plus).click();
	    //driver.findElement(By.id("emailuserregister")).click();
	    Actions action = new Actions(driver);
	    action.moveToElement(driver.findElement(PageEmailSignupModal.Register)).doubleClick().build().perform(); //Double-click
	    Thread.sleep(3000);
	    logger.log(LogStatus.PASS, "Email signup with an unregistered email address");
	    //Assert.assertEquals("Thank you for signing up to receive emails from Total Wine & More!", driver.findElement(By.cssSelector("#email-signup-overlay-success > div.modal-dialog > div.modal-content > div.modal-body > div.heading-h1")).getText());
	    //Assert.assertEquals(driver.findElements(By.cssSelector("html>body>section#email-signup-overlay-success.modal.fade.in.email-signup-overlay.an-emailSignUpSuccess>div.modal-dialog>div.modal-content>div.modal-body>div#emailCarousel.carousel.slide>div.carousel-inner-container>div.carousel-indicators-wrapper>div.button>a.btn.btn-red.signUpBtn.analyticsRegister")).isEmpty(),false);
	}
}
