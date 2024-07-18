 package utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BaseTest;
import io.netty.handler.timeout.TimeoutException;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class SeleniumUtils {

    private static ExtentTest test = BaseTest.test;

    public static WebElement findElement(String xpath) {
        return BaseTest.driver.findElement(By.xpath(xpath));
    }

    public static void clickElement(String xpath, String elementName) {
        WebElement element = findElement(xpath);
        element.click();
        logInfo("Clicked on " + elementName);
    }

    public static String getElementText(String xpath) {
        WebElement element = findElement(xpath);
        waitForElementVisibility(xpath, Duration.ofSeconds(10));  // Adjust the timeout as needed
        return element.getText();
    }


    public static void sendKeysToElement(String xpath, String input, String elementName) {
        WebElement element = findElement(xpath);
        element.clear();
        element.sendKeys(input);
        logInfo("Entered '" + input + "' in " + elementName);
    }
    public static void takeScreenshot(String screenshotName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) BaseTest.driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        
        String projectDir = System.getProperty("user.dir");
        String screenshotDirPath = projectDir + File.separator + "screenshots";

        File screenshotDir = new File(screenshotDirPath);

        
        if (!screenshotDir.exists()) {
            if (screenshotDir.mkdirs()) {
                logInfo("Screenshots directory created: " + screenshotDirPath);
            } else {
                logInfo("Failed to create screenshots directory: " + screenshotDirPath);
            }
        }

        File dst = new File(screenshotDirPath + File.separator + screenshotName + ".jpeg");

        FileHandler.copy(source, dst);
        test.info(MediaEntityBuilder.createScreenCaptureFromPath(dst.getAbsolutePath()).build());
    }
    public static void waitForAndCaptureSuccessMessage(String xpath, Duration timeout, String screenshotName) {
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, timeout);

        // Wait for the success message to be visible
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        // Capture screenshot when the success message appears
        try {
            takeScreenshot(screenshotName);
        } catch (IOException e) {
            logInfo("Failed to capture screenshot: " + e.getMessage());
        }

        logInfo("Success message captured: " + successMessage.getText());
    }


    public static void waitForElementVisibility(String xpath, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }
    public static boolean isElementPresent(String xpath, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.driver, timeout);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }


    public static void waitForElementToBeClickable(String xpath, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public static void clickElementWithWait(String xpath, String elementName, Duration timeout) {
        waitForElementToBeClickable(xpath, timeout);
        clickElement(xpath, elementName);
    }

    public static void selectByVisibleText(String xpath, String text, String dropdownName) {
        WebElement dropdown = findElement(xpath);
        Select select = new Select(dropdown);
        select.selectByVisibleText(text);
        logInfo("Selected '" + text + "' from " + dropdownName + " dropdown.");
    }
    public static void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) BaseTest.driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        logInfo("Scrolled into view: " + element.toString());
    }
    public static void scrollDownByPixels(WebDriver driver, int pixels) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String script = "window.scrollBy(0, " + pixels + ")";
            js.executeScript(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void acceptAlert() {
        Alert alert = BaseTest.driver.switchTo().alert();
        alert.accept();
        logInfo("Accepted the alert.");
    }

    public static void dismissAlert() {
        Alert alert = BaseTest.driver.switchTo().alert();
        alert.dismiss();
        logInfo("Dismissed the alert.");
    }

    public static void setupDriver() throws TimeoutException {
        try {
            JavascriptExecutor js = (JavascriptExecutor) BaseTest.driver;
            BaseTest.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(5));
            BaseTest.driver.navigate().refresh();
            BaseTest.driver.manage().window().maximize();
        } catch (TimeoutException e) {
            throw new RuntimeException("TimeoutException occurred in setupDriver: " + e.getMessage());
        }
    }

    public static void acceptAlertAndLog() {
        Alert alert = waitForAlert();
        alert.accept();
        logInfo("Accepted the alert: " + alert.getText());
    }

    public static void dismissAlertAndLog() {
        Alert alert = waitForAlert();
        alert.dismiss();
        logInfo("Dismissed the alert: " + alert.getText());
    }

    private static Alert waitForAlert() {
        WebDriverWait wait = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.alertIsPresent());
    }
    public static boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
 //only for 1 testcase
    public static String checkVideoPresence(String xpath, Duration timeout) {
        try {
            WebDriverWait wait = new WebDriverWait(BaseTest.driver, timeout);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return null; // Return null if the element is present
        } catch (TimeoutException e) {
            // Return the error message if the element is not present within the timeout
            return "Video element not found: " + e.getMessage();
        }
    }
    //only for zupa
    public static void increaseVolume(String videoPlayerXPath) {
        // Execute JavaScript to get the current volume
        Number currentVolume = (Number) ((JavascriptExecutor) BaseTest.driver).executeScript("return arguments[0].volume;", findElement(videoPlayerXPath));

        // Check if the current volume is less than 1.0
        if (currentVolume.doubleValue() < 1.0) {
            // If yes, increase the volume by 0.1
            ((JavascriptExecutor) BaseTest.driver).executeScript("arguments[0].volume += 0.1;", findElement(videoPlayerXPath));
        }
    }


    public static void maximizeScreen(String videoPlayerXPath) {
        // Execute JavaScript to maximize the screen
        ((JavascriptExecutor) BaseTest.driver).executeScript("arguments[0].requestFullscreen();", findElement(videoPlayerXPath));
    }

    public static void triggerPictureInPicture(String videoPlayerXPath) {
        // Execute JavaScript to trigger picture-in-picture
        ((JavascriptExecutor) BaseTest.driver).executeScript("arguments[0].requestPictureInPicture();", findElement(videoPlayerXPath));
    }
    public static void playVideo(String videoPlayerXPath) {
        // Execute JavaScript to play the video
        ((JavascriptExecutor) BaseTest.driver).executeScript("arguments[0].play();", findElement(videoPlayerXPath));
        logInfo("Video is now playing.");
    }
    public static void minimizeVideo(String videoPlayerXPath) {
        // Execute JavaScript to minimize the video
        ((JavascriptExecutor) BaseTest.driver).executeScript("arguments[0].pause();", findElement(videoPlayerXPath));
        logInfo("Video is now minimized.");
    }
    public static void exitPictureInPicture(String videoPlayerXPath) {
        // Execute JavaScript to exit Picture-in-Picture mode
        ((JavascriptExecutor) BaseTest.driver).executeScript("document.exitPictureInPicture();", findElement(videoPlayerXPath));
        logInfo("Exited Picture-in-Picture mode.");
    }
    public static void clickElementWithScrolling(WebElement element) {
        // Scroll to the element to make it visible and clickable
        scrollIntoView(element);

        // Wait for a moment after scrolling
        waitForMilliseconds(1000);

        // Click on the element using JavascriptExecutor
        ((JavascriptExecutor) BaseTest.driver).executeScript("arguments[0].click();", element);
        logInfo("Clicked on element: " + element.toString());
    }

    public static void waitForMilliseconds(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void logInfo(String message) {
        test.info(message);
    }
}