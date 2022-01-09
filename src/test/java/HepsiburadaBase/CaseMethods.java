package HepsiburadaBase;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class CaseMethods extends BasePage{

    public CaseMethods(WebDriver driver){
        super(driver);
    }


    public void login() {

        driver.get(configuration.getProperty("mainPage"));
        waitForLoad(driver);
        Assert.assertEquals(driver.getCurrentUrl(), configuration.getProperty("mainPage"));
        hoverOnElementByCSS("#myAccount > span > span.sf-OldMyAccount-PhY-T");
        clickElementById("login");
        fillInTheBlankById("txtUserName", configuration.getProperty("userMail"));
        clickElementById("btnLogin");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtPassword")));
        fillInTheBlankById("txtPassword", configuration.getProperty("password"));
        clickElementById("btnEmailSelect");
        String userName = findElementByXpath("//*[@title='Hesabım']/span[2]").getText();
        Assert.assertEquals(userName, "Fatih Burak Pehlivan");
    }

    public void searchProductAndCommentDetail(){
        fillInTheBlankByXpath("//*[@class='desktopOldAutosuggestTheme-input']", "iphone");
        clickElementByXpath("//*[@class='SearchBoxOld-buttonContainer']");
        clickElementByXpath("//*[@data-test-id='product-card-name'][text() = 'iPhone 13 128 GB' ]");
        switchToTab(1);
        scrollToElementByID("productReviewsTab");
        clickElementById("productReviewsTab");
        System.out.println(findElementById("productReviewsTab").getText());
        if(findElementById("productReviewsTab").getText() == "Değerlendirmeler (0)"){
            driver.get(configuration.getProperty("mainPage"));
            waitForLoad(driver);
            Assert.assertEquals(driver.getCurrentUrl(), configuration.getProperty("mainPage"));
        }
        clickElementByXpath("//div[@class='paginationOverlay']/div/div[1]//*[@class='hermes-ReviewCard-module-tAGUS']");
        String stringThanks = driver.findElement(By.xpath("//div[@class='paginationOverlay']/div/div[1]//*[@class='hermes-ReviewCard-module-1ZiTv']")).getText();
        System.out.println(stringThanks);
        Assert.assertEquals(stringThanks, "Teşekkür Ederiz.");
    }

}
