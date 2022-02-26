import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.rshb.ru/");
        

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        WebElement loginFormButton = driver.findElement(By.id("ibankouter"));
        loginFormButton.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.id("login-tabs")));

        if(driver.getTitle().equalsIgnoreCase("Интернет-банк РСХБ") && (driver.getCurrentUrl().equalsIgnoreCase("https://online.rshb.ru/b1/ib6/wf2/retail/ib/loginretaildefault"))) {
            System.out.println("log: form loaded");

            TimeUnit.SECONDS.sleep(2);

            WebElement loginField = driver.findElement(By.id("textfield"));
            loginField.sendKeys("тест");

            TimeUnit.SECONDS.sleep(2);

            WebElement passwordField = driver.findElement(By.id("passwordfield"));
            passwordField.sendKeys("тест");

            TimeUnit.SECONDS.sleep(2);

            WebElement showPassword = driver.findElement(By.xpath("//img[@title= 'Показать пароль']"));
            showPassword.click();

            TimeUnit.SECONDS.sleep(2);

            if(loginField.getAttribute("value").equalsIgnoreCase("тест") && passwordField.getAttribute("value").equalsIgnoreCase("тест")) {
                WebElement CheckBoxForm = driver.findElement(By.id("rememberLogin"));
                CheckBoxForm.click();

                TimeUnit.SECONDS.sleep(2);

                WebElement loginSendButton = driver.findElement(By.className("ib-button-text"));
                loginSendButton.click();

                TimeUnit.SECONDS.sleep(2);

                WebElement errorForm = driver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[1]/ul/li"));

                if(errorForm.isDisplayed() && errorForm.getText().equalsIgnoreCase("Логин или пароль введены неверно.")) {
                    System.out.println("log: success");
                }
            }
        }

        TimeUnit.SECONDS.sleep(5);

        driver.quit();
    }
}
