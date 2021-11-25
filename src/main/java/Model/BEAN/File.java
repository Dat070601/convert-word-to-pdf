package Model.BEAN;

import java.sql.Blob;
import java.sql.Date;

public class File {
	private int id_file;
	private int id_user;
	private Date date;
	private String file_name;
	private boolean status; 
	private Blob data;
	
	public File() {}
	public File(int id_file, int id_user, Date date, String file_name, boolean status, Blob data) {
		super();
		this.id_file = id_file;
		this.id_user = id_user;
		this.date = date;
		this.file_name = file_name;
		this.status = status;
		this.data = data;
	}
	
	public int getFileID()
	{
		return id_file;
	}
	
	public void setFileID(int id_file)
	{
		this.id_file = id_file;
	}
	
	public int getUserID() {
		return id_user;
	}
	public void setUserID(int id_user) {
		this.id_user = id_user;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFileName() {
		return file_name;
	}
	public void setFileName(String file_name) {
		this.file_name = file_name;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Blob getData()
	{
		return data;
	}
	public void setData(Blob data)
	{
		this.data = data;
	}
}