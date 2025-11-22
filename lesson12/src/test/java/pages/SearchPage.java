package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.element.SearchInput;
import settings.BaseClass;

import static com.codeborne.selenide.Selenide.$;

public class SearchPage extends BaseClass {
    SelenideElement searchBar = $("[name=\"text\"]");
    SelenideElement searchResult = $("[class=\"HeaderLogo\"]");
    SelenideElement searchButton = $("[type=\"submit\"]");

    private final SearchInput userSearch = new SearchInput($("[name=\"text\"]"));

    @Step("Открываем страницу поиска")
    public SearchPage openSearchPage(){
        Selenide.open("https://ya.ru/?npr=1");
        return this;
    }

    @Step ("Вводим поисковый запрос")
    public SearchPage setSearchRequest(String request){
        userSearch.search();
//        searchBar.shouldBe(Condition.visible);
//        searchBar.click();
//        searchBar.setValue(request);
//       searchBar.pressEnter();
        return this;
    }

    @Step("Нажимаем кнопку поиска")
    public SearchPage pressSearchButton(){
        searchButton.click();
        return this;
    }

    @Step("Проверяем результат поиска")
    public SearchPage checkSearch(){
        searchResult.shouldBe(Condition.visible);
        return this;
    }

}
