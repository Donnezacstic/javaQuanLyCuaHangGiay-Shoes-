/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataThongKe;
import GUI.Sweet.SweetComboBox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static quanlycuahanggiay.DateTime.DateStringToTimestamp;
import static quanlycuahanggiay.DateTime.TimestampToDateString;

/**
 *
 * @author HUUNHAN
 */
public class DataThongKeDonHang {
     public static String[] getNgayXuatDonHang(){
        String[] temp = new String[quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.size()];
        for(int i=0;i<quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.size();i++){
            temp[i]=quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.get(i).getNgayXuat();
        }
        return temp;
    } 
    public static String[] getMaDonHang(){
        String[] temp = new String[quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.size()];
        for(int i=0;i<quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.size();i++){
            temp[i]=quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.get(i).getMaDonHang();
        }
        return temp;
    }
    public static String[] getTrangThaiDonHang(){
        String[] temp = new String[quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.size()];
        for(int i=0;i<quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.size();i++){
            temp[i]=quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.get(i).getTrangThai();
        }
        return temp;
    }
    public static int[] ThongKeDonHangNgay(Date dateNgay){
        int[] thongKeDonHang = {0,0,0};
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strNgay = dateFormat.format(dateNgay);
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] trangThaiDonHang = getTrangThaiDonHang();
        for(int i =0;i<ngayXuatDonHang.length;i++){
            if(TimestampToDateString(ngayXuatDonHang[i],1).equals(strNgay)){
                thongKeDonHang[0]++;
                if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                    thongKeDonHang[1]++;
                }
                if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                    thongKeDonHang[2]++;
                }
            }
        }
        return thongKeDonHang;
    }
    public static int[] ThongKeDonHangTuDen(Date dateTu,Date dateDen){
        int[] thongKeDonHang = {0,0,0};
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDateTu= dateFormat.format(dateTu);
        String strDateDen= dateFormat.format(dateDen);
        
        long longDateTu = Long.parseLong(DateStringToTimestamp(strDateTu+" 00:00:01"));
        long longDateDen = Long.parseLong(DateStringToTimestamp(strDateDen+" 23:59:59"));
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] trangThaiDonHang = getTrangThaiDonHang();
        for(int i=0;i<ngayXuatDonHang.length;i++){
            if(Long.parseLong(ngayXuatDonHang[i]) >= longDateTu && Long.parseLong(ngayXuatDonHang[i]) <= longDateDen){
                thongKeDonHang[0]++;
                if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                    thongKeDonHang[1]++;
                }
                if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                    thongKeDonHang[2]++;
                }     
            }
        }
        return thongKeDonHang;
    }
    public static int[] ThongKeDonHangThang(SweetComboBox cbxNam,SweetComboBox cbxThang)
    {
        int[] thongKeDonHang = {0,0,0};
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] trangThaiDonHang = getTrangThaiDonHang();
        for(int i=0;i<ngayXuatDonHang.length;i++)
        {
            String year = TimestampToDateString(ngayXuatDonHang[i],1).split("/")[2];
            String month = TimestampToDateString(ngayXuatDonHang[i], 1).split("/")[1];
            if(cbxNam.getSelectedItem().toString().equals(year) && cbxThang.getSelectedItem().toString().equals(month))
            {
                thongKeDonHang[0]++;
                if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                    thongKeDonHang[1]++;
                }
                if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                    thongKeDonHang[2]++;
                }
            }
        }
        return thongKeDonHang;
    }
    public static int[] ThongKeDonHangQuy(SweetComboBox cbxNam,SweetComboBox cbxQuy)
    {
        int[] thongKeDonHang ={0,0,0};
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] trangThaiDonHang = getTrangThaiDonHang();
        switch(cbxQuy.getSelectedItem().toString()){
            case "1":
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/01/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeDonHang[0]++;
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                            thongKeDonHang[1]++;
                        }
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                            thongKeDonHang[2]++;
                        }
                    }
                }
                break;
            case "2":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/04/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeDonHang[0]++;
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                            thongKeDonHang[1]++;
                        }
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                            thongKeDonHang[2]++;
                        }
                    }
                }
                break;
            case "3":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/07/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeDonHang[0]++;
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                            thongKeDonHang[1]++;
                        }
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                            thongKeDonHang[2]++;
                        }
                    }
                }
                break;
            case "4":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/10/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeDonHang[0]++;
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                            thongKeDonHang[1]++;
                        }
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                            thongKeDonHang[2]++;
                        }
                    }
                }
                break;
        }
        return thongKeDonHang;
    }
    public static int[] ThongKeDonHangNam(SweetComboBox cbxNam){
        int[] thongKeDonHang = {0,0,0};
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] trangThaiDonHang = getTrangThaiDonHang();
        for(int i=0;i<ngayXuatDonHang.length;i++)
        {
            if(TimestampToDateString(ngayXuatDonHang[i],1).split("/")[2].equals(cbxNam.getSelectedItem().toString()))
            {
                thongKeDonHang[0]++;
                if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                    thongKeDonHang[1]++;
                }
                if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                    thongKeDonHang[2]++;
                }
            }
        }
        return thongKeDonHang;
    }
    public static int DonHangNgayHomNay(){
        int DonHangNgayHomNay=0;
        Date d = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        for(int i=0;i<ngayXuatDonHang.length;i++){
            if(Long.parseLong(DateStringToTimestamp(dateFormat.format(d)+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <= Long.parseLong(DateStringToTimestamp(dFormat.format(d)))){
                DonHangNgayHomNay++;
            }
        }
        return DonHangNgayHomNay;
    }
    public static float processBarDonHang(){
        float percent = 0;
        float DonHangMotNgay = 50;
        percent = ((float)DonHangNgayHomNay()*100)/DonHangMotNgay;
        return percent;
    }
    
    
    
    
    
    /*Số đơn hàng của 1 khách hàng*/
    public static String[] getMaKhachHangDonHang(){
       String[] temp = new String[quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.size()];
       for(int i=0;i<quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.size();i++){
           temp[i]=quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.get(i).getMaKhachHang();
       }
       return temp;
    }
    public static int[] SoLuongDonHangMoiKhachHang(String MaKhachHang){
        int[] SoLuong = {0};
        quanlycuahanggiay.quanlycuahanggiay.DonHangBUS.DonHangList.forEach(DonHang->{
           if(DonHang.getMaKhachHang().equals(MaKhachHang)){
                SoLuong[0]++;
           }
        });
        return SoLuong;
    }
    //Excel
    public static int[] ThongKeDonHangThang(String Nam,String Thang)
    {
        int[] thongKeDonHang = {0,0,0};
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] trangThaiDonHang = getTrangThaiDonHang();
        for(int i=0;i<ngayXuatDonHang.length;i++)
        {
            String year = TimestampToDateString(ngayXuatDonHang[i],1).split("/")[2];
            String month = TimestampToDateString(ngayXuatDonHang[i], 1).split("/")[1];
            if(Nam.equals(year) && Thang.equals(month))
            {
                thongKeDonHang[0]++;
                if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                    thongKeDonHang[1]++;
                }
                if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                    thongKeDonHang[2]++;
                }
            }
        }
        return thongKeDonHang;
    }
    public static int[] ThongKeDonHangQuy(String Nam,String Quy)
    {
        int[] thongKeDonHang ={0,0,0};
        String[] ngayXuatDonHang = getNgayXuatDonHang();
        String[] maDonHang = getMaDonHang();
        String[] trangThaiDonHang = getTrangThaiDonHang();
        switch(Quy){
            case "1":
                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/01/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+Nam+" 23:59:59")) )
                    {
                        thongKeDonHang[0]++;
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                            thongKeDonHang[1]++;
                        }
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                            thongKeDonHang[2]++;
                        }
                    }
                }
                break;
            case "2":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/04/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+Nam+" 23:59:59")) )
                    {
                        thongKeDonHang[0]++;
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                            thongKeDonHang[1]++;
                        }
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                            thongKeDonHang[2]++;
                        }
                    }
                }
                break;
            case "3":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/07/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+Nam+" 23:59:59")) )
                    {
                        thongKeDonHang[0]++;
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                            thongKeDonHang[1]++;
                        }
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                            thongKeDonHang[2]++;
                        }
                    }
                }
                break;
            case "4":

                for(int i=0;i<ngayXuatDonHang.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/10/"+Nam+" 00:00:01")) <= Long.parseLong(ngayXuatDonHang[i]) && Long.parseLong(ngayXuatDonHang[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+Nam+" 23:59:59")) )
                    {
                        thongKeDonHang[0]++;
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("1")){
                            thongKeDonHang[1]++;
                        }
                        if(trangThaiDonHang[i].toString().equalsIgnoreCase("0")){
                            thongKeDonHang[2]++;
                        }
                    }
                }
                break;
        }
        return thongKeDonHang;
    }
    public static ArrayList<String[]> ThongKeDonHangThangExcel( String nam )
    {
        ArrayList<String[]> result = new ArrayList<>();
        String[] thang = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        for(int i=0;i<thang.length;i++)
        {
            int[] temp = ThongKeDonHangThang(nam,thang[i]);

            String[] tungquy = {nam,thang[i],temp[0]+"",temp[1]+"",+temp[2]+""};
            result.add(tungquy);
        }
        return result;
    }
    public static ArrayList<String[]> ThongKeDonHangQuyExcel( String nam )
    {
        ArrayList<String[]> result = new ArrayList<>();
        String[] quy = {"1","2","3","4"};
        for(int i=0;i<quy.length;i++)
        {
            int[] temp = ThongKeDonHangQuy(nam,quy[i]);
            String[] tungquy = {nam,quy[i],temp[0]+"",temp[1]+"",temp[2]+""};
            result.add(tungquy);
        }
        return result;
    }
}