package IO.Excel;

import DTO.AccountDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ObjectExcel 
{
    public static InputStream fis;
    public static XSSFWorkbook wb;
    public static boolean ReadFile( String excelFilePath ) throws FileNotFoundException, IOException
    {
            try 
            {

                if (excelFilePath==null){
                    return false;
                }
                fis = new FileInputStream(new File(excelFilePath));
                wb = new XSSFWorkbook(fis);
            } catch (FileNotFoundException ex) 
            {
                System.out.println("Loi : "+ex.getMessage());
            }
            catch (IOException ex) 
            {
                System.out.println("Loi : "+ex.getMessage());
            }
            return true;
        }
    public static boolean VerifyData( int fields )
    {
            int count = 0;
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) 
                    {
                        Iterator<Cell> cellIterator = nextRow.cellIterator();
                        AccountDTO dto = new AccountDTO();
                        while (cellIterator.hasNext()) 
                        {
                            Cell cell = cellIterator.next();
                            count++;
                        }
                    }
            return (fields==count);
        }
    public static void autosizeColumn(Sheet sheet, int lastColumn ) 
        {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
        } 
}
