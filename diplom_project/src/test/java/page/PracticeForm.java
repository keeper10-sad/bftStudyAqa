package page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import page.components.Calendar;
import page.components.ResultModal;
import utils.UtilsCollection;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PracticeForm {
    SelenideElement header = $("[class =\"text-center\"]");
    SelenideElement formName = $("[class = \"practice-form-wrapper\"]");
    SelenideElement nameInput = $("[id = \"firstName\"]");
    SelenideElement lastNameInput = $("[id = \"lastName\"]");
    SelenideElement emailInput = $("[id = \"userEmail\"]");
    SelenideElement maleRadio = $("label[for='gender-radio-1']");
    SelenideElement femaleRadio = $("label[for='gender-radio-2']");
    SelenideElement otherRadio = $("label[for='gender-radio-3']");
    SelenideElement mobileInput = $("[id= \"userNumber\"]");
    SelenideElement birthInput = $("[id=\"dateOfBirthInput\"]");
    SelenideElement subjectsInput = $("[id = \"subjectsInput\"]");
    SelenideElement hobbiesSports = $("label[for='hobbies-checkbox-1']");
    SelenideElement hobbiesReading = $("label[for='hobbies-checkbox-2']");
    SelenideElement hobbiesMusic = $("label[for='hobbies-checkbox-3']");
    SelenideElement pictureDownload = $("[id = \"uploadPicture\"]");
    SelenideElement adressInput = $("[id = \"currentAddress\"]");
    SelenideElement stateInput = $("[id = \"react-select-3-input\"]");
    SelenideElement cityInput = $("[id = \"react-select-4-input\"]");
    SelenideElement submitButton = $("[id = \"submit\"]");

    private String selectedGender;
    private List<String> selectedHobbies;

    Calendar calendar = new Calendar();

    @Step("Открываем сайт")
    public PracticeForm openPracticeForm(){
        Selenide.open("https://demoqa.com/automation-practice-form");
        header.shouldBe(visible);
        formName.shouldHave(text("Student Registration Form"));
        return this;
    }

    @Step("Вводим Имя")
    public PracticeForm setName(String value){
        nameInput.setValue(value);
        return this;
    }

    @Step("Вводим фамилию")
    public PracticeForm setLastName(String value){
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Вводим email")
    public PracticeForm setEmail(String value){
        emailInput.setValue(value);
        return this;
    }

    @Step("Выбор пола")
    public PracticeForm choiceGender(){
        selectedGender = UtilsCollection.chooseGender();
        return this;
    }
    @Step("Получаем выбранный пол")
    public String getSelectedGender() {
        return selectedGender;
    }

    @Step("Вводим телефон")
    public PracticeForm setPhoneNumber(String value){
        mobileInput.setValue(value);
        return this;
    }

    @Step("Выбираем дату рождения")
    public PracticeForm choiceDateBirth(String day, String month, String year){
        birthInput.click();
        calendar.choiceDate(day, month, year);
        return this;
    }

    @Step("Получаем дату рождения")
    public String getBirthDate(){
        return birthInput.getValue();
    }

    @Step("Выбираем направление")
    public PracticeForm setSubjects(String value){
        subjectsInput
                .setValue(value)
                .pressEnter();
        return this;
    }

    @Step("Выбираем увлечение")
    public PracticeForm choiseHobbies(){
        selectedHobbies = UtilsCollection.chooseHobbies();
        return this;
    }
    @Step("Получаем выбранные хобби")
    public String getSelectedHobbies() {
        return String.join(", ", selectedHobbies);
    }

    @Step("Загружаем фото")
    public PracticeForm downloadPicture(String fileName){
        pictureDownload.uploadFromClasspath(fileName);
        return this;
    }

    @Step("Вводим адрес проживания")
    public PracticeForm setAdress(String value){
        adressInput.setValue(value);
        return this;
    }

    @Step("Выбираем штат")
    public PracticeForm setState(String state){
        stateInput
                .setValue(state)
                .pressEnter();
        return this;
    }

    @Step("Выбираем город")
    public PracticeForm setCity(String city){
       cityInput
        .setValue(city)
        .pressEnter();
        return this;
    }

    @Step("Завершаем регистрацию")
    public ResultModal pressSubmit(){
        submitButton.scrollIntoView(true)
                .click();
        return new ResultModal();
    }
}
