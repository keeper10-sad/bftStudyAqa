package test;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import page.PracticeForm;
import page.components.ResultModal;
import settings.BaseClass;

public class SuccesfullSubmitTest extends BaseClass {
    PracticeForm practiceForm = new PracticeForm();

    @Tag("positive")
    @Test
    @DisplayName("Проверка успешного заполнения регистрационной формы")
    public void test(){
        practiceForm.openPracticeForm()
                .setName("Андрей")
                .setLastName("Дорохин")
                .setEmail("stud-86@mail.ru")
                .choiceGender()
                .setPhoneNumber("9003058045")
                .choiceDateBirth("11", "December", "1986")
                .setSubjects("Computer Science")
                .choiseHobbies()
                .downloadPicture("1.jpg")
                .setAdress("Воронеж, 232 Стрелковой дивизии, д.23, кв.81")
                .setState("NCR")
                .setCity("Gurgaon")
                .pressSubmit();

        ResultModal modal = new ResultModal();
        modal.checkTitle()
                .checkValue("Student Name","Андрей Дорохин")
                .checkValue("Student Email","stud-86@mail.ru")
                .checkValue("Gender", practiceForm.getSelectedGender())
                .checkValue("Mobile","9003058045")
                .checkValue("Date of Birth","11 December,1986")
                .checkValue("Subjects","Computer Science")
                .checkValue("Hobbies", practiceForm.getSelectedHobbies())
                .checkValue("Picture","1.jpg")
                .checkValue("Address","Воронеж, 232 Стрелковой дивизии, д.23, кв.81")
                .checkValue("State and City","NCR Gurgaon");

        Allure.step("Скриншот после Submit",
                () -> { Selenide.screenshot("after_submit"); });
    }
}
