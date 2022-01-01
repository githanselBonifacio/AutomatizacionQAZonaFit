package co.com.sofka.pages.offers;

import co.com.sofka.models.Customer;
import co.com.sofka.pages.common.CommonActionOnpages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class FormPayPage extends CommonActionOnpages {
    private final Customer customer;
    private static final String TITULO_FORMULARIO = "FACTURACIÓN Y ENVÍO";

    //localizadores de entrada
    private static final By precioProducto = By.xpath("//td[@class=\"product-total\"]/span");

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"customer_details\"]/div[1]/div/h3")
    private WebElement tituloDetallesPago;

    @CacheLookup
    @FindBy(xpath = "//tr[@class=\"cart-subtotal\"]/td/span")
    private WebElement precioSubTotal;

    //formulario
    @CacheLookup
    @FindBy(id = "billing_myfield12")
    private WebElement cedula;

    @CacheLookup
    @FindBy(id = "billing_email")
    private WebElement email;

    @CacheLookup
    @FindBy(id = "billing_first_name")
    private WebElement nombre;

    @CacheLookup
    @FindBy(id = "billing_last_name")
    private WebElement apellido;

    private static final By region = By.id("select2-billing_state-container");
    private static final By municipio = By.id("select2-billing_city-container");
    private static final By cajaTextoRegionMunicipio = By.xpath("/html/body/span/span/span[1]/input");

    @CacheLookup
    @FindBy(id = "billing_address_1")
    private WebElement direccion;

    @CacheLookup
    @FindBy(id = "billing_address_2")
    private WebElement noDireccion;

    @CacheLookup
    @FindBy(id = "billing_phone")
    private WebElement telefono;

    @CacheLookup
    @FindBy(xpath = "//tr[@class=\"order-total\"]/td")
    private WebElement precioTotalFacturado;

    private static final By pagoBaloto = By.xpath("//label[@for=\"payment_method_bank_transfer_1\"]");
    private static final By radioButtonBaloto = By.id("payment_method_bank_transfer_1");
    private static final By contenedorTerminosCondiciones = By.xpath("//*[@id=\"payment\"]/div/div/p");
    private static final By checkBoxTerminos = By.id("terms");
    private static final By btnRealizarPedido = By.id("place_order");

    public FormPayPage(WebDriver driver, Customer customer){
        super(driver);
        this.customer=customer;
        PageFactory.initElements(driver,FormPayPage.this);
    }

    public void fillFormPay () {

            addWaitByVisibility(cedula, 15);
            scrollTo(cedula);
            typeInto(cedula, customer.getCedula());

            typeInto(email, customer.getEmail());

            typeInto(nombre, customer.getName());

            typeInto(apellido, customer.getLastName());

            scrollTo(region);
            click(region);
            addWaitByVisibility(cajaTextoRegionMunicipio,5);
            typeInto(cajaTextoRegionMunicipio, customer.getRegion());
            pressEnter(cajaTextoRegionMunicipio);

            click(municipio);
            addWaitByVisibility(cajaTextoRegionMunicipio,5);
            typeInto(cajaTextoRegionMunicipio, customer.getCity());
            pressEnter(cajaTextoRegionMunicipio);

            typeInto(direccion, customer.getAddress());

            typeInto(noDireccion, customer.getNoAddress());

            typeInto(telefono, customer.getPhone());

            while (getAtributeElement(radioButtonBaloto,"checked")==null){
                addWaitByVisibility(pagoBaloto,4);
                scrollTo(pagoBaloto);
                clickWithAccion(pagoBaloto);
           }


            while(getAtributeElement(contenedorTerminosCondiciones,"class")
                  .equals("form-row validate-required")) {
                addWaitByVisibility(checkBoxTerminos,4);
                scrollTo(contenedorTerminosCondiciones);
                clickWithAccion(checkBoxTerminos);
            }
            scrollTo(btnRealizarPedido);
            addwaitBeClickable(btnRealizarPedido,5);
            clickWithAccion(btnRealizarPedido);
    }

    public Boolean isTitleAppear() {
        addWaitByVisibility(tituloDetallesPago,10);
        String titlePage = getTex(tituloDetallesPago);
        return titlePage.contains(TITULO_FORMULARIO);
    }

    public int subTotalPrice(){
        ArrayList<String> listPriceFull = getTextElementsUnic(precioProducto);
        int subTotalPrice =0;
        for (String product : listPriceFull) {
            if (product.length() > 0) {
                int i = Integer.parseInt(product.replaceAll("[-+.^:,$]",
                        ""));
                subTotalPrice += i;
            }
        }
        return subTotalPrice;
    }

    public int getSubTotalPrice(){
       return getTextToInt(precioSubTotal);
    }

    public  int getTotalFacturado(){
        return getTextToInt(precioTotalFacturado);
    }
}
