package co.com.sofka.pages.offers;

import co.com.sofka.pages.common.CommonActionOnpages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends CommonActionOnpages {

    //localizadores de entrada
    @CacheLookup
    @FindBy(xpath = "//a[@class=\"checkout-button button alt wc-forward\" ]")
    private WebElement btnFinalizarCompra;
    //constructor
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,CartPage.this);
    }
    public void pressFinalizeBuy(){
        addWaitByVisibility(btnFinalizarCompra,10);//se espera a que se agregen todos los productos
        scrollTo(btnFinalizarCompra);
        click(btnFinalizarCompra);
    }
}
