package utils;

import java.io.*;
import com.github.javafaker.Faker;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressGenerator {

    private static final String FIRST_NAME_FILE_PATH = "AddressFirstName.txt";
    private static final String LAST_NAME_FILE_PATH = "AddressLastName.txt";
    private static final String STREET_NAME_FILE_PATH = "AddressStreetName.txt";
    private static final String CITY_NAME_FILE_PATH = "AddressCityName.txt";
    private static final String STATE_NAME_FILE_PATH = "AddressStateName.txt";
    private static final String ZIP_CODE_FILE_PATH = "AddressZipCode.txt";
    private static final String HOME_PHONE_FILE_PATH = "AddressHomePhone.txt";
    private static final String MOBILE_PHONE_FILE_PATH = "AddressMobilePhone.txt";
    private static Logger logger = Logger.getLogger(AddressGenerator.class.getName());
    public static Faker faker = new Faker();


    public static String getNextAddressFirstName() {

        String addressFirstName = null;

        try (BufferedReader br = new BufferedReader(new FileReader(FIRST_NAME_FILE_PATH))) {
             addressFirstName = (faker.name().firstName());

        } catch (IOException e) {
            logger.log(Level.INFO, "Exception during getting first name",e);
        }
        try (PrintWriter writer = new PrintWriter(FIRST_NAME_FILE_PATH, "UTF-8")) {
            writer.println(addressFirstName);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.log(Level.INFO, "Exception during getting first name",e);
        }
        return addressFirstName;
    }

    public static String getCurrentAddressFirstName() throws FileNotFoundException {

        File file = new File(FIRST_NAME_FILE_PATH);
        Scanner in = new Scanner(file);
        String currentAddressFirstName = in.nextLine();

        return currentAddressFirstName;
    }

    public static String getNextAddressLastName() {

        String addressLastName = null;

        try (BufferedReader br = new BufferedReader(new FileReader(LAST_NAME_FILE_PATH))) {
            addressLastName = (faker.name().lastName());

        } catch (IOException e) {
            logger.log(Level.INFO, "Exception during getting last name",e);
        }
        try (PrintWriter writer = new PrintWriter(LAST_NAME_FILE_PATH, "UTF-8")) {
            writer.println(addressLastName);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.log(Level.INFO, "Exception during getting last name",e);
        }
        return addressLastName;
    }

    public static String getCurrentAddressLastName() throws FileNotFoundException {

        File file = new File(LAST_NAME_FILE_PATH);
        Scanner in = new Scanner(file);
        String getCurrentAddressLastName = in.nextLine();

        return getCurrentAddressLastName;
    }

    public static String getNextAddressStreetName() {

        String addressStreetName = null;

        try (BufferedReader br = new BufferedReader(new FileReader(STREET_NAME_FILE_PATH))) {
            addressStreetName = (faker.address().streetName());

        } catch (IOException e) {
            logger.log(Level.INFO, "Exception during getting street name",e);
        }
        try (PrintWriter writer = new PrintWriter(STREET_NAME_FILE_PATH, "UTF-8")) {
            writer.println(addressStreetName);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.log(Level.INFO, "Exception during getting street name",e);
        }
        return addressStreetName;
    }

    public static String getCurrentAddressStreetName() throws FileNotFoundException {

        File file = new File(STREET_NAME_FILE_PATH);
        Scanner in = new Scanner(file);
        String getCurrentAddressStreetName = in.nextLine();

        return getCurrentAddressStreetName;
    }

    public static String getNextAddressCityName() {

        String addressCityName = null;

        try (BufferedReader br = new BufferedReader(new FileReader(CITY_NAME_FILE_PATH ))) {
            addressCityName = (faker.address().cityName());

        } catch (IOException e) {
            logger.log(Level.INFO, "Exception during getting city name",e);
        }
        try (PrintWriter writer = new PrintWriter(CITY_NAME_FILE_PATH, "UTF-8")) {
            writer.println(addressCityName);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.log(Level.INFO, "Exception during getting city name",e);
        }
        return addressCityName;
    }

    public static String getCurrentAddressCityName() throws FileNotFoundException {

        File file = new File(CITY_NAME_FILE_PATH);
        Scanner in = new Scanner(file);
        String getCurrentAddressCityName = in.nextLine();

        return getCurrentAddressCityName;
    }

    public static String getNextAddressStateName() {

        String addressStateName = null;

        try (BufferedReader br = new BufferedReader(new FileReader(STATE_NAME_FILE_PATH ))) {
            addressStateName = (faker.address().state());

        } catch (IOException e) {
            logger.log(Level.INFO, "Exception during getting state name",e);
        }
        try (PrintWriter writer = new PrintWriter(STATE_NAME_FILE_PATH, "UTF-8")) {
            writer.println(addressStateName);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.log(Level.INFO, "Exception during getting state name",e);
        }
        return addressStateName;
    }

    public static String getCurrentAddressStateName() throws FileNotFoundException {

        File file = new File(STATE_NAME_FILE_PATH);
        Scanner in = new Scanner(file);
        String getCurrentAddressStateName = in.nextLine();

        return getCurrentAddressStateName;
    }

    public static String getNextAddressZipCode() {

        long LongAddressZipCode = 0;
        String AddressZipCode = null;

        try (BufferedReader br = new BufferedReader(new FileReader(ZIP_CODE_FILE_PATH))) {
            LongAddressZipCode = (faker.number().numberBetween(10000, 99999));

            AddressZipCode = Long.toString(LongAddressZipCode);

        } catch (IOException e) {
            logger.log(Level.INFO, "Exception during getting zip code",e);
        }
        try (PrintWriter writer = new PrintWriter(ZIP_CODE_FILE_PATH, "UTF-8")) {
            writer.println(AddressZipCode);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.log(Level.INFO, "Exception during getting zip code",e);
        }
        return AddressZipCode;
    }

    public static String getCurrentAddressZipCode() throws FileNotFoundException {

        File file = new File(ZIP_CODE_FILE_PATH);
        Scanner in = new Scanner(file);
        String getCurrentAddressZipCode = in.nextLine();

        return getCurrentAddressZipCode;
    }

    public static String getNextAddressHomePhone() {

        long LongAddressHomePhone = 0;
        String AddressHomePhone = null;

        try (BufferedReader br = new BufferedReader(new FileReader(HOME_PHONE_FILE_PATH))) {
            LongAddressHomePhone = (faker.number().numberBetween(10000000, 99999999));

            AddressHomePhone = Long.toString(LongAddressHomePhone);

        } catch (IOException e) {
            logger.log(Level.INFO, "Exception during getting home phone",e);
        }
        try (PrintWriter writer = new PrintWriter(HOME_PHONE_FILE_PATH, "UTF-8")) {
            writer.println(AddressHomePhone);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.log(Level.INFO, "Exception during getting home phone",e);
        }
        return AddressHomePhone;
    }

    public static String getCurrentAddressHomePhone() throws FileNotFoundException {

        File file = new File(HOME_PHONE_FILE_PATH);
        Scanner in = new Scanner(file);
        String getCurrentAddressHomePhone = in.nextLine();

        return getCurrentAddressHomePhone;
    }

    public static String getNextAddressMobilePhone() {

        long LongAddressMobilePhone = 0;
        String AddressMobilePhone = null;

        try (BufferedReader br = new BufferedReader(new FileReader(MOBILE_PHONE_FILE_PATH))) {
            LongAddressMobilePhone = (faker.number().numberBetween(100000000, 999999999));

            AddressMobilePhone = Long.toString(LongAddressMobilePhone);

        } catch (IOException e) {
            logger.log(Level.INFO, "Exception during getting mobile phone",e);
        }
        try (PrintWriter writer = new PrintWriter(MOBILE_PHONE_FILE_PATH, "UTF-8")) {
            writer.println(AddressMobilePhone);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            logger.log(Level.INFO, "Exception during getting mobile phone",e);
        }
        return AddressMobilePhone;
    }

    public static String getCurrentAddressMobilePhone() throws FileNotFoundException {

        File file = new File(MOBILE_PHONE_FILE_PATH);
        Scanner in = new Scanner(file);
        String getCurrentAddressMobilePhone = in.nextLine();

        return getCurrentAddressMobilePhone;
    }
}
