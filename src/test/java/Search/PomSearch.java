package Search;

import Utils.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PomSearch extends BasePage {
    By byForSearch;
    By byForToSearch;
    By byForResults;
    By byForHowManyResults;
    By byForInNew;
    By byForColorFiter;
    By byForFilterByWhiteColor;
    By byForGenderFiter;
    By byForList;
    By byForGenderFiterSelect;
    By byForSortFilter;
    By byForSortBy;
    By byForSortList;
    By byForNewItems;

    public PomSearch(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        initBys();
    }

    private void initBys() {
        byForSearch = By.cssSelector("[id=\"header-big-screen-search-box\"]");
        byForToSearch = By.cssSelector("[title~=\"Camera\"]");
        byForHowManyResults = By.cssSelector("[title~=\"Skirt\"]");
        byForResults = By.cssSelector("[class=\"plp158 components__TotalProductsTitle-sc-1ghi76-5 gPpsyD plp165\"]");
        byForInNew = By.cssSelector("[id=\"plp-facet-checkbox-label-feat:newin\"]");
        byForColorFiter = By.cssSelector("[title=\"Colour\"]");
        byForFilterByWhiteColor = By.cssSelector("[id=\"plp-facet-checkbox-label-colour:white\"]");
        byForList = By.cssSelector("img[alt=\"adidas Juventus White Home Baby Kit (M40488) | ₪ 177\"]:last-of-type");
        byForGenderFiter = By.cssSelector("[class=\"components__ExpansionTitle-e98ok3-1 NzYFh\"]");
        byForGenderFiterSelect = By.cssSelector("[id=\"plp-facet-checkbox-label-gender:newbornboys\"]");
        byForSortFilter = By.cssSelector("[class=\"plp38 plp9 plp11 components__StyledMenuButton-sc-1md1rpu-0 hOjZPQ plp20\"]");
        byForSortBy = By.cssSelector("[class=\"plp38 plp352 plp348 components__StyledMenuItem-sc-13q34d8-0 goqDJD plp349 plp359 plp360\"]:nth-child(5) ");
        byForSortList = By.cssSelector("[class=\"plp53 components__ProductGrid-sc-13l6k0i-2 fqVHxZ plp54\"]   [class=\"produc40 components__WasPrice-sc-1l4gzfe-3 erPOwY produc42\"] > span");
        byForNewItems = By.cssSelector("[class=\"plp53 components__ProductGridItem-sc-13l6k0i-3 iLjPtp plp55 plp93 plp119 plp133 plp146\"] [class=\"produc40 components__NewInLabel-sc-85kmcv-6 bmQmmv produc42\"]");
    }

    public void goToPage() {
        getDriver().get("https://www.next.co.il/en");
    }

    public void enterValues(String strToSearch) {
        typeInto(strToSearch + "\n", byForSearch);
    }

    public boolean isRightLstBySearch(String str) {
        enterValues(str);
        int count = 0;
        List<String> l = new ArrayList<>();
        List<WebElement> lst = findElemnts(byForToSearch);
        lst.forEach((w) -> l.add(w.getAttribute("title").toString()));
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).contains(str))
                count++;
        }
        takeScreenShot("prScreen/Search", "LstSizeImg", getDriver());
        return l.size() == count;
    }

    public List<String> print(List<WebElement> l, List<String> lsource) {
        for (int i = 0; i < l.size(); i++) {
            if (!lsource.contains(l.get(i).getAttribute("title").toString()))
                lsource.add(l.get(i).getAttribute("title").toString());
        }
        return lsource;
    }
    public boolean specificFilter(String s) {
        enterValues(s);

        waitUntilVisibilityElementLocated(byForGenderFiter);
        click(byForGenderFiter);

        waitUntilVisibilityElementLocated(byForGenderFiterSelect);
        click(byForGenderFiterSelect);
        click(byForColorFiter);

        waitUntilVisibilityElementLocated(byForFilterByWhiteColor);
        click(byForFilterByWhiteColor);

        waitUntilVisibilityElementLocated(byForFilterByWhiteColor);
        click(byForFilterByWhiteColor);

        waitUntilVisibilityElementLocated(byForList);
        String strAlt = (findElement(byForList).getAttribute("alt"));
        takeScreenShot("prScreen/Search", "FilterBySpesificImg", getDriver());
        return strAlt.contains("adidas") && strAlt.contains("Baby") && strAlt.contains("White");
    }

    public boolean filterByPrice(String s) {
        enterValues(s);
        click(byForInNew);
        click(byForColorFiter);
        waitUntilVisibilityElementLocated(byForFilterByWhiteColor);
        click(byForFilterByWhiteColor);
        getDriver().manage().window().setSize(new Dimension(1024, 768));
        waitUntilVisibilityElementLocated(byForSortFilter);
        click(byForSortFilter);
        waitUntilVisibilityElementLocated(byForSortBy);
        click(byForSortBy);
        List<WebElement> l = findElemnts(byForSortList);
        clear();
        List<Integer> lst = new ArrayList<>();

        for (int i = 0; i < l.size(); i++) {
            clear();
            try {
                String st = findElemnts(byForSortList).get(i).getText();
                if (st.matches(".*[0-9].*")) {
                    st = st.substring(2, st.length());
                    int price = Integer.parseInt(st.toString());
                    lst.add(price);
                }
            }
            catch (IndexOutOfBoundsException exception){
                System.out.println(exception.toString()+" at page "+getDriver().getCurrentUrl());
            }
        }
        getDriver().manage().window().minimize();
        takeScreenShot("prScreen/Search", "IsSortedByPriceImg", getDriver());
        return checkingSort(lst, 0);
    }

    public boolean checkingSort(List<Integer> l, int index) {
        for (int i = index; i < l.size() - 1; i++) {
            if (!checkList(l, l.get(i), i + 1))
                return false;
        }
        return true;
    }

    public boolean checkList(List<Integer> l, int num, int index) {
        for (int i = index; i < l.size(); i++) {
            if (l.get(i) > num)
                return false;
        }
        return true;
    }

    public boolean filterByIsNew(String s) {
        normal();
        enterValues(s);
        click(byForInNew);

        waitUntilVisibilityElementLocated(byForNewItems);
        List<WebElement> l = findElemnts(byForNewItems);
        for (int i = 0; i < l.size(); i++) {
            if (!l.get(i).getText().equals("NEW IN"))
                return false;
        }
        return true;
    }
}
