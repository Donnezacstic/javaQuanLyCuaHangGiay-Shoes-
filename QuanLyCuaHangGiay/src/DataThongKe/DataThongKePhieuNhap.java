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
public class DataThongKePhieuNhap {
    public static String[] getNgayPhieuNhap(){
        String[] temp = new String[quanlycuahanggiay.quanlycuahanggiay.PhieuNhapBUS.PhieuNhapList.size()];
        for(int i=0;i<quanlycuahanggiay.quanlycuahanggiay.PhieuNhapBUS.PhieuNhapList.size();i++)
        {
            temp[i]=quanlycuahanggiay.quanlycuahanggiay.PhieuNhapBUS.PhieuNhapList.get(i).getNgayNhap();
        }
        return temp;
    }
    public static String[] getTongTien(){
        String[] temp = new String[quanlycuahanggiay.quanlycuahanggiay.PhieuNhapBUS.PhieuNhapList.size()];
        for(int i=0;i<quanlycuahanggiay.quanlycuahanggiay.PhieuNhapBUS.PhieuNhapList.size();i++)
        {
            temp[i]=quanlycuahanggiay.quanlycuahanggiay.PhieuNhapBUS.PhieuNhapList.get(i).getTongTien();
        }
        return temp;
    }
    public static int[] ThongKePhieuNhapNgay(Date dateName){
        int[] thongKePhieuNhap = {0,0};
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strNgay = dateFormat.format(dateName);  
        
        String[] ngay=getNgayPhieuNhap();
        String[] tongTien=getTongTien();
      
        for(int i=0;i<ngay.length;i++){
            if(TimestampToDateString(ngay[i],1).equals(strNgay))
            {
                thongKePhieuNhap[0]++;
                thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
            }
        }
        
        return thongKePhieuNhap;
    }
    public static int[] ThongKePhieuNhapTuDen(Date dateTu,Date dateDen){
        int[] thongKePhieuNhap = {0,0};
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDateTu= dateFormat.format(dateTu);
        String strDateDen= dateFormat.format(dateDen);
        
        long longDateTu = Long.parseLong(DateStringToTimestamp(strDateTu+" 00:00:01"));
        long longDateDen = Long.parseLong(DateStringToTimestamp(strDateDen+" 23:59:59"));
        
        String[] ngay=getNgayPhieuNhap();
        String[] tongTien=getTongTien();
        
        for(int i=0;i<ngay.length;i++)
        {
            if( longDateTu <= Long.parseLong(ngay[i]) && Long.parseLong(ngay[i]) <= longDateDen)
            {
                thongKePhieuNhap[0]++;
                thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
            }
        }
        
        return thongKePhieuNhap;
    }
    public static int[] ThongKePhieuNhapThang(SweetComboBox cbxNam,SweetComboBox cbxThang)
    {
        int[] thongKePhieuNhap = {0,0};
        String[] ngay=getNgayPhieuNhap();
        String[] tongTien=getTongTien();
        
        for(int i=0;i<ngay.length;i++)
        {
            String year = TimestampToDateString(ngay[i],1).split("/")[2];
            String month = TimestampToDateString(ngay[i], 1).split("/")[1];
            if(cbxNam.getSelectedItem().toString().equals(year) && cbxThang.getSelectedItem().toString().equals(month))
            {
                thongKePhieuNhap[0]++;
                thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
            }
        }
        
        return thongKePhieuNhap;
    }
    public static int[] ThongKePhieuNhapQuy(SweetComboBox cbxNam,SweetComboBox cbxQuy)
    {
        int[] thongKePhieuNhap = {0,0};
        String[] ngay=getNgayPhieuNhap();
        String[] tongTien=getTongTien();

        switch(cbxQuy.getSelectedItem().toString()){
            case "1":
                for(int i=0;i<ngay.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/01/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngay[i]) && Long.parseLong(ngay[i]) <= Long.parseLong(DateStringToTimestamp("31/03/"+cbxNam.getSelectedItem().toString()+" 23:59:59")))
                    {
                        thongKePhieuNhap[0]++;
                        thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
                    }
                }
                break;
            case "2":

                for(int i=0;i<ngay.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/04/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngay[i]) && Long.parseLong(ngay[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKePhieuNhap[0]++;
                        thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
                    }
                }
                break;
            case "3":

                for(int i=0;i<ngay.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/07/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngay[i]) && Long.parseLong(ngay[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKePhieuNhap[0]++;
                        thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
                    }
                }
               break;
            case "4":
                for(int i=0;i<ngay.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/10/"+cbxNam.getSelectedItem().toString()+" 00:00:01")) <= Long.parseLong(ngay[i]) && Long.parseLong(ngay[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+cbxNam.getSelectedItem().toString()+" 23:59:59")) )
                    {
                        thongKePhieuNhap[0]++;
                        thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
                    }
                }
                break;
        }
        return thongKePhieuNhap;
    }
    public static int[] ThongKePhieuNhapNam(SweetComboBox cbxNam)
    {
        int[] thongKePhieuNhap = {0,0};
        String[] ngay=getNgayPhieuNhap();
        String[] tongTien=getTongTien();
        
        for(int i=0;i<ngay.length;i++)
        {
            if(TimestampToDateString(ngay[i],1).split("/")[2].equals(cbxNam.getSelectedItem().toString()))
            {
                thongKePhieuNhap[0]++;
                thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
            }
        }
        
        return thongKePhieuNhap;
    }
    //Excel
    public static int[] ThongKePhieuNhapThang( String Nam, String Thang)
    {
        int[] thongKePhieuNhap = {0,0};
        String[] ngay=getNgayPhieuNhap();
        String[] tongTien=getTongTien();
        
        for(int i=0;i<ngay.length;i++)
        {
            String year = TimestampToDateString(ngay[i],1).split("/")[2];
            String month = TimestampToDateString(ngay[i], 1).split("/")[1];
            if(Nam.equals(year) && Thang.equals(month))
            {
                thongKePhieuNhap[0]++;
                thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
            }
        }
        
        return thongKePhieuNhap;
    }
    public static int[] ThongKePhieuNhapQuy( String Nam, String Quy)
    {
        int[] thongKePhieuNhap = {0,0};
        String[] ngay=getNgayPhieuNhap();
        String[] tongTien=getTongTien();

        switch(Quy){
            case "1":
                for(int i=0;i<ngay.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/01/"+Nam+" 00:00:01")) <= Long.parseLong(ngay[i]) && Long.parseLong(ngay[i]) <= Long.parseLong(DateStringToTimestamp("31/03/"+Nam+" 23:59:59")))
                    {
                        thongKePhieuNhap[0]++;
                        thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
                    }
                }
                break;
            case "2":

                for(int i=0;i<ngay.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/04/"+Nam+" 00:00:01")) <= Long.parseLong(ngay[i]) && Long.parseLong(ngay[i]) <=  Long.parseLong(DateStringToTimestamp("30/06/"+Nam+" 23:59:59")) )
                    {
                        thongKePhieuNhap[0]++;
                        thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
                    }
                }
                break;
            case "3":

                for(int i=0;i<ngay.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/07/"+Nam+" 00:00:01")) <= Long.parseLong(ngay[i]) && Long.parseLong(ngay[i]) <=  Long.parseLong(DateStringToTimestamp("30/09/"+Nam+" 23:59:59")) )
                    {
                        thongKePhieuNhap[0]++;
                        thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
                    }
                }
               break;
            case "4":
                for(int i=0;i<ngay.length;i++)
                {
                    if(Long.parseLong(DateStringToTimestamp("01/10/"+Nam+" 00:00:01")) <= Long.parseLong(ngay[i]) && Long.parseLong(ngay[i]) <=  Long.parseLong(DateStringToTimestamp("31/12/"+Nam+" 23:59:59")) )
                    {
                        thongKePhieuNhap[0]++;
                        thongKePhieuNhap[1]=thongKePhieuNhap[1]+Integer.parseInt(tongTien[i]);
                    }
                }
                break;
        }
        return thongKePhieuNhap;
    }
    public static ArrayList<String[]> ThongKePhieuNhapThangExcel( String nam )
    {
        ArrayList<String[]> result = new ArrayList<>();
        String[] thang = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        for(int i=0;i<thang.length;i++)
        {
            int[] temp = ThongKePhieuNhapThang(nam,thang[i]);
            String[] tungquy = {nam,thang[i],temp[0]+"",temp[1]+""};
            result.add(tungquy);
        }
        return result;
    }
    public static ArrayList<String[]> ThongKePhieuNhapQuyExcel( String nam )
    {
        ArrayList<String[]> result = new ArrayList<>();
        String[] quy = {"1","2","3","4"};
        for(int i=0;i<quy.length;i++)
        {
            int[] temp = ThongKePhieuNhapQuy(nam,quy[i]);
            String[] tungquy = {nam,quy[i],temp[0]+"",temp[1]+""};
            result.add(tungquy);
        }
        return result;
    }
}
