package IO.Excel;

import DTO.SanPhamDTO;
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

public class SanPhamExcel extends ObjectExcel{
    public static final int CELL_MASANPHAM = 0;
    public static final int CELL_TENSANPHAM  = 1;
    public static final int CELL_SOLUONG  = 2;
    public static final int CELL_MAMAU  = 3;
    public static final int CELL_MASIZE  = 4;
    public static final int CELL_MATHUONGHIEU  = 5;
    public static final int CELL_IMGSOURCE  = 6;
    public static ArrayList<SanPhamDTO> ImportExcelFile( String excelFilePath, int fields ) throws IOException
    {     
        if(!ReadFile(excelFilePath))
        {
            return null;
        }
        if(!VerifyData(fields)){
            return null;
        }
        ArrayList<SanPhamDTO> list = new ArrayList<>();
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
                SanPhamDTO dto = new SanPhamDTO();
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
//                        cell.setCellStyle(Cell.CELL_TYPE_STRING);
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) 
                    {
                    case CELL_MASANPHAM:
                        dto.setMaSanPham(cell.getStringCellValue());
                        break;
                    case CELL_TENSANPHAM:
                        dto.setTenSanPham(cell.getStringCellValue());
                        break;
                    case CELL_SOLUONG:
                        dto.setSoLuong(cell.getStringCellValue());
                        break;
                    case CELL_MAMAU:
                        dto.setMaMau(cell.getStringCellValue());
                        break;
                    case CELL_MASIZE:
                        dto.setMaSize(cell.getStringCellValue());
                        break;
                    case CELL_MATHUONGHIEU:
                        dto.setMaThuongHieu(cell.getStringCellValue());
                        break;
                    case CELL_IMGSOURCE:
                        dto.setImgSource(cell.getStringCellValue());
                        break;
                    }
                }
                list.add(dto);
        }
        return list;
    }
    public static void ExportExcelFile( String excelFilePath, ArrayList<SanPhamDTO> list ) throws IOException
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
    Cell cellHeader = headerRow.createCell(CELL_MASANPHAM);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Mã Sản Phẩm");

    cellHeader = headerRow.createCell(CELL_TENSANPHAM);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Tên Sản Phẩm");

    cellHeader = headerRow.createCell(CELL_SOLUONG);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Số Lượng");

    cellHeader = headerRow.createCell(CELL_MAMAU);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Mã Màu");

    cellHeader = headerRow.createCell(CELL_MASIZE);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Mã Size");

    cellHeader = headerRow.createCell(CELL_MATHUONGHIEU);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Mã Thương Hiệu");

    cellHeader = headerRow.createCell(CELL_IMGSOURCE);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Nguồn Ảnh");

    int rowNum = 1;
    for (int i = 0;i < list.size();i++){
        Row row =  sheet.createRow(rowNum++);
        Cell cell = row.createCell(CELL_MASANPHAM);
        cell.setCellValue(list.get(i).getMaSanPham());

        cell = row.createCell(CELL_TENSANPHAM);
        cell.setCellValue(list.get(i).getTenSanPham());

        cell = row.createCell(CELL_SOLUONG);
        cell.setCellValue(list.get(i).getSoLuong());

        cell = row.createCell(CELL_MAMAU);
        cell.setCellValue(list.get(i).getMaMau());

        cell = row.createCell(CELL_MASIZE);
        cell.setCellValue(list.get(i).getMaSize());

        cell = row.createCell(CELL_MATHUONGHIEU);
        cell.setCellValue(list.get(i).getMaThuongHieu());

        cell = row.createCell(CELL_IMGSOURCE);
        cell.setCellValue(list.get(i).getImgSource());
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
