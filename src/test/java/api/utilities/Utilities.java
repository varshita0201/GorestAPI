package api.utilities;

import java.io.*;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int  getRow(String path,String xsheet) throws IOException {
		fis=new FileInputStream(path);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet(xsheet);
		int rowNum=sheet.getLastRowNum();
		wb.close();
		fis.close();
		return rowNum;
	}
	
	public static int getCol(String path,String xsheet,int rowNum) throws IOException {
		fis=new FileInputStream(path);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet(xsheet);
		row=sheet.getRow(rowNum);
		int colNum=row.getLastCellNum();
		wb.close();
		fis.close();
		return colNum;
	}
	
	public static String cellData(String xfile,String xsheet,int rowNum,int colNum) throws IOException {
		fis=new FileInputStream(xfile);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet(xsheet);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		String data;
		try {
			DataFormatter f=new DataFormatter();
			String celldata=f.formatCellValue(cell);
			return celldata;
		}catch(Exception e) {
			data="No data Entered";
		}
		wb.close();
		fis.close();
		return data;
	}
}
