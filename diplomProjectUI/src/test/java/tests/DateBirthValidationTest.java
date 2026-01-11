package test;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import page.PracticeForm;
import page.enums.DateValues;
import settings.BaseClass;

import static org.assertj.core.api.Assertions.assertThat;


public class DateBirthValidationTest extends BaseClass {
    @ParameterizedTest(name = "вариант даты рождения:  {0}")
    @DisplayName("Проверка валидации даты рождения")
    @EnumSource(DateValues.class)
    void birtDateValidation(DateValues dateValues){
        PracticeForm practiceForm = new PracticeForm();
        practiceForm.openPracticeForm();

        String before = practiceForm.getBirthDate();

        // Логируется дату, которую подставляю
        Allure.step("Пробуем подставить дату: " +
                dateValues.getDay() + "." +
                dateValues.getMonth() + "." +
                dateValues.getYear());

        if(dateValues.isPositive()){
            Allure.step("Позитивный сценарий: выбираем дату", () -> {
                practiceForm.choiceDateBirth(
                        dateValues.getDay(),
                        dateValues.getMonth(),
                        dateValues.getYear()
                );
                assertThat(practiceForm.getBirthDate())
                        .as("Дата подставлена")
                        .isEqualTo(dateValues.getExpected());
            });
        }else {
            Allure.step("Негативный сценарий: выбираем дату", () -> {
                try{
                    practiceForm.choiceDateBirth(
                            dateValues.getDay(),
                            dateValues.getMonth(),
                            dateValues.getYear()
                    );
                    System.out.println("Негативный сценарий не сработал.");
                } catch (RuntimeException e) {
                    System.out.println("негативный сценарий отработал: " + e.getMessage());
                    assertThat(practiceForm.getBirthDate()).isEqualTo(before);
                }
            });
        }
    }
}
