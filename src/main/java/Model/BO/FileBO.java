package Model.BO;

import java.io.ByteArrayOutputStream;
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

	public byte[] ConvertFileToPDF(String docPath)
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
	            return loadFile(pdfPath); // tao mang byte[] cua file PDF
	        } catch (FileNotFoundException ex) {
	            System.out.println(ex.getMessage());
	            return null;
	        } catch (IOException ex) {
	            System.out.println(ex.getMessage());
	            return null;
	        }
	}
	
	public static byte[] readFully(InputStream stream) throws IOException
	{
	    byte[] buffer = new byte[8192];
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    int bytesRead;
	    while ((bytesRead = stream.read(buffer)) != -1)
	    {
	        baos.write(buffer, 0, bytesRead);
	    }
	    return baos.toByteArray();
	}
	
	public static byte[] loadFile(String sourcePath) throws IOException
	{	
	    InputStream inputStream = null;
	    try 
	    {
	        inputStream = new FileInputStream(sourcePath);
	        return readFully(inputStream);
	    } 
	    finally
	    {
	        if (inputStream != null)
	        {
	            inputStream.close();
	        }
	    }
	}
	
	public byte[] DownloadFile()
	{
		return FileDAO.DowloadFile();
	}
}
