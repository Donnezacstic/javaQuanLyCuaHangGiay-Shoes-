package IO.PDF;

import DTO.SanPhamDTO;
import DTO.ChiTietDonHangDTO;
import DTO.DonHangDTO;
import DTO.KhachHangDTO;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import static quanlycuahanggiay.Currency.changeCurrency;

public class HoaDonBanHangPDF {
    public static Paragraph paragraph;
    private static String tencongty = "CÔNG TY TNHH MỘT THÀNH VIÊN SAGO";
    private static String diachicongty = "Lầu 3, Tòa nhà Lữ Gia, Số 70 Lữ Gia, Phường 15, Quận 11, TP. Hồ Chí Minh, VN";
    private static String hinhthucthanhtoan = "COD";
    private static String sodienthoaicongty = "0123 456 789";
    private static String [] tableHeader = {"STT","Tên Sản Phẩm", "Màu", "Size","Số lượng","Đơn giá","Tạm tính"};
    private static String NguoiMuaHang = "Chữ ký người nhận";
    private static String GhiRoHoTen = "(Ký, ghi rõ họ, tên)";
    private static String logoSource = "src/images/logo.png";
    private static String MaVach = "iText/mavach.png";
    private static String NhacNho = "(Cần kiểm tra, đối chiếu khi giao, nhận hóa đơn)";
    private static String unicodeFontSource = "iText/vuArial.ttf";
    private static File fontFile;
    private static Image img;
    private static Font font;
    private static String dates;
    private static ArrayList<String[]> chitietdonhang;
    private static ArrayList<String> donhang;
    private static int soluong ;
    private static int VAT;
    private static int thanhtoan;
    private static BaseFont bf;
    private static void PrepareItemForPrinting() throws IOException, DocumentException
    {
         //LOGO
        img = Image.getInstance(logoSource);
        
        //FONT BASE
        fontFile = new File(unicodeFontSource);
        bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        font = new Font(bf,10);
    }
    private static void ConvertDataForPrinting( DonHangDTO object, ArrayList<ChiTietDonHangDTO> array )
    {
        soluong=0;
        VAT = 0;
        thanhtoan=0;
        chitietdonhang = new ArrayList<>();
        array.forEach(ctdh->{
            SanPhamDTO sanpham = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(ctdh.getMaSanPham());
            String mau = quanlycuahanggiay.quanlycuahanggiay.MauBUS.getTenMauByMaMau(sanpham.getMaMau());
            String size = quanlycuahanggiay.quanlycuahanggiay.SizeBUS.getTenSizeByMaSize(sanpham.getMaSize());
            String[] content = {sanpham.getTenSanPham(),mau,size,ctdh.getSoLuong(),ctdh.getDonGia(),ctdh.getThanhTien()};
            soluong += Integer.parseInt(ctdh.getSoLuong());
            chitietdonhang.add(content);
        });
        donhang = new ArrayList<>();
        KhachHangDTO khachhang = quanlycuahanggiay.quanlycuahanggiay.KhachHangBUS.getByMaKhachHang(object.getMaKhachHang());
        donhang.add(object.getMaDonHang());
        donhang.add(object.getTongTien());
        donhang.add(khachhang.getHoTen());
        donhang.add(khachhang.getDiaChi());
        donhang.add(khachhang.getSoDienThoai());
        donhang.add(soluong+"");
        VAT = (int)((Integer.parseInt(donhang.get(1))*2.0f)/100.0f);
        thanhtoan = (Integer.parseInt(donhang.get(1))+VAT);
    }
    public static Document addBillHeader( Document doc ) throws DocumentException, IOException
    {
        img.setAbsolutePosition(-5, 540);
        float height = img.getHeight();
        float width = img.getWidth();
        paragraph = new Paragraph();
        paragraph.add(img);
        doc.add(paragraph);
        img = Image.getInstance(MaVach);
        img.setAbsolutePosition(270, 540);
        img.scaleAbsolute(width, height);
        paragraph = new Paragraph();
        paragraph.add(img);
        doc.add(paragraph);
        paragraph = new Paragraph("Mã hóa đơn: ",font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(paragraph);
        paragraph = new Paragraph(donhang.get(0),font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(10);
        doc.add(paragraph);
        DottedLineSeparator dls = new DottedLineSeparator();
        dls.setPercentage(59500f / 523f);
        dls.setLineWidth(1.5f);
        doc.add(dls);
        return doc;
    }
    public static Document addProviderInfomation( Document doc ) throws DocumentException, IOException
    {
        Chunk test = new Chunk(tencongty,font);
        paragraph = new Paragraph("Từ",font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingBefore(2);
        paragraph.setSpacingAfter(2);
        doc.add(paragraph);
        paragraph=new Paragraph(test);
        paragraph.setSpacingAfter(2);
        doc.add(paragraph);
        paragraph = new Paragraph(diachicongty,font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingAfter(2);
        doc.add(paragraph);
        paragraph = new Paragraph("SĐT: "+sodienthoaicongty,font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingAfter(5);
        doc.add(paragraph);
        DottedLineSeparator dls = new DottedLineSeparator();
        dls.setPercentage(5000f);
        dls.setLineWidth(1.5f);
        doc.add(dls);
        return doc;
    }
    public static Document addReceiverInfomation( Document doc ) throws DocumentException, IOException
    {
        paragraph = new Paragraph("Đến",font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(2);
        doc.add(paragraph);
        Chunk test = new Chunk(donhang.get(2),font);
        paragraph = new Paragraph(test);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingAfter(2);
        doc.add(paragraph);
        paragraph = new Paragraph(donhang.get(3),font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingAfter(2);
        doc.add(paragraph);
        paragraph = new Paragraph("SĐT: "+donhang.get(4),font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingAfter(2);
        doc.add(paragraph);
        paragraph = new Paragraph("Hình thức thanh toán: "+hinhthucthanhtoan,font);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        paragraph.setSpacingAfter(5);
        paragraph.setAlignment(Element.ALIGN_BOTTOM);
        doc.add(paragraph);
        DottedLineSeparator dls = new DottedLineSeparator();
        dls.setPercentage(5000f);
        dls.setLineWidth(1.5f);
        doc.add(dls);
        return doc;
    }
    public static Document addPdfBody(Document doc) throws DocumentException, IOException
    {
        font = new Font(bf,10,Font.BOLD);
        paragraph = new Paragraph("Nội dung hàng: (Tổng SL sản phẩm: "+donhang.get(5)+")",font);
        paragraph.setSpacingBefore(2);
        paragraph.setSpacingAfter(2);
        doc.add(paragraph);
        font = new Font(bf,10);
        String ten,mau,size,soluong,dongia,tamtinh;
        for(int i=0;i<chitietdonhang.size();i++)
        {
        ten = chitietdonhang.get(i)[0];
        mau = chitietdonhang.get(i)[1];
        size = chitietdonhang.get(i)[2];
        soluong = chitietdonhang.get(i)[3];
        dongia = "Đơn giá: "+changeCurrency(chitietdonhang.get(i)[4])+"đ";
        tamtinh = "Tạm tính: "+changeCurrency(chitietdonhang.get(i)[5])+"đ";
        paragraph = new Paragraph(i+". "+ten+", "+mau+", "+size+", "+"SL: "+soluong+", "+dongia+", "+tamtinh,font);
        paragraph.setSpacingAfter(2);
        doc.add(paragraph);
        }
        paragraph = new Paragraph("");
        paragraph.setSpacingBefore(5);
        paragraph.setSpacingAfter(5);
        doc.add(paragraph);
        DottedLineSeparator dls = new DottedLineSeparator();
        dls.setPercentage(5000f);
        dls.setLineWidth(1.5f);
        doc.add(dls);
        return doc;
    }
    public static Document addVerify(Document doc) throws DocumentException, IOException
    {
        paragraph = new Paragraph("Tổng tiền:                      "+changeCurrency(donhang.get(1))+" vnđ",font);
        paragraph.setIndentationLeft(50);
        doc.add(paragraph);
        paragraph = new Paragraph("Thuế GTGT:                  "+changeCurrency(Integer.toString(VAT))+" vnđ",font);
        paragraph.setIndentationLeft(50);
        doc.add(paragraph);
        paragraph = new Paragraph("Tổng tiền thanh toán:    "+changeCurrency(Integer.toString(thanhtoan))+" vnđ",font);
        paragraph.setIndentationLeft(50);
        doc.add(paragraph);
        paragraph = new Paragraph("Lưu ý:                                                                                           "+NguoiMuaHang,font);
        paragraph.setSpacingBefore(20);
        paragraph.setIndentationLeft(10);
        doc.add(paragraph);
        paragraph = new Paragraph("- Chuyển hoàn sau 3 lần giao;                                                      "+GhiRoHoTen,font);
        paragraph.setIndentationLeft(10);
        doc.add(paragraph);
        paragraph = new Paragraph("- Lưu kho tối đa 5 ngày;",font);
        paragraph.setIndentationLeft(10);
        doc.add(paragraph);
        paragraph = new Paragraph("- Kiểm tra hàng nguyên vẹn, không móp/méo, đổ/vỡ",font);
        paragraph.setIndentationLeft(10);
        paragraph.setSpacingAfter(150);
        doc.add(paragraph);
        paragraph = new Paragraph(NhacNho,font);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(20);
        doc.add(paragraph);
        return doc;
    }
    public static void exportPdf( String path, DonHangDTO donhang, ArrayList<ChiTietDonHangDTO> chitietdonhang ) throws IOException
    {
        Document doc = new Document(PageSize.A5, 10f, 10f, 10f, 0f);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path));
            PrepareItemForPrinting();
            ConvertDataForPrinting(donhang,chitietdonhang);
            doc.open();
            doc = addBillHeader(doc);
            doc = addProviderInfomation(doc);
            doc = addReceiverInfomation(doc);
            doc = addPdfBody(doc);
            doc = addVerify(doc);
            doc.close();
            System.out.println("2620-Success");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
