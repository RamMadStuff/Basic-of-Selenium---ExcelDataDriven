package dataDriven.excelDataProvider;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvide {
	
	DataFormatter formatter=new DataFormatter();
	@Test(dataProvider="datadriven")
	public void testCaseData(String Testcases, String Data1, String Data2 , String Data3)
	{
		 System.out.println(Testcases+" " + Data1 +" "+ Data2 + Data3);
	}
	
	
	@DataProvider(name="datadriven")
	public Object[][] getData() throws IOException
	{
		//Object[][] data = {{"hello","bye",1},{"hi","tata",112},{"tcs","tester",234}};
		FileInputStream fis = new FileInputStream("C:\\Users\\saira\\OneDrive\\Desktop\\Selenium\\datadriven.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowCount=sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colcount=row.getLastCellNum();
		Object data[][]=new Object[rowCount-1][colcount];
		for(int i=0;i<rowCount-1;i++)
		{
			row=sheet.getRow(i+1);
			for(int j=0;j<colcount;j++)
			{
				XSSFCell cell =row.getCell(j);
				data[i][j]=formatter.formatCellValue(cell);
				
			}
		}
		return data;
		
		
			
		}
	
	

}
