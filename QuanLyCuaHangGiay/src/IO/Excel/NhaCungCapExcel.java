/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO.Excel;

import DTO.NhaCungCapDTO;
import static IO.Excel.ObjectExcel.ReadFile;
import static IO.Excel.ObjectExcel.VerifyData;
import static IO.Excel.ObjectExcel.wb;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
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
public class NhaCungCapExcel extends ObjectExcel{
    public static final int CELL_MANHACUNGCAP = 0;
        public static final int CELL_TENNHACUNGCAP  = 1;
        public static final int CELL_SODIENTHOAI  = 2;
        public static final int CELL_DIACHI  = 3;
        public static ArrayList<NhaCungCapDTO> ImportExcelFile( String excelFilePath, int fields ) throws IOException
        {     
            if(!ReadFile(excelFilePath))
            {
                return null;
            }
            if(!VerifyData(fields)){
                return null;
            }
            ArrayList<NhaCungCapDTO> list = new ArrayList<>();
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            DataFormat fmt = wb.createDataFormat();
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setDataFormat(
            fmt.getFormat("text"));
            while (iterator.hasNext()) {
            Row nextRow = iterator.next();
                if (nextRow.getRowNum() == 0) 
                    {
                        continue;
                    }
                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    NhaCungCapDTO dto = new NhaCungCapDTO();
                    while (cellIterator.hasNext()) 
                    {
                        Cell cell = cellIterator.next();
//                        cell.setCellStyle(Cell.CELL_TYPE_STRING);
                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex) 
                        {
                        case CELL_MANHACUNGCAP:
                            dto.setMaNhaCungCap(cell.getStringCellValue());
                            break;
                        case CELL_TENNHACUNGCAP:
                            dto.setTenNhaCungCap(cell.getStringCellValue());
                            break;
                        case CELL_SODIENTHOAI:
                            dto.setSoDienThoai(cell.getStringCellValue());
                            break;
                        case CELL_DIACHI:
                            dto.setDiaChi(cell.getStringCellValue());
                            break;
                        }
                    }
                    list.add(dto);
            }
            return list;
        }
        public static void ExportExcelFile( String excelFilePath, ArrayList<NhaCungCapDTO> list ) throws IOException
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
        Cell cellHeader = headerRow.createCell(CELL_MANHACUNGCAP);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Mã Nhà Cung Cấp");
 
        cellHeader = headerRow.createCell(CELL_TENNHACUNGCAP);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Tên Nhà Cung Cấp");
        
        cellHeader = headerRow.createCell(CELL_SODIENTHOAI);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Số Điện Thoại");
        
        cellHeader = headerRow.createCell(CELL_DIACHI);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Địa Chí");
        int rowNum = 1;
        for (int i = 0;i < list.size();i++){
            Row row =  sheet.createRow(rowNum++);
            Cell cell = row.createCell(CELL_MANHACUNGCAP);
            cell.setCellValue(list.get(i).getMaNhaCungCap());
            
            cell = row.createCell(CELL_TENNHACUNGCAP);
            cell.setCellValue(list.get(i).getTenNhaCungCap());
            
            cell = row.createCell(CELL_SODIENTHOAI);
            cell.setCellValue(list.get(i).getSoDienThoai());
            
            cell = row.createCell(CELL_DIACHI);
            cell.setCellValue(list.get(i).getDiaChi());
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
