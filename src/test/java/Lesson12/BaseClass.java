package Lesson12;

import com.codeborne.selenide.Configuration;

import org.junit.Before;


import static com.codeborne.selenide.Selenide.open;

public class BaseClass {
    public final static String BASE_URL = "https://dzen.ru/?yredirect=true";

    @Before
    public void setUp(){
        Configuration.browser = "chrome";
        open(BASE_URL);
    }
}
