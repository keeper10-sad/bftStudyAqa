package settings;

import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.*;

public class BaseClass {

    public static void localDriver(){
        timeout = 3000;
        pageLoadTimeout = 120000;
        browser = "chrome";
        browserSize = "1920x1080";
    }

    @BeforeAll
    public static void setup(){
        localDriver();
    }

}
