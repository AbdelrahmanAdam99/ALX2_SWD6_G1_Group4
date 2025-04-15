package project;

// Import Selenium and TestNG libraries for browser automation and testing
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class placeOrder {
    // Instantiate Chrome browser driver
    WebDriver driver = new ChromeDriver();

    // Runs before test method
    @BeforeTest
    public void openBrother()
    {
        driver.get("https://demoblaze.com");// Navigates to demoblaze.com
    }

    // the main test case
    @Test
    public void testcase() throws InterruptedException {

        String[] orderData = {
                                "ahmed mahmoud",   //Name
                                "USA",             //Country
                                "Florida",         //City
                                "1234568796425842",//Credit card
                                "April",           //Month
                                "2025"             //Year
                                };
        // ----- Add product from Phones -----
        // Find 'Phones' page and click
        WebElement phonesPageElement = driver.findElement(By.xpath("//*[text() = 'Phones']"));
        phonesPageElement.click();

        // Wait until the product appears
        WebDriverWait wait0 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait0.until(d->driver.findElement(By.xpath("//*[@class='hrefch' and text() = 'Nokia lumia 1520']")).isDisplayed());

        // Select the product
        WebElement productFromPhonesElement = driver.findElement(By.xpath("//*[@class='hrefch' and text() = 'Nokia lumia 1520']"));
        productFromPhonesElement.click();

        // Wait for product page appear
        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait1.until(d->driver.findElement(By.xpath("//*[@class='name' and text() = 'Nokia lumia 1520']"))).isDisplayed();

        //Gets the price text and convert it from text to integer
        String d = driver.findElement(By.xpath("//*[@class = 'price-container']")).getText();
        int phoneProductPrice =Integer.parseInt(d.substring(1,4));

        // Clicks on 'Add to cart' button
        WebElement addToCardElement0 = driver.findElement(By.xpath("//*[text() = 'Add to cart']"));
        addToCardElement0.click();

        //wait for the alert
        WebDriverWait wait2 =new WebDriverWait(driver,Duration.ofSeconds(5));
        Alert alert = wait2.until(ExpectedConditions.alertIsPresent());

        // Checks for the alert text
        if(!alert.getText().equals("Product added"))
        {
            // Fails test if the alert massage do not contain "product added"
            Assert.fail("Test failed : can not add product to cord");
        }
        alert.accept();// Accepts the alert

        // navigate to 'Home' tab again to add another product
        WebElement homePageElement = driver.findElement(By.xpath("//*[text() = 'Home ']"));
        homePageElement.click();

        // --------- Add product from Monitors category ---------
        //Waits for monitors page to appear
        WebDriverWait wait3 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait3.until(as->driver.findElement(By.xpath("//*[text() = 'Monitors']"))).isDisplayed();

        // Finds 'monitors' page and Clicks on it
        WebElement monitorsPageElement = driver.findElement(By.xpath("//*[text() = 'Monitors']"));
        monitorsPageElement.click();

        // Wait for a product to appear
        WebDriverWait wait4 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait4.until(c->driver.findElement(By.xpath("//*[@class='hrefch'  and text() ='Apple monitor 24']")).isDisplayed());

        // find and click on the product
        WebElement productFromMonitorsElement = driver.findElement(By.xpath("//*[@class='hrefch'  and text() ='Apple monitor 24']"));
        productFromMonitorsElement.click();

        // wait for the product page to appear
        WebDriverWait wait5 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait5.until(ds->driver.findElement(By.xpath("//*[@class='name' and text() = 'Apple monitor 24']"))).isDisplayed();

        //Gets the price text and convert it from text to integer
        String ddd = driver.findElement(By.xpath("//*[@class = 'price-container']")).getText();
        int monitorProductPrice =Integer.parseInt(ddd.substring(1,4));

        // Clicks on 'Add to cart' button
        WebElement addToCardElement2 = driver.findElement(By.xpath("//*[text() = 'Add to cart']"));
        addToCardElement2.click();

        //wait for the alert
        WebDriverWait wait6 =new WebDriverWait(driver,Duration.ofSeconds(5));
        Alert alert2 = wait6.until(ExpectedConditions.alertIsPresent());

        // Checks for the alert text
        if(!alert2.getText().equals("Product added"))
        {
            // Fails test if the alert massage do not contain "product added"
            Assert.fail("Test failed : can not add product to cord");
        }
        alert2.accept();// Accepts the alert

        // ----- Go to Cart -----
        WebElement cartPageElement = driver.findElement(By.id("cartur"));
        cartPageElement.click();

        //Waits for card page to appear
        WebDriverWait wait7 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait7.until(ds->driver.findElement(By.id("orderModalLabel"))).isDisplayed();
        Thread.sleep(2000);// Wait for price update

        // Gets total price from cart
        String ee = driver.findElement(By.id("totalp")).getText();
        int totalOrderBeforeDelete = Integer.parseInt(ee);

        // compare total price from card to product prices before delete a product
        if(totalOrderBeforeDelete != (phoneProductPrice+monitorProductPrice))
        {
            Assert.fail("Test failed : Total cost is not correct before delete");// Fails if prices don’t match
        }

        // ----- Delete a product from cart -----
        // Find delete product and click on 'delete' button
        WebElement deleteElement= driver.findElement(By.xpath("//tr[@class='success' and td[text()='Apple monitor 24']]//a[text()='Delete']"));
        deleteElement.click();

        // Wait for card page appear again
        WebDriverWait wait8 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait8.until(dd->driver.findElement(By.id("totalp")).isDisplayed());
        Thread.sleep(3000);

        // Gets total price from cart after delete the product
        int totalOrderAfterDelete = Integer.parseInt(driver.findElement(By.id("totalp")).getText());

        // compare total price from card to product prices after delete a product
        if(totalOrderAfterDelete != (phoneProductPrice))
        {
            Assert.fail("Test failed : Total cost is not correct After delete");// Fails if prices don’t match
        }

        // ----- Place Order -----
        // Find 'Place Order' button and click
        WebElement  placeOrderElement = driver.findElement(By.xpath("//*[text() = 'Place Order']"));
        placeOrderElement.click();

        // Wait for place order form to appear
        WebDriverWait wait9 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait9.until(f->driver.findElement(By.id("orderModalLabel")).isDisplayed());


        // Gets total price from 'place order' form
        int totalOrderFromPlace = Integer.parseInt((driver.findElement(By.id("totalm")).getText()).substring(7,10));

        // compare total prices after delete a product to total price on place order form
        if(totalOrderFromPlace != totalOrderAfterDelete)
        {
            Assert.fail("Test failed : Total cost is not correct at place page");// Fails if prices don’t match
        }


        // ----- Fill in the form details -----
        driver.findElement(By.id("name")).sendKeys(orderData[0]);
        driver.findElement(By.id("country")).sendKeys(orderData[1]);
        driver.findElement(By.id("city")).sendKeys(orderData[2]);
        driver.findElement(By.id("card")).sendKeys(orderData[3]);
        driver.findElement(By.id("month")).sendKeys(orderData[4]);
        driver.findElement(By.id("year")).sendKeys(orderData[5]);
        driver.findElement(By.xpath("//*[text() = 'Purchase']")).click();

        try {
            Alert alert3 = wait1.until(ExpectedConditions.alertIsPresent());
            Assert.fail("test failed : " +alert3.getText()); // Fail if not visible
        }catch (TimeoutException e)
        {
            // Wait for confirmation
            WebDriverWait wait10 = new WebDriverWait(driver,Duration.ofSeconds(5));
            wait10.until(dd->driver.findElement(By.xpath("//*[text() = 'Thank you for your purchase!']")).isDisplayed());
        }
        // Log success and Print order summary
        System.out.println("The order has been confermed with details ");
        System.out.println(driver.findElement(By.xpath("//*[@class = 'lead text-muted ']")).getText());

        driver.findElement(By.xpath("//*[@class = 'confirm btn btn-lg btn-primary' and text() = 'OK']")).click();// Click OK


        Thread.sleep(3000);
    }

    // This method runs after the test and closes the browser
    @AfterTest
    public void closeBrother()
    {
            driver.quit();// Closes all browser window and ends session
    }
}