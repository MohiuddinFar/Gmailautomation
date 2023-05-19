package gmailautomation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Gmailautomation {
	private WebDriver driver;
	public void setup() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Initialize ChromeDriver
        driver = new ChromeDriver();
    }
	public void teardown() {
        // Close the browser
        driver.quit();
    }
	public void login(String username, String password) {
        // Navigate to the Gmail homepage
        driver.get("https://www.gmail.com");

        // Enter the username and click "Next"
        WebElement usernameInput = driver.findElement(By.id("identifierId"));
        usernameInput.sendKeys(username);
        driver.findElement(By.id("identifierNext")).click();

        // Enter the password and click "Next"
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(password);
        driver.findElement(By.id("passwordNext")).click();
    }
	public void composeEmail(String subject, String body) {
        // Click on the "Compose" button
        driver.findElement(By.cssSelector("div[role='button'][gh='cm']")).click();

        // Enter the email subject
        WebElement subjectInput = driver.findElement(By.name("subjectbox"));
        subjectInput.sendKeys(subject);

        // Enter the email body
        WebElement bodyInput = driver.findElement(By.cssSelector("div[aria-label='Message Body'][role='textbox']"));
        bodyInput.sendKeys(body);

        // Click on the "Send" button
        driver.findElement(By.cssSelector("div[role='button'][aria-label*='Send']")).click();
    }
	public boolean isEmailSentSuccessfully() {
        // Verify if a success message is displayed after sending the email
        WebElement successMessage = driver.findElement(By.cssSelector("div[role='alert'][aria-live='assertive']"));
        return successMessage.getText().contains("Your message has been sent.");
    }
	public static void main(String[] args) {
		Gmailautomation test = new Gmailautomation();
        test.setup();

        // Replace with your Gmail credentials
        String username = "your_username";
        String password = "your_password";

        test.login(username, password);
        test.composeEmail("Incubyte", "Automation QA test for Incubyte");

        if (test.isEmailSentSuccessfully()) {
            System.out.println("Email sent successfully!");
        } else {
            System.out.println("Failed to send the email.");
        }

        test.teardown();
    }
	
}

