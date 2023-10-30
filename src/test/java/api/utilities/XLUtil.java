package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtil 
//decalre global variables of all class req. for parameterization
{ 
   String path;
 public  FileInputStream filein;
 public  FileOutputStream fileout;
 public  XSSFWorkbook wrkbook;
 public  XSSFSheet sheet;
 public  XSSFRow row;
 public  XSSFCell cell;
 public  XSSFCellStyle cellstyle;
  
  
 public XLUtil (String path)// this constructor is craeated to pass the path of excel file to file input stram method
	{
	this.path=path;
    }
//get no of rows from given excel sheet
 
 public int getrowcount(String sheetname) throws IOException
 {
	 filein=new FileInputStream(path);
	 wrkbook=new XSSFWorkbook(filein);
	 sheet=wrkbook.getSheet(sheetname);
	 int rowcount=sheet.getLastRowNum();
	 wrkbook.close();
	 filein.close();
	 return rowcount;
 }
 
 
 // to get colum no from excelsheet
 
 public int getcellcount(String sheetname, int rownum) throws IOException
 {
	filein=new FileInputStream(path);//this will go to a perticular excel sheet which path we have provided
	wrkbook=new XSSFWorkbook(filein);//this will open that excel sheet
	sheet=wrkbook.getSheet(sheetname);// this will open a specific sheet from that excel file using thst sheet name
	row=sheet.getRow(rownum); // this will get row number
	int cellcount=row.getLastCellNum(); // this will get no of cell present fron that row
	wrkbook.close();// this will close the excel sheet
	filein.close();// this will close the file input
	return cellcount;// this will give us the count of ceels present for that perticular row num we have provided
 }
 
 // get data from that cell in stirng format
 public String getcelldata(String sheetname, int rownum, int cellnum) throws IOException
 {
	 filein=new FileInputStream(path);
	 wrkbook=new XSSFWorkbook(filein);
	 sheet=wrkbook.getSheet(sheetname);
	 row=sheet.getRow(rownum);
	 cell=row.getCell(cellnum);
	 DataFormatter formatter=new DataFormatter();
	 String data;
	 try
	 {
		 data=formatter.formatCellValue(cell);// this will get data form sheet nad will returnt he data instring formatt
		 //regardless of its data type
	 }
	 catch(Exception e) 
	 {
		 data="";
	 }
	 wrkbook.close();
	 filein.close();
	 return data;
 }
 
 //this method will write the data in excel sheet
 public void Setcelldata(String sheetname,int rownum, int cellnum,String data) throws IOException
 {
	 
	 File XLfile=new File(path);// if file do not exist
	  if(XLfile.exists())
	  {// this will create a new excel file for us
		  wrkbook=new XSSFWorkbook();
		  fileout=new FileOutputStream(path);
		  wrkbook.write(fileout);
	  }
	 
	 filein=new FileInputStream(path);
	 wrkbook=new XSSFWorkbook(filein);
	 
	 if(wrkbook.getSheetIndex(sheetname)==-1)// if sheet does not exists create new sheet
	    wrkbook.createSheet(sheetname);
	    wrkbook.getSheet(sheetname);
	 
	 if(sheet.getRow(rownum)==null)// if row num does not exists create new row num
		 sheet.createRow(rownum);
	     row= sheet.getRow(rownum);
	     
	 cell=row.createCell(cellnum);
	 cell.setCellValue(data);
	 fileout=new FileOutputStream(path);
	 wrkbook.write(fileout);
	 wrkbook.close();
	 filein.close();
	 fileout.close();
 }
 
 public void fillRedcolor(String sheetname, int rownum, int cellnum) throws IOException 
 {// this will highlight the cell in red color
      filein=new FileInputStream(path);
      wrkbook=new XSSFWorkbook(filein);
      sheet=wrkbook.getSheet(sheetname);
      row=sheet.getRow(rownum);
      cell=row.getCell(cellnum);
      
       cellstyle = wrkbook.createCellStyle();
       cellstyle.setFillForegroundColor(IndexedColors.RED.getIndex());
       cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
       cell.setCellStyle(cellstyle);
       wrkbook.write(fileout);
       wrkbook.close();
       filein.close();
       fileout.close();
       
 }
 public void fillGreencolor(String sheetname, int rownum, int cellnum) throws IOException 
 {// this will highlight the cell in red color
      filein=new FileInputStream(path);
      wrkbook=new XSSFWorkbook(filein);
      sheet=wrkbook.getSheet(sheetname);
      row=sheet.getRow(rownum);
      cell=row.getCell(cellnum);
      
       cellstyle = wrkbook.createCellStyle();
       cellstyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
       cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
       cell.setCellStyle(cellstyle);
       wrkbook.write(fileout);
       wrkbook.close();
       filein.close();
       fileout.close();
       
 }
 
}
