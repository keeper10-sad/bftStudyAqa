package page.components;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import settings.BaseClass;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ResultModal extends BaseClass {
    SelenideElement modalTitle = $("[id = \"example-modal-sizes-title-lg\"]");
    SelenideElement modalTable = $("[class = \"table-responsive\"]");

    @Step("Проверяем название модалки")
    public ResultModal checkTitle(){
        modalTitle
                .should(appear)
                .shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    @Step("Проверяем значения полей")
    public ResultModal checkValue(String key, String value){
        modalTable.$$("tr")
                .findBy(text(key))
                .shouldHave(text(value));
        return this;
    }
}
