package Model.BO;

import Model.DAO.FileDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class FileBO {
	public static void ConvertFileToPDF(String docPath, String pdfPath)
	{
//		  InputStream doc = new FileInputStream(new File(docPath));
//          XWPFDocument document = new XWPFDocument(doc);
//          PdfOptions options = PdfOptions.create();
//          OutputStream out = new FileOutputStream(new File(pdfPath));
//          PdfConverter.getInstance().convert(document, out, options);
		docPath = "D:\\Semester 5\\Lập trình mạng\\TEST.docx";
		try {
			FileInputStream docFile = new FileInputStream(docPath);
			XWPFDocument  docuemnt = new XWPFDocument(docFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static byte[] DownloadFile()
	{
		return FileDAO.DowloadFile();
	}
}
