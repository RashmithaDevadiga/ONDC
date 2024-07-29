package testcases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.qa.ExtentReporterListener.ExtentReportSetup;

import base.BaseTest;
import utilities.SeleniumUtils;


public class ONDC_portal_Flow3  extends BaseTest {
    private static ExtentTest test;
    @BeforeClass
    public void setUpClass() throws IOException {
        test = ExtentReportSetup.getExtent().createTest(getClass().getSimpleName());
    }

    @Test
    public void MerchantSidePartial_OrderCancel() throws InterruptedException, IOException {
       
       // setUp(); Not needed 

        SeleniumUtils.setupDriver();

      SeleniumUtils.clickElement(loc.getProperty("Domain1"), "Domain1");
       SeleniumUtils.clickElement(loc.getProperty("Reatil"), "Retail");
       SeleniumUtils.clickElement(loc.getProperty("TranscationType"), "TranscationType");
       SeleniumUtils.clickElement(loc.getProperty("B2C"), "B2C");
       SeleniumUtils.clickElement(loc.getProperty("Environment"), "Environment");
       SeleniumUtils.clickElement(loc.getProperty("PrePod"), "PrePod");
       SeleniumUtils.clickElement(loc.getProperty("Role"), "Role");
       SeleniumUtils.clickElement(loc.getProperty("Role_sellerNP"), "Role_sellerNP");
       SeleniumUtils.clickElement(loc.getProperty("API_version"), "API_version");
       SeleniumUtils.clickElement(loc.getProperty("APIversion"), "APIversion");
       Thread.sleep(20000);
    
       SeleniumUtils.clickElement(loc.getProperty("Start"), "Start");
       Thread.sleep(3000);
       SeleniumUtils.clickElement(loc.getProperty("Fill_App_Details"), "Fill_App_Details");
       Thread.sleep(1000);
       SeleniumUtils.clickElement(loc.getProperty("Domain"), "Domain");
       SeleniumUtils.clickElement(loc.getProperty("FashionID"), "FashionID");
       
       SeleniumUtils.sendKeysToElement(loc.getProperty("ServiceablePincode"), "560001", "ServiceablePincode");
       SeleniumUtils.clickElement(loc.getProperty("version"), "version");
       SeleniumUtils.clickElement(loc.getProperty("version_select"), "version_select");
       
       SeleniumUtils.sendKeysToElement(loc.getProperty("item_name"), "c455bf79-fd70-4efc-8cd4-82f6b5a8e118", "item_name");
       SeleniumUtils.sendKeysToElement(loc.getProperty("serviceableGPS"), " 12.979688,77.592687", "serviceableGPS");
       SeleniumUtils.clickElement(loc.getProperty("Nptype"), "Nptype");
       SeleniumUtils.clickElement(loc.getProperty("Nptype_select"), "Nptype_select");

       
      

       SeleniumUtils.sendKeysToElement(loc.getProperty("subscriber_uri"), "https://api.test.esamudaay.com/ondc/sdk/bpp/retail/esamudaay/", "subscriber_uri");
       SeleniumUtils.sendKeysToElement(loc.getProperty("subscriber_id"), "api.test.esamudaay.com/ondc/sdk/bpp/retail/esamudaay", "subscriber_id");
       SeleniumUtils.sendKeysToElement(loc.getProperty("provider_id"), "Gold Plated Chain", "provider_id");
       
       SeleniumUtils.clickElement(loc.getProperty("proceed"), "proceed");
       Thread.sleep(1000);
       SeleniumUtils.clickElement(loc.getProperty("Flow3"), "Flow3");
       Thread.sleep(10000);




    }
    }

    
    
