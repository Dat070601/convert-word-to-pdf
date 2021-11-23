package Model.BO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import Model.DAO.FileDAO;

public class FileBO {

	public void ConvertFileToPDF(String docPath)
	{
		 try {
			 	String pdfPath = ""; // tạo đường dẫn tới file pdf
			 						//(dẫn tới thư mục pdf_files trong source)
	            InputStream doc = new FileInputStream(new File(docPath));
	            XWPFDocument document = new XWPFDocument(doc);
	            PdfOptions options = PdfOptions.create();
	            OutputStream out = new FileOutputStream(new File(pdfPath));
	            PdfConverter.getInstance().convert(document, out, options); // tạo file pdf thành công
	            doc.close(); 
	            out.close();

	        } catch (FileNotFoundException ex) {
	            System.out.println(ex.getMessage());
	        } catch (IOException ex) {

	            System.out.println(ex.getMessage());
	        }
	}
	
	public byte[] DownloadFile()
	{
		return FileDAO.DowloadFile();
	}
}
