
package IO.PDF;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class ObjectPDF {
    public static Paragraph paragraph;
    private static String cooperationName = "SAGO CO-OPERATION ®";
    private static String companyName = "SAGOBOOK™";
    private static String companyAddress = "273 An Dương Vương ,Phường 3, Quận 5, TP.Hồ Chí Minh, Việt Nam";
    private static String companyEmail = "sagobo@sagocorp.com.vn";
    private static String companyPhone = "099-555-666";
    private static String logoSource = "src/images/logo.png";
    private static String unicodeFontSource = "iText/vuArial.ttf";
    public static Document addPdfHeader( Document doc ) throws DocumentException, IOException
    {
        Image logo = Image.getInstance(logoSource);
        File fontFile = new File(unicodeFontSource);
        BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf,10);
        Font blue = new Font(bf, 10, Font.NORMAL, BaseColor.BLUE);       
        Calendar cal = new GregorianCalendar();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dates = (day+"/"+(month+1)+"/"+year);
        paragraph = new Paragraph(cooperationName);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        paragraph.setSpacingAfter(10);
        logo.setAbsolutePosition(5, 500);
        logo.setAlignment(Element.ALIGN_LEFT);
        paragraph.add(logo);
        doc.add(paragraph);
        paragraph = new Paragraph(companyName);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        paragraph.setSpacingAfter(10);
        doc.add(paragraph);
        paragraph = new Paragraph(companyAddress,blue);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        doc.add(paragraph);
        paragraph = new Paragraph(companyEmail,blue);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        doc.add(paragraph);
        paragraph = new Paragraph(companyPhone,blue);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        paragraph.setSpacingAfter(20);
        doc.add(paragraph);
        doc.add(new LineSeparator(0.5f, 100, null, 0, -5));
        paragraph = new Paragraph("NGÀY : "+dates,font);
        paragraph.setSpacingBefore(30);
        paragraph.setSpacingAfter(5);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        doc.add(paragraph);
        return doc;
    }
}
