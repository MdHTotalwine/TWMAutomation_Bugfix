package com.totalwine.test.shoppinglist;

/*
 * Shopping List (Add item to existing list)
 * Workflow:
 * 1. Navigate to PDP
 * 2. Add to Shopping List
 * 3. Login to Account
 * 4. Add to existing shopping list
 * 5. Validate that item is present in existing shopping list
 *  
 * Shopping List (Add item to existing list)
 * Workflow:
 * 1. Add item to new shopping list
 * 2. Navigate to PDP
 * 3. Add to Shopping List
 * 4. Login to Account
 * 5. Add to new shopping list
 * 6. Validate that item is present in newly created shopping list
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
import jxl.read.biff.BiffException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.totalwine.test.actions.SiteAccess;
import com.totalwine.test.config.ConfigurationFunctions;
import com.totalwine.test.pages.PageGlobal;
import com.totalwine.test.pages.PageShoppingList;
import com.totalwine.test.trials.Browser;

public class ShoppingListAddItem extends Browser {
	
	public String IP = "71.193.51.0";


	@BeforeMethod
	public void setUp() throws Exception {
		driver.manage().window().maximize();
	  } 
	
	//Create new shopping list and add item to it
	@Test
	public void ShoppingListAddItemNewTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("New Shopping List Add/Edit/Delete Test");
		SiteAccess.ActionAccessSite(driver, IP);
	    
		//Navigate to PDP
	    driver.navigate().to(ConfigurationFunctions.accessURL+"/wine/white-wine/chardonnay/cloud-break-chardonnay/p/110892750");
	    Thread.sleep(3000);
	    logger.log(LogStatus.PASS, "Access PDP");
		//Add to Shopping List
	    driver.findElement(By.cssSelector("section#pdpTabs.pdp-carousel > section.item.pdp-tab-overview section.pdp-tab-overview-type > div.pdp-buy > button.btn.btn-red.anAddToListInit")).click();
	    Thread.sleep(2000);
	    logger.log(LogStatus.PASS, "Add item to new shopping list");
	    //Login to Account
	    driver.switchTo().frame("iframe-signin-overlay");
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys(ConfigurationFunctions.TESTLOGIN);
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys(ConfigurationFunctions.TESTPWD);
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(8000);
	    logger.log(LogStatus.PASS, "Login to account");
		//Add to new shopping list
	    driver.findElement(By.cssSelector("#dWishListName > div > span > i")).click();
	    driver.findElement(By.cssSelector("#dWishListName > div > div > div > div.jspPane > ul > li:nth-child(2)")).click();
	    driver.findElement(By.cssSelector("#addToList")).click();
	    Thread.sleep(2000);
	    Assert.assertEquals("Your new shopping list has been created!", driver.findElement(By.cssSelector("div.add-list-confirm-right > div.add-list-success")).getText());
	    driver.findElement(By.cssSelector("button#addToList")).click();
	    Thread.sleep(3000);
	    logger.log(LogStatus.PASS, "Create new shopping list");
	    //Validate that item is present in existing shopping list
	    driver.findElement(By.cssSelector("button.btn-red.an_ProdView")).click();
	    Thread.sleep(5000);
	    Assert.assertEquals(driver.findElements(By.linkText("Cloud Break Chardonnay")).isEmpty(),false);
	    logger.log(LogStatus.PASS, "Validate that items is inserted into new shopping list");
	    //Delete item from Shopping List (so it can be added again)
	    WebElement scroll_Price = driver.findElement(By.linkText("Cloud Break Chardonnay"));
	 	scroll_Price.sendKeys(Keys.ARROW_DOWN);
	 	scroll_Price.sendKeys(Keys.ARROW_DOWN);
	    driver.findElement(By.cssSelector("a.icon-list-delete.analyticsDeleteList")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("#frmDeleteProduct > div.send-list-btn > button.btn-red")).click();
	    Thread.sleep(2000);
	    logger.log(LogStatus.PASS, "Delete item from shopping list");
	    //Delete Shopping list
	    driver.findElement(By.cssSelector("a.analyticsDeleteList")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector("div.modal-dialog > div > div > div > form#dd > div > button.btn-red")).click();
	    Thread.sleep(2000);
	    logger.log(LogStatus.PASS, "Delete new list");
	    //Validate Login and then Log out
	    Assert.assertEquals(driver.findElements(By.linkText("Welcome, Rajat")).isEmpty(),false);
	    driver.findElement(By.linkText("Welcome, Rajat")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    Thread.sleep(5000);
	    Assert.assertEquals(driver.findElements(PageGlobal.TopNavAccount).isEmpty(),false);
	    logger.log(LogStatus.PASS, "Log out of account");
	}
		
	//Add item to existing shopping list
	@Test
	public void ShoppingListAddItemExistingTest () throws InterruptedException, BiffException, IOException {
		logger=report.startTest("Existing Shopping List Add/Edit/Delete Test");
		SiteAccess.ActionAccessSite(driver, IP);
		
		//Navigate to PDP
	    driver.navigate().to(ConfigurationFunctions.accessURL+"/wine/white-wine/chardonnay/cloud-break-chardonnay/p/110892750");
	    Thread.sleep(3000);
	    logger.log(LogStatus.PASS, "Access PDP");
		//Add to Shopping List
	    driver.findElement(By.cssSelector("section#pdpTabs.pdp-carousel > section.item.pdp-tab-overview section.pdp-tab-overview-type > div.pdp-buy > button.btn.btn-red.anAddToListInit")).click();
	    Thread.sleep(2000);
	    logger.log(LogStatus.PASS, "Add item to shopping list");
		//Login to Account
	    driver.switchTo().frame("iframe-signin-overlay");
	    driver.findElement(By.id("j_username")).clear();
	    driver.findElement(By.id("j_username")).sendKeys("rsud@live.com");
	    driver.findElement(By.id("j_password")).clear();
	    driver.findElement(By.id("j_password")).sendKeys("yoyo55");
	    driver.findElement(By.xpath("//button[@type='button']")).click();
	    Thread.sleep(8000);
	    logger.log(LogStatus.PASS, "Login to account");
	    //Add to existing shopping list
	    driver.findElement(By.cssSelector("div#dWishListName > div.customselect")).click();
	    //driver.findElement(By.cssSelector("div#dWishListName > div.customselect > div > div > div > ul > li[data-val=icongo]")).click();
	    driver.findElement(By.xpath("//div/div/div/div/ul/li[2]")).click();
	    driver.findElement(By.cssSelector("button#addToList")).click();
	    Thread.sleep(3000);
	    logger.log(LogStatus.PASS, "Add item to existing shopping list");
		//Validate that item is present in existing shopping list
	    driver.findElement(By.cssSelector("button.btn-red.an_ProdView")).click();
	    Thread.sleep(5000);
	    Assert.assertEquals(driver.findElements(By.linkText("Cloud Break Chardonnay")).isEmpty(),false);
	    logger.log(LogStatus.PASS, "Validate that item is present in existing shopping list");
	    
	  //Check for survey popup
	    if (driver.findElements(By.xpath("//img[contains(@src,'https://qdistribution.qualtrics.com/WRQualtricsShared/Graphics//black_popup_x.png')]")).size()!=0)
	    	driver.findElement(By.xpath("//img[contains(@src,'https://qdistribution.qualtrics.com/WRQualtricsShared/Graphics//black_popup_x.png')]")).click();
	    Thread.sleep(2000);
	    
	    //Delete item from Shopping List (so it can be added again)
	    driver.findElement(By.linkText("Cloud Break Chardonnay")).sendKeys(Keys.ARROW_DOWN);
	    //driver.findElement(By.xpath("//a[@onclick=\"setDeleteLineItemForm('110892750-1','205','icongo','Cloud Break Chardonnay')\"]")).click();
	    driver.findElement(PageShoppingList.DeleteLink).click();
	 	Thread.sleep(2000);
	    driver.findElement(By.cssSelector("#frmDeleteProduct > div.send-list-btn > button.btn-red")).click();
	    Thread.sleep(2000);
	    logger.log(LogStatus.PASS, "Delete item from shopping list");
	    //Validate Login and then Log out
	    Assert.assertEquals(driver.findElements(By.linkText("Welcome, Rajat")).isEmpty(),false);
	    driver.findElement(By.linkText("Welcome, Rajat")).click();
	    driver.findElement(By.linkText("Log out")).click();
	    Thread.sleep(5000);
	    Assert.assertEquals(driver.findElements(PageGlobal.TopNavAccount).isEmpty(),false);
	    logger.log(LogStatus.PASS, "Logout of account");
	}
}
