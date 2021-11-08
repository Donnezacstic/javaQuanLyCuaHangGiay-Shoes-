package IO.PDF;

import DTO.ChiTietPhieuNhapDTO;
import DTO.KhachHangDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.Sweet.SweetAlert;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import static quanlycuahanggiay.Currency.changeCurrency;
import static quanlycuahanggiay.DateTime.TimestampToDateString;

public class HoaDonNhapHangPDF {

    public static Paragraph paragraph;
    private static String[] tableHeader = {"STT", "Tên Sản Phẩm", "Màu", "Size", "Đơn giá (vnđ)", "Số Lượng", "Tạm Tính (vnđ)"};
    private static String TieuDeHoaDon = "HÓA ĐƠN NHẬP HÀNG";
    private static String TieuDeMaPhieuNhap = "Mã Phiếu Nhập:";
    private static String DonViCungCap = "Nhà cung cấp: ";
    private static String SoDienThoaiDonViCungCap = "Số điện thoại: ";
    private static String DiaChiDonViCungCap = "Địa chỉ: ";
    private static String DonViNhanHangTen = "Nhân viên nhập hàng: ";
    private static String DonViNhanHang = "Đơn vị nhận hàng: ";
    private static String SoDienThoaiDonViNhanHang = "Số điện thoại: ";
    private static String DiaChiDonViNhanHang = "Địa chỉ: ";
    private static String HinhThucThanhToan = "Hình thức thanh toán: ";
    private static String NguoiMuaHang = "Người mua hàng";
    private static String NguoiBanHang = "Người bán hàng";
    private static String GhiRoHoTen = "(Ký, ghi rõ họ, tên)";
    private static String logoSource = "src/images/logo.png";
    private static String NhacNho = "(Cần kiểm tra, đối chiếu khi giao, nhận hóa đơn)";
    private static String unicodeFontSource = "iText/vuArial.ttf";
    private static File fontFile;
    private static Image img;
    private static Font font, blue, red;
    private static int VAT;
    private static int thanhtoan;
    private static ArrayList<String[]> chitietphieunhap;
    private static ArrayList<String> phieunhap;

    private static void PrepareItemForPrinting() throws IOException, DocumentException 
    {
        //LOGO
        img = Image.getInstance(logoSource);

        //FONT BASE
        fontFile = new File(unicodeFontSource);
        BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font = new Font(bf, 10);
        blue = new Font(bf, 12, Font.NORMAL, BaseColor.BLUE);
        red = new Font(bf, 12, Font.BOLD, BaseColor.RED);

    }

    private static void ConvertDataForPrinting(PhieuNhapDTO object, ArrayList<ChiTietPhieuNhapDTO> array) 
    {
        VAT = 0;
        thanhtoan = 0;
        chitietphieunhap = new ArrayList<>();
        array.forEach(ctdh -> {
            SanPhamDTO sanpham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(ctdh.getMaSanPham());
            String mau = quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(sanpham.getMaMau());
            String size = quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(sanpham.getMaSize());
            String[] content = {sanpham.getTenSanPham(), mau, size, ctdh.getSoLuong(), ctdh.getDonGia(), ctdh.getThanhTien()};
            chitietphieunhap.add(content);
        });
        phieunhap = new ArrayList<>();
        NhanVienDTO nhanvien = quanlycuahanggiay.quanlycuahanggiay.NhanVienBUS.getByMaNhanVien(object.getMaNhanVien());
        NhaCungCapDTO nhacungcap = quanlycuahanggiay.quanlycuahanggiay.NhaCungCapBUS.getByMaNhaCungCap(object.getMaNhaCungCap());
        phieunhap.add(nhacungcap.getTenNhaCungCap());//0

        phieunhap.add(nhacungcap.getDiaChi());//1

        phieunhap.add(nhacungcap.getSoDienThoai());//2

        phieunhap.add(object.getMaPhieuNhap());//3

        phieunhap.add(nhanvien.getHoTen());//4

        phieunhap.add(object.getNgayNhap());//5

        phieunhap.add(object.getTongTien());//6

        VAT = (int) ((Integer.parseInt(phieunhap.get(6)) * 2.0f) / 100.0f);
        thanhtoan = (Integer.parseInt(phieunhap.get(6)) + VAT);
    }

    public static Document addBillHeader( Document doc) throws DocumentException, IOException 
    {
        //PRINTING PDF
        paragraph = new Paragraph(TieuDeHoaDon, red);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(10);
        img.setAbsolutePosition(10, 500);
        img.setAlignment(Element.ALIGN_LEFT);
        paragraph.add(img);
        doc.add(paragraph);
        paragraph = new Paragraph(TimestampToDateString(phieunhap.get(5), 1), font);
        paragraph.setSpacingAfter(5);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(paragraph);
        paragraph = new Paragraph(TieuDeMaPhieuNhap, blue);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        doc.add(paragraph);
        paragraph = new Paragraph(phieunhap.get(3), blue);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        paragraph.setSpacingAfter(20);
        doc.add(paragraph);
        doc.add(new LineSeparator(0.5f, 100, null, 0, -5));
        return doc;
    }

    public static Document addProviderInfomation( Document doc) throws DocumentException, IOException 
    {
        Chunk test = new Chunk(phieunhap.get(0), red);
        paragraph = new Paragraph(DonViCungCap, font);
        paragraph.add(test);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingBefore(15);
        paragraph.setSpacingAfter(10);
        doc.add(paragraph);
        paragraph = new Paragraph(DiaChiDonViCungCap + phieunhap.get(1), font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingAfter(10);
        doc.add(paragraph);
        paragraph = new Paragraph(SoDienThoaiDonViCungCap + phieunhap.get(2), font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingAfter(15);
        doc.add(paragraph);
        doc.add(new LineSeparator(0.5f, 100, null, 0, -5));
        return doc;
    }

    public static Document addReceiverInfomation( Document doc ) throws DocumentException, IOException 
    {
        paragraph = new Paragraph(DonViNhanHangTen + phieunhap.get(4), font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingBefore(15);
        paragraph.setSpacingAfter(10);
        doc.add(paragraph);
        Chunk test = new Chunk("CÔNG TY TNHH SAGO", red);
        paragraph = new Paragraph(DonViCungCap, font);
        paragraph.add(test);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingAfter(10);
        doc.add(paragraph);
        paragraph = new Paragraph(DiaChiDonViCungCap + "273 An Dương Vương ,Phường 3, Quận 5, TP.Hồ Chí Minh, Việt Nam", font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingAfter(10);
        doc.add(paragraph);
        paragraph = new Paragraph(SoDienThoaiDonViCungCap + "099555666", font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingAfter(15);
        doc.add(paragraph);
        return doc;
    }

    public static Document addPdfBody( Document doc ) throws DocumentException, IOException
    {
        PdfPTable table = new PdfPTable(7);
        Chunk header;
        for (int i = 0; i < tableHeader.length; i++) {
            header = new Chunk(tableHeader[i], font);
            PdfPCell headerCell = new PdfPCell(new Paragraph(header));
            table.addCell(headerCell);
        }
        for (int i = 0; i < chitietphieunhap.size(); i++) {
            PdfPCell data1 = new PdfPCell(new Paragraph(i + "", font));
            PdfPCell data2 = new PdfPCell(new Paragraph(chitietphieunhap.get(i)[0], font));
            PdfPCell data3 = new PdfPCell(new Paragraph(chitietphieunhap.get(i)[1], font));
            PdfPCell data4 = new PdfPCell(new Paragraph(chitietphieunhap.get(i)[2], font));
            PdfPCell data5 = new PdfPCell(new Paragraph(changeCurrency(chitietphieunhap.get(i)[3]), font));
            PdfPCell data6 = new PdfPCell(new Paragraph(chitietphieunhap.get(i)[4], font));
            PdfPCell data7 = new PdfPCell(new Paragraph(changeCurrency(chitietphieunhap.get(i)[5]), font));
            //Thêm data vào bảng.
            table.addCell(data1);
            table.addCell(data2);
            table.addCell(data3);
            table.addCell(data4);
            table.addCell(data5);
            table.addCell(data6);
            table.addCell(data7);
        }
        //create empty row
        int emptyCell = chitietphieunhap.size();
        for (int i = 0; i < 1; i++) {
            PdfPCell data1 = new PdfPCell(new Paragraph(Integer.toString(emptyCell), font));
            PdfPCell data2 = new PdfPCell(new Paragraph(""));
            PdfPCell data3 = new PdfPCell(new Paragraph(""));
            PdfPCell data4 = new PdfPCell(new Paragraph(""));
            PdfPCell data5 = new PdfPCell(new Paragraph(""));
            PdfPCell data6 = new PdfPCell(new Paragraph(""));
            PdfPCell data7 = new PdfPCell(new Paragraph(""));
            table.addCell(data1);
            table.addCell(data2);
            table.addCell(data3);
            table.addCell(data4);
            table.addCell(data5);
            table.addCell(data6);
            table.addCell(data7);
        }
        //create total bar
        String[] totalBar = {"Tổng tiền hàng: (vnđ)", "Thuế GTGT: (vnđ)", "Tổng tiền thanh toán: (vnđ)"};
        String[] totalData = {phieunhap.get(6), VAT + "", thanhtoan + ""};
        for (int i = 0; i < totalBar.length; i++) {
            PdfPCell data1 = new PdfPCell(new Paragraph(totalBar[i],font));
            PdfPCell data2 = new PdfPCell(new Paragraph());
            PdfPCell data3 = new PdfPCell(new Paragraph());
            PdfPCell data4 = new PdfPCell(new Paragraph());
            PdfPCell data5 = new PdfPCell(new Paragraph());
            PdfPCell data6 = new PdfPCell(new Paragraph());
            PdfPCell data7 = new PdfPCell(new Paragraph(changeCurrency(totalData[i]), font));
            data1.setBorderWidthRight(0);
            data2.setBorderWidthRight(0);
            data2.setBorderWidthLeft(0);
            data3.setBorderWidthRight(0);
            data3.setBorderWidthLeft(0);
            data4.setBorderWidthRight(0);
            data4.setBorderWidthLeft(0);
            data5.setBorderWidthRight(0);
            data5.setBorderWidthLeft(0);
            data6.setBorderWidthRight(0);
            data6.setBorderWidthLeft(0);
            table.addCell(data1);
            table.addCell(data2);
            table.addCell(data3);
            table.addCell(data4);
            table.addCell(data5);
            table.addCell(data6);
            table.addCell(data7);
        }
        PdfPTable sotienghibangchu = new PdfPTable(2);
        PdfPCell data1 = new PdfPCell(new Paragraph("Số tiền viết bằng chữ: ", font));
        PdfPCell data2 = new PdfPCell(new Paragraph("", font));
        data1.setBorderWidthRight(0);
        data1.setPadding(5);
        data2.setBorderWidthLeft(0);
        data2.setPadding(5);
        sotienghibangchu.addCell(data1);
        sotienghibangchu.addCell(data2);
        sotienghibangchu.setWidthPercentage(100);
        sotienghibangchu.setSpacingAfter(10);
        table.setSpacingBefore(5);
        table.setWidthPercentage(100);
        doc.add(table);
        doc.add(sotienghibangchu);
        return doc;
    }

    public static Document addVerify( Document doc ) throws DocumentException, IOException
    {
        Chunk test = new Chunk(NguoiMuaHang, font);
        paragraph = new Paragraph(test);
        test = new Chunk(NguoiBanHang, font);
        paragraph.add("                                                                                                                         ");
        paragraph.add(test);
        paragraph.setIndentationLeft(100);
        doc.add(paragraph);
        test = new Chunk(GhiRoHoTen, font);
        paragraph = new Paragraph(test);
        test = new Chunk(GhiRoHoTen, font);
        paragraph.add("                                                                                                                       ");
        paragraph.add(test);
        paragraph.setIndentationLeft(95);
        paragraph.setSpacingAfter(150);
        doc.add(paragraph);
        paragraph = new Paragraph(NhacNho, font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(20);
        doc.add(paragraph);
        return doc;
    }

    public static void exportPdf(String path, ArrayList<ChiTietPhieuNhapDTO> array, PhieuNhapDTO currentPhieuNhap ) throws IOException, DocumentException
    {
        PrepareItemForPrinting();
        ConvertDataForPrinting(currentPhieuNhap,array);
        Document doc = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 0f);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.open();
            doc = addBillHeader(doc);
            doc = addProviderInfomation(doc);
            doc = addReceiverInfomation(doc);
            doc = addPdfBody(doc);
            doc = addVerify(doc);
            doc.close();
        } catch (DocumentException | FileNotFoundException e) {
            SweetAlert.showSweetAlert(new JFrame(), "Lỗi", e.getMessage(), 1);
        }
    }
}
