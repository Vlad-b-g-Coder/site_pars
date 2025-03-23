package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        for (int i = 0; i < 1; i++) {
            final int instanceNumber = i + 1;
            executor.execute(() -> {
                try {
                    runBrowserTask(instanceNumber);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown();
    }

    private static void runBrowserTask(int instanceNumber) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");

        String userProfile = "C:\\Users\\YourUsername\\AppData\\Local\\Google\\Chrome1\\User Data" + instanceNumber;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=" + userProfile);
        options.addArguments("--window-size=375,812");
        options.addArguments("--user-agent=Mozilla/5.0 (Linux; Android 10; Mi 10 Pro Build/QKQ1.191117.002) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Mobile Safari/537.36");
        options.addArguments("--auto-open-devtools-for-tabs");
        WebDriver webdriver = new ChromeDriver(options);
        webdriver.get("file:///D:/Virtus/Virtus.html");
        while (true) {
            WebDriverWait wait = new WebDriverWait(webdriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlToBe("file:///D:/Virtus/Virtus.html"));

            System.out.println("Сайт загружен для экземпляра №" + instanceNumber);
            WebElement iframeElement = webdriver.findElement(By.cssSelector("iframe.zA1w1IOq"));
            webdriver.switchTo().frame(iframeElement);
            WebDriverWait wait1 = new WebDriverWait(webdriver, Duration.ofSeconds(10000)); // ждем до 10 секунд

            WebElement linkElement = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a._contentCon_shqa4_18[href=\"#/game\"]")));

            linkElement.click();
            Thread.sleep(100);
            WebElement collectButton = webdriver.findElement(By.cssSelector("div._wrapper_c86a2_1 > div > p._text_c86a2_17"));
            collectButton.click();
            Thread.sleep(1000);
            webdriver.navigate().refresh();
            Thread.sleep(2 * 60 * 60 * 1000);
        }
    }

    private static void loginAndSaveCookies(WebDriver driver, int instanceNumber) throws InterruptedException {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
        }catch (NoSuchElementException e){}
    }
}