package src.main.java.demo;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class BookMyShow {
    public WebDriver driver;

    public BookMyShow() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.quit();
    }

    public void testcase01() {
        try {
            // Navigate to the website
            driver.get("https://in.bookmyshow.com/explore/home/chennai");

            // Task 1: Find the image URLs for all the "Recommended Movies"
            List<WebElement> recommendedMovies = driver.findElements(By.xpath("//div[@class='cards cards-grid']/div[contains(@class,'recommended')]"));
            System.out.println("Image URLs for Recommended Movies:");
            for (WebElement movie : recommendedMovies) {
                String imageURL = movie.findElement(By.tagName("img")).getAttribute("src");
                System.out.println(imageURL);
            }

            // Task 2: Print Name & Language of the 2nd item in the "Premiere" list
            WebElement premiereMovie = driver.findElement(By.xpath("//div[@class='cards cards-grid']/div[contains(@class,'premiere')][2]"));
            String movieName = premiereMovie.findElement(By.tagName("h3")).getText();
            String movieLanguage = premiereMovie.findElement(By.xpath(".//span[contains(text(),'Language')]/following-sibling::span")).getText();
            System.out.println("Name of the 2nd Premiere Movie: " + movieName);
            System.out.println("Language of the 2nd Premiere Movie: " + movieLanguage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver in a finally block to ensure it gets closed even if an exception occurs.
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
