
package IO.Excel;

import DTO.AccountDTO;
import DTO.MauDTO;
import DTO.NhanVienDTO;
import GUI.Sweet.SweetAlert;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MauExcel extends ObjectExcel{
        public static final int CELL_MAMAU = 0;
        public static final int CELL_TENMAU  = 1;
        public static ArrayList<MauDTO> ImportExcelFile( String excelFilePath, int fields ) throws IOException
        {     
            if(!ReadFile(excelFilePath))
            {
                return null;
            }
            if(!VerifyData(fields)){
                return null;
            }
            ArrayList<MauDTO> accountList = new ArrayList<>();
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
            Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) 
                    {
                        continue;
                    }
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    MauDTO dto = new MauDTO();
                    while (cellIterator.hasNext()) 
                    {
                        Cell cell = cellIterator.next();
                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex) 
                        {
                        case CELL_MAMAU:
                            dto.setMaMau(cell.getStringCellValue());
                            break;
                        case CELL_TENMAU:
                            dto.setTenMau(cell.getStringCellValue());
                            break;
                        }
                    }
                    accountList.add(dto);
            }
            return accountList;
        }
        public static void ExportExcelFile( String excelFilePath, ArrayList<MauDTO> accountList ) throws IOException
        {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();            
        // Chỉnh font,...
        Font headerFont = workbook.createFont();
        headerFont.setBoldweight((short)3);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Ghi Header
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        Row headerRow = sheet.createRow(0);
        Cell cellHeader = headerRow.createCell(CELL_MAMAU);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Mã màu");
 
        cellHeader = headerRow.createCell(CELL_TENMAU);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Tên màu");

        int rowNum = 1;
        for (int i = 0;i < accountList.size();i++){
            Row row =  sheet.createRow(rowNum++);
            Cell cell = row.createCell(CELL_MAMAU);
            cell.setCellValue(accountList.get(i).getMaMau());
            
            cell = row.createCell(CELL_TENMAU);
            cell.setCellValue(accountList.get(i).getTenMau());
            

        }
        // Canh đều column
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        OutputStream fileOut = new FileOutputStream(new File(excelFilePath));
        workbook.write(fileOut);
        fileOut.close();
    }   
}
