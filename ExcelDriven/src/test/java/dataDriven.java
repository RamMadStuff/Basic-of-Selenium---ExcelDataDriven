import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {
	
	public ArrayList<String> getData(String testcaseName) throws IOException
	{
		ArrayList<String> a= new ArrayList<String>();
		FileInputStream fis = new FileInputStream("C:\\Users\\saira\\OneDrive\\Desktop\\Selenium\\datadriven.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		 for (int i=0;i<sheets;i++)
		 {
			 if(workbook.getSheetName(i).equalsIgnoreCase("testadata"))
			 {
				 System.out.println("checking for testadata sheet in excel...");
				 XSSFSheet sheet = workbook.getSheetAt(i);
				 
				 //identify testcases coloumn by scanning the entire 1st row
				 
				 Iterator<Row> row = sheet.iterator(); // sheet is the collection of rows 	
				 Row firstrow = row.next();
				 System.out.println("excell has next row");
				 Iterator<Cell> ce = firstrow.cellIterator();//row is the collection of coloumns
				 int k=0;
				 int coloumn=0;
				 while(ce.hasNext())
				 {
					Cell value = ce.next();
					if(value.getStringCellValue().equalsIgnoreCase("Testcases"))
					{
						coloumn = k;
					}
					k++;
				 }
				 System.out.println(coloumn);
				 
				 //once the coloumn is identified then scan the entire testcase coloumn to identify the purchase testcase row
				 
				 System.out.println("checkcode3");
				 while(row.hasNext())
				 {
					 System.out.println("checkcode4");
					Row r= row.next();
					if(r.getCell(coloumn).getStringCellValue().equalsIgnoreCase(testcaseName))
							{
		//after you grab purchase testcase row = pull all the data of that row and feed into  test
						
								Iterator<Cell> cv = r.cellIterator();
								while(cv.hasNext())
								{
									Cell c= cv.next();
									if(c.getCellType()==CellType.STRING)
									{
										a.add(c.getStringCellValue());
									}
									else {
										
										a.add(NumberToTextConverter.toText(c.getNumericCellValue()));	
									}
									
								}
							}
					
					
				 }
			 }
		 }
		return a;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
		System.out.println("checking for testadata sheet in excel...");
	}

}
