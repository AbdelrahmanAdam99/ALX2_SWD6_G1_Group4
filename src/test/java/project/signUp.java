package project;
// Importing Selenium WebDriver and TestNG libraries
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

// Test class to verify sign-up functionality using Selenium and TestNG
public class signUp{
    // Creating WebDriver instance (Chrome browser)
    WebDriver driver = new ChromeDriver();

    // This method runs before the test and opens the website
    @BeforeTest
    public void openBrother()
    {
        driver.get("https://demoblaze.com");
    }

    // Main test case for sign-up
    @Test
    public void testcase() throws InterruptedException {
        // Sample usernames and passwords (some valid, some invalid)
        String[] userNames = {"","Ahmedali","monaahmad","mandomuhammed","muhamedashraf"};
        String[] passwords = {"135484","","ee556945","aa3987@4$","1235842"};

        // Locating elements required for the sign-up process
        WebElement signUpElement = driver.findElement(By.id("signin2"));
        WebElement signUpUserNameElement = driver.findElement(By.id("sign-username"));
        WebElement signUpPasswordElement = driver.findElement(By.id("sign-password"));
        List<WebElement> SignUpButtonElements = driver.findElements(By.xpath("//*[text() = 'Sign up']"));




        // click on sign up button
        signUpElement.click();

        // Iterates through all test cases using username/password combinations
        for(int i = 0; i <userNames.length ;i++)
        {
            // Waits until the sign-up form is visible
            Wait<WebDriver> wait0 = new WebDriverWait(driver,Duration.ofSeconds(5));
            wait0.until(d -> SignUpButtonElements.get(0).isDisplayed());

            // Clears input fields before each attempt
            signUpUserNameElement.clear();
            signUpPasswordElement.clear();

            // Enters current test username and password
            signUpUserNameElement.sendKeys(userNames[i]);
            signUpPasswordElement.sendKeys(passwords[i]);

            // Clicks the form's sign-up button to submit
            SignUpButtonElements.get(1).click();

            // Waits for the alert to appear after sign-up attempt
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
            Alert alert = wait1.until(ExpectedConditions.alertIsPresent());

            // Logs the alert message and test data to the console
            System.out.println("Username'"+userNames[i]+"'" +" and passward '" +passwords[i]+"'" + "\nThe Alert massage is " +alert.getText());
            Thread.sleep(1000);

            // check the state of the alert
            if(alert.getText().equals("Sign up successful."))
            {
                alert.accept();
                //click to sign up button again in case fo successful sign up
                Thread.sleep(100);
                signUpElement.click();
            }
            else
            {
                // Accepts alert for all other messages (e.g., "User already exists", empty fields)
                alert.accept();
            }
        }
        Thread.sleep(1000);
    }
    // This method runs after the test and closes the browser
    @AfterTest
    public void closeBrother()
    {
        // Closes all browser windows and ends the WebDriver session
        driver.quit();
    }
}
