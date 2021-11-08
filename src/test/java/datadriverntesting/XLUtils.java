package datadriverntesting;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {

    public static FileInputStream fis;

    public static XSSFWorkbook wbo;

    public static XSSFSheet ws;

    public static XSSFRow row;

    public static XSSFCell cell;

    public static FileOutputStream fo;



    public static int getRowCount(String XLFile, String XLSheetName) throws IOException
    {

        fis=new FileInputStream(XLFile);

        wbo=new XSSFWorkbook(fis);

        ws=wbo.getSheet(XLSheetName);

        int RowCount=ws.getLastRowNum();

        wbo.close();
        fis.close();

        return RowCount;
    }

    public static int getCelCount(String XLFile, String XLSheetName, int rownum) throws IOException
    {

        fis=new FileInputStream(XLFile);

        wbo=new XSSFWorkbook(fis);

        ws=wbo.getSheet(XLSheetName);

        row=ws.getRow(rownum);

        int celCount=ws.getLastRowNum();

        wbo.close();
        fis.close();

        return celCount;
    }
    
    public static String getCellData(String XLFile, String XLSheetName, int rownum, int cellnum) throws IOException
    {
        fis=new FileInputStream(XLFile);

        wbo=new XSSFWorkbook(fis);

        ws=wbo.getSheet(XLSheetName);

        row=ws.getRow(rownum);

        cell=row.getCell(cellnum);

        String data;
        try {
            DataFormatter formatter=new DataFormatter();
            String cellData=formatter.formatCellValue(cell);
            return cellData;
        }
        catch (Exception e)
        {
            data="";
        }

        wbo.close();
        fis.close();
        return data;

    }

    public static void SetCelData(String XLFile, String XLSheetName, int rownum, int cellnum, String data) throws IOException
    {

        fis=new FileInputStream(XLFile);

        wbo=new XSSFWorkbook(fis);

        ws=wbo.getSheet(XLSheetName);

        row=ws.getRow(rownum);

        cell=row.getCell(cellnum);

        cell.setCellValue(data);

        FileOutputStream fo=new FileOutputStream(XLFile);

        wbo.write(fo);

        wbo.close();

        fis.close();

        fo.close();


    }








}
