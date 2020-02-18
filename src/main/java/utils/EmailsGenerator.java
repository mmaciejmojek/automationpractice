package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class EmailsGenerator {
	
	private static final String FILE_PATH = "Email.txt";
	private static final String MAIL_PATTERN = "mapko@yahoo.com:";
	
	public static String getNextEmail() {
		int index = 0;
		String mail = null;
		int randomValue = 0;


		try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
			String line = br.readLine();
			
			// mapko@yahoo.com:500
			
			String[] mailArr = line.split(":");
			mail = mailArr[0];
			index = Integer.parseInt(mailArr[1]);

			Random random = new Random();
			int randomInt = random.nextInt()+1;

			randomValue = ++index + randomInt;
			int atIndex = mail.indexOf("@");

			mail = mail.substring(0, atIndex) + randomValue + mail.substring(atIndex) ; // mapko501@yahoo.com


		} catch (IOException e) {
			// logger
		}
		
		try (PrintWriter writer = new PrintWriter(FILE_PATH, "UTF-8")) {
		    writer.println(MAIL_PATTERN + randomValue );
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// logger
		}


		return mail;
	}
	
	public static String getCurrentEmail() {
		
		int index = 0;
		String email = null;

		
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
			
			String line = br.readLine();
			String[] mailArr = line.split(":");
			email = mailArr[0];
			index = Integer.parseInt(mailArr[1]);
			int atIndex = email.indexOf("@");

			email = email.substring(0, atIndex) + index  +  email.substring(atIndex);
			
		} catch (IOException e) {
			// logger
		}
		
		return email;
	}
}
