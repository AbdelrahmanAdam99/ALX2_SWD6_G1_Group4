package project;
// Importing required Selenium and TestNG libraries
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;






public class addProducts {
    // Creating a WebDriver instance using Chrome
    WebDriver driver = new ChromeDriver();







    // This method runs before the test starts, opens the browser and navigates to the website
    @BeforeTest
    public void openBrother()
    {
        driver.get("https://demoblaze.com");
    }






    // Main test case to add products to the cart and verify the total price
    @Test
    public void testcase() throws InterruptedException {

        // --------- Add product from Phones category ---------
        // Finds 'Phones' tab and Clicks on 'Phones' tab
        WebElement phonesPageElement = driver.findElement(By.xpath("//*[text() = 'Phones']"));
        phonesPageElement.click();

        // Waits for product to appear
        WebDriverWait wait0 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait0.until(d->driver.findElement(By.xpath("//*[@class='hrefch' and text() = 'Samsung galaxy s7']")).isDisplayed());

        // Clicks on the product link
        WebElement productFromPhonesElement = driver.findElement(By.xpath("//*[@class='hrefch' and text() = 'Samsung galaxy s7']"));
        productFromPhonesElement.click();

        // Waits for product page to appear
        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait1.until(d->driver.findElement(By.xpath("//*[@class='name' and text() = 'Samsung galaxy s7']"))).isDisplayed();

        //Gets the price text and convert it from text to integer
        String d = driver.findElement(By.xpath("//*[@class = 'price-container']")).getText();
        int phoneProductPrice =Integer.parseInt(d.substring(1,4));

        // Clicks on 'Add to cart' button
        WebElement addToCardElement0 = driver.findElement(By.xpath("//*[text() = 'Add to cart']"));
        addToCardElement0.click();

        // Waits for alert appears
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

        // --------- Add product from Laptops category ---------
        //Waits for 'Laptops' page to appear
        WebDriverWait wait3 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait3.until(a->driver.findElement(By.xpath("//*[text() = 'Laptops']"))).isDisplayed();

        // Finds the 'Laptops' page and click
        WebElement laptopsPageElement = driver.findElement(By.xpath("//*[text() = 'Laptops']"));
        laptopsPageElement.click();

        // Wait for a product to appear
        WebDriverWait wait4 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait4.until(c->driver.findElement(By.xpath("//*[@class='hrefch'  and text() ='MacBook air']")).isDisplayed());
        //click on the product
        WebElement productFromLaptopsElement = driver.findElement(By.xpath("//*[@class='hrefch'  and text() ='MacBook air']"));
        productFromLaptopsElement.click();

        //wait for product page is appear
        WebDriverWait wait5 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait5.until(ds->driver.findElement(By.xpath("//*[@class='name' and text() = 'MacBook air']"))).isDisplayed();

        //Gets the price text and convert it from text to integer
        String dd = driver.findElement(By.xpath("//*[@class = 'price-container']")).getText();
        int laptopProductPrice =Integer.parseInt(dd.substring(1,4));
        WebElement addToCardElement1 = driver.findElement(By.xpath("//*[text() = 'Add to cart']"));
        addToCardElement1.click();

        // Waits for alert appears
        WebDriverWait wait6 =new WebDriverWait(driver,Duration.ofSeconds(5));
        Alert alert1 = wait6.until(ExpectedConditions.alertIsPresent());

        // Checks for the alert text
        if(!alert1.getText().equals("Product added"))
        {
            // Fails test if the alert massage do not contain "product added"
            Assert.fail("Test failed : can not add product to cord");
        }
        alert1.accept();// Accepts the alert

        // navigate to 'Home' tab again to add another product
        WebElement homePageElement1 = driver.findElement(By.xpath("//*[text() = 'Home ']"));
        homePageElement1.click();

        // --------- Add product from Monitors category ---------
        //Waits for monitors page to appear
        WebDriverWait wait7 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait7.until(as->driver.findElement(By.xpath("//*[text() = 'Monitors']"))).isDisplayed();

        // Finds 'monitors' page and Clicks on it
        WebElement monitorsPageElement = driver.findElement(By.xpath("//*[text() = 'Monitors']"));
        monitorsPageElement.click();

        // Wait for a product to appear
        WebDriverWait wait8 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait8.until(c->driver.findElement(By.xpath("//*[@class='hrefch'  and text() ='ASUS Full HD']")).isDisplayed());

        // find and click on the product
        WebElement productFromMonitorsElement = driver.findElement(By.xpath("//*[@class='hrefch'  and text() ='ASUS Full HD']"));
        productFromMonitorsElement.click();

        // wait for the product page to appear
        WebDriverWait wait9 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait9.until(ds->driver.findElement(By.xpath("//*[@class='name' and text() = 'ASUS Full HD']"))).isDisplayed();

        //Gets the price text and convert it from text to integer
        String ddd = driver.findElement(By.xpath("//*[@class = 'price-container']")).getText();
        int monitorProductPrice =Integer.parseInt(ddd.substring(1,4));

        // Clicks on 'Add to cart' button
        WebElement addToCardElement2 = driver.findElement(By.xpath("//*[text() = 'Add to cart']"));
        addToCardElement2.click();

        //wait for the alert
        WebDriverWait wait10 =new WebDriverWait(driver,Duration.ofSeconds(5));
        Alert alert2 = wait10.until(ExpectedConditions.alertIsPresent());

        // Checks for the alert text
        if(!alert2.getText().equals("Product added"))
        {
            // Fails test if the alert massage do not contain "product added"
            Assert.fail("Test failed : can not add product to cord");
        }
        alert2.accept();// Accepts the alert

        // --------- Go to cart and verify total cost ---------
        // Finds cart page and Click on it
        WebElement cartPageElement = driver.findElement(By.id("cartur"));
        cartPageElement.click();

        //Waits for card page to appear
        WebDriverWait wait11 = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait11.until(ds->driver.findElement(By.id("orderModalLabel"))).isDisplayed();
        Thread.sleep(1000);// Waits 1 sec to allow total price to show

        // Gets total price from cart
        int totalOrder = Integer.parseInt(driver.findElement(By.id("totalp")).getText());

        // compare total price from card to product prices
        if(totalOrder != (phoneProductPrice+laptopProductPrice+monitorProductPrice))
        {
            Assert.fail("Test failed : Total cost is not correct");// Fails if prices don’t match
        }

        // Go home page again
        WebElement homePageElement2 = driver.findElement(By.xpath("//*[text() = 'Home ']"));
        homePageElement2.click();

    }







    // This method runs after the test and closes the browser
    @AfterTest
    public void closeBrother()
    {
        driver.quit();// Closes all browser windows and ends session
    }
}
