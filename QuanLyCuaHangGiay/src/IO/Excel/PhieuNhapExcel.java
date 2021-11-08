/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO.Excel;

import DTO.PhieuNhapDTO;
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
public class PhieuNhapExcel {
    public static final int CELL_MAPHIEUNHAP = 0;
    public static final int CELL_MANHANVIEN  = 1;
    public static final int CELL_MANHACUNGCAP  = 2;
    public static final int CELL_NGAYNHAP  = 3;
    public static final int CELL_TONGTIEN  = 4;
        public static ArrayList<PhieuNhapDTO> ImportExcelFile( String excelFilePath, int fields ) throws IOException
        {     
            if(!ReadFile(excelFilePath))
            {
                return null;
            }
            if(!VerifyData(fields)){
                return null;
            }
            ArrayList<PhieuNhapDTO> list = new ArrayList<>();
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
                    PhieuNhapDTO dto = new PhieuNhapDTO();
                    while (cellIterator.hasNext()) 
                    {
                        Cell cell = cellIterator.next();
//                        cell.setCellStyle(Cell.CELL_TYPE_STRING);
                        int columnIndex = cell.getColumnIndex();
                        switch (columnIndex) 
                        {
                        case CELL_MAPHIEUNHAP:
                            dto.setMaPhieuNhap(cell.getStringCellValue());
                            break;
                        case CELL_MANHANVIEN:
                            dto.setMaNhanVien(cell.getStringCellValue());
                            break;
                        case CELL_MANHACUNGCAP:
                            dto.setMaNhaCungCap(cell.getStringCellValue());
                            break;
                        case CELL_NGAYNHAP:
                            dto.setNgayNhap(cell.getStringCellValue());
                            break;
                        case CELL_TONGTIEN:
                            dto.setTongTien(cell.getStringCellValue());
                            break;
                        }
                    }
                    list.add(dto);
            }
            return list;
        }
        public static void ExportExcelFile( String excelFilePath, ArrayList<PhieuNhapDTO> list ) throws IOException
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
        Cell cellHeader = headerRow.createCell(CELL_MAPHIEUNHAP);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Mã Phiếu Nhập");
 
        cellHeader = headerRow.createCell(CELL_MANHANVIEN);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Mã Nhân Viên");
        
        cellHeader = headerRow.createCell(CELL_MANHACUNGCAP);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Mã Nhà Cung Cấp");
        
        cellHeader = headerRow.createCell(CELL_NGAYNHAP);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Ngày Nhập");
        
        cellHeader = headerRow.createCell(CELL_TONGTIEN);
        cellHeader.setCellStyle(headerCellStyle);
        cellHeader.setCellValue("Tổng Tiền");
        
        int rowNum = 1;
        for (int i = 0;i < list.size();i++){
            Row row =  sheet.createRow(rowNum++);
            Cell cell = row.createCell(CELL_MAPHIEUNHAP);
            cell.setCellValue(list.get(i).getMaPhieuNhap());
            
            cell = row.createCell(CELL_MANHANVIEN);
            cell.setCellValue(list.get(i).getMaNhanVien());
            
            cell = row.createCell(CELL_MANHACUNGCAP);
            cell.setCellValue(list.get(i).getMaNhaCungCap());
            
            cell = row.createCell(CELL_NGAYNHAP);
            cell.setCellValue(list.get(i).getNgayNhap());
            
            cell = row.createCell(CELL_TONGTIEN);
            cell.setCellValue(list.get(i).getTongTien());
            
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
