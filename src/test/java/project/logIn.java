package project;

// Importing Selenium WebDriver and TestNG libraries
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

// Test class to verify login functionality using Selenium and TestNG
public class logIn {
    // Creating WebDriver instance (Chrome browser)
    WebDriver driver = new ChromeDriver();

    // This method runs before the test and opens the website
    @BeforeTest
    public void openBrother()
    {
        driver.get("https://demoblaze.com");
    }

    // Main test case for login
    @Test
    public void testcase() throws InterruptedException {

        // Hardcoded login credentials
        String username = "AhmadMohamed";
        String password = "12345678910";
        // Finding elements for login functionality
        WebElement loginElement = driver.findElement(By.id("login2"));
        WebElement loginUserNameElement = driver.findElement(By.id("loginusername"));
        WebElement loginPasswordElement = driver.findElement(By.id("loginpassword"));
        List<WebElement> loginButtonElements = driver.findElements(By.xpath("//*[text() = 'Log in']"));

        // Clicking the login from header
        loginElement.click();

        // Waits until the login form appears
        Wait<WebDriver> wait0 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait0.until(d -> loginButtonElements.get(0).isDisplayed());

        // Entering login credential
        loginUserNameElement.sendKeys(username);
        loginPasswordElement.sendKeys(password);

        // Clicking 'Log in' button on the form
        loginButtonElements.get(1).click();

        try {
            // Wait any alert that might appear (indicating login failure)
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(1));
            Alert alert = wait1.until(ExpectedConditions.alertIsPresent());
            // If alert appears, fail the test with alert message
            Assert.fail("Test failed : " +alert.getText());
            alert.accept();
            // If no alert appears, after 1 second through Timeout Exception (login is assumed to be successful)

        } catch (TimeoutException e) {
            // Verifying welcome message
            WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(5));
            wait2.until(xx->driver.findElement(By.id("nameofuser")).isDisplayed());

            String welcomeMassage = driver.findElement(By.id("nameofuser")).getText();
            String[] afterSplit = welcomeMassage.split("Welcome ");
            // Check if welcome message matches the username
            if (!afterSplit[1].equals(username)) {
                // Fail the test if the user doesn't match
                Assert.fail("User Name does not match.");
            }
            else
            {
                System.out.println("Test passed : Sign in successful.");
            }
        }
    }

    // This method runs after the test and closes the browser
    @AfterTest
    public void closeBrother()
    {
        driver.quit();// Quits the entire browser session
    }
}
