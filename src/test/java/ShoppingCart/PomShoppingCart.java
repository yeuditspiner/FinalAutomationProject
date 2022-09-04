package ShoppingCart;

import Utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PomShoppingCart extends BasePage {
    By byForBuy;
    By byForColor;
    By byForChoosColor;
    By byForChoosSize;
    By byForSize;
    By byForChooseProduct;
    By byForAddToCart;
    By byForToPutInCart;
    By ByForItemInCart;
    By byForBag1;
    By byForPrice1;
    By byForPrice2;
    By byForQty;
    String comp1, comp2, price1, price2;
    //for remove:
    By byForRemove;
    By byForRemoveColor;
    By byForRemoveChoseColor;
    By byForRemoveSize;
    By byForRemoveChooseSize;
    By byForBtnRemoveItem;
    By byForQtyInCart;
    By byForStaleInCart;
    By byForTotal;
    By byForContinueShopping;

    public PomShoppingCart(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        initBys();
    }

    public void initBys() {
        byForBuy = By.cssSelector("#header-big-screen-search-box");
        byForSize = By.cssSelector("[class=\"InStock\"]");
        byForColor = By.cssSelector("[id=\"dk_container_Colour-631503\"]");
        byForChoosColor = By.cssSelector("[class=\"dk_dropdown_option\"]");
        byForChoosSize = By.cssSelector("[class=\"dk_container  dk_theme_default Selector SizeSelector\"]");
        byForChooseProduct = By.cssSelector("[class=\"components__StyledTitleWrapper-eziadi-5 dmenzY\"]");
        byForAddToCart = By.cssSelector("[class=\"nxbtn primary btn-addtobag addToBagCTA Enabled\"]");
        byForBag1 = By.cssSelector("[class=\"header70 header41 header43 cta-Button-sc-1wrc9rk chdGWQ\"]");
        byForToPutInCart = By.cssSelector("[class=\"Title\"]>h1");
        ByForItemInCart = By.cssSelector("[class=\"itemName\"]");
        byForPrice1 = By.cssSelector("[class=\"price\"]");
        byForPrice2 = By.cssSelector("[class=\"nowPrice\"]");
        byForQty = By.cssSelector("[id=\"dk_container_Qty_1\"]");
        byForRemoveColor = By.cssSelector("[id=\"dk_container_Colour-277437\"]");
        byForRemoveChoseColor = By.cssSelector("[class=\"dk_option_current\"]");
        byForRemove = By.cssSelector("[class=\"produc40 produc34 produc36 components__TileTitle-ve5e54-0 iiyOMh produc42 produc63\"]");
        byForRemoveSize = By.cssSelector("[id=\"dk_container_Size-911-969\"]");
        byForRemoveChooseSize = By.cssSelector("[class=\"InStock\"]:nth-child(6)");
        byForBtnRemoveItem = By.cssSelector("[class=\"Hide DeleteButton showText\"]");
        byForQtyInCart = By.cssSelector("[class=\"components__Container-kisnsx-0 caTInU shoppingbag\"]");
        byForStaleInCart = By.cssSelector("#itemCount");
        byForTotal = By.cssSelector("[id=\"title\"]");
        byForContinueShopping = By.cssSelector("[class=\"nxbtn primary GoToCheckout TopLink\"]");
    }

    public void goToPage() {
        getDriver().get("https://www.next.co.il/en");
    }

    public boolean checkAddToCart(String strToBuy) {
        typeInto(strToBuy + "\n", byForBuy);
        click(byForChooseProduct);
        waitUntilVisibilityElementLocated(byForColor);
        click(byForColor);
        waitUntilVisibilityElementLocated(byForChoosColor);
        click(byForChoosColor);
        click(byForChoosSize);
        waitUntilVisibilityElementLocated(byForSize);
        click(byForSize);
        comp1 = getText(byForToPutInCart);
        price2 = getText(byForPrice2);
        click(byForAddToCart);
        waitUntilVisibilityElementLocated(byForBag1);
        click(byForBag1);
        comp2 = getText(ByForItemInCart);
        comp2 = comp2.substring(5, 31);
        price1 = getText(byForPrice1);
        return compare();
    }

    public boolean compare() {
        String smallPrice, bigPrice, totalPrice;
        smallPrice = price2.substring(2, 5);
        bigPrice = price2.substring(10, 13);
        totalPrice = price1.substring(2, 5);

        int sPrice = 0, bPrice = 0, tPrice = 0, qty = 0;
        sPrice = Integer.parseInt(smallPrice);
        bPrice = Integer.parseInt(bigPrice);
        tPrice = Integer.parseInt(totalPrice);
        qty = Integer.parseInt(getText(byForQty));
        takeScreenShot("prScreen/Shopping", "AddToCartImg", getDriver());
        if ((tPrice / qty) >= sPrice && (tPrice / qty) <= bPrice)
            return comp1.equals(comp2);
        return false;
    }

    public boolean checkRemoveFromCart(String strToRemove) {
        typeInto(strToRemove + "\n", byForBuy);
        //the buy process:
        buyProcess();
        //the process to remove:
        waitUntilVisibilityElementLocated(byForRemoveSize);
        click(byForRemoveSize);
        waitUntilVisibilityElementLocated(byForRemoveChooseSize);
        click(byForRemoveChooseSize);
        waitUntilVisibilityElementLocated(byForAddToCart);
        click(byForAddToCart);
        waitUntilVisibilityElementLocated(byForBag1);
        click(byForBag1);
        takeScreenShot("prScreen/Shopping", "BeforeRFromTheCartImg", getDriver());
        waitUntilVisibilityElementLocated(byForStaleInCart);
        int amount = Integer.parseInt(getText(byForStaleInCart));
        waitUntilVisibilityElementLocated(byForBtnRemoveItem);
        click(byForBtnRemoveItem);
         waitUntilVisibilityElementLocated(byForContinueShopping);
        takeScreenShot("prScreen/Shopping", "RemoveingFromTheCartImg", getDriver());
        waitUntilVisibilityElementLocated(byForQtyInCart);
        int qty = Integer.parseInt(getText(byForQtyInCart));
        return (amount - 1 == qty);
    }

    void buyProcess() {
        click(byForRemove);
        waitUntilVisibilityElementLocated(byForRemoveColor);
        click(byForRemoveColor);
        waitUntilVisibilityElementLocated(byForRemoveChoseColor);
        click(byForRemoveChoseColor);
    }
}
