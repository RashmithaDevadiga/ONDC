package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;
import com.qa.ExtentReporterListener.ExtentReportSetup;

import io.github.bonigarcia.wdm.WebDriverManager;




public class BaseTest {
    public static String Date;
    public static WebDriver driver;

    public static Properties prop = new Properties();
    public static Properties loc = new Properties();

    public static FileReader fr;
    public static FileReader fr1;

    public static ExtentTest test;

    
    @BeforeTest
    public void setUp() throws IOException {
        if (driver == null) {
            fr = new FileReader("/Users/rashmithadevadiga/eclipse-workspace/ONDC/src/main/resources/configfiles/config.properties");
            prop.load(fr);
            fr1 = new FileReader("/Users/rashmithadevadiga/eclipse-workspace/ONDC/src/main/resources/configfiles/locators.properties");
            loc.load(fr1);
        }

        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            driver.get(prop.getProperty("testurl"));
        } else if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            driver.get(prop.getProperty("testurl"));
        }
    }


    
    @AfterTest
    public void tearDown() {
        System.out.println("Tear down successful ");
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void startTest() {
        test = ExtentReportSetup.getExtent().createTest(getClass().getSimpleName());
    }

    @AfterMethod
    public void endTest() {
        ExtentReportSetup.tearDown();
    }
}
