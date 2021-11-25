package Model.BO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import com.aspose.words.*;

import Model.BEAN.File;
import Model.DAO.FileDAO;

public class FileBO {
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
