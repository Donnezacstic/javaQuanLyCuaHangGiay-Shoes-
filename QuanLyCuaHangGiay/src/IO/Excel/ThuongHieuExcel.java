/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO.Excel;

import DTO.ThuongHieuDTO;
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
public class ThuongHieuExcel {
    public static final int CELL_MATHUONGHIEU = 0;
        public static final int CELL_TENTHUONGHIEU  = 1;
        public static ArrayList<ThuongHieuDTO> ImportExcelFile( String excelFilePath, int fields ) throws IOException
        {     
            if(!ReadFile(excelFilePath))
            {
                return null;
            }
            if(!VerifyData(fields)){
                return null;
            }
            ArrayList<ThuongHieuDTO> accountList = new ArrayList<>();
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
            Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) 
                    {
                        continue;
                    }
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    ThuongHieuDTO dto = new ThuongHieuDTO();
                    while (cellIterator.hasNext()) 
                    {
                        Cell cell = cellIterator.next();
                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex) 
                        {
                        case CELL_MATHUONGHIEU:
                            dto.setMaThuongHieu(cell.getStringCellValue());
                            break;
                        case CELL_TENTHUONGHIEU:
                            dto.setTenThuongHieu(cell.getStringCellValue());
                            break;
                        }
                    }
                    accountList.add(dto);
            }
            return accountList;
        }
        public static void ExportExcelFile( String excelFilePath, ArrayList<ThuongHieuDTO> accountList ) throws IOException
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
        Cell cellHeader = headerRow.createCell(CELL_MATHUONGHIEU);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Mã ThuongHieu");
 
        cellHeader = headerRow.createCell(CELL_TENTHUONGHIEU);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Tên ThuongHieu");

        int rowNum = 1;
        for (int i = 0;i < accountList.size();i++){
            Row row =  sheet.createRow(rowNum++);
            Cell cell = row.createCell(CELL_MATHUONGHIEU);
            cell.setCellValue(accountList.get(i).getMaThuongHieu());
            
            cell = row.createCell(CELL_TENTHUONGHIEU);
            cell.setCellValue(accountList.get(i).getTenThuongHieu());
            

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
