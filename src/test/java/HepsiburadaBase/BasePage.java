package HepsiburadaBase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);

    }


    public void clickElementByXpath(String xpath) {
        waitUntilJSReady();
        waitClickableWebElementByXpath(xpath);
        scrollToElementByXpath(xpath);
        driver.findElement(By.xpath(xpath)).click();

    }


    public void clickElementById(String id) {
        waitUntilJSReady();
        waitClickableWebElementById(id);
        scrollToElementByID(id);
        driver.findElement(By.id(id)).click();
    }

    public void fillInTheBlankById(String id, String keys) {
        waitUntilJSReady();
        scrollToElementByID(id);
        driver.findElement(By.id(id)).sendKeys(keys);
    }

    public void fillInTheBlankByXpath(String xpath, String keys) {
        waitUntilJSReady();
        scrollToElementByXpath(xpath);
        driver.findElement(By.xpath(xpath)).sendKeys(keys);
    }



    public void scrollToElementByXpath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToElementByCSS(String css) {
        WebElement element = driver.findElement(By.cssSelector(css));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToElementByID(String id) {
        WebElement element = driver.findElement(By.id(id));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }



    public void waitUntilJSReady() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }



    // MOVE TO A ELEMENT
    public void hoverOnElementByCSS(String css) {
        waitPageLoad();
        WebElement element = driver.findElement(By.cssSelector(css));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }


    // WAIT METHODS
    public void waitDOM() {
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }


    public void waitPageLoad() {
        waitDOM();
    }


    public void waitClickableWebElementByXpath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitClickableWebElementById(String id) {
        WebElement element = driver.findElement(By.id(id));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }



}
