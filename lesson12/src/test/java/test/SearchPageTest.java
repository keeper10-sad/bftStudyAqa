package test;

import org.junit.Test;
import pages.SearchPage;
import settings.BaseClass;

public class SearchPageTest extends BaseClass {
    SearchPage searchPage = new SearchPage();

    @Test
    public void test(){
        searchPage.openSearchPage()
                .setSearchRequest("Selenide")
                .pressSearchButton()
                .checkSearch();
    }
}
