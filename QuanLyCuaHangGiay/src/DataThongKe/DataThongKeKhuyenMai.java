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
import javax.swing.JComboBox;
import static quanlycuahanggiay.DateTime.DateStringToTimestamp;
import static quanlycuahanggiay.DateTime.TimestampToDateString;


/**
 *
 * @author HUUNHAN
 */
public class DataThongKeKhuyenMai {
    public static String[] getNgayTao(){
        String[] temp = new String[quanlycuahanggiay.quanlycuahanggiay.ChuongTrinhKhuyenMaiBUS.ChuongTrinhKhuyenMaiList.size()];
        
        for(int i=0;i<quanlycuahanggiay.quanlycuahanggiay.ChuongTrinhKhuyenMaiBUS.ChuongTrinhKhuyenMaiList.size();i++){
            temp[i]=quanlycuahanggiay.quanlycuahanggiay.ChuongTrinhKhuyenMaiBUS.ChuongTrinhKhuyenMaiList.get(i).getNgayTao();
        }
        return temp;
    }
    public static String[] getNgayChiTietKhuyenMai(){
        String[] temp = new String[quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.ChiTietKhuyenMaiList.size()];
        for(int i=0;i<quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.ChiTietKhuyenMaiList.size();i++){
            temp[i]=quanlycuahanggiay.quanlycuahanggiay.ChiTietKhuyenMaiBUS.ChiTietKhuyenMaiList.get(i).getThoiGianBatDauSuDung();
        }
        return temp;
    }
    public static int[] ThongKeKhuyenMaiNgay(Date dateNgay){
        int[] thongKeKhuyenMai ={0,0};
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strNgay = dateFormat.format(dateNgay);  
        String[] ngayChuongTrinhKM = getNgayTao();
        String[] ngayChiTietKM = getNgayChiTietKhuyenMai();
        for(int i = 0;i<ngayChuongTrinhKM.length;i++){
            if(TimestampToDateString(ngayChuongTrinhKM[i],1).equals(strNgay)){
                thongKeKhuyenMai[0]++;
            }
        }
        for(int i=0;i<ngayChiTietKM.length;i++){
            if(TimestampToDateString(ngayChiTietKM[i],1).equals(strNgay)){
                thongKeKhuyenMai[1]++;
            }
        }
        return thongKeKhuyenMai;
    }
    public static int[] ThongKeKhuyenMaiTuDen(Date dateTu,Date dateDen){
        int[] thongKeKhuyenMai = {0,0};
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDateTu= dateFormat.format(dateTu);
        String strDateDen= dateFormat.format(dateDen);
        
        long longDateTu = Long.parseLong(DateStringToTimestamp(strDateTu+" 00:00:01"));
        long longDateDen = Long.parseLong(DateStringToTimestamp(strDateDen+" 23:59:59"));
        
        String[] ngayChuongTrinhKM = getNgayTao();
        String[] ngayChiTietKM = getNgayChiTietKhuyenMai();
        for(int i = 0;i<ngayChuongTrinhKM.length;i++){
            if(longDateTu <= Long.parseLong(ngayChuongTrinhKM[i]) && Long.parseLong(ngayChuongTrinhKM[i]) <= longDateDen){
                thongKeKhuyenMai[0]++;
            }
        }
        for(int i=0;i<ngayChiTietKM.length;i++){
            if(longDateTu <= Long.parseLong(ngayChiTietKM[i]) && Long.parseLong(ngayChiTietKM[i]) <= longDateDen){
                thongKeKhuyenMai[1]++;
            }
        }
        return thongKeKhuyenMai;
    }
    public static int[] ThongKeKhuyenMaiThang(SweetComboBox cbxNam,SweetComboBox cbxThang){
        int[] thongKeKhuyenMai = {0,0};
        String[] ngayChuongTrinhKM=getNgayTao();
        String[] ngayChiTietKM=getNgayChiTietKhuyenMai();
        
        for(int i=0;i<ngayChuongTrinhKM.length;i++)
        {
            String year = TimestampToDateString(ngayChuongTrinhKM[i],1).split("/")[2];
            String month = TimestampToDateString(ngayChuongTrinhKM[i], 1).split("/")[1];
            if(cbxNam.getSelectedItem().toString().equals(year) && cbxThang.getSelectedItem().toString().equals(month))
            {
                thongKeKhuyenMai[0]++;
            }
        }
        for(int i=0;i<ngayChiTietKM.length;i++)
        {
            String year = TimestampToDateString(ngayChiTietKM[i],1).split("/")[2];
            String month = TimestampToDateString(ngayChiTietKM[i], 1).split("/")[1];
            if(cbxNam.getSelectedItem().toString().equals(year) && cbxThang.getSelectedItem().toString().equals(month))
            {
                thongKeKhuyenMai[1]++;
            }
        }
        return thongKeKhuyenMai;
    }
    public static int[] ThongKeKhuyenMaiQuy(JComboBox cbxNam,JComboBox cbxQuy){
        int[] thongKeKhuyenMai = {0,0};
        String[] ngayChuongTrinhKM=getNgayTao();
        String[] ngayChiTietKM=getNgayChiTietKhuyenMai();

        switch(cbxQuy.getSelectedItem().toString()){
            case "1":
                for(int i=0;i<ngayChuongTrinhKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/01/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayChuongTrinhKM[i]) && Long.parseLong(ngayChuongTrinhKM[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[0]++;
                    }
                }
                for(int i=0;i<ngayChiTietKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/01/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayChiTietKM[i]) && Long.parseLong(ngayChiTietKM[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[1]++;
                    }
                }
                break;
            case "2":

                for(int i=0;i<ngayChuongTrinhKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/04/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayChuongTrinhKM[i]) && Long.parseLong(ngayChuongTrinhKM[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[0]++;
                    }
                }
                for(int i=0;i<ngayChiTietKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/04/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayChiTietKM[i]) && Long.parseLong(ngayChiTietKM[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[1]++;
                    }
                }
                break;
            case "3":

                for(int i=0;i<ngayChuongTrinhKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/07/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayChuongTrinhKM[i]) && Long.parseLong(ngayChuongTrinhKM[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[0]++;
                    }
                }
                for(int i=0;i<ngayChiTietKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/07/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayChiTietKM[i]) && Long.parseLong(ngayChiTietKM[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[1]++;
                    }
                }
                break;
            case "4":

                for(int i=0;i<ngayChuongTrinhKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/10/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayChuongTrinhKM[i]) && Long.parseLong(ngayChuongTrinhKM[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[0]++;
                    }
                }
                for(int i=0;i<ngayChiTietKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/10/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngayChiTietKM[i]) && Long.parseLong(ngayChiTietKM[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[1]++;
                    }
                }
                break;
        }
        return thongKeKhuyenMai;
    }
    public static int[] ThongKeKhuyenMaiNam(JComboBox cbxNam){
        int[] thongKePhieuNhap = {0,0};
        String[] ngayChuongTrinhKM=getNgayTao();
        String[] ngayChiTietKM=getNgayChiTietKhuyenMai();
        
        for(int i=0;i<ngayChuongTrinhKM.length;i++)
        {
            if(TimestampToDateString(ngayChuongTrinhKM[i],1).split("/")[2].equals(cbxNam.getSelectedItem().toString()))
            {
                thongKePhieuNhap[0]++;
            }
        }
        for(int i=0;i<ngayChiTietKM.length;i++)
        {
            if(TimestampToDateString(ngayChiTietKM[i],1).split("/")[2].equals(cbxNam.getSelectedItem().toString()))
            {
                thongKePhieuNhap[1]++;
            }
        }
        
        return thongKePhieuNhap;
    }
    //Excel
    public static int[] ThongKeKhuyenMaiThang(String Nam,String Thang){
        int[] thongKeKhuyenMai = {0,0};
        String[] ngayChuongTrinhKM=getNgayTao();
        String[] ngayChiTietKM=getNgayChiTietKhuyenMai();
        
        for(int i=0;i<ngayChuongTrinhKM.length;i++)
        {
            String year = TimestampToDateString(ngayChuongTrinhKM[i],1).split("/")[2];
            String month = TimestampToDateString(ngayChuongTrinhKM[i], 1).split("/")[1];
            if(Nam.equals(year) && Thang.equals(month))
            {
                thongKeKhuyenMai[0]++;
            }
        }
        for(int i=0;i<ngayChiTietKM.length;i++)
        {
            String year = TimestampToDateString(ngayChiTietKM[i],1).split("/")[2];
            String month = TimestampToDateString(ngayChiTietKM[i], 1).split("/")[1];
            if(Nam.equals(year) && Thang.equals(month))
            {
                thongKeKhuyenMai[1]++;
            }
        }
        return thongKeKhuyenMai;
    }
    public static int[] ThongKeKhuyenMaiQuy(String Nam,String Quy)
    {
        int[] thongKeKhuyenMai = {0,0};
        String[] ngayChuongTrinhKM=getNgayTao();
        String[] ngayChiTietKM=getNgayChiTietKhuyenMai();

        switch(Quy.toString()){
            case "1":
                for(int i=0;i<ngayChuongTrinhKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/01/"+Nam+" 00:00:01")) <= Long.parseLong(ngayChuongTrinhKM[i]) && Long.parseLong(ngayChuongTrinhKM[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+Nam+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[0]++;
                    }
                }
                for(int i=0;i<ngayChiTietKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/01/"+Nam+" 00:00:01")) <= Long.parseLong(ngayChiTietKM[i]) && Long.parseLong(ngayChiTietKM[i]) <=  Long.parseLong(DateStringToTimestamp("31/03/"+Nam+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[1]++;
                    }
                }
                break;
            case "2":

                for(int i=0;i<ngayChuongTrinhKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/04/"+Nam+" 00:00:01")) <= Long.parseLong(ngayChuongTrinhKM[i]) && Long.parseLong(ngayChuongTrinhKM[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+Nam+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[0]++;
                    }
                }
                for(int i=0;i<ngayChiTietKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/04/"+Nam+" 00:00:01")) <= Long.parseLong(ngayChiTietKM[i]) && Long.parseLong(ngayChiTietKM[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+Nam+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[1]++;
                    }
                }
                break;
            case "3":

                for(int i=0;i<ngayChuongTrinhKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/07/"+Nam+" 00:00:01")) <= Long.parseLong(ngayChuongTrinhKM[i]) && Long.parseLong(ngayChuongTrinhKM[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+Nam+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[0]++;
                    }
                }
                for(int i=0;i<ngayChiTietKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/07/"+Nam+" 00:00:01")) <= Long.parseLong(ngayChiTietKM[i]) && Long.parseLong(ngayChiTietKM[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+Nam+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[1]++;
                    }
                }
                break;
            case "4":

                for(int i=0;i<ngayChuongTrinhKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/10/"+Nam+" 00:00:01")) <= Long.parseLong(ngayChuongTrinhKM[i]) && Long.parseLong(ngayChuongTrinhKM[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+Nam+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[0]++;
                    }
                }
                for(int i=0;i<ngayChiTietKM.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/10/"+Nam+" 00:00:01")) <= Long.parseLong(ngayChiTietKM[i]) && Long.parseLong(ngayChiTietKM[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+Nam+" 23:59:59")) )
                    {
                        thongKeKhuyenMai[1]++;
                    }
                }
                break;
        }
        return thongKeKhuyenMai;
    }
    public static ArrayList<String[]> ThongKeKhuyenMaiThangExcel( String nam )
    {
        ArrayList<String[]> result = new ArrayList<>();
        String[] thang = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        for(int i=0;i<thang.length;i++)
        {
            int[] temp = ThongKeKhuyenMaiThang(nam,thang[i]);

            String[] tungquy = {nam,thang[i],temp[0]+"",temp[1]+""};
            result.add(tungquy);
        }
        return result;
    }
    public static ArrayList<String[]> ThongKeKhuyenMaiQuyExcel( String nam )
    {
        ArrayList<String[]> result = new ArrayList<>();
        String[] quy = {"1","2","3","4"};
        for(int i=0;i<quy.length;i++)
        {
            int[] temp = ThongKeKhuyenMaiQuy(nam,quy[i]);
            String[] tungquy = {nam,quy[i],temp[0]+"",temp[1]+""};
            result.add(tungquy);
        }
        return result;
    }
}
