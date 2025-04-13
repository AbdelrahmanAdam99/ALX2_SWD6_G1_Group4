package project;
// Importing required Selenium and TestNG libraries
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import java.util.logging.SocketHandler;

public class contact {
    // Creating a WebDriver instance using Chrome
    WebDriver driver = new ChromeDriver();
    // This method runs before the test starts, opens the browser and navigates to the website
    @BeforeTest
    public void openBrother()
    {
        driver.get("https://demoblaze.com");
    }
    // Main test case to send message for contact with website owner
    @Test
    public void testcase() throws InterruptedException {
        String[] contactData = {
                                "abdoadam@gmail.com",                  //E-mail
                                "abdo adam",                           // contact name
                                "My order not confiermed"              //message
        };


        //find 'contact' button and click
        WebElement contactButtonElement  = driver.findElement(By.xpath("//*[text() = 'Contact']"));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(d->contactButtonElement.isDisplayed());
        contactButtonElement.click();

        //wait until contact form is displayed
        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait1.until(dd->driver.findElement(By.id("exampleModalLabel")).isDisplayed());

        //fill contact form
        driver.findElement(By.id("recipient-email")).sendKeys(contactData[0]);
        driver.findElement(By.id("recipient-name")).sendKeys(contactData[1]);
        driver.findElement(By.id("message-text")).sendKeys(contactData[2]);
        driver.findElement(By.xpath("//*[text() = 'Send message']")).click();

        //wait until alert form is displayed and get the alert message
        try {
            WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(5));
            Alert alert = wait2.until(ExpectedConditions.alertIsPresent());

            for (String contactDatum : contactData) {
                if (contactDatum.isEmpty() && alert.getText().equals("Thanks for the message!!")) {
                    Assert.fail("The form accepts the message even when one or more fields are empty");
                }
            }
            System.out.println("Test passed : " +alert.getText());
            alert.accept();
        } catch (Exception e)
        {
            Assert.fail("Test failed : Message not send");
        }


        Thread.sleep(2000);


    }
    // This method runs after the test and closes the browser
    @AfterTest
    public void closeBrother()
    {
        driver.quit();// Closes all browser windows and ends session
    }
}
