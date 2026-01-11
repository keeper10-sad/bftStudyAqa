package page.components;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Calendar {
    public void choiceDate(String day, String month, String year){
        $("[class = \"react-datepicker__year-select\"]").selectOption(year);
        $("[class = \"react-datepicker__month-select\"]").selectOption(month);
//        $("[class = \"react-datepicker__day react-datepicker__day--0" + day + "\"]").click();
        try {
            int dayInt = Integer.parseInt(day);
            $$(".react-datepicker__day")
                    .exclude(Condition.cssClass("react-datepicker__day--outside-month"))
                    .findBy(Condition.text(dayInt + ""))
                    .shouldBe(Condition.visible)
                    .click();
        } catch (com.codeborne.selenide.ex.ElementNotFound e) {
            throw new RuntimeException("Некорректная дата: " + day + " " + month + " " + year);
        }
    }
}
