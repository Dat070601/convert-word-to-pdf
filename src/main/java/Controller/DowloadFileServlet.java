package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.File;
import Model.BO.FileBO;

@WebServlet("/DownloadFileServlet")
public class DowloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DowloadFileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       try {  
	           int id = 0;
	           try {
	               id = Integer.parseInt(request.getParameter("id"));
	           } catch (Exception e) {}
	           File file = new FileBO().GetFilePDFFromDB(id);
	           if (file == null) {
	               response.getWriter().write("Không tìm thấy file!");
	               return;
	           }
	           
	           String file_name = file.getFileName();
	           String content_type = this.getServletContext().getMimeType(file_name);
	           response.setHeader("Content-Type", content_type);
	           response.setHeader("Content-Length", String.valueOf(file.getData().length()));
	           response.setHeader("Content-Disposition", "inline; filename=\"" + file.getFileName() + "\"");
	           Blob file_data = file.getData();
	           InputStream is = file_data.getBinaryStream();
	           byte[] bytes = new byte[1024];
	           int bytes_read;
	           while ((bytes_read = is.read(bytes)) != -1) {
	               response.getOutputStream().write(bytes, 0, bytes_read);
	           }
	           is.close();

	       } catch (Exception e) {
	           throw new ServletException(e);
	       } 
	       finally
	       {
	    	   new FileBO().CloseConQuietly();
	       }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	} 
}
