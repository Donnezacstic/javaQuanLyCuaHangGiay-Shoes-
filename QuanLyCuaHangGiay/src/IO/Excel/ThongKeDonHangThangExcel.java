/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO.Excel;

import static IO.Excel.ObjectExcel.autosizeColumn;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
public class ThongKeDonHangThangExcel extends ObjectExcel{
    public static final int CELL_NAM = 0;
    public static final int CELL_QUY  = 1;
    public static final int CELL_TONGDONHANG  = 2;
    public static final int CELL_DONHANGTHANHCONG  = 3;
    public static final int CELL_DONHANGBIHUY  = 4;
    public static void ExportExcelFile( String excelFilePath, ArrayList<String[]> list ) throws IOException
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
    Row title = sheet.createRow(0);
    Cell cellHeader = title.createCell(0);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Thống kê đơn hàng theo tháng");    
    Row headerRow = sheet.createRow(1);
    cellHeader = headerRow.createCell(CELL_NAM);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Năm");

    cellHeader = headerRow.createCell(CELL_QUY);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Tháng");

    cellHeader = headerRow.createCell(CELL_TONGDONHANG);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Tổng đơn hàng");

    cellHeader = headerRow.createCell(CELL_DONHANGTHANHCONG);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Tổng đơn hàng thành công");

    cellHeader = headerRow.createCell(CELL_DONHANGBIHUY);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Tổng đơn hàng bị hủy");

    int rowNum = 2;
    for (int i = 0;i < list.size();i++){
        Row row =  sheet.createRow(rowNum++);
        Cell cell = row.createCell(CELL_NAM);
        cell.setCellValue(list.get(i)[0]);

        cell = row.createCell(CELL_QUY);
        cell.setCellValue(list.get(i)[1]);

        cell = row.createCell(CELL_TONGDONHANG);
        cell.setCellValue(list.get(i)[2]);

        cell = row.createCell(CELL_DONHANGTHANHCONG);
        cell.setCellValue(list.get(i)[3]);

        cell = row.createCell(CELL_DONHANGBIHUY);
        cell.setCellValue(list.get(i)[4]);
    }
    // Canh đều column
    int numberOfColumn = sheet.getRow(1).getPhysicalNumberOfCells();
    autosizeColumn(sheet, numberOfColumn);

    OutputStream fileOut = new FileOutputStream(new File(excelFilePath));
    workbook.write(fileOut);
    fileOut.close();
    System.out.println("6120-Success");
} 
}
