package utils;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$;

public class UtilsCollection {
    private static final Random random = new Random();

    // Локаторы и имена элементов
    public static final SelenideElement[] GENDER_ELEMENTS = {
            $("label[for='gender-radio-1']"),
            $("label[for='gender-radio-2']"),
            $("label[for='gender-radio-3']")
    };
    public static final String[] GENDER_NAMES = {"Male", "Female", "Other"};

    public static final SelenideElement[] HOBBIES_ELEMENTS = {
            $("label[for='hobbies-checkbox-1']"),
            $("label[for='hobbies-checkbox-2']"),
            $("label[for='hobbies-checkbox-3']")
    };
    public static final String[] HOBBIES_NAMES = {"Sports", "Reading", "Music"};

    @Step("Выбираем случайный пол")
    public static String chooseGender(){
        int index = random.nextInt(GENDER_ELEMENTS.length);
        GENDER_ELEMENTS[index].click();
        Allure.step("Выбран пол: " + GENDER_NAMES[index]);
        return GENDER_NAMES[index];
    }

    @Step("Выбираем случайное хобби")
    public static List<String> chooseHobbies(){
        List<String> selected = new ArrayList<>();
        boolean picked = false;

        for (int i = 0; i < HOBBIES_ELEMENTS.length; i++) {
            if (random.nextBoolean()) {
                HOBBIES_ELEMENTS[i].click();
                selected.add(HOBBIES_NAMES[i]);
                picked = true;
            }
        }
        if (!picked) {
            int idx = random.nextInt(HOBBIES_ELEMENTS.length);
            HOBBIES_ELEMENTS[idx].click();
            selected.add(HOBBIES_NAMES[idx]);
        }
        Allure.step("Выбраны хобби: " + selected);
        return selected;
    }
}
