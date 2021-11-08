/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataThongKe;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;

/**
 *
 * @author HUUNHAN
 */
public class DataThongKeNhanVien {
    public static String[] getTrangThaiNhanVien(){
       String[] temp = new String[quanlycuahanggiay.quanlycuahanggiay.NhanVienBUS.NhanVienList.size()];
       for(int i=0;i<quanlycuahanggiay.quanlycuahanggiay.NhanVienBUS.NhanVienList.size();i++){
           temp[i]=quanlycuahanggiay.quanlycuahanggiay.NhanVienBUS.NhanVienList.get(i).getTrangThai();
       }
       return temp;
    }
    public static int ThongKeSoLuongNhanVienConLam(){
        int SoLuong=0;
        String[] TrangThaiNhanVien = getTrangThaiNhanVien();
        for(int i=0;i<TrangThaiNhanVien.length;i++){
            if(toLowerCase(TrangThaiNhanVien[i]).equals(toLowerCase("Còn làm"))){
                SoLuong++;
            }
        }
        return SoLuong;
    }
}
