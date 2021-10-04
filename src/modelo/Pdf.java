
package modelo;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

/**
 *
 * @author williams parra
 * @version 1.0
 * @date 03/19/2021
 */

public class Pdf {
    
        public static int GuardarPDF(JFreeChart grafico, String titulo, String comentario) {
        int r = 0;
        Document documento = new Document();

        try {
            String ruta = System.getProperty("user.home");
            //ir a escritorio + nombre de archivo
            java.util.Date fecha = new java.util.Date();
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/" + titulo + new SimpleDateFormat("_ddMMyyyyHHmmss").format(fecha) + ".pdf"));
            //abrir documento pdf
            documento.open();
            //Generar Encabezado            
            Paragraph fechaInforme = new Paragraph();
            fechaInforme.setAlignment(Paragraph.ALIGN_CENTER);
            fechaInforme.add(Chunk.NEWLINE);
            fechaInforme.setFont(FontFactory.getFont("Tahoma", 12, Font.BOLD, BaseColor.DARK_GRAY));
            fechaInforme.add("Fecha: " + new SimpleDateFormat("dd-MM-yyyy").format(fecha) + "\n\n");
            Paragraph tituloInforme = new Paragraph();
            tituloInforme.setAlignment(Paragraph.ALIGN_CENTER);
            tituloInforme.add(Chunk.NEWLINE);
            tituloInforme.setFont(FontFactory.getFont("Tahoma", 24, Font.BOLD, BaseColor.DARK_GRAY));
            tituloInforme.add(titulo + "\n\n\n");
            //crear tabla para encabezado
            PdfPTable encPdf = new PdfPTable(3);
            encPdf.setWidthPercentage(100);
            encPdf.setWidths(new float[]{20, 75, 30});
            encPdf.getDefaultCell().setBorder(0);
            encPdf.setHorizontalAlignment(Element.ALIGN_LEFT);
            //agregar imagen
            encPdf.addCell("");
            encPdf.addCell("");
            encPdf.addCell(fechaInforme);
            //agregar imagen y encabezado a documento            
            documento.add(encPdf);
            documento.add(tituloInforme);

            //crear titutlo pagina
            Paragraph tituloPagina = new Paragraph();
            tituloPagina.setAlignment(Paragraph.ALIGN_CENTER);
            tituloPagina.add(Chunk.NEWLINE);
            tituloPagina.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            tituloPagina.add(titulo + "\n\n");

            //crear imagen de grafico
            String direccionImagen = "src/img/image.png";
            ChartUtilities.saveChartAsPNG(new File(direccionImagen), grafico, 600, 800);
            Image imgGrafico = Image.getInstance(direccionImagen);
            imgGrafico.scaleToFit(400, 600);
            imgGrafico.setAlignment(Chunk.ALIGN_LEFT);

            imgGrafico.setAlignment(Element.ALIGN_CENTER);          
            
            //generando conclusion
            
            Paragraph conclusion = new Paragraph();
            conclusion.setAlignment(Paragraph.ALIGN_CENTER);
            conclusion.add(Chunk.NEWLINE);
            conclusion.setFont(FontFactory.getFont("Tahoma", 12, Font.BOLD, BaseColor.DARK_GRAY));
            conclusion.add("\n"+ comentario + "\n");
            conclusion.setAlignment(Element.ALIGN_CENTER);
            
            //agregando conclusion
            
            documento.add(imgGrafico);
            documento.add(conclusion);
            documento.newPage();
            
            //cerrar documento
            documento.close();
            r = 1;
        } catch (Exception e) {
            System.out.println("error : "+e.getMessage());
        }
        return r;
    }
    
}
