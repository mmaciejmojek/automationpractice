package automationpractice.com;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import automationpractice.com.pageObject.Account;
import automationpractice.com.pageObject.CartSummary;
import automationpractice.com.pageObject.Clothes;
import automationpractice.com.pageObject.ShoppingActions;
import automationpractice.com.pageObject.SignInForm;
import utils.EmailsGenerator;

import java.io.FileNotFoundException;

public class TshirtOrderTest {
    private WebDriver driver;
    private Actions action;
    private Clothes clothes;
    private ShoppingActions shoppingActions;
    private CartSummary summary;
    private SignInForm signinForm;
    private Account account;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);
        clothes = new Clothes(driver);
        shoppingActions = new ShoppingActions(driver);
        signinForm = new SignInForm(driver);
        summary = new CartSummary(driver);
        account = new Account(driver);

        String baseUrl = "http://automationpractice.com/index.php";
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }
    @AfterClass
    public void closeAll() {
        account.getAccountLogout().click();
        driver.quit();
    }
    @Test(priority = 1)
    public void selectTshirt() {
        Assert.assertTrue(clothes.getTShirtsBtn().isDisplayed());

        action.moveToElement(clothes.getTShirtsBtn()).perform();

        clothes.getTShirtsBtn().click();

        Assert.assertTrue(clothes.getTshirtProduct(1).isDisplayed());
        Assert.assertEquals(clothes.getTshirtCount().size(), 1);

        action.moveToElement(clothes.getTshirtProduct(1)).perform();
        Assert.assertTrue(shoppingActions.getAddToCartBtn().isDisplayed());
        action.click(shoppingActions.getAddToCartBtn()).build().perform();

        shoppingActions.getProceedToCheckoutBtn().click();

    }
    @Test(priority = 2)
    public void checkingCartTshirtQtyAndPrice() {
        Assert.assertEquals(summary.getCartSummTotalProductsNum().size(), 1);

        Assert.assertEquals(summary.getCartSummTotalProductsPrice().getText(), "$16.51");
        Assert.assertEquals(summary.getCartSummaryTotalPrice().getText(), "$18.51");
        Assert.assertEquals(summary.getCartSummTotalShipping().getText(), "$2.00");

        summary.getCartProceedBtn().click();
    }
    @Test(priority = 3)
    public void tshirtSigninPage() {
        Assert.assertTrue(signinForm.getSignInForm().isDisplayed());

        signinForm.setEmailField(EmailsGenerator.getCurrentEmail());
        signinForm.setPasswordField("tester123");
        signinForm.getSignInBtn().click();


    }
    @Test(priority = 4)
    public void tshirtAdressPage() throws FileNotFoundException {
        Assert.assertEquals(summary.getCartSummBillingAdressName().getText(), utils.AddressGenerator.getCurrentAddressFirstName()+" "+utils.AddressGenerator.getCurrentAddressLastName());
        Assert.assertEquals(summary.getCartSummBillingAdressOne().getText(), utils.AddressGenerator.getCurrentAddressStreetName());
        Assert.assertEquals(summary.getCartSummBillingAdressCityState().getText(), utils.AddressGenerator.getCurrentAddressCityName() + ", " + utils.AddressGenerator.getCurrentAddressStateName() +" "+ utils.AddressGenerator.getCurrentAddressZipCode() );
        Assert.assertEquals(summary.getCartSummBillingAdressCountry().getText(), "United States");
        Assert.assertEquals(summary.getCartSummBillingAdressHomePhone().getText(), utils.AddressGenerator.getCurrentAddressHomePhone());
        Assert.assertEquals(summary.getCartSummBillingAdressMobile().getText(), utils.AddressGenerator.getCurrentAddressMobilePhone());

        summary.getCartProceedBtnTwo().click();
    }
    @Test(priority = 5)
    public void tshirtShippingPage() {
        summary.getCartSummTermsOfServiceCheck().click();
        summary.getCartProceedBtnTwo().click();

    }
    @Test(priority = 6)
    public void tshirtPaymentPage() {
        summary.getCartSummPayByCheck().click();

        Assert.assertEquals(summary.getCartSummPayByCheckConfirm().getText(), "CHECK PAYMENT");
        summary.getCartSummConfirmOrderBtn().click();
    }
    @Test(priority = 7)
    public void tshirtConfirmOrder() {
        Assert.assertTrue(summary.getCartSummSuccessMsg().isDisplayed());
        Assert.assertEquals(summary.getCartSummSuccessMsg().getText(), "Your order on My Store is complete.");
    }
    @Test(priority = 8)
    public void checkIsTshirtOrderVisibleInOrderHistorySection() {
        account.getAccountBtn().click();

        Assert.assertTrue(account.getAccountOrderHistoryBtn().isDisplayed());

        account.getAccountOrderHistoryBtn().click();

        Assert.assertTrue(account.getAccountOrderListTable().isDisplayed());

        account.getAccountBtn().click();
        account.getAccountOrderHistoryBtn().click();

        Assert.assertEquals(account.getAccountOrdersLis().size(), 1);
    }
}
