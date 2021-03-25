package HepsiburadaBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CaseMethods extends BasePage{

    Constants constants = new Constants();

    public CaseMethods(WebDriver driver){
        super(driver);
    }

    public void loginAndQuit() {

        driver.get(constants.getMainPage());
        waitPageLoad();
        if (driver.getCurrentUrl().equals("https://www.hepsiburada.com/")) {
            Assert.assertTrue(true);
        }
        hoverOnElementByCSS("#myAccount > span > span.sf-OldMyAccount-PhY-T");
        clickElementById("login");
        fillInTheBlankById("txtUserName", constants.getMailAddress());
        fillInTheBlankById("txtPassword", constants.getPassword());
        clickElementById("btnLogin");
        waitPageLoad();
        String myNameAndSurname = driver.findElement(By.xpath("//*[@title='Hesabım']/span[2]")).getText();
        Assert.assertEquals(myNameAndSurname, "murat ersoy");
        hoverOnElementByCSS("#myAccount > span > a > span.sf-OldMyAccount-PhY-T");
        driver.findElement(By.cssSelector("#myAccount > div > div.sf-OldMyAccount-32BWo > ul > li:nth-child(9) > a")).click();
        waitPageLoad();
        scrollToElementByCSS("#myAccount > span > span.sf-OldMyAccount-PhY-T");
    }

    public void login() {

        driver.get(constants.getMainPage());
        waitPageLoad();
        if (driver.getCurrentUrl().equals("https://www.hepsiburada.com/")) {
            Assert.assertTrue(true);
        }
        hoverOnElementByCSS("#myAccount > span > span.sf-OldMyAccount-PhY-T");
        clickElementById("login");
        fillInTheBlankById("txtUserName", constants.getMailAddress());
        fillInTheBlankById("txtPassword", constants.getPassword());
        clickElementById("btnLogin");

        String myNameAndSurname = driver.findElement(By.xpath("//*[@title='Hesabım']/span[2]")).getText();
        Assert.assertEquals(myNameAndSurname, "murat ersoy");
    }

    public void searchProductAndCommentDetail(){
        fillInTheBlankByXpath("//*[@class='desktopOldAutosuggestTheme-input']", "iphone");
        clickElementByXpath("//*[@class='SearchBoxOld-buttonContainer']");
        clickElementByXpath("//li[2]//div[@class='product-detail']//h3");
        clickElementById("productReviewsTab");
        clickElementByXpath("//div[@class='paginationOverlay']/div/div[1]//*[@class='hermes-ReviewCard-module-tAGUS']");
        String stringThanks = driver.findElement(By.xpath("//div[@class='paginationOverlay']/div/div[1]//*[@class='hermes-ReviewCard-module-1ZiTv']")).getText();
        System.out.println(stringThanks);
        Assert.assertEquals(stringThanks, "Teşekkür Ederiz.");
    }
}
