
package quanlycuahanggiay;

import java.util.regex.Pattern;

public class Regexp {
    private static String PATTERN_MAKHACHHANG ="KH\\d+";
    private static String PATTERN_HOTEN = "^[a-z\\s]+$";
    private static String PATTERN_STRING ="[^\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\=\\\\\\{\\}\\[\\]\\.\\?\\:\\;\\'\"]+";
    private static String PATTERN_MANHANVIEN = "^NV\\d+";
    private static String PATTERN_NAMSINH = "\\d{4}";
    private static String PATTERN_EMAIL = "^[a-z0-9][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
    private static String PATTERN_SODIENTHOAI = "^0\\d{9}";
    //private static String PATTERN_DIACHI = "[a-zA-Z0-9./,\\s]+$";[a-zA-Z\\s][\\s][a-zA-Z.,\\s][\\s][a-zA-Z.,\\s][\\s][a-zA-Z\\s]+$"
    private static String PATTERN_DIACHI = "[a-z0-9/,.\\s]+$";
    private static String PATTERN_SO = "\\d+";
    private static String PATTERN_DONGIA = "\\d+";
    private static String PATTERN_MUCLUONG = "\\d+";
    private static String PATTERN_MAMAU = "^M\\d+";
    private static String PATTERN_MADONHANG = "^DH\\d+";
    private static String PATTERN_MAPHIEUNHAP = "^PN\\d+";
    private static String PATTERN_MATHUONGHIEU = "^TH\\d+";
    private static String PATTERN_MASIZE = "^S\\d+";
    private static String PATTERN_MANHACUNGCAP ="^NCC\\d+";
    private static String PATTERN_TENSIZE ="\\d+";
    private static String PATTERN_TENMAU ="[^\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\_\\+\\=\\\\\\{\\}\\[\\]\\.\\?\\:\\;\\'\"\\d]+";
    private static String PATTERN_MACHUONGTRINHKHUYENMAI ="CT\\d+";
    private static String PATTERN_MACHITIETKHUYENMAI ="CTKM\\d+";
    private static String PATTERN_SOTUNHIEN ="^[1-9]\\d*";
    private static String PATTERN_SOPHANTRAM ="^([0-9]|([1-9][0-9])|100)$";
    private static String PATTERN_DONGIAPHIEUNHAP="^[1-9][0-9]{3,}";
    private static String PATTERN_TOITHIEUKHUYENMAI="^[1-9][0-9]{3,}";
    private static String PATTERN_SOLUONGPHIEUNHAP="^[1-9][0-9]*";
    public static boolean DONGIAPHIEUNHAP(String dongia) {
        return Pattern.matches(PATTERN_DONGIAPHIEUNHAP, dongia);
    }
    public static boolean TOITHIEUKHUYENMAI(String dongia){
            return Pattern.matches(PATTERN_TOITHIEUKHUYENMAI, dongia);
}
    public static boolean SOLUONGPHIEUNHAP(String soluong) {
        return Pattern.matches(PATTERN_SOLUONGPHIEUNHAP, soluong);
    }
    public static boolean SoPhanTram(String SoPhanTram) {
        return Pattern.matches(PATTERN_SOPHANTRAM, SoPhanTram);
    }
    public static boolean MaNhaCungCap(String MaNhaCungCap){
        return Pattern.matches(PATTERN_MANHACUNGCAP, MaNhaCungCap);
    
    }
    public static boolean SoTuNhien(String SOTUNHIEN){
        return Pattern.matches(PATTERN_SOTUNHIEN, SOTUNHIEN);
    
    }
    public static boolean MaKhachHang(String MaKhachHang){
        return Pattern.matches(PATTERN_MAKHACHHANG,MaKhachHang);
    }
    public static boolean HoTen(String HoTen){
        return Pattern.matches(PATTERN_HOTEN, HoTen);
    }
    public static boolean Email(String Email){
        return Pattern.matches(PATTERN_EMAIL,Email);
    }
    public static boolean DiaChi(String DiaChi){
        return Pattern.matches(PATTERN_DIACHI,DiaChi);
    }
    public static boolean MaPhieuNhap(String MaPhieuNhap){
        return Pattern.matches(PATTERN_MAPHIEUNHAP, MaPhieuNhap);
    }
    public static  boolean  MaDonHang(String MaDonHang){
        return Pattern.matches(PATTERN_MADONHANG, MaDonHang);
    }
    public static boolean MaChuongTrinhKhuyenMai(String MaChuongTrinhKhuyenMai){
        return Pattern.matches(PATTERN_MACHUONGTRINHKHUYENMAI,MaChuongTrinhKhuyenMai);
    }
    public static boolean MaChiTietKhuyenMai(String MaChiTietKhuyenMai){
        return Pattern.matches(PATTERN_MACHITIETKHUYENMAI,MaChiTietKhuyenMai);
    }
    public static boolean So(String So){
        return Pattern.matches(PATTERN_SO,So);
    }
    public static  boolean  MaNhanVien(String MaNhanVien){
        return Pattern.matches(PATTERN_MANHANVIEN, MaNhanVien);
    }
    public static boolean MaThuongHieu(String MaThuongHieu){
        return Pattern.matches(PATTERN_MATHUONGHIEU,MaThuongHieu);
    }
    public static  boolean  MaMau(String MaMau){
        return Pattern.matches(PATTERN_MAMAU, MaMau);
    }
    public static boolean TenMau(String TenMau){
        return Pattern.matches(PATTERN_TENMAU,TenMau);
    }
    public static boolean TenSize(String TenSize){
        return Pattern.matches(PATTERN_TENSIZE,TenSize);
    }
    public static boolean MaSize(String MaSize){
        return Pattern.matches(PATTERN_MASIZE,MaSize);
    }
    public static boolean Ten(String Ten){
        return Pattern.matches(PATTERN_STRING,Ten);
    }
    public static boolean SoDienThoai(String SoDienThoai){
        
        return Pattern.matches(PATTERN_SODIENTHOAI, SoDienThoai);
    }
    public static boolean MucLuong(String MucLuong){
        return Pattern.matches(PATTERN_MUCLUONG,MucLuong);
    }
    public static boolean DonGia(String DonGia){
        return Pattern.matches(PATTERN_DONGIA, DonGia);
    }
    public static boolean NamSinh(String NamSinh){
        return Pattern.matches(PATTERN_NAMSINH, NamSinh) && Integer.parseInt(NamSinh) <= Integer.parseInt(DateTime.getCurrenDateString(1).split("/")[2])-18;
    }    
}