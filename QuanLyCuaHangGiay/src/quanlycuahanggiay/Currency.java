/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahanggiay;

/**
 *
 * @author admin
 */
import java.text.NumberFormat;
import java.util.Locale;
public class Currency {
    
    public Currency(){
    
}
    public static String changeCurrency(String number){
        double currency = Double.parseDouble(number);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        number = vn.format(currency);
        return number;
    }

}
