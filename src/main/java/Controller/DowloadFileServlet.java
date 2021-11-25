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

/**
 * Servlet implementation class DowloadFileServlet
 */
@WebServlet("/DownloadFileServlet")
public class DowloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DowloadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	       try {  
	           int id = 0;
	           try {
	               id = Integer.parseInt(request.getParameter("id"));
	           } catch (Exception e) {}
	           File file = new FileBO().GetFilePDFFromDB(id);
	           if (file == null) {
	               response.getWriter().write("Không tìm thấy file !");
	               return;
	           }
	           
	           String fileName = file.getFileName();
	           String contentType = this.getServletContext().getMimeType(fileName);
	           response.setHeader("Content-Type", contentType);
	           response.setHeader("Content-Length", String.valueOf(file.getData().length()));
	           response.setHeader("Content-Disposition", "inline; filename=\"" + file.getFileName() + "\"");
	           Blob fileData = file.getData();
	           InputStream is = fileData.getBinaryStream();
	           byte[] bytes = new byte[1024];
	           int bytesRead;
	           while ((bytesRead = is.read(bytes)) != -1) {
	               response.getOutputStream().write(bytes, 0, bytesRead);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	} 
}
