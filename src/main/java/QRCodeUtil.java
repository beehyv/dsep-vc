import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;


import java.io.File;
import java.io.IOException;

/** This ads QR Code to the file */
public class QRCodeUtil {

    public static void main(String[] args) throws IOException {

        // Path to existing PDF file
        String src = "Resume.pdf";

        // Path to QR code image file
        String qrCodeImagePath = "qrcode.png";

        // Path to output PDF file
        String dest = "output.pdf";

        // Load existing PDF document
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(src), new PdfWriter(dest));

        // Create document object
        Document document = new Document(pdfDoc);

        // Load QR code image
        ImageData qrCodeImageData = ImageDataFactory.create(qrCodeImagePath);

        // Create QR code image element
        Image qrCodeImage = new Image(qrCodeImageData);

         // Set size of QR code image
      qrCodeImage.scaleAbsolute(100, 100);

        //  // Get page size
           PageSize pageSize = pdfDoc.getDefaultPageSize();
 
        // //  // Set position of QR code image at top center of first page
        //   qrCodeImage.setFixedPosition(pageSize.getWidth() / 2 - qrCodeImage.getImageScaledWidth() / 2, pageSize.getTop() - 50);
 
           qrCodeImage.setFixedPosition(pageSize.getRight()-200,pageSize.getTop() - 200);
 
        

        // Add QR code image to document
        document.add(qrCodeImage);

        // Close the document
        document.close();
    }
}


