package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import page.PracticeForm;
import page.enums.InvalidValues;
import settings.BaseClass;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class PhoneEmailValidationTest extends BaseClass {
    @Tag("negative")
    @ParameterizedTest(name = "вариант телефона/эл.почты: {0}")
    @DisplayName("Проверка длины номера телефона и формата электронной почты")
    @EnumSource(InvalidValues.class)
    void invalidPhoneEmailValidation(InvalidValues values){

        PracticeForm practiceForm = new PracticeForm();
        practiceForm.openPracticeForm()
                .setName("Андрей")
                .setLastName("Дорохин")
                .choiceGender();
        //Проверка телефона и почты
        if (values.name().startsWith("PHONE")){
            practiceForm.setPhoneNumber(values.getValue());
            practiceForm.setEmail("stud-86@mail.ru");
            practiceForm.pressSubmit();
            $("#userNumber:invalid").should(exist);
        } else if(values.name().startsWith("EMAIL")){
            practiceForm.setEmail(values.getValue());
            practiceForm.setPhoneNumber("9003058045");
            practiceForm.pressSubmit();
            $("#userEmail:invalid").should(exist);
        }
    }
}
