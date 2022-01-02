package co.com.sofka.pages.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CommonActionOnpages {
    private final WebDriver driver;

    public CommonActionOnpages(WebDriver driver) {
        this.driver = driver;
    }

    protected void addWaitByVisibility(By locator, int wait){
        WebDriverWait await = new WebDriverWait(driver,wait);
        await.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected void addWaitByVisibility(WebElement webElement, int wait){
        WebDriverWait await = new WebDriverWait(driver,wait);
        await.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected  void addWaitByVisibilityAllElement(By locator, int wait){
        WebDriverWait await = new WebDriverWait(driver,wait);
        await.until((ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
    }

    protected  void addWaitByPresentDom(By locator, int wait){
        WebDriverWait await = new WebDriverWait(driver,wait);
        await.until((ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(locator)))));
    }
    //accion
    protected void typeInto(By locator, String value){
        driver.findElement(locator).sendKeys(value);
    }

    protected void typeInto(WebElement webElement, String value){
        webElement.sendKeys(value);
    }

    protected void click(By locator){driver.findElement(locator).click();}

    protected void click(WebElement webElement){webElement.click();}

    protected  void repeatClick(By locator, int repeats){
        for(int i = 0;i<repeats;i++){
            click(locator);
        }
    }
    protected  void repeatClick(WebElement webElement, int repeats){
        for(int i = 0;i<repeats;i++){
            click(webElement);
        }
    }
    protected void clickWithAccion(By locator){
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    protected  void pressEnter(By locator){driver.findElement(locator).sendKeys(Keys.ENTER);}

    public void scrollTo(By locator){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(locator));
    }
    public void scrollTo(WebElement webElement){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView();", webElement);
    }
    public String getAtributeElement(By locator, String atribute){
        return driver.findElement(locator).getAttribute(atribute);
    }

    protected String getTex(By locator){
        return driver.findElement(locator).getText();
    }

    protected String getTex(WebElement webElement){
        return webElement.getText();
    }

    protected int getTextToInt(WebElement webElement){
        scrollTo(webElement);
        return Integer.parseInt(getTex(webElement).replaceAll("[-+.^:,$]",
                ""));
    }
    protected int getTextToInt(By locator){
        scrollTo(locator);
        return Integer.parseInt(getTex(locator).replaceAll("[-+.^:,$]",
                ""));
    }
    protected ArrayList<String[]> getTextElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        ArrayList<String[]> elementsText = new ArrayList<>();
        for(WebElement webElement: elements){
            String [] detalle = webElement.getText().split("\\n");
            elementsText.add(detalle);
        }
        return elementsText;
    }
    protected ArrayList<String> getTextElementsUnic(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        ArrayList<String> elementsText = new ArrayList<>();
        for(WebElement webElement: elements){
            String detalle = getTex(webElement);
            elementsText.add(detalle);
        }
        return elementsText;
    }
}
