/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO.PDF;

import DTO.PhieuNhapDTO;
import static IO.PDF.ObjectPDF.addPdfHeader;
import static IO.PDF.ObjectPDF.paragraph;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author khanh
 */
public class PhieuNhapPDF {
    private static String [] tableHeader = {"Mã Phiếu Nhập","Mã Nhân Viên", "Mã Nhà Cung Cấp", "Ngày Nhập","Tổng Tiền"};
    private static String titleReport = "BẢN BÁO CÁO PHIẾU NHẬP";
    public static Document addPdfBody( Document doc, ArrayList<PhieuNhapDTO> array ) throws DocumentException, IOException
    {
        File fontFile = new File("iText/vuArial.ttf");
        BaseFont bf = BaseFont.createFont(fontFile.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font = new Font(bf,10);
        paragraph = new Paragraph(titleReport,font);
        paragraph.setSpacingAfter(5);
        paragraph.setAlignment(Element.ALIGN_LEFT);
        doc.add(paragraph);
        PdfPTable table = new PdfPTable(5);
            Font red = new Font(bf, 12, Font.NORMAL, BaseColor.RED);
            Chunk header;
            for ( int i = 0 ; i < tableHeader.length ; i++ ){
                header = new Chunk(tableHeader[i], red);
                PdfPCell headerCell = new PdfPCell(new Paragraph(header));
                table.addCell(headerCell);
            }    
            for(int i=0;i<array.size();i++){
            PdfPCell data1 = new PdfPCell(new Paragraph(array.get(i).getMaPhieuNhap(),font));
            PdfPCell data2 = new PdfPCell(new Paragraph(array.get(i).getMaNhanVien(),font));
            PdfPCell data3 = new PdfPCell(new Paragraph(array.get(i).getMaNhaCungCap(),font));
            PdfPCell data4 = new PdfPCell(new Paragraph(array.get(i).getNgayNhap(),font));
            PdfPCell data5 = new PdfPCell(new Paragraph(array.get(i).getTongTien(),font));
                //Thêm data vào bảng.
            table.addCell(data1);
            table.addCell(data2);
            table.addCell(data3);
            table.addCell(data4);
            table.addCell(data5);
            }
            table.setSpacingBefore(20);
            table.setSpacingAfter(10);
            table.setWidthPercentage(100);
            doc.add(table);
            return doc;
    }
    public static void exportPdf( String path, ArrayList<PhieuNhapDTO> array ) throws IOException
    {
        Document doc = new Document(PageSize.A4.rotate(), 10f, 10f, 10f, 0f);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path));
            doc.open();
            doc = addPdfHeader(doc);
            doc = addPdfBody(doc,array);
            doc.close();
            System.out.println("6220-Success");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
