package co.com.sofka.pages.offers;

import co.com.sofka.pages.common.CommonActionOnpages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmBuyPage extends CommonActionOnpages {
    private static final String MENSAJE_ESPERADO_CONFIRMACION = "Gracias. Tu pedido ha sido recibido.";
    @CacheLookup
    @FindBy(xpath = "//*[@id=\"main\"]/div[2]/div/div/div[2]/div/p/strong")
    private WebElement mensajeConfirmacion;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"main\"]/div[2]/div/div/div[2]/div/ul/li[3]/strong/span")
    private WebElement precioFactura;

    //constructor
    public ConfirmBuyPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,ConfirmBuyPage.this);
    }

    public int getPrecioTotalFactura(){
        addWaitByVisibility(precioFactura,15);
        return getTextToInt(precioFactura);
    }

    public boolean isDisplayMessageConfirmBuy(){
        addWaitByVisibility(precioFactura,15);
        return getTex(mensajeConfirmacion).equals(MENSAJE_ESPERADO_CONFIRMACION);
    }
}
