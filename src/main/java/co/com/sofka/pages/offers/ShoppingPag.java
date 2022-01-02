package co.com.sofka.pages.offers;
import co.com.sofka.pages.common.CommonActionOnpages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class ShoppingPag  extends CommonActionOnpages {
    //localizadores de entrada
    @CacheLookup
    @FindBy(xpath="//*[@id=\"mega-menu-item-198556\"]/a")
    private WebElement menuOfertas;

    @CacheLookup
    @FindBy(xpath = "//option[@value=\"price\"]")
    private WebElement filtroPrecioBajo;

    @CacheLookup
    @FindBy(xpath="//*[@name=\"orderby\" and @aria-label=\"Pedido de la tienda\"]")
    private WebElement filtroProductos;

    private static final By btnAgregarCarrito = By.xpath("//*[@class=\"add-to-cart-button\"]");


    public ShoppingPag(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,ShoppingPag.this);
    }
    public void selectCategoryOffer(){
        addWaitByVisibility(menuOfertas,6);
        repeatClick(menuOfertas,2);
    }
    public void selectProductsRandom(int cant){

            addWaitByVisibility(filtroProductos,10);
            click(filtroProductos);
            addWaitByVisibility(filtroPrecioBajo,2);
            click(filtroPrecioBajo);

            ArrayList<String> listProduct;
            listProduct = getTextElementsUnic(btnAgregarCarrito);//lista de botones disponibles para en viar al carrito
            Random random = new Random();
            //solo se seleccionara los botones para añadir directamente los productos
            ArrayList<Integer> indexProductFilter = new ArrayList<>();
            for (int i = 0; i < listProduct.size(); i++) {
                if (Objects.equals(listProduct.get(i), "AÑADIR AL CARRITO")) {
                    indexProductFilter.add(i + 1);
                }
            }
            //presionar productos uno a uno, según la cantidad
            for (int i = 0; i < cant; i++) {
                int index = indexProductFilter.get(random.nextInt(indexProductFilter.size()));
                String position = "//*[@id=\"main\"]/div/div[2]/div/div[2]/div[" + index + "]/div/div[2]/div[2]/div[3]";
                indexProductFilter.remove((Integer) index);
                scrollTo(By.xpath(position));
                addWaitByVisibility(By.xpath(position), 8);
                click(By.xpath(position));
                String afterClick="//*[@id=\"main\"]/div/div[2]/div/div[2]/div["+index+"]/div/div[2]/div[2]/div[3]/a[2]";
                addWaitByVisibility(By.xpath(afterClick),8);
                if(i==cant-1){
                    click(By.xpath(afterClick));
                }
            }
    }
}
