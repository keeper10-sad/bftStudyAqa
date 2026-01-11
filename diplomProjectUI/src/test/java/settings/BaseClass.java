package settings;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseClass {

    private static final String ALLURE_RESULTS_DIR = "allure-results";

    public static void localDriver(){
        timeout = 4000;
        browser = "chrome";
        browserSize = "1920x1080";
        pageLoadStrategy = "eager";
    }

    @BeforeAll
    public static void setup(){
        cleanAllureResults();
        localDriver();
    }
    @AfterEach
    public void addScrollableScreenshots(TestInfo testInfo) {
        takeScrollScreenshots("Screenshot_" + testInfo.getDisplayName());
    }

    public static void takeScrollScreenshots(String name) {
        try {
            WebDriver driver = getWebDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // общая высота страницы
            long pageHeight = (long) js.executeScript(
                    "return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight);"
            );

            int viewportHeight = ((Number) js.executeScript("return window.innerHeight;")).intValue();

            int scrollY = 0;
            int index = 1;

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH-mm-ss");

            while (scrollY < pageHeight) {
                js.executeScript("window.scrollTo(0, arguments[0]);", scrollY);
                Thread.sleep(300); // даём странице прогрузиться

                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                String screenshotName = String.format("%s_%s_part_%d",
                        name,
                        LocalDateTime.now().format(dtf),
                        index++
                );

                Allure.addAttachment(screenshotName, new ByteArrayInputStream(screenshot));

                scrollY += viewportHeight;
            }

            // возвращаемся наверх
            js.executeScript("window.scrollTo(0, 0);");

            // Логируем шаг в Allure
            Allure.step("Скриншоты страницы для теста: " + name);

        } catch (Exception e) {
            System.out.println("Ошибка при scroll-скриншотах: " + e.getMessage());
        }
    }
    private static void cleanAllureResults() {
        try {
            File dir = new File(ALLURE_RESULTS_DIR);

            if (!dir.exists()) {
                return;
            }

            Files.walk(dir.toPath())
                    .map(java.nio.file.Path::toFile)
                    .sorted((a, b) -> -a.compareTo(b)) // сначала файлы, потом папки
                    .forEach(File::delete);

        } catch (Exception e) {
            System.out.println("Не удалось очистить allure-results: " + e.getMessage());
        }
    }
}
