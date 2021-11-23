package Model.BEAN;

import java.sql.Blob;
import java.sql.Date;

public class File {
	private int id_user;
	private Date date;
	private String file_name;
	private boolean status; 
	private Blob data;
	
	public File(int id_user, Date date, String file_name, int status, Blob data) {
		super();
		this.id_user = id_user;
		this.date = date;
		this.file_name = file_name;
		if(status == 0)
			this.status = false;
		else 
			this.status = true;
		this.data = data;
	}
	public File()
	{
		
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
	public void setStatus(int status) {
		if(status == 0)
			this.status = false;
		else
			this.status = true;
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