
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.POPUP.ThemSanPhamVaoPhieuNhapJFrame;
import GUI.THEMMOI.TaoPhieuNhapJPanel;
import java.util.ArrayList;
import java.util.HashMap;
import static quanlycuahanggiay.Regexp.DONGIAPHIEUNHAP;
import static quanlycuahanggiay.Regexp.DonGia;
import static quanlycuahanggiay.Regexp.SOLUONGPHIEUNHAP;
import static quanlycuahanggiay.Regexp.SoTuNhien;
import static quanlycuahanggiay.Regexp.Ten;
import static quanlycuahanggiay.unicode.removeAccent;

public class ChiTietPhieuNhapBUS {
    public static ArrayList<ChiTietPhieuNhapDTO> ChiTietPhieuNhapList;
    public ChiTietPhieuNhapDAO ChiTietPhieuNhapDAO;
    private SanPhamDTO SanPhamTrongChiTiet;
    
    public ChiTietPhieuNhapBUS(){
        ChiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO();
        ChiTietPhieuNhapList = new ArrayList<>();
        fetchAll();
    }
    
    public void fetchAll(){
        ChiTietPhieuNhapList = ChiTietPhieuNhapDAO.fetchAll();
    }
    public static ChiTietPhieuNhapDTO getByMaSanPham(String MaSanPham){
        for(ChiTietPhieuNhapDTO ChiTietPhieuNhap : ChiTietPhieuNhapList){
            if(ChiTietPhieuNhap.getMaSanPham().equals(MaSanPham)){
                return ChiTietPhieuNhap;
            }
        }
        return null;
    }
    public boolean isExist(String MaPhieuNhap){
        for(ChiTietPhieuNhapDTO ChiTietPhieuNhap : ChiTietPhieuNhapList){
            if(ChiTietPhieuNhap.getMaPhieuNhap().toLowerCase().equals(MaPhieuNhap.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public boolean add(ChiTietPhieuNhapDTO ChiTietPhieuNhap){
        if(Exists(ChiTietPhieuNhap.getMaSanPham())){
            GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã phiếu nhập đã tồn tại", 1);
            return false;
        } else{
            String Error = Validation(ChiTietPhieuNhap);
            if(Error == null){
                    ChiTietPhieuNhapDAO.add(ChiTietPhieuNhap);
                    ChiTietPhieuNhapList = ChiTietPhieuNhapDAO.fetchAll();
                    return true;
            }
            else
                GUI.Sweet.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
                return false;
        }
    }
        public ArrayList<SanPhamDTO> advancedSearch(HashMap<String, String> advancedKeyValue){
        ArrayList<SanPhamDTO> result = new ArrayList<>();
        ThemSanPhamVaoPhieuNhapJFrame.DanhSachSanPham.forEach(SanPham ->{
        boolean TenSanPham = SanPham.getTenSanPham().toLowerCase().contains(advancedKeyValue.get("TenSanPham").toLowerCase());
        if(!TenSanPham)
                TenSanPham = removeAccent(SanPham.getTenSanPham().toLowerCase()).contains(advancedKeyValue.get("TenSanPham").toLowerCase());
        if(SanPham.getMaSanPham().toLowerCase().contains(advancedKeyValue.get("MaSanPham").toLowerCase()) &&
                TenSanPham &&
                SanPham.getMaMau().contains(advancedKeyValue.get("Mau")) &&
                SanPham.getMaSize().contains(advancedKeyValue.get("Size")) &&
                SanPham.getMaThuongHieu().contains(advancedKeyValue.get("ThuongHieu"))
            )
                result.add(SanPham);
        });    
        return result;
    }
    public ArrayList<ChiTietPhieuNhapDTO> advancedSearchThemSanPham(HashMap<String, String> advancedKeyValue)
        {
            ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
            TaoPhieuNhapJPanel.ChiTietPhieuNhapList.forEach(SanPham ->{
            SanPhamTrongChiTiet = quanlycuahanggiay.quanlycuahanggiay.SanPhamBUS.getByMaSanPham(SanPham.getMaSanPham());
            boolean TenSanPham = SanPhamTrongChiTiet.getTenSanPham().toLowerCase().contains(advancedKeyValue.get("TenSanPham").toLowerCase());
            if(!TenSanPham)
                    TenSanPham = removeAccent(SanPhamTrongChiTiet.getTenSanPham().toLowerCase()).contains(advancedKeyValue.get("TenSanPham").toLowerCase());
            if(SanPham.getMaSanPham().toLowerCase().contains(advancedKeyValue.get("MaSanPham").toLowerCase()) &&
                    TenSanPham &&
                    SanPhamTrongChiTiet.getMaMau().contains(advancedKeyValue.get("Mau")) &&
                    SanPhamTrongChiTiet.getMaSize().contains(advancedKeyValue.get("Size")) &&
                    SanPhamTrongChiTiet.getMaThuongHieu().contains(advancedKeyValue.get("ThuongHieu"))
                )
                    result.add(SanPham);
            });    
            return result;
        }
    public String Validation(ChiTietPhieuNhapDTO ChiTietPhieuNhap){
        if(!DONGIAPHIEUNHAP(ChiTietPhieuNhap.getDonGia())){
            return "Đơn giá không hợp lệ";
        }
        if(!SOLUONGPHIEUNHAP(ChiTietPhieuNhap.getSoLuong()))
         {
             return "Số lượng không hợp lệ";
         }
//        if(ChiTietPhieuNhap.getDonGia().equals("0"))
//        {
//            return "Đơn giá không hợp lệ";
//        }
//        if(ChiTietPhieuNhap.getSoLuong().equals("0"))
//        {
//            return "Số lượng không hợp lệ";
//        }
         return null;
    }
    public boolean Exists(String MaPhieuNhap){
        for (ChiTietPhieuNhapDTO ChiTietPhieuNhap : ChiTietPhieuNhapList) {
            if(ChiTietPhieuNhap.getMaPhieuNhap().toLowerCase().equals(MaPhieuNhap.toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public static ArrayList<ChiTietPhieuNhapDTO> getChiTietPhieuNhapByMaPhieuNhap(String MaPhieuNhap){
        ArrayList<ChiTietPhieuNhapDTO> array = new ArrayList<ChiTietPhieuNhapDTO>();
        for (ChiTietPhieuNhapDTO ChiTietPhieuNhap : ChiTietPhieuNhapList) {
            if(ChiTietPhieuNhap.getMaPhieuNhap().toLowerCase().equals(MaPhieuNhap.toLowerCase())){
                array.add(ChiTietPhieuNhap);
            }
        }
        return array;
    }
//    public boolean add(ChiTietPhieuNhapDTO ChiTietPhieuNhap){
//        if(Exists(ChiTietPhieuNhap.getMaPhieuNhap())){
//            GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", "Mã sản phẩm đã tồn tại", 1);
//            return false;
//        } else{
//            String Error = Validation(ChiTietPhieuNhap);
//            if(Error == null){
//                    ChiTietPhieuNhapDAO.add(ChiTietPhieuNhap);
//                    ChiTietPhieuNhapList = ChiTietPhieuNhapDAO.fetchAll();
//                    return true;
//            }
//            else
//                GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
//                return false;
//        }
//    }
    
//    public boolean edit(ChiTietPhieuNhapDTO ChiTietPhieuNhap){
//        String Error = Validation(ChiTietPhieuNhap);
//            if(Error == null){
//                ChiTietPhieuNhapDAO.edit(ChiTietPhieuNhap);
//                ChiTietPhieuNhapList = ChiTietPhieuNhapDAO.fetchAll();
//                return true;
//            }
//        GUI.SweetAlert.SweetAlert.showSweetAlert(new javax.swing.JFrame(), "Lỗi", Error, 1);
//            return false;
//    }
    
//    public static void getAuth(String MaChiTietPhieuNhap){
//        DAO.ChiTietPhieuNhapDAO.getAuth(MaChiTietPhieuNhap);
//    }
    
//    public ChiTietPhieuNhapDTO getByMaChiTietPhieuNhap(String MaChiTietPhieuNhap){
//        for (ChiTietPhieuNhapDTO ChiTietPhieuNhap : ChiTietPhieuNhapList) {
//            if(ChiTietPhieuNhap.getMaChiTietPhieuNhap().toLowerCase().equals(MaChiTietPhieuNhap.toLowerCase())){
//                return ChiTietPhieuNhap;
//            }
//        }
//        return null;
//    }
//    
//    public ArrayList<ChiTietPhieuNhapDTO> findByTenChiTietPhieuNhap(String TenChiTietPhieuNhap){
//        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
//        ChiTietPhieuNhapList.forEach(ChiTietPhieuNhap ->{
//            if(ChiTietPhieuNhap.getTenChiTietPhieuNhap().toLowerCase().contains(TenChiTietPhieuNhap.toLowerCase()))
//                result.add(ChiTietPhieuNhap);
//        });
//        return result;
//    }
//    
//    public ArrayList<ChiTietPhieuNhapDTO> findByMaChiTietPhieuNhap(String MaChiTietPhieuNhap){
//        ArrayList<ChiTietPhieuNhapDTO> result = new ArrayList<>();
//        ChiTietPhieuNhapList.forEach(ChiTietPhieuNhap ->{
//            if(ChiTietPhieuNhap.getMaChiTietPhieuNhap().toLowerCase().contains(MaChiTietPhieuNhap.toLowerCase()))
//                result.add(ChiTietPhieuNhap);
//        });
//        return result;
//    }
    
//    private String Validation(ChiTietPhieuNhapDTO ChiTietPhieuNhap){
//        if(!DonGia(ChiTietPhieuNhap.getDonGia())){
//            return "Đơn giá không hợp lệ";
//        }
//        if(!Ten(ChiTietPhieuNhap.getTenChiTietPhieuNhap())){
//            return "Tên sản phẩm không hợp lệ";
//        }
//        return null;
//    }
    
//    public String getLatest(){
//        return ChiTietPhieuNhapDAO.getLatest();
//    }
}
