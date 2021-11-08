/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahanggiay;

import com.toedter.calendar.JDateChooser;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class DateTime {

    public static String TYPE = "dd/MM/yyyy HH:mm:ss";
    private static DateFormat dateFormat = new SimpleDateFormat(TYPE);

    public static String getCurrentTimestamp() {
        return Long.toString(Instant.now().plusSeconds(25200).getEpochSecond());
    }

    public static String getCurrenDateString(int option) {
        return TimestampToDateString(getCurrentTimestamp(), option);
    }

    public static String DateStringToTimestamp(String date) {
        //Specified date format : DD/MM/YYYY : hh:mm:ss
        try {
            if(!date.equals(":")) date=date.concat(" 00:00:00");
            return Long.toString(dateFormat.parse(date).toInstant().plusSeconds(25200).getEpochSecond());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String TimestampToDateString(String Timestamp, int option) {
        if (option == 1) /**
         * Return relative date *
         **/
        {
            return dateFormat.format(Date.from(Instant.ofEpochSecond(Long.parseLong(Timestamp) - 25200))).split(" ")[0];
        }

        if (option == 0) /**
         * Return absolute date *
         **/
        {
            return dateFormat.format(Date.from(Instant.ofEpochSecond(Long.parseLong(Timestamp) - 25200)));
        }

        return dateFormat.format(Date.from(Instant.ofEpochSecond(Long.parseLong(Timestamp) - 25200))).split(" ")[1];
    }

    public static void main(String[] args) {
        System.out.print(DateTime.DateStringToTimestamp("1/1/2020"));
        
    }
    public static void setDate(JDateChooser dateNgay,JDateChooser dateTu,JDateChooser dateDen){
        dateTu.setDateFormatString("dd/MM/yyyy");
        dateDen.setDateFormatString("dd/MM/yyyy");
        dateTu.setDate(new Date());
        dateDen.setDate(dateTu.getDate());
        dateTu.setMaxSelectableDate(new Date());
        dateDen.setMaxSelectableDate(new Date());
        
        dateNgay.setDateFormatString("dd/MM/yyyy");
        dateNgay.setDate(new Date());
        dateNgay.setMaxSelectableDate(new Date());

    }
}   
