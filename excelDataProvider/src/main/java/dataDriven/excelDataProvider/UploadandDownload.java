package dataDriven.excelDataProvider;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UploadandDownload {
	public static void main(String[] args) throws IOException {
		
		String fruitName = "Apple";
		String fileName = "C:\\Users\\saira\\Downloads\\download.xlsx";
		String updatedValue="595"; 
		
		WebDriver driver = new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		//download
		driver.findElement(By.cssSelector("#downloadButton")).click();
		//edit
		
		int col = getColumnNumber(fileName,"price");
		int row = getRowNumber(fileName,"Apple");
		Assert.assertTrue(updateCell(fileName,row,col,updatedValue));
		
		//upload
		WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
		upload.sendKeys("C:\\Users\\saira\\Downloads\\download.xlsx");
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20)); 
		By toastLocator = By.cssSelector(".Toastify__toast-body div:nth-child(2)");
		w.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
		//w.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
		String toastText = driver.findElement(toastLocator).getText();
		System.out.println(toastText);
		Assert.assertEquals(toastText, "Updated Excel Data Successfully.");
		w.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
		String priceColumn=driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String price = driver.findElement(By.xpath("//div[text()='"+fruitName+"']/parent::div/parent::div/div[@id='cell-"+priceColumn+"-undefined']")).getText();
		//String price = driver.findElement(By.xpath("//td[text()='Apple']/following-sibling::td")).getText();
		Assert.assertEquals(updatedValue,price);
		System.out.println(price);
		driver.close();
		
		
		
		
		
	}

	private static boolean updateCell(String fileName, int row, int col, String updatedValue) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a= new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);	
		XSSFSheet sheets = workbook.getSheet("Sheet1");
		Row rows = sheets.getRow(row-1);
		Cell cell=rows.getCell(col-1);
		cell.setCellValue(updatedValue);
		FileOutputStream fos = new FileOutputStream(fileName);
		workbook.write(fos);
		workbook.close();
		fis.close();
		return true;
		
		
	}

	private static int getRowNumber(String fileName, String textName) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a= new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);	
		XSSFSheet sheets = workbook.getSheet("Sheet1");
		Iterator<Row> rows = sheets.iterator();
		int k=1;
		int rowIndex=-1;
		while(rows.hasNext())
		{
			Row row = rows.next();
			Iterator<Cell> ce = row.cellIterator();
			 while(ce.hasNext())
			 {
				Cell cell = ce.next();
				if(cell.getCellType()==CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(textName))
				{
					rowIndex= k;
				}
		}
			 k++;
	}
		return rowIndex;
	}

	private static int getColumnNumber(String fileName, String colName) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> a= new ArrayList<String>();
		FileInputStream fis = new FileInputStream(fileName);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheets = workbook.getSheet("Sheet1");
		Iterator<Row> row = sheets.iterator();	 // sheet is the collection of rows 	
		 Row firstrow = row.next();
		 System.out.println("excell has next row");
		 Iterator<Cell> ce = firstrow.cellIterator();//row is the collection of coloumns
		 int k=1;
		 int column=0;
		 while(ce.hasNext())
		 {
			Cell value = ce.next();
			if(value.getStringCellValue().equalsIgnoreCase(colName))
			{
				column = k;
			}
			k++;
		 }
		 System.out.println(column);
		
		return column;
	}
	} 

