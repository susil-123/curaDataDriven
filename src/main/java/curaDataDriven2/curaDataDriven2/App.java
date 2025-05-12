package curaDataDriven2.curaDataDriven2;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class App 
{
   WebDriver driver = new EdgeDriver();
   public ArrayList<ArrayList<String>> extractData() throws IOException {
	   boolean flag=false;
	   ArrayList<ArrayList<String>> usersCreds = new ArrayList();
	   String file = "C:\\Users\\susil.k\\eclipse-workspace\\curaDataDriven2\\dataFolder\\data.xlsx";
	   FileInputStream fis = new FileInputStream(file);
	   Workbook workbook = new XSSFWorkbook(fis);
	   Sheet sheet = workbook.getSheetAt(0);

	   for(int i=1;i<=sheet.getLastRowNum();i++) {
		   flag=true;
		   Row row = sheet.getRow(i);
		   String user = row.getCell(0).getStringCellValue();
		   String password = row.getCell(1).getStringCellValue();
		   ArrayList<String> userCreds=new ArrayList();
		   userCreds.add(user);
		   userCreds.add(password);
		   usersCreds.add(userCreds);
//		   System.out.println("User: "+user+" password: "+password);
	   }
	   if(!flag) {
		   ArrayList<String> userCreds=new ArrayList();
		   userCreds.add("NULL");
		   userCreds.add("NULL");
		   usersCreds.add(userCreds);
	   }
	   return usersCreds;

   }

   public void displayArrayList(ArrayList<ArrayList<String>> usersCreds) {
		  for(ArrayList<String> al :usersCreds) {
			  System.out.println(al);
		  }
   }

   public void launchWebSite() {
	   driver.get("https://katalon-demo-cura.herokuapp.com/");
   }
   
   public boolean verifyLogin() {
	   try {	   
		   WebElement incorrectLoginElement = driver.findElement(By.xpath("//p[@class='lead text-danger']"));
	   }
	   catch(Exception e) {
		   return true;		   
	   }
	return false;
   }
   
   public void logout() {
	   WebElement optionsBar = driver.findElement(By.xpath("//i[@class='fa fa-bars']"));
	   WebElement btnLogout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
	   
	   optionsBar.click();
	   btnLogout.click();
   }
   
   public void login() throws IOException {
//	   WebElement makeAppointment = driver.findElement(By.xpath("//a[@id='btn-make-appointment']"));
//	   makeAppointment.click();
//	   WebElement inputUser = driver.findElement(By.xpath("//input[@id='txt-username']"));
//	   WebElement inputPassword = driver.findElement(By.xpath("//input[@id='txt-password']"));
//	   WebElement btnLogin = driver.findElement(By.xpath("//button[@id='btn-login']"));
	   
	   driver.findElement(By.xpath("//a[@id='btn-make-appointment']")).click();
	   
	   
	   
	   ArrayList<ArrayList<String>> usersCreds = extractData();
	   ArrayList<String> testCase = new ArrayList();
	   
	   
	   for(ArrayList<String> al : usersCreds) {
		   String user     = al.get(0);
		   String password = al.get(1);
//		   inputUser.clear();
//		   inputPassword.clear();
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		   driver.findElement(By.xpath("//input[@id='txt-username']")).sendKeys(user);
		   driver.findElement(By.xpath("//input[@id='txt-password']")).sendKeys(password);
		   driver.findElement(By.xpath("//button[@id='btn-login']")).click();
		   boolean loginResult = verifyLogin();
		   if(loginResult) {
			   testCase.add("passed");
			   logout();
			   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			   driver.findElement(By.xpath("//a[@id='btn-make-appointment']")).click();
//			   makeAppointment.click();
		   }
		   else {			   
			   testCase.add("failed");
		   }
	   }
	   System.out.println(testCase);
	   
   }
}


/*
package curaDataDriven.curaDataDriven;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class App 
{
   WebDriver driver = new EdgeDriver();
   String file = "C:\\Users\\susil.k\\eclipse-workspace\\curaDataDriven\\dataFolder\\data.xlsx";
   

   public ArrayList<ArrayList<String>> extractData() throws IOException {
	   boolean flag=false;
	   ArrayList<ArrayList<String>> usersCreds = new ArrayList();
	   FileInputStream fis = new FileInputStream(file);
	   Workbook workbook = new XSSFWorkbook(fis);
	   Sheet sheet = workbook.getSheetAt(0);

	   for(int i=1;i<=sheet.getLastRowNum();i++) {
		   flag=true;
		   Row row = sheet.getRow(i);
		   String user = row.getCell(0).getStringCellValue();
		   String password = row.getCell(1).getStringCellValue();
		   ArrayList<String> userCreds=new ArrayList();
		   userCreds.add(user);
		   userCreds.add(password);
		   usersCreds.add(userCreds);
//		   System.out.println("User: "+user+" password: "+password);
	   }
	   if(!flag) {
		   ArrayList<String> userCreds=new ArrayList();
		   userCreds.add("NULL");
		   userCreds.add("NULL");
		   usersCreds.add(userCreds);
	   }
	   
	   fis.close();
	   workbook.close();
	   
	   return usersCreds;

   }

   public void displayArrayList(ArrayList<ArrayList<String>> usersCreds) {
		  for(ArrayList<String> al :usersCreds) {
			  System.out.println(al);
		  }
   }

   public void launchWebSite() {
	   driver.get("https://katalon-demo-cura.herokuapp.com/");
   }
   
   public boolean verifyLogin() {
	   try {	   
		   WebElement incorrectLoginElement = driver.findElement(By.xpath("//p[@class='lead text-danger']"));
	   }
	   catch(Exception e) {
		   return true;		   
	   }
	return false;
   }
   
   public void logout() {
//	   WebElement optionsBar = driver.findElement(By.xpath("//i[@class='fa fa-bars']"));
//	   WebElement btnLogout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
//	   optionsBar.click();
//	   btnLogout.click();
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	   driver.findElement(By.xpath("//i[@class='fa fa-bars']")).click();
	   driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

   }
   
   public ArrayList<String> login() throws IOException {
//	   WebElement makeAppointment = driver.findElement(By.xpath("//a[@id='btn-make-appointment']"));
//	   makeAppointment.click();
//	   WebElement inputUser = driver.findElement(By.xpath("//input[@id='txt-username']"));
//	   WebElement inputPassword = driver.findElement(By.xpath("//input[@id='txt-password']"));
//	   WebElement btnLogin = driver.findElement(By.xpath("//button[@id='btn-login']"));
	   
	   driver.findElement(By.xpath("//a[@id='btn-make-appointment']")).click();
	   
	   
	   
	   ArrayList<ArrayList<String>> usersCreds = extractData();
	   ArrayList<String> testCase = new ArrayList();
	   
	   
	   for(ArrayList<String> al : usersCreds) {
		   String user     = al.get(0);
		   String password = al.get(1);
//		   inputUser.clear();
//		   inputPassword.clear();
		   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		   driver.findElement(By.xpath("//input[@id='txt-username']")).sendKeys(user);
		   driver.findElement(By.xpath("//input[@id='txt-password']")).sendKeys(password);
		   driver.findElement(By.xpath("//button[@id='btn-login']")).click();
		   boolean loginResult = verifyLogin();
		   if(loginResult) {
			   testCase.add("passed");
			   logout();
			   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			   driver.findElement(By.xpath("//a[@id='btn-make-appointment']")).click();
//			   makeAppointment.click();
		   }
		   else {			   
			   testCase.add("failed");
		   }
	   }
//	   System.out.println(testCase);
	   return testCase;
	   
   }

   public void enterTestCase(ArrayList<String> testCase) throws IOException {
	  FileInputStream fis = new FileInputStream(file);
	   Workbook workbook =  new XSSFWorkbook(fis);
	   Sheet sheet = workbook.getSheetAt(0);
	   for(int i=1;i<=sheet.getLastRowNum();i++) {
		   Row row = sheet.getRow(i);
		   row.createCell(2).setCellValue(testCase.get(i-1));
	   }
	   
	   fis.close();
	   FileOutputStream fos = new FileOutputStream(file);
	   workbook.write(fos);
	   
	   fos.close();
	   workbook.close();
   }
}
*/
