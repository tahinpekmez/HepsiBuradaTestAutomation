package HepsiburadaBase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;


public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    Actions actions;
    Configuration configuration;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.actions = new Actions(driver);
        this.configuration = new Configuration();

    }


    public WebElement findElementByXpath(String xpath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement findElementByCSS(String css) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
        return driver.findElement(By.cssSelector(css));
    }

    public WebElement findElementById(String id) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        return driver.findElement(By.id(id));
    }

    public void clickElementByXpath(String xpath) {
        waitUntilJSReady();
        waitClickableWebElementByXpath(xpath);
        highlightElement(findElementByXpath(xpath));
        driver.findElement(By.xpath(xpath)).click();

    }


    public void clickElementById(String id) {
        waitUntilJSReady();
        waitClickableWebElementById(id);
        highlightElement(findElementById(id));
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


    protected void highlightElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 1px dashed red;");
        } catch (Exception e) {
            driver.navigate().refresh();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                    "color: red; border: 1px dashed red;");
        }
    }



    public void scrollToElementByXpath(String xpath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", findElementByXpath(xpath) );
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToElementByCSS(String css) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(css)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", findElementByCSS(css));
    }

    public void scrollToElementByID(String id) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", findElementById(id));
    }


    public void waitUntilJSReady() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }



    // MOVE TO A ELEMENT
    public void hoverOnElementByCSS(String css) {
        waitForLoad(driver);
        WebElement webElement = findElementByCSS(css);
        actions.moveToElement(webElement).build().perform();
    }


    // WAIT METHODS
    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = driver1 -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }


    public void waitClickableWebElementByXpath(String xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void waitClickableWebElementById(String id) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
    }


    public void waitClickableWebElementByCSS(String css) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
    }

    public void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void waitForNewWindow(WebDriver driver, int timeout) {
        try {
            boolean flag = false;
            int counter = 0;

            while (!flag) {

                try {
                    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

                    if (tabs.size() > 1) {
                        return;
                    }

                    sleep(1);

                    counter++;
                    if (counter > timeout) {
                        return;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
            }
        } catch (Exception e) {
            driver.navigate().refresh();

            boolean flag = false;
            int counter = 0;

            while (!flag) {

                try {
                    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                    if (tabs.size() > 1) {
                        flag = true;
                        return;
                    }
                    sleep(1);
                    counter++;
                    if (counter > timeout) {
                        return;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    return;
                }
            }
        }
    }

    protected void switchToTab(int index) {
        try {
            waitForNewWindow(driver, 10);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0)).close();
            driver.switchTo().window(tabs.get(index));
        } catch (Exception e) {
            driver.navigate().refresh();
            waitForNewWindow(driver, 10);
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0)).close();
            driver.switchTo().window(tabs.get(index));
        }

    }

}
