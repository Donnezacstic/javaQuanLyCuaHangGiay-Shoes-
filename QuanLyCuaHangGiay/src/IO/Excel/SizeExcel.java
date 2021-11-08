/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO.Excel;

import DTO.SizeDTO;
import static IO.Excel.ObjectExcel.ReadFile;
import static IO.Excel.ObjectExcel.VerifyData;
import static IO.Excel.ObjectExcel.autosizeColumn;
import static IO.Excel.ObjectExcel.wb;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author khanh
 */
public class SizeExcel {
    public static final int CELL_MASIZE = 0;
        public static final int CELL_TENSIZE  = 1;
        public static ArrayList<SizeDTO> ImportExcelFile( String excelFilePath, int fields ) throws IOException
        {     
            if(!ReadFile(excelFilePath))
            {
                return null;
            }
            if(!VerifyData(fields)){
                return null;
            }
            ArrayList<SizeDTO> accountList = new ArrayList<>();
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
            Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) 
                    {
                        continue;
                    }
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    SizeDTO dto = new SizeDTO();
                    while (cellIterator.hasNext()) 
                    {
                        Cell cell = cellIterator.next();
                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex) 
                        {
                        case CELL_MASIZE:
                            dto.setMaSize(cell.getStringCellValue());
                            break;
                        case CELL_TENSIZE:
                            dto.setTenSize(cell.getStringCellValue());
                            break;
                        }
                    }
                    accountList.add(dto);
            }
            return accountList;
        }
        public static void ExportExcelFile( String excelFilePath, ArrayList<SizeDTO> accountList ) throws IOException
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
        Cell cellHeader = headerRow.createCell(CELL_MASIZE);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Mã Size");
 
        cellHeader = headerRow.createCell(CELL_TENSIZE);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Tên Size");

        int rowNum = 1;
        for (int i = 0;i < accountList.size();i++){
            Row row =  sheet.createRow(rowNum++);
            Cell cell = row.createCell(CELL_MASIZE);
            cell.setCellValue(accountList.get(i).getMaSize());
            
            cell = row.createCell(CELL_TENSIZE);
            cell.setCellValue(accountList.get(i).getTenSize());
            

        }
        // Canh đều column
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        OutputStream fileOut = new FileOutputStream(new File(excelFilePath));
        workbook.write(fileOut);
        fileOut.close();
        System.out.println("6120-Success");
    }  
}
