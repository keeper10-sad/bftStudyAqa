package Lesson12;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;

public class SearchPageTest extends BaseClass {

    SearchPage searchPage = new SearchPage();

    //Нахождение поисковой строки и клик по ней
    @Test
    @Step("ввод поискового запроса")
    public void findSearchBar() {
        searchPage.searchBar.click();
        actions().sendKeys("Selenide").perform();
        switchTo().frame($("iframe[id^='ya-search-iframe']"));
        searchPage.searchButton.click();
        switchTo().defaultContent();
        searchPage.logoYandex.should(Condition.visible);
    }
}
