import java.io.IOException;
import java.util.ArrayList;

public class ExcelSample {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		dataDriven datadriven = new dataDriven();
		ArrayList data = datadriven.getData("Add Profile");
		
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		
		

	}

}
