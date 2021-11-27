package Model.BO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import Model.BEAN.File;
import Model.DAO.FileDAO;
import fr.opensagres.poi.xwpf.converter.core.XWPFConverterException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class FileBO {
	public boolean UploadFile(byte[] data, File file) {
		if (file != null) {
			File newFile = new FileDAO().UploadFile(file);
			new XuLy(data, newFile).start();
			return true;
		}
		return false;
	}
	
	public boolean UpdateFile(File file) {
		if (file == null)
			return false;
		return new FileDAO().UpdateFile(file);
	}
	
	public byte[] ConvertToFilePDF(byte[] docx) throws XWPFConverterException, IOException {
	    InputStream first_data = new ByteArrayInputStream(docx);
	    XWPFDocument doc = new XWPFDocument(first_data);
	    PdfOptions options = PdfOptions.create();
	    ByteArrayOutputStream pdf_file = new ByteArrayOutputStream();
	    PdfConverter.getInstance().convert(doc, pdf_file, options);
	    doc.write(pdf_file);
	    doc.close();
	    return pdf_file.toByteArray();
	}
	
	public File GetFilePDFFromDB(int id) throws SQLException
	{
		return new FileDAO().GetFilePDFFromDB(id);
	}
	
	public int CheckFile(File file, int id_user)
	{
		if(file == null)
			return 1; 
		else if(file.getUserID() != id_user)
			return -1; 
		return 0;
	}
	
	public void CloseConQuietly()
	{
		new FileDAO().CloseConQuietly();
	}
}

class XuLy extends Thread {
	String stringEnd = " - DocToPdf - LTM";
	byte[] data;
	File file;
	public XuLy(byte[] data, File file) {
		this.data = data;
		this.file = file;
	}
	public void run() {
		if (this.file != null) {
			try {	
				System.out.println("Converting " + this.file.getFileName() + " ...");
				// Convert file
				this.data = new FileBO().ConvertToFilePDF(this.data);
				// Update data
				this.file.setData(new javax.sql.rowset.serial.SerialBlob(this.data));	
				this.file.setFileName(this.file.getFileName() + stringEnd);
				this.file.setStatus(true);
				new FileBO().UpdateFile(this.file);
			} catch (Exception e) {
				System.out.println("Convert or set blob error: " + e.toString());
			}
			System.out.println("Convert " + this.file.getFileName() + " done !");
		}
	}
}