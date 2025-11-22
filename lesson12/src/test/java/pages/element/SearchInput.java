package pages.element;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class SearchInput {

    public SearchInput(SelenideElement element) {
        this.element = element;
    }

    private final SelenideElement element;

    public SearchInput search(){
        element.shouldBe(Condition.visible);
        element.click();
        element.setValue("Selenide");
        return this;
    }


}
