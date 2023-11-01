package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders
{  // we will get this test data from excelsheet and store it in 2-D string array
	//this method will retuen all the data present inside of excel sheet
   @DataProvider(name="alldata")
	public String[][] getalldata() throws IOException
   {
		String xlfilepath=System.getProperty("user.dir")+"//Test Data//testdata.xlsx";
		XLUtil xl=new XLUtil(xlfilepath);
		
		int rownum=xl.getrowcount("userdata");
		
		int cellnum=xl.getcellcount("userdata", 1);//row number should start from 1 because 0 row is title and not data
		
		// declare and initialise the 2-D array
		String apiUserdata[][]=new String[rownum][cellnum];
		//applay for loop to get adta from rows and cells
		//outer for loop for rows and inner for cells
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<cellnum;j++)
			{
				apiUserdata[i-1][j]=xl.getcelldata("userdata", i, j);
			}
		}
		
	   return apiUserdata;
		
	}
	@DataProvider(name="usernames")
	public String[] GetUsername() throws IOException
	{
		String xlfilepath=System.getProperty("user.dir")+"//Test Data//testdata.xlsx";
		XLUtil xl=new XLUtil(xlfilepath);
		
		int rownum=xl.getrowcount("userdata");
		
		String apiusername[]=new String[rownum];// this is single dimentinal array beacuse we ae storing username only that is in a single cell
		
		for(int i=1;i<=rownum;i++)
		{
			apiusername[i-1]=xl.getcelldata("userdata", i, 1);// this will start from cell index 1 where usernames are stored in excel
		}
		
		return apiusername;
	}
	
	
	
	
	
	
	
	
	
	
	
}
