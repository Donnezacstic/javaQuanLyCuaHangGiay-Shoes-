/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataThongKe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static quanlycuahanggiay.DateTime.DateStringToTimestamp;
import static quanlycuahanggiay.DateTime.TimestampToDateString;

/**
 *
 * @author HUUNHAN
 */
public class ChartData {
    public ChartData(){
    
    }
   public static int[] createDataChartPresent(String[] date){
        String[] month = new String[date.length];
        String[] year = new String[date.length];
        int[] dataOfMonths = {0,0,0,0,0,0,0,0,0,0,0,0};
        Date d = new Date();
        DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        String strYearPresent = DateStringToTimestamp(dateFormat.format(d));
        
        for (int i = 0; i<date.length;i++)
        {
            month[i] = TimestampToDateString(date[i], 1).split("/")[1];
            year[i] = TimestampToDateString(date[i],1).split("/")[2];
        }
        
        for(int i = 0; i<month.length;i++)
        {
            if(year[i].equals(TimestampToDateString(strYearPresent,1).split("/")[2]))
            {    
                switch(month[i])
                {
                    case "01": dataOfMonths[0]++;
                        break;
                    case "02": dataOfMonths[1]++;
                        break;
                    case "03": dataOfMonths[2]++;
                        break;
                    case "04": dataOfMonths[3]++;
                        break;
                    case "05": dataOfMonths[4]++;
                        break;
                    case "06": dataOfMonths[5]++;
                        break;
                    case "07": dataOfMonths[6]++;
                        break;
                    case "08": dataOfMonths[7]++;
                        break;
                    case "09": dataOfMonths[8]++;
                        break;
                    case "10": dataOfMonths[9]++;
                        break;
                    case "11": dataOfMonths[10]++;
                        break;
                    case "12": dataOfMonths[11]++;
                        break;
                }
            }
        }
        return dataOfMonths;
   }
    public static int[] createDataChartPrevious(String[] date){
        String[] month = new String[date.length];
        String[] year = new String[date.length];
        int[] dataOfMonths = {0,0,0,0,0,0,0,0,0,0,0,0};
        Date d = new Date();
        DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        String strYearPresent = DateStringToTimestamp(dateFormat.format(d));
        
        for (int i = 0; i<date.length;i++)
        {
            month[i] = TimestampToDateString(date[i], 1).split("/")[1];
            year[i] = TimestampToDateString(date[i],1).split("/")[2];
        }
        
        for(int i = 0; i<month.length;i++)
        {
            if(year[i].equals(Integer.toString(Integer.parseInt(TimestampToDateString(strYearPresent,1).split("/")[2])-1)))
            {    
                switch(month[i])
                {
                    case "01": dataOfMonths[0]++;
                        break;
                    case "02": dataOfMonths[1]++;
                        break;
                    case "03": dataOfMonths[2]++;
                        break;
                    case "04": dataOfMonths[3]++;
                        break;
                    case "05": dataOfMonths[4]++;
                        break;
                    case "06": dataOfMonths[5]++;
                        break;
                    case "07": dataOfMonths[6]++;
                        break;
                    case "08": dataOfMonths[7]++;
                        break;
                    case "09": dataOfMonths[8]++;
                        break;
                    case "10": dataOfMonths[9]++;
                        break;
                    case "11": dataOfMonths[10]++;
                        break;
                    case "12": dataOfMonths[11]++;
                        break;
                }
            }
        }
        return dataOfMonths;
   }
    public static int[] createDataChartPresentDoanhThu(String[] date){
        int[] doanhThuThang = {0,0,0,0,0,0,0,0,0,0,0,0};
        String[] month = new String[date.length];
        String[] year = new String[date.length];
        String[] tongTienDonHang = DataDoanhThu.getTongTienDonHang();
        Date d = new Date();
        DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
        String strYearPresent = DateStringToTimestamp(dateFormat.format(d));
        
        for (int i = 0; i<date.length;i++)
        {
            month[i] = TimestampToDateString(date[i], 1).split("/")[1];
            year[i] = TimestampToDateString(date[i],1).split("/")[2];
        }
        
        for(int i = 0; i<month.length;i++)
        {
            if(year[i].equals(TimestampToDateString(strYearPresent,1).split("/")[2]))
            {    
                switch(month[i])
                {
                    case "01": doanhThuThang[0]=doanhThuThang[0]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                    case "02": doanhThuThang[1]=doanhThuThang[1]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                    case "03": doanhThuThang[2]=doanhThuThang[2]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                    case "04": doanhThuThang[3]=doanhThuThang[3]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                    case "05": doanhThuThang[4]=doanhThuThang[4]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                    case "06": doanhThuThang[5]=doanhThuThang[5]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                    case "07": doanhThuThang[6]=doanhThuThang[6]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                    case "08": doanhThuThang[7]=doanhThuThang[7]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                    case "09": doanhThuThang[8]=doanhThuThang[8]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                    case "10": doanhThuThang[9]=doanhThuThang[9]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                    case "11": doanhThuThang[10]=doanhThuThang[10]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                    case "12": doanhThuThang[11]=doanhThuThang[11]+Integer.parseInt(tongTienDonHang[i]);
                        break;
                }
            }
        }
        return doanhThuThang;
   }
}

