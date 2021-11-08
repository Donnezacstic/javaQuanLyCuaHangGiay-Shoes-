
package IO.Excel;

import DTO.NhanVienDTO;
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

public class NhanVienExcel extends ObjectExcel{
    public static final int CELL_MANHANVIEN = 0;
    public static final int CELL_HOTEN  = 1;
    public static final int CELL_GIOITINH  = 2;
    public static final int CELL_NAMSINH  = 3;
    public static final int CELL_SODIENTHOAI  = 4;
    public static final int CELL_DIACHI  = 5;
    public static final int CELL_TRANGTHAI  = 6;
    public static final int CELL_LUONG  = 7;
    public static final int CELL_NGAYVAOLAM  = 8;
    public static final int CELL_CALAM  = 9;
    public static final int CELL_IMGSOURCE  = 10;
    public static ArrayList<NhanVienDTO> ImportExcelFile( String excelFilePath, int fields ) throws IOException
    {     
        if(!ReadFile(excelFilePath))
        {
            return null;
        }
        if(!VerifyData(fields)){
            return null;
        }
        ArrayList<NhanVienDTO> list = new ArrayList<>();
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
                NhanVienDTO dto = new NhanVienDTO();
                while (cellIterator.hasNext()) 
                {
                    Cell cell = cellIterator.next();
//                        cell.setCellStyle(Cell.CELL_TYPE_STRING);
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex) 
                    {
                    case CELL_MANHANVIEN:
                        dto.setMaNhanVien(cell.getStringCellValue());
                        break;
                    case CELL_HOTEN:
                        dto.setHoTen(cell.getStringCellValue());
                        break;
                    case CELL_GIOITINH:
                        dto.setGioiTinh(cell.getStringCellValue());
                        break;
                    case CELL_NAMSINH:
                        dto.setNamSinh(cell.getStringCellValue());
                        break;
                    case CELL_SODIENTHOAI:
                        dto.setSoDienThoai(cell.getStringCellValue());
                        break;
                    case CELL_DIACHI:
                        dto.setDiaChi(cell.getStringCellValue());
                        break;
                    case CELL_TRANGTHAI:
                        dto.setTrangThai(cell.getStringCellValue());
                        break;
                    case CELL_LUONG:
                        dto.setLuong(cell.getStringCellValue());
                        break;
                    case CELL_NGAYVAOLAM:
                        dto.setNgayVaoLam(cell.getStringCellValue());
                        break;
                    case CELL_CALAM:
                        dto.setCaLam(cell.getStringCellValue());
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
    public static void ExportExcelFile( String excelFilePath, ArrayList<NhanVienDTO> list ) throws IOException
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
    Cell cellHeader = headerRow.createCell(CELL_MANHANVIEN);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Mã Nhà Cung Cấp");

    cellHeader = headerRow.createCell(CELL_HOTEN);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Họ Tên");

    cellHeader = headerRow.createCell(CELL_GIOITINH);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Giới Tính");

    cellHeader = headerRow.createCell(CELL_NAMSINH);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Năm Sinh");

    cellHeader = headerRow.createCell(CELL_SODIENTHOAI);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Số Điện Thoại");

    cellHeader = headerRow.createCell(CELL_DIACHI);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Địa Chỉ");

    cellHeader = headerRow.createCell(CELL_TRANGTHAI);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Trạng Thái");

    cellHeader = headerRow.createCell(CELL_LUONG);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Lương");

    cellHeader = headerRow.createCell(CELL_NGAYVAOLAM);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Ngày Vào Làm");

    cellHeader = headerRow.createCell(CELL_CALAM);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Ca Lam");

    cellHeader = headerRow.createCell(CELL_IMGSOURCE);
    cellHeader.setCellStyle(headerCellStyle);
    cellHeader.setCellValue("Nguồn Ảnh");

    int rowNum = 1;
    for (int i = 0;i < list.size();i++){
        Row row =  sheet.createRow(rowNum++);
        Cell cell = row.createCell(CELL_MANHANVIEN);
        cell.setCellValue(list.get(i).getMaNhanVien());

        cell = row.createCell(CELL_HOTEN);
        cell.setCellValue(list.get(i).getHoTen());

        cell = row.createCell(CELL_GIOITINH);
        cell.setCellValue(list.get(i).getGioiTinh());

        cell = row.createCell(CELL_NAMSINH);
        cell.setCellValue(list.get(i).getNamSinh());

        cell = row.createCell(CELL_SODIENTHOAI);
        cell.setCellValue(list.get(i).getSoDienThoai());

        cell = row.createCell(CELL_DIACHI);
        cell.setCellValue(list.get(i).getDiaChi());

        cell = row.createCell(CELL_TRANGTHAI);
        cell.setCellValue(list.get(i).getTrangThai());

        cell = row.createCell(CELL_LUONG);
        cell.setCellValue(list.get(i).getLuong());

        cell = row.createCell(CELL_NGAYVAOLAM);
        cell.setCellValue(list.get(i).getNgayVaoLam());

        cell = row.createCell(CELL_CALAM);
        cell.setCellValue(list.get(i).getCaLam());

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
