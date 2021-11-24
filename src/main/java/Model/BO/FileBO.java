package Model.BO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.aspose.words.*;

import Model.DAO.FileDAO;

public class FileBO {

	public byte[] ConvertFileToPDF(byte[] docFile) throws Exception
	{
		ByteArrayInputStream inStream = new ByteArrayInputStream(docFile);
		
		// Load Document from inStream
		Document doc = new Document(inStream);
		
		// Save the modified document into out stream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		doc.save(baos, SaveFormat.PDF);
		return baos.toByteArray();
	}
	
	public byte[] DownloadFile()
	{
		return FileDAO.DowloadFile();
	}
}
