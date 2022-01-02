package co.com.sofka.stepsdefinition.Offers;

import co.com.sofka.models.Customer;
import co.com.sofka.pages.offers.CartPage;
import co.com.sofka.pages.offers.ConfirmBuyPage;
import co.com.sofka.pages.offers.FormPayPage;
import co.com.sofka.pages.offers.ShoppingPag;
import co.com.sofka.setup.ConfiWebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class BuyProductsOfferSteps extends ConfiWebDriver {
    private Customer customerSCE2;
    private int totalFacturado;
    private static  final Logger logger = LogManager.getLogger(BuyProductsOfferSteps.class);

    @Given("el cliente se encuentra en la categoría de ofertas")
    public void elClienteSeEncuentraEnLaCategoriaDeOfertas() {
        try{
            generalSetup();
            ShoppingPag shoppingPag = new ShoppingPag(driver);
            shoppingPag.selectCategoryOffer();
        }catch (Exception e){
            quiteDriver();
            logger.warn("Error al ingresar  categoria de ofertas\n ",e);
            Assertions.fail(e.getMessage(),e);
        }
    }
    @When("el cliente seleccione los productos y los confirme en el carrito")
    public void elClienteSeleccioneLosProductosYLosConfirmeEnElCarrito() {
        try{
            ShoppingPag shoppingPag = new ShoppingPag(driver);
            CartPage cartPage = new CartPage(driver);
            shoppingPag.selectProductsRandom(3);
            cartPage.pressFinalizeBuy();
        }catch (Exception e){
            quiteDriver();
            logger.warn("Error al seleccionar productos\n ",e);
            Assertions.fail(e.getMessage(),e);
        }
    }
    @When("llene la información del formulario de pago y envíe")
    public void lleneLaInformacionDelFormularioDePagoYEnvie() {
        try{
            addAttributesCustomer();
            FormPayPage formPayPage = new FormPayPage(driver, customerSCE2);
            formPayPage.fillFormPay();
            totalFacturado= formPayPage.getTotalFacturado();
        }catch(Exception e){
            quiteDriver();
            logger.warn("error al llenar el formulario\n ",e);
            Assertions.fail(e.getMessage(),e);
        }
    }
    @Then("el sistema dirige a la página del formulario de pago")
    public void elSistemaDirigeALaPaginaDelFormularioDePago(){
        try{
            FormPayPage formPayPage = new FormPayPage(driver, customerSCE2);
            Assertions.assertTrue(formPayPage.isTitleAppear());
        }catch (AssertionError e){
            quiteDriver();
            logger.warn("error, no se direcciona a la página de pago\n",e);
            Assertions.fail(e.getMessage(),e);
        }
    }
    @Then("el sistema debe calcular y mostrar el subtotal de los productos en la página del formulario de pago")
    public void elSistemaDebeCalcularYMostrarElSubtotalDeLosProductosEnLaPaginaDelFormularioDePago() {
        try {
            FormPayPage formPayPage= new FormPayPage(driver, customerSCE2);
            Assertions.assertEquals(formPayPage.subTotalPrice(),formPayPage.getSubTotalPrice());
            logger.info("validación subtotal de productos, la entrada fue <$"+formPayPage.subTotalPrice()+
                    "> se obtuvo <$"+formPayPage.getSubTotalPrice()+">");
            quiteDriver();
        }catch (AssertionError e){
            quiteDriver();
            logger.warn("error, el subtotal es incorrecto \n",e);
            Assertions.fail(e.getMessage(),e);
        }
    }
    @Then("el sistema confirma el envió del pedido")
    public void elSistemaConfirmaElEnvioDelPedido(){
        try{
            ConfirmBuyPage confirmBuyPage = new ConfirmBuyPage(driver);
            Assertions.assertTrue(confirmBuyPage.isDisplayMessageConfirmBuy());
        }catch (AssertionError e){
            quiteDriver();
            logger.warn("no se confirma el envío \n",e);
            Assertions.fail(e.getMessage(),e);
        }
    }
    @Then("el sistema debe mostrar el correspondiente monto total en la factura")
    public void elSistemaDebeMostrarElCorrespondienteMontoTotalEnLaFactura(){
        ConfirmBuyPage confirmBuyPage = new ConfirmBuyPage(driver);
        try{
            int precioFacturaPage = confirmBuyPage.getPrecioTotalFactura();
            Assertions.assertEquals(totalFacturado,precioFacturaPage);
            logger.info("validación del monto total en factura: la entrada fue <$"+totalFacturado+
                    "> se obtuvo <$"+precioFacturaPage+">");
            quiteDriver();
        }catch (AssertionError e){
            quiteDriver();
            logger.warn("Verificación de datos de facturación fallida\n",e);
            Assertions.fail(e.getMessage(),e);
        }
    }

    protected void addAttributesCustomer(){
        customerSCE2 = new Customer();
        customerSCE2.setCedula("8987456");
        customerSCE2.setName("Ernesto");
        customerSCE2.setLastName("Campos");
        customerSCE2.setEmail("ccampos@gmail.com");
        customerSCE2.setAddress("cra 39");
        customerSCE2.setNoAddress("10-40");
        customerSCE2.setCity("Medellin");
        customerSCE2.setRegion("Antioquia");
        customerSCE2.setPhone("3015898445");
    }
}
