package Lesson12;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage extends BaseClass {
    public final SelenideElement searchBar = $("form[action = 'https://yandex.ru/search/']");
    public final SelenideElement searchButton = $("button[class = 'arrow__button']");
    public final SelenideElement inframeSearch = $("iframe[id^='ya-search-iframe']");
    public final SelenideElement logoYandex = $("iframe[id^='ya-search-iframe']");
}
