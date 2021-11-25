package Model.BO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.SQLException;

import com.aspose.words.*;

import Model.BEAN.File;
import Model.DAO.FileDAO;

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
	
	public byte[] ConvertFileToPDF(byte[] docFile) throws Exception
	{
		ByteArrayInputStream inStream = new ByteArrayInputStream(docFile);
		// Load Document from inStream
		Document doc = new Document(inStream);
		// remove watermark
		if (doc.getWatermark().getType() == WatermarkType.TEXT) {
			doc.getWatermark().remove();
		}
		// Save the modified document into out stream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		doc.save(baos, SaveFormat.PDF);
		return baos.toByteArray();
	}
	
	public File GetFilePDFFromDB(int id) throws SQLException
	{
		return new FileDAO().GetFilePDFFromDB(id);
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
				this.data = new FileBO().ConvertFileToPDF(this.data);
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