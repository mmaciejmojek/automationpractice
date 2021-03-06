package automationpractice.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import automationpractice.com.pageObject.Account;
import automationpractice.com.pageObject.CreateAccount;
import automationpractice.com.pageObject.CreateAccountForm;
import automationpractice.com.pageObject.Homepage;
import automationpractice.com.pageObject.SignInForm;
import utils.EmailsGenerator;
import utils.AddressGenerator;

public class CreateAccountFormTest {

	private  WebDriver driver;

	private  Homepage homepage;
	private  CreateAccount createAccount;
	private  CreateAccountForm createAccountForm;
	private  SignInForm signin;
	private  Account account;

	@BeforeClass
	public  void setup() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		homepage = new Homepage(driver);
		createAccount = new CreateAccount(driver);
		createAccountForm = new CreateAccountForm(driver);
		signin = new SignInForm(driver);
		account = new Account(driver);
		String baseUrl = "http://automationpractice.com/index.php";
		driver.get(baseUrl);
	}

	@AfterClass
	public void closeAll() {
		account.getAccountLogout().click();
		driver.quit();
	}

	@Test(priority = 1)
	public void authenticationPage() {
		homepage.getSignInBtn().click();

		Assert.assertTrue(createAccount.getCreateAccountForm().isDisplayed());
		Assert.assertTrue(createAccount.getCreatAccountEmailField().isDisplayed());
		Assert.assertTrue(createAccount.getCreateAccountBtn().isDisplayed());
		Assert.assertTrue(signin.getSignInForm().isDisplayed());
	}

	@Test(priority = 2)
	public void authenticationPageEmailField() {
		// Without email
		createAccount.getCreateAccountBtn().click();

		Assert.assertTrue(createAccount.getEmailErrorMessage().isDisplayed());

		// Wrong email format (mapko89ct, mapko89ct@gmail ...)
		createAccount.setCreateAccountEmailField("marko");
		createAccount.getCreateAccountBtn().click();

		Assert.assertTrue(createAccount.getEmailErrorMessage().isDisplayed());
		Assert.assertTrue(createAccount.getEmailFieldHighlightedRed().isDisplayed());

		// Registered email
		createAccount.setCreateAccountEmailField("email@email.email");
		createAccount.getCreateAccountBtn().click();

		Assert.assertTrue(createAccount.getEmailBeenRegistered().isDisplayed());

		// Correct email
		createAccount.setCreateAccountEmailField(EmailsGenerator.getNextEmail());
		createAccount.getCreateAccountBtn().click();

		Assert.assertTrue(createAccountForm.getAccountCreationForm().isDisplayed());


	}

	@Test(priority = 3)
	public void personalInfoFields() {
		// With values
		createAccountForm.setCustomerFirstNameField("Marko");
		createAccountForm.setCustomerLastNameField("Stevanovic");
		createAccountForm.setCustomerEmailField("mapko89ct@gmail.com");
		createAccountForm.setCustomerPasswordField("tester");

		createAccountForm.getAccountCreationForm().click();

		Assert.assertTrue(createAccountForm.getFirstNameHighlightedGreen().isDisplayed());
		Assert.assertTrue(createAccountForm.getLastNameHighlightedGreen().isDisplayed());
		Assert.assertTrue(createAccountForm.getEmailHighlightedGreen().isDisplayed());
		Assert.assertTrue(createAccountForm.getPasswordHighlightedGreen().isDisplayed());

		// Without values
		createAccountForm.setCustomerFirstNameField("");
		createAccountForm.setCustomerLastNameField("");
		createAccountForm.setCustomerEmailField("");
		createAccountForm.setCustomerPasswordField("");

		createAccountForm.getAccountCreationForm().click();

		Assert.assertTrue(createAccountForm.getFirstNameHighlightedRed().isDisplayed());
		Assert.assertTrue(createAccountForm.getLastNameHighlightedRed().isDisplayed());
		Assert.assertTrue(createAccountForm.getEmailHighlightedRed().isDisplayed());
		Assert.assertTrue(createAccountForm.getPasswordHighlightedRed().isDisplayed());
	}

	@Test(priority = 4)
	public void requiredFieldsEmpty() {
		createAccountForm.getAddressAliasField().clear();
		createAccountForm.setCustomerEmailField("");
		createAccountForm.selectCountry("-");
		createAccountForm.getRegisterBtn().click();

		Assert.assertTrue(createAccountForm.getPhoneNumberError().isDisplayed());
		Assert.assertTrue(createAccountForm.getLastNameError().isDisplayed());
		Assert.assertTrue(createAccountForm.getFirstNameError().isDisplayed());
		Assert.assertTrue(createAccountForm.getEmailRequiredError().isDisplayed());
		Assert.assertTrue(createAccountForm.getPasswordRequiredError().isDisplayed());
		Assert.assertTrue(createAccountForm.getCountryRequiredError().isDisplayed());
		Assert.assertTrue(createAccountForm.getAddressRequiredError().isDisplayed());
		Assert.assertTrue(createAccountForm.getAddressAliasRequiredError().isDisplayed());
		Assert.assertTrue(createAccountForm.getCityRequiredError().isDisplayed());
		Assert.assertTrue(createAccountForm.getCountryUnselectedError().isDisplayed());

		createAccountForm.selectCountry("United States");
		createAccountForm.getRegisterBtn().click();

		Assert.assertTrue(createAccountForm.getStateRequredError().isDisplayed());
		Assert.assertTrue(createAccountForm.getPostalCodeError().isDisplayed());
	}

	@Test(priority = 5)
	public void requiredFieldsInputFormat() throws Exception {
		// Wrong format
		createAccountForm.setCustomerEmailField("mapko89ct@gmail");
		createAccountForm.setCustomerPasswordField("test");
		createAccountForm.setPostalCodeField("asd");
		createAccountForm.setHomePhoneField("asd");
		createAccountForm.setMobilePhoneField("asd");

		createAccountForm.getRegisterBtn().click();

		Assert.assertTrue(createAccountForm.getEmailInvalidError().isDisplayed());
		Assert.assertTrue(createAccountForm.getPasswordInvalidError().isDisplayed());
		Assert.assertTrue(createAccountForm.getPostalCodeError().isDisplayed());
		Assert.assertTrue(createAccountForm.getHomePhoneInvalidError().isDisplayed());
		Assert.assertTrue(createAccountForm.getMobilePhoneInvalidError().isDisplayed());

		// Correct format
		createAccountForm.setCustomerEmailField(EmailsGenerator.getNextEmail());
		createAccountForm.setCustomerPasswordField("tester");
		createAccountForm.setPostalCodeField("21000");
		createAccountForm.setHomePhoneField("056");
		createAccountForm.setMobilePhoneField("065");

		Assert.assertTrue(createAccountForm.getEmailInvalidError().isDisplayed());
		Assert.assertTrue(createAccountForm.getPasswordInvalidError().isDisplayed());
		Assert.assertTrue(createAccountForm.getPostalCodeError().isDisplayed());
		Assert.assertTrue(createAccountForm.getHomePhoneInvalidError().isDisplayed());
		Assert.assertTrue(createAccountForm.getMobilePhoneInvalidError().isDisplayed());
	}

	@Test(priority = 6)
	public void createAccountSuccessfully() {
		// Required fields filled
		createAccountForm.setCustomerFirstNameField(utils.AddressGenerator.getNextAddressFirstName());
		createAccountForm.setCustomerLastNameField(utils.AddressGenerator.getNextAddressLastName());
		createAccountForm.setCustomerPasswordField("tester123");
		createAccountForm.selectCustomerDateOfBirthDay("20");
		createAccountForm.selectCustomerDateOfBirthMonth("10");
		createAccountForm.selectCustomerDateOfBirthYear("2000");
		createAccountForm.setAddressField(utils.AddressGenerator.getNextAddressStreetName());
		createAccountForm.setCityField(utils.AddressGenerator.getNextAddressCityName());
		createAccountForm.selectState(utils.AddressGenerator.getNextAddressStateName());
		createAccountForm.setPostalCodeField(utils.AddressGenerator.getNextAddressZipCode());
		createAccountForm.setHomePhoneField(utils.AddressGenerator.getNextAddressHomePhone());
		createAccountForm.setMobilePhoneField(utils.AddressGenerator.getNextAddressMobilePhone());
		createAccountForm.setAddressAliasField("My Address");
		createAccountForm.getRegisterBtn().click();



		Assert.assertTrue(createAccountForm.successfullyCreatedAccount().isDisplayed());
	}
}
