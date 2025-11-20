package Lesson11;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;

@Epic("Первый UI-тест")
@Feature("Успешный поиск в Яндекс")

public class SuccesTest {

    @Test
    public void succesSearchTest() {
        openPage();
        setFor();
        checkSearch();
    }
        //Открытие страницы поиска
    @Step("Открыть страницу")
        void openPage() {
            open("https://ya.ru/?npr=1");
        }

        //Ввод поискового запроса
    @Step("Ввод поискового запроса")
    void setFor() {
        $("[name=\"text\"]").click();
        $("[name=\"text\"]").setValue("Selenide").pressEnter();
    }

        //Подтверждение поиска
    @Step("Подтверждение результатво поиска")
    void checkSearch(){
        $("[class=\"HeaderLogo\"]").shouldBe(Condition.visible);
    }
}
