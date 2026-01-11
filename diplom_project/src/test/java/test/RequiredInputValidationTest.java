package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import page.PracticeForm;
import page.enums.RequiredInput;
import settings.BaseClass;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RequiredInputValidationTest extends BaseClass {
    @Tag("negative")
    @ParameterizedTest(name = "обязательное поле: {0}")// проверка начинается с первого поля
    @DisplayName("Проверка валидации заполнения обязательных полей")
    @EnumSource(RequiredInput.class)
    void requiredInputValidation(RequiredInput input){
        PracticeForm practiceForm = new PracticeForm();
        practiceForm.openPracticeForm();

        // Если не проверяем поле на обязательность заполнения, то заполняем его.
        // Поочереди проверяем обязательность заполнения поля
        // Одно поле всегда остается пустым, остальные заполнены
        if (input != RequiredInput.FIRST_NAME) {
            practiceForm.setName("Андрей");
        }
        if (input != RequiredInput.LAST_NAME) {
            practiceForm.setLastName("Дорохин");
        }
        if (input != RequiredInput.GENDER) {
            practiceForm.choiceGender();
        }
        if (input != RequiredInput.MOBILE) {
            practiceForm.setPhoneNumber("9003058045");
        }

        practiceForm.pressSubmit();

        switch (input){
            case FIRST_NAME:$("#firstName:invalid").should(exist);
            break;

            case LAST_NAME:$("#lastName:invalid").should(exist);
            break;

            case MOBILE:$("#userNumber:invalid").should(exist);
            break;

            case GENDER:$("[id = \"example-modal-sizes-title-lg\"]").shouldNotBe(visible);
            break;
        }
    }
}
