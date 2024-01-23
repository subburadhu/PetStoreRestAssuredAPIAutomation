package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
		@DataProvider(name="CreateUserData")
		public String [][] getAllUserData() throws IOException
		{
			String path=".\\testData\\TestUserData.xlsx";//taking xl file from testData
			
			ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility
			
			int totalrows=xlutil.getRowCount("Sheet1");	
			int totalcols=xlutil.getCellCount("Sheet1",1);
					
			String apiuserdata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
			
			for(int r=1;r<=totalrows;r++)  //1   //read the data from xl storing in two dimensional array
			{		
				for(int c=0;c<totalcols;c++)  //0    r is rows c is col
				{
					apiuserdata[r-1][c]= xlutil.getCellData("Sheet1",r,c);  //1,0
				}
			}
			return apiuserdata;//returning two dimension array
					
		}
		
		//DataProvider 2
		@DataProvider(name="GetUserNamesData")
		public String[] getUserNames() throws IOException
		{
			
			String path=".\\testData\\TestUserData.xlsx";//taking xl file from testData

			ExcelUtility xlutil=new ExcelUtility(path);//creating an object for XLUtility

			int rownum=xlutil.getRowCount("Sheet1");
			
			String apiusernames[]=new String[rownum];
			
			for(int r=1;r<=rownum;r++)
			{
				apiusernames[r-1] = xlutil.getCellData("Sheet1", r, 1);
			}
			
			return apiusernames;
		}
		
		//DataProvider 3
		
		//DataProvider 4

}
