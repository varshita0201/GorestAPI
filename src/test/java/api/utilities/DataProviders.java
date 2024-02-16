package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name="alldata")
	public String[][] getAllData() throws IOException{
		String path=System.getProperty("user.dir")+"\\testdata\\gorest.xlsx";
		int rowNum=Utilities.getRow(path, "Sheet1");
		int colNum=Utilities.getCol(path, "Sheet1", rowNum);
		System.out.println(colNum);
		String completeData[][]=new String[rowNum][colNum];
		for(int i=1;i<=rowNum;i++) {
			for(int j=0;j<colNum;j++) {
				completeData[i-1][j]=Utilities.cellData(path,"Sheet1", i, j);
			}
		}
		return completeData;
	}
	
	@DataProvider(name="updateddata")
	public String[][] getUpdatedData() throws IOException{
		String path=System.getProperty("user.dir")+"\\testdata\\gorest.xlsx";
		int rowNum=Utilities.getRow(path, "Sheet2");
		int colNum=Utilities.getCol(path, "Sheet2", rowNum);
		System.out.println(colNum);
		String completeData[][]=new String[rowNum][colNum];
		for(int i=1;i<=rowNum;i++) {
			for(int j=0;j<colNum;j++) {
				completeData[i-1][j]=Utilities.cellData(path,"Sheet2", i, j);
			}
		}
		return completeData;
	}
}
